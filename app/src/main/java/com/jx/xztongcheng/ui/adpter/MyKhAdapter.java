package com.jx.xztongcheng.ui.adpter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.bean.response.ExpressAddressDTOS;
import com.jx.xztongcheng.bean.response.OrderListBean;

import java.util.List;

public class MyKhAdapter extends BaseQuickAdapter<OrderListBean, BaseViewHolder> {

    public MyKhAdapter(List data) {
        super(R.layout.item_my_kh, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, OrderListBean item) {
        helper.setText(R.id.tv_name, "姓名：         电话：").setText(R.id.tv_dz, "订单号："+item.getGeneralOrderNo());
    }
}
