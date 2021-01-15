package com.jayphone.android.animation.property;

import android.animation.TypeEvaluator;

/**
 * 抛物线估值器
 * Created by JayPhone on 2021/1/10
 */
public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        float x = startValue.x + fraction * (endValue.x - startValue.x);
        float y = startValue.y + fraction * (endValue.y - startValue.y);
        return new Point(x, y);
    }
}
