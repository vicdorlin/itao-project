package com.itao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.itao.mapper.TbItemParamMapper;
import com.itao.po.TbItemParam;
import com.itao.vo.request.EUDataGridListRequestVo;
import com.itao.vo.response.EUDataGridResultVo;
import com.itao.vo.response.ItaoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.itao.util.CommonUtils.notExist;

/**
 * 商品规格
 * @author Created by Vicdor(linss) on 2016-05-08 17:37.
 */
@Controller
@RequestMapping("item/param")
public class ItemParamController {
    @Resource
    private TbItemParamMapper tbItemParamMapper;

    /**商品规格列表
     * ==普通查询跳过service层
     * @param requestVo 请求的分页参数
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public EUDataGridResultVo getItemParamList(EUDataGridListRequestVo requestVo){
        Integer page;
        Integer rows;
        if(notExist(requestVo) || notExist(page = requestVo.getPage()) || notExist(rows = requestVo.getRows())){
            page = 0;
            rows = 20;
        }
        Map<String,Object> map = Maps.newHashMap();
        /*分页处理*/
        PageHelper.startPage(page,rows);
        List<TbItemParam> list = tbItemParamMapper.getListByMap(map);
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        return new EUDataGridResultVo(list,pageInfo.getTotal());
    }

    /**
     * 根据商品类目id查询商品规格
     * @param itemCatId 商品类目id
     * @return
     */
    @ResponseBody
    @RequestMapping("query/itemcatid/{itemCatId}")
    public ItaoResult getItemParamByCid(@PathVariable Long itemCatId){
        if(notExist(itemCatId)){
            //TODO 异常处理
        }
        return ItaoResult.ok(tbItemParamMapper.getByItemCatId(itemCatId));
    }
}
