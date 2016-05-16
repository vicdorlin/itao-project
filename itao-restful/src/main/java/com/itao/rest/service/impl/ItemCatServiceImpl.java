package com.itao.rest.service.impl;

import com.itao.mapper.TbItemCatMapper;
import com.itao.po.TbItemCat;
import com.itao.rest.domain.CatNode;
import com.itao.rest.domain.CatResult;
import com.itao.rest.service.ItemCatService;

import static com.itao.util.CommonUtils.*;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类列表
 *
 * @author Created by Vicdor(linss) on 2016-05-16 23:58.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public CatResult getItemCatList() {
        return new CatResult(getCatList(0L));
    }

    /**
     * 查询分类列表
     *
     * @param parentId
     * @return
     */
    public List<?> getCatList(Long parentId) {
        List<TbItemCat> catList = tbItemCatMapper.getListByParentId(parentId);
        List resultList = new ArrayList<>();
        for (TbItemCat tbItemCat : catList) {
            Long itemCatId = tbItemCat.getId();
            String itemName = tbItemCat.getName();
            if(tbItemCat.getIsParent()){
                List<?> item = getCatList(itemCatId);
                String name;
                if (!tbItemCat.isTopHierarchy(parentId)) {
                    name = itemName;
                }else {
                    name = "<a href='/products/"+itemCatId+".html'>"+itemName+"</a>";
                }
                String url = "/products/" + itemCatId + ".html";

                CatNode node = new CatNode(url, name, item);
                resultList.add(node);
            }else {
                resultList.add("/products/"+itemCatId+".html|"+itemName);
            }
        }
        return resultList;
    }
}
