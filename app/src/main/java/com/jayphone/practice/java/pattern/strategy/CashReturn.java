package com.jayphone.practice.java.pattern.strategy;

/**
 * Created by JayPhone on 2020/3/31
 */
public class CashReturn implements Cash {
    private double moneyCondition = 0.0;    //返利条件
    private double moneyReturn = 0.0d;    //返利值

    public CashReturn(double moneyCondition, double moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }

    @Override
    public double acceptCash(double money) {
        //如果当前金额大于等于返利条件，则使用当前金额减去返利值
        double result = money;
        if (money >= moneyCondition) {
            result = money - Math.floor(money / moneyCondition) * moneyReturn;
        }
        return result;
    }
}
