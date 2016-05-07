package com.itao.service.impl;

import com.google.common.collect.Maps;
import com.itao.service.PictureService;
import com.itao.util.CommonUtils;
import com.itao.util.DateUtils;
import com.itao.util.FtpUtil;
import com.itao.util.IDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Vicdor on 2016-05-07-0007.
 */
@Service
public class PictureServiceImpl implements PictureService {
    //注入配置属性
    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private Integer port;
    @Value("${ftp.user}")
    private String user;
    @Value("${ftp.pwd}")
    private String pwd;
    @Value("${ftp.path}")
    private String path;
    @Value("${image.host}")
    private String imageHost;
    @Value("${image.item.dir}")
    private String itemDir;

    @Override
    public Map<String,Object> uploadPictrue(MultipartFile uploadFile) throws IOException {
        Map<String,Object> resultMap = Maps.newHashMap();
        String imagePath = DateUtils.getDateStr(DateUtils.DATE_YYYYMMDD);
        //TODO 抛业务异常
        if(CommonUtils.notExist(uploadFile)) return null;
        //取原文件名
        String oldName = uploadFile.getOriginalFilename();
        //生成新文件名
        //UUID.randomUUID();
        String newName = IDUtils.generateImageName(oldName);

        //图片上传
        String itemImagePath = itemDir + "/" + imagePath;
        boolean uploadResult = FtpUtil.uploadFile(host,port,user,pwd,path,itemImagePath,newName,uploadFile.getInputStream());
        if(uploadResult){
            resultMap.put("error",0);
            resultMap.put("url",imageHost + "/" + itemImagePath + "/" + newName);
        }else {
            resultMap.put("error",1);
            resultMap.put("message","图片上传失败，请联系管理员");
        }
        return resultMap;
    }
}
