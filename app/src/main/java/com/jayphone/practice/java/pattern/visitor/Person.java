package com.jayphone.practice.java.pattern.visitor;

/**
 * 人的抽象类。只有一个“接受”的抽象方法，它是用来获得“状态”对象的
 * Created by JayPhone on 2020/4/1
 */
public abstract class Person {
    public abstract void accept(Action action);
}
