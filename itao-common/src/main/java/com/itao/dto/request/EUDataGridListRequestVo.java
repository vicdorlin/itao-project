package com.itao.dto.request;

/**
 * Created by Vicdor on 2016-04-30-0030.
 */
public class EUDataGridListRequestVo {
    private Integer page;
    private Integer rows;

    public EUDataGridListRequestVo() {
    }

    public EUDataGridListRequestVo(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
