package com.phunware.espresso.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import com.phunware.R;
import com.phunware.activity.HomeScreen;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
public class HomeScreenTest extends ActivityInstrumentationTestCase2<HomeScreen> {

    public HomeScreenTest() {
        super(HomeScreen.class);
    }

    @Override protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testToolbarShouldHaveAppName() {
        onView(withText(R.string.app_name))
                .check(matches(isDisplayed()));
    }

    public void testShowOneVenue() {
        onView(withText("Venue"))
                .check(matches(isDisplayed()));
        onView(withText("Address"))
                .check(matches(isDisplayed()));
    }
}
