package com.jx.xztongcheng.ui.adpter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.bean.response.StatisResponse;

import java.util.List;

public class TimeDataAdapter extends BaseQuickAdapter<StatisResponse.ListBean, BaseViewHolder> {

    public TimeDataAdapter(List data) {
        super(R.layout.item_time_data, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StatisResponse.ListBean item) {
        helper.setText(R.id.tv_time, item.getDate())
                .setText(R.id.tv_cnt, item.getContractMoneys());

    }

}
