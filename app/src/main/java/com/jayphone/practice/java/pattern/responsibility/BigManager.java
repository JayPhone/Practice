package com.jayphone.practice.java.pattern.responsibility;

/**
 * Created by JayPhone on 2020/3/31
 */
public class BigManager extends Handler {
    @Override
    public void handle(Bill bill) {
        if (bill.getPrice() <= MONEY_1500) {
            System.out.println(bill.name() + "的报销单：" + bill.code() + "，金额：" + bill.getPrice() + " 大主管已审批报销");
        } else {
            if (mNextHandler != null) {
                mNextHandler.handle(bill);
            } else {
                System.out.println(bill.name() + "的报销单：" + bill.code() + "，金额：" + bill.getPrice() + " 金额过大，不予报销");
            }
        }
    }
}
