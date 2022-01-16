package de.hdmstuttgart.weatherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdmstuttgart.weatherapp.adapters.HourListAdapter;
import de.hdmstuttgart.weatherapp.models.Hour;
import de.hdmstuttgart.weatherapp.viewmodels.DayViewModel;
import de.hdmstuttgart.weatherapp.viewmodels.WeatherViewModel;

public class HoursFragment extends Fragment {

    private HourListAdapter hourListAdapter;
    private RecyclerView recyclerView;
    private View view;
    private List<Hour> hourList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hours_fragment, container, false);
        recyclerView = view.findViewById(R.id.hoursRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        initDayViewModel();
        return view;
    }

    /**
     * Method to initiate DayViewModel
     * */
    public void initDayViewModel(){
        DayViewModel dayViewModel = new ViewModelProvider(requireActivity()).get(DayViewModel.class);
        dayViewModel.getSelected().observe(getViewLifecycleOwner(), this::initWeatherViewModel);
    }

    /**
     * Method to initiate WeatherViewModel and initiate Adapter
     * */
    public void initWeatherViewModel(int index){
        WeatherViewModel.getInstance(view.getContext()).getData().observe(getViewLifecycleOwner(), weatherModel -> {
                hourList = new ArrayList<>();
                for(int i = index; i < weatherModel.getList().size(); i++) {
                    Hour hour = new Hour(i, weatherModel.getList().get(i).getDtTxt(), weatherModel.getList().get(i).getMain().getTemp());
                    if (hourList.size() == 0) {
                        hourList.add(hour);
                    }
                    if (hourList.size() != 0 && hour.equals(hourList.get(hourList.size() - 1))) {
                        hourList.add(hour);
                    }
                }
                hourList.remove(hourList.get(0));
                hourListAdapter = new HourListAdapter(view.getContext(), hourList);
                recyclerView.setAdapter(hourListAdapter);
        });
    }
}
