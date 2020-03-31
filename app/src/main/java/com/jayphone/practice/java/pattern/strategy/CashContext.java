package com.jayphone.practice.java.pattern.strategy;

/**
 * Created by JayPhone on 2020/3/31
 */
public class CashContext {
    private Cash mCash;

    public CashContext(Cash cash) {
        mCash = cash;
    }

    public void setCash(Cash cash) {
        mCash = cash;
    }

    public double getResult(double money) {
        return mCash.acceptCash(money);
    }
}
