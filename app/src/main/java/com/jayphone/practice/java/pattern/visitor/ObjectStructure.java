package com.jayphone.practice.java.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JayPhone on 2020/4/1
 */
public class ObjectStructure {
    private List<Person> mPersonList = new ArrayList<>();

    public void add(Person person) {
        mPersonList.add(person);
    }

    public void remove(Person person) {
        mPersonList.remove(person);
    }

    public void display(Action action) {
        for (Person person : mPersonList) {
            person.accept(action);
        }
    }
}
