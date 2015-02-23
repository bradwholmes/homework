package com.phunware.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import com.phunware.Injector;
import com.phunware.R;

public abstract class BaseActivity extends ActionBarActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.inject(this);

        setContentView(R.layout.activity_base);
        setSupportActionBar((Toolbar) findViewById(R.id.action_bar));
    }
}
