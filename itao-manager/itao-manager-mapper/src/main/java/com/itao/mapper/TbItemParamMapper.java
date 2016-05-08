package com.itao.mapper;

import com.itao.po.TbItemParam;

import java.util.List;
import java.util.Map;

public interface TbItemParamMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItemParam record);

    int insertSelective(TbItemParam record);

    TbItemParam selectByPrimaryKey(Long id);

    /**
     * 根据条件获取商品规格列表
     * @param searchMap
     * @return
     */
    List<TbItemParam> getListByMap(Map<String,Object> searchMap);

    /**
     * 根据商品类目详情
     * @param itemCatId 商品类目Id
     * @return 照常就是只有一条记录的，如果出现异常，就针对性处理数据
     */
    TbItemParam getByItemCatId(Long itemCatId);

    int updateByPrimaryKeySelective(TbItemParam record);

    int updateByPrimaryKeyWithBLOBs(TbItemParam record);

    int updateByPrimaryKey(TbItemParam record);
}