package com.jx.xztongcheng.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
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
                    return true;
                }
                return false;
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
                        ToastUtils.showShort("入库成功");
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
