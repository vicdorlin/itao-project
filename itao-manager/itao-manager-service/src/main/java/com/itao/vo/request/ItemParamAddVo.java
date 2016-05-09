package com.itao.vo.request;

import com.itao.vo.BaseVo;

/**
 * @author Created by Vicdor(linss) on 2016-05-09 23:22.
 */
public class ItemParamAddVo extends BaseVo {
    private Long itemCatId;
    private String paramData;

    public ItemParamAddVo(Long itemCatId, String paramData) {
        this.itemCatId = itemCatId;
        this.paramData = paramData;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }
}
