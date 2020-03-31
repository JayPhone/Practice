package com.jayphone.practice.java.pattern.command;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 调用者，服务员
 * Created by JayPhone on 2020/3/31
 */
public class Waiter {
    private List<Order> mOrderList = new ArrayList<>();

    private int mMuttonCount = 0;
    private int mChickenWingCount = 0;

    public Waiter(int muttonCount, int chickenWingCount) {
        mMuttonCount = muttonCount;
        mChickenWingCount = chickenWingCount;
    }

    /**
     * 点单
     *
     * @param order
     */
    public void setOrder(Order order) {
        mOrderList.add(order);
        if (order instanceof BakeMuttonOrder) {
            if (mMuttonCount > 0) {
                System.out.println("添加订单：" + order.getName() + "\t时间：" + new Date().toString());
                mMuttonCount--;
            } else {
                System.out.println("【服务员：羊肉没有了，请点别的烧烤】");
                cancelOrder(order);
            }
        } else if (order instanceof BakeChickenWingOrder) {
            if (mChickenWingCount > 0) {
                System.out.println("添加订单：" + order.getName() + "\t时间：" + new Date().toString());
                mChickenWingCount--;
            } else {
                System.out.println("【服务员：鸡翅没有了，请点别的烧烤】");
                cancelOrder(order);
            }
        }
    }

    /**
     * 撤单
     *
     * @param order
     */
    public void cancelOrder(Order order) {
        mOrderList.remove(order);
    }

    /**
     * 下单
     */
    public void doOrder() {
        if (mOrderList != null && mOrderList.size() > 0) {
            for (Order order : mOrderList) {
                order.execute();
            }
        }
    }
}
