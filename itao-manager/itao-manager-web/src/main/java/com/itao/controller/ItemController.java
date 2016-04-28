package com.itao.controller;

import com.itao.pojo.TbItem;
import com.itao.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Vicdor on 2016-04-29-0029.
 */
@Controller
public class ItemController {
    @Resource
    private ItemService itemService;

    @ResponseBody
    @RequestMapping("/item/{itemId}")
    public TbItem getItemById(@PathVariable Long itemId){
        return itemService.getItemById(itemId);
    }
}
