package com.itao.rest.domain;

import java.util.List;

/**
 * @author Created by Vicdor(linss) on 2016-05-16 23:55.
 */
public class CatResult {

    private List<?> data;

    public CatResult(List<?> data) {
        this.data = data;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
