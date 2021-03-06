package com.jx.xztongcheng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.request.SaveAuthRequest;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.utils.GlideImageLoader;
import com.jx.xztongcheng.utils.PermissionHelper;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class NameAuthenticationActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @BindView(R.id.iv_card1)
    ImageView ivCard1;
    @BindView(R.id.iv_card2)
    ImageView ivCard2;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_code)
    EditText etCode;
    private int img = 1;
    String icCardUrl1 = "";
    String icCardUrl2 = "";

    @Override
    public int intiLayout() {
        return R.layout.activity_name_authentication;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "????????????", true);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ll_sfzzm, R.id.ll_sfzfm, R.id.tv_btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sfzzm:
                img = 1;
                selectImage();
                break;
            case R.id.ll_sfzfm:
                if (StringUtils.isEmpty(icCardUrl1)) {
                    ToastUtils.showShort("???????????????????????????");
                    return;
                }
                img = 2;
                selectImage();
                break;
            case R.id.tv_btn2:
                String name = etName.getText().toString().trim();
                String code = etCode.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(code)
                        && !TextUtils.isEmpty(icCardUrl1) && !TextUtils.isEmpty(icCardUrl2)) {
                    SaveAuthRequest request = new SaveAuthRequest();
                    SaveAuthRequest.CourierAuthInfoFormBean bean = new SaveAuthRequest.CourierAuthInfoFormBean();
                    bean.setIdcard(code);
                    bean.setName(name);
                    bean.setIdCardUrl(icCardUrl1);
                    bean.setIdCardUrlBack(icCardUrl2);
                    request.setCourierAuthInfoForm(bean);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(request));

                    RetrofitManager.build().create(UserService.class)
                            .saveAuth(requestBody)
                            .compose(RxScheduler.observeOnMainThread())
                            .as(RxScheduler.<BaseResponse<EmptyResponse>>bindLifecycle(this))
                            .subscribe(new BaseObserver<EmptyResponse>() {
                                @Override
                                public void onSuccess(EmptyResponse emptyResponse) {
                                    ToastUtils.showShort("????????????");
                                    finish();
                                }

                                @Override
                                public void onFail(int code, String error) {

                                }
                            });
                } else {
                    ToastUtils.showShort("????????????????????????");
                }
                break;
        }
    }

    private void selectImage() {

        PermissionHelper.requestCAMERA(() -> {
            PictureSelector.create(NameAuthenticationActivity.this)
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case PictureConfig.CHOOSE_REQUEST:
                        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                        uploadImage(selectList.get(0).getCompressPath());
                        switch (img) {
                            case 1:
                                GlideImageLoader.loadImageAndDefault(this, selectList.get(0).getCompressPath(), ivCard1);
                                break;
                            case 2:
                                GlideImageLoader.loadImageAndDefault(this, selectList.get(0).getCompressPath(), ivCard2);
                                break;
                        }
                        break;
                    default:
                }
                break;
        }
    }


    private void uploadImage(final String imgPath) {
        File file = new File(imgPath);//????????????????????????????????????????????????sdcdrd????????????????????????
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Map<String, RequestBody> map = new HashMap<>();
        map.put("prefix", RequestBody.create(null, "courier_auth"));
        showLoading();
        RetrofitManager.build().create(UserService.class).uploadImage(map, body)
                .compose(RxScheduler.<BaseResponse<String>>observeOnMainThread())
                .as(AutoDispose.<BaseResponse<String>>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new BaseObserver<String>() {

                               @Override
                               public void onSuccess(String s) {
                                   hideLoading();
                                   switch (img) {
                                       case 1:
                                           icCardUrl1 = s;
                                           break;
                                       case 2:
                                           icCardUrl2 = s;
                                           break;
                                   }
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
