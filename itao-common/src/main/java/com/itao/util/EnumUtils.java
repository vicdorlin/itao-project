package com.itao.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Vicdor on 2016-05-07-0007.
 */
public class EnumUtils {

    /**
     * 枚举处理
     * @param elementType
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T valueOf(Class<T> elementType,Object value){
        if(value==null){
            return null;
        }
        if(elementType.isEnum()){
            Object[] objs = elementType.getEnumConstants();
            for (Object obj : objs) {
                Method m;
                try {
                    m = obj.getClass().getDeclaredMethod("values", null);
                    Object[] result = (Object[]) m.invoke(obj, null);
                    for(Object objOne : result){
                        Field code = objOne.getClass().getDeclaredField("value");
                        code.setAccessible(true);
                        Object objVal = code.get(objOne);
                        if(objVal.equals(value)){
                            return (T)objOne;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static <T> T valueOf(Class<T> elementType,String filedName,Object value){
        if(value==null){
            return null;
        }
        if(elementType.isEnum()){
            Object[] objs = elementType.getEnumConstants();
            for (Object obj : objs) {
                Method m;
                try {
                    m = obj.getClass().getDeclaredMethod("values", null);
                    Object[] result = (Object[]) m.invoke(obj, null);
                    for(Object objOne : result){
                        Field code = objOne.getClass().getDeclaredField(filedName);
                        code.setAccessible(true);
                        Object objVal = code.get(objOne);
                        if(objVal.equals(value)){
                            return (T)objOne;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    enum TestEnum{
        TEST1(1,"hi"),
        TEST2(2,"ho");

        private int key;
        private String value;

        TestEnum(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }
        public void setKey(int key) {
            this.key = key;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }

    enum TestEnum2{
        TEST1(1,"hi"),
        TEST2(2,"ho");

        private int key;
        private String content;

        TestEnum2(int key, String content) {
            this.key = key;
            this.content = content;
        }

        public int getKey() {
            return key;
        }
        public void setKey(int key) {
            this.key = key;
        }
        public String getValue() {
            return content;
        }
        public void setValue(String content) {
            this.content = content;
        }
    }

    public static void main(String[] args) {
        /*TestEnum testEnum = valueOf(TestEnum.class,"ho");
        if(testEnum == TestEnum.TEST2){
            System.out.println("tttt2");
        }*/

        TestEnum2 testEnum2 = valueOf(TestEnum2.class,"content","ho");
        if(testEnum2 == TestEnum2.TEST2){
            System.out.println("tttt2");
        }

        TestEnum2 testEnum22 = valueOf(TestEnum2.class,"key",2);
        if(testEnum22 == TestEnum2.TEST2){
            System.out.println("tttt22");
        }
    }
}
