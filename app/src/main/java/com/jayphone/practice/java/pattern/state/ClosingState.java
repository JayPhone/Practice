package com.jayphone.practice.java.pattern.state;

/**
 * 具体电梯状态，关门状态
 * Created by JayPhone on 2020/3/31
 */
public class ClosingState extends LiftState {
    //电梯门关了可以再开
    @Override
    void open() {
        mContext.setLiftState(Context.OPENING_STATE);
        mContext.open();
    }

    //执行电梯门关闭方法
    @Override
    void close() {
        System.out.println("电梯门关闭");
    }

    //电梯门关了就运行
    @Override
    void run() {
        mContext.setLiftState(Context.RUNNING_STATE);
        mContext.run();
    }

    //电梯门关了但没有按楼层
    @Override
    void stop() {
        mContext.setLiftState(Context.STOPPING_STATE);
        mContext.stop();
    }
}
