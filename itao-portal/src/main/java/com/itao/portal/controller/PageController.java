package com.itao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
