package de.hdmstuttgart.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.hdmstuttgart.weatherapp.viewmodels.WeatherViewModel;

public class LocationFragment extends Fragment {

    private TextView locationText;
    private ImageView iconSearch;
    private View view;
    private OnInteractionListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.location_fragment, container, false);
        locationText = view.findViewById(R.id.location);
        iconSearch = view.findViewById(R.id.iconSearch);
        iconClicked();
        initWeatherViewModel();
        return view;
    }

    /**
     * Interface to change fragment
     * */
    public interface OnInteractionListener {
        void changeToSearchFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        if (context instanceof OnInteractionListener) {
            listener = (OnInteractionListener) context;
        } else {
            throw new ClassCastException(context + "must implement " + OnInteractionListener.class.getSimpleName());
        }
        super.onAttach(context);
    }

    /**
     * Method to initiate WeatherViewModel
     * */
    public void initWeatherViewModel(){
        WeatherViewModel.getInstance(view.getContext()).getData().observe(getViewLifecycleOwner(), weatherModel ->{
            locationText.setText(weatherModel.getCity().getName());
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    LoadingDialog.getLoadingDialog((Activity) getContext()).endLoadingDialog();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    /**
     * Method to change fragment when icon is clicked
     * */
    public void iconClicked(){
        iconSearch.setOnClickListener(v -> listener.changeToSearchFragment());
    }
}
