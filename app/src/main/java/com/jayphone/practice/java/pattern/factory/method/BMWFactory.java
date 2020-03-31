package com.jayphone.practice.java.pattern.factory.method;

import com.jayphone.practice.java.pattern.factory.BMWCar;
import com.jayphone.practice.java.pattern.factory.Car;

/**
 * Created by JayPhone on 2020/3/30
 */
public class BMWFactory implements Factory {
    @Override
    public Car createCar() {
        return new BMWCar();
    }
}
