package com.jayphone.practice.java.threadPool;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by JayPhone on 2020/6/17
 */
public class ThreadLocalActivity extends AppCompatActivity {
    private static final String TAG = "ThreadLocalActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();
        integerThreadLocal.set(1);
        Log.e(TAG, "integerThreadLocal: " + integerThreadLocal.get());

        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set("hello world");
        Log.e(TAG, "stringThreadLocal: " + stringThreadLocal.get());
    }
}
