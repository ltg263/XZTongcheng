package com.jx.xztongcheng.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;

import butterknife.BindView;
import butterknife.OnClick;


public class ToolRkListActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar mToolbar;
    @BindView(R.id.et_ddh)
    EditText mEtDdh;
    @BindView(R.id.et_zl)
    EditText mEtZl;

    @Override
    public int intiLayout() {
        return R.layout.activity_tool_rk_list;
    }

    @Override
    public void initView() {
        setToolbar(mToolbar, "订单入库", true);
        mToolbar.setNavigationIcon(R.mipmap.icon_common_back);

    }

    @Override
    public void initData() {
        mEtDdh.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入过程中，还在内存里，没到屏幕上
                LogUtils.w("CharSequence---------------"+s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 在输入之前会触发的
                LogUtils.w("beforeTextChanged---------------"+s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 输入完将要显示到屏幕上时会触发
                boolean isEmpty = s.toString().trim().isEmpty();
                LogUtils.w("beforeTextChanged---------------"+s);
            }
        });
    }

    @OnClick({R.id.tv_sao, R.id.tv_weight, R.id.tv_rk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sao:
                break;
            case R.id.tv_weight:
                break;
            case R.id.tv_rk:
                goRk();
                break;
        }
    }

    private void goRk() {
        String ddh = mEtDdh.getText().toString();
        String zl = mEtZl.getText().toString();
        if(StringUtils.isEmpty(ddh)){
            ToastUtils.showShort("入库订单号不能为空");
            return;
        }
        RetrofitManager.build().create(OrderService.class).coreStorage(ddh,zl)
                .compose(RxScheduler.<BaseResponse<Object>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<Object>>bindLifecycle(ToolRkListActivity.this))
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(Object o) {

                        ToastUtils.showShort("入库成功");
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                    }
                });
    }
}
