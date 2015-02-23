package com.phunware.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import com.phunware.R;

public class Details extends ActionBarActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_screen);
        setSupportActionBar((Toolbar) findViewById(R.id.action_bar));
        getSupportActionBar().setTitle(getString(R.string.details_title));
    }
}
