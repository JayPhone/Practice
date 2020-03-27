package com.jayphone.practice.java.pattern.facade;

/**
 * 向外部提供服务的系统，避免暴露子系统细节及提供简单的调用
 * Created by JayPhone on 2020/3/27
 */
public class SystemControl {
    private SubSystemA mSubSystemA;
    private SubSystemB mSubSystemB;
    private SubSystemC mSubSystemC;

    public SystemControl() {
        mSubSystemA = new SubSystemA();
        mSubSystemB = new SubSystemB();
        mSubSystemC = new SubSystemC();
    }

    public void doABusiness() {
        mSubSystemA.doBusiness();
    }

    public void doBBusiness() {
        mSubSystemB.doBusiness();
    }

    public void doCBusiness() {
        mSubSystemC.doBusiness();
    }

    public void doBCBusiness() {
        mSubSystemB.doBusiness();
        mSubSystemC.doBusiness();
    }
}
