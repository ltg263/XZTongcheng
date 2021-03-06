package com.jx.xztongcheng.ui.fragment;


import android.content.Intent;
import androidx.appcompat.widget.Toolbar;
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
import com.jx.xztongcheng.utils.DialogHelper;
import com.jx.xztongcheng.utils.GlideImageLoader;
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
        toolbarTitle.setText("????????????");

        loadBanner();
    }

    private void loadBanner() {
        RetrofitManager.build().create(UserService.class)
                .getBanner(1)
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
//        RetrofitManager.build().create(UserService.class)
//                .getBannerGao("77f0363d7e97bb832d81e108ce8a776e","?????????????????????????????????6???")
//                .compose(RxScheduler.observeOnMainThread())
//                .as(RxScheduler.<BaseResponse<EmptyResponse>>bindLifecycle(this))
//                .subscribe(new BaseObserver<EmptyResponse>() {
//                    @Override
//                    public void onSuccess(EmptyResponse emptyResponse) {
//
//                    }
//
//                    @Override
//                    public void onFail(int code, String error) {
//
//                    }
//                });
    }

    private void setBanner() {

        bannerImg1.clear();
        for (BannerListResponse.ListBean data : bannerReponse.getList()) {
            bannerImg1.add(data.getImage());
        }

        //???????????????????????????????????????????????????????????????????????????
        bannerHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //????????????????????????????????????????????????
        bannerHome.setImageLoader(new GlideImageLoader());
        //????????????????????????????????????
        bannerHome.setImages(bannerImg1);
        //?????????????????????????????????????????????????????????????????????????????????????????????
        bannerHome.setBannerAnimation(Transformer.Default);
        //??????????????????????????????
//        banner_theme.setBannerTitles(themeTitles);
        //????????????????????????
        bannerHome.setDelayTime(3000);
        //???????????????????????????????????????????????????
        bannerHome.isAutoPlay(true);
        //???????????????????????????????????????????????????
        bannerHome.setIndicatorGravity(BannerConfig.CENTER)
                //???????????????????????????????????????????????????????????????????????????????????????????????????
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
                //????????????????????????????????????????????????
                .start();
    }

    @Override
    protected void initData() {

    }

    //??????AMapLocationClient?????????
    public AMapLocationClient mLocationClient = null;
    //???????????????????????????
    public AMapLocationListener mLocationListener = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                amapLocation.setCityCode(String.valueOf(Integer.parseInt(amapLocation.getAdCode()) / 100 * 100));
                aMapLocation = amapLocation;
                if (amapLocation.getErrorCode() == 0) {
                    //??????????????????amapLocation?????????????????????
                    double locationType = amapLocation.getLocationType();//??????????????????????????????????????????????????????????????????????????????
                    App.lat = amapLocation.getLatitude();
                    App.lon = amapLocation.getLongitude();
                } else {
                    //???????????????????????????ErrCode????????????????????????????????????????????????errInfo???????????????????????????????????????
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
                mLocationClient.stopLocation();
            }
        }
    };
    //??????AMapLocationClientOption??????
    public AMapLocationClientOption mLocationOption = null;


    @OnClick({R.id.ll_kjgl1, R.id.ll_zpjgl2, R.id.ll_jjgl3,R.id.tv_md,R.id.tv_rk,R.id.tv_ck,R.id.tv_xd})
    public void onViewClicked(View view) {
        if (!App.isLogin()) {
            ActivityUtils.startActivity(LoginActivity.class);
            return;
        }

        if(!DialogHelper.isAuthStatus(getActivity())){
            Log.w(this.toString(),"?????????????????????");
            return;
        }
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
