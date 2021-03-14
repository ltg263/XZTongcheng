package com.jx.xztongcheng.ui.adpter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.bean.response.ExpressAddressDTOS;
import com.jx.xztongcheng.bean.response.OrderListBean;

import java.util.List;

public class ToolMdAdapter extends BaseQuickAdapter<OrderListBean, BaseViewHolder> {

    public ToolMdAdapter(List data) {
        super(R.layout.item_tool_md, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean item) {
        ExpressAddressDTOS dtos = item.getExpressOrderDTOS().get(0).getExpressAddressDTOS();
        helper.setText(R.id.tv_ddmh, item.getGeneralOrderNo())
                .setText(R.id.tv_name_j,"收件人："+dtos.getMailName()).setText(R.id.tv_phone_j,"电话："+dtos.getMailMobile())
                .setText(R.id.tv_name_s,"寄件人："+dtos.getToName()).setText(R.id.tv_phone_s,"电话："+dtos.getToMobile());
    }

}
