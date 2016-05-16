package com.itao.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Created by Vicdor(linss) on 2016-05-16 23:45.
 */
public class CatNode {

    @JsonProperty("u")
    private String url;
    @JsonProperty("n")
    private String name;
    @JsonProperty("i")
    private List<?> item;

    public CatNode(String url, String name, List<?> item) {
        this.url = url;
        this.name = name;
        this.item = item;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
