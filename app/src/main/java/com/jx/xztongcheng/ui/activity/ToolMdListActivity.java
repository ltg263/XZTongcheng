package com.jx.xztongcheng.ui.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.response.CoreOrderList;
import com.jx.xztongcheng.bean.response.EmptyResponse;
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
    int page = 1, pageSize = 10;
    @BindView(R.id.tv_ydy)
    TextView mTvYdy;
    @BindView(R.id.tv_wdy)
    TextView mTvWdy;
    private List<OrderListBean> beanList;
    private List<OrderListBean> beanListDy = new ArrayList<>();
    ArrayList<String> expressOrderIds = new ArrayList<>();
    boolean isPrint = false;


    @Override
    public int intiLayout() {
        return R.layout.activity_tool_md_list;
    }

    @Override
    public void initView() {
//        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.theme_color));
        setToolbar(mToolbar, "????????????", true);
        mToolbar.setNavigationIcon(R.mipmap.icon_common_back);

        tv_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (beanList != null && beanList.size() > 0) {
                    beanListDy.clear();
                    if (tv_print.getText().toString().equals("????????????")) {
                        tv_print.setText("??????");
                        for (int i = 0; i < beanList.size(); i++) {
                            beanList.get(i).setIsPrint(0);
                        }
                        bnt_print.setVisibility(View.VISIBLE);
                        timeDataAdapter.notifyDataSetChanged();
                    } else {
                        tv_print.setText("????????????");
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
                    ToastUtils.showShort("??????????????????");
                    return;

                }
                expressOrderIds.clear();
                for(int i=0;i<beanListDy.size();i++){
                    expressOrderIds.add(beanListDy.get(i).getExpressOrderDTOS().get(0).getExpressOrderId()+"");
                    if(i==0){
                        if(!StringUtils.isEmpty(beanListDy.get(0).getExpressOrderDTOS().get(0).getAdvertisingImage())){
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    bitmapGg = ToolMdDetailsActivity.getBitmap(beanListDy.get(0).getExpressOrderDTOS().get(0).getAdvertisingImage());
                                }
                            }).start();
                        }
                    }
                }
                RetrofitManager.build().create(OrderService.class)
                        .updatePrintStatus(expressOrderIds)
                        .compose(RxScheduler.observeOnMainThread())
                        .as(RxScheduler.bindLifecycle(ToolMdListActivity.this))
                        .subscribe(new BaseObserver<EmptyResponse>() {
                            @Override
                            public void onSuccess(EmptyResponse emptyResponse) {
                                startPrint();
                            }
                        });
            }
        });

        mTvYdy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPrint) {
                    isPrint = true;
                    mTvYdy.setBackgroundColor(getResources().getColor(R.color.color_blue_theme));
                    mTvYdy.setTextColor(getResources().getColor(R.color.color_ffffff));
                    mTvWdy.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
                    mTvWdy.setTextColor(getResources().getColor(R.color.color_blue_theme));
                    tv_print.setText("????????????");
                    bnt_print.setVisibility(View.GONE);
                    page = 1;
                    showLoading();
                    loadData();
                }
            }
        });
        mTvWdy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPrint) {
                    isPrint = false;
                    mTvWdy.setBackgroundColor(getResources().getColor(R.color.color_blue_theme));
                    mTvWdy.setTextColor(getResources().getColor(R.color.color_ffffff));
                    mTvYdy.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
                    mTvYdy.setTextColor(getResources().getColor(R.color.color_blue_theme));
                    tv_print.setText("????????????");
                    bnt_print.setVisibility(View.GONE);
                    page = 1;
                    showLoading();
                    loadData();
                }
            }
        });
    }
    Bitmap bitmapGg =null;
    private void startPrint() {
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
                    while (a < beanListDy.size()) {
                        try {
                            if (ToolMdDetailsActivity.isConnected) {
                                OrderSheetInfo list = new OrderSheetInfo();
                                OrderListBean.ExpressOrderDTOSBean data = beanListDy.get(a).getExpressOrderDTOS().get(0);
                                list.setOrderNo(data.getOrderNo());
                                Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_logo)).getBitmap();
                                if(data.getExpressOrderId()!=1){
                                    bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_logo_c)).getBitmap();
                                }
