package com.itao.service.impl;

import com.itao.mapper.TbContentMapper;
import com.itao.po.TbContent;
import com.itao.service.ContentService;
import com.itao.vo.request.ContentAddVo;
import com.itao.vo.response.ItaoResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Created by Vicdor(linss) on 2016-05-22 18:46.
 */
@Service
public class ContentServiceImpl implements ContentService{
    @Resource
    private TbContentMapper tbContentMapper;

    @Override
    public ItaoResult addContent(ContentAddVo addVo) {
        TbContent tbContent = addVo.copyTo(TbContent.class);
        tbContent.buildContent();
        tbContentMapper.insert(tbContent);
        return ItaoResult.ok();
    }
}
