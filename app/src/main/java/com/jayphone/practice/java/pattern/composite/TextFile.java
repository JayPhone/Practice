package com.jayphone.practice.java.pattern.composite;

/**
 * 叶子节点，最小单位
 * 此处为具体文件
 * Created by JayPhone on 2020/3/27
 */
public class TextFile extends File {
    public TextFile(String name) {
        super(name);
    }

    @Override
    public void display() {
        System.out.println("这是文本文件，文件名：" + super.getName());
    }
}
