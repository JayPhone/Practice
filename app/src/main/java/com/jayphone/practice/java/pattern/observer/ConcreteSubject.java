package com.jayphone.practice.java.pattern.observer;

/**
 * Created by JayPhone on 2020/3/31
 */
public class ConcreteSubject extends Subject {
    public void doSomething() {
        super.notifyObserver();
    }
}
