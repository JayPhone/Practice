package com.jayphone.practice.java;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JayPhone on 2020/3/13
 * Java泛型在编译后会将泛型擦除，如果有类型限定，如Class<T extend String>或Class<T super String>,擦除后的类型为限定类型，否则擦除后的类型为Object
 * Java编译器是通过先检查代码中泛型的类型，然后在进行类型擦除，再进行编译
 */
public class GenericActivity extends AppCompatActivity {
    private static final String TAG = "GenericActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        genericClear();
        reflexGeneric();
        assignGeneric();
    }

    /**
     * 泛型擦除，编译后获取的类型都一致，原始类型相等，说明String和Integer被擦除了
     */
    public void genericClear() {
        List<String> stringList = new ArrayList<>();
        stringList.add("abc");
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        Log.e(TAG, "List<String>的Class类型: " + stringList.getClass());
        Log.e(TAG, "List<Integer>的Class类型: " + integerList.getClass());
        boolean sameType = stringList.getClass() == integerList.getClass();
        Log.e(TAG, "List<String>和List<Integer>的Class类型是否相同: " + sameType);
    }

    /**
     * 通过反射机制添加为List<String>添加Integer元素，是可以成功的，说明发生了类型擦除
     */
    public void reflexGeneric() {
        List<String> stringList = new ArrayList<>();
        stringList.add("abc");
        try {
            //可以成功添加整形元素
            stringList.getClass().getMethod("add", Object.class).invoke(stringList, 1);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < stringList.size(); i++) {
            //输出整形元素是会发生类型转换，强制转换成<E>，所以会报ClassCastException
            Log.e(TAG, " List<String>通过反射添加Integer元素: " + stringList.get(i));
        }
    }

    /**
     * 在不指定泛型的情况下，泛型变量的类型为该方法中的几种类型的同一父类的最小级，直到Object
     * 在指定泛型的情况下，该方法的几种类型必须是该泛型的实例的类型或者其子类
     * 总结：两个泛型都必须是指定类型的子类
     */
    public void assignGeneric() {
        /*不指定泛型的时候*/
        int i = add(1, 2);//这两个参数都是Integer，所以T为Integer类型
        Number f = add(1, 1.2);//这两个参数一个是Integer，一个是Float，所以取同一父类的最小级，为Number
        Object o = add(1, "abc");//这两个参数一个是Integer，一个是String，所以取同一父类的最小级，为Object

        /*指定泛型的时候*/
        int a = GenericActivity.<Integer>add(1, 2);//指定了Integer，所以只能为Integer类型或者其子类
        //int b = GenericActivity.<Integer>add(1, 2.2);//编译错误，指定了Integer，不能为Float
        Number c = GenericActivity.<Number>add(1, 2.2);//指定为Number，所以可以为Integer和Float
    }

    public static <T> T add(T x, T y) {
        return y;
    }

    public void refTransfer() {
        ArrayList<Object> list1 = new ArrayList<Object>();
        list1.add(new Object());
        list1.add(new Object());
        //ArrayList<String> list2 = list1; //编译错误

        /*
         * 实际上，在第4行代码的时候，就会有编译错误。那么，我们先假设它编译没错。那么当我们使用list2引用用get()方法取值的时候,
         * 返回的都是String类型的对象（上面提到了，类型检测是根据引用来决定的），可是它里面实际上已经被我们存放了Object类型的对象，
         * 这样就会有ClassCastException了。所以为了避免这种极易出现的错误，Java不允许进行这样的引用传递。
         * 这也是泛型出现的原因，就是为了解决类型转换的问题，我们不能违背它的初衷）。
         */

        ArrayList<String> list3 = new ArrayList<String>();
        list1.add(new String());
        list1.add(new String());
        //ArrayList<Object> list4 = list3; //编译错误

        /*
         * 没错，这样的情况比第一种情况好的多，最起码，在我们用list2取值的时候不会出现ClassCastException，因为是从String转换为Object。
         * 可是，这样做有什么意义呢，泛型出现的原因，就是为了解决类型转换的问题。我们使用了泛型，到头来，还是要自己强转，违背了泛型设计的初衷。
         * 所以java不允许这么干。再说，你如果又用list2往里面add()新的对象，那么到时候取得时候，我怎么知道我取出来的到底是String类型的，还是Object类型的呢？
         */
    }
}
