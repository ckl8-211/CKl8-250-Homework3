package com.example.rlam.ckl8_250_homework3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Margaret on 5/4/15.
 *
 * This sample app demos how to create a custom View with some attributes defined in xml
 */
public class CustomView extends View {
    GestureDetector gestureDetector;

    private int circleColor;
    private Paint mCirclePaint;                         // Paint for drawing
    private Point mCenter;                              // Center of the Custom View
    private final static float mRadius = 50;            // Radius for the circle

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, new GestureListener());
        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCenter = new Point();

        if(attrs!=null) {
            // Retrieve all the attributes set in xml
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0);
            // Get the circle color
            circleColor = a.getInteger(R.styleable.CustomView_circleColor, 0);
            a.recycle();
        }

        // Set the paint color
        mCirclePaint.setColor(circleColor);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        // event when double tap occurs
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            // Set center point x to  uppper left corner
            mCenter.x = (int) mRadius;
            // Set center point y to be h uppper left corner
            mCenter.y = (int) mRadius;
            invalidate();
            return true;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // If there is a size change
        if ((w!=oldw) || (h!=oldh)) {
            // Set center point x to  uppper left corner
            mCenter.x = (int) mRadius;
            // Set center point y to be h uppper left corner
            mCenter.y = (int) mRadius;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_MOVE:
                mCenter.x = (int) x;
                mCenter.y =(int) y;
                this.invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw a circle at the center of the view
        canvas.drawCircle(mCenter.x,                // X-coordinate of circle center
                            mCenter.y,              // Y-coordinate of circle center
                            mRadius,                // Circle radius
                            mCirclePaint);          // Circle Paint
    }
}
