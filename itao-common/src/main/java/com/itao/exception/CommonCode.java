package com.itao.exception;

/**
 * @author Created by Vicdor(linss) on 2016-05-20 01:09.
 */
public enum CommonCode implements Code{
    NO_LIST_HERE(1001,"无数据可导")
    ;
    private int code;
    private String msg;

    CommonCode(int code, String msg) {
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
