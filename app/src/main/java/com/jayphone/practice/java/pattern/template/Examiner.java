package com.jayphone.practice.java.pattern.template;

/**
 * Created by JayPhone on 2020/3/31
 */
public class Examiner extends AbstractTestPaper {
    @Override
    protected String answer1() {
        return "2";
    }

    @Override
    protected String answer2() {
        return "4";
    }

    @Override
    protected String answer3() {
        return "6";
    }
}
