package com.jx.xztongcheng.ui.activity;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.event.CourierListBaen;
import com.jx.xztongcheng.bean.request.ForgetPasswordRequest;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.ui.adpter.DepositLogAdapter;
import com.jx.xztongcheng.utils.DialogUtils;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class DepositLogActivity extends BaseActivity {


    @BindView(R.id.include)
    Toolbar myToolbar;
    @BindView(R.id.logRv)
    RecyclerView logRv;

    DepositLogAdapter adapter;

    @Override
    public int intiLayout() {
        return R.layout.activity_deposit_log;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar,"提现列表",true);

        adapter = new DepositLogAdapter(null);
        logRv.setAdapter(adapter);

//        logRv.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.line_gray)));
//        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                switch (view.getId()) {
//                    case R.id.btnCancel:
//                        DialogUtils.cancelDialog(DepositLogActivity.this, "取消提现", "确认取消提现申请吗?",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        RetrofitManager.build().cancelDeposit()
//                                                .compose(RxSchedulers.<BaseData<Object>>compose())
//                                                .as(RxSchedulers.<BaseData<Object>>bindLifecycle(DepositLogActivity.this))
//                                                .subscribe(new BaseObserver<Object>() {
//                                                    @Override
//                                                    public void onHandleSuccess(BaseData<Object> t) throws Exception {
//                                                        ToastUtils.showShort("取消成功");
//                                                        initData();
//                                                    }
//                                                });
//                                    }
//                                }).show();
//                        break;
//                }
//            }
//        });
    }

    @Override
    public void initData() {
        RetrofitManager.build().create(UserService.class)
                .getCourierList()
                .compose(RxScheduler.<BaseResponse<CourierListBaen>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<CourierListBaen>>bindLifecycle(this))
                .subscribe(new BaseObserver<CourierListBaen>() {
                    @Override
                    public void onSuccess(CourierListBaen emptyResponse) {
                        adapter.setNewData(emptyResponse.getList());
                    }

                    @Override
                    public void onFail(int code, String error) {

                    }
                });
    }
}
