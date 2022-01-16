package de.hdmstuttgart.weatherapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;

import de.hdmstuttgart.weatherapp.viewmodels.HourViewModel;
import de.hdmstuttgart.weatherapp.viewmodels.WeatherViewModel;

public class WeatherFragment extends Fragment {
    private TextView temp, tempMax, tempMin, humidity, wind, description, feelLike;
    private ImageView icon;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.weather_fragment, container, false);
        temp = view.findViewById(R.id.temp);
        tempMax = view.findViewById(R.id.temp_max);
        tempMin = view.findViewById(R.id.temp_min);
        humidity = view.findViewById(R.id.humidity);
        wind = view.findViewById(R.id.wind);
        description = view.findViewById(R.id.description);
        feelLike = view.findViewById(R.id.feel_like);
        icon = view.findViewById(R.id.iconWeather);

        initSharedViewModel();
        return view;
    }

    public void initSharedViewModel(){
        HourViewModel hourViewModel = new ViewModelProvider(requireActivity()).get(HourViewModel.class);
        hourViewModel.getSelected().observe(getViewLifecycleOwner(), this::initWeatherViewModel);
    }

    @SuppressLint("SetTextI18n")
    public void initWeatherViewModel(int index){
        WeatherViewModel.getInstance(view.getContext()).getData().observe(getViewLifecycleOwner(), weatherModel -> {
            temp.setText(weatherModel.getList().get(index).getMain().getTemp() + " °C");
            tempMax.setText("Max: " + weatherModel.getList().get(index).getMain().getTempMax() + " °C");
            tempMin.setText("Min: " + weatherModel.getList().get(index).getMain().getTempMin() + " °C");
            humidity.setText("Humidity: " + weatherModel.getList().get(index).getMain().getHumidity() + " %");
            wind.setText("Wind: " + weatherModel.getList().get(index).getWind().getSpeed() + " km/h");
            description.setText(weatherModel.getList().get(index).getWeather().get(0).getDescription());
            feelLike.setText("Feel like: " + weatherModel.getList().get(index).getMain().getFeelsLike() + " °C");
            String urlIcon = "https://openweathermap.org/img/wn/" + weatherModel.getList().get(index).getWeather().get(0).getIcon() + "@4x.png";
            Glide.with(view.getContext())
                    .load(urlIcon)
                    .into(icon);
        });
    }
}
