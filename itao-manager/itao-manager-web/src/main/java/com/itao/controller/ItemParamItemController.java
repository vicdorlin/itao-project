package com.itao.controller;

import com.itao.mapper.TbItemParamItemMapper;
import com.itao.po.TbItemParamItem;
import com.itao.util.CommonUtils;
import com.itao.util.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 商品规格参数
 *
 * @author Created by Vicdor(linss) on 2016-05-10 02:23.
 */
@Controller
@RequestMapping("item/param")
public class ItemParamItemController {
    @Resource
    private TbItemParamItemMapper tbItemParamItemMapper;

    @RequestMapping("item/{itemId}")
    public String getStringItemParamByItemId(@PathVariable Long itemId, Model model) {
        TbItemParamItem itemParamItem = tbItemParamItemMapper.getStringItemParamByItemId(itemId);
        // TODO: 2016-05-11-0011 异常处理
        if (CommonUtils.notExist(itemParamItem)) {
            return "";
        }
        //取规格参数信息
        String paramData = itemParamItem.getParamData();
        List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
        //生成html
        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for (Map m : jsonList) {
            sb.append("		<tr>\n");
            sb.append("			<th class=\"tdTitle\" colspan=\"2\">" + m.get("group") + "</th>\n");
            sb.append("		</tr>\n");
            sb.append("		<tr>\n");
            sb.append("        </tr>\n");
            List<Map> lm = (List<Map>) m.get("params");
            for (Map lMap : lm) {
                sb.append("		<tr>\n");
                sb.append("			<td class=\"tdTitle\">" + lMap.get("v") + "</td><td>360</td>\n");
                sb.append("		</tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");

        model.addAttribute("itemParam",sb.toString());
        return "item";
    }

}
