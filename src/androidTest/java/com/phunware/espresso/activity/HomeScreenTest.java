package com.phunware.espresso.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import com.phunware.ApplicationModule;
import com.phunware.Injector;
import com.phunware.R;
import com.phunware.activity.HomeScreen;
import com.phunware.api.PhunwareS3Service;
import com.phunware.domain.Venue;
import dagger.Module;
import dagger.Provides;
import rx.Observable;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.util.Arrays.asList;

@LargeTest
public class HomeScreenTest extends ActivityInstrumentationTestCase2<HomeScreen> {

    private static final String mVenueName = "Sports Authority Field @ Mile High";
    private static final String mAddress = "1701 Bryant Street #700";
    private static final String mCity = "Denver";
    private static final String mState = "CO";

    public HomeScreenTest() {
        super(HomeScreen.class);
    }

    @Override protected void setUp() throws Exception {
        super.setUp();

        Injector.replaceWithTestModule(new MockWebServerModule());

        getActivity();
    }

    public void testToolbarShouldHaveAppName() {
        onView(withText(R.string.app_name))
                .check(matches(isDisplayed()));
    }

    public void testShowOneVenue() {
        onView(withText(mVenueName))
                .check(matches(isDisplayed()));
        onView(withText(mAddress + ", " + mCity + ", " + mState))
                .check(matches(isDisplayed()));
    }

    public void testOpenDetailsForVenue() {
        onView(withText(mVenueName))
                .perform(click());

        onView(withText(R.string.details_title))
                .check(matches(isDisplayed()));
    }

    @Module(library = true, injects = HomeScreen.class, includes = ApplicationModule.class, overrides = true)
    public static class MockWebServerModule {
        @Provides PhunwareS3Service providesPhunwareS3Service() {
            return new PhunwareS3Service() {
                @Override public Observable<List<Venue>> getVenues() {
                    Venue venue = new Venue();
                    venue.setName(mVenueName);
                    venue.setAddress(mAddress);
                    venue.setCity(mCity);
                    venue.setState(mState);

                    return Observable.just(asList(venue));
                }
            };
        }
    }
}
