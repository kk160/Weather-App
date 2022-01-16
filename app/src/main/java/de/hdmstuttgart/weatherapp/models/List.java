package de.hdmstuttgart.weatherapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO to represent the API Array List
 * */
public class List {

    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private java.util.List<Weather> weather = null;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    public Main getMain() {
        return main;
    }

    public java.util.List<Weather> getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }

    public String getDtTxt() {
        return dtTxt;
    }

}
