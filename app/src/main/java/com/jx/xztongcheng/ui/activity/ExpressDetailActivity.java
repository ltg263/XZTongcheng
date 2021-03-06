package com.jx.xztongcheng.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.event.AddressLagBean;
import com.jx.xztongcheng.bean.event.ExpressEvent;
import com.jx.xztongcheng.bean.request.SettingPriceRequest;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.bean.response.ExpressAddressDTOS;
import com.jx.xztongcheng.bean.response.OrderListBean;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.ui.fragment.SwitchMapFragment;
import com.jx.xztongcheng.utils.DialogUtils;
import com.jx.xztongcheng.utils.PermissionHelper;
import com.jx.xztongcheng.utils.PopupWindowA;
import com.jx.xztongcheng.widget.MyMapView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

//????????????
public class ExpressDetailActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar myToolbar;
    @BindView(R.id.map)
    MyMapView mMap;
    @BindView(R.id.nsw)
    NestedScrollView mNsw;
    @BindView(R.id.tv_get_address1)
    TextView tvGetAddress1;
    @BindView(R.id.tv_get_address2)
    TextView tvGetAddress2;
    @BindView(R.id.tv_to_address1)
    TextView tvToAddress1;
    @BindView(R.id.tv_to_address2)
    TextView tvToAddress2;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_put_time)
    TextView tvPutTime;
    @BindView(R.id.tv_get_address)
    TextView tvGetAddress;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.et_express_company)
    EditText etExpressCompany;
    @BindView(R.id.et_express_no)
    EditText etExpressNo;
    @BindView(R.id.et_waybill_number)
    EditText et_waybill_number;
    @BindView(R.id.et_weight)
    EditText etWeight;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.et_package_price)
    EditText etPackagePrice;
    @BindView(R.id.et_bj_amount)
    EditText etBjAmount;
    @BindView(R.id.et_bj_price)
    EditText etBjPrice;
    @BindView(R.id.et_dsje)
    EditText etDsje;
    @BindView(R.id.et_other_price)
    EditText etOtherPrice;
    @BindView(R.id.tv_btn1)
    TextView tvBtn1;
    @BindView(R.id.tv_btn2)
    TextView tvBtn2;
    @BindView(R.id.ll_btm)
    LinearLayout llBtm;
    @BindView(R.id.cv_price)
    CardView cvPrice;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.ll_kdxx)
    LinearLayout llKdxx;
    @BindView(R.id.tv_express)
    TextView tvExpress;
    @BindView(R.id.coup_price)
    TextView coupPrice;

    private AMap aMap;
    private int orderId;
    private OrderListBean coreOrderList;
    private String payType = "WXSCAN";

    private int calculationType = 0;//??????  2 ??????
    private double discount = 0.0;

    @Override
    public int intiLayout() {
        return R.layout.activity_express_detail;
    }

    @Override
    public void initView() {
        orderId = getIntent().getIntExtra("orderId", 0);
        setToolbar(myToolbar, "????????????", true);

        initMap();

        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String cS = s.toString().substring(0,s.toString().length()-1);
                if(!StringUtils.isEmpty(s) && calculationType==1 && discount!=0){
                    coupPrice.setText(""+(Double.valueOf(s.toString())*discount));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    public void initData() {
    }

    private void initMap() {
        //??????????????????????????????
        if (aMap == null) {
            aMap = mMap.getMap();
        }
//        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(SPUtils.getInstance().getFloat(ConstValues.LAT, 1), SPUtils.getInstance().getFloat(ConstValues.LON, 1))));
        aMap.moveCamera(CameraUpdateFactory.zoomTo(13));
        // ?????? Marker ??????????????????
//        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
//            // marker ?????????????????????????????????
//            // ?????? true ?????????????????????????????????????????????false
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//
//                return false;
//            }
//        };
//        // ?????? Marker ???????????????
//        aMap.setOnMarkerClickListener(markerClickListener);


        mMap.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    mNsw.requestDisallowInterceptTouchEvent(false);
                } else {
                    mNsw.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        tvExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindw();
            }
        });
    }

    PopupWindowA window;
    private void popupWindw() {
        window = new PopupWindowA(this, new PopupWindowA.GiveDialogInterface() {
            @Override
            public void btnConfirm(int type) {
                window.dismiss();
                String address = tvGetAddress2.getText().toString();
                if(type==2){
                    address = tvToAddress2.getText().toString();
                }

                goDiZhi(address);
            }
        });
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.setOutsideTouchable(true);
        window.showAtLocation(tvName, Gravity.BOTTOM,  0, 0);
    }

    private void goDiZhi(String address) {
        RetrofitManager.build().create(UserService.class)
                .getBannerGao("77f0363d7e97bb832d81e108ce8a776e",address)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(this))
                .subscribe(new Observer<AddressLagBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddressLagBean addressLagBean) {
                        if(addressLagBean.getStatus().equals("1") && addressLagBean.getGeocodes()!=null && addressLagBean.getGeocodes().size()>0){
                            String location = addressLagBean.getGeocodes().get(0).getLocation();
                            String[] locationA = location.split(",");
                            LatLonPoint latLonPoint = new LatLonPoint(Double.valueOf(locationA[1]),Double.valueOf(locationA[0]));
                            PoiItem poiItem = new PoiItem(address, latLonPoint, "", "");
                            if (poiItem != null) {
                                if (!AppUtils.isAppInstalled("com.autonavi.minimap")
                                        && !AppUtils.isAppInstalled("com.baidu.BaiduMap")
                                        && !AppUtils.isAppInstalled("com.tencent.map")) {
                                    ToastUtils.showShort("???????????????????????????");
                                } else {
                                    SwitchMapFragment switchMapFragment = SwitchMapFragment.newInstance(poiItem);
                                    switchMapFragment.show(getSupportFragmentManager(), "SwitchPayDialogFragment");
                                }
                            }
                        }else{
                            ToastUtils.showShort("?????????????????????????????????");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("locationA","e"+e.toString());
                        ToastUtils.showShort("?????????????????????????????????");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void refreshData() {
        RetrofitManager.build().create(OrderService.class).getOrderDetail(orderId)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(this))
                .subscribe(new BaseObserver<OrderListBean>() {
                    @Override
                    public void onSuccess(OrderListBean c) {
                        if (c != null && c.getExpressOrderDTOS() != null && c.getExpressOrderDTOS().size() > 0) {
                            coreOrderList = c;
                            calculationType = c.getCalculationType();
                            discount = c.getDiscount();
                            refreshUI();
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                    }
                });
    }

    private void refreshUI() {
        OrderListBean.ExpressOrderDTOSBean orderDTOSBean = coreOrderList.getExpressOrderDTOS().get(0);
        OrderListBean.ExpressDTOBean expressDTOBean = orderDTOSBean.getExpressDTO();
        ExpressAddressDTOS addressDTOS = orderDTOSBean.getExpressAddressDTOS();
        if (orderDTOSBean == null || expressDTOBean == null || addressDTOS == null) {
            ToastUtils.showShort("????????????");
//            finish();
//            return;
        }
        if (addressDTOS != null) {
            tvRemark.setText("???         ??? ???" + orderDTOSBean.getRemark());
            tvGetAddress1.setText(addressDTOS.getMailName() + "  " + addressDTOS.getMailMobile());
            tvGetAddress2.setText(addressDTOS.getMailAddress());
            tvToAddress1.setText(addressDTOS.getToName() + "  " + addressDTOS.getToMobile());
            tvToAddress2.setText(addressDTOS.getToAddress());
            tvContact.setOnClickListener(view -> {
                PermissionHelper.requestPhone(new PermissionHelper.OnPermissionGrantedListener() {
                    @Override
                    public void onPermissionGranted() {
                        PhoneUtils.call(addressDTOS.getMailMobile());
                    }
                });
            });
        }
        if (expressDTOBean != null) {
            tvName.setText("???????????? ???" + expressDTOBean.getExpressName());
            tvPutTime.setText("???????????? ???" + expressDTOBean.getCreateTime());
            if (addressDTOS != null)
                tvGetAddress.setText("???????????? ???" + addressDTOS.getMailAddress());
        }

        switch (orderDTOSBean.getPayType()){
            case "CASH":
                tvPayType.setText("????????????");
                break;
            case "DESTINATION":
                tvPayType.setText("????????????");
                break;
            case "COLLECTING_MONEY":
                tvPayType.setText("????????????:"+orderDTOSBean.getMoney()+"???");
                break;
        }
        switch (coreOrderList.getNextTransportStatus()) {
            case 1:
                tvBtn2.setText("??????");
                tvBtn2.setOnClickListener(v -> {
                    DialogUtils.cancelDialog(ExpressDetailActivity.this, "????????????", "????????????????????????"
                            , new DialogInterface.OnClickListener() {
                                @Override

                                public void onClick(DialogInterface dialog, int which) {
                                    RetrofitManager.build().create(OrderService.class).receiveExpress(coreOrderList.getExpressGeneralOrderId())
                                            .compose(RxScheduler.<BaseResponse<EmptyResponse>>observeOnMainThread())
                                            .as(RxScheduler.<BaseResponse<EmptyResponse>>bindLifecycle(ExpressDetailActivity.this))
                                            .subscribe(new BaseObserver<EmptyResponse>() {
                                                @Override
                                                public void onSuccess(EmptyResponse coreOrderList) {
                                                    ToastUtils.showShort("????????????");
                                                    finish();
//                                                    refreshData();
                                                }

                                                @Override
                                                public void onFail(int code, String msg) {
                                                    super.onFail(code, msg);

                                                }
                                            });
                                }
                            }).show();
                });
                break;
            case 6:
//                    if (coreOrderList.getExpressType() == 1) {
//                        tvBtn2.setText("??????");
//                        tvBtn2.setOnClickListener(v -> pickUpOrder());
//                    } else {
                tvBtn2.setText("??????");
                tvBtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String expressWeight = etWeight.getText().toString().trim();
                        String bjAmount = etBjAmount.getText().toString().trim();
                        String bjPrice = etBjPrice.getText().toString().trim();
                        String dshk = etDsje.getText().toString().trim();
                        String packagePrice = etPackagePrice.getText().toString().trim();
                        String price = etPrice.getText().toString().trim();
                        String otherPrice = etOtherPrice.getText().toString().trim();
                        if (TextUtils.isEmpty(expressWeight)
                                || TextUtils.isEmpty(bjAmount)
                                || TextUtils.isEmpty(bjPrice)
                                || TextUtils.isEmpty(dshk)
                                || TextUtils.isEmpty(packagePrice)
                                || TextUtils.isEmpty(price)
                                || TextUtils.isEmpty(otherPrice)
                        ) {
                            ToastUtils.showShort("?????????????????????");
                            return;
                        }

                        SettingPriceRequest settingPriceRequest = new SettingPriceRequest();
                        settingPriceRequest.setFee(price);
                        settingPriceRequest.setOrderId(orderId);
                        settingPriceRequest.setInsuredAmount(bjAmount);
                        settingPriceRequest.setInsuredFee(bjPrice);
                        settingPriceRequest.setOtherFee(otherPrice);
                        settingPriceRequest.setPackageFee(packagePrice);
                        settingPriceRequest.setPayType(payType);
                        settingPriceRequest.setWeight(expressWeight);
                        settingPriceRequest.setTotalBonus(dshk);

                        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(settingPriceRequest));
                        RetrofitManager.build().create(OrderService.class)
                                .settingPrice(body)
                                .compose(RxScheduler.observeOnMainThread())
                                .as(RxScheduler.bindLifecycle(ExpressDetailActivity.this))
                                .subscribe(new BaseObserver<Double>() {
                                    @Override
                                    public void onSuccess(Double sprice) {
                                        try {
//                                            int totalPrice = Integer.parseInt(bjPrice)
//                                                    + Integer.parseInt(price)
//                                                    + Integer.parseInt(otherPrice)
//                                                    + Integer.parseInt(dshk)
//                                                    + Integer.parseInt(packagePrice);
                                            Intent intent = new Intent(ExpressDetailActivity.this, PayForImageActivity.class);
                                            intent.putExtra("orderId", orderId);
                                            intent.putExtra("totalPrice", sprice);
                                            intent.putExtra("code", orderDTOSBean.getCode());
                                            startActivity(intent);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    }
                });

//                    }
                break;
            case 2:
                tvBtn2.setText("???????????????");
                tvBtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pickUpOrder();
                    }
                });
                break;
            case 3:
                tvBtn2.setText("????????????");
                tvBtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RetrofitManager.build().create(OrderService.class)
                                .send(orderId)
                                .compose(RxScheduler.observeOnMainThread())
                                .as(RxScheduler.bindLifecycle(ExpressDetailActivity.this))
                                .subscribe(new BaseObserver<EmptyResponse>() {
                                    @Override
                                    public void onSuccess(EmptyResponse emptyResponse) {
                                        ToastUtils.showShort("????????????");
                                        finish();
//                                        refreshData();
                                    }
                                });
                    }
                });
                break;
            case 4:
                tvBtn2.setText("??????");
                tvBtn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (coreOrderList.getExpressType() == 1) {
                            finishOrder(null);
                        } else {
                            View view1 = LayoutInflater.from(ExpressDetailActivity.this).inflate(R.layout.dialog_edit, null);
                            EditText editCode = view1.findViewById(R.id.edit_code);
                            new AlertDialog.Builder(ExpressDetailActivity.this)
                                    .setTitle("??????????????????")
                                    .setView(view1)
                                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finishOrder(editCode.getText().toString());
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
                        }
                    }
                });
                break;
            case 5:
                tvBtn2.setText("????????????");
                break;
            case 10:
                tvBtn2.setText("???????????????");
                break;
            case 0:
                tvBtn2.setText("?????????");
                break;
            default:
                tvBtn2.setText("?????????");
                break;

        }
        if (coreOrderList.getNextTransportStatus() == 6) {
            tvPayType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectPay();
                }
            });
            llKdxx.setVisibility(View.GONE);
            setEditTextable(etExpressCompany, true);
            setEditTextable(etExpressNo, true);
            setEditTextable(et_waybill_number, true);
            setEditTextable(etWeight, true);
            setEditTextable(etBjAmount, true);
            setEditTextable(etBjPrice, true);
            setEditTextable(etDsje, true);
            setEditTextable(etPackagePrice, true);
            setEditTextable(etPrice, true);
            setEditTextable(etOtherPrice, true);
        } else {
            llKdxx.setVisibility(View.VISIBLE);
            setEditTextable(etExpressCompany, false);
            setEditTextable(etExpressNo, false);
            setEditTextable(et_waybill_number, false);
            setEditTextable(etWeight, false);
            setEditTextable(etBjAmount, false);
            setEditTextable(etBjPrice, false);
            setEditTextable(etDsje, false);
            setEditTextable(etPackagePrice, false);
            setEditTextable(etPrice, false);
            setEditTextable(etOtherPrice, false);
        }
        etExpressCompany.setText(coreOrderList.getWebsiteName());
        etBjAmount.setText(coreOrderList.getCost());
        etBjPrice.setText(coreOrderList.getInsuredFee());
        etExpressNo.setText(coreOrderList.getExpressNo());
        et_waybill_number.setText(orderDTOSBean.getWaybillNumber());
        etWeight.setText(coreOrderList.getWeight());
        etDsje.setText(coreOrderList.getTotalBonus() + "");
        etPrice.setText(coreOrderList.getFee() + "");
        etPackagePrice.setText(coreOrderList.getPackageFee());
        etOtherPrice.setText(coreOrderList.getOtherFee());
        coupPrice.setText(coreOrderList.getCouponAmount());
    }

    private void finishOrder(String toString) {
        RetrofitManager.build().create(OrderService.class)
                .finish(orderId, toString)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(ExpressDetailActivity.this))
                .subscribe(new BaseObserver<EmptyResponse>() {
                    @Override
                    public void onSuccess(EmptyResponse emptyResponse) {
                        ToastUtils.showShort("????????????");
                        finish();
                    }
                });
    }

    private void pickUpOrder() {
        if (coreOrderList.getExpressType() == 1) {
            View view1 = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null);
            EditText editCode = view1.findViewById(R.id.edit_code);
            new AlertDialog.Builder(this)
                    .setTitle("??????????????????")
                    .setView(view1)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            pickUp(editCode.getText().toString());
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
        } else {
            pickUp(null);
        }
    }

    private void pickUp(String toString) {
        RetrofitManager.build().create(OrderService.class)
                .pickupOrder(orderId, toString)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(ExpressDetailActivity.this))
                .subscribe(new BaseObserver<EmptyResponse>() {
                    @Override
                    public void onSuccess(EmptyResponse emptyResponse) {
                        ToastUtils.showShort("????????????");
                        finish();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    private void setEditTextable(EditText editText, boolean editable) {
        if (!editable) { // disable editing password
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            editText.setClickable(false); // user navigates with wheel and selects widget
        } else { // enable editing of password
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.setClickable(true);
        }
    }

    AlertDialog alertDialog;
    int selectPos = 0;

    private void selectPay() {
//        final String[] items = {"????????????", "???????????????", "????????????"};
//        final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
//        alertBuilder.setTitle("??????????????????");
//        alertBuilder.setSingleChoiceItems(items, selectPos, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                tvPayType.setText(items[i]);
//                selectPos = i;
//                if (i == 0) {
//                    payType = "BALANCE";
//                } else if (i == 1) {
//                    payType = "ALISCANQR";
//                } else {
//                    payType = "WXSCANQR";
//                }
//                alertDialog.dismiss();
//            }
//        });
//
//        alertDialog = alertBuilder.create();
//        alertDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(new ExpressEvent());
    }
}
