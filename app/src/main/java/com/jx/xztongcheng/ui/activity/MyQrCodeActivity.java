package com.jx.xztongcheng.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.ImageView;

import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class MyQrCodeActivity extends BaseActivity {


    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @Override
    public int intiLayout() {
        return R.layout.activity_my_qr_code;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "专属码", true);
    }

    @Override
    public void initData() {

        RetrofitManager.build().create(OrderService.class)
                .getQrCode(1, null)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(this))
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();//得到图片的流
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        ivCode.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
