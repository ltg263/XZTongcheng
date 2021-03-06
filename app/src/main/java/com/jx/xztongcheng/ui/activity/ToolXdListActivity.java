package com.jx.xztongcheng.ui.activity;

import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.request.CustomerCreateExpress;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.utils.AddressPickTask;
import com.jx.xztongcheng.utils.PickerViewUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ToolXdListActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar myToolbar;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.sv_1)
    Switch mSv1;
    @BindView(R.id.sv_2)
    Switch mSv2;
    @BindView(R.id.sv_3)
    Switch mSv3;
    @BindView(R.id.tv_addressj)
    TextView mTvAddressj;
    @BindView(R.id.et_addressj)
    EditText mEtAddressj;
    @BindView(R.id.tv_addresss)
    TextView mTvAddresss;
    @BindView(R.id.et_addresss)
    EditText mEtAddresss;
    @BindView(R.id.et_pm)
    EditText mEtPm;
    @BindView(R.id.et_zl)
    EditText mEtZl;
    @BindView(R.id.et_names)
    EditText mEtNames;
    @BindView(R.id.et_mobiles)
    EditText mEtMobiles;
    @BindView(R.id.et_namej)
    EditText mEtNamej;
    @BindView(R.id.et_mobilej)
    EditText mEtMobilej;
    @BindView(R.id.et_bjj)
    EditText et_bjj;
    @BindView(R.id.et_je)
    EditText et_je;

    @Override
    public int intiLayout() {
        return R.layout.activity_tool_xd_list;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "???????????????", true);
        mSv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mSv1.isChecked()) {
                    et_bjj.setVisibility(View.INVISIBLE);
                } else {
                    et_bjj.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_time, R.id.tv_xd, R.id.tv_addressj, R.id.tv_addresss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_addressj:
                onAddressPicker(0);
                break;
            case R.id.tv_addresss:
                onAddressPicker(1);
                break;
            case R.id.tv_time:
                PickerViewUtils.selectorDate(ToolXdListActivity.this,
                        new boolean[]{true, true, true, true, true, false}, new PickerViewUtils.GetTimeInterface() {
                            @Override
                            public void getTime(Date time) {
                                mTvTime.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(time));
                            }
                        });
                break;
            case R.id.tv_xd:
                helpCustomerCreateExpress();
                break;
        }
    }

    String mailProvince, mailCity, mailDistrict;//??????????????????ID
    String toProvince, toCity, toDistrict;//??????????????????ID

    private void helpCustomerCreateExpress() {
        String str1 = mEtNames.getText().toString();
        String str2 = mEtNamej.getText().toString();
        String str3 = mEtMobilej.getText().toString();
        String str4 = mEtMobiles.getText().toString();
        String str5 = mTvAddresss.getText().toString();
        String str6 = mTvAddressj.getText().toString();
        String str7 = mEtAddresss.getText().toString();
        String str8 = mEtAddressj.getText().toString();
        String str9 = mEtPm.getText().toString();
        String str10 = mEtZl.getText().toString();
        String str11 = mTvTime.getText().toString();
        String str12 = et_bjj.getText().toString();
        String str13 = et_je.getText().toString();
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)
                || StringUtils.isEmpty(str3) || StringUtils.isEmpty(str4)
                || StringUtils.isEmpty(str5) || StringUtils.isEmpty(str6)
                || StringUtils.isEmpty(str7) || StringUtils.isEmpty(str8)
                || StringUtils.isEmpty(str9) || StringUtils.isEmpty(str10)
                || StringUtils.isEmpty(str11) || StringUtils.isEmpty(str13)) {
            ToastUtils.showShort("?????????????????????");
            return;
        }

        CustomerCreateExpress mCustomerCreateExpress = new CustomerCreateExpress();
        mCustomerCreateExpress.setConfirm("1");//?????????????????????????????? 1???2???

        mCustomerCreateExpress.setFastStatus("2");//	??????????????????1?????????;2??????
        if (mSv1.isChecked()) {
            mCustomerCreateExpress.setFastStatus("1");
        }

        mCustomerCreateExpress.setInsuredStatus("2");
        if (mSv2.isChecked()) {
            mCustomerCreateExpress.setInsuredStatus("1");//????????????:1,??????;2,?????????
            if (StringUtils.isEmpty(et_bjj.getText().toString())) {
                ToastUtils.showShort("?????????????????????");
                return;
            }
            mCustomerCreateExpress.setInsuredFee(str12);//????????????
        }
        mCustomerCreateExpress.setMailType("1");//????????????:1,????????????
