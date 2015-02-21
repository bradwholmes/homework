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
        venues.addItemDecoration(new HorizontalRule(this));
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

    private static class HorizontalRule extends RecyclerView.ItemDecoration {
        private Paint paint = new Paint();

        public HorizontalRule(Context context) {
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, displayMetrics);
            paint.setStrokeWidth(strokeWidth);
        }

        @Override public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getLeft() + parent.getPaddingLeft();
            int right = parent.getRight() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                int y = child.getBottom() + params.bottomMargin;
                canvas.drawLine(left, y, right, y, paint);
            }
        }
    }
}
