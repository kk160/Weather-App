package de.hdmstuttgart.weatherapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import de.hdmstuttgart.weatherapp.viewmodels.HourViewModel;
import de.hdmstuttgart.weatherapp.viewmodels.LocationViewModel;
import de.hdmstuttgart.weatherapp.viewmodels.WeatherViewModel;

public class SearchFragment extends Fragment {

    private View view;
    private EditText searchField;
    private Button searchButton;
    private Button gpsButton;
    private OnInteractionListenerSF listener;
    private FusedLocationProviderClient fusedLocationProviderClient;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search_fragment, container, false);
        searchField = view.findViewById(R.id.searchField);
        searchButton = view.findViewById(R.id.searchButton);
        gpsButton = view.findViewById(R.id.gpsButton);
        setSearchButtonOnClick();
        setGpsButtonOnClick();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        if (context instanceof OnInteractionListenerSF) {
            listener = (OnInteractionListenerSF) context;
        } else {
            throw new ClassCastException(context + "must implement " + OnInteractionListenerSF.class.getSimpleName());
        }
        super.onAttach(context);
    }

    public void setSearchButtonOnClick(){
        searchButton.setOnClickListener(v -> {
            WeatherViewModel.getInstance(view.getContext()).setLocation(String.valueOf(searchField.getText()));
            HourViewModel hourViewModel = new ViewModelProvider(requireActivity()).get(HourViewModel.class);
            hourViewModel.select(0);
            listener.changeToLocationFragment();
        });
    }

    public void setGpsButtonOnClick(){
        gpsButton.setOnClickListener(v -> {
            HourViewModel hourViewModel = new ViewModelProvider(requireActivity()).get(HourViewModel.class);
            hourViewModel.select(0);

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(view.getContext());
            //check permission for location
            if (ActivityCompat.checkSelfPermission(view.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationViewModel.getInstance(view.getContext()).requestLocation(view.getContext());
                LocationViewModel.getInstance(view.getContext()).getLocation().observe(getViewLifecycleOwner(), string -> {
                    WeatherViewModel.getInstance(view.getContext()).setLocation(string);
                });
            }
            else {
                ActivityCompat.requestPermissions((Activity) view.getContext(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            }
            listener.changeToLocationFragment();
        });
    }

    public interface OnInteractionListenerSF{
        void changeToLocationFragment();
    }
}
