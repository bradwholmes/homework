package com.phunware.activity;

import android.os.Bundle;
import com.phunware.R;
import com.phunware.api.PhunwareS3Service;
import com.phunware.fragment.VenueList;

import javax.inject.Inject;

public class HomeScreen extends BaseActivity {

    @Inject PhunwareS3Service phunwareS3Service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_view, new VenueList(), VenueList.class.getSimpleName())
                .commit();
    }
}
