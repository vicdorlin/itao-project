package com.itao.vo.response;

import java.util.List;

/**EasyUi数据返回封装
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
