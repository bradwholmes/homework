package com.phunware.espresso.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import com.google.common.collect.Lists;
import com.phunware.R;
import com.phunware.activity.Details;
import com.phunware.domain.ScheduleItem;
import com.phunware.domain.Venue;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class DetailsTest extends ActivityInstrumentationTestCase2<Details> {

    private static final String VENUE_NAME = "venue name";

    public DetailsTest() {
        super(Details.class);
    }

    @Override protected void setUp() throws Exception {
        super.setUp();

        Venue venue = new Venue()
                .setName(VENUE_NAME)
                .setCity("Austin")
                .setSchedule(Lists.newArrayList(
                        new ScheduleItem()
                ));

        setActivityIntent(Details.create(getInstrumentation().getContext(), venue));

        getActivity();
    }

    public void testShowVenueName() {
        onView(withId(R.id.venue_name))
                .check(matches(withText(VENUE_NAME)));
    }
}
