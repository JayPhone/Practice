package com.jayphone.practice.java.pattern.factory.simple;

import com.jayphone.practice.java.pattern.factory.BMWCar;
import com.jayphone.practice.java.pattern.factory.Car;
import com.jayphone.practice.java.pattern.factory.AudiCar;

/**
 * 简单工厂模式，不符合开闭原则，新增产品时需要修改create方法
 * Created by JayPhone on 2020/3/30
 */
public class CarFactory {
    public static Car create(String product) {
        if (product.equalsIgnoreCase("AudiCar")) {
            return new AudiCar();
        } else if (product.equalsIgnoreCase("BMWCar")) {
            return new BMWCar();
        }
        return null;
    }
}
