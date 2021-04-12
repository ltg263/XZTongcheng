package com.jx.xztongcheng.ui.activity;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.clazz.UserInfo;
import com.jx.xztongcheng.bean.event.AccountLists;
import com.jx.xztongcheng.bean.request.CashoutSaveBean;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DepositActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar myToolbar;
    @BindView(R.id.etAmount)
    EditText etAmount;
    @BindView(R.id.tvAmount)
    TextView tvAmount;
    @BindView(R.id.allDeposit)
    TextView allDeposit;
    @BindView(R.id.selectCard)
    TextView selectCard;

    int card = 0;
    int type;
    String money;
    @BindView(R.id.slogon)
    TextView slogon;

    @Override
    public int intiLayout() {
        return R.layout.activity_deposit;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "提现详情", true);
    }

    @Override
    public void initData() {
        money = App.getInstance().getUserInfo().getBalance();
        type = getIntent().getIntExtra("type", 0);

        etAmount.setText(money + "");
        tvAmount.setText("可提现金额：￥" + money);

        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!StringUtils.isEmpty(s))
                    if (Double.parseDouble(s.toString()) > Double.valueOf(money)) {
                        etAmount.setText(money);
                    }
            }
        });

    }

    @OnClick({R.id.selectCard, R.id.allDeposit, R.id.btnDeposit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.selectCard:
                Intent intent = new Intent(this, BankCardManageActivity.class);
                intent.putExtra("isSelect", true);
                startActivityForResult(intent, 1);
                break;
            case R.id.allDeposit:
                etAmount.setText(money);
                break;
            case R.id.btnDeposit:
                if (CommonUtils.isFastDoubleClick()) {
                    return;
                }
                if (card == 0 || Double.parseDouble(etAmount.getText().toString()) == 0) {
                    ToastUtils.showShort("请正确填写信息");
                    return;
                }

                CashoutSaveBean request = new CashoutSaveBean();
                request.setAmount(etAmount.getText().toString());
                request.setType("1");
                request.setAccountId(card + "");
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(request));

                RetrofitManager.build().create(UserService.class)
                        .addCashoutSave(requestBody)
                        .compose(RxScheduler.observeOnMainThread())
                        .as(RxScheduler.<BaseResponse<Boolean>>bindLifecycle(this))
                        .subscribe(new BaseObserver<Boolean>() {
                            @Override
                            public void onSuccess(Boolean mBoolean) {
                                if (mBoolean) {
                                    RetrofitManager.build().create(UserService.class).getUserInfo()
                                            .compose(RxScheduler.<BaseResponse<UserInfo>>observeOnMainThread())
                                            .subscribe(new BaseObserver<UserInfo>() {
                                                @Override
                                                public void onSuccess(UserInfo userInfo) {
                                                    App.getInstance().setUserInfo(new Gson().toJson(userInfo));
                                                    ToastUtils.showShort("提现成功进入审核");
                                                    finish();
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onFail(int code, String error) {

                            }
                        });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (resultCode) {
            case 2:
                if (data != null) {
                    if (data.hasExtra("item")) {
                        AccountLists.ListBean bean = (AccountLists.ListBean) data.getSerializableExtra("item");
                        selectCard.setText(bean.getBank() + bean.getAccountNo());
                        card = bean.getId();
                    }
                }
                break;
        }
    }
}
