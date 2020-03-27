package com.jayphone.practice.java.pattern.composite;

/**
 * Component 定义系统各层次对象的共有方法和属性，可以预先定义一些默认行为和属性
 * 此处为文件
 * Created by JayPhone on 2020/3/27
 */
public abstract class File {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void display();
}
