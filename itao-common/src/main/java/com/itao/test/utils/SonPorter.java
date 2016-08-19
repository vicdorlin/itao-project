package com.itao.test.utils;

import com.itao.warrior.DataPorter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author vicdor
 * @create 2016-08-19 17:24
 */
public class SonPorter extends DataPorter {
    protected <D> void copyValue(D d, Object fieldValueA, Method dSetter, String fieldName) throws IllegalAccessException, InvocationTargetException {
        System.out.println("===  === " + "==");
    }
}
