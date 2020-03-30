package com.jayphone.practice.java.pattern;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jayphone.practice.java.pattern.factory.abs.Bicycle;
import com.jayphone.practice.java.pattern.factory.method.AudiFactory;
import com.jayphone.practice.java.pattern.factory.method.BMWFactory;
import com.jayphone.practice.java.pattern.factory.method.Factory;
import com.jayphone.practice.java.pattern.factory.simple.CarFactory;
import com.jayphone.practice.java.pattern.factory.Car;
import com.jayphone.practice.java.pattern.flyweight.Piece;
import com.jayphone.practice.java.pattern.flyweight.PieceFactory;

/**
 * Created by JayPhone on 2020/3/28
 */
public class PatternActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //简单工厂模式，工厂类里面只有一个简单的方法，通过if else去判断具体生产的产品，新增产品时，需要修改工厂类的方法，不符合开闭原则
        Car audi = CarFactory.create("AudiCar");
        Car bmw = CarFactory.create("BMWCar");

        //工厂方法模式，对简单工厂模式进行了横向扩展，每个产品由具体对应的工厂类去创建，符合开闭原则，当新增一个产品时同时需要新增一个对应的工厂类，会造成类的膨胀
        Factory audiFactory = new AudiFactory();
        Car audi1 = audiFactory.createCar();
        Factory bmwFactory = new BMWFactory();
        Car bmw1 = bmwFactory.createCar();

        //抽象工厂模式，对于工厂方法模式进行了纵向扩展，每个工厂可以生产一个产品族
        com.jayphone.practice.java.pattern.factory.abs.Factory audiFactory1 = new com.jayphone.practice.java.pattern.factory.abs.AudiFactory();
        Car audiCar = audiFactory1.createCar();
        Bicycle audiBicycle = audiFactory1.createBicycle();
        com.jayphone.practice.java.pattern.factory.abs.Factory bmwFactory1 = new com.jayphone.practice.java.pattern.factory.abs.BMWFactory();
        Car bmwCar = bmwFactory1.createCar();
        Bicycle bmwBicycle = bmwFactory1.createBicycle();

        //享元模式
        Piece black = PieceFactory.getPiece("black");
        black.coordinate(1, 1);
        Piece white = PieceFactory.getPiece("white");
        white.coordinate(2, 2);
        Piece black2 = PieceFactory.getPiece("black");
        black2.coordinate(3, 3);
        Piece black3 = PieceFactory.getPiece("black");
        black3.coordinate(4, 4);
        Piece white2 = PieceFactory.getPiece("white");
        white2.coordinate(5, 5);
        System.out.println("实际棋子对象: " + PieceFactory.getSize());
    }
}
