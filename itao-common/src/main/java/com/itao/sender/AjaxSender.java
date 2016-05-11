package com.itao.sender;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Created by Vicdor(linss) on 2016-05-12 01:23.
 */
public class AjaxSender extends BaseSender {
    private SysCode sycCode = SysCode.newInstance();

    public AjaxSender(HttpServletRequest request, HttpServletResponse response) {
        this.init(request, response);
    }

    protected String booleanToData(boolean result) {
        StringBuffer sb;
        if(result) {
            sb = new StringBuffer();
            sb.append("{\"").append(this.sycCode.CODENAME).append("\":\"").append(this.sycCode.SUCC).append("\"}");
            return sb.toString();
        } else {
            sb = new StringBuffer();
            sb.append("{\"").append(this.sycCode.CODENAME).append("\":\"").append(this.sycCode.UNKNOWN).append("\"}");
            return sb.toString();
        }
    }

    protected String codeToData(Code code) {
        if(code == null) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append("{\"").append(this.sycCode.CODENAME).append("\":").append(code.getCode()).append(",\"msg\":\"").append(code.getMsg()).append("\"}");
            return sb.toString();
        }
    }

    protected String expectionToData(Exception exp) {
        StringBuffer sb = new StringBuffer();
        sb.append("{\"").append(this.sycCode.CODENAME).append("\":\"").append(this.sycCode.UNKNOWN).append("\"}");
        return sb.toString();
    }

    protected String mapToData(Map<String, Object> map) {
        return JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[]{SerializerFeature.WriteDateUseDateFormat});
    }

    public String getResponseType() {
        return "text/html";
    }
}
