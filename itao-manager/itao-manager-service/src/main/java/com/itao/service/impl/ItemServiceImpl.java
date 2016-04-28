package com.itao.service.impl;

import com.itao.mapper.TbItemMapper;
import com.itao.pojo.TbItem;
import com.itao.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**商品管理Service
 * Created by Vicdor on 2016-04-29-0029.
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Resource
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }
}
