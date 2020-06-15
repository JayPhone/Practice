package com.jayphone.android.view;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jayphone.practice.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JayPhone on 2020/6/15
 */
public class WebViewActivity extends AppCompatActivity {
    @BindView(R.id.wwContent)
    WebView wwContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);

        wwContent.loadUrl("https://m.bilibili.com/");
    }
}
