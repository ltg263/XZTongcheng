package com.jx.xztongcheng.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayBZJActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.iv_wx)
    ImageView ivWx;
    @BindView(R.id.rl_wx)
    RelativeLayout rlWx;
    @BindView(R.id.iv_alipay)
    ImageView ivAlipay;
    @BindView(R.id.rl_alipay)
    RelativeLayout rlAlipay;
    @BindView(R.id.rb_wx)
    RadioButton rbWx;
    @BindView(R.id.rb_zfb)
    RadioButton rbZfb;
    @BindView(R.id.rgb_pay)
    RadioGroup rgbPay;

    @Override
    public int intiLayout() {
        return R.layout.activity_pay_bzj;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "保证金", true);

        rgbPay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getId()) {
                    case R.id.rb_wx:
                        ToastUtils.showShort("去微信支付");
                        break;
                    case R.id.rb_zfb:
                        ToastUtils.showShort("去支付宝支付");
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {

    }

}
