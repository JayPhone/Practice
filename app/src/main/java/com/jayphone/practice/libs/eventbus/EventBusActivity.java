package com.jayphone.practice.libs.eventbus;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by JayPhone on 2020/9/26
 */
public class EventBusActivity extends AppCompatActivity {
    private static final String TAG = "EventBusActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);

        EventBus.getDefault().post(new Event("我是消息"));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(Event event) {
        Log.e(TAG, "handleEvent: " + event.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
