package com.jayphone.android.view.dispatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.jayphone.practice.R;

/**
 * 1.验证事件传递流程
 * 2.验证cancel事件
 * 3.验证ViewGroup事件拦截
 */
public class DispatchActivity extends AppCompatActivity {
    private static final String TAG = "DispatchActivity";

    /**
     * 事件默认传递流程:
     * DispatchActivity.dispatchTouchEvent -> MyViewGroup.dispatchTouchEvent -> MyViewGroup.onInterceptTouchEvent
     * -> MyView.dispatchTouchEvent -> MyView.onTouchEvent -> MyViewGroup.onTouchEvent -> DispatchActivity.onTouchEvent
     * <p>
     * 同一个事件序列，如果子View(ViewGroup)没有处理该事件(没有消费事件)，
     * 那么后续事件就不会再传递到子View中。
     * DispatchActivity.dispatchTouchEvent -> DispatchActivity.onTouchEvent
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent: " + MotionEvent.actionToString(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e(TAG, "onTouchEvent: " + MotionEvent.actionToString(ev.getAction()));
        return super.onTouchEvent(ev);
    }
}
