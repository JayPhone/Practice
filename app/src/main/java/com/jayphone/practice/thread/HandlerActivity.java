package com.jayphone.practice.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by JayPhone on 2020/3/11
 * 创建Handler时,默认和当前线程的Looper进行关联，如果当前线程为子线程，则需要调用Looper.prepare(),否则会报错
 * Looper为一个ThreadLocal对象，是线程内部的变量，所以保证每个线程都有一个Looper
 * 调用Looper.prepare()时会在内部创建一个MessageQueue,Handler发送的消息会发送到其中
 * Looper.loop()方法为一个死循环，会不断从消息队列中获取消息并分发到指定Handler去处理
 */
public class HandlerActivity extends AppCompatActivity {
    private static final String TAG = "HandlerActivity";

    public static final int MSG_WHAT_1 = 1;
    public static final int MSG_WHAT_2 = 2;

    private Handler mUnLeakHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mUnLeakHandler = new UnLeakHandler();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    Message m = Message.obtain();
//                    m.what = MSG_WHAT_1;
//                    mUnLeakHandler.sendMessage(m);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                mUnLeakHandler = new UnLeakHandler(getMainLooper(), new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        Log.e(TAG, "handleMessage: callBack");
                        //返回true，则不会继续调用handleMessage()方法，否则会调用
                        return false;
                    }
                });
                while (true) {
                    //发送普通消息
                    Message m = Message.obtain();
                    m.what = MSG_WHAT_1;
                    m.setTarget(mUnLeakHandler);
                    m.sendToTarget();
//                    mUnLeakHandler.sendMessage(m);

                    //发送带Runnable的消息,使用post发送消息，不会回调handleMessage()
                    mUnLeakHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "handleMessage: run");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 主线程创建的Handler
     * 1.使用静态内部类来避免持有外部类的引用
     * 2.使用弱引用来持有activity的引用
     */
    private static class UnLeakHandler extends Handler {
        UnLeakHandler() {
            super();
        }

        UnLeakHandler(Looper looper) {
            super(looper);
        }

        UnLeakHandler(Callback callback) {
            super(callback);
        }

        UnLeakHandler(Looper looper, Callback callback) {
            super(looper, callback);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MSG_WHAT_1:
                    Log.e(TAG, "handleMessage1 thread: " + Thread.currentThread().getName());
                    break;
                case MSG_WHAT_2:
                    Log.e(TAG, "handleMessage2 thread: " + Thread.currentThread().getName());
                    break;
            }
        }
    }
}
