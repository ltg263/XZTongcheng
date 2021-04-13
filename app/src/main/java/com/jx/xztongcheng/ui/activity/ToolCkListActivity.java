package com.jx.xztongcheng.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.event.CarListBean;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.utils.PickerViewUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ToolCkListActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar mToolbar;
    @BindView(R.id.et_ddh)
    EditText mEtDdh;
    @BindView(R.id.tv_ck)
    TextView mTvCk;
    @BindView(R.id.tv_status)
    TextView tv_status;
    private List<String> carLists= new ArrayList<>();
    private List<CarListBean.ListBean> carBeanLists;

    @Override
    public int intiLayout() {
        return R.layout.activity_tool_ck_list;
    }

    @Override
    public void initView() {
        setToolbar(mToolbar, "订单出库", true);
        mToolbar.setNavigationIcon(R.mipmap.icon_common_back);

        mEtDdh.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if ((event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
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

    @Override
    public void initData() {
        loadData();
    }

    @OnClick({R.id.tv_sao, R.id.tv_ck, R.id.tv_rk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sao:

                //动态权限申请
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
                } else {
                    Intent intent = new Intent(this, CaptureZActivity.class);
                    intent.putExtra("type",0);
                    intent.putExtra("websiteCarId",websiteCarId);
                    startActivityForResult(intent, ToolRkListActivity.RESULT_SUCCESS);
                }
                break;
            case R.id.tv_ck:
//                startActivity(new Intent(this,ToolCarListActivity.class));
                goCarList();
                break;
            case R.id.tv_rk:
                goRk();
                break;
        }
    }
    private String websiteCarId = null;

    private void goCarList() {
        if(carLists.size()==0){
            ToastUtils.showShort("暂无车辆");
            return;
        }
        PickerViewUtils.selectorCustomC(this, carLists, "请选择车辆", new PickerViewUtils.ConditionInterfacd() {
            @Override
            public void setIndex(int pos) {
                websiteCarId=carBeanLists.get(pos).getWebsiteCarId();
                mTvCk.setText(carBeanLists.get(pos).getPlateNumber());
            }
        });

    }

    private void goRk() {
        String ddh = mEtDdh.getText().toString();
        if(StringUtils.isEmpty(ddh)){
            ToastUtils.showShort("出库订单号不能为空");
            return;
        }
        RetrofitManager.build().create(OrderService.class).outWarehouse(ddh,websiteCarId)
                .compose(RxScheduler.<BaseResponse<Object>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<Object>>bindLifecycle(ToolCkListActivity.this))
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        tv_status.setText(mEtDdh.getText().toString()+"出库成功");
                        mEtDdh.setText("");
                        ToastUtils.showShort("出库成功");
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                    }
                });
    }
    private void loadData(){
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("pageSize", 100);
        RetrofitManager.build().create(OrderService.class).getCarList(map)
                .compose(RxScheduler.<BaseResponse<CarListBean>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<CarListBean>>bindLifecycle(this))
                .subscribe(new BaseObserver<CarListBean>() {
                    @Override
                    public void onSuccess(CarListBean coreOrderList) {
                        if(coreOrderList.getList()!=null && coreOrderList.getList().size()>0){
                            carBeanLists =coreOrderList.getList();
                            for(int i= 0;i<coreOrderList.getList().size();i++){
                                carLists.add(coreOrderList.getList().get(i).getPlateNumber());
                            }
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                    }
                });
    }
}
