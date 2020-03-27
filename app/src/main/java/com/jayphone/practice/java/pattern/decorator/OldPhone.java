package com.jayphone.practice.java.pattern.decorator;

/**
 * 老款手机，实现了手机接口
 * Created by JayPhone on 2020/3/27
 */
public class OldPhone implements Phone {
    @Override
    public void call() {
        System.out.println("打电话");
    }
}
