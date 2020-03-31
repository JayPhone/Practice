package com.jayphone.practice.java.pattern.state;

/**
 * Created by JayPhone on 2020/3/31
 */
public class Context {
    //定义出电梯的所有状态
    public final static LiftState OPENING_STATE = new OpeningState();
    public final static LiftState CLOSING_STATE = new ClosingState();
    public final static LiftState RUNNING_STATE = new RunningState();
    public final static LiftState STOPPING_STATE = new StoppingState();

    private LiftState mLiftState;

    public void setLiftState(LiftState liftState) {
        mLiftState = liftState;
        //通知到各个实现类中
        mLiftState.setContext(this);
    }

    public LiftState getLiftState() {
        return mLiftState;
    }

    /**
     * 开门
     */
    public void open() {
        mLiftState.open();
    }

    /**
     * 关门
     */
    public void close() {
        mLiftState.close();
    }

    /**
     * 运行
     */
    public void run() {
        mLiftState.run();
    }

    /**
     * 停止
     */
    public void stop() {
        mLiftState.stop();
    }
}
