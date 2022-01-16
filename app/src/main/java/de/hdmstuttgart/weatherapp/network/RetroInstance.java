package de.hdmstuttgart.weatherapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Class containing the instance of Retrofit
 * */
public class RetroInstance {

    // URL from the API
    private static final String BASE_URL = "https://api.openweathermap.org/";
    // Instance of Retrofit
    private static Retrofit retrofit;

    /**
     * Get Retrofit instance
     * */
    public static Retrofit getRetrofit() {
        if(retrofit == null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
