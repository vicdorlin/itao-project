package com.itao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.itao.mapper.TbItemDescMapper;
import com.itao.po.TbItemDesc;
import com.itao.vo.request.EUDataGridListRequestVo;
import com.itao.vo.request.ItemAddVo;
import com.itao.vo.response.EUDataGridResultVo;
import com.itao.mapper.TbItemMapper;
import com.itao.po.TbItem;
import com.itao.service.ItemService;
import static com.itao.util.CommonUtils.exist;
import static com.itao.util.CommonUtils.notExist;

import com.itao.vo.response.ItaoResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**商品管理Service
 * Created by Vicdor on 2016-04-29-0029.
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Resource
    private TbItemMapper tbItemMapper;
    @Resource
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }

    /*@Override
    public EUDataGridResultVo getItemList(EUDataGridListRequestVo requestVo) {
        Integer page;
        Integer rows;
        if(notExist(requestVo) || notExist(page = requestVo.getPage()) || notExist(rows = requestVo.getRows())){
            page = 0;
            rows = 20;
        }
        Map<String,Object> map = Maps.newHashMap();
        *//*分页处理*//*
        PageHelper.startPage(page,rows);
        List<TbItem> list = tbItemMapper.getListByMap(map);
        EUDataGridResultVo resultVo = new EUDataGridResultVo();
        resultVo.setRows(list);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        resultVo.setTotal(pageInfo.getTotal());
        return resultVo;
    }*/

    @Override
    public ItaoResult addItem(ItemAddVo itemAddVo,String itemDesc) {
        //TODO 抛业务异常
        if(notExist(itemAddVo) || notExist(itemDesc)) return null;

        //添加商品
        TbItem item = itemAddVo.copyTo(TbItem.class);
        item.buildItem();
        tbItemMapper.insert(item);

        //添加商品描述
        TbItemDesc tbItemDesc = new TbItemDesc(item.getId(),item.getCreated(),item.getUpdated(),itemDesc);
        tbItemDescMapper.insert(tbItemDesc);

        return ItaoResult.ok();
    }
}
