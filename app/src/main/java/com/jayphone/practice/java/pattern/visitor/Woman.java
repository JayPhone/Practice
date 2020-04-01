package com.jayphone.practice.java.pattern.visitor;

/**
 * Created by JayPhone on 2020/4/1
 */
public class Woman extends Person {
    @Override
    public void accept(Action action) {
        action.getWomanConclusion(this);
    }
}
