package com.jayphone.practice.java.pattern.command;

/**
 * 具体命令，烤羊肉订单
 * Created by JayPhone on 2020/3/31
 */
public class BakeMuttonOrder extends Order {
    private String name;

    public BakeMuttonOrder(Barbecuer barbecuer) {
        super(barbecuer);
        name = "烤羊肉串";
    }

    @Override
    public void execute() {
        mBarbecuer.bakeMutton();
    }

    @Override
    public String getName() {
        return name;
    }
}
