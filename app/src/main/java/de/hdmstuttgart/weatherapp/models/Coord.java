package de.hdmstuttgart.weatherapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO to represent the API model Coord
 * */
public class Coord {
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }
}
