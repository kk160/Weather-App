package de.hdmstuttgart.weatherapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO to represent the API model Main
 * */
public class Main {
   @SerializedName("temp")
   @Expose
   private Double temp;
    @SerializedName("feels_like")
    @Expose
    private Double feelsLike;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;

    public Double getTemp() {
        return temp;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public Integer getHumidity() {
        return humidity;
    }
}
