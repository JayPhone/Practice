package com.jayphone.practice.java.pattern.state;

/**
 * 具体电梯状态，关门状态
 * Created by JayPhone on 2020/3/31
 */
public class StoppingState extends LiftState {
    //停止后可以开门
    @Override
    void open() {
        mContext.setLiftState(Context.OPENING_STATE);
        mContext.open();
    }

    //门本来就是关着的
    @Override
    void close() {

    }

    //停止后可以运行
    @Override
    void run() {
        mContext.setLiftState(Context.RUNNING_STATE);
        mContext.run();
    }

    //执行停止方法
    @Override
    void stop() {
        System.out.println("电梯停止了");
    }
}
