package com.jayphone.practice.java.annotation;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jayphone.processor.CompileAnnotation;

import java.lang.reflect.Field;

/**
 * Created by JayPhone on 2020/5/14
 */
public class AnnotationActivity extends AppCompatActivity {
    private static final String TAG = "AnnotationActivity";

    @RuntimeAnnotation("Jayphone")
    private String mName;
    @RuntimeAnnotation("Programmer")
    private String mMajor;
    @RuntimeAnnotation
    private int age;
    private String phone;

    @CompileAnnotation("170")
    private int mHeight;
    @CompileAnnotation("180")
    private int weight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //处理运行时注解
        Class<AnnotationActivity> clazz = AnnotationActivity.class;
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length == 0) {
            return;
        }

        for (Field field : fields) {
            RuntimeAnnotation runtimeAnnotation = field.getAnnotation(RuntimeAnnotation.class);
            if (runtimeAnnotation != null) {
                Log.e(TAG, "field: " + field.getName() + " annotation: " + runtimeAnnotation.value());
            } else {
                Log.e(TAG, "field: " + field.getName() + "无注解");
            }
        }
    }
}