//                                list.setWebsiteName(beanListDy.get(a).getWebsiteName());
                                list.setWebsiteName(data.getExpressAddressDTOS().getToWebsiteName());
                                list.setConsigneeName(data.getExpressAddressDTOS().getToName());
                                list.setConsigneeMobile(data.getExpressAddressDTOS().getToMobile());
                                list.setConsigneeAddress(data.getExpressAddressDTOS().getToAddress());
                                list.setMailingName(data.getExpressAddressDTOS().getMailName());
                                list.setMailingMobile(data.getExpressAddressDTOS().getMailMobile());
                                list.setMailingAddress(data.getExpressAddressDTOS().getMailAddress());
                                list.setExpressName(data.getExpressDTO().getExpressName());
                                list.setAdvertisingImage(data.getAdvertisingImage());
                                list.setWaybillNumber(data.getWaybillNumber());
                                list.setAmount(data.getMoney());
                                list.setType("1");
                                switch (data.getPayType()){
                                    case "CASH":
                                        list.setType("2");
                                        break;
                                    case "DESTINATION":
                                        list.setType("3");
                                        break;
                                    case "COLLECTING_MONEY":
                                        list.setType("4");
                                        break;

                                }
                                PrintLabel pl = new PrintLabel();
                                Bitmap bitmapY = ToolMdDetailsActivity.zoomImage(bitmap, 500, 70);
                                if(bitmapGg!=null){
                                    bitmapGg = ToolMdDetailsActivity.zoomImage(bitmapGg,540,80);
                                }
                                pl.Lable(ToolMdDetailsActivity.printPP_cpcl, bitmapY,bitmapGg, list);
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
                if (tv_print.getText().toString().equals("??????")) {
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

        showLoading();
        loadData();
    }

    private void loadData() {
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("pageSize", pageSize);
        map.put("orderType", 1);
        map.put("expressType", 1);
        map.put("orderStatus", 9);//1??????;2??????;3?????????;4??????;5??????;6??????
        if (isPrint) {
            map.put("isPrint", 1);
        } else {
            map.put("isPrint", 0);
        }
        RetrofitManager.build().create(OrderService.class).myOrderList(map)
                .compose(RxScheduler.<BaseResponse<CoreOrderList>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<CoreOrderList>>bindLifecycle(this))
                .subscribe(new BaseObserver<CoreOrderList>() {
                    @Override
                    public void onSuccess(CoreOrderList coreOrderList) {
                        hideLoading();
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
                        hideLoading();
                        timeDataAdapter.loadMoreFail();
                        if (refresh.isRefreshing()) {
                            refresh.setRefreshing(false);
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ToolMdDetailsActivity.isConnected ) {
            mTitle.setText(R.string.title_connected_to);
            mTitle.append(ToolMdDetailsActivity.name);
        }
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
// "websiteName":"????????????????????????",
// "parcelAffiliation":"???????????????",
// "orderNo":"EO210312193917010039",
// "consigneeName":"??????","consigneeMobile":
// "13512345678","consigneeAddress":"?????????-?????????-?????????,????????????(???????????????????????????318???)408",
// "mailingName":"???","mailingMobile":"15827313949",
// "mailingAddress":"?????????-?????????-?????????,?????????-?????????-?????????,?????????-?????????-?????????,?????????-?????????-?????????,?????????-???
// ??????-?????????,???????????????????????????(??????????????????)203","expressName":"?????????",
// "expressNum":"1","expressWeight":1.0}}


//{websiteNo='null', websiteName='???',
// parcelAffiliation='null',
// orderNo='EO210311191639002245',
// consigneeName='?????????-?????????-?????????,?????????-?????????-?????????,?????????-?????????-?????????,?????????-?????????-?????????,?????????-?????????-?????????,???????????????????????????(??????????????????)203',
// consigneeMobile='15827313949',
// consigneeAddress='?????????-?????????-?????????,?????????-?????????-?????????,?????????-?????????-?????????,?????????-?????????-?????????,?????????-?????????-?????????,???????????????????????????(??????????????????)203', mailingName='??????', mailingMobile='13512345678', mailingAddress='?????????-?????????-?????????,????????????(???????????????????????????318???)408', expressName='??????', expressNum='null', expressWeight='null'}