package com.jx.xztongcheng.net;

import android.text.TextUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import retrofit2.HttpException;

public abstract class BaseSubscriber<T> implements Subscriber<BaseResponse<T>> {

    public BaseSubscriber() {
    }

    @Override
    public void onSubscribe(Subscription s) {
        //观察者设置接收事件的数量,如果不设置接收不到事件
        s.request(128);
    }

    @Override
    public void onNext(BaseResponse<T> tBaseResponse) {
        /**
         * 0 成功
         * 其他 错误
         */
        int status = tBaseResponse.getStatus();

        if (status == 0) {//请求成功
            onSuccess(tBaseResponse.getData());
        } else {//失败
            onFail(tBaseResponse.getStatus(), tBaseResponse.getSub_mesg());
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
        onFail(400, e.getMessage());

    }

    @Override
    public void onComplete() {
        // Timber.d("加载完毕！");
    }

    public abstract void onSuccess(@Nullable T t);

    public abstract void onFail(int code, String error);

}
