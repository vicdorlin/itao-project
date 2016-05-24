package com.itao.rest.service.impl;

import com.google.common.collect.Maps;
import com.itao.mapper.TbContentMapper;
import com.itao.po.TbContent;
import com.itao.rest.service.ContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 内容管理
 * @author Created by Vicdor(linss) on 2016-05-25 01:48.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Resource
    private TbContentMapper tbContentMapper;

    /**
     * 根据内容分类id查询内容列表
     * @param contentCid
     * @return
     */
    @Override
    public List<TbContent> getContentList(Long contentCid) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("categoryId",contentCid);
        return tbContentMapper.getListByMap(map);
    }
}
