package com.itao.util;

import java.math.BigDecimal;

/**
 * 封装对一些数据的操作
 *
 * @author vicdor
 * @create 2016-06-22 13:09
 */
public class NumberFormatUtils {
    /**
     * 厘转元，并保留二位小数
     * @param liValue
     * @return
     */
    public static String liToYuan(Long liValue){
        if(liValue == null){
            return "0.00";
        }
        return new BigDecimal(liValue).divide(new BigDecimal(1000), 2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static void main(String[] args) {

    }
}
