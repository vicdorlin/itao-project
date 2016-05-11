package com.itao.sender;

/**
 * @author Created by Vicdor(linss) on 2016-05-12 01:32.
 */
public interface Code {
    int sysStat = 1;
    int serverStat = 2;
    String CODENAME = "code";
    String CODEMSG = "msg";

    String getCode();

    String getMsg();

    boolean isLoged();

    int getCodeStat();
}
