package com.jx.xztongcheng.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.bean.response.WebSiteReponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.ui.adpter.WebSiteAdapter;

import butterknife.BindView;
import butterknife.OnClick;

public class BindingSiteActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar myToolbar;
    @BindView(R.id.rv_site)
    RecyclerView rvSite;
    private WebSiteAdapter webSiteAdapter;
    private int currentPos = -1;

    @Override
    public int intiLayout() {
        return R.layout.activity_binding_site;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "隶属站点", true);
        webSiteAdapter = new WebSiteAdapter(null);
        webSiteAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (currentPos != -1) {
                    webSiteAdapter.getData().get(currentPos).setSelect(false);
                    webSiteAdapter.notifyItemChanged(currentPos);
                }
                webSiteAdapter.getData().get(position).setSelect(true);
                webSiteAdapter.notifyItemChanged(position);
                currentPos = position;
            }
        });
        rvSite.setAdapter(webSiteAdapter);
    }

    @Override
    public void initData() {
        RetrofitManager.build().create(OrderService.class)
                .getWebsite(App.lon, App.lat)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(BindingSiteActivity.this))
                .subscribe(new BaseObserver<WebSiteReponse>() {
                    @Override
                    public void onSuccess(WebSiteReponse reponse) {
                        webSiteAdapter.setNewData(reponse.getList());
                    }
                });
    }

    @OnClick({R.id.tv_bind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_bind:
                if (webSiteAdapter != null && currentPos!=-1) {
                    WebSiteReponse.ListBean bean = webSiteAdapter.getData().get(currentPos);
                    RetrofitManager.build().create(OrderService.class)
                            .addSite(bean.getWebsiteId())
                            .compose(RxScheduler.observeOnMainThread())
                            .as(RxScheduler.bindLifecycle(BindingSiteActivity.this))
                            .subscribe(new BaseObserver<EmptyResponse>() {
                                @Override
                                public void onSuccess(EmptyResponse reponse) {
                                    ToastUtils.showShort("绑定成功");
                                    finish();
                                }
                            });
                }
                break;
        }
    }
}
