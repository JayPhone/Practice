package com.jayphone.practice.java.pattern.responsibility;

/**
 * Created by JayPhone on 2020/3/31
 */
public abstract class Handler {
    public static final int MONEY_500 = 500;
    public static final int MONEY_1000 = 1000;
    public static final int MONEY_1500 = 1500;

    protected Handler mNextHandler;

    public void setNextHandler(Handler nextHandler) {
        mNextHandler = nextHandler;
    }

    public abstract void handle(Bill bill);
}
