package com.jayphone.practice.java.pattern.proxy.statis;

/**
 * 代理类，拳击手代理人
 * Created by JayPhone on 2020/3/27
 */
public class Agent implements Boxer {
    private Boxer boxer;

    public Agent(Boxer boxer) {
        this.boxer = boxer;
    }

    @Override
    public void fight() {
        System.out.println("静态代理 - 赛前洽谈");
        System.out.println("静态代理 - 情绪安抚");
        boxer.fight();
        System.out.println("静态代理 - 庆功宴");
    }
}
