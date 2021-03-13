package com.jx.xztongcheng.ui.adpter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jx.xztongcheng.R;

import java.util.List;

public class DepositLogAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    public DepositLogAdapter(List data) {
        super(R.layout.item_deposit_log, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
//        helper.setText(R.id.realityAmount,"到账"+item.getRealityAmount())
////                .setText(R.id.info,"（提现"+item.getApplyAmount()+"，其中"+item.getFee()+"为平台手续费）")
////                .setText(R.id.time,item.getApplyTime());
////        helper.setGone(R.id.btnCancel,false);
////        switch (item.getStatus()) {
////            case "1":
////                helper.setGone(R.id.btnCancel,true);
////                helper.setText(R.id.status,"等待处理");
////                helper.addOnClickListener(R.id.btnCancel);
////                break;
////            case "2":
////                helper.setText(R.id.status,"提现成功");
////                break;
////            case "3":
////                helper.setText(R.id.status,"提现失败");
////                break;
////            case "4":
////                helper.setText(R.id.status,"已取消");
////                break;
////        }
    }

}
