package com.itao.mapper;

import com.itao.po.TbItemParamItem;

public interface TbItemParamItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItemParamItem record);

    int insertSelective(TbItemParamItem record);

    TbItemParamItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItemParamItem record);

    int updateByPrimaryKeyWithBLOBs(TbItemParamItem record);

    int updateByPrimaryKey(TbItemParamItem record);

    /** 根据商品id获取商品规格参数
     * @param itemId
     * @return
     */
    TbItemParamItem getStringItemParamByItemId(Long itemId);
}