package com.jx.xztongcheng.ui.activity;

import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.event.LoginEvent;
import com.jx.xztongcheng.bean.request.LoginRequest;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.bean.response.LoginResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.utils.CommonUtils;
import com.jx.xztongcheng.utils.ConstValues;
import com.jx.xztongcheng.utils.CountDownTimerUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_verify)
    EditText etVerify;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.auth_code)
    TextView authCode;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.ll_yzm)
    LinearLayout ll_yzm;
    @BindView(R.id.ll_password)
    LinearLayout ll_password;

    private int type = 0;

    @Override
    public int intiLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "", true);
        changeUI();
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_phone, R.id.tv_code, R.id.auth_code, R.id.tv_forget, R.id.tv_register, R.id.tv_login,R.id.ll_yhxy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_phone:
                type = 0;
                changeUI();
                break;
            case R.id.tv_code:
                type = 1;
                changeUI();
                break;
            case R.id.auth_code:
                getVerifyCode();
                break;
            case R.id.tv_forget:
                readyGoActivity(ForgetPasswordActivity.class);
                break;
            case R.id.tv_register:
                readyGoActivity(RegisterActivity.class);
                break;
            case R.id.tv_login:
                login();
                break;
            case R.id.ll_yhxy:
                readyGoActivity(WebViewActivity.class);
                break;
        }
    }

    private void login() {
        LoginRequest bean = new LoginRequest();
        bean.setUsername(etAccount.getText().toString());
        bean.setAndroidId(CommonUtils.getAndroid_Id());
        if (type == 0) {
            bean.setPassword(etPassword.getText().toString());
            bean.setGrantType("account_password");
        }else {
            bean.setVerifyCode(etVerify.getText().toString());
            bean.setGrantType("sms_code" );
        }
        SPUtils.getInstance().put(ConstValues.TOKENID, "");
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(bean));
        RetrofitManager.build().create(UserService.class)
                .login(requestBody)
                .compose(RxScheduler.<BaseResponse<LoginResponse>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<LoginResponse>>bindLifecycle(this))
                .subscribe(new BaseObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        SPUtils.getInstance().put(ConstValues.TOKENID, "Bearer" + " " + response.getAccess_token());
                        App.getInstance().setUserInfo(response.getUserInfo());
                        ToastUtils.showShort("登录成功");
                        EventBus.getDefault().post(new LoginEvent());
                        finish();
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                    }
                });
    }


    private void changeUI() {
        if (type == 0) {
            tvPhone.setTextSize(22);
            tvCode.setTextSize(18);
            tvPhone.setTextColor(getResources().getColor(R.color.black33));
            tvCode.setTextColor(getResources().getColor(R.color.black66));
            ll_yzm.setVisibility(View.GONE);
            ll_password.setVisibility(View.VISIBLE);
//            etVerify.setHint("请输入密码");
//            authCode.setVisibility(View.GONE);
        } else {
            tvPhone.setTextSize(18);
            tvCode.setTextSize(22);
            tvPhone.setTextColor(getResources().getColor(R.color.black66));
            tvCode.setTextColor(getResources().getColor(R.color.black33));
            ll_yzm.setVisibility(View.VISIBLE);
            ll_password.setVisibility(View.GONE);
//            etVerify.setHint("请输入验证码");
//            authCode.setVisibility(View.VISIBLE);
        }
    }

    private void getVerifyCode() {
        String account = etAccount.getText().toString();
        if ("".equals(account)) {
            ToastUtils.showShort("请输入手机号");
            return;
        }
        RetrofitManager.build().create(UserService.class)
                .getCode(2, account)
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

//    private class CountDownTimerUtils extends CountDownTimer {
//        private TextView mTextView;
//
//        public CountDownTimerUtils(TextView textView, long millisInFuture) {
//            super(millisInFuture, 1000);
//            this.mTextView = textView;
//        }
//
//        @Override
//        public void onTick(long millisUntilFinished) {
//            mTextView.setClickable(false); //设置不可点击
//            mTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorButtonDisable));
//            mTextView.setText(millisUntilFinished / 1000 + "秒后重新获取"); //设置倒计时时间
//        }
//
//        @Override
//        public void onFinish() {
//            mTextView.setText("再次获取");
//            mTextView.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.color_blue_theme));
//            mTextView.setClickable(true);//重新获得点击
//        }
//    }

}
