package de.hdmstuttgart.weatherapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestingUI {


    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void changeLocation() {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.iconSearch))
                .perform(click());

        onView(withId(R.id.searchField))
                .perform(replaceText("Stuttgart"), closeSoftKeyboard());

        onView(withId(R.id.searchButton))
                .perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.location))
                .check(matches(withText("Stuttgart")));

        onView(withId(R.id.iconSearch))
                .perform(click());

        onView(withId(R.id.searchField))
                .perform(replaceText("München"), closeSoftKeyboard());

        onView(withId(R.id.searchButton))
                .perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.location))
                .check(matches(withText("Munich")));

        onView(withId(R.id.iconSearch))
                .perform(click());

        onView(withId(R.id.searchField))
                .perform(replaceText("Augsburg"), closeSoftKeyboard());

        onView(withId(R.id.searchButton))
                .perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.location))
                .check(matches(withText("Augsburg")));

    }
}


