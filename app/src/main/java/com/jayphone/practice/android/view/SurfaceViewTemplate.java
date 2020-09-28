package com.jayphone.practice.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by JayPhone on 2020/9/4
 */
public class SurfaceViewTemplate extends SurfaceView implements SurfaceHolder.Callback {
    public SurfaceViewTemplate(Context context) {
        super(context);
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SurfaceViewTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
