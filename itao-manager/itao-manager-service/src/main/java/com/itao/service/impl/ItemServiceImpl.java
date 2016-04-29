package com.itao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.itao.dto.request.EUDataGridListRequestVo;
import com.itao.dto.response.EUDataGridResultVo;
import com.itao.mapper.TbItemMapper;
import com.itao.pojo.TbItem;
import com.itao.service.ItemService;
import static com.itao.util.CommonUtils.exist;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**商品管理Service
 * Created by Vicdor on 2016-04-29-0029.
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Resource
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem getItemById(Long itemId) {
        return tbItemMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public EUDataGridResultVo getItemList(EUDataGridListRequestVo requestVo) {
        Integer page = 0;
        Integer rows = 20;
        if(exist(requestVo)){
            Integer p = requestVo.getPage();
            Integer r = requestVo.getRows();
            if(exist(p)) page = p;
            if(exist(r)) rows = r;
        }
        Map<String,Object> map = Maps.newHashMap();
        /*分页处理*/
        PageHelper.startPage(page,rows);
        List<TbItem> list = tbItemMapper.getListByMap(map);
        EUDataGridResultVo resultVo = new EUDataGridResultVo();
        resultVo.setRows(list);
        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
        resultVo.setTotal(pageInfo.getTotal());
        return resultVo;
    }
}
