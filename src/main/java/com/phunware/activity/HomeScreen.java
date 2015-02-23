package com.phunware.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.phunware.Injector;
import com.phunware.R;
import com.phunware.api.PhunwareS3Service;
import com.phunware.domain.Venue;
import com.phunware.ui.HorizontalRule;
import retrofit.RestAdapter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

public class HomeScreen extends ActionBarActivity {

    @Inject PhunwareS3Service phunwareS3Service;
    @Inject RestAdapter restAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);

        setContentView(R.layout.activity_home_screen);

        setSupportActionBar((Toolbar) findViewById(R.id.action_bar));

        final RecyclerView venues = (RecyclerView) findViewById(R.id.venues);
        venues.setHasFixedSize(true);
        venues.setLayoutManager(new LinearLayoutManager(this));
        venues.addItemDecoration(new HorizontalRule(this));

        phunwareS3Service.getVenues()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<List<Venue>>() {
                            @Override public void onCompleted() {
                            }

                            @Override public void onError(Throwable e) {
                                Toast.makeText(HomeScreen.this, "An error occurred retrieving data.", Toast.LENGTH_SHORT)
                                        .show();
                            }

                            @Override public void onNext(List<Venue> venuesData) {
                                venues.setAdapter(new VenuesAdapter(venuesData));
                            }
                        });
    }

    private static class VenuesAdapter extends RecyclerView.Adapter<VenueViewHolder> {
        private List<Venue> venues;

        public VenuesAdapter(List<Venue> venues) {
            this.venues = venues;
        }

        @Override public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.venue_summary, parent, false);
            return new VenueViewHolder(view);
        }

        @Override public void onBindViewHolder(VenueViewHolder holder, int position) {
            Venue venue = venues.get(position);
            holder.mName.setText(venue.getName());
            String fullAddress = venue.getFullAddress();
            holder.mAddress.setText(fullAddress);
        }

        @Override public int getItemCount() {
            return venues.size();
        }
    }

    private static class VenueViewHolder extends RecyclerView.ViewHolder {

        public final TextView mName;
        private final TextView mAddress;

        public VenueViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.name);
            mAddress = (TextView) itemView.findViewById(R.id.address);
        }
    }
}
