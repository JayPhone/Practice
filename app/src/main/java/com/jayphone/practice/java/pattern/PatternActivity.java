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
import com.jayphone.practice.java.pattern.proxy.dynamic.AgentHandler;
import com.jayphone.practice.java.pattern.proxy.statis.Agent;
import com.jayphone.practice.java.pattern.proxy.statis.Boxer;
import com.jayphone.practice.java.pattern.proxy.statis.StrongBoxer;
import com.jayphone.practice.java.pattern.singleton.Singleton;

import java.lang.reflect.Proxy;

/**
 * Created by JayPhone on 2020/3/27
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
    }
}
