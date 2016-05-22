package com.itao.service.impl;

import static com.itao.code.BusinessCode.*;
import com.itao.exception.BusinessException;
import com.itao.mapper.TbContentCategoryMapper;
import com.itao.po.TbContentCategory;
import com.itao.service.ContentCategoryService;
import com.itao.util.CommonUtils;
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

    @Override
    public ItaoResult delContentCategory(Long id) {
        //得到当前的分类内容
        TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(id);
        if(CommonUtils.notExist(category)) throw new BusinessException(NO_CONTENT_CATEGORY);
        List<TbContentCategory> sons = tbContentCategoryMapper.getListByParentId(id);
        if(CommonUtils.isSetNotEmpty(sons)){
            for (TbContentCategory son : sons) {
                son.delContentCategory();
                //递归删除子集
                delContentCategory(son.getId());
            }
        }
        //删除当前结点
        TbContentCategory contentCategory = new TbContentCategory(id);
        contentCategory.delContentCategory();
        tbContentCategoryMapper.updateByPrimaryKeySelective(contentCategory);

        //得到父id
        Long parentId = category.getParentId();
        List<TbContentCategory> sonList = tbContentCategoryMapper.getListByParentId(parentId);
        if(CommonUtils.isSetEmpty(sonList)){
            TbContentCategory father = new TbContentCategory(parentId);
            father.setIsParent(false);
            tbContentCategoryMapper.updateByPrimaryKeySelective(father);
        }

        return ItaoResult.ok();
    }

    @Override
    public ItaoResult modContentCategory(Long id, String name) {
        tbContentCategoryMapper.updateByPrimaryKeySelective(new TbContentCategory(name,id));
        return ItaoResult.ok();
    }
}
