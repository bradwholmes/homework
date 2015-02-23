package com.phunware.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.phunware.R;
import com.phunware.domain.Venue;

public class VenueDetails extends Fragment {

    public static final String KEY_VENUE = "key_venue";

    public static Fragment create(Venue venue) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_VENUE, venue);

        VenueDetails fragment = new VenueDetails();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        Venue venue = (Venue) getArguments().getSerializable(KEY_VENUE);

        TextView venueName = (TextView) view.findViewById(R.id.venue_name);
        venueName.setText(venue.getName());
    }
}
