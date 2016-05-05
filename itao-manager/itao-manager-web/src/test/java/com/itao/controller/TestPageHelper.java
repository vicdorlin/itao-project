package com.itao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.itao.mapper.TbItemMapper;
import com.itao.pojo.TbItem;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by Vicdor on 2016-05-06-0006.
 */
public class TestPageHelper {
    @Test
    public void testPageHelper(){
        //创建一个Spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        //从spring容器中获得Mapper代理对象
        TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
        //执行查询并分页
        Map<String,Object> map = Maps.newHashMap();
        //分页处理
        PageHelper.startPage(1,10);
        List<TbItem> list = tbItemMapper.getListByMap(map);
        for(TbItem item : list){
            System.out.println(item.getTitle());
        }
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        System.out.println("---\n---\n---\n"+pageInfo.getTotal());
    }

}
