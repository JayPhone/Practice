package com.jayphone.practice.java.reflection;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.jayphone.practice.java.reflection.model.Teacher;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * Created by JayPhone on 2020/4/21
 */
public class ReflectionActivity extends AppCompatActivity {
    private static final String TAG = "ReflectionActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filedMethod();
    }

    /**
     * Class相关方法
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void classMethod() {
        try {
            //创建对象
            Class<?> teacherClazz = Class.forName("com.jayphone.practice.java.reflection.model.Teacher");
            Class<?> teacherClazz1 = Teacher.class;
            Teacher teacher = (Teacher) teacherClazz.newInstance();

            //获取对象信息
            System.out.println("Teacher类类加载器：" + teacherClazz.getClassLoader().getClass().getName());
            System.out.println("Teacher类全称：" + teacherClazz.getName() + " 简称：" + teacherClazz.getSimpleName() + " 规范名称：" + teacherClazz.getCanonicalName());
            System.out.println("Teacher类父类为：" + teacherClazz.getSuperclass().getName());
            System.out.println("Teacher类包名为：" + teacherClazz.getPackage());
            //获取实现的接口
            Class<?>[] interfaces = teacherClazz.getInterfaces();
            for (Class<?> interf : interfaces) {
                System.out.println("实现接口名：" + interf.getName());
            }
            //获取public内部类或接口
            Class<?>[] clazzs = teacherClazz.getClasses();
            for (Class<?> clazz : clazzs) {
                if (clazz.isInterface()) {
                    System.out.println("内部public接口名：" + clazz.getName());
                } else {
                    System.out.println("内部public类名：" + clazz.getName());
                }
            }
            //获取所有内部类或接口
            Class<?>[] clazzs1 = teacherClazz.getDeclaredClasses();
            for (Class<?> clazz : clazzs1) {
                if (clazz.isInterface()) {
                    System.out.println("内部接口名：" + clazz.getName());
                } else {
                    System.out.println("内部类名：" + clazz.getName());
                }
            }


            //获取构造函数
            Constructor<?>[] constructors = teacherClazz.getConstructors();
            System.out.println("构造函数个数: " + constructors.length);
            for (Constructor<?> constructor : constructors) {
                System.out.println("构造函数名字: " + constructor.getName());

                //获取构造函数参数
                Parameter[] parameters = constructor.getParameters();
                StringBuilder parmsSb = new StringBuilder();
                for (Parameter parameter : parameters) {
                    parmsSb.append(parameter.getType()).append(" ").append(parameter.getName()).append(" , ");
                }
                parmsSb.append("\n");
                System.out.println("参数: " + parmsSb.toString());
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Field相关方法
     */
    private void filedMethod() {
        Teacher teacher = new Teacher("Jayphone", 24, "计算机", 7000, null);
        Class<Teacher> teacherClass = Teacher.class;
        try {
            //返回某个类的所有public属性，包括其继承类的public属性。
            Field[] fields = teacherClass.getFields();
            for (Field field : fields) {
                System.out.println("Public Filed名称：" + field.getName());
            }

            //获得public的属性,可获取父类的public属性，非public属性不能获取，报java.lang.NoSuchFieldException异常
            Field majorField = teacherClass.getField("major");
            String major = (String) majorField.get(teacher);
            System.out.println("Teacher的major是：" + major);

            //获取所有属性，包括public,private,package,protected,不包含父类的属性
            Field[] allFields = teacherClass.getDeclaredFields();
            for (Field field : allFields) {
                System.out.println("Filed名称：" + field.getName());
            }

            //获取指定属性，包括public,private,package,protected，不能获取父类任何属性
            Field salaryFiled = teacherClass.getDeclaredField("salary");
            //如果是private属性，需要setAccessible为true
            salaryFiled.setAccessible(true);
            double salary = salaryFiled.getDouble(teacher);
            System.out.println("Teacher的salary是：" + salary);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
