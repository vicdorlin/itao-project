package com.itao.exception;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static com.itao.util.CommonUtils.notExist;

/**
 * @author vicdor
 * @create 2016-06-22 13:35
 */
public final class RuleCheck {
    /**
     * 空验证
     * @param key
     * @param msg
     * @throws RuleException
     */
    public static final void checkEmpty(Object key, String msg) throws RuleException {
        if (notExist(key)) {
            throw new RuleException("value:" + msg + " can not be empty");
        }
    }

    /**
     * 简化后的空验证 1
     * @param javaBean	类型符合JavaBean规范的java对象
     * @param keyWords	需要进行空验证的字段名称集
     *        (集合中元素若非javaBean中字段名称，则该元素无效，
     *        并且会打印相关的异常堆栈信息，应尽量避免)
     * @throws RuleException
     */
    public static final void checkEmpty(Object javaBean, List<String> keyWords) throws RuleException {
        if(javaBean == null || keyWords == null || keyWords.size() == 0) return;
        try {
            Class clazz = javaBean.getClass();
            for (String keyWord : keyWords) {
                PropertyDescriptor pd = new PropertyDescriptor(keyWord, clazz);
                Method getMethod = pd.getReadMethod();//获得keyWord在obj中对应的getter方法
                Object o = getMethod.invoke(javaBean);
                checkEmpty(o,keyWord);//空验证
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }  catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 简化后的空验证 2
     * @param javaBean 验证所有声明的字段（包括父类中的所有字段，但不包括任何serialVersionUID)
     */
    public static final void checkEmpty(Object javaBean) throws RuleException {
        if(javaBean == null) return;
        try {
            Class clazz = javaBean.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String keyWord = field.getName();
                if("serialVersionUID".equals(keyWord)) continue;
                PropertyDescriptor pd = new PropertyDescriptor(keyWord, clazz);
                Method getMethod = pd.getReadMethod();//获得keyWord在obj中对应的getter方法
                Object o = getMethod.invoke(javaBean);
                checkEmpty(o,keyWord);//空验证
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }  catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 简化后的空验证 3
     * @param javaBean 类型符合JavaBean规范的java对象
     * @param exceptWords 不需要进行空验证的字段名称集
     * @throws RuleException
     */
    public static final void checkEmptyWithout(Object javaBean, List<String> exceptWords) throws RuleException {
        if(javaBean == null) return;
        if(exceptWords == null || exceptWords.size() == 0) {
            checkEmpty(javaBean);
            return;
        }
        try {
            Class clazz = javaBean.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String keyWord = field.getName();
                if("serialVersionUID".equals(keyWord)) continue;
                if(exceptWords.indexOf(keyWord) >= 0) continue;
                PropertyDescriptor pd = new PropertyDescriptor(keyWord, clazz);
                Method getMethod = pd.getReadMethod();//获得keyWord在obj中对应的getter方法
                Object o = getMethod.invoke(javaBean);
                checkEmpty(o,keyWord);//空验证
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }  catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
