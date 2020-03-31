package com.jayphone.practice.java.pattern.template;

/**
 * 模板方法模式，试卷
 * Created by JayPhone on 2020/3/31
 */
public abstract class AbstractTestPaper {
    public final void test() {
        question1();
        question2();
        question3();
    }

    public void question1() {
        System.out.println("1+1=?");
        System.out.println("答案：" + answer1());
    }

    public void question2() {
        System.out.println("2+2=?");
        System.out.println("答案：" + answer2());
    }

    public void question3() {
        System.out.println("3+3=?");
        System.out.println("答案：" + answer3());
    }

    protected abstract String answer1();

    protected abstract String answer2();

    protected abstract String answer3();
}
