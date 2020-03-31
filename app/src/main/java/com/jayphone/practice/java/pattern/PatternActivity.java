package com.jayphone.practice.java.pattern;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jayphone.practice.java.pattern.builder.Car;
import com.jayphone.practice.java.pattern.composite.Folder;
import com.jayphone.practice.java.pattern.composite.ImageFile;
import com.jayphone.practice.java.pattern.composite.TextFile;
import com.jayphone.practice.java.pattern.composite.VideoFile;
import com.jayphone.practice.java.pattern.decorator.DecorativePhone;
import com.jayphone.practice.java.pattern.decorator.OldPhone;
import com.jayphone.practice.java.pattern.decorator.Phone;
import com.jayphone.practice.java.pattern.facade.SystemControl;
import com.jayphone.practice.java.pattern.factory.abs.Bicycle;
import com.jayphone.practice.java.pattern.factory.method.AudiFactory;
import com.jayphone.practice.java.pattern.factory.method.BMWFactory;
import com.jayphone.practice.java.pattern.factory.method.Factory;
import com.jayphone.practice.java.pattern.factory.simple.CarFactory;
import com.jayphone.practice.java.pattern.flyweight.Piece;
import com.jayphone.practice.java.pattern.flyweight.PieceFactory;
import com.jayphone.practice.java.pattern.observer.ConcreteObserver;
import com.jayphone.practice.java.pattern.observer.ConcreteSubject;
import com.jayphone.practice.java.pattern.observer.Subject;
import com.jayphone.practice.java.pattern.proxy.dynamic.AgentHandler;
import com.jayphone.practice.java.pattern.proxy.statis.Agent;
import com.jayphone.practice.java.pattern.proxy.statis.Boxer;
import com.jayphone.practice.java.pattern.proxy.statis.StrongBoxer;
import com.jayphone.practice.java.pattern.singleton.Singleton;
import com.jayphone.practice.java.pattern.strategy.Cash;
import com.jayphone.practice.java.pattern.strategy.CashContext;
import com.jayphone.practice.java.pattern.strategy.CashNormal;
import com.jayphone.practice.java.pattern.strategy.CashRebate;
import com.jayphone.practice.java.pattern.strategy.CashReturn;
import com.jayphone.practice.java.pattern.template.Examiner;

import java.lang.reflect.Proxy;

/*
 * Created by JayPhone on 2020/3/28
 */
public class PatternActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //单例模式
        Singleton singleton = Singleton.getInstance();

        //建造者模式
        Car car = new Car.CarBuilder()
                .setColor("红色")
                .setEngine("法拉利引擎")
                .setTyre("防滑轮胎")
                .build();

        //静态代理模式
        Boxer boxer = new StrongBoxer();
        Boxer agent = new Agent(boxer);
        agent.fight();

        //动态代理模式
        Boxer dynamicAgent = (Boxer) Proxy.newProxyInstance(boxer.getClass().getClassLoader(), boxer.getClass().getInterfaces(), new AgentHandler(boxer));
        dynamicAgent.fight();

        //组合模式
        Folder image = new Folder("photo");
        image.add(new ImageFile("car.jpg"));
        image.add(new ImageFile("bike.png"));
        Folder video = new Folder("video");
        video.add(new VideoFile("my world.mp4"));
        video.add(new VideoFile("god father.mkv"));
        video.add(new VideoFile("hell mother.avi"));
        video.add(new VideoFile("beyond.rmvb"));
        Folder all = new Folder("all");
        all.add(image);
        all.add(video);
        all.add(new TextFile("password.txt"));
        all.display();

        //装饰模式
        Phone oldPhone = new OldPhone();
        oldPhone.call();
        DecorativePhone newPhone = new DecorativePhone(oldPhone);
        newPhone.call();
        newPhone.net();
        newPhone.game();

        //外观模式
        SystemControl system = new SystemControl();
        system.doABusiness();
        system.doBBusiness();
        system.doCBusiness();
        system.doBCBusiness();

        //简单工厂模式，工厂类里面只有一个简单的方法，通过if else去判断具体生产的产品，新增产品时，需要修改工厂类的方法，不符合开闭原则
        com.jayphone.practice.java.pattern.factory.Car audi = CarFactory.create("AudiCar");
        com.jayphone.practice.java.pattern.factory.Car bmw = CarFactory.create("BMWCar");

        //工厂方法模式，对简单工厂模式进行了横向扩展，每个产品由具体对应的工厂类去创建，符合开闭原则，当新增一个产品时同时需要新增一个对应的工厂类，会造成类的膨胀
        Factory audiFactory = new AudiFactory();
        com.jayphone.practice.java.pattern.factory.Car audi1 = audiFactory.createCar();
        Factory bmwFactory = new BMWFactory();
        com.jayphone.practice.java.pattern.factory.Car bmw1 = bmwFactory.createCar();

        //抽象工厂模式，对于工厂方法模式进行了纵向扩展，每个工厂可以生产一个产品族
        com.jayphone.practice.java.pattern.factory.abs.Factory audiFactory1 = new com.jayphone.practice.java.pattern.factory.abs.AudiFactory();
        com.jayphone.practice.java.pattern.factory.Car audiCar = audiFactory1.createCar();
        Bicycle audiBicycle = audiFactory1.createBicycle();
        com.jayphone.practice.java.pattern.factory.abs.Factory bmwFactory1 = new com.jayphone.practice.java.pattern.factory.abs.BMWFactory();
        com.jayphone.practice.java.pattern.factory.Car bmwCar = bmwFactory1.createCar();
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

        //模板方法模式
        Examiner examiner = new Examiner();
        examiner.test();

        //观察者模式
        ConcreteSubject subject = new ConcreteSubject();
        subject.addObserver(new ConcreteObserver());
        subject.addObserver(new ConcreteObserver());
        subject.doSomething();

        //策略模式
        Cash cashNormal = new CashNormal();
        Cash cashRebate = new CashRebate(0.5);
        Cash cashReturn = new CashReturn(2, 3);
        CashContext cashContext = new CashContext(cashNormal);
        System.out.println("价格为：" + cashContext.getResult(10));
        cashContext.setCash(cashRebate);
        System.out.println("价格为：" + cashContext.getResult(10));
        cashContext.setCash(cashReturn);
        System.out.println("价格为：" + cashContext.getResult(10));
    }
}
