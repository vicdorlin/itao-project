package com.itao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.itao.mapper.TbContentMapper;
import com.itao.po.TbContent;
import com.itao.service.ContentService;
import com.itao.vo.request.ContentAddVo;
import com.itao.vo.response.EUDataGridResultVo;
import com.itao.vo.response.ItaoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 类目内容管理
 * @author Created by Vicdor(linss) on 2016-05-22 17:22.
 */
@Controller
@RequestMapping("content")
public class ContentController {

    @Resource
    private TbContentMapper tbContentMapper;
    @Resource
    private ContentService contentService;

    /**
     * 根据分类id获取内容列表
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping("query/list")
    public EUDataGridResultVo getContentListBycategoryId(Long categoryId,int page, int rows){
        Map<String,Object> map = Maps.newHashMap();
        map.put("categoryId",categoryId);
        /*分页处理*/
        PageHelper.startPage(page, rows);
        List<TbContent> list = tbContentMapper.getListByMap(map);
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        return new EUDataGridResultVo(list,pageInfo.getTotal());
    }

    /**
     * 新增内容
     * @param addVo
     * @return
     */
    @ResponseBody
    @RequestMapping("save")
    public ItaoResult addContent(ContentAddVo addVo){
        return contentService.addContent(addVo);
    }

}
