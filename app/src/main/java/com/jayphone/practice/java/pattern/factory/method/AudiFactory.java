package com.jayphone.practice.java.pattern.factory.method;

import com.jayphone.practice.java.pattern.factory.AudiCar;
import com.jayphone.practice.java.pattern.factory.Car;

/**
 * Created by JayPhone on 2020/3/30
 */
public class AudiFactory implements Factory {
    @Override
    public Car createCar() {
        return new AudiCar();
    }
}
