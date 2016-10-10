package com.itao.sender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author Created by Vicdor(linss) on 2016-05-12 01:30.
 */
public abstract class BaseSender implements Sender {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String succMsg;
    private String failMsg;
    protected SysCode sysCode = SysCode.newInstance();

    public Sender setDefault(String succ, String fail) {
        this.succMsg = succ;
        this.failMsg = fail;
        return this;
    }

    protected String getSuccMsg() {
        return this.succMsg;
    }

    protected String getFailMsg() {
        return this.failMsg;
    }

    public BaseSender() {
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void sendData(Object obj) {
        this.sendData(obj, null);
    }

    public void sendData(Object obj, String type) {
        if(obj != null) {
            try {
                String e = this.getSendData(obj);
                if(type == null) {
                    type = this.getResponseType();
                }

                this.response.setContentType(type + ";charset=utf-8");
                PrintWriter out = this.response.getWriter();
                StringBuffer buffer = new StringBuffer();
                buffer.append(e);
                out.print(buffer);
                out.flush();
                out.close();
            } catch (Exception var6) {
                throw new RuntimeException(var6);
            }
        }
    }

    public String getSendData(Object obj) {
        String str = null;
        if(obj instanceof Code) {
            str = this.codeToData((Code)obj);
        } else if(obj instanceof Boolean) {
            str = this.booleanToData(((Boolean)obj).booleanValue());
        } else if(obj instanceof Exception) {
            str = this.expectionToData((Exception)obj);
        } else if(obj instanceof Map) {
            Map map = (Map)obj;
            if(!map.containsKey(this.sysCode.CODENAME)) {
                map.put(this.sysCode.CODENAME, this.sysCode.SUCC);
            }

            str = this.mapToData(map);
        } else {
            str = obj.toString();
            if("1".equals(str)) {
                str = "{\"" + this.sysCode.CODENAME + "\":\"" + this.sysCode.SUCC + "\"}";
            } else if(String.valueOf(this.sysCode.SUCC).equals(str)) {
                str = "{\"" + this.sysCode.CODENAME + "\":\"" + this.sysCode.SUCC + "\"}";
            }
        }

        return str;
    }

    protected abstract String codeToData(Code var1);

    protected abstract String booleanToData(boolean var1);

    protected abstract String expectionToData(Exception var1);

    protected abstract String mapToData(Map<String, Object> var1);

    public abstract String getResponseType();
}
