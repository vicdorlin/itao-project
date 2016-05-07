package com.itao.controller;

import com.google.common.collect.Maps;
import com.itao.service.PictureService;
import com.itao.util.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * 图片处理
 * Created by Vicdor on 2016-05-07-0007.
 */
@Controller
@RequestMapping("pic")
public class PictureController {
    @Resource
    private PictureService pictureService;

    @ResponseBody
    @RequestMapping("upload")
    public String uploadImage(MultipartFile uploadFile){
        Map<String,Object> resultMap;
        try {
            resultMap = pictureService.uploadPictrue(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
            resultMap = Maps.newHashMap();
            resultMap.put("error",1);
            resultMap.put("message","图片上传异常，请联系管理员");
        }
        //由于前端插件的兼容性（不完全支持firefox)，将数据转换为json字符串返回
        return JsonUtils.toJson(resultMap);
    }
}
