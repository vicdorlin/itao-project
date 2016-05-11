package com.itao.util;

import com.itao.sender.PageRequestMap;

import java.util.HashMap;
import java.util.List;

/**
 * @author Created by Vicdor(linss) on 2016-05-12 02:41.
 */
public class ParseUtils {
    public ParseUtils() {
    }

    public static <T> HashMap<String, Object> jqGridPaper(PageRequestMap pr, List<T> obj) {
        HashMap map = new HashMap();
        map.put("rows", obj);
        if(pr.containsKey("total")) {
            map.put("total", pr.get("total"));
            map.put("page", Integer.valueOf(pr.getInt("page")));
            long total = pr.getLong("total");
            long pagesize = (long)pr.getInt("pagesize");
            long pageTotal = (total + pagesize - 1L) / pagesize;
            map.put("pages", Long.valueOf(pageTotal));
        }

        return map;
    }

    public static <T> HashMap<String, Object> jqGridTree(List<T> obj) {
        HashMap map = new HashMap();
        map.put("types", obj);
        return map;
    }
}
