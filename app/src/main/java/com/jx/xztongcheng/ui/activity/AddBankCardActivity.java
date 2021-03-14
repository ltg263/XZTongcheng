package com.jx.xztongcheng.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.event.AccountLists;
import com.jx.xztongcheng.bean.request.BankCardRequest;
import com.jx.xztongcheng.bean.response.AddAccount;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.utils.GlideImageLoader;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddBankCardActivity extends BaseActivity {


    @BindView(R.id.include)
    Toolbar toolbar;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.cardNo)
    EditText cardNo;
    @BindView(R.id.bankName)
    EditText bankName;
    @BindView(R.id.cardImage)
    ImageView cardImage;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.iv)
    ImageView mIv;

    int status = 1;
    boolean isUpdate = false;
    int id;
    BankCardRequest request = new BankCardRequest();

    @Override
    public int intiLayout() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    public void initView() {
        setToolbar(toolbar,"添加银行卡",true);

        isUpdate = getIntent().getBooleanExtra("isUpdate",false);
        id = getIntent().getIntExtra("id",0);
    }

    @Override
    public void initData() {
        if (isUpdate) {
            RetrofitManager.build().create(UserService.class).getAcconunt(id+"")
                    .compose(RxScheduler.observeOnMainThread())
                    .as(RxScheduler.bindLifecycle(this))
                    .subscribe(new BaseObserver<AccountLists.ListBean>() {
                        @Override
                        public void onSuccess(AccountLists.ListBean request) {
                            hideLoading();
                            username.setText(request.getName());
                            phone.setText(request.getMobile());
                            cardNo.setText(request.getAccountNo());
                            bankName.setText(request.getBank());
                            status = request.getStatus();
                            if(request.getStatus()==1){
                                mIv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_circle_yes_l));
                            }
//                            GlideImageLoader.loadImageAndDefault(AddBankCardActivity.this,request.getImgUrl(),cardImage);
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            hideLoading();
                        }
                    });
        }
    }

    @OnClick({R.id.cardImage, R.id.btnSubmit,R.id.iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardImage:
                selectImage();
                break;
            case R.id.iv:
                if(status == 1){
                    status = 2;
                    mIv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_circle_no));
                }else{
                    status = 1;
                    mIv.setImageDrawable(getResources().getDrawable(R.mipmap.ic_circle_yes_l));
                }
                break;
            case R.id.btnSubmit:
                String mobile = phone.getText().toString();
//                String idCard = cardNo.getText().toString();
                String realName = username.getText().toString();
                String accountNo = cardNo.getText().toString();
                int accountType = 2;
                String bank = bankName.getText().toString();
                if (StringUtils.isEmpty(mobile) ||
//                        StringUtils.isEmpty(idCard) ||
                        StringUtils.isEmpty(realName)
                        ||StringUtils.isEmpty(bank)
                        ||StringUtils.isEmpty(accountNo)
//                        ||StringUtils.isEmpty(request.getImgUrl())
                ){
                    ToastUtils.showShort("请填写完整的信息");
                    return;
                }
                request.setMobile(mobile);
                request.setStatus(status);
                request.setName(realName);
                request.setAccountNo(accountNo);
                request.setBank(bank);
                request.setCashOutType(1);
                request.setId(id);
//                request.setImgUrl(request.getImgUrl());
//                if (isUpdate)
//                    request.setId(id);

                Log.w("--->>>","new Gson().toJson(request):"+new Gson().toJson(request));
                RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),new Gson().toJson(request));
                if (isUpdate) {
                    showLoading();
                    RetrofitManager.build().create(UserService.class).addCardUpdate(body)
                            .compose(RxScheduler.observeOnMainThread())
                            .as(RxScheduler.bindLifecycle(this))
                            .subscribe(new BaseObserver<EmptyResponse>() {
                                @Override
                                public void onSuccess(EmptyResponse emptyResponse) {
                                    hideLoading();
                                    ToastUtils.showShort("添加成功");
                                    finish();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    hideLoading();
                                }
                            });

                } else {
                    showLoading();
                    RetrofitManager.build().create(UserService.class).addCard(request.getAccountNo(),request.getBank(),
                            request.getMobile(),request.getName(),request.getStatus()+"",1+"")
                            .compose(RxScheduler.observeOnMainThread())
                            .as(RxScheduler.bindLifecycle(this))
                            .subscribe(new BaseObserver<EmptyResponse>() {
                                @Override
                                public void onSuccess(EmptyResponse emptyResponse) {
                                    hideLoading();
                                    ToastUtils.showShort("添加成功");
                                    finish();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    super.onError(e);
                                    hideLoading();
                                }
                            });
                }
                break;
        }
    }

    private void selectImage() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
//                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case PictureConfig.CHOOSE_REQUEST:
                        List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                        GlideImageLoader.loadImageAndDefault(this,selectList.get(0).getCompressPath(),cardImage);
                        uploadImage(selectList.get(0).getCompressPath());
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

        MultipartBody.Part body = MultipartBody.Part.createFormData("file1", file.getName(), requestFile);

//        Map<String, RequestBody> map = new HashMap<>();
//        map.put("fileType", RequestBody.create(null, String.valueOf(4)));
        showLoading();
//        RetrofitManager.build().uploadImage(body)
//                .compose(RxSchedulers.<BaseData<String>>compose())
//                .as(AutoDispose.<BaseData<String>>autoDisposable(AndroidLifecycleScopeProvider.from(this)))
//                .subscribe(
//                        new BaseObserver<String>() {
//                            @Override
//                            public void onHandleSuccess(BaseData<String> t) throws Exception {
//                                hideLoading();
//                                request.setImgUrl(t.getData());
//                            }
//
//                            @Override
//                            public void onHandleError(String msg) {
//                                hideLoading();
//                                ToastUtils.showShort("上传图片失败");
//                            }
//                        }
//                );

    }

}
