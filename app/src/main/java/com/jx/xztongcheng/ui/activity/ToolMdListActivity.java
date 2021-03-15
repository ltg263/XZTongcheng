package com.jx.xztongcheng.ui.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.response.CoreOrderList;
import com.jx.xztongcheng.bean.response.OrderListBean;
import com.jx.xztongcheng.bean.response.OrderSheetInfo;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.ui.activity.print.DeviceListActivity;
import com.jx.xztongcheng.ui.activity.print.PrintLabel;
import com.jx.xztongcheng.ui.activity.print.ToolMdDetailsActivity;
import com.jx.xztongcheng.ui.adpter.ToolMdAdapter;
import com.qr.print.PrintPP_CPCL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class ToolMdListActivity extends BaseActivity {
    @BindView(R.id.include)
    Toolbar mToolbar;
    @BindView(R.id.tv_print)
    TextView tv_print;
    @BindView(R.id.bnt_print)
    TextView bnt_print;
    @BindView(R.id.rv_data)
    RecyclerView mRvData;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.title_right_text)
    TextView mTitle;
    ToolMdAdapter timeDataAdapter;
    int page = 1, pageSize = 20;
    private List<OrderListBean> beanList;
    private List<OrderListBean> beanListDy = new ArrayList<>();

    @Override
    public int intiLayout() {
        return R.layout.activity_tool_md_list;
    }

    @Override
    public void initView() {
//        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.theme_color));
        setToolbar(mToolbar, "面单统计", true);
        mToolbar.setNavigationIcon(R.mipmap.icon_common_back);

        tv_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beanList != null && beanList.size() > 0) {
                    beanListDy.clear();
                    if (tv_print.getText().toString().equals("批量打印")) {
                        tv_print.setText("取消");
                        for (int i = 0; i < beanList.size(); i++) {
                            beanList.get(i).setIsPrint(0);
                        }
                        bnt_print.setVisibility(View.VISIBLE);
                        timeDataAdapter.notifyDataSetChanged();
                    } else {
                        tv_print.setText("批量打印");
                        for (int i = 0; i < beanList.size(); i++) {
                            beanList.get(i).setIsPrint(-1);
                        }
                        bnt_print.setVisibility(View.GONE);
                        timeDataAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
        bnt_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beanListDy.size() == 0) {
                    ToastUtils.showShort("最少选择一条");
                    return;

                }
                startPrint();
            }
        });
    }

    private void startPrint() {
        Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.mipmap.banner_yellow)).getBitmap();
        ToolMdDetailsActivity.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //If the Bluetooth adapter is not supported,programmer is over
        if (ToolMdDetailsActivity.mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if (ToolMdDetailsActivity.printPP_cpcl == null) {
            ToolMdDetailsActivity.printPP_cpcl = new PrintPP_CPCL();
        }
        if (!ToolMdDetailsActivity.isConnected) {
            Intent serverIntent = new Intent(this, DeviceListActivity.class);
            startActivityForResult(serverIntent, ToolMdDetailsActivity.REQUEST_CONNECT_DEVICE);
        } else {
            new Thread(new Runnable() {
                int a = 0;
                @Override
                public void run() {
                    while (a<beanListDy.size()) {
                        try {
                            if (ToolMdDetailsActivity.isConnected) {
                                OrderSheetInfo list = new OrderSheetInfo();
                                OrderListBean.ExpressOrderDTOSBean data = beanListDy.get(a).getExpressOrderDTOS().get(0);
                                list.setOrderNo(data.getOrderNo());
                                list.setWebsiteName(beanListDy.get(a).getWebsiteName());
                                list.setConsigneeName(data.getExpressAddressDTOS().getToName());
                                list.setConsigneeMobile(data.getExpressAddressDTOS().getToMobile());
                                list.setConsigneeAddress(data.getExpressAddressDTOS().getToAddress());
                                list.setMailingName(data.getExpressAddressDTOS().getMailName());
                                list.setMailingMobile(data.getExpressAddressDTOS().getMailMobile());
                                list.setMailingAddress(data.getExpressAddressDTOS().getMailAddress());
                                list.setExpressName(data.getExpressDTO().getExpressName());
                                PrintLabel pl = new PrintLabel();

                                pl.Lable(ToolMdDetailsActivity.printPP_cpcl,bitmap ,list);
                            }
                            a++;
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }
    }

    @Override
    public void initData() {
        timeDataAdapter = new ToolMdAdapter(null);
        mRvData.setAdapter(timeDataAdapter);
        timeDataAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (timeDataAdapter.getData().get(position).getIsPrint() == 1) {
                    timeDataAdapter.getData().get(position).setIsPrint(0);
                    beanListDy.remove(timeDataAdapter.getData().get(position));
                } else {
                    beanListDy.add(timeDataAdapter.getData().get(position));
                    timeDataAdapter.getData().get(position).setIsPrint(1);
                }
                timeDataAdapter.notifyDataSetChanged();
            }
        });
        timeDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (tv_print.getText().toString().equals("取消")) {
                    return;
                }
                Intent mIntent = new Intent(ToolMdListActivity.this, ToolMdDetailsActivity.class);
                mIntent.putExtra("id", timeDataAdapter.getItem(position).getExpressOrderDTOS().get(0).getExpressOrderId() + "");
                startActivity(mIntent);
            }
        });

        refreshLoad();
    }

    private void refreshLoad() {

        refresh.setColorSchemeColors(getResources().getColor(R.color.theme_color));
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }
        });
        timeDataAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                loadData();
            }
        }, mRvData);
        timeDataAdapter.disableLoadMoreIfNotFullPage();
        loadData();
    }

    private void loadData() {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        map.put("orderType", 1);
        map.put("expressType", 1);
        map.put("orderStatus", 2);
        RetrofitManager.build().create(OrderService.class).myOrderList(map)
                .compose(RxScheduler.<BaseResponse<CoreOrderList>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<CoreOrderList>>bindLifecycle(this))
                .subscribe(new BaseObserver<CoreOrderList>() {
                    @Override
                    public void onSuccess(CoreOrderList coreOrderList) {
                        if (page == 1) {
                            beanList = coreOrderList.getList();
                            timeDataAdapter.setNewData(beanList);
                            if (refresh.isRefreshing()) {
                                refresh.setRefreshing(false);
                            }
                        } else {
                            if (coreOrderList.getList().size() == 0) {
                                timeDataAdapter.loadMoreEnd();
                            } else {
                                timeDataAdapter.loadMoreComplete();
                                beanList.addAll(coreOrderList.getList());
                                timeDataAdapter.addData(beanList);
                            }
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                        timeDataAdapter.loadMoreFail();
                        if (refresh.isRefreshing()) {
                            refresh.setRefreshing(false);
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ToolMdDetailsActivity.D) {
            Log.d(TAG, "onActivityResult " + resultCode);
        }
        switch (requestCode) {
            case ToolMdDetailsActivity.REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    if (ToolMdDetailsActivity.isConnected & (ToolMdDetailsActivity.printPP_cpcl != null)) {
                        ToolMdDetailsActivity.printPP_cpcl.disconnect();
                        ToolMdDetailsActivity.isConnected = false;
                    }
                    String sdata = data.getExtras()
                            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    ToolMdDetailsActivity.address = sdata.substring(sdata.length() - 17);
                    ToolMdDetailsActivity.name = sdata.substring(0, (sdata.length() - 17));
                    if (!ToolMdDetailsActivity.isConnected) {
                        if (ToolMdDetailsActivity.printPP_cpcl.connect(ToolMdDetailsActivity.name, ToolMdDetailsActivity.address)) {
                            ToolMdDetailsActivity.isConnected = true;
                            mTitle.setText(R.string.title_connected_to);
                            mTitle.append(ToolMdDetailsActivity.name);
                        } else {
                            ToolMdDetailsActivity.isConnected = false;
                        }
                    }
                }
                break;


        }
    }
}
//{"websiteNo":"990101",
// "websiteName":"鄞州钟公庙中心站",
// "parcelAffiliation":"宁波分拨包",
// "orderNo":"EO210312193917010039",
// "consigneeName":"程真","consigneeMobile":
// "13512345678","consigneeAddress":"浙江省-宁波市-海曙区,顺德华庭(宁波市海曙区顺德路318号)408",
// "mailingName":"程","mailingMobile":"15827313949",
// "mailingAddress":"浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁
// 波市-鄞州区,鄞州科技信息孵化园(宁波市鄞州区)203","expressName":"哦哦哦",
// "expressNum":"1","expressWeight":1.0}}


//{websiteNo='null', websiteName='程',
// parcelAffiliation='null',
// orderNo='EO210311191639002245',
// consigneeName='浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,鄞州科技信息孵化园(宁波市鄞州区)203',
// consigneeMobile='15827313949',
// consigneeAddress='浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,鄞州科技信息孵化园(宁波市鄞州区)203', mailingName='程真', mailingMobile='13512345678', mailingAddress='浙江省-宁波市-海曙区,顺德华庭(宁波市海曙区顺德路318号)408', expressName='手机', expressNum='null', expressWeight='null'}