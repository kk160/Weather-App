package de.hdmstuttgart.weatherapp.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Hour extends Day {

    //Temperature
    private double temp;

    /**
     * Hour constructor
     *
     * @param dateId Integer
     * @param date String
     * @param temp double
     * */
    public Hour(int dateId, String date, double temp){
       super(dateId, date);
        this.temp = temp;
    }

    /**
     * Method to get the time
     *
     * throws ParseException in case of an error
     * */
    public  String getHour() throws ParseException {
        return new SimpleDateFormat("HH:mm").format(getDate());
    }

    /**
     * Method to get the temperature
     * */
    public String getTemp() {
        return String.valueOf(temp);
    }
}
