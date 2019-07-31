/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.string;

/**
 * @author lidai
 * @date 2019/6/19 10:07
 */
public class Test_2 {

    static int s;
    int i;
    int j;

    /**
     * 非静态代码块，每次创建对象都会执行
     */ {
        int i = 1;
        i++;
        j++;
        s++;
    }

    public void test(int j) {
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) {
        Test_2 obj1 = new Test_2();
        Test_2 obj2 = new Test_2();
        obj1.test(10);
        obj1.test(20);
        obj2.test(30);
        System.out.println(obj1.i + "," + obj1.j + "," + obj1.s);
        System.out.println(obj2.i + "," + obj2.j + "," + obj2.s);
    }

    /**
     * 分析  成员变量初始化的时候有默认值0，局部变量则没有
     * 首先初始化i,j,s 均为0 创建对象的时候会执行非静态代码块，创建了两个对象，则执行两次非静态代码块 此时 s = 1，i,j均为局部变量，不用管
     * obj1.test(10) 此时 j为局部变量，i=1 ,s=3
     * obj1.test(20) 此时，i=2 ,s=4 ,此i的值为obj1对象的值，s 为静态变量
     * obj2.test(30）此时，i=1，s=5
     *
     * 静态变量与对象无关，则s=5
     */

    /**
     * 另知识点     局部变量和成员变量的区别
     * 声明的位置
     *      局部变量：方法体中,局部代码块中
     *      成员变量：类的方法外
     *          类变量：static修饰
     *          实例变量：没有static修饰
     * 修饰符
     *      局部变量：final
     *      成员变量：public private protected final static volatile transient
     * 值存储位置
     *      局部变量：栈
     *      实例变量：堆
     *      类变量：方法区
     * 作用域
     *      局部变量：从声名处开始，到方法}为止
     *      成员变量
     *          实例变量：在类内可以通过this访问，this可以省略
     *          类变量：类外可以通过类名直接访问
     *
     * 生命周期
     *      局部变量：每个线程每次调用都是新的生命周期
     *      成员变量：
     *          实例变量：随着对象的创建而被初始化，随着对象的回收而消亡，每个成员的实例变量都是独立的
     *          类变量：随着类的初始化而被初始化，随类的销毁而消亡，所有类变量对该类的所有对象共享
     */
}

