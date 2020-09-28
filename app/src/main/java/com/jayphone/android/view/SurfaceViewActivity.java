package com.jayphone.android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jayphone.practice.R;

public class SurfaceViewActivity extends AppCompatActivity {

    private CircleSpreadSurfaceView mCircleSpreadSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        mCircleSpreadSurfaceView = findViewById(R.id.circle_spread_sv);
    }
}
