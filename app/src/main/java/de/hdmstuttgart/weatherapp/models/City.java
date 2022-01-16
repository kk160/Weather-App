package de.hdmstuttgart.weatherapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO to represent the API model city
 * */
public class City {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coord")
    @Expose
    private Coord coord;
    @SerializedName("country")
    @Expose
    private String country;

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }
}
