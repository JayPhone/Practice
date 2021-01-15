package com.jayphone.android.animation.property;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
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

    PointAnimatorView mPointAnimatorView;
    Button mBtnStart;

    TextView mTvCount;
    TextView mTvBgAlpha;

    TextView mTvRotation;

    TextView mTvTranslation;

    TextView mTvScale;

    TextView mTvCompose;
    private ObjectAnimator mTranslationAnimator;

    TextView mTvXMLValue;

    TextView mTvXMLAlpha;

    TextView mTvXMLCompose;

    TextView mTvViewPropertyAnimator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        mPointAnimatorView = findViewById(R.id.pointAnimatorView);

        mTvCount = findViewById(R.id.tvCount);
        mTvCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 5);
                valueAnimator.setDuration(2000);
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
                objectAnimator.setDuration(2000);
                objectAnimator.start();
            }
        });

        mTvRotation = findViewById(R.id.tvRotation);
        mTvRotation.setOnClickListener(v -> {
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTvRotation, "rotation", 0f, 360f);
            objectAnimator.setDuration(2000);
            objectAnimator.start();
        });

        mTvTranslation = findViewById(R.id.tvTranslation);
        mTvTranslation.setOnClickListener(v -> {
            float curTranslationX = mTvTranslation.getTranslationX();
            mTranslationAnimator = ObjectAnimator.ofFloat(mTvTranslation, "translationX", curTranslationX, 500f, curTranslationX);
            mTranslationAnimator.setDuration(2000);
            mTranslationAnimator.setRepeatCount(3);
            mTranslationAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.e(TAG, "onAnimationStart: 动画开始");
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.e(TAG, "onAnimationEnd: 动画结束");
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.e(TAG, "onAnimationCancel: 动画取消");
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    Log.e(TAG, "onAnimationRepeat: 动画重复执行");
                }
            });
            mTranslationAnimator.start();
        });

        mTvScale = findViewById(R.id.tvScale);
        mTvScale.setOnClickListener(v -> {
            AnimatorSet animatorSet = new AnimatorSet();

            ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(mTvScale, "scaleX", 1f, 3f, 1f);
            ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(mTvScale, "scaleY", 1f, 3f, 1f);

            animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
            animatorSet.setDuration(2000);

            //动画监听适配器，实现了Animator.AnimatorListener和Animator.AnimatorPauseListener
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }
            });
            animatorSet.start();
        });

        mTvCompose = findViewById(R.id.tvCompose);
        mTvCompose.setOnClickListener(v -> {
            float curTranslationX = mTvCompose.getTranslationX();
            Log.e(TAG, "compose: " + curTranslationX);
            ObjectAnimator moveRight = ObjectAnimator.ofFloat(mTvCompose, "translationX", curTranslationX, 500f);
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(mTvCompose, "scaleX", 1f, 3f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTvCompose, "scaleY", 1f, 3f, 1f);
            ObjectAnimator rotation = ObjectAnimator.ofFloat(mTvCompose, "rotation", 0f, 360f);
            ObjectAnimator moveLeft = ObjectAnimator.ofFloat(mTvCompose, "translationX", curTranslationX + 500f, 0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(scaleX).with(scaleY).with(rotation).after(moveRight).before(moveLeft);
            animatorSet.setDuration(2000);

            //动画监听器，包含动画开始，结束，重复开始和取消的监听
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    Log.e(TAG, "onAnimationStart: " + animation.toString());
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.e(TAG, "onAnimationEnd: " + animation.toString());
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    Log.e(TAG, "onAnimationCancel: " + animation.toString());
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    Log.e(TAG, "onAnimationRepeat: " + animation.toString());
                }
            });
            animatorSet.start();
        });

        mTvXMLValue = findViewById(R.id.tvXMLValue);
        mTvXMLValue.setOnClickListener(v -> {
            Animator animator = AnimatorInflater.loadAnimator(PropertyAnimationActivity.this, R.animator.animator_value);
        });

        mTvXMLAlpha = findViewById(R.id.tvXMLAlpha);
        mTvXMLAlpha.setOnClickListener(v -> {
            Animator animator = AnimatorInflater.loadAnimator(PropertyAnimationActivity.this, R.animator.animator_alpha);
            animator.setTarget(v);
            animator.start();
        });

        mTvXMLCompose = findViewById(R.id.tvXMLCompose);
        mTvXMLCompose.setOnClickListener(v -> {
            Animator animator = AnimatorInflater.loadAnimator(PropertyAnimationActivity.this, R.animator.animator_compose);
            animator.setTarget(v);
            animator.start();
        });

        mTvViewPropertyAnimator = findViewById(R.id.tvViewPropertyAnimator);
        mTvViewPropertyAnimator.animate().alpha(0f).setDuration(2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTranslationAnimator != null) {
            mTranslationAnimator.cancel();
        }
    }
}
