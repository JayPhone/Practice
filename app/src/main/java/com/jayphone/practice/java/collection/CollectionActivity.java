package com.jayphone.practice.java.collection;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by JayPhone on 2020/4/16
 */
public class CollectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        useArrayList();

//        useMap();
    }

    public void useArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.remove("1");
    }

    public void useLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
    }

    public void useMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("张三", "1");
        map.put("李四", "2");
        map.put("王五", "3");
        map.put("赵六", "5");
        map.put("孙七", "6");
        map.put("7", "7");
        map.put("8", "8");
        map.put("9", "9");
        map.put("10", "10");
        map.put("11", "11");
        map.put("12", "12");
        map.put("13", "13");
        map.put("14", "14");
        map.put("15", "15");
        map.put("16", "16");
    }
}
