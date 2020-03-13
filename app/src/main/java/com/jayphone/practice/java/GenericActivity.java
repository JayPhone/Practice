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
//        reflexGeneric();
//        assignGeneric();
//        genericType();
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("b");
        stringList.add("c");

        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        wildcard(stringList);
        wildcard(integerList);
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

    /**
     * 静态泛型方法
     */
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

    public void genericType() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        List<?> list2 = list1;
        Log.e(TAG, "genericType: " + list2.getClass());
        for (int i = 0; i < list2.size(); i++) {
            Log.e(TAG, "ele: " + list2.get(i));
        }
    }

    public void bound() {
        C<Float> c = new C<>();
    }

    /**
     * 基础泛型
     *
     * @param <T>
     */
    class A<T> {
        private T value;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    /**
     * 继承泛型类并指定类型(Integer)，其中的getValue()和setValue(Integer value)实质为方法重载，由于类型擦出的原因，擦出后为Object类型，则子类重新之后的
     * getValue()和setValue(Integer value)的返回值也应该为Object类型，但是现在返回值为指定的类型(Integer),实际上底层JVM是使用了桥接的方式，生成了返回了Object
     * 类型的重写方法然后调用返回指定类型(Integer)类型的方法，来处理泛型与多态的冲突
     */
    class B extends A<Integer> {
        @Override
        public Integer getValue() {
            return super.getValue();
        }

        @Override
        public void setValue(Integer value) {
            super.setValue(value);
        }
    }

    /**
     * 继承泛型类不指定类型，子类的边界可与父类一致或者范围更窄
     *
     * @param <T>
     */
    class C<T extends Float> extends A<T> {

    }

    /**
     * 通配符的使用
     * 只能调用与对象无关的方法，不能调用对象与类型有关的方法。因为直到外界使用才知道具体的类型是什么。
     * 也就是说，List集合，不能使用add()方法的。因为add()方法是把对象丢进集合中，而现在我是不知道对象的类型是什么
     */
    public void wildcard(List<?> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof String) {
                Log.e(TAG, "wildcard: true");
            } else {
                Log.e(TAG, "wildcard: false");
            }
            Log.e(TAG, "wildcard: " + list.get(i));
            Log.e(TAG, "wildcard: " + list.get(i).getClass());
        }
    }

    /**
     * 泛型上限，类继承由上到下
     *
     * @param <T>
     */
    class D<T extends Number> {

    }

    /**
     * 通配符界限
     * 如果要从集合中读取类型T的数据，并且不能写入，可以使用 ? extends 通配符；(Producer Extends)
     * 如果要从集合中写入类型T的数据，并且不需要读取，可以使用 ? super 通配符；(Consumer Super)
     * 如果既要存又要取，那么就不要使用任何通配符。
     */
    public void PECS() {
        List<? extends Season> seasonList = new ArrayList<>();
        //编译错误,List<? extends Season> 表示 “具有任何从Season继承类型的列表”，编译器无法确定List所持有的类型，所以无法安全的向其中添加对象。
        //可以添加null,因为null 可以表示任何类型。所以List 的add 方法不能添加任何有意义的元素。
//        seasonList.add(new Season());
//        seasonList.add(new Spring());

        //但是可以接受现有的子类型List赋值
        List<Spring> springList = new ArrayList<>();
        springList.add(new Spring());
        seasonList = springList;
        //由于我们已经保证了List中保存的是Season类或者他的某一个子类，所以，可以用get方法直接获得值
        Spring spring = (Spring) seasonList.get(0);

        /*
         * List<? extends Season>Season,下面这样的赋值都是合法的
         * 如果List<? extends Season>支持add方法的方法合法的话
         * list1可以add Season和所有Season的子类
         * list2可以add Spring和所有Spring的子类
         * list3可以add Winter和所有Winter的子类
         * 这样的话，问题就出现了
         * List<? extends Season>所应该持有的对象是Season的子类，而且具体是哪一个子类还是个未知数，所以加入任何Season的子类都会有问题，
         * 因为如果add Spring的话，可能List<? extends Season>持有的对象是new ArrayList()
         * Spring的加入肯定是不行的，如果 如果add Winter的话，可能List<? extends Season>持有的对象是new ArrayList<Jonathan的子类>()
         * Winter的加入又不合法，所以List<? extends Season> list 不能进行add
         */
        List<? extends Season> list1 = new ArrayList<Spring>();
        List<? extends Season> list2 = new ArrayList<Summer>();
        List<? extends Season> list3 = new ArrayList<Fall>();
        List<? extends Season> list4 = new ArrayList<Winter>();


        List<Season> s1 = new ArrayList<>();
        List<? super Season> s = s1;
        s.add(new Spring());
        s.add(new Summer());
        //这里的Season是一个Spring的超类（父类,superclass）的List。同样地，出于对类型安全的考虑，我们可以加入Spring对象或者其任何子类（如HotSpring）对象，
        //但由于编译器并不知道List的内容究竟是Season的哪个超类，因此不允许加入特定的任何超类型。而当我们读取的时候，
        //编译器在不知道是什么类型的情况下只能返回Object对象，因为Object是任何Java类的最终祖先类。
//        s.add(new Object());
    }

    class Season {

    }

    class Spring extends Season {
    }

    class Summer extends Season {
    }

    class Fall extends Season {

    }

    class Winter extends Season {
    }

}
