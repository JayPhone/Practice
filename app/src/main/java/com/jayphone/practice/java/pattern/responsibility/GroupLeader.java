package com.jayphone.practice.java.pattern.responsibility;

/**
 * Created by JayPhone on 2020/3/31
 */
public class GroupLeader extends Handler {
    @Override
    public void handle(Bill bill) {
        if (bill.getPrice() <= MONEY_500) {
            System.out.println(bill.name() + "的报销单：" + bill.code() + "，金额：" + bill.getPrice() + " 组长已审批报销");
        } else {
            if (mNextHandler != null) {
                mNextHandler.handle(bill);
            }
        }
    }
}
