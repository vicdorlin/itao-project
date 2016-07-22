package com.itao.rest.controller;

import com.itao.rest.domain.CatResult;
import com.itao.rest.service.ItemCatService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Created by Vicdor(linss) on 2016-05-17 01:36.
 */
@Controller
@RequestMapping("itemcat")
public class ItemCatController {

    @Resource
    private ItemCatService itemCatService;

    /*@RequestMapping(value = "all",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback){
        CatResult catResult = itemCatService.getItemCatList();
        String json = toJson(catResult);
        return callback+"("+json+");";
    }*/
    @RequestMapping("all")
    @ResponseBody
    public Object getItemCatList(String callback) {
        CatResult catResult = itemCatService.getItemCatList();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
