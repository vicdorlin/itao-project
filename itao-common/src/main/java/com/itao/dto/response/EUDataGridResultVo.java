package com.itao.dto.response;

import java.util.List;

/**
 * Created by Vicdor on 2016-04-30-0030.
 */
public class EUDataGridResultVo {

    private Long total;
    private List<?> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
