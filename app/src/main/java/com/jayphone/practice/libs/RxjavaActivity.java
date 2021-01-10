package com.jayphone.practice.libs;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JayPhone on 2020/3/24
 */
public class RxjavaActivity extends AppCompatActivity {
    private static final String TAG = "RxjavaActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hook方法，可以拦截Observable
//        RxJavaPlugins.setOnObservableAssembly(new Function<Observable, Observable>() {
//            @Override
//            public Observable apply(Observable observable) throws Exception {
//                return null;
//            }
//        });

//        base();
        useZip();
    }

    private void base() {
        Observable
                .create((ObservableOnSubscribe<Integer>) emitter -> {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onComplete();
                })
                .subscribeOn(Schedulers.io())
                .map(integer -> {
                    Log.e(TAG, "map: " + Thread.currentThread().getName());
                    return integer + "";
                })
                .observeOn(AndroidSchedulers.mainThread())
                .filter(s -> {
                    Log.e(TAG, "filter: " + Thread.currentThread().getName());
                    return true;
                })
                .observeOn(Schedulers.io())
                .doOnNext(s -> Log.e(TAG, "doOnNext: " + Thread.currentThread().getName()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + Thread.currentThread().getName());
                        Log.e(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void useZip() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Observable
                .fromIterable(list)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(integer + " is means: ");
                    }
                })
                .zipWith(list, new BiFunction<String, Integer, String>() {
                    @Override
                    public String apply(String s, Integer integer) throws Exception {
                        return s + integer;
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
