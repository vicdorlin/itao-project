package com.itao.controller;

import com.itao.service.ContentCategoryService;
import com.itao.vo.response.EUTreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 内容分类管理
 * @author Created by Vicdor(linss) on 2016-05-19 01:35.
 */
@Controller
@RequestMapping("content/category")
public class ContentCategoryController {
    @Resource
    private ContentCategoryService contentCategoryService;

    /**
     * 列表
     * @param parentId
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public List<EUTreeNode> getContentCategoryList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        return contentCategoryService.getContentCategoryList(parentId);
    }
}
