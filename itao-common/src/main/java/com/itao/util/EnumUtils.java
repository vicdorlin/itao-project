package com.itao.util;

import java.lang.reflect.Field;

/**
 * Created by Vicdor on 2016-05-07-0007.
 */
public class EnumUtils {

    /**
     * 根据枚举对象属性值获取枚举对象
     * 注：只适用于查询value值归属在名为"value"字段下的枚举类
     * @param elementType 枚举类的类对象
     * @param value 被查询的属性值
     * @param <T> 枚举类
     * @return 返回对应的枚举对象
     */
    public static <T> T valueOf(Class<T> elementType, Object value) {
        return valueOf(elementType, "value", value);
    }

    /**
     * 根据枚举对象属性值获取枚举对象
     * @param elementType 枚举类的类对象
     * @param fieldName 字段名称（如:"value","key","content"...）
     * @param value 枚举对象某属性值
     * @param <T> 枚举类
     * @return 返回对应的枚举对象
     */
    public static <T> T valueOf(Class<T> elementType, String fieldName, Object value) {
        if(value != null && elementType.isEnum()){
            T[] fields = elementType.getEnumConstants();
            for (T f : fields) {
                try {
                    Field field = f.getClass().getDeclaredField(fieldName);
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

    /*enum TestEnum {
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
    }*/
}
