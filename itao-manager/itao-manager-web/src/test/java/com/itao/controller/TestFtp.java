package com.itao.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Vicdor on 2016-05-06-0006.
 */
public class TestFtp {
    @Test
    public void testFtpClient() throws IOException {
        //创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();
        //创建ftp连接
        ftpClient.connect("192.168.0.103",21);
        //使用用户名和密码登陆ftpd服务器
        ftpClient.login("ftpuser","0");
        //上传文件

        //设置上传路径
        ftpClient.changeWorkingDirectory("/home/ftpuser/nginx/html/images");

        //修改上传文件的格式（ftpClient默认传文本，而图片是二进制格式，所以图片会失真）
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        //读取本地文件
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Vicdor\\Desktop\\22.jpg"));
        /*
         *  参数一：服务器文件名
         *  参数二：上传文档的inputStream
         */
        ftpClient.storeFile("666.jpg",inputStream);
        //关闭连接
        ftpClient.logout();
        if(inputStream != null) inputStream.close();
    }
}
