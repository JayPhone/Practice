package com.jayphone.practice.java.pattern.state;

/**
 * 具体电梯状态，开门状态
 * Created by JayPhone on 2020/3/31
 */
public class OpeningState extends LiftState {
    //执行打开电梯门方法
    @Override
    void open() {
        System.out.println("电梯门开启");
    }

    //打开后还可以关闭电梯门
    @Override
    void close() {
        mContext.setLiftState(Context.CLOSING_STATE);
        mContext.close();
    }

    //门开着不能运行
    @Override
    void run() {

    }

    //门开着已经停止了
    @Override
    void stop() {

    }
}
