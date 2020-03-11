package com.jayphone.practice.thread;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by JayPhone on 2020/3/11
 * 在子线程中回调主线程设置的监听，监听器中的方式是在子线程中回调
 */
public class CallBackActivity extends AppCompatActivity {
    private static final String TAG = "CallBackActivity";
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MyThread(new Callback() {
            @Override
            public void onEvent() {
                Log.e(TAG, "onEvent: " + Thread.currentThread().getName());
            }
        }).start();
    }

    class MyThread extends Thread {
        private Callback mCallback;

        public MyThread(Callback callback) {
            mCallback = callback;
        }

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onEvent();
                }
            });

        }
    }

    public interface Callback {
        void onEvent();
    }
}
