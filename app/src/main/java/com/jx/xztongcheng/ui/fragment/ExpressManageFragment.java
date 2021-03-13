package com.jx.xztongcheng.ui.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jx.xztongcheng.JWebSocketClient;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.base.BaseFragment;
import com.jx.xztongcheng.bean.event.ExpressEvent;
import com.jx.xztongcheng.bean.event.LoginEvent;
import com.jx.xztongcheng.bean.response.CoreOrderList;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.bean.response.OrderListBean;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.ui.activity.ExpressDetailActivity;
import com.jx.xztongcheng.ui.activity.MultiExpressActivity;
import com.jx.xztongcheng.ui.adpter.ExpressManageAdapter;
import com.jx.xztongcheng.utils.DialogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ExpressManageFragment extends BaseFragment {

    private ExpressManageAdapter expressAdapter;
    private int orderStatus;
    private List<OrderListBean> beanList = new ArrayList<>();
    @BindView(R.id.expressRv)
    RecyclerView orderRv;
    Intent intent;
    Map<String, Object> map = new HashMap<>();
    int page = 1, pageSize = 20;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private int type; //1最新 2我的
    private int expressType;//1快递,2跑腿
    private int fastStatus;


    public static ExpressManageFragment newInstance(int orderStatus, int type, int expressType, int fastStatus) {
        ExpressManageFragment fragment = new ExpressManageFragment();
        Bundle args = new Bundle();
        args.putInt("orderStatus", orderStatus);
        args.putInt("type", type);
        args.putInt("expressType", expressType);
        args.putInt("fastStatus", fastStatus);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_express_manage;
    }

    @Override
    protected void initView() {
        orderStatus = getArguments().getInt("orderStatus");
        expressType = getArguments().getInt("expressType");
        fastStatus = getArguments().getInt("fastStatus", 0);
        type = getArguments().getInt("type");
        if (orderStatus != 0)
            map.put("orderStatus", orderStatus);
        if (fastStatus != 0) {
            map.put("fastStatus", fastStatus);
        }
        map.put("page", page);
        map.put("pageSize", pageSize);
        map.put("lat", App.lat);
        map.put("lng", App.lon);


        expressAdapter = new ExpressManageAdapter(type);
        orderRv.setAdapter(expressAdapter);
        expressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (expressAdapter.getData().get(position).getExpressOrderDTOS().size() > 1) {
                    Intent intent = new Intent(getActivity(), MultiExpressActivity.class);
                    intent.putExtra("data", (Serializable) expressAdapter.getData().get(position).getExpressOrderDTOS());
                    intent.putExtra("expressGeneralOrderId", expressAdapter.getData().get(position).getExpressGeneralOrderId());
                    intent.putExtra("price", expressAdapter.getData().get(position).getTotalAmount() + expressAdapter.getData().get(position).getTotalBonus());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), ExpressDetailActivity.class);
                    intent.putExtra("orderId", expressAdapter.getData().get(position).getExpressOrderDTOS().get(0).getExpressOrderId());
                    startActivity(intent);
                }
            }
        });

        expressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                final int id = beanList.get(position).getExpressGeneralOrderId();
                switch (view.getId()) {
                    case R.id.tv_status:
                        if (((TextView)(view)).getText().toString().equals("接单")) {
                            DialogUtils.cancelDialog(getActivity(), "接受订单", "确认接受订单吗？"
                                    , new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            RetrofitManager.build().create(OrderService.class).receiveExpress(id)
                                                    .compose(RxScheduler.<BaseResponse<EmptyResponse>>observeOnMainThread())
                                                    .as(RxScheduler.<BaseResponse<EmptyResponse>>bindLifecycle(getActivity()))
                                                    .subscribe(new BaseObserver<EmptyResponse>() {
                                                        @Override
                                                        public void onSuccess(EmptyResponse coreOrderList) {
                                                            ToastUtils.showShort("接单成功");
                                                            EventBus.getDefault().post(new ExpressEvent());
                                                        }

                                                        @Override
                                                        public void onFail(int code, String msg) {
                                                            super.onFail(code, msg);
                                                        }
                                                    });
                                        }
                                    }).show();
                        } else {
                            if (expressAdapter.getData().get(position).getExpressOrderDTOS().size() > 1) {
                                Intent intent = new Intent(getActivity(), MultiExpressActivity.class);
                                intent.putExtra("data", (Serializable) expressAdapter.getData().get(position).getExpressOrderDTOS());
                                intent.putExtra("expressGeneralOrderId", expressAdapter.getData().get(position).getExpressGeneralOrderId());
                                intent.putExtra("price", expressAdapter.getData().get(position).getTotalAmount() + expressAdapter.getData().get(position).getTotalBonus());
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getActivity(), ExpressDetailActivity.class);
                                intent.putExtra("orderId", expressAdapter.getData().get(position).getExpressOrderDTOS().get(0).getExpressOrderId());
                                startActivity(intent);
                            }
                        }
                        break;
                    case R.id.tv_transfer:
                        if (expressAdapter.getData().get(position).getTransferStatus() == 0 || expressAdapter.getData().get(position).getTransferStatus() > 3) {
                            //申请转单
                            DialogUtils.cancelDialog(getActivity(), "转单", "确认转单吗？"
                                    , new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            RetrofitManager.build().create(OrderService.class).transfer(id)
                                                    .compose(RxScheduler.<BaseResponse<EmptyResponse>>observeOnMainThread())
                                                    .as(RxScheduler.<BaseResponse<EmptyResponse>>bindLifecycle(getActivity()))
                                                    .subscribe(new BaseObserver<EmptyResponse>() {
                                                        @Override
                                                        public void onSuccess(EmptyResponse coreOrderList) {
                                                            ToastUtils.showShort("请求成功");
                                                            refreshData();
                                                        }

                                                        @Override
                                                        public void onFail(int code, String msg) {
                                                            super.onFail(code, msg);
                                                        }
                                                    });
                                        }
                                    }).show();
                        }
                        break;
                }
            }
        });

        refreshLoad();
    }

    private void refreshData() {
        page = 1;
        loadData();
    }

    private void refreshLoad() {

        refresh.setColorSchemeColors(getResources().getColor(R.color.theme_color));
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }
        });
        expressAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ++page;
                loadData();
            }
        }, orderRv);
        expressAdapter.disableLoadMoreIfNotFullPage();

    }

    @Override
    protected void initData() {
        loadData();
    }

    public void loadData() {
        map.put("page", page);
        map.put("takeModel", expressType);
        if (type == 1) {
            RetrofitManager.build().create(OrderService.class).coreList(map)
                    .compose(RxScheduler.<BaseResponse<CoreOrderList>>observeOnMainThread())
                    .as(RxScheduler.<BaseResponse<CoreOrderList>>bindLifecycle(this))
                    .subscribe(new BaseObserver<CoreOrderList>() {
                        @Override
                        public void onSuccess(CoreOrderList coreOrderList) {
                            if (page == 1) {
                                beanList = coreOrderList.getList();
                                expressAdapter.setNewData(beanList);
                                if (refresh.isRefreshing()) {
                                    refresh.setRefreshing(false);
                                }
                            } else {
                                if (coreOrderList.getList().size() == 0) {
                                    expressAdapter.loadMoreEnd();
                                } else {
                                    expressAdapter.loadMoreComplete();
                                    beanList.addAll(coreOrderList.getList());
                                    expressAdapter.addData(beanList);
                                }
                            }
                        }

                        @Override
                        public void onFail(int code, String msg) {
                            super.onFail(code, msg);
                            expressAdapter.loadMoreFail();
                            if (refresh.isRefreshing()) {
                                refresh.setRefreshing(false);
                            }
                        }
                    });
        } else {
            RetrofitManager.build().create(OrderService.class).myOrderList(map)
                    .compose(RxScheduler.<BaseResponse<CoreOrderList>>observeOnMainThread())
                    .as(RxScheduler.<BaseResponse<CoreOrderList>>bindLifecycle(this))
                    .subscribe(new BaseObserver<CoreOrderList>() {
                        @Override
                        public void onSuccess(CoreOrderList coreOrderList) {
                            if (page == 1) {
                                beanList = coreOrderList.getList();
                                expressAdapter.setNewData(beanList);
                                if (refresh.isRefreshing()) {
                                    refresh.setRefreshing(false);
                                }
                            } else {
                                if (coreOrderList.getList().size() == 0) {
                                    expressAdapter.loadMoreEnd();
                                } else {
                                    expressAdapter.loadMoreComplete();
                                    beanList.addAll(coreOrderList.getList());
                                    expressAdapter.addData(beanList);
                                }
                            }
                        }

                        @Override
                        public void onFail(int code, String msg) {
                            super.onFail(code, msg);
                            expressAdapter.loadMoreFail();
                            if (refresh.isRefreshing()) {
                                refresh.setRefreshing(false);
                            }
                        }
                    });
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ExpressEvent event) {
        refreshData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginEvent(LoginEvent loginEvent) {
       refreshData();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
