package com.itao.controller;

import com.itao.vo.response.EUTreeNode;
import com.itao.service.ItemCatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**商品分类管理Controller
 * Created by Vicdor on 2016-05-04-0004.
 */
@Controller
@RequestMapping("item/cat")
public class ItemCatController {

    @Resource
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping("list")
    private List<EUTreeNode> getCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        return itemCatService.getCatList(parentId);
    }
}
