package com.jayphone.practice.java.pattern.strategy;

/**
 * Created by JayPhone on 2020/3/31
 */
public class CashRebate implements Cash {
    //折扣
    private double moneyRebate = 1;

    public CashRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}
