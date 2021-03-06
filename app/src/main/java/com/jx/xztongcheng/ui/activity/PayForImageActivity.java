package com.jx.xztongcheng.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.request.RechargeSaveBean;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.utils.GlideImageLoader;
import com.jx.xztongcheng.utils.PermissionHelper;
import com.jx.xztongcheng.utils.PickerViewUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
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
    @BindView(R.id.tv_fkfs)
    TextView tv_fkfs;
    @BindView(R.id.iv_camera)
    ImageView ivCamera;
    @BindView(R.id.ll_rwm)
    LinearLayout ll_rwm;
    @BindView(R.id.ll_dsfy)
    LinearLayout ll_dsfy;
    @BindView(R.id.et_dsfy)
    EditText et_dsfy;

    private int orderId;
    private double totalPrice;
    String imgUrl = "";
    String payType = "CASH";
    String code = "";
    @Override
    public int intiLayout() {
        return R.layout.activity_pay_for_image;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "????????????", true);
        orderId = getIntent().getIntExtra("orderId", 0);
        code = getIntent().getStringExtra("code");
        totalPrice = getIntent().getDoubleExtra("totalPrice", 0);
        tvContent.setText("??????" + totalPrice + "?????????");
        tv_fkfs.setText("????????????"+totalPrice+"???");


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
                        InputStream inputStream = responseBody.byteStream();//??????????????????
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

    @OnClick({R.id.finishGet, R.id.iv_camera,R.id.tv_fkfs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.finishGet:
                if (TextUtils.isEmpty(imgUrl)) {
                    ToastUtils.showShort("??????????????????????????????????????????");
                    return;
                }

                if(payType.equals("COLLECTING_MONEY")){
                    if(StringUtils.isEmpty(et_dsfy.getText().toString())){
                        ToastUtils.showShort("??????????????????????????????");
                        return;
                    }
                }
                View view1 = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null);
                EditText editCode = view1.findViewById(R.id.edit_code);
                new AlertDialog.Builder(this)
                        .setTitle("??????????????????")
                        .setView(view1)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(!editCode.getText().toString().equals(code)){
                                    ToastUtils.showShort("???????????????");
                                    return;
                                }
                                if(!payType.equals("WXAPP") && !payType.equals("-1")){
                                    RechargeSaveBean mRechargeSaveBean = new RechargeSaveBean();
                                    mRechargeSaveBean.setExpressOrderId(orderId);
                                    mRechargeSaveBean.setPayType(payType);
                                    if(payType.equals("COLLECTING_MONEY")){
                                        String dsfy = et_dsfy.getText().toString();
                                        if(StringUtils.isEmpty(dsfy)){
                                            ToastUtils.showShort("??????????????????????????????");
                                            return;
                                        }
                                        mRechargeSaveBean.setMoney(dsfy);
                                    }
                                    RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(mRechargeSaveBean));
                                    RetrofitManager.build().create(OrderService.class)
                                            .orderPya(body)
                                            .compose(RxScheduler.observeOnMainThread())
                                            .as(RxScheduler.bindLifecycle(PayForImageActivity.this))
                                            .subscribe(new BaseObserver<EmptyResponse>() {
                                                @Override
                                                public void onSuccess(EmptyResponse emptyResponse) {
                                                    RetrofitManager.build().create(OrderService.class)
                                                            .pickupOrder(orderId, editCode.getText().toString(), imgUrl)
                                                            .compose(RxScheduler.observeOnMainThread())
                                                            .as(RxScheduler.bindLifecycle(PayForImageActivity.this))
                                                            .subscribe(new BaseObserver<EmptyResponse>() {
                                                                @Override
                                                                public void onSuccess(EmptyResponse emptyResponse) {
                                                                    ToastUtils.showShort("????????????");
                                                                    finish();
                                                                }

                                                                @Override
                                                                public void onFail(int code, String msg) {
                                                                    super.onFail(code, msg);
                                                                    ToastUtils.showShort("????????????");
                                                                    finish();
                                                                }
                                                            });
                                                }
                                            });
                                    return;
                                }
                                RetrofitManager.build().create(OrderService.class)
                                        .pickupOrder(orderId, editCode.getText().toString(), imgUrl)
                                        .compose(RxScheduler.observeOnMainThread())
                                        .as(RxScheduler.bindLifecycle(PayForImageActivity.this))
                                        .subscribe(new BaseObserver<EmptyResponse>() {
                                            @Override
                                            public void onSuccess(EmptyResponse emptyResponse) {
                                                ToastUtils.showShort("????????????");
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
            case R.id.tv_fkfs:
                List<String> carLists = new ArrayList<>();
                carLists.add("????????????"+totalPrice+"???");
                carLists.add("????????????"+totalPrice+"???");
                carLists.add("???????????????"+totalPrice+"???");
                carLists.add("????????????");
                carLists.add("???????????????");
                PickerViewUtils.selectorCustomC(this, carLists, "?????????????????????", new PickerViewUtils.ConditionInterfacd() {
                    @Override
                    public void setIndex(int pos) {
                        tv_fkfs.setText(carLists.get(pos));
                        ll_rwm.setVisibility(View.GONE);
                        ll_dsfy.setVisibility(View.GONE);
                        if(pos==0){
                            payType = "CASH";
                        }
                        if(pos==1){
                            payType = "DESTINATION";
                        }
                        if(pos==2){
                            payType = "WXAPP";
                            ll_rwm.setVisibility(View.VISIBLE);
                        }
                        if(pos==3){
                            payType = "COLLECTING_MONEY";
                            ll_dsfy.setVisibility(View.VISIBLE);
                        }
                        if(pos==4){
                            ll_rwm.setVisibility(View.GONE);
                            ll_dsfy.setVisibility(View.GONE);
                            payType = "-1";
                        }
                    }
                });
                break;
        }
    }


    private void selectImage() {
        PermissionHelper.requestCAMERA(() -> {
            PictureSelector.create(PayForImageActivity.this)
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
                        GlideImageLoader.loadImageAndDefault(this, selectList.get(0).getCompressPath(), ivCamera);
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
        ToastUtils.showShort("???????????????...");
        RetrofitManager.build().create(UserService.class).uploadImage(map, body)
                .compose(RxScheduler.<BaseResponse<String>>observeOnMainThread())
                .as(AutoDispose.<BaseResponse<String>>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(new BaseObserver<String>() {

                               @Override
                               public void onSuccess(String s) {
                                   hideLoading();
                                   imgUrl = s;
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
