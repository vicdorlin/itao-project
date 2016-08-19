package com.itao.test;

import com.itao.util.CommonUtils;

/**
 * @author vicdor
 * @create 2016-08-19 17:46
 */
public class Test {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setAge(11);
        dog.setName("Wang");
        Cat cat = new SonPorter().copyData(Cat.class,dog);
        System.out.println("===  === " + CommonUtils.transferToString(cat));
    }
}
