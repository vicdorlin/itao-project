package com.itao.controller;

import com.google.common.collect.Maps;
import static com.itao.planet.Constants.*;

import com.itao.sender.PageRequestMap;
import com.itao.util.WebUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

/**
 * @author Created by Vicdor(linss) on 2016-05-12 00:13.
 */
public class ItemControllerTest implements BaseTest{
    @Test
    public void testGetItemById(){
        Map<String,Object> map = Maps.newHashMap();
        try {
            String m = WebUtils.doPost(HOST+"item/info/974401",map, CHARSET_UTF8,20000,20000);
            System.out.println(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testList(){
        Map<String,Object> map = Maps.newHashMap();
        try {
            String m = WebUtils.doPost(HOST+"item/list",map, CHARSET_UTF8,20000,20000);
            System.out.println(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTestList(){
        Map<String,Object> map = Maps.newHashMap();
        map.put("sidx","id");
        map.put("sord","desc");
//        map.put("id","936920");
        try {
            String m = WebUtils.doPost(HOST+"item/test/list",map, CHARSET_UTF8,20000,20000);
            System.out.println(m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
