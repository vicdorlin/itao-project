package com.itao.mapper;

import com.itao.pojo.TbItem;
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

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);
}