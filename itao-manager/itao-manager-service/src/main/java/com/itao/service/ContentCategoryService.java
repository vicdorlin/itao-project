package com.itao.service;

import com.itao.vo.response.EUTreeNode;
import com.itao.vo.response.ItaoResult;

import java.util.List;

/**
 * 内容分类管理
 * @author Created by Vicdor(linss) on 2016-05-18 23:36.
 */
public interface ContentCategoryService {
    /**
     * 列表
     * @param parentId
     * @return
     */
    List<EUTreeNode> getContentCategoryList(Long parentId);

    /**
     * 新增分类
     * @param parentId
     * @param name
     * @return
     */
    ItaoResult insertContentCategory(long parentId,String name);

    /**
     * 删除分类（软删除）
     * @param id
     * @return
     */
    ItaoResult delContentCategory(Long id);
}
