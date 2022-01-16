package de.hdmstuttgart.weatherapp.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Day {

    // Date in format of yyyy-MM-dd hh:mm:ss
    private String date;
    // Identification number of date
    private final int dateId;

    /**
     * Day constructor
     *
     * @param  dateId Integer
     * @param  date String
     * */
    public Day(int dateId, String date){
        this.dateId= dateId;
        this.date = date;
    }

    /**
     * Method to get dateId
     * */
    public int getDateId() {
        return dateId;
    }

    /**
     * Method to get the current pattern of date
     *
     * throws ParseException in case of an error
     * */
    public Date getDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.parse(date);
    }

    /**
     * Method to get the day of the month
     *
     * throws ParseException in case of an error
     * */
    public String getDayNumber() throws ParseException {
        return new SimpleDateFormat("dd").format(getDate());
    }

    /**
     * Method to get the day of the week
     *
     * throws ParseException in case of an error
     * */
    public String getDayName () throws ParseException {
        return new SimpleDateFormat("EEE").format(getDate());
    }

    /**
     * Method to get the month
     *
     * throws ParseException in case of an error
     * */
    public String getMonth () throws ParseException{
        return new SimpleDateFormat("MMM").format(getDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        try {
            return Objects.equals(getDayName(), day.getDayName());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int hashCode() {
        try {
            return Objects.hash(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
