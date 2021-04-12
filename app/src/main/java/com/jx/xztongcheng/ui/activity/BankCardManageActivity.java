package com.jx.xztongcheng.ui.activity;

import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.event.AccountLists;
import com.jx.xztongcheng.bean.request.BankCardRequest;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.UserService;
import com.jx.xztongcheng.ui.adpter.BankCardAdapter;
import com.jx.xztongcheng.utils.DialogUtils;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

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

        setToolbar(myToolbar, "银行卡管理", true);

        isSelect = getIntent().getBooleanExtra("isSelect",false);
        adapter = new BankCardAdapter(null);
        cardRv.setAdapter(adapter);
        if (isSelect) {
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(BankCardManageActivity.this,AddBankCardActivity.class);
                    intent.putExtra("item",BankCardManageActivity.this.adapter.getData().get(position));
                    setResult(2,intent);
                    finish();
                }
            });
        } else {
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(BankCardManageActivity.this, AddBankCardActivity.class);
                    intent.putExtra("isUpdate", true);
                    intent.putExtra("id", BankCardManageActivity.this.adapter.getData().get(position).getId());
                    startActivity(intent);
                }
            });
        }

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                if(view.getId()==R.id.iv_mr){
                    if(BankCardManageActivity.this.adapter.getData().get(position).getStatus()==1){
                        return;
                    }
                    BankCardRequest request = new BankCardRequest();
                    request.setId(BankCardManageActivity.this.adapter.getData().get(position).getId());
                    request.setStatus(1);

                    RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8"),new Gson().toJson(request));
                    RetrofitManager.build().create(UserService.class).addCardUpdatestatus(body)
                            .compose(RxScheduler.observeOnMainThread())
                            .as(RxScheduler.bindLifecycle(BankCardManageActivity.this))
                            .subscribe(new BaseObserver() {
                                @Override
                                public void onSuccess(Object str) {
                                    aaa();
                                    ToastUtils.showShort("修改成功");
                                }
                            });
                    return;
                }
                DialogUtils.showDialogHint(BankCardManageActivity.this, "删除银行卡将无法恢复，确认删除吗",
                        false, () -> {
                            RetrofitManager.build().create(UserService.class).deleteCard(
                                    BankCardManageActivity.this.adapter.getData().get(position).getId())
                                    .compose(RxScheduler.observeOnMainThread())
                                    .as(RxScheduler.bindLifecycle(BankCardManageActivity.this))
                                    .subscribe(new BaseObserver() {
                                        @Override
                                        public void onSuccess(Object str) {
                                            BankCardManageActivity.this.adapter.remove(position);
                                            BankCardManageActivity.this.adapter.notifyDataSetChanged();
                                            ToastUtils.showShort("删除成功");
                                        }
                                    });
                        });


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
        aaa();
//
//                .subscribe(new BaseObserver<BankCardBean>() {
//                    @Override
//                    public void onHandleSuccess(BaseData<BankCardBean> t) throws Exception {
//                        listBeans = t.getData().getList();
//                        adapter.setNewData(listBeans);
//                    }
//                });
    }

    private void aaa() {

        RetrofitManager.build().create(UserService.class).getBankCard()
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(this))
                .subscribe(new BaseObserver<AccountLists>() {
                    @Override
                    public void onSuccess(AccountLists emptyResponse) {
                        adapter.setNewData(emptyResponse.getList());
                    }
                });
    }
}
