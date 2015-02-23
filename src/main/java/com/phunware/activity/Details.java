package com.phunware.activity;

import android.os.Bundle;
import com.phunware.R;

public class Details extends BaseActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle(getString(R.string.details_title));
    }
}
