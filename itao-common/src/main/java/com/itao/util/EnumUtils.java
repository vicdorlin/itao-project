package com.itao.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Vicdor on 2016-05-07-0007.
 */
public class EnumUtils {

    /**
     * 枚举处理
     *
     * @param elementType
     * @param value
     * @param <T>
     * @return
     */
    public static <T> T valueOf(Class<T> elementType, Object value) {
        return valueOf(elementType, "value", value);
    }

    public static <T> T valueOf(Class<T> elementType, String filedName, Object value) {
        if(value != null && elementType.isEnum()){
            T[] fields = elementType.getEnumConstants();
            for (T f : fields) {
                try {
                    Field field = f.getClass().getDeclaredField(filedName);
                    field.setAccessible(true);
                    T fieldValue = (T) field.get(f);
                    if(value.equals(fieldValue)) return f;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

    enum TestEnum {
        TEST1(1, "hi"),
        TEST2(2, "ho");

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

    enum TestEnum2 {
        TEST1(1, "hi"),
        TEST2(2, "ho");

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
        TestEnum testEnum = valueOf(TestEnum.class, "ho");
        if (testEnum == TestEnum.TEST2) {
            System.out.println("test for value default");
        }

        TestEnum2 testEnum2 = valueOf(TestEnum2.class, "content", "ho");
        if (testEnum2 == TestEnum2.TEST2) {
            System.out.println("test for content");
        }

        TestEnum2 testEnum22 = valueOf(TestEnum2.class, "key", 2);
        if (testEnum22 == TestEnum2.TEST2) {
            System.out.println("test for key");
        }
    }
}
