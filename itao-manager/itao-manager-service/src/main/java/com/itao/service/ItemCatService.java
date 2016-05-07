package com.itao.service;

import com.itao.vo.response.EUTreeNode;

import java.util.List;

/**
 * Created by Vicdor on 2016-05-04-0004.
 */
public interface ItemCatService {

    List<EUTreeNode> getCatList(Long parentId);
}
