package com.jayphone.practice.java.pattern.visitor;

/**
 * Created by JayPhone on 2020/4/1
 */
public class Success extends Action {
    @Override
    public void getManConclusion(Man man) {
        System.out.println("男人成功");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println("女人成功");
    }
}
