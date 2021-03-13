package com.jx.xztongcheng.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.utils.CommonUtils;

import butterknife.BindView;
import butterknife.OnClick;

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

//    BankCardBean.ListBean card;
    int type;
    double money;
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
        money = getIntent().getDoubleExtra("money", 0);
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
                    if (Double.parseDouble(s.toString()) > money) {
                        etAmount.setText(money + "");
                    }
            }
        });

//        if (type == 5) {
//            RetrofitManager.build().withdrawalRule()
//                    .compose(RxSchedulers.<BaseData<Rule>>compose())
//                    .as(RxSchedulers.<BaseData<Rule>>bindLifecycle(this))
//                    .subscribe(new BaseObserver<Rule>() {
//                        @Override
//                        public void onHandleSuccess(BaseData<Rule> t) throws Exception {
//                            Rule rule = t.getData();
//                            slogon.setText(rule.getExt1()+"："+"\n"+rule.getExt2());
//                        }
//                    });
//        }
//
//        RetrofitManager.build().getDefaultCard()
//                .compose(RxSchedulers.<BaseData<BankCardBean.ListBean>>compose())
//                .as(RxSchedulers.<BaseData<BankCardBean.ListBean>>bindLifecycle(this))
//                .subscribe(new BaseObserver<BankCardBean.ListBean>() {
//                    @Override
//                    public void onHandleSuccess(BaseData<BankCardBean.ListBean> t) throws Exception {
//                        if (t.getData()!=null) {
//                            card = t.getData();
//                            selectCard.setText(card.getRemark() + card.getAccountNo());
//                        }
//                    }
//                });

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
                etAmount.setText(money + "");
                break;
            case R.id.btnDeposit:
                if (CommonUtils.isFastDoubleClick()) {
                    return;
                }
//                if (card == null || Double.parseDouble(etAmount.getText().toString()) == 0) {
//                    ToastUtils.showShort("请正确填写信息");
//                    return;
//                }

//                RetrofitManager.build().setDefaultCard(card.getId())
//                        .compose(RxSchedulers.<BaseData<Object>>compose())
//                        .as(RxSchedulers.<BaseData<Object>>bindLifecycle(this))
//                        .subscribe(new BaseObserver<Object>() {
//                            @Override
//                            public void onHandleSuccess(BaseData<Object> t) throws Exception {
//                                RetrofitManager.build().goDeposit(
//                                        Double.parseDouble(StringUtils.isEmpty(etAmount.getText().toString()) ? "0" : etAmount.getText().toString())
//                                        , type, card.getId())
//                                        .compose(RxSchedulers.<BaseData<Object>>compose())
//                                        .as(RxSchedulers.<BaseData<Object>>bindLifecycle(DepositActivity.this))
//                                        .subscribe(new BaseObserver<Object>() {
//                                            @Override
//                                            public void onHandleSuccess(BaseData<Object> t) throws Exception {
//                                                ToastUtils.showShort("申请成功");
//                                                finish();
//                                            }
//                                        });
//                            }
//                        });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (resultCode) {
            case 2:
                if (data != null) {
//                    if (data.hasExtra("item")) {
//                        card = (BankCardBean.ListBean) data.getSerializableExtra("item");
//                        selectCard.setText(card.getRemark() + card.getAccountNo());
//                    }
                }
                break;
        }
    }
}
