package com.jayphone.practice.java.pattern.composite;

/**
 * Created by JayPhone on 2020/3/27
 */
public class VideoFile extends File {
    public VideoFile(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("这是影像文件，文件名：" + super.getName());
    }
}
