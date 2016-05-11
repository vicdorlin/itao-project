package com.itao.sender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Created by Vicdor(linss) on 2016-05-12 01:42.
 */
public class HtmlSender extends BaseSender {

    private String templateHtml;
    private String url;

    public HtmlSender(HttpServletRequest request, HttpServletResponse response) {
        this.init(request, response);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected String booleanToData(boolean result) {
        String temp = null;
        if(result) {
            temp = this.getTemplateHtml(this.getSuccMsg());
        } else {
            temp = this.getTemplateHtml(this.getFailMsg());
        }

        return temp;
    }

    protected String codeToData(Code code) {
        return this.getTemplateHtml(code.getMsg());
    }

    protected String expectionToData(Exception exp) {
        return this.getTemplateHtml(this.getFailMsg());
    }

    public String getResponseType() {
        return "text/html";
    }

    protected String mapToData(Map<String, Object> map) {
        HttpServletRequest request = this.getRequest();
        if(request != null) {
            ;
        }

        Iterator i$ = map.entrySet().iterator();

        while(i$.hasNext()) {
            Map.Entry entry = (Map.Entry)i$.next();
            request.setAttribute((String)entry.getKey(), entry.getValue());
        }

        return null;
    }

    private String getTemplateHtml(String cont) {
        this.templateHtml = this.getDefaultTemplateHtml(cont, this.url);
        return this.templateHtml;
    }

    private String getDefaultTemplateHtml(String cont, String url) {
        if(url == null) {
            url = "document.referrer";
        }

        String hrefScript = "location.href=\"" + url + "\"";
        if(url.equals("document.referrer")) {
            hrefScript = "location.href=document.referrer";
        }

        if(url.equals("window.history.go(-1)")) {
            hrefScript = "window.history.go(-1)";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        sb.append("<html>");
        sb.append("<head><TITLE></TITLE>");
        sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
        sb.append("");
        sb.append("</head><body></body><script type=\"text/javascript\">alert(\"" + cont + "\");" + hrefScript + ";</script></html>");
        return sb.toString();
    }
}