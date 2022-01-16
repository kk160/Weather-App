package de.hdmstuttgart.weatherapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * POJO to represent the API model Wind
 * */
public class Wind {
   @SerializedName("speed")
   @Expose
   private Double speed;

   public Double getSpeed() {
        return speed;
    }
}
