package de.hdmstuttgart.weatherapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdmstuttgart.weatherapp.adapters.DayListAdapter;
import de.hdmstuttgart.weatherapp.models.Day;
import de.hdmstuttgart.weatherapp.viewmodels.WeatherViewModel;

public class DaysFragment extends Fragment {

    public View view;
    private DayListAdapter dayListAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.days_fragment, container, false);
        initRecyclerView();
        initWeatherViewModel();
        return view;
    }

    public void initRecyclerView(){
        recyclerView = view.findViewById(R.id.daysRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    public void initWeatherViewModel(){
        WeatherViewModel.getInstance(view.getContext()).getData().observe(getViewLifecycleOwner(), weatherModel -> {
            List<Day> dayList = new ArrayList<>();
            for(int i = 0; i < weatherModel.getList().size(); i++){
                Day day = new Day(i, weatherModel.getList().get(i).getDtTxt());
                if(dayList.size()== 0){
                    dayList.add(day);
                }
                if(dayList.size()!=0 &&  !(day.equals(dayList.get(dayList.size()-1))) ) {
                    dayList.add(day);
                }
            }
            dayListAdapter = new DayListAdapter(view.getContext(), dayList);
            recyclerView.setAdapter(dayListAdapter);
        });
    }
}
