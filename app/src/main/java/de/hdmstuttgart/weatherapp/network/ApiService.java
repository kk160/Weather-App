package de.hdmstuttgart.weatherapp.network;

import de.hdmstuttgart.weatherapp.models.WeatherModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * API interface for Retrofit
 * */
public interface ApiService {
    @GET("data/2.5/forecast?")
    Call<WeatherModel> getWeatherByLocation(@Query("q") String location, @Query("units") String units, @Query("apikey") String apiKey);
}
