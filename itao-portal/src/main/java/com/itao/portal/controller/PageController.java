package com.itao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面展示Controller
 * Created by Vicdor on 2016-04-29-0029.
 */
@Controller
public class PageController {

    /**
     * 展示首页
     * @return
     */
    @RequestMapping("index")
    public String showIndexPage(){
        return "index";
    }

    /**
     * httpClient 调用post请求测试用接口
     * @return
     */
    @ResponseBody
    @RequestMapping(value="httpclient/post",method = RequestMethod.POST)
    public String testPost(String name,String pwd){
        return "name === "+name+" === pwd === "+pwd;
    }

}
