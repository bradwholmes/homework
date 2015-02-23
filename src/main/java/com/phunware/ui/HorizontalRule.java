package com.phunware.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

public class HorizontalRule extends RecyclerView.ItemDecoration {
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
