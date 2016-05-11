package com.itao.sender;

/**
 * @author Created by Vicdor(linss) on 2016-05-12 01:21.
 */
public interface Sender {

    void sendData(Object var1);

    Sender setDefault(String var1, String var2);

    void sendData(Object var1, String var2);

    String getResponseType();

    String getSendData(Object var1);
}
