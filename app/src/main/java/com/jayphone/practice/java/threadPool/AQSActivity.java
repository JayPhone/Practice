package com.jayphone.practice.java.threadPool;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by JayPhone on 2020/6/17
 */
public class AQSActivity extends AppCompatActivity {
    private static final String TAG = "AQSActivity";
    private int count = 0;
    //独占式锁
    private ReentrantLock mReentrantLock = new ReentrantLock();
    //自定义独占式锁
    private Mutex mMutex = new Mutex();
    //共享式锁
    private Semaphore mSemaphore = new Semaphore(3);

    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Runnable reentrantRunnable = () -> {
            for (int i = 0; i < 10; i++) {
                incrementReentrantLock();
            }
            Log.e(TAG, "count: " + count + " threadName: " + Thread.currentThread().getName());
        };

        Runnable mutexRunnable = () -> {
            for (int i = 0; i < 100; i++) {
                incrementMutex();
            }
        };

        Runnable semaphoreRunnable = () -> {
            enter();
        };

        for (int i = 0; i < 10; i++) {
//            Thread thread = new Thread(reentrantRunnable);
            Thread thread = new Thread(semaphoreRunnable);
            thread.start();
        }
    }

    private void incrementReentrantLock() {
        mReentrantLock.lock();
        try {
            count++;
            Log.e(TAG, "count: " + count + " threadName: " + Thread.currentThread().getName());
        } finally {
            mReentrantLock.unlock();
        }
    }

    private void incrementMutex() {
        mMutex.lock();
        try {
            count++;
            Log.e(TAG, "count: " + count + " threadName: " + Thread.currentThread().getName());
        } finally {
            mMutex.unlock();
        }
    }

    /**
     * 同一时间可有3个线程进入
     */
    private void enter() {
        try {
            mSemaphore.acquire();
            Thread.sleep(2000);
            Log.e(TAG, "threadName: " + Thread.currentThread().getName() + " time: " + mSimpleDateFormat.format(new Date(System.currentTimeMillis())));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mSemaphore.release();
        }
    }
}
