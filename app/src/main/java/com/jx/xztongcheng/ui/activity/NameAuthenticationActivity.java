package com.jx.xztongcheng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
                    ToastUtils.showShort("请先上传身份证正面");
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
                                    ToastUtils.showShort("提交成功");
                                    finish();
                                }

                                @Override
                                public void onFail(int code, String error) {

                                }
                            });
                } else {
                    ToastUtils.showShort("请先填写完整信息");
                }
                break;
        }
    }

    private void selectImage() {

        PermissionHelper.requestCAMERA(() -> {
            PictureSelector.create(NameAuthenticationActivity.this)
                    .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio
                    .maxSelectNum(1)// 最大图片选择数量 int
                    .minSelectNum(1)// 最小选择数量 int
                    .imageSpanCount(4)// 每行显示个数 int
                    .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                    .previewImage(true)// 是否可预览图片 true or false
                    .isCamera(true)// 是否显示拍照按钮 true or false
                    .enableCrop(true)// 是否裁剪 true or false
                    .compress(true)// 是否压缩 true or false
                    .withAspectRatio(16, 10)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                    .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                    .isGif(false)// 是否显示gif图片 true or false
                    .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                    .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
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
        File file = new File(imgPath);//访问手机端的文件资源，保证手机端sdcdrd中必须有这个文件
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
                                   ToastUtils.showShort("图片上传失败");
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
