package com.jayphone.android.animation.property;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jayphone.practice.R;

/**
 * Created by JayPhone on 2021/1/10
 */
public class PropertyAnimationActivity extends AppCompatActivity {
    private static final String TAG = "PropertyAnimationActivi";

    ImageView mIvBall;
    Button mBtnStart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        mIvBall = findViewById(R.id.ivBall);
        mBtnStart = findViewById(R.id.btnStart);

        mBtnStart.setOnClickListener(v -> {
            //设置自定义的TypeEvaluator，起始属性
            ValueAnimator valueAnimator = ValueAnimator.ofObject(new ParabolaTypeEvaluator(), new Point(0, 0));
            //设置时间
            valueAnimator.setDuration(1500);
            //设置线性时间插值器
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Point point = (Point) animation.getAnimatedValue();
                    mIvBall.setX(point.x);
                    mIvBall.setY(point.y);
                    Log.e(TAG, point.x + ":" + point.y);
                }
            });
            valueAnimator.start();
        });
    }
}
