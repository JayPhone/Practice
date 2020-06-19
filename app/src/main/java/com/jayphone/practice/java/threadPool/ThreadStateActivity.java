package com.jayphone.practice.java.threadPool;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by JayPhone on 2020/6/19
 */
public class ThreadStateActivity extends AppCompatActivity {
    private static final String TAG = "ThreadStateActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                Log.e(TAG, "i = " + i);
            }
        });
        thread.start();
        thread.interrupt();
        //isInterrupted（）方法并不会清除中断状态
        Log.e(TAG, "第一次调用thread.isInterrupted()：" + thread.isInterrupted());
        Log.e(TAG, "第二次调用thread.isInterrupted()：" + thread.isInterrupted());

        Log.e(TAG, "第一次调用thread.interrupted()：" + thread.interrupted());
        Log.e(TAG, "第二次调用thread.interrupted()：" + thread.interrupted());
        Log.e(TAG, "thread是否存活：" + thread.isAlive());
    }
}
