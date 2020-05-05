package com.jayphone.practice.java.reflection.model;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Created by JayPhone on 2020/4/21
 */
public class Student extends Person {

    private String className;

    public Student() {

    }

    public Student(String className) {
        this.className = className;
    }

    public Student(String name, int age, String className) {
        super(name, age);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{" +
                "className='" + className + '\'' +
                '}';
    }
}
