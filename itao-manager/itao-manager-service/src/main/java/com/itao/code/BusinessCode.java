package com.itao.code;

import com.itao.exception.Code;

/**
 * @author Created by Vicdor(linss) on 2016-05-21 01:50.
 */
public enum  BusinessCode implements Code {
    NO_CONTENT_CATEGORY(2000,"分类不存在");

    private int code;
    private String msg;

    BusinessCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
