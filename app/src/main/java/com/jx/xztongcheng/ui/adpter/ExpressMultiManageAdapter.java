package com.jx.xztongcheng.ui.adpter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.bean.response.OrderListBean;

public class ExpressMultiManageAdapter extends BaseQuickAdapter<OrderListBean.ExpressOrderDTOSBean, BaseViewHolder> {

    public ExpressMultiManageAdapter() {
        super(R.layout.item_express_manage);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.ExpressOrderDTOSBean bean) {
        if (bean != null) {
            if (bean.getExpressAddressDTOS() != null) {
                helper.setText(R.id.tv_content, bean.getRemark())
                        .setText(R.id.tv_get_address1, bean.getExpressAddressDTOS().getMailAddress())
                        .setText(R.id.tv_get_address2, bean.getExpressAddressDTOS().getMailProvinceStr() + bean.getExpressAddressDTOS().getMailCityStr())
                        .setText(R.id.tv_to_address1, bean.getExpressAddressDTOS().getToAddress())
                        .setText(R.id.tv_to_address2, bean.getExpressAddressDTOS().getToProvinceStr() + bean.getExpressAddressDTOS().getToCityStr());
                helper.setGone(R.id.tv_get_distance, bean.getExpressAddressDTOS().getDistance() != 0)
                        .setGone(R.id.tv_to_distance, bean.getExpressAddressDTOS().getDistrict() != 0);
                helper.setText(R.id.tv_get_distance, bean.getExpressAddressDTOS().getDistance() + "m");
                helper.setText(R.id.tv_to_distance, bean.getExpressAddressDTOS().getDistance() + "m");
                helper.setText(R.id.tv_time, bean.getExpressAddressDTOS().getTime() + "分钟内送达");
            }
            helper.setText(R.id.tv_content, bean.getRemark());
            if (Double.parseDouble(bean.getTotalBonus()) == 0) {
                helper.setText(R.id.tv_price, "￥" + bean.getTotalAmount());
            } else {
                helper.setText(R.id.tv_price, "￥" + bean.getTotalAmount() + "(打赏：￥" + bean.getTotalBonus() + "元)");
            }
            helper.setGone(R.id.tv_price, bean.getFee() != 0);

            if (bean.getExpressDTO() != null) {
                if (bean.getExpressDTO().getTransportStatus() == 0) {
                    helper.setText(R.id.tv_status, "接单");
                } else if (bean.getExpressDTO().getTransportStatus() == 1) {
                    helper.setText(R.id.tv_status, "进行中");
                } else if (bean.getExpressDTO().getTransportStatus() == 2) {
                    helper.setText(R.id.tv_status, "已揽件");
                }
                TextView tvBtn2 = helper.getView(R.id.tv_status);
                switch (bean.getExpressDTO().getTransportStatus()) {
                    case 3:
                        tvBtn2.setText("已揽件");
                        break;
                    case 4:
                        tvBtn2.setText("到达站点");
                        break;
                    case 5:
                        tvBtn2.setText("站点发出");
                        break;
                    case 6:
                        tvBtn2.setText("派件中");
                        break;
                    case 7:
                        tvBtn2.setText("已到达");
                        break;
                }
            }

        }

        helper.addOnClickListener(R.id.tv_status);

    }

}
