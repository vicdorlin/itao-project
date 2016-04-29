package com.itao.service;

import com.itao.dto.request.EUDataGridListRequestVo;
import com.itao.pojo.TbItem;
import com.itao.dto.response.EUDataGridResultVo;

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
}
