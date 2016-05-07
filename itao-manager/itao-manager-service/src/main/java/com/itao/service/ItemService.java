package com.itao.service;

import com.itao.vo.request.EUDataGridListRequestVo;
import com.itao.po.TbItem;
import com.itao.vo.request.ItemAddVo;
import com.itao.vo.response.EUDataGridResultVo;
import com.itao.vo.response.ItaoResult;

/**
 * Created by Vicdor on 2016-04-29-0029.
 */
public interface ItemService {
    /**
     * 根据商品id获取商品信息
     * @param itemId
     * @return
     */
    TbItem getItemById(Long itemId);

    /**
     * 商品列表查询
     * @param requestVo
     * @return
     */
    EUDataGridResultVo getItemList(EUDataGridListRequestVo requestVo);

    /**
     * 添加商品
     * @param itemAddVo 封装接收前端传来的数据
     * @return
     */
    ItaoResult addItem(ItemAddVo itemAddVo);
}
