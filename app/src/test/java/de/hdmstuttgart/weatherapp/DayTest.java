package de.hdmstuttgart.weatherapp;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

import de.hdmstuttgart.weatherapp.models.Day;

public class DayTest {
    Day day1 = new Day(0, "2021-01-12 09:15:00");
    Day day2 = new Day(9, "2021-06-05 10:22:00");
    Day day3 = new Day(33, "2021-11-20 06:00:00");
    Day day4 = new Day(20, "2022-11-01 16:45:00");

    @Test
    public void getDateIdTest(){
        Assert.assertEquals(0, day1.getDateId());
        Assert.assertEquals(9, day2.getDateId());
        Assert.assertEquals(33, day3.getDateId());
        Assert.assertEquals(20, day4.getDateId());
        Assert.assertNotEquals(9, day1.getDateId());
        Assert.assertNotEquals(20, day2.getDateId());
        Assert.assertNotEquals(0, day3.getDateId());
        Assert.assertNotEquals(33, day4.getDateId());
    }

    @Test
    public void getDayNumberTest() throws ParseException {
        Assert.assertEquals("12", day1.getDayNumber());
        Assert.assertEquals("05", day2.getDayNumber());
        Assert.assertEquals("20", day3.getDayNumber());
        Assert.assertEquals("01", day4.getDayNumber());
        Assert.assertNotEquals("14", day1.getDayNumber());
        Assert.assertNotEquals("01", day2.getDayNumber());
        Assert.assertNotEquals("31", day3.getDayNumber());
        Assert.assertNotEquals("02", day4.getDayNumber());
    }
}
