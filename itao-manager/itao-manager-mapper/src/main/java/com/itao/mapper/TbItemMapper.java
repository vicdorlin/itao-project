package com.itao.mapper;

import com.itao.po.TbItem;
import com.itao.sender.PageRequestMap;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    TbItem selectByPrimaryKey(Long id);

    List<TbItem> getListByMap(Map<String,Object> searchMap);

    List<TbItem> getListByPageRequestMap(PageRequestMap pr);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);
}