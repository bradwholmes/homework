package com.phunware.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

public class VenueList extends Fragment {

    @Inject PhunwareS3Service phunwareS3Service;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_venue_list, container, false);
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        final RecyclerView venues = (RecyclerView) view.findViewById(R.id.venues);
        venues.setHasFixedSize(true);
        venues.setLayoutManager(new LinearLayoutManager(getActivity()));
        venues.addItemDecoration(new HorizontalRule(getActivity()));

        phunwareS3Service.getVenues()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Venue>>() {
                    @Override public void onCompleted() {
                    }

                    @Override public void onError(Throwable e) {
                        if(getActivity() == null) return;

                        Toast.makeText(getActivity(), "An error occurred retrieving data.", Toast.LENGTH_SHORT)
                                .show();
                    }

                    @Override public void onNext(List<Venue> venuesData) {
                        if(getView() != null) {
                            venues.setAdapter(new VenuesAdapter(venuesData));
                        }
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
