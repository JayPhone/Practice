package com.jayphone.practice.java.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JayPhone on 2020/3/31
 */
public class Subject {
    private List<Observer> mObserverList = new ArrayList<>();

    public void addObserver(Observer observer) {
        mObserverList.add(observer);
    }

    public void removeObserver(Observer observer) {
        mObserverList.remove(observer);
    }

    public void notifyObserver() {
        if (mObserverList != null && mObserverList.size() > 0) {
            for (int i = 0; i < mObserverList.size(); i++) {
                mObserverList.get(i).update();
            }
        }
    }
}
