package com.phunware.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.phunware.R;

public class HomeScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        setSupportActionBar((Toolbar) findViewById(R.id.action_bar));

        RecyclerView venues = (RecyclerView) findViewById(R.id.venues);
        venues.setHasFixedSize(true);
        venues.setLayoutManager(new LinearLayoutManager(this));
        venues.setAdapter(new VenuesAdapter());
    }

    private static class VenuesAdapter extends RecyclerView.Adapter<VenueViewHolder> {
        @Override public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.venue_summary, parent, false);
            return new VenueViewHolder(view);
        }

        @Override public void onBindViewHolder(VenueViewHolder holder, int position) {

        }

        @Override public int getItemCount() {
            return 1;
        }
    }

    private static class VenueViewHolder extends RecyclerView.ViewHolder {
        public VenueViewHolder(View itemView) {
            super(itemView);
        }
    }
}
