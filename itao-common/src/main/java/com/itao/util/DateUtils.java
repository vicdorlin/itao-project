package com.itao.util;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by Vicdor on 2016-05-07-0007.
 */
public class DateUtils {
    /**
     * 默认时间格式
     */
    public static final String DATE_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_YYYYMMDD = "yyyyMMdd";

    /**
     * 获取当前时间指定格式时间字符串
     * @param format 格式
     * @return 指定格式时间字符串
     */
    public static String getDateStr(String format){
        return new DateTime().toString(format);
    }

    /**
     * 将当前时间转为默认格式字符串
     * @return 默认格式字符串
     */
    public static String getDateStr(){
        return getDateStr(DATE_DEFAULT);
    }

    /**
     * 将date转为默认格式的时间字符串
     * @param date 要被转换的date
     * @return 默认格式的时间字符串
     */
    public static String getDateStr(Date date){
        return new DateTime(date).toString(DATE_DEFAULT);
    }

    /**
     * 将date转为指定格式的时间字符串
     * @param date 要被转换的date
     * @param format 指定转换格式
     * @return 指定格式的时间字符串
     */
    public static String getDateStr(Date date,String format){
        if(CommonUtils.notExist(format)) return getDateStr(date);
        return new DateTime(date).toString(format);
    }

}
