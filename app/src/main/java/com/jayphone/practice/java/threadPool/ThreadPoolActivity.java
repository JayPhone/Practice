package com.jayphone.practice.java.threadPool;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by JayPhone on 2020/6/1
 */
public class ThreadPoolActivity extends AppCompatActivity {
    private static final String TAG = "ThreadPoolActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        singleThreadExecutor();
//        fixedThreadPool();
//        cachedThreadPool();
        scheduledThreadPool();
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     */
    private void singleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "singleThreadExecutor: name " + Thread.currentThread().getName() + " index " + index);
            });
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            });
        }
    }

    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     */
    private void fixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "fixedThreadPool: name " + Thread.currentThread().getName() + " index " + index);
            });
        }
    }

    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     */
    private void cachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "cachedThreadPool: name " + Thread.currentThread().getName() + " index " + index);
            });
        }
    }

    /**
     * 创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
     */
    private void scheduledThreadPool() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        executorService.scheduleAtFixedRate(() -> {
            Log.e(TAG, "scheduledThreadPool: name " + Thread.currentThread().getName());
        }, 100, 100, TimeUnit.MILLISECONDS);
    }

    private void customThreadPool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,//线程池长期维持的线程数，即使线程处于Idle状态，也不会回收。
                10,//线程数的上限
                1,//超过corePoolSize的线程的idle时长
                TimeUnit.MINUTES,//超过这个时间，多余的线程会被回收
                new ArrayBlockingQueue<>(512),//任务的排队队列
                Executors.defaultThreadFactory(),//新线程的产生方式
                new ThreadPoolExecutor.DiscardPolicy());//拒绝策略
//        threadPoolExecutor.execute();
    }

}
