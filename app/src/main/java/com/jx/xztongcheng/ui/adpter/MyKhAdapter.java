package com.jx.xztongcheng.ui.adpter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.bean.event.UserExclusiveList;

import java.util.List;

public class MyKhAdapter extends BaseQuickAdapter<UserExclusiveList.ListBean, BaseViewHolder> {

    public MyKhAdapter(List data) {
        super(R.layout.item_my_kh, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, UserExclusiveList.ListBean item) {
        helper.setText(R.id.tv_name, "姓名："+item.getUserName()).setText(R.id.tv_dz, "电话："+item.getUserMobile());
    }
}
