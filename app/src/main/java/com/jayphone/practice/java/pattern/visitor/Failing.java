package com.jayphone.practice.java.pattern.visitor;

/**
 * Created by JayPhone on 2020/4/1
 */
public class Failing extends Action {
    @Override
    public void getManConclusion(Man man) {
        System.out.println("男人失败");
    }

    @Override
    public void getWomanConclusion(Woman woman) {
        System.out.println("女人失败");
    }
}
