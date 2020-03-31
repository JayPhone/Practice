package com.jayphone.practice.java.pattern.observer;

/**
 * Created by JayPhone on 2020/3/31
 */
public class ConcreteObserver implements Observer {
    @Override
    public void update() {
        System.out.println("收到消息");
    }
}
