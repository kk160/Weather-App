package de.hdmstuttgart.weatherapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO to represent the API model from OpenWeatherMap
 * */
public class WeatherModel {

  @SerializedName("list")
  @Expose
  private java.util.List<List> list = null;
  @SerializedName("city")
  @Expose
  private City city;

  public java.util.List<List> getList() {
    return list;
  }

  public City getCity() {
    return city;
  }
}
