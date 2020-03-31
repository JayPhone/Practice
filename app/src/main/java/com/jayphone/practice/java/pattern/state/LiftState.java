package com.jayphone.practice.java.pattern.state;

/**
 * 抽象状态，电梯状态
 * Created by JayPhone on 2020/3/31
 */
public abstract class LiftState {
    protected Context mContext;

    public void setContext(Context context) {
        mContext = context;
    }

    /**
     * 开门
     */
    abstract void open();

    /**
     * 关门
     */
    abstract void close();

    /**
     * 运行
     */
    abstract void run();

    /**
     * 停止
     */
    abstract void stop();
}
