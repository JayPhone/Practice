package com.jayphone.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;

/**
 * Created by JayPhone on 2020/9/5
 */
public class CircleSpreadSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = "CircleSpreadSurfaceView";

    private SurfaceHolder mHolder;

    private int mSurfaceWidth = 0;
    private int mSurfaceHeight = 0;

    private Paint mPaint;
    //控制绘画线程的标志位
    private boolean mIsDrawing = false;
    //圆是否在扩散，到达边缘后收缩
    private boolean mIsSpread = true;
    //圆的半径
    private int mRadius = 0;
    //圆扩大的速度
    private int mSpeed = 3;
    //是否是纵向
    private boolean mVertical;

    public CircleSpreadSurfaceView(Context context) {
        super(context);
        initView();
    }

    public CircleSpreadSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(BLUE);

        mHolder = getHolder();
        //为SurfaceHolder添加一个SurfaceHolder.Callback回调接口
        mHolder.addCallback(this);
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            Canvas canvas = mHolder.lockCanvas();
            if (canvas == null) {
                return;
            }
            canvas.drawColor(BLACK);
            if (mIsSpread) {
                mRadius += mSpeed;
            } else {
                mRadius -= mSpeed;
            }
            int range;
            if (mVertical) {
                range = mSurfaceWidth / 2;
            } else {
                range = mSurfaceHeight / 2;
            }
            if (mRadius >= range) {
                mIsSpread = false;
                mRadius -= mSpeed;
            } else if (mRadius <= 0) {
                mIsSpread = true;
                mRadius += mSpeed;
            }
            canvas.drawCircle(mSurfaceWidth / 2, mSurfaceHeight / 2, mRadius, mPaint);
            mHolder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e(TAG, "surfaceCreated: ");
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.e(TAG, "surfaceChanged width: " + width + " height: " + height);
        mHolder = holder;
        mRadius = 0;
        mIsSpread = true;
        mSurfaceWidth = width;
        mSurfaceHeight = height;
        mVertical = height > width;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e(TAG, "surfaceDestroyed: ");
        mIsDrawing = false;
    }
}
