package com.jayphone.practice.java.pattern.visitor;

/**
 * Created by JayPhone on 2020/4/1
 */
public abstract class Action {
    //得到男人的结论或反应
    public abstract void getManConclusion(Man man);

    //得到女人的结论或反应
    public abstract void getWomanConclusion(Woman woman);
}
