package com.jayphone.practice.java.pattern.factory.abs;

import com.jayphone.practice.java.pattern.factory.Car;

/**
 * Created by JayPhone on 2020/3/30
 */
public interface Factory {
    Car createCar();

    Bicycle createBicycle();
}
