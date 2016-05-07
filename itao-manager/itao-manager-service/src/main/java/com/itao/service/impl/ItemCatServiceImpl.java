package com.itao.service.impl;

import com.itao.vo.response.EUTreeNode;
import com.itao.mapper.TbItemCatMapper;
import com.itao.po.TbItemCat;
import com.itao.service.ItemCatService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理
 * Created by Vicdor on 2016-05-04-0004.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EUTreeNode> getCatList(Long parentId) {
        List<TbItemCat> catList = tbItemCatMapper.getListByParentId(parentId);
        List<EUTreeNode> nodes = new ArrayList<>();
        for(TbItemCat cat : catList){
            EUTreeNode node = new EUTreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent() ? "closed" : "open");
            nodes.add(node);
        }
        return nodes;
    }
}
