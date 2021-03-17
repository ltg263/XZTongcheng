package com.jx.xztongcheng.ui.adpter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.bean.event.CourierListBaen;

import java.util.List;

public class DepositLogAdapter extends BaseQuickAdapter<CourierListBaen.ListBean, BaseViewHolder> {

    public DepositLogAdapter(List data) {
        super(R.layout.item_deposit_log, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourierListBaen.ListBean item) {
        helper.setText(R.id.realityAmount,"到账"+item.getRealAmount())
                .setText(R.id.info,"(提现"+item.getAmount()+"，其中"+item.getFee()+"为平台手续费)")
                .setText(R.id.time,item.getCreateTime());
        helper.setGone(R.id.btnCancel,false);
        //状态:1,审核中;2审核失败;3驳回;4成功
        switch (item.getStatus()) {
            case 1:
//                helper.setGone(R.id.btnCancel,true);
                helper.setText(R.id.status,"等待处理");
//                helper.addOnClickListener(R.id.btnCancel);
                break;
            case 2:
            case 3:
                helper.setText(R.id.status,"提现失败");
                break;
            case 4:
                helper.setText(R.id.status,"提现成功");
                break;
        }
        //1,银行卡;2微信;3,支付宝
//        switch (item.getType()) {
//            case 1:
//                helper.setText(R.id.info,"提现到银行卡");
//                break;
//            case 2:
//                helper.setText(R.id.info,"提现到微信");
//                break;
//            case 3:
//                helper.setText(R.id.info,"提现到支付宝");
//                break;
//        }
    }

}
