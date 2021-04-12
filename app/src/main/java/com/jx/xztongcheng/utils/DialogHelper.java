package com.jx.xztongcheng.utils;

import android.app.Activity;
import android.app.Dialog;
import androidx.lifecycle.LifecycleOwner;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.bean.event.VersionBean;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.ui.activity.BindingSiteActivity;
import com.jx.xztongcheng.widget.FullScreenDialog;

import constant.UiType;
import listener.Md5CheckResultListener;
import listener.UpdateDownloadListener;
import model.UiConfig;
import model.UpdateConfig;
import update.UpdateAppUtils;

/**
 * Created by ShiXL on 2018/2/9.
 */

public class DialogHelper {

    public static void showRationaleDialog(final PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) return;
        new AlertDialog.Builder(topActivity)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage("请授权此权限，否则无法正常使用功能")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(true);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        shouldRequest.again(false);
                    }
                })
                .setCancelable(false)
                .create()
                .show();

    }

    public static void showOpenAppSettingDialog() {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) return;
        new AlertDialog.Builder(topActivity)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage("前往系统设置打开APP权限")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtils.launchAppDetailsSettings();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    public static boolean isBindingStatus(Context mContext){
        if(App.getInstance().getUserInfo().getBindingStatus()==0){
            DialogUtils.cancelDialog(mContext, "绑定站点", "请先绑定站点"
                    , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityUtils.startActivity(BindingSiteActivity.class);
                        }
                    }).show();
            return false;
        }
        if(App.getInstance().getUserInfo().getBindingStatus()==1){
            ToastUtils.showShort("正在站点申请中，请等待审核通过");
            return false;
        }
        if(App.getInstance().getUserInfo().getBindingStatus()==3){
            ToastUtils.showShort("站点失败，请联系站点");
            return false;
        }
        return true;
    }

    public static boolean isAuthStatus(Context mContext){
        if(!isBindingStatus(mContext)){
            return false;
        }
        if (App.getInstance().getUserInfo().getAuthStatus() == 0 || App.getInstance().getUserInfo().getAuthStatus() == 3) {
            Dialog dialog = new FullScreenDialog(mContext);
            dialog.show();
            return false;
        }
        if (App.getInstance().getUserInfo().getAuthStatus() == 1) {
            ToastUtils.showShort("正在认证中，请等待认证通过");
            return false;
        }
        return true;
    }


    public interface UploadFileInterface{
        void succeed(String path);
        void failure();
    }
    public static void getVersionUpdating(Activity mContext,UploadFileInterface fileInterface) {
        RetrofitManager.build().create(UserService.class)
                .getVersionUpdating(1)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.<BaseResponse<VersionBean>>bindLifecycle((LifecycleOwner) mContext))
                .subscribe(new BaseObserver<VersionBean>() {
                    @Override
                    public void onSuccess(VersionBean t) {
                        goUpdating(mContext,t,fileInterface);
                    }

                    @Override
                    public void onFail(int code, String error) {

                    }
                });

    }

    public static void goUpdating(Context mContext, VersionBean data, UploadFileInterface fileInterface) {
        if(data.getVersion().equals(getVersionName(mContext))){
            fileInterface.failure();
            return;
        }
        UpdateAppUtils.init(mContext);
        UpdateConfig updateConfig = new UpdateConfig();
        updateConfig.setCheckWifi(true);
        updateConfig.setNeedCheckMd5(false);
        updateConfig.setNotifyImgRes(R.mipmap.ic_app_logo);
        UiConfig uiConfig = new UiConfig();
        uiConfig.setUiType(UiType.PLENTIFUL);
        uiConfig.setUpdateLogoImgRes(R.mipmap.ic_app_logo);
        uiConfig.setUpdateBtnBgRes(R.drawable.shape_radius_theme_full_8dp);
        UpdateAppUtils
                .getInstance()
                .apkUrl(data.getFileUrl())
                .updateTitle("发现新版本:V"+data.getVersion())
                .updateContent("更新内容:"+data.getContent())
                .uiConfig(uiConfig)
                .updateConfig(updateConfig)
                .setMd5CheckResultListener(new Md5CheckResultListener() {
                    @Override
                    public void onResult(boolean result) {

                    }
                })
                .setUpdateDownloadListener(new UpdateDownloadListener() {
                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onDownload(int progress) {

                    }

                    @Override
                    public void onFinish() {

                    }

                })
                .update();
    }

    /**
     * 获取版本名称
     *
     * @param context 上下文
     *
     * @return 版本名称
     */
    public static String getVersionName(Context context) {

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

}
