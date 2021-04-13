package com.jx.xztongcheng.ui.activity;

import com.blankj.utilcode.util.ToastUtils;
import com.google.zxing.Result;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.utils.LoadingDialog;
import com.yzq.zxinglibrary.android.CaptureActivity;

public class CaptureZActivity extends CaptureActivity {
    LoadingDialog mLoading;
    @Override
    public void handleDecode(Result rawResult) {
        showLoading();
        if(getIntent().getIntExtra("type",0)==1){
            RetrofitManager.build().create(OrderService.class).coreStorage(rawResult.getText(), getIntent().getStringExtra("weight"))
                    .compose(RxScheduler.<BaseResponse<Object>>observeOnMainThread())
                    .as(RxScheduler.<BaseResponse<Object>>bindLifecycle(this))
                    .subscribe(new BaseObserver() {
                        @Override
                        public void onSuccess(Object o) {
                            hideLoading();
                            onResume();
                            ToastUtils.showShort("入库成功:" + rawResult.getText());
                        }

                        @Override
                        public void onFail(int code, String msg) {
                            hideLoading();
                            onResume();
                            super.onFail(code, msg);
                        }
                    });
        }else{
            RetrofitManager.build().create(OrderService.class).outWarehouse(rawResult.getText(), getIntent().getStringExtra("websiteCarId"))
                    .compose(RxScheduler.<BaseResponse<Object>>observeOnMainThread())
                    .as(RxScheduler.<BaseResponse<Object>>bindLifecycle(this))
                    .subscribe(new BaseObserver() {
                        @Override
                        public void onSuccess(Object o) {
                            hideLoading();
                            onResume();
                            ToastUtils.showShort("出库成功:" + rawResult.getText());
                        }

                        @Override
                        public void onFail(int code, String msg) {
                            hideLoading();
                            onResume();
                            super.onFail(code, msg);
                        }
                    });
        }
    }


    public void showLoading() {
        if (mLoading != null && !mLoading.isShowing()) {
            mLoading.show();
        } else {
            mLoading = LoadingDialog.show(this, R.string.loading_text, false, null);
        }
    }



    public void hideLoading() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }

}
