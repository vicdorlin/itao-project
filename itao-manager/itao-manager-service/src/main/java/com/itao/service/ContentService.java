package com.itao.service;

import com.itao.vo.request.ContentAddVo;
import com.itao.vo.response.ItaoResult;

/**
 * @author Created by Vicdor(linss) on 2016-05-22 18:46.
 */
public interface ContentService {
    ItaoResult addContent(ContentAddVo addVo);
}
