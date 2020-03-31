package com.jayphone.practice.java.pattern.factory.abs;

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

    @Override
    public Bicycle createBicycle() {
        return new AudiBicycle();
    }
}
