package com.phunware.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.common.base.Strings;
import com.phunware.R;
import com.phunware.domain.ScheduleItem;
import com.phunware.domain.Venue;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        ImageView photo = (ImageView) view.findViewById(R.id.photo);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView address = (TextView) view.findViewById(R.id.address);
        ListView listView = (ListView) view.findViewById(R.id.schedule);

        Bundle arguments = getArguments();
        if(arguments == null) return;

        Venue venue = (Venue) arguments.getSerializable(KEY_VENUE);
        if(venue == null) return;

        name.setText(venue.getName());
        address.setText(venue.getFullAddress());

        if(!Strings.isNullOrEmpty(venue.getImageUrl())) {
            Picasso.with(getActivity())
                    .load(venue.getImageUrl())
                    .into(photo);
        }

        ArrayList<ScheduleItem> schedule = venue.getSchedule();
        if (schedule != null) {
            listView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.schedule_item, schedule));
        }
    }
}
