package com.itao.sender;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Created by Vicdor(linss) on 2016-05-12 01:53.
 */
public class PageRequestMap extends HashMap<String,Object> {
    private static final long serialVersionUID = -6039383318682465663L;
    public static final String P_PAGE = "page";
    public static final String P_PAGESIZE = "pagesize";
    public static final String P_TOTAL = "total";
    public boolean jqGridSubmit;

    public PageRequestMap(HttpServletRequest request) {
        this(request, 1, 20);
    }

    public PageRequestMap() {
        this.jqGridSubmit = false;
        this.pageInit(1, 20);
    }

    public PageRequestMap(HttpServletRequest request, int defalutPage, int defaultPagesize) {
        this();
        this.pageInit(defalutPage, defaultPagesize);
        if(request != null) {
            String currPage = null;
            String pageSize = null;
            if(request.getParameter("nd") != null && request.getParameter("_search") != null) {
                currPage = request.getParameter("page");
                pageSize = request.getParameter("rows");
                this.jqGridSubmit = true;
            } else {
                currPage = request.getParameter("page");
                pageSize = request.getParameter("pageSize") == null?request.getParameter("pagesize"):request.getParameter("pageSize");
                this.jqGridSubmit = false;
            }

            if(currPage != null) {
                this.put("page", currPage);
            }

            if(pageSize != null) {
                this.put("pagesize", pageSize);
            }

            Map map = request.getParameterMap();
            Iterator i$ = map.entrySet().iterator();

            while(i$.hasNext()) {
                Entry entry = (Entry)i$.next();
                String key = (String)entry.getKey();
                String[] m = (String[])((String[])entry.getValue());
                if(m.length == 1) {
                    this.put(key, m[0]);
                } else {
                    this.put(key, m);
                }
            }
        }
    }

    public long getLong(String key) {
        Object obj = this.get(key);
        return Long.parseLong(obj.toString());
    }

    public int getInt(String key) {
        Object obj = this.get(key);
        return Integer.parseInt(obj.toString());
    }

    public boolean getBoolean(String key) {
        Object obj = this.get(key);
        return Boolean.parseBoolean(obj.toString());
    }

    public String getString(String key) {
        Object obj = this.get(key);
        return obj == null?null:obj.toString();
    }

    private void pageInit(int defalutPage, int defaultPagesize) {
        this.put("page", Integer.valueOf(defalutPage));
        this.put("pagesize", Integer.valueOf(defaultPagesize));
    }

    public long getCurrResult() {
        int page = this.getInt("page");
        int pagesize = this.getInt("pagesize");
        long currentResult = (long)((page - 1) * pagesize);
        if(currentResult < 0L) {
            currentResult = 0L;
        }
        return currentResult;
    }

    public boolean isJqGridSubmit() {
        return this.jqGridSubmit;
    }

    public void setJqGridSubmit(boolean jqGridSubmit) {
        this.jqGridSubmit = jqGridSubmit;
    }
}
