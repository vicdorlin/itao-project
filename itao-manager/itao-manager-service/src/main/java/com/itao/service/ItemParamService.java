package com.itao.service;

import com.itao.vo.request.ItemParamAddVo;
import com.itao.vo.response.ItaoResult;

/**
 * 商品规格service
 * @author Created by Vicdor(linss) on 2016-05-09 01:49.
 */
public interface ItemParamService {

    /**
     * @param itemParamAddVo
     * @return
     */
    ItaoResult addItemParam(ItemParamAddVo itemParamAddVo);
}
