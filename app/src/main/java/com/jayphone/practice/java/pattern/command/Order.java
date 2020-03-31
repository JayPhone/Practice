package com.jayphone.practice.java.pattern.command;

/**
 * 抽象命令，订单
 * Created by JayPhone on 2020/3/31
 */
public abstract class Order {
    protected Barbecuer mBarbecuer;

    public Order(Barbecuer barbecuer) {
        mBarbecuer = barbecuer;
    }

    //执行命令
    abstract public void execute();

    //获取名称
    abstract public String getName();
}
