package com.jx.xztongcheng.ui.activity;

import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.utils.CountDownTimerUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ChangePhoneActivity extends BaseActivity {


    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.ll_mobile)
    LinearLayout llMobile;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.et_verify)
    EditText etVerify;
    @BindView(R.id.auth_code)
    TextView authCode;
    @BindView(R.id.ll_et)
    LinearLayout llEt;
    @BindView(R.id.tv_done)
    TextView tvDone;

    @Override
    public int intiLayout() {
        return R.layout.activity_change_phone;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.auth_code, R.id.tv_done})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.auth_code:
                getVerifyCode();
                break;
            case R.id.tv_done:
                if (tvDone.getText().toString().equals("更换手机号")) {
                    llMobile.setVisibility(View.GONE);
                    llEt.setVisibility(View.VISIBLE);
                    tvDone.setText("确定更换");
                } else {
                    String account = etMobile.getText().toString();
                    String verify = etVerify.getText().toString();
                    RetrofitManager.build().create(UserService.class)
                            .changeMobile(account, verify)
                            .compose(RxScheduler.<BaseResponse<EmptyResponse>>observeOnMainThread())
                            .as(RxScheduler.<BaseResponse<EmptyResponse>>bindLifecycle(this))
                            .subscribe(new BaseObserver<EmptyResponse>() {
                                @Override
                                public void onSuccess(EmptyResponse emptyResponse) {
                                    ToastUtils.showShort("修改成功");
                                    finish();
                                    ActivityUtils.startActivity(LoginActivity.class);
                                }

                                @Override
                                public void onFail(int code, String error) {

                                }
                            });
                }
                break;
        }
    }

    private void getVerifyCode() {
        String account = etMobile.getText().toString();
        if ("".equals(account)) {
            ToastUtils.showShort("请输入手机号");
            return;
        }
        RetrofitManager.build().create(UserService.class)
                .getCode(1, account)
                .compose(RxScheduler.<BaseResponse<EmptyResponse>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<EmptyResponse>>bindLifecycle(this))
                .subscribe(new BaseObserver<EmptyResponse>() {
                    @Override
                    public void onSuccess(EmptyResponse emptyResponse) {
                        ToastUtils.showShort("验证码发送成功");
                        CountDownTimerUtils count = new CountDownTimerUtils(authCode, 60000);
                        count.start();
                    }

                    @Override
                    public void onFail(int code, String error) {

                    }
                });
    }

}
