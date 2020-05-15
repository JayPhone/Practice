package com.jayphone.practice.java.reflection.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by JayPhone on 2020/4/21
 */
public class Teacher extends Person implements School, Serializable {
    public String major;
    private double salary;
    private List<Student> mStudentList;

    public Teacher() {

    }

    public Teacher(String major, double salary, List<Student> studentList) {
        this.major = major;
        this.salary = salary;
        mStudentList = studentList;
    }

    public Teacher(String name, int age, String major, double salary, List<Student> studentList) {
        super(name, age);
        this.major = major;
        this.salary = salary;
        mStudentList = studentList;
    }

    protected String getMajor() {
        return major;
    }

    private void setMajor(String major) {
        this.major = major;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Student> getStudentList() {
        return mStudentList;
    }

    public void setStudentList(List<Student> studentList) {
        mStudentList = studentList;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "major='" + major + '\'' +
                ", salary=" + salary +
                ", mStudentList=" + mStudentList +
                '}';
    }

    public class Leader {

    }

    public interface Learn {

    }

    private interface Teach {

    }

    public static int staticMethod(int num) {
        return num;
    }
}
