package com.jx.xztongcheng.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.event.ExpressEvent;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.bean.response.OrderListBean;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.ui.adpter.ExpressMultiManageAdapter;
import com.jx.xztongcheng.utils.DialogUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MultiExpressActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar myToolbar;
    @BindView(R.id.rv_express)
    RecyclerView rvExpress;
    @BindView(R.id.cn_yf)
    TextView cnYf;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.rl_btm)
    RelativeLayout rlBtm;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    private ExpressMultiManageAdapter manageAdapter;
    private List<OrderListBean.ExpressOrderDTOSBean> expressOrderDTOS;
    private double allPrice;
    private int expressGeneralOrderId;

    @Override
    public int intiLayout() {
        return R.layout.activity_multi_express;
    }

    @Override
    public void initView() {
        expressOrderDTOS = (List<OrderListBean.ExpressOrderDTOSBean>) getIntent().getSerializableExtra("data");
        allPrice = getIntent().getDoubleExtra("price", 0);
        expressGeneralOrderId = getIntent().getIntExtra("expressGeneralOrderId", 0);

        setToolbar(myToolbar, "批量件管理", true);
        tvPrice.setText("￥" + allPrice);
        manageAdapter = new ExpressMultiManageAdapter();
        rvExpress.setAdapter(manageAdapter);
        manageAdapter.setNewData(expressOrderDTOS);

        manageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MultiExpressActivity.this, ExpressDetailActivity.class);
                intent.putExtra("orderId", manageAdapter.getData().get(position).getExpressOrderId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
    }

    @OnClick({R.id.tv_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_status:
                DialogUtils.cancelDialog(MultiExpressActivity.this, "接受订单", "确认接受订单吗？"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RetrofitManager.build().create(OrderService.class).receiveExpress(expressGeneralOrderId)
                                        .compose(RxScheduler.<BaseResponse<EmptyResponse>>observeOnMainThread())
                                        .as(RxScheduler.<BaseResponse<EmptyResponse>>bindLifecycle(MultiExpressActivity.this))
                                        .subscribe(new BaseObserver<EmptyResponse>() {
                                            @Override
                                            public void onSuccess(EmptyResponse coreOrderList) {
                                                ToastUtils.showShort("接单成功");
                                                EventBus.getDefault().post(new ExpressEvent());
                                                finish();
                                            }

                                            @Override
                                            public void onFail(int code, String msg) {
                                                super.onFail(code, msg);
                                            }
                                        });
                            }
                        }).show();
                break;
        }
    }
}
