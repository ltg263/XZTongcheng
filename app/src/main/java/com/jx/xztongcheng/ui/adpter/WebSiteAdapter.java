package com.jx.xztongcheng.ui.adpter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.bean.response.WebSiteReponse;

import java.util.List;

public class WebSiteAdapter extends BaseQuickAdapter<WebSiteReponse.ListBean, BaseViewHolder> {

    public WebSiteAdapter(List data) {
        super(R.layout.item_website, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WebSiteReponse.ListBean item) {

        helper.setText(R.id.tv_name, item.getName());
        helper.setGone(R.id.iv_select, item.isSelect());

    }

}
