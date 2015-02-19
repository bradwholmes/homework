package com.phunware.activity;

import android.widget.TextView;
import com.phunware.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(RobolectricTestRunner.class)
public class HomeScreenTest {

    @Test
    public void testSomething() throws Exception {
        HomeScreen activity = Robolectric.buildActivity(HomeScreen.class)
                .create()
                .start()
                .resume()
                .get();

        TextView viewById = (TextView) activity.findViewById(R.id.hello);

        assertThat(viewById).hasText(R.string.hello_world);
    }
}
