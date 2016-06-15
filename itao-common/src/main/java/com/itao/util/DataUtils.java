package com.itao.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用于封装一些数据的处理
 * 1，A copyTo D : [组装另一个类型的对象]：【推荐】 D copyData(Class<D> clazzD, A arg)
 * 2，List<A> copyTo List<D> : [填充新的list]：【推荐】 List<D> copyDatas(Class<D> clazzD, List<A> args)
 * 3，List<A> copyTo List<D> : [补充原有的list]：【推荐】 List<D> attachDatas(List<D> dogs, List<A> args)
 * 4，List<A> copyTo List<D> : [补充原有的list（dogs为空或空集则同2）]：【推荐】 List<D> attachDatas(Class<D> clazzD, List<D> dogs, List<A> args)
 *
 * @author vicdor
 * @create 2016-06-15 11:17
 */
public class DataUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataUtils.class);

    /**
     * （若符合要求（即存在数据传递关系的类之间相同字段名对应字段类型也相同，
     * 且对参与数据传递的字段没有其他限制（即无需keys），
     * 并且只进行同名字段传递(即无需keyMap)），
     * 则推荐使用）
     * 要求字段名称和类型一一对应
     */
    public static <D, A> D copyData(Class<D> clazzD, A arg) {
        return copyData(clazzD, arg, null, null);
    }

    /**
     * 使用指定的keys中的D字段
     */
    public static <D, A> D copyData(Class<D> clazzD, A arg, List<String> keys) {
        return copyData(clazzD, arg, keys, null);
    }

    /**
     * 部分字段使用keyMap指定的关系对应起来
     *
     * @param keyMap <k,v> k:D中字段 v:A中字段
     */
    public static <D, A> D copyData(Class<D> clazzD, A arg, Map<String, String> keyMap) {
        return copyData(clazzD, arg, null, keyMap);
    }

    /**
     * 将arg对象按照keys指定的字段,根据名称一一对应（部分按照keyMap指定对应）copy为一个新的D类型对象
     *
     * @param clazzD 要生成的对象的类型
     * @param arg    数据来源
     * @param keys   指定要copy数据的字段名
     * @param keyMap <k,v> k:D中字段 v:A中字段
     */
    public static <D, A> D copyData(Class<D> clazzD, A arg, List<String> keys, Map<String, String> keyMap) {
        if (arg == null || clazzD == null) return null;

        //1,如果未提供keys则默认使用D类所有字段名
        if (keys == null || keys.size() == 0) {
            Field[] dFields = clazzD.getDeclaredFields();
            keys = Lists.newArrayList();
            for (Field field : dFields) {
                keys.add(field.getName());
            }
        }

        //用于存储收集的问题key
        Set<String> errorKeySet = Sets.newHashSet();
        Class clazzA = arg.getClass();

        if (errorKeySet.size() > 0)
            LOGGER.info("1000 === 创建PropertyDescriptor失败 === errorKeySet:{}", errorKeySet);

        return (D) compose(arg, clazzD, clazzA, keys, keyMap, errorKeySet);
    }



    /*===copyDatas意味着生成=======【list数据操作】======attachDatas意味着附加，即不断往传入的dogs中添加数据===*/

    /**
     * (若符合默认规则推荐使用)
     * 将A的数据集，按照名称直接对应copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param args A的数据集
     * @return D的数据集
     */
    public static <D, A> List<D> copyDatas(Class<D> clazzD, List<A> args) {
        return attachDatas(clazzD, null, args, null, null);
    }

    /**
     * 将A的数据集，按照keys中指定的D的字段，依据名称直接对应copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param args A的数据集
     * @param keys 指定的D的字段
     * @return D的数据集
     */
    public static <D, A> List<D> copyDatas(Class<D> clzzD, List<A> args, List<String> keys) {
        return attachDatas(clzzD, null, args, keys, null);
    }

    /**
     * 将A的数据集按照字段名称直接对应(部分字段按照keyMap中指定的关系对应)copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param args   A的数据集
     * @param keyMap <k,v> k为D中字段名，v为A中字段名
     * @return D的数据集
     */
    public static <D, A> List<D> copyDatas(Class<D> clzzD, List<A> args, Map<String, String> keyMap) {
        return attachDatas(clzzD, null, args, null, keyMap);
    }

    /**
     * 将A的数据集，按照keys中指定的D的字段，按照字段名称直接对应(部分字段按照keyMap中指定的关系对应)copy为一个新的D的数据集
     * 要求：相同的字段名有相同的数据类型（可解决）
     *
     * @param args   A的数据集
     * @param keys   指定的D的字段
     * @param keyMap <k,v> k为D中字段名，v为A中字段名
     * @return D的数据集
     */
    public static <D, A> List<D> copyDatas(Class<D> clazzD, List<A> args, List<String> keys, Map<String, String> keyMap) {
        return attachDatas(clazzD, null, args, keys, keyMap);
    }

    /**
     * （dogs有数据后，若符合默认规则，推荐使用）
     * 将A的数据集，按照字段对应关系copy添加到D的数据集，以丰富dogs
     * 要求：相同的字段名有相同的数据类型（可解决）
     * 要求：dogs中至少要有一条数据
     *
     * @param dogs D的数据集
     * @param args A的数据集
     * @return D的数据集
     */
    public static <D, A> List<D> attachDatas(List<D> dogs, List<A> args) {
        return attachDatas(null, dogs, args, null, null);
    }

    /**
     * （若符合默认规则，推荐使用）
     * 将A的数据集，按照字段对应关系copy添加到D的数据集，以丰富dogs
     * 要求：相同的字段名有相同的数据类型（可解决）
     * 说明，dogs可以为空或空集
     */
    public static <D, A> List<D> attachDatas(Class<D> clazzD, List<D> dogs, List<A> args) {
        return attachDatas(clazzD, dogs, args, null, null);
    }

    /**
     * 将A的数据集，按照keys中指定的D的字段，依据名称直接对应copy添加到D的数据集，以丰富dogs
     * 要求：相同的字段名有相同的数据类型（可解决）
     * 要求：keys当有数据
     */
    public static <D, A> List<D> attachDatas(List<D> dogs, List<A> args, List<String> keys) {
        return attachDatas(null, dogs, args, keys, null);
    }

    /**
     * 有map无keys有clazzD
     */
    public static <D, A> List<D> attachDatas(Class<D> clazzD, List<D> dogs, List<A> args, Map<String, String> keyMap) {
        return attachDatas(clazzD, dogs, args, null, keyMap);
    }

    /**
     * 有map有keys无clazzD
     */
    public static <D, A> List<D> attachDatas(List<D> dogs, List<A> args, List<String> keys, Map<String, String> keyMap) {
        return attachDatas(null, dogs, args, keys, keyMap);
    }

    /**
     * 将A类型的集合args中的部分数据(通过keys和keyMap指定)添加进入D类型的集合dogs中
     * 注：   0，D，A都应符合标准的bean规范（无视serialVersionUID）
     * 1，clazzD、dogs、keys 至少应该有一个是有数据(非空)的，否则调用本方法无意义
     * 这里的数据依据，keys的优先级最高，其次为clazzD，再次为dogs中的元素；
     *
     * @param clazzD 加这个参数的目的是如若dogs无值，那么我将无法获取它，而我又存在需要通过它获取到keys的状况
     * @param dogs   指代我们要合成的数据集
     * @param args   现有的数据集，要将<A>args中的数据植入<D>dogs
     * @param keys   指定D类中哪些字段参与数据合成
     * @param keyMap <k,v> 用于处理两个类因字段名不同导致的尴尬 k:应当为keys中存在的属于D的字段名，v：应当为A中存在的字段名
     * @param <D>    要合成的数据类型
     * @param <A>    现提供数据的集合元素的数据类型
     * @return 处理后的D类型数据集合
     */
    public static <D, A> List<D> attachDatas(Class<D> clazzD, List<D> dogs, List<A> args, List<String> keys, Map<String, String> keyMap) {
        if (args == null || args.size() <= 0) return dogs;

        //1,如果未提供keys则默认使用D类所有字段名
        if (keys == null || keys.size() == 0) {
            Field[] dFields;
            if (clazzD == null) {
                //keys没有，classD有咩有，dogs也没点东西，玩毛...
                if (dogs == null || dogs.size() <= 0) return dogs;
                //dogs中有数据，则通过dogs中的一条数据来获取D类字段集
                dFields = dogs.get(0).getClass().getDeclaredFields();
            } else {
                dFields = clazzD.getDeclaredFields();
            }
            keys = Lists.newArrayList();
            for (Field field : dFields) {
                keys.add(field.getName());
            }
        }

        if (dogs == null) dogs = Lists.newArrayList();

        //用于存储收集的问题key
        Set<String> errorKeySet = Sets.newHashSet();
        //2,遍历args
        for (A a : args) {
            Class clazzA = a.getClass();
            dogs.add((D) compose(a, clazzD, clazzA, keys, keyMap, errorKeySet));
        }
        if (errorKeySet.size() > 0)
            LOGGER.info("1000 === 创建PropertyDescriptor失败 === errorKeySet:{}", errorKeySet);
        return dogs;
    }

    public static <D, A> D compose(A a, Class<D> clazzD, Class<A> clazzA, List<String> keys, Map<String, String> keyMap, Set<String> errorKeySet) {
        D d = null;
        try {
            d = clazzD.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        for (String key : keys) {
            if ("serialVersionUID".equals(key)) continue;

            //默认取相同名称的字段
            String valueKey = key;
            if (keyMap != null && keyMap.size() > 0) {
                Set<String> keySet = keyMap.keySet();
                if (keySet.contains(key)) {
                    valueKey = keyMap.get(key);
                }
            }

            try {
                PropertyDescriptor pdA = new PropertyDescriptor(valueKey, clazzA);
                Method aGetter = pdA.getReadMethod();
                Object fieldValueA = aGetter.invoke(a);

                //此处fieldValueA类型可能与D中相应字段不匹配，需要根据key值区别处理

                PropertyDescriptor pdD = new PropertyDescriptor(key, clazzD);
                Method dSetter = pdD.getWriteMethod();
                dSetter.invoke(d, fieldValueA);
            } catch (IntrospectionException e) {
                    /*创建PropertyDescriptor失败，指定字段名（key）不存在于相应类中
                    或无其对应getters(boolean字段可以为is开头) or setters*/
                if (!errorKeySet.contains(key)) {
                    errorKeySet.add(key);
                }
                continue;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                /*参数类型不匹配*/
                LOGGER.info("1002 === 参数类型不匹配 === key:{}", key);
            }
        }
        return d;
    }
}
