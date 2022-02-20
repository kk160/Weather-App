package de.hdmstuttgart.weatherapp;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

import de.hdmstuttgart.weatherapp.models.Hour;

public class HourTest {
    // pre-implementation
    Hour hour1 = new Hour(0, "2021-01-12 09:15:00", 23.1);
    Hour hour2 = new Hour(9, "2021-06-05 10:22:00", 14.1);
    Hour hour3 = new Hour(33, "2021-11-20 06:00:00", 12.3);
    Hour hour4 = new Hour(20, "2022-11-01 16:45:00", 22.5);

    //testing if hour matches
    @Test
    public void getHourTest() throws ParseException {
        Assert.assertEquals("09:15", hour1.getHour());
        Assert.assertEquals("10:22", hour2.getHour());
        Assert.assertEquals("06:00", hour3.getHour());
        Assert.assertEquals("16:45", hour4.getHour());
        Assert.assertNotEquals("08:15", hour1.getHour());
        Assert.assertNotEquals("09:15", hour2.getHour());
        Assert.assertNotEquals("12:34", hour3.getHour());
        Assert.assertNotEquals("14:59", hour4.getHour());
    }

    //testing if temperature matches
    @Test
    public void getTempTest() throws ParseException {
        Assert.assertEquals(String.valueOf(23.1), hour1.getTemp());
        Assert.assertEquals(String.valueOf(14.1), hour2.getTemp());
        Assert.assertEquals(String.valueOf(12.3), hour3.getTemp());
        Assert.assertEquals(String.valueOf(22.5), hour4.getTemp());
        Assert.assertNotEquals(String.valueOf(15.0), hour1.getTemp());
        Assert.assertNotEquals(String.valueOf(18.9), hour2.getTemp());
        Assert.assertNotEquals(String.valueOf(13.1), hour3.getTemp());
        Assert.assertNotEquals(String.valueOf(17.6), hour4.getTemp());
    }
}
