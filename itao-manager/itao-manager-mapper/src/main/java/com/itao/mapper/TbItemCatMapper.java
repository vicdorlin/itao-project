package com.itao.mapper;

import com.itao.po.TbItemCat;

import java.util.List;

public interface TbItemCatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItemCat record);

    int insertSelective(TbItemCat record);

    TbItemCat selectByPrimaryKey(Long id);

    List<TbItemCat> getListByParentId(Long parentId);

    int updateByPrimaryKeySelective(TbItemCat record);

    int updateByPrimaryKey(TbItemCat record);
}