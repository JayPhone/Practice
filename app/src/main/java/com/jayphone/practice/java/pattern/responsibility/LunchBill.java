package com.jayphone.practice.java.pattern.responsibility;

/**
 * Created by JayPhone on 2020/3/31
 */
public class LunchBill implements Bill {
    private String mCode;
    private String mName;
    private double mPrice;

    public LunchBill(String code, String name, double price) {
        mCode = code;
        mName = name;
        mPrice = price;
    }

    @Override
    public String code() {
        return mCode;
    }

    @Override
    public String name() {
        return mName;
    }

    @Override
    public double getPrice() {
        return mPrice;
    }
}
