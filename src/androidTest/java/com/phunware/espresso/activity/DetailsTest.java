package com.phunware.espresso.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import com.google.common.collect.Lists;
import com.phunware.R;
import com.phunware.activity.Details;
import com.phunware.domain.ScheduleItem;
import com.phunware.domain.Venue;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

@LargeTest
public class DetailsTest extends ActivityInstrumentationTestCase2<Details> {

    private final String mVenueName = "venue name";
    private final String mCity = "Austin";
    private final String mScheduledTime = "Monday 2/23 8:00am to 12pm";

    public DetailsTest() {
        super(Details.class);
    }

    @Override protected void setUp() throws Exception {
        super.setUp();

        Venue venue = new Venue()
                .setName(mVenueName)
                .setCity(mCity)
                .setSchedule(Lists.newArrayList(
                        getScheduleItem(mScheduledTime)
                ));

        setActivityIntent(Details.create(getInstrumentation().getContext(), venue));

        getActivity();
    }

    public void testShowVenueInformation() {
        onView(withId(R.id.name))
                .check(matches(withText(mVenueName)));

        onView(withId(R.id.address))
                .check(matches(withText(mCity)));

        onData(anything())
                .inAdapterView(withId(R.id.schedule))
                .atPosition(0)
                .check(matches(withText(mScheduledTime)));
    }

    private ScheduleItem getScheduleItem(final String scheduledTime) {
        return new ScheduleItem() {
            @Override public String toString() {
                return scheduledTime;
            }
        };
    }
}
