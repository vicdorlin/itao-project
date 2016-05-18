package com.itao.service.impl;

import com.itao.mapper.TbContentCategoryMapper;
import com.itao.po.TbContentCategory;
import com.itao.service.ContentCategoryService;
import com.itao.vo.response.EUTreeNode;
import com.itao.vo.response.ItaoResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类管理
 * @author Created by Vicdor(linss) on 2016-05-19 01:26.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Resource
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EUTreeNode> getContentCategoryList(Long parentId) {
        List<TbContentCategory> contentCategoryList = tbContentCategoryMapper.getListByParentId(parentId);
        List<EUTreeNode> nodes = new ArrayList<>();
        for(TbContentCategory contentCategory : contentCategoryList){
            EUTreeNode node = new EUTreeNode(
                    contentCategory.getId(),
                    contentCategory.getName(),
                    contentCategory.getIsParent() ? "closed" : "open");
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public ItaoResult insertContentCategory(long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory(parentId,name);
        contentCategory.buildContentCategory();
        //添加纪录
        tbContentCategoryMapper.insert(contentCategory);
        //父节点
        TbContentCategory father = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if(!father.getIsParent()){
            father.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(father);
        }
        //返回结果
        return ItaoResult.ok(contentCategory);
    }
}
