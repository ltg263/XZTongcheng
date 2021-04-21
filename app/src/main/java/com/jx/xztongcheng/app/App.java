package com.jx.xztongcheng.app;

import android.app.Application;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.jx.xztongcheng.bean.clazz.UserInfo;
import com.jx.xztongcheng.utils.ConstValues;

import cn.jpush.android.api.JPushInterface;


/**
 * Create by Sxl on 2020/10/15.
 */
public class App extends Application {

    private static App INSTANCE;
    public static double lat;
    public static double lon;
    private UserInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        if (INSTANCE == null)
            INSTANCE = this;
        Utils.init(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static App getInstance() {
        return INSTANCE;
    }

    public UserInfo getUserInfo() {
        try {
            userInfo = new Gson().fromJson(SPUtils.getInstance().getString(ConstValues.USER_INFO), UserInfo.class);
        } catch (Exception e) {
            userInfo = new UserInfo();
        }
        if (userInfo == null) {
            userInfo = new UserInfo();
        }
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        SPUtils.getInstance().put(ConstValues.USER_INFO, userInfo);
    }

    public String getTokenId() {
        return SPUtils.getInstance().getString(ConstValues.TOKENID);
    }

    public static boolean isLogin() {
        return !StringUtils.isEmpty(App.getInstance().getTokenId());
    }

    public static void clearLogin() {
        SPUtils.getInstance().put(ConstValues.USER_INFO, "");
        SPUtils.getInstance().put(ConstValues.TOKENID, "");
    }
}
