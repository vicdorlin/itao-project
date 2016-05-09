package com.itao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.itao.mapper.TbItemMapper;
import com.itao.vo.request.EUDataGridListRequestVo;
import com.itao.vo.request.ItemAddVo;
import com.itao.vo.response.EUDataGridResultVo;
import com.itao.po.TbItem;
import com.itao.service.ItemService;
import com.itao.vo.response.ItaoResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.itao.util.CommonUtils.notExist;

/**
 * 商品管理
 * Created by Vicdor on 2016-04-29-0029.
 */
@Controller
@RequestMapping("item")
public class ItemController {
    @Resource
    private ItemService itemService;
    @Resource
    private TbItemMapper tbItemMapper;

    /**
     * 根据Id获取商品信息
     *
     * @param itemId
     * @return
     */
    @ResponseBody
    @RequestMapping("info/{itemId}")
    public TbItem getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    /**
     * 获取商品信息分页列表
     *
     * @param requestVo
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public EUDataGridResultVo getItemList(EUDataGridListRequestVo requestVo) {
//        return itemService.getItemList(requestVo);
        Integer page;
        Integer rows;
        if (notExist(requestVo) || notExist(page = requestVo.getPage()) || notExist(rows = requestVo.getRows())) {
            page = 0;
            rows = 20;
        }
        Map<String, Object> map = Maps.newHashMap();
        /*分页处理*/
        PageHelper.startPage(page, rows);
        List<TbItem> list = tbItemMapper.getListByMap(map);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        return new EUDataGridResultVo(list, pageInfo.getTotal());
    }

    /**
     * 新增商品
     *
     * @param addVo 前端提交过来的商品信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ItaoResult addItem(ItemAddVo addVo, String desc, String itemParams) {
        return itemService.addItem(addVo, desc ,itemParams);
    }
}
