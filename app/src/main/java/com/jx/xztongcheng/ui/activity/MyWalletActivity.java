package com.jx.xztongcheng.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.clazz.UserInfo;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;

import butterknife.BindView;
import butterknife.OnClick;

public class MyWalletActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.tv_amount)
    TextView tvAmount;

    @Override
    public int intiLayout() {
        return R.layout.activity_my_wallet;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "我的钱包", true);
    }

    @Override
    public void initData() {
        loadData();
    }

    private void loadData() {
//        RetrofitManager.build().create(UserService.class)
//                .getCurrentAmount(1)
//                .compose(RxScheduler.<BaseResponse<EmptyResponse>>observeOnMainThread())
//                .as(RxScheduler.<BaseResponse<EmptyResponse>>bindLifecycle(this))
//                .subscribe(new BaseObserver<EmptyResponse>() {
//                    @Override
//                    public void onSuccess(EmptyResponse response) {
//                    }
//
//                    @Override
//                    public void onFail(int code, String error) {
//
//                    }
//                });

        tvAmount.setText(App.getInstance().getUserInfo().getBalance());
    }

    @OnClick({R.id.goToDeposit, R.id.logRl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goToDeposit:
                Intent intent = new Intent(this, DepositActivity.class);
//                intent.putExtra("money",balanceBean.getUsableMoney());
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.logRl:
                ActivityUtils.startActivity(DepositLogActivity.class);
                break;
        }
    }
}
