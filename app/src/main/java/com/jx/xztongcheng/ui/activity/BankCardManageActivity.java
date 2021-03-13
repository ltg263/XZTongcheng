package com.jx.xztongcheng.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.ui.adpter.BankCardAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class BankCardManageActivity extends BaseActivity {



    @BindView(R.id.include)
    Toolbar myToolbar;
    @BindView(R.id.cardRv)
    RecyclerView cardRv;

    BankCardAdapter adapter;
//    List<BankCardBean.ListBean> listBeans = new ArrayList<>();
    boolean isSelect = false;

    @Override
    public int intiLayout() {
        return R.layout.activity_bank_card_manage;
    }

    @Override
    public void initView() {

        setToolbar(myToolbar,"银行卡管理",true);

        isSelect = getIntent().getBooleanExtra("isSelect",false);
        adapter = new BankCardAdapter(null);
        cardRv.setAdapter(adapter);
        if (isSelect) {
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    Intent intent = new Intent();
//                    intent.putExtra("item",listBeans.get(position));
//                    setResult(2,intent);
//                    finish();
                }
            });
        } else {
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(BankCardManageActivity.this,AddBankCardActivity.class);
//                    intent.putExtra("isUpdate",true);
//                    intent.putExtra("id",listBeans.get(position).getId());
                    startActivity(intent);
                }
            });
        }

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
//                new XPopup.Builder(BankCardManageActivity.this)
//                        .asConfirm("删除银行卡", "删除银行卡将无法恢复，确认删除吗",
//                                new OnConfirmListener() {
//                                    @Override
//                                    public void onConfirm() {
//                                        RetrofitManager.build().deleteCard(listBeans.get(position).getId())
//                                                .compose(RxSchedulers.<BaseData<Object>>compose())
//                                                .as(RxSchedulers.<BaseData<Object>>bindLifecycle(BankCardManageActivity.this))
//                                                .subscribe(new BaseObserver<Object>() {
//                                                    @Override
//                                                    public void onHandleSuccess(BaseData<Object> t) throws Exception {
//                                                        listBeans.remove(position);
//                                                        adapter.remove(position);
//                                                        ToastUtils.showShort("删除成功");
//                                                    }
//                                                });
//                                    }
//                                }).show();

            }
        });
    }

    @Override
    public void initData() {
    }

    @OnClick(R.id.addBankCard)
    public void onViewClicked() {
        ActivityUtils.startActivity(AddBankCardActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RetrofitManager.build().create(UserService.class).getBankCard()
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(this))
                .subscribe(new BaseObserver<EmptyResponse>() {
                    @Override
                    public void onSuccess(EmptyResponse emptyResponse) {

                    }
                });
//
//                .subscribe(new BaseObserver<BankCardBean>() {
//                    @Override
//                    public void onHandleSuccess(BaseData<BankCardBean> t) throws Exception {
//                        listBeans = t.getData().getList();
//                        adapter.setNewData(listBeans);
//                    }
//                });
    }
}
