package com.jx.xztongcheng.ui.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.base.BaseFragment;
import com.jx.xztongcheng.bean.response.BannerListResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.ui.activity.ExpressManageActivity;
import com.jx.xztongcheng.ui.activity.LoginActivity;
import com.jx.xztongcheng.ui.activity.ToolCkListActivity;
import com.jx.xztongcheng.ui.activity.ToolMdListActivity;
import com.jx.xztongcheng.ui.activity.ToolRkListActivity;
import com.jx.xztongcheng.ui.activity.ToolXdListActivity;
import com.jx.xztongcheng.ui.activity.WebViewWithBackActivity;
import com.jx.xztongcheng.utils.GlideImageLoader;
import com.jx.xztongcheng.widget.FullScreenDialog;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.banner_home)
    Banner bannerHome;
    @BindView(R.id.iv_kjgl1)
    ImageView ivKjgl1;
    @BindView(R.id.tv_kjgl1)
    TextView tvKjgl1;
    @BindView(R.id.ll_kjgl1)
    LinearLayout llKjgl1;
    @BindView(R.id.iv_zpjgl2)
    ImageView ivZpjgl2;
    @BindView(R.id.tv_zpjgl2)
    TextView tvZpjgl2;
    @BindView(R.id.ll_zpjgl2)
    LinearLayout llZpjgl2;
    @BindView(R.id.iv_jjgl3)
    ImageView ivJjgl3;
    @BindView(R.id.tv_jjgl3)
    TextView tvJjgl3;
    @BindView(R.id.ll_jjgl3)
    LinearLayout llJjgl3;
    AMapLocation aMapLocation = null;

    private Intent intent;
    private BannerListResponse bannerReponse;
    private List<String> bannerImg1 = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        toolbarTitle.setText("享指同城");

        loadBanner();
    }

    private void loadBanner() {
        RetrofitManager.build().create(UserService.class)
                .getBanner(5)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.<BaseResponse<BannerListResponse>>bindLifecycle(this))
                .subscribe(new BaseObserver<BannerListResponse>() {
                    @Override
                    public void onSuccess(BannerListResponse emptyResponse) {
                        bannerReponse = emptyResponse;
                        setBanner();

                    }

                    @Override
                    public void onFail(int code, String error) {

                    }
                });

    }

    private void setBanner() {

        bannerImg1.clear();
        for (BannerListResponse.ListBean data : bannerReponse.getList()) {
            bannerImg1.add(data.getImage());
        }

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        bannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        bannerHome.setImageLoader(new GlideImageLoader());
        //设置图片网址或地址的集合
        bannerHome.setImages(bannerImg1);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        bannerHome.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
//        banner_theme.setBannerTitles(themeTitles);
        //设置轮播间隔时间
        bannerHome.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        bannerHome.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        bannerHome.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        if (bannerReponse.getList().get(position).getType() == 1) {
                            intent = new Intent(getActivity(), WebViewWithBackActivity.class);
                            intent.putExtra("url", bannerReponse.getList().get(position).getMessage());
                            startActivity(intent);
                        }
                    }
                })
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    @Override
    protected void initData() {

    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                amapLocation.setCityCode(String.valueOf(Integer.parseInt(amapLocation.getAdCode()) / 100 * 100));
                aMapLocation = amapLocation;
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    double locationType = amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    App.lat = amapLocation.getLatitude();
                    App.lon = amapLocation.getLongitude();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
                mLocationClient.stopLocation();
            }
        }
    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;


    @OnClick({R.id.ll_kjgl1, R.id.ll_zpjgl2, R.id.ll_jjgl3,R.id.tv_md,R.id.tv_rk,R.id.tv_ck,R.id.tv_xd})
    public void onViewClicked(View view) {
        if (!App.isLogin()) {
            ActivityUtils.startActivity(LoginActivity.class);
            return;
        }
        if (App.getInstance().getUserInfo().getAuthStatus() == 0 || App.getInstance().getUserInfo().getAuthStatus() == 3) {
            showVerifyDialog();
            return;
        } else if (App.getInstance().getUserInfo().getAuthStatus() == 1) {
            ToastUtils.showShort("正在认证中，请等待认证通过");
            return;
        } else {
            switch (view.getId()) {
                case R.id.ll_kjgl1:
                    intent = new Intent(getActivity(), ExpressManageActivity.class);
                    intent.putExtra("expressType", 1);
                    startActivity(intent);
                    break;
                case R.id.ll_zpjgl2:
                    intent = new Intent(getActivity(), ExpressManageActivity.class);
                    intent.putExtra("expressType", 2);
                    startActivity(intent);
                    break;
                case R.id.ll_jjgl3:
                    intent = new Intent(getActivity(), ExpressManageActivity.class);
                    intent.putExtra("expressType", 1);
                    intent.putExtra("fastStatus", 1);
                    startActivity(intent);
                    break;
                case R.id.tv_md:
                    intent = new Intent(getActivity(), ToolMdListActivity.class);
                    intent.putExtra("expressType", 1);
                    intent.putExtra("fastStatus", 1);
                    startActivity(intent);
                    break;
                case R.id.tv_rk:
                    intent = new Intent(getActivity(), ToolRkListActivity.class);
                    intent.putExtra("expressType", 1);
                    intent.putExtra("fastStatus", 1);
                    startActivity(intent);
                    break;
                case R.id.tv_ck:
                    intent = new Intent(getActivity(), ToolCkListActivity.class);
                    intent.putExtra("expressType", 1);
                    intent.putExtra("fastStatus", 1);
                    startActivity(intent);
                    break;
                case R.id.tv_xd:
                    intent = new Intent(getActivity(), ToolXdListActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    public void showVerifyDialog() {
        Dialog dialog = new FullScreenDialog(getContext());
        dialog.show();
    }
}
