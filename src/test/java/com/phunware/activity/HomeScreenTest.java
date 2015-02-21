package com.phunware.activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class HomeScreenTest {

    @Test public void shouldCreateActivity() throws Exception {
        HomeScreen activity = Robolectric.buildActivity(HomeScreen.class)
                .create()
                .start()
                .resume()
                .get();
    }
}
