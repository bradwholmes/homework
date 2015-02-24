package com.phunware.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.phunware.R;
import com.phunware.api.PhunwareS3Service;
import com.phunware.domain.Venue;
import com.phunware.fragment.VenueDetails;
import com.phunware.fragment.VenueList;

import javax.inject.Inject;

public class HomeScreen extends BaseActivity implements DetailsLauncher {

    @Inject
    PhunwareS3Service phunwareS3Service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_view, new VenueList(), VenueList.class.getSimpleName())
                .commit();
    }

    @Override public void showVenue(Venue venue) {
        Fragment detailsFragment = getSupportFragmentManager().findFragmentById(R.id.details_view);
        if (detailsFragment != null && detailsFragment.isInLayout()) {
            Fragment fragment = VenueDetails.create(venue);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_view, fragment, fragment.getClass().getSimpleName())
                    .commit();
        } else {
            startActivity(Details.create(this, venue));
        }
    }
}
