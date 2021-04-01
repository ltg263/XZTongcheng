package com.jx.xztongcheng.wxapi;

import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.umeng.socialize.weixin.view.WXCallbackActivity;


public class WXEntryActivity extends WXCallbackActivity {
    @Override
    public void onResp(BaseResp resp) {
        super.onResp(resp);
        String code = ((SendAuth.Resp) resp).code;
//        TextUtil.logOut("code:"+code);
//        login(code);

    }
//    public static void login(String code) {
//        RetrofitUtil.getInstance().apiService()
//                .register(code)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<Result>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Result result) {
//                        if (result.getStatus() == 0) {
//                        }else{
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }

}