package com.jx.xztongcheng.ui.activity;

import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.event.ParamData;
import com.jx.xztongcheng.bean.request.RechargeSaveBean;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.pay.alipay.PaymentContract;
import com.jx.xztongcheng.pay.alipay.PaymentParameterBean;
import com.jx.xztongcheng.pay.alipay.PaymentPresenter;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PayBZJActivity extends BaseActivity  implements PaymentContract.View {

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
    @BindView(R.id.button_send)
    Button button_send;

    private IWXAPI api;
    int payType = 0;

    public static String WX_ID="wxed2cc4a73e26523b";
    private PaymentPresenter paymentPresenter;
    @Override
    public int intiLayout() {
        return R.layout.activity_pay_bzj;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "保证金", true);
        api = WXAPIFactory.createWXAPI(this, WX_ID);
        paymentPresenter = new PaymentPresenter(this, PayBZJActivity.this);

        rgbPay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getId()) {
                    case R.id.rb_wx:
                        payType = 1;
                        ToastUtils.showShort("去微信支付");
                        break;
                    case R.id.rb_zfb:
                        payType = 2;
                        ToastUtils.showShort("去支付宝支付");
                        break;
                }
            }
        });
        button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                appPay();
            }
        });
    }

    private void appPay() {
        payType = 2;
        if(rbWx.isChecked()){
            payType = 1;
        }
        RechargeSaveBean mRechargeSaveBean = new RechargeSaveBean();
        mRechargeSaveBean.setAmount(200);
        mRechargeSaveBean.setRechargeType(2);
        if(payType==1){
            mRechargeSaveBean.setPayType("WXAPP");
        }else{
            mRechargeSaveBean.setPayType("ALIAPP");
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(mRechargeSaveBean));
        RetrofitManager.build().create(OrderService.class)
                .OrderRecharge(body)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(this))
                .subscribe(new BaseObserver<Integer>() {
                    @Override
                    public void onSuccess(Integer id) {
                        payOrder(id);
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                        hideLoading();
                    }
                });
    }
    private void payOrder(Integer id) {
        RechargeSaveBean mRechargeSaveBean = new RechargeSaveBean();
        mRechargeSaveBean.setOrderId(id);
        if(payType==1){
            mRechargeSaveBean.setPayType("WXAPP");
        }else{
            mRechargeSaveBean.setPayType("ALIAPP");
        }
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(mRechargeSaveBean));
        RetrofitManager.build().create(OrderService.class)
                .OrderPay(body)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(this))
                .subscribe(new BaseObserver<ParamData>() {
                    @Override
                    public void onSuccess(ParamData mParamData) {
                        if(payType==1){
                            weCahtPay(mParamData);
                        }else{
                            appPayZfb(mParamData.getParam());
                        }
                        hideLoading();
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                        hideLoading();
                    }
                });

    }

    @Override
    public void initData() {

    }

    private void weCahtPay(ParamData mPayModel) {
        boolean flag = UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.WEIXIN);
        if (flag) {
            PaymentParameterBean mPaymentParameterBean = new PaymentParameterBean();
            mPaymentParameterBean.setWxAppid(mPayModel.getAppid());
            mPaymentParameterBean.setPartnerId(mPayModel.getPartnerid());
            mPaymentParameterBean.setPrepayId(mPayModel.getPrepayid());
            mPaymentParameterBean.setNonceStr(mPayModel.getNoncestr());
            mPaymentParameterBean.setTimeStamp(mPayModel.getTimestamp());
            mPaymentParameterBean.setPackageValue(mPayModel.getPackageX());
            mPaymentParameterBean.setSign(mPayModel.getSign());
            paymentPresenter.doWXPay(mPaymentParameterBean);
        } else {
            ToastUtils.showShort( "您没有安装微信客户端!");
        }
    }

    private void appPayZfb(String data) {
        PaymentParameterBean mPaymentParameterBean1 = new PaymentParameterBean();
        mPaymentParameterBean1.setOrderInfo(data);
        paymentPresenter.doAliPay(mPaymentParameterBean1);
    }


    @Override
    public void onWXPaySuccess() {

    }

    @Override
    public void onAliPaySuccess() {
        ToastUtils.showShort( "支付成功!");
    }

    @Override
    public void onWxPayFailure() {

    }

    @Override
    public void onAliPayFailure() {
        ToastUtils.showShort( "支付未成功!");
        paymentResultCallback(false);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }
    /*支付结果的回调*/
    private void paymentResultCallback(boolean flag) {
//        ActivityCollector.getAppManager().finishotherActivity(MainActivity.activity, ShoppingPaymentActivity.this);
//        if (flag) {
//            IntentUtils.getInstence().intent(ShoppingPaymentActivity.this, MyOrderActivity.class, "position", 0);
//        } else {
//            IntentUtils.getInstence().intent(ShoppingPaymentActivity.this, MyOrderActivity.class, "position", 1);
//        }
        finish();
    }

}
