package com.jayphone.android.animation.property;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PointAnimatorView extends View {

    public static final float RADIUS = 50f;
    private Point mCurrentPoint;
    private Paint mPaint;
    private String mColor;

    public PointAnimatorView(Context context) {
        this(context, null);
    }

    public PointAnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurrentPoint == null) {
            mCurrentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = mCurrentPoint.x;
        float y = mCurrentPoint.y;
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    private void startAnimation() {
        Point startPoint = new Point(RADIUS, RADIUS);
        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);
        ValueAnimator pointAnim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        pointAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Point point = (Point) animation.getAnimatedValue();
                mCurrentPoint.x = point.x;
                mCurrentPoint.y = point.y;
                invalidate();
            }
        });

        ObjectAnimator colorAnim = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), "#0000FF", "#FF0000");
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(pointAnim).with(colorAnim);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }
}
