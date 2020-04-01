package com.jayphone.practice.java.pattern.mediator;

/**
 * 抽象角色类，国家
 * Created by JayPhone on 2020/3/31
 */
public abstract class Country {
    protected UnitedNations mUnitedNations;

    public Country(UnitedNations unitedNations) {
        mUnitedNations = unitedNations;
    }

    public abstract void sendMessage(String messgae);

    public abstract void receiverMessage(String message);
}
