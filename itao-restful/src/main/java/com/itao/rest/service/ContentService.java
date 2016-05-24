package com.itao.rest.service;

import com.itao.po.TbContent;

import java.util.List;

/**
 * @author Created by Vicdor(linss) on 2016-05-25 01:46.
 */
public interface ContentService {
    List<TbContent> getContentList(Long contentCid);
}
