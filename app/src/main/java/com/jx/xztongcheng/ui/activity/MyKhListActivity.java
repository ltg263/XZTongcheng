package com.jx.xztongcheng.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.event.UserExclusiveList;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.bean.response.OrderListBean;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.ui.adpter.MyKhAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MyKhListActivity extends BaseActivity {
    @BindView(R.id.include)
    Toolbar mToolbar;
    @BindView(R.id.rv_data)
    RecyclerView mRvData;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    MyKhAdapter mMyKhAdapter;
    int page = 1, pageSize = 20;
    private List<UserExclusiveList.ListBean> beanList;

    @Override
    public int intiLayout() {
        return R.layout.activity_my_kh_list;
    }

    @Override
    public void initView() {
        setToolbar(mToolbar, "我的客户", true);
        mToolbar.setNavigationIcon(R.mipmap.icon_common_back);

    }

    @Override
    public void initData() {
        mMyKhAdapter = new MyKhAdapter(null);
        mRvData.setAdapter(mMyKhAdapter);
        mMyKhAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
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
        mMyKhAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                loadData();
            }
        }, mRvData);
        mMyKhAdapter.disableLoadMoreIfNotFullPage();
        loadData();
    }

    private void loadData(){
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        RetrofitManager.build().create(OrderService.class).getUserExclusiveList(map)
                .compose(RxScheduler.<BaseResponse<UserExclusiveList>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<UserExclusiveList>>bindLifecycle(this))
                .subscribe(new BaseObserver<UserExclusiveList>() {
                    @Override
                    public void onSuccess(UserExclusiveList response) {
                        if (page == 1) {
                            beanList = response.getList();
                            mMyKhAdapter.setNewData(beanList);
                            if (refresh.isRefreshing()) {
                                refresh.setRefreshing(false);
                            }
                        } else {
                            if (response.getList().size() == 0) {
                                mMyKhAdapter.loadMoreEnd();
                            } else {
                                mMyKhAdapter.loadMoreComplete();
                                beanList.addAll(response.getList());
                                mMyKhAdapter.addData(beanList);
                            }
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                        mMyKhAdapter.loadMoreFail();
                        if (refresh.isRefreshing()) {
                            refresh.setRefreshing(false);
                        }
                    }
                });
    }
}
