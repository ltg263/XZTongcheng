package com.jx.xztongcheng.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.event.CarListBean;
import com.jx.xztongcheng.bean.response.CoreOrderList;
import com.jx.xztongcheng.bean.response.OrderListBean;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.ui.activity.print.ToolMdDetailsActivity;
import com.jx.xztongcheng.ui.adpter.ToolMdAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ToolCarListActivity extends BaseActivity {
    @BindView(R.id.include)
    Toolbar mToolbar;
    @BindView(R.id.rv_data)
    RecyclerView mRvData;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    ToolMdAdapter timeDataAdapter;
    int page = 1, pageSize = 20;
    private List<OrderListBean> beanList;

    @Override
    public int intiLayout() {
        return R.layout.activity_tool_md_list;
    }

    @Override
    public void initView() {
//        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.theme_color));
        setToolbar(mToolbar, "车辆列表", true);
        mToolbar.setNavigationIcon(R.mipmap.icon_common_back);

    }

    @Override
    public void initData() {
        timeDataAdapter = new ToolMdAdapter(null);
        mRvData.setAdapter(timeDataAdapter);
        timeDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent mIntent = new Intent(ToolCarListActivity.this, ToolMdDetailsActivity.class);
                mIntent.putExtra("id",timeDataAdapter.getItem(position).getExpressOrderDTOS().get(0).getExpressOrderId()+"");
                startActivity(mIntent);
            }
        });

        refreshLoad();
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
        timeDataAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                loadData();
            }
        }, mRvData);
        timeDataAdapter.disableLoadMoreIfNotFullPage();
        loadData();
    }

    private void loadData(){
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        RetrofitManager.build().create(OrderService.class).getCarList(map)
                .compose(RxScheduler.<BaseResponse<CarListBean>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<CarListBean>>bindLifecycle(this))
                .subscribe(new BaseObserver<CarListBean>() {
                    @Override
                    public void onSuccess(CarListBean coreOrderList) {
//                        if (page == 1) {
//                            beanList = coreOrderList.getList();
//                            timeDataAdapter.setNewData(beanList);
//                            if (refresh.isRefreshing()) {
//                                refresh.setRefreshing(false);
//                            }
//                        } else {
//                            if (coreOrderList.getList().size() == 0) {
//                                timeDataAdapter.loadMoreEnd();
//                            } else {
//                                timeDataAdapter.loadMoreComplete();
//                                beanList.addAll(coreOrderList.getList());
//                                timeDataAdapter.addData(beanList);
//                            }
//                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                        timeDataAdapter.loadMoreFail();
                        if (refresh.isRefreshing()) {
                            refresh.setRefreshing(false);
                        }
                    }
                });
    }
}
