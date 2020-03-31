package com.jayphone.practice.java.pattern.state;

/**
 * 具体电梯状态，运行状态
 * Created by JayPhone on 2020/3/31
 */
public class RunningState extends LiftState {
    //正在运行不能开门
    @Override
    void open() {

    }

    //正在运行门是关闭的
    @Override
    void close() {

    }

    //正在运行
    @Override
    void run() {
        System.out.println("电梯运行中");
    }

    //运行后可以停止
    @Override
    void stop() {
        mContext.setLiftState(Context.STOPPING_STATE);
        mContext.close();
    }
}
