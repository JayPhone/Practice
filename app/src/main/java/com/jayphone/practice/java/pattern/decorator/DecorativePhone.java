package com.jayphone.practice.java.pattern.decorator;

/**
 * 装饰过的手机，增加了上网，玩游戏的功能的手机
 * Created by JayPhone on 2020/3/27
 */
public class DecorativePhone implements Phone {
    private Phone mPhone;

    public DecorativePhone(Phone phone) {
        mPhone = phone;
    }

    public void net() {
        System.out.println("上网");
    }

    public void game() {
        System.out.println("玩游戏");
    }

    @Override
    public void call() {
        mPhone.call();
    }
}
