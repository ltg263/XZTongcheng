package com.jx.xztongcheng.ui.activity;

import android.content.DialogInterface;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.bean.response.GrabStatusResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.utils.DialogHelper;
import com.jx.xztongcheng.utils.DialogUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {


    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.changePwd)
    LinearLayout changePwd;
    @BindView(R.id.changePhone)
    LinearLayout changePhone;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    @BindView(R.id.tv_sfz_status)
    TextView tvSfzStatus;
    @BindView(R.id.sv_qishou)
    Switch svQishou;

    @Override
    public int intiLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "设置", true);
        if (App.getInstance().getUserInfo().getAuthStatus() == 0 || App.getInstance().getUserInfo().getAuthStatus() == 3) {
            tvSfzStatus.setText("未绑定");
        } else if (App.getInstance().getUserInfo().getAuthStatus() == 1) {
            tvSfzStatus.setText("正在认证中");
        } else {
            tvSfzStatus.setText("已绑定");
        }
        RetrofitManager.build().create(UserService.class)
                .getGrab()
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.<BaseResponse<GrabStatusResponse>>bindLifecycle(SettingActivity.this))
                .subscribe(new BaseObserver<GrabStatusResponse>() {
                    @Override
                    public void onSuccess(GrabStatusResponse emptyResponse) {
                        if (emptyResponse == null) {
                            return;
                        }
                        if (emptyResponse.getGrabStatus() == 1) {
                            svQishou.setChecked(true);
                        } else {
                            svQishou.setChecked(false);
                        }
                    }

                    @Override
                    public void onFail(int code, String error) {
                    }
                });

        svQishou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!DialogHelper.isAuthStatus(SettingActivity.this)) {
                    svQishou.setChecked(!b);
                    Log.w(this.toString(), "实名认证未完成");
                    return;
                }
                RetrofitManager.build().create(UserService.class)
                        .updateGrab(b ? 1 : 2)
                        .compose(RxScheduler.observeOnMainThread())
                        .as(RxScheduler.<BaseResponse<EmptyResponse>>bindLifecycle(SettingActivity.this))
                        .subscribe(new BaseObserver<EmptyResponse>() {
                            @Override
                            public void onSuccess(EmptyResponse emptyResponse) {
                            }

                            @Override
                            public void onFail(int code, String error) {
                            }
                        });
            }
        });

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.changePwd, R.id.changePhone, R.id.tv_logout, R.id.bind_site, R.id.ll_verify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.changePwd:
                ActivityUtils.startActivity(ForgetPasswordActivity.class);
                break;
            case R.id.changePhone:

                break;
            case R.id.bind_site:
                ActivityUtils.startActivity(BindingSiteActivity.class);
                break;
            case R.id.ll_verify:
                if (DialogHelper.isBindingStatus(SettingActivity.this)) {
                    if (App.getInstance().getUserInfo().getAuthStatus() == 0 || App.getInstance().getUserInfo().getAuthStatus() == 3) {
                        ActivityUtils.startActivity(NameAuthenticationActivity.class);
                    }
                }
                break;
            case R.id.tv_logout:
                DialogUtils.cancelDialog(SettingActivity.this, "退出登录", "是否退出登录"
                        , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                App.clearLogin();
                                ToastUtils.showShort("退出成功");
                                finish();
                            }
                        }).show();
                break;
        }
    }
}
