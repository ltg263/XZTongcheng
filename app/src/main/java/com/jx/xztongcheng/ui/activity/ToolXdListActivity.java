package com.jx.xztongcheng.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.utils.PickerViewUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToolXdListActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar myToolbar;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.sv_1)
    Switch mSv1;
    @BindView(R.id.sv_2)
    Switch mSv2;
    @BindView(R.id.tv_rk)
    TextView mTvRk;
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

    @Override
    public int intiLayout() {
        return R.layout.activity_tool_xd_list;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "下单", true);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_time, R.id.tv_rk,R.id.tv_addressj, R.id.tv_addresss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_addressj:

                break;
            case R.id.tv_addresss:

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
            case R.id.tv_rk:

//                mSv1.isChecked()
                break;
        }
    }
}
