package com.jayphone.practice.java.pattern.singleton;

/**
 * Created by JayPhone on 2020/3/27
 */
public class Singleton {
    private volatile static Singleton INSTANCE;

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }

    private Singleton() {

    }
}
