package com.jayphone.practice.java.pattern.proxy.statis;

/**
 * 强壮的拳击手，真正的类
 * Created by JayPhone on 2020/3/27
 */
public class StrongBoxer implements Boxer {
    @Override
    public void fight() {
        System.out.println("干他");
    }
}
