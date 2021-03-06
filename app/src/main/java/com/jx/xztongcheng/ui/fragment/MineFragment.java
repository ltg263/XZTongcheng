package com.jx.xztongcheng.ui.fragment;


import android.content.Intent;
import androidx.cardview.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.base.BaseFragment;
import com.jx.xztongcheng.bean.clazz.UserInfo;
import com.jx.xztongcheng.bean.request.RegisterBean;
import com.jx.xztongcheng.bean.response.BannerListResponse;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.ui.activity.ExpressManageActivity;
import com.jx.xztongcheng.ui.activity.LoginActivity;
import com.jx.xztongcheng.ui.activity.MyKhListActivity;
import com.jx.xztongcheng.ui.activity.MyQrCodeActivity;
import com.jx.xztongcheng.ui.activity.MyWalletActivity;
import com.jx.xztongcheng.ui.activity.PayBZJActivity;
import com.jx.xztongcheng.ui.activity.SettingActivity;
import com.jx.xztongcheng.ui.activity.StasisPartActivity;
import com.jx.xztongcheng.ui.activity.WebViewWithBackActivity;
import com.jx.xztongcheng.utils.DialogHelper;
import com.jx.xztongcheng.utils.GlideImageLoader;
import com.jx.xztongcheng.utils.PermissionHelper;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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

        //???????????????????????????????????????????????????????????????????????????
        bannerMine.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //????????????????????????????????????????????????
        bannerMine.setImageLoader(new GlideImageLoader());
        //????????????????????????????????????
        bannerMine.setImages(bannerImg1);
        //?????????????????????????????????????????????????????????????????????????????????????????????
        bannerMine.setBannerAnimation(Transformer.Default);
        //????????????????????????
        bannerMine.setDelayTime(3000);
        //???????????????????????????????????????????????????
        bannerMine.isAutoPlay(true);
        //???????????????????????????????????????????????????
        bannerMine.setIndicatorGravity(BannerConfig.CENTER)
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
        refreshData();
    }
    @OnClick({R.id.ll_jrsj, R.id.ll_jrsr, R.id.ll_khs, R.id.ll_wallet, R.id.ll_setting, R.id.rl_info, R.id.ll_kjgl, R.id.ll_zpj, R.id.iv_code, R.id.ll_code, R.id.ll_qd, R.id.ll_bzj})
    public void onViewClicked(View view) {
        if( view.getId()!=R.id.ll_setting &&!DialogHelper.isAuthStatus(getActivity())){
            Log.w(this.toString(),"?????????????????????");
            return;
        }
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
                selectImage();
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
                            if(!TextUtils.isEmpty(userInfo.getWebsiteName())){
                                tv_dh.setText(userInfo.getWebsiteName()+"-"+userInfo.getNickname());
                            }
                            GlideImageLoader.loadImageViewWithCirclr(getActivity(), userInfo.getAvatar(), ivHead);
                        }
                    });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private void selectImage() {
        PermissionHelper.requestCAMERA(() -> {
            PictureSelector.create(getActivity())
                    .openGallery(PictureMimeType.ofImage())//??????.PictureMimeType.ofAll()?????????.ofImage()?????????.ofVideo()?????????.ofAudio
                    .maxSelectNum(1)// ???????????????????????? int
                    .minSelectNum(1)// ?????????????????? int
                    .imageSpanCount(4)// ?????????????????? int
                    .selectionMode(PictureConfig.SINGLE)// ?????? or ?????? PictureConfig.MULTIPLE or PictureConfig.SINGLE
                    .previewImage(true)// ????????????????????? true or false
                    .isCamera(true)// ???????????????????????? true or false
                    .enableCrop(true)// ???????????? true or false
                    .compress(true)// ???????????? true or false
                    .withAspectRatio(16, 10)// int ???????????? ???16:9 3:2 3:4 1:1 ????????????
                    .scaleEnabled(true)// ????????????????????????????????? true or false
                    .hideBottomControls(true)// ????????????uCrop??????????????????????????? true or false
                    .isGif(false)// ????????????gif?????? true or false
                    .freeStyleCropEnabled(true)// ???????????????????????? true or false
                    .forResult(PictureConfig.CHOOSE_REQUEST);//????????????onActivityResult code
        });
    }
    public void updateAvatar(Intent data){
        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
        uploadImage(selectList.get(0).getCompressPath());
    }
    private void uploadImage(final String imgPath) {
        File file = new File(imgPath);//????????????????????????????????????????????????sdcdrd????????????????????????
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Map<String, RequestBody> map = new HashMap<>();
        map.put("prefix", RequestBody.create(null, "courier_auth"));
        showLoading();
        ToastUtils.showShort("???????????????...");
        RetrofitManager.build().create(UserService.class).uploadImage(map, body)
                .compose(RxScheduler.<BaseResponse<String>>observeOnMainThread())
                .as(AutoDispose.<BaseResponse<String>>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new BaseObserver<String>() {

                               @Override
                               public void onSuccess(String s) {
                                   hideLoading();
//                                   imgUrl = s;
                                   updateAvatar(s);
                               }

                               @Override
                               public void onFail(int code, String msg) {
//                                super.onFail(code, msg);
                                   hideLoading();
                                   ToastUtils.showShort("??????????????????");
                               }
                           }
                );
    }

    private void updateAvatar(String s) {
        RegisterBean request = new RegisterBean();
        request.setAvatar(s);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(request));
        RetrofitManager.build().create(UserService.class)
                .updateAvatar(requestBody)
                .compose(RxScheduler.<BaseResponse<EmptyResponse>>observeOnMainThread())
                .as(AutoDispose.<BaseResponse<EmptyResponse>>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new BaseObserver<EmptyResponse>() {

                               @Override
                               public void onSuccess(EmptyResponse s) {
                                   refreshData();
                                   ToastUtils.showShort("??????????????????");
                               }

                               @Override
                               public void onFail(int code, String msg) {
//                                super.onFail(code, msg);
                                   hideLoading();
                                   ToastUtils.showShort("??????????????????");
                               }
                           }
                );
    }


}
