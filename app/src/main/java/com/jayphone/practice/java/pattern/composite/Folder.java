package com.jayphone.practice.java.pattern.composite;


import java.util.ArrayList;
import java.util.List;

/**
 * 树枝节点,定义树枝节点的行为，存储子节点，组合树枝节点和叶子节点形成一个树形结构
 * 此处为文件夹
 * Created by JayPhone on 2020/3/27
 */
public class Folder extends File {
    private List<File> mFiles = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    @Override
    public void display() {
        for (int i = 0; i < mFiles.size(); i++) {
            mFiles.get(i).display();
        }
    }

    public void add(File file) {
        mFiles.add(file);
    }

    public void remove(File file) {
        mFiles.remove(file);
    }
}
