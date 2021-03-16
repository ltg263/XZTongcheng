package com.jx.xztongcheng.net;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.ui.activity.LoginActivity;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by Hasee on2019/8/30
 * Des: 基于以上响应实体类，我们创建RxJava订阅者的封装基类:BaseObserver
 * 这里主要处理了返回的数据,错误异常的统一处理,以及Dialog加载框的处理.
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {


    public BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        /**
         * 000000 成功
         * 其他 错误
         */
        String code = tBaseResponse.getCode();

        if (code.equals("000000")) {
            onSuccess(tBaseResponse.getData());
        } else {
            onFail(-1, tBaseResponse.getSub_mesg() + "");
            if (tBaseResponse.getSub_mesg().equals("无效token")) {
                App.clearLogin();
                ActivityUtils.startActivity(LoginActivity.class);
            }
            ToastUtils.showShort(tBaseResponse.getSub_mesg());
        }

    }


    @Override
    public void onError(Throwable e) {
        String errorMsg;
        if (e instanceof IOException) {
            /** 没有网络 */
            errorMsg = "Please check your network status";
        } else if (e instanceof HttpException) {
            /** 网络异常，http 请求失败，即 http 状态码不在 [200, 300) 之间, such as: "server internal error". */
            errorMsg = ((HttpException) e).response().message();
        } else if (e instanceof Exception) {
            /** 网络正常，http 请求成功，服务器返回逻辑错误 */
            errorMsg = e.getMessage();
        } else {
            /** 其他未知错误 */
            errorMsg = !TextUtils.isEmpty(e.getMessage()) ? e.getMessage() : "unknown error";
        }
        e.printStackTrace();
        onFail(400, e.getMessage() + errorMsg);
        Log.e("net_error", "onError: " + e.getMessage());
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(T t);

    public void onFail(int code, String msg) {
        ToastUtils.showShort(msg);
    }

}
