package com.itao.exception;

/**
 * @author Created by Vicdor(linss) on 2016-05-20 00:59.
 */
public interface Code {
    String CODENAME = "code";
    String CODEMSG = "msg";

    int getCode();
    String getMsg();
}
