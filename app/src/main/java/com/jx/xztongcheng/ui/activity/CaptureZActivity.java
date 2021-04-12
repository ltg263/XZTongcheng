package com.jx.xztongcheng.ui.activity;

import com.blankj.utilcode.util.ToastUtils;
import com.google.zxing.Result;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.yzq.zxinglibrary.android.CaptureActivity;

public class CaptureZActivity extends CaptureActivity {
    @Override
    public void handleDecode(Result rawResult) {
        RetrofitManager.build().create(OrderService.class).coreStorage(rawResult.getText(), null)
                .compose(RxScheduler.<BaseResponse<Object>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<Object>>bindLifecycle(this))
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        onResume();
                        ToastUtils.showShort("入库成功:" + rawResult.getText());
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                    }
                });
    }
}
