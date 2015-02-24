package com.phunware.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.*;
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
    private Venue mVenue;

    public static Fragment create(Venue venue) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_VENUE, venue);

        VenueDetails fragment = new VenueDetails();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Bundle arguments = getArguments();
        if (arguments == null) return;

        mVenue = (Venue) arguments.getSerializable(KEY_VENUE);
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.share, menu);

        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat
                .getActionProvider(menu.findItem(R.id.menu_item_share));
        setupShareAction(shareActionProvider);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageView photo = (ImageView) view.findViewById(R.id.photo);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView address = (TextView) view.findViewById(R.id.address);
        ListView listView = (ListView) view.findViewById(R.id.schedule);

        if(mVenue == null) return;

        name.setText(mVenue.getName());
        address.setText(mVenue.getFullAddress());

        if(!Strings.isNullOrEmpty(mVenue.getImageUrl())) {
            Picasso.with(getActivity())
                    .load(mVenue.getImageUrl())
                    .into(photo);
        }

        ArrayList<ScheduleItem> schedule = mVenue.getSchedule();
        if (schedule != null) {
            listView.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.schedule_item, schedule));
        }
    }

    private void setupShareAction(ShareActionProvider shareActionProvider) {
        if(mVenue == null || shareActionProvider == null) return;

        Intent shareIntent = new Intent(Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, mVenue.getName() + " @ " + mVenue.getFullAddress());
        shareActionProvider.setShareIntent(shareIntent);
    }
}
