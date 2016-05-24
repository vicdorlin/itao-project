package com.itao.rest.controller;

import com.itao.po.TbContent;
import com.itao.rest.service.ContentService;
import com.itao.util.ExceptionUtil;
import com.itao.vo.response.ItaoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 内容管理Controller
 * @author Created by Vicdor(linss) on 2016-05-25 02:02.
 */
@Controller
@RequestMapping("content")
public class ContentController {
    @Resource
    private ContentService contentService;

    @ResponseBody
    @RequestMapping("list/{contentCategoryId}")
    public ItaoResult getContentList(@PathVariable Long contentCategoryId){
        try {
            List<TbContent> list = contentService.getContentList(contentCategoryId);
            return ItaoResult.ok(list);
        }catch (Exception e){
            return ItaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
