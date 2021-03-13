package com.jx.xztongcheng.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
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
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class PayForImageActivity extends BaseActivity {


    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_weight_price)
    TextView tvWeightPrice;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;

    private int orderId;
    private double totalPrice;
    String imgUrl = "";

    @Override
    public int intiLayout() {
        return R.layout.activity_pay_for_image;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "详情订单", true);
        orderId = getIntent().getIntExtra("orderId", 0);
        totalPrice = getIntent().getDoubleExtra("totalPrice", 0);

        tvContent.setText("支付" + totalPrice + "元运费");

    }

    @Override
    public void initData() {
        RetrofitManager.build().create(OrderService.class)
                .getQrCode(2, orderId)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(this))
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();//得到图片的流
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        ivImage.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @OnClick({R.id.finishGet, R.id.iv_camera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finishGet:
                if (TextUtils.isEmpty(imgUrl)) {
                    ToastUtils.showShort("请拍摄照片或等待照片上传成功");
                    return;
                }
                View view1 = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null);
                EditText editCode = view1.findViewById(R.id.edit_code);
                new AlertDialog.Builder(this)
                        .setTitle("请输入取件码")
                        .setView(view1)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RetrofitManager.build().create(OrderService.class)
                                        .pickupOrder(orderId, editCode.getText().toString(), imgUrl)
                                        .compose(RxScheduler.observeOnMainThread())
                                        .as(RxScheduler.bindLifecycle(PayForImageActivity.this))
                                        .subscribe(new BaseObserver<EmptyResponse>() {
                                            @Override
                                            public void onSuccess(EmptyResponse emptyResponse) {
                                                ToastUtils.showShort("取件成功");
                                                finish();
                                            }
                                        });
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setCancelable(false)
                        .create()
                        .show();
                break;
            case R.id.iv_camera:
                selectImage();
                break;
        }
    }


    private void selectImage() {
        PermissionHelper.requestCAMERA(() -> {
            PictureSelector.create(PayForImageActivity.this)
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
                        GlideImageLoader.loadImageAndDefault(this, selectList.get(0).getCompressPath(), ivCamera);
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
        ToastUtils.showShort("图片上传中...");
        RetrofitManager.build().create(UserService.class).uploadImage(map, body)
                .compose(RxScheduler.<BaseResponse<String>>observeOnMainThread())
                .as(AutoDispose.<BaseResponse<String>>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new BaseObserver<String>() {

                               @Override
                               public void onSuccess(String s) {
                                   hideLoading();
                                   imgUrl = s;
                                   ToastUtils.showShort("图片上传成功");
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


}
