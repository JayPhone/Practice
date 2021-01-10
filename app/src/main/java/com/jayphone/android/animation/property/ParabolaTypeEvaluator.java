package com.jayphone.android.animation.property;

import android.animation.TypeEvaluator;

/**
 * 抛物线估值器
 * Created by JayPhone on 2021/1/10
 */
public class ParabolaTypeEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        float x = 200 * (fraction * 1.5f);
        float y = 200 * (fraction * 1.5f) * (fraction * 1.5f);
        return new Point(x, y);
    }
}
