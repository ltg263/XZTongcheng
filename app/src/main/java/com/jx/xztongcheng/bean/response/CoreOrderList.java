package com.jx.xztongcheng.bean.response;

import java.util.List;

/**
 * Create by Sxl on 2020/11/28.
 */
public class CoreOrderList {


    private int totalCount;
    private List<OrderListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<OrderListBean> getList() {
        return list;
    }

    public void setList(List<OrderListBean> list) {
        this.list = list;
    }

}
