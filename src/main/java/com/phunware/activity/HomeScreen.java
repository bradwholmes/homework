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
    }
}
