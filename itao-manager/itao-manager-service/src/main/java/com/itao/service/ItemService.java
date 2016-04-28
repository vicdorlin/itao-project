package com.itao.service;

import com.itao.pojo.TbItem;

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
}
