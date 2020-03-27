package com.jayphone.practice.java.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by JayPhone on 2020/3/27
 */
public class AgentHandler implements InvocationHandler {
    private Object object;

    public AgentHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理 - 赛前洽谈");
        System.out.println("动态代理 - 情绪安抚");
        Object result = method.invoke(this.object, args);
        System.out.println("动态代理 - 庆功宴");
        return result;
    }
}
