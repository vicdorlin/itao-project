package com.itao.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Vicdor on 2016-05-07-0007.
 */
public interface PictureService {
    /**
     * 图片上传
     * @param uploadFile 前端提交上来的图片
     * @return 成功：<"error",0><"url,"..."> 失败：<"error",1><"message","...">
     */
    Map<String,Object> uploadPictrue(MultipartFile uploadFile) throws IOException;
}
