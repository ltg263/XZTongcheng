package com.jx.xztongcheng.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.base.BaseFragment;
import com.jx.xztongcheng.bean.clazz.UserInfo;
import com.jx.xztongcheng.bean.response.BannerListResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.ui.activity.ExpressManageActivity;
import com.jx.xztongcheng.ui.activity.LoginActivity;
import com.jx.xztongcheng.ui.activity.MyInfoActivity;
import com.jx.xztongcheng.ui.activity.MyKhListActivity;
import com.jx.xztongcheng.ui.activity.MyQrCodeActivity;
import com.jx.xztongcheng.ui.activity.MyWalletActivity;
import com.jx.xztongcheng.ui.activity.PayBZJActivity;
import com.jx.xztongcheng.ui.activity.SettingActivity;
import com.jx.xztongcheng.ui.activity.StasisPartActivity;
import com.jx.xztongcheng.ui.activity.WebViewWithBackActivity;
import com.jx.xztongcheng.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends BaseFragment {

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.banner_mine)
    Banner bannerMine;
    @BindView(R.id.tv_jrsj)
    TextView tvJrsj;
    @BindView(R.id.tv_jrsk)
    TextView tvJrsk;
    @BindView(R.id.tv_dh)
    TextView tv_dh;
    @BindView(R.id.tv_khs)
    TextView tvKhs;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.cv_banner)
    CardView cvBanner;
    Unbinder unbinder;

    private BannerListResponse bannerReponse;
    private List<String> bannerImg1 = new ArrayList<>();

    private Intent intent;

    public MineFragment() {
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        loadBanner();
    }

    private void loadBanner() {
        RetrofitManager.build().create(UserService.class)
                .getBanner(6)
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
        if (bannerImg1.size() == 0) {
            cvBanner.setVisibility(View.GONE);
        } else {
            cvBanner.setVisibility(View.VISIBLE);
        }

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        bannerMine.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        bannerMine.setImageLoader(new GlideImageLoader());
        //设置图片网址或地址的集合
        bannerMine.setImages(bannerImg1);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        bannerMine.setBannerAnimation(Transformer.Default);
        //设置轮播间隔时间
        bannerMine.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        bannerMine.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        bannerMine.setIndicatorGravity(BannerConfig.CENTER)
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
        refreshData();
    }
    @OnClick({R.id.ll_jrsj, R.id.ll_jrsr, R.id.ll_khs, R.id.ll_wallet, R.id.ll_setting, R.id.rl_info, R.id.ll_kjgl, R.id.ll_zpj, R.id.iv_code, R.id.ll_code, R.id.ll_qd, R.id.ll_bzj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_jrsj:
                intent = new Intent(getActivity(), StasisPartActivity.class);
                intent.putExtra("index", 0);
                startActivity(intent);
                break;
            case R.id.ll_jrsr:
                intent = new Intent(getActivity(), StasisPartActivity.class);
                intent.putExtra("index", 1);
                startActivity(intent);
                break;
            case R.id.ll_khs:
//                intent = new Intent(getActivity(), StasisPartActivity.class);
//                intent.putExtra("index", 2);
//                startActivity(intent);
                ActivityUtils.startActivity(MyKhListActivity.class);
                break;
            case R.id.ll_code:
            case R.id.iv_code:
                ActivityUtils.startActivity(MyQrCodeActivity.class);
                break;
            case R.id.ll_wallet:
                ActivityUtils.startActivity(MyWalletActivity.class);
                break;
            case R.id.ll_setting:
                ActivityUtils.startActivity(SettingActivity.class);
                break;
            case R.id.rl_info:
//                ActivityUtils.startActivity(MyInfoActivity.class);
                break;
            case R.id.ll_qd:
            case R.id.ll_kjgl:
                intent = new Intent(getActivity(), ExpressManageActivity.class);
                intent.putExtra("expressType", 1);
                startActivity(intent);
                break;
            case R.id.ll_zpj:
                intent = new Intent(getActivity(), ExpressManageActivity.class);
                intent.putExtra("expressType", 2);
                startActivity(intent);
                break;
            case R.id.ll_bzj:
                intent = new Intent(getActivity(), PayBZJActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void refreshData() {
        if (StringUtils.isEmpty(App.getInstance().getTokenId())) {
            ActivityUtils.startActivity(LoginActivity.class);
        } else {
            RetrofitManager.build().create(UserService.class).getUserInfo()
                    .compose(RxScheduler.<BaseResponse<UserInfo>>observeOnMainThread())
                    .subscribe(new BaseObserver<UserInfo>() {
                        @Override
                        public void onSuccess(UserInfo userInfo) {
                            App.getInstance().setUserInfo(new Gson().toJson(userInfo));
                            if (tvName != null) {
//                                tvName.setText(userInfo.getNickname());
                                tvName.setText(userInfo.getUsername());
                            } else {
                                return;
                            }
                            tvBalance.setText(TextUtils.isEmpty(userInfo.getBalance()) ? "0" : userInfo.getBalance());
                            tvJrsj.setText(TextUtils.isEmpty(userInfo.getTodayCount()) ? "0" : userInfo.getTodayCount());
                            tvJrsk.setText(TextUtils.isEmpty(userInfo.getTodayAmont()) ? "0" : userInfo.getTodayAmont());
                            tvKhs.setText(TextUtils.isEmpty(userInfo.getHuamanCount()) ? "0" : userInfo.getHuamanCount());
                        }
                    });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }
}
