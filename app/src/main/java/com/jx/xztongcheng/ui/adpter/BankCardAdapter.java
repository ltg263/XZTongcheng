package com.jx.xztongcheng.ui.adpter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jx.xztongcheng.R;

import java.util.List;

public class BankCardAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    public BankCardAdapter(List data) {
        super(R.layout.item_bank_card, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
//        helper.setText(R.id.bankName,item.getRemark())
//                .setText(R.id.cardNo,item.getAccountNo());
//
//        helper.addOnClickListener(R.id.iv_delete);

    }

}
