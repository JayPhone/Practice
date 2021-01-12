package com.jayphone.android.animation.property;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    TextView mTvCount;
    TextView mTvBgAlpha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        mIvBall = findViewById(R.id.ivBall);
        mBtnStart = findViewById(R.id.btnStart);
        mBtnStart.setOnClickListener(v -> {
//            //设置自定义的TypeEvaluator，起始属性
//            ValueAnimator valueAnimator = ValueAnimator.ofObject(new ParabolaTypeEvaluator(), new Point(0, 0));
//            //设置时间
//            valueAnimator.setDuration(1500);
//            //设置线性时间插值器
//            valueAnimator.setInterpolator(new LinearInterpolator());
//            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    Point point = (Point) animation.getAnimatedValue();
//                    mIvBall.setX(point.x);
//                    mIvBall.setY(point.y);
//                    Log.e(TAG, point.x + ":" + point.y);
//                }
//            });
//            valueAnimator.start();

        });

        mTvCount = findViewById(R.id.tvCount);
        mTvCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 5);
                valueAnimator.setDuration(5000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();
                        Log.e(TAG, "onAnimationUpdate: " + value);
                        mTvCount.setText(String.valueOf(value));
                    }
                });
                valueAnimator.start();
            }
        });

        mTvBgAlpha = findViewById(R.id.tvBgAlpha);
        mTvBgAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTvBgAlpha, "alpha", 1f, 0f, 1f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
            }
        });
    }
}
