package com.jx.xztongcheng.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import butterknife.BindView;
import butterknife.OnClick;


public class ToolRkListActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar mToolbar;
    @BindView(R.id.et_ddh)
    EditText mEtDdh;
    @BindView(R.id.et_zl)
    EditText mEtZl;
    @BindView(R.id.tv_status)
    TextView tv_status;

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
        mEtDdh.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                Log.w("onEditorAction","onEditorAction:"+event.getKeyCode());
                if (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction()) {
//                    mEtZl.setFocusable(true);
//                    mEtZl.setFocusableInTouchMode(true);
//                    mEtZl.requestFocus();
                    goRk();
                    return true;
                }
                return false;
            }
        });
        mEtDdh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String cS = s.toString().substring(0,s.toString().length()-1);
                if(s.toString().indexOf("\n")>0){
                    mEtDdh.setText(s.toString().replace("\n",""));
                    mEtDdh.setSelection(mEtDdh.getText().toString().length());
                    goRk();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public static final int RESULT_SUCCESS = 1;
    @OnClick({R.id.iv_sao, R.id.tv_weight, R.id.tv_rk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sao:
                //动态权限申请
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    String weight = mEtZl.getText().toString();
                    Intent intent = new Intent(this, CaptureZActivity.class);
                    intent.putExtra("type",1);
                    if(!StringUtils.isEmpty(weight)){
                        intent.putExtra("weight",weight);
                    }
                    startActivityForResult(intent, RESULT_SUCCESS);
                }
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
                        tv_status.setText(mEtDdh.getText().toString()+"入库成功");
                        mEtDdh.setText("");
                        ToastUtils.showShort("入库成功");
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.w("onAcitvity:","拿到了返回值");//日志封装可以忽略
        if (requestCode == RESULT_SUCCESS) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                Log.w("onAcitvity:","二维码返回结果为："+content);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