//        if(mSv3.isChecked()){
//            mCustomerCreateExpress.setMailType("1");
//        }
        mCustomerCreateExpress.setOrderType("1");//	1??????2??????, ????????????1
        mCustomerCreateExpress.setPickUpTime(str11 + ":00");//????????????, 2020-10-12 13:40:00
//        mCustomerCreateExpress.setStartAddressId(str6);//????????????
        List<CustomerCreateExpress.ExpressInfoFormsBean> beans = new ArrayList<>();
        CustomerCreateExpress.ExpressInfoFormsBean bean = new CustomerCreateExpress.ExpressInfoFormsBean();
        bean.setAmount(str13);//??????
        bean.setWeight(str10);//??????
        bean.setExpressName(str9);
        beans.add(bean);
        mCustomerCreateExpress.setExpressInfoForms(beans);

        //???????????????
        mCustomerCreateExpress.setMailName(str2);
        mCustomerCreateExpress.setMailMobile(str3);
        mCustomerCreateExpress.setMailAddress(str6);
        mCustomerCreateExpress.setMailAddressText(str8);
        mCustomerCreateExpress.setMailProvince(mailProvince);
        mCustomerCreateExpress.setMailCity(mailCity);
        mCustomerCreateExpress.setMailDistrict(mailDistrict);
        //???????????????
        mCustomerCreateExpress.setToName(str1);
        mCustomerCreateExpress.setToMobile(str4);
        mCustomerCreateExpress.setToAddress(str5);
        mCustomerCreateExpress.setToAddressText(str7);
        mCustomerCreateExpress.setToProvince(toProvince);
        mCustomerCreateExpress.setToCity(toCity);
        mCustomerCreateExpress.setToDistrict(toDistrict);
        Log.w("mCustomerCreateExpress", "mCustomerCreateExpress:" + mCustomerCreateExpress.toString());

        showLoading();
        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), new Gson().toJson(mCustomerCreateExpress));
        RetrofitManager.build().create(OrderService.class)
                .helpCustomerCreateExpress(body)
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(this))
                .subscribe(new BaseObserver<EmptyResponse>() {
                    @Override
                    public void onSuccess(EmptyResponse id) {
                        hideLoading();
                        ToastUtils.showShort("????????????");
                        finish();
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                        hideLoading();
                    }
                });
    }

    public void onAddressPicker(int type) {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {

            @Override
            public void onAddressInitFailed() {
                Toast.makeText(ToolXdListActivity.this, "?????????????????????", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
                    if (type == 0) {
                        mTvAddressj.setText(province.getAreaName() + city.getAreaName());
                        mailProvince = province.getAreaId();
                        mailCity = city.getAreaId();
//                        mailDistrict = county.getAreaId();
                    } else {
                        mTvAddresss.setText(province.getAreaName() + city.getAreaName());
                        toProvince = province.getAreaId();
                        toCity = city.getAreaId();
//                        toDistrict = county.getAreaId();
                    }
                } else {
                    if (type == 0) {
                        mTvAddressj.setText(province.getAreaName() + city.getAreaName() + county.getAreaName());
                        mailProvince = province.getAreaId();
                        mailCity = city.getAreaId();
                        mailDistrict = county.getAreaId();
                    } else {
                        mTvAddresss.setText(province.getAreaName() + city.getAreaName() + county.getAreaName());
                        toProvince = province.getAreaId();
                        toCity = city.getAreaId();
                        toDistrict = county.getAreaId();
                    }
                }
            }
        });
        task.execute("?????????", "?????????", "?????????");
    }
}
