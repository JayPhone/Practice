package com.jayphone.practice.java.pattern.strategy;

/**
 * Created by JayPhone on 2020/3/31
 */
public class CashNormal implements Cash {
    @Override
    public double acceptCash(double money) {
        return money;
    }
}
