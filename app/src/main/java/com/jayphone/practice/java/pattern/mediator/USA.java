package com.jayphone.practice.java.pattern.mediator;

/**
 * 具体角色，美国
 * Created by JayPhone on 2020/3/31
 */
public class USA extends Country {
    public USA(UnitedNations unitedNations) {
        super(unitedNations);
    }

    @Override
    public void sendMessage(String messgae) {
        System.out.println("美国发表声明：" + messgae);
        mUnitedNations.declare(messgae, this);
    }

    @Override
    public void receiverMessage(String message) {
        System.out.println("美国接收到声明：" + message);
    }
}
