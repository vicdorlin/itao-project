package com.itao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vicdor on 2016-04-29-0029.
 */
@Controller
public class PageController {

    @RequestMapping("")
    public String showIndexPage(){
        return "index";
    }

    /**
     * 展示其他页面
     * @param page
     * @return
     */
    @RequestMapping("{page}")
    public String showPages(@PathVariable String page){
        return page;
    }
}
