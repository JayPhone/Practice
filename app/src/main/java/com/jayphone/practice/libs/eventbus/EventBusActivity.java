package com.jayphone.practice.libs.eventbus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jayphone.practice.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by JayPhone on 2020/9/17
 */
public class EventBusActivity extends AppCompatActivity {
    private static final String TAG = "EventBusActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        //获取默认配置的EventBus
        EventBus.getDefault().register(this);

        Button publishBtn = findViewById(R.id.publish_btn);
        publishBtn.setOnClickListener(v -> EventBus.getDefault().post(new MessageEvent("I am message")));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandleMessageEvent(MessageEvent messageEvent) {
        Log.e(TAG, "onHandleMessageEvent: " + messageEvent.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
