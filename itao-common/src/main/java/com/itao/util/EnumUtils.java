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
}
