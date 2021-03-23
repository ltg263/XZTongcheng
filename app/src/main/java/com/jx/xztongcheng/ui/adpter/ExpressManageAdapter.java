package com.jx.xztongcheng.ui.adpter;

import android.util.Log;
import android.widget.TextView;

import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.bean.response.CoreOrderList;
import com.jx.xztongcheng.bean.response.OrderListBean;
import com.jx.xztongcheng.utils.GlideImageLoader;

import java.util.Locale;

public class ExpressManageAdapter extends BaseQuickAdapter<OrderListBean, BaseViewHolder> {

    private int type; //1最新 2我的

    public ExpressManageAdapter(int type) {
        super(R.layout.item_express_manage);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean item) {
        OrderListBean.ExpressOrderDTOSBean bean = item.getExpressOrderDTOS().get(0);
        if (bean != null) {
            if (bean.getExpressAddressDTOS() != null) {
                helper.setText(R.id.tv_content, bean.getRemark())
                        .setText(R.id.tv_get_address1, bean.getExpressAddressDTOS().getMailAddress())
                        .setText(R.id.tv_get_address2, bean.getExpressAddressDTOS().getMailProvinceStr() + bean.getExpressAddressDTOS().getMailCityStr())
                        .setText(R.id.tv_to_address1, bean.getExpressAddressDTOS().getToAddress())
                        .setText(R.id.tv_to_address2, bean.getExpressAddressDTOS().getToProvinceStr() + bean.getExpressAddressDTOS().getToCityStr());
                helper.setGone(R.id.tv_get_distance, bean.getExpressAddressDTOS().getDistance() != 0)
                        .setGone(R.id.tv_to_distance, bean.getExpressAddressDTOS().getDistrict() != 0);
                helper.setText(R.id.tv_get_distance, bean.getExpressAddressDTOS().getDistance() + "km");
                helper.setText(R.id.tv_to_distance, bean.getExpressAddressDTOS().getDistrict() + "km");
                helper.setText(R.id.tv_time, getFriendlyTimeSpanByNow(TimeUtils.string2Millis(item.getAppointmentTime())) + "送达");
            }
            helper.setText(R.id.tv_content, bean.getRemark());
            if (item.getTotalBonus() == 0) {
                helper.setText(R.id.tv_price, "￥" + item.getTotalAmount());
            } else {
                helper.setText(R.id.tv_price, "￥" + item.getTotalAmount() + "(打赏：￥" + item.getTotalBonus() + "元)");
            }
            helper.setGone(R.id.tv_price, bean.getFee() != 0);

            if(item.getExpressType()==1){
                helper.setImageResource(R.id.iv_type,R.mipmap.ic_ddxp_kuai);
            }else{
                helper.setImageResource(R.id.iv_type,R.mipmap.ic_ddxp_pao);
            }

            Log.w("getNextTransportStatus","item.getNextTransportStatus():"+item.getNextTransportStatus());
            TextView tvBtn2 = helper.getView(R.id.tv_status);
            switch (item.getNextTransportStatus()) {
                case 1:
                    tvBtn2.setText("接单");
                    break;
                case 2:
                    tvBtn2.setText("完成取件");
                    break;
                case 3:
                    tvBtn2.setText("派送中");
                    break;
                case 4:
                    tvBtn2.setText("送达");
                    break;
                case 5:
                    tvBtn2.setText("送至站点");
                    break;
                case 6:
                    tvBtn2.setText("正在揽件");
                    break;
                case 0:
                    tvBtn2.setText("已取消");
                    break;
                default:
                    tvBtn2.setText("已完成");
                    break;
            }

        }
        if (type == 1 || item.getNextTransportStatus() == 0 || item.getNextTransportStatus() == 4 || item.getNextTransportStatus() == 5 || item.getNextTransportStatus() > 6) {
            helper.setGone(R.id.tv_transfer, false);
        } else {
            helper.setGone(R.id.tv_transfer, true);
            if (item.getTransferStatus() == 1) {
                helper.setText(R.id.tv_transfer, "转单中");
            } else if (item.getTransferStatus() == 2) {
                helper.setText(R.id.tv_transfer, "转单成功");
            } else if (item.getTransferStatus() == 3) {
                helper.setText(R.id.tv_transfer, "转单失败");
            } else {
                helper.setText(R.id.tv_transfer, "转单");
            }
        }

        helper.addOnClickListener(R.id.tv_status);
        helper.addOnClickListener(R.id.tv_transfer);
    }

    public static String getFriendlyTimeSpanByNow(final long millis) {
        long now = System.currentTimeMillis();
        long span = millis - now;
        if (span < 0)
            return String.format("%tF", millis);
        if (span < 1000) {
            return "刚刚";
        } else if (span < TimeConstants.MIN) {
            return String.format(Locale.getDefault(), "%d秒内", span / TimeConstants.SEC);
        } else if (span < TimeConstants.HOUR) {
            return String.format(Locale.getDefault(), "%d分钟内", span / TimeConstants.MIN);
        } else if (span < TimeConstants.DAY) {
            return String.format(Locale.getDefault(), "%d小时内", span / TimeConstants.HOUR);
        }
        return String.format("%tF", millis);
    }

}
