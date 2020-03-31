package com.jayphone.practice.java.pattern.command;

/**
 * 具体命令，烤鸡翅订单
 * Created by JayPhone on 2020/3/31
 */
public class BakeChickenWingOrder extends Order {
    private String name;

    public BakeChickenWingOrder(Barbecuer barbecuer) {
        super(barbecuer);
        name = "烤鸡翅";
    }

    @Override
    public void execute() {
        mBarbecuer.bakeChickenWing();
    }

    @Override
    public String getName() {
        return name;
    }
}
