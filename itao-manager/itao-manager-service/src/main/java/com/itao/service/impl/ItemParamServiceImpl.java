package com.itao.service.impl;

import com.itao.mapper.TbItemParamMapper;
import com.itao.po.TbItemParam;
import com.itao.service.ItemParamService;
import com.itao.util.CommonUtils;
import com.itao.vo.request.ItemParamAddVo;
import com.itao.vo.response.ItaoResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商品规格service
 * @author Created by Vicdor(linss) on 2016-05-09 01:54.
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Resource
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public ItaoResult addItemParam(ItemParamAddVo itemParamAddVo) {
        if(CommonUtils.notExist(itemParamAddVo)){
            // TODO: 2016-05-09-0009 抛业务异常
            return null;
        }
        //添加商品规格
        TbItemParam itemParam = itemParamAddVo.copyTo(TbItemParam.class);
        itemParam.buildParam();
        tbItemParamMapper.insert(itemParam);
        return ItaoResult.ok();
    }
}
