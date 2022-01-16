package de.hdmstuttgart.weatherapp.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import de.hdmstuttgart.weatherapp.models.WeatherModel;
import de.hdmstuttgart.weatherapp.network.ApiService;
import de.hdmstuttgart.weatherapp.network.RetroInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class containing the WeatherViewModel instance and allowing to set and observe data
 * */
public class WeatherViewModel extends ViewModel {

    // Instance of WeatherViewModel
    private static WeatherViewModel instance;
    // Location is needed for the API Call
    private String location = "";
    //Data contains all weather data
    private MutableLiveData<WeatherModel> data = new MutableLiveData<>();

    /**
     * Method to get the WeatherViewModel instance
     *
     * @param context of the current state of the application
     * */
    public static WeatherViewModel getInstance(Context context){
        if(instance == null){
           instance = new ViewModelProvider((ViewModelStoreOwner) context).get(WeatherViewModel.class);
        }
        return instance;
    }

    /**
     * Method to get data
     * */
    public LiveData<WeatherModel> getData(){
        return data;
    }

    /**
     * Method that sets a new storage location and calls makeApiCall()
     *
     * @param location containing the name of the new city
     * */
    public void setLocation(String location){
        this.location = location;
        instance.makeApiCall();
    }

    /**
     * Method that makes the API call
     * */
    public void makeApiCall(){
        ApiService apiService = RetroInstance.getRetrofit().create(ApiService.class);
        Call<WeatherModel> call = apiService.getWeatherByLocation(location, "metric", "2f13b8bf3605882496dd60b569490007");
        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(@NonNull Call<WeatherModel> call, @NonNull Response<WeatherModel> response) {
                data.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<WeatherModel> call, @NonNull Throwable t) {
                Log.e("Oh noo.", t.getMessage());
            }
        });
    }
}
