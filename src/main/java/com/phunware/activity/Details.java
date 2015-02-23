package com.phunware.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import com.phunware.R;
import com.phunware.domain.Venue;
import com.phunware.fragment.VenueDetails;

public class Details extends BaseActivity {

    public static Intent create(Context context, Venue venue) {
        return new Intent(context, Details.class)
                .putExtra(VenueDetails.KEY_VENUE, venue);
    }
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle(getString(R.string.details_title));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Venue venue = (Venue) getIntent().getSerializableExtra(VenueDetails.KEY_VENUE);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_view, VenueDetails.create(venue), VenueDetails.class.getSimpleName())
                .commit();
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
