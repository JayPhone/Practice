package com.jayphone.practice.java.pattern.adapter;

/**
 * 手机充电器，适配器类，Adapter
 * Created by JayPhone on 2020/3/27
 */
public class PhoneCharge implements DC5V {
    private AC220V mAC220V;

    public PhoneCharge(AC220V AC220V) {
        mAC220V = AC220V;
    }

    @Override
    public void supply() {
        mAC220V.supply();
        System.out.println("开始降压，变流");
    }
}
