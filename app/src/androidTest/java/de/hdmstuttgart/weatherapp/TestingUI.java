package de.hdmstuttgart.weatherapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import org.junit.Test;


@LargeTest
@RunWith(AndroidJUnit4.class)

public class TestingUI {


    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void TestingUI() {

        onView(withId(R.id.iconSearch))
                .perform(click());

        onView(withId(R.id.searchField))
                .perform(replaceText("Stuttgart"), closeSoftKeyboard());

        onView(withId(R.id.searchButton))
                .perform(click());

        onView(withId(R.id.iconSearch))
                .perform(click());

        onView(withId(R.id.searchField))
                .perform(replaceText("München"), closeSoftKeyboard());

        onView(withId(R.id.searchButton))
                .perform(click());

        onView(withId(R.id.iconSearch))
                .perform(click());

        onView(withId(R.id.searchField))
                .perform(replaceText("Augsburg"), closeSoftKeyboard());

        onView(withId(R.id.searchButton))
                .perform(click());

    }
}


