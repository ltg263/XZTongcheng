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
    //.setText(R.id.tv_phone_j,"电话："+dtos.getMailMobile()).setText(R.id.tv_phone_s,"电话："+dtos.getToMobile())
    @Override
    protected void convert(BaseViewHolder helper, OrderListBean item) {
        ExpressAddressDTOS dtos = item.getExpressOrderDTOS().get(0).getExpressAddressDTOS();
        helper.setText(R.id.tv_ydmh, "运单号："+item.getExpressOrderDTOS().get(0).getWaybillNumber()).setText(R.id.tv_ddmh, "订单号："+item.getGeneralOrderNo()).setGone(R.id.iv_isprint,false)
                .setText(R.id.tv_name_j,"寄件人："+dtos.getMailName()+"   "+dtos.getMailMobile()).setText(R.id.tv_dz_j,dtos.getMailAddress())
                .setText(R.id.tv_name_s,"收件人："+dtos.getToName()+"   "+dtos.getToMobile()).setText(R.id.tv_dz_s,dtos.getToAddress());
        if(item.getIsPrint()!=-1){
            helper.setGone(R.id.iv_isprint,true);
            if(item.getIsPrint() == 0){
                helper.setImageResource(R.id.iv_isprint,R.mipmap.ic_circle_no);
            }else if(item.getIsPrint() == 1){
                helper.setImageResource(R.id.iv_isprint,R.mipmap.ic_circle_yes_l);
            }
        }
        helper.addOnClickListener(R.id.iv_isprint);
    }
}
