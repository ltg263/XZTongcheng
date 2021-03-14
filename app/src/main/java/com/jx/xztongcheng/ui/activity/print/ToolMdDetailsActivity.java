package com.jx.xztongcheng.ui.activity.print;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.response.OrderSheetInfo;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.qr.print.PrintPP_CPCL;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;


public class ToolMdDetailsActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar mToolbar;
    @BindView(R.id.title_right_text)
    TextView mTitle;
    @BindView(R.id.button_send)
    Button mSendButton;
    public static PrintPP_CPCL printPP_cpcl;
    public static final boolean D = true;
    public static boolean isConnected = false;
    public static final int REQUEST_CONNECT_DEVICE = 1;
    public static final int REQUEST_ENABLE_BT = 2;
    public static String address = "";
    public static String name = "";
    public static BluetoothAdapter mBluetoothAdapter = null;
    @BindView(R.id.tv_zdh)
    TextView mTvZdh;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tv_sjr_1)
    TextView mTvSjr1;
    @BindView(R.id.tv_jjr_1)
    TextView mTvJjr1;
    @BindView(R.id.tv_ddxx)
    TextView mTvDdxx;
    @BindView(R.id.tv_sjr_2)
    TextView mTvSjr2;
    @BindView(R.id.tv_jjnr)
    TextView mTvJjnr;
    @BindView(R.id.tv_sm1)
    TextView mTvSm1;
    @BindView(R.id.tv_gm1)
    TextView mTvGm1;
    @BindView(R.id.tv_sjr_3)
    TextView mTvSjr3;
    @BindView(R.id.tv_jjr_3)
    TextView mTvJjr3;
    @BindView(R.id.tv_jrsm)
    TextView mTvJrsm;
    @BindView(R.id.tv_sl)
    TextView mTvSl;
    @BindView(R.id.tv_zl)
    TextView mTvZl;
    @BindView(R.id.tv_websiteName)
    TextView tv_websiteName;
    @BindView(R.id.ll_bj)
    LinearLayout ll_bj;
    // Layout Views
    private int interval;
    private boolean isSending = false;
    OrderSheetInfo coreOrderList;
    @Override
    public int intiLayout() {
        return R.layout.activity_tool_md_details;
    }

    @Override
    public void initView() {
        setToolbar(mToolbar, "打印预览", true);
        mToolbar.setNavigationIcon(R.mipmap.icon_common_back);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //If the Bluetooth adapter is not supported,programmer is over
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        if (!isConnected) {
            Intent serverIntent = new Intent(this, DeviceListActivity.class);
            startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
        } else {
            isConnected = true;
            mTitle.setText(R.string.title_connected_to);
            mTitle.append(name);
        }
        if (printPP_cpcl == null) {
            printPP_cpcl = new PrintPP_CPCL();
        }
    }
    Bitmap bitmap;
    @Override
    public void initData() {

        RetrofitManager.build().create(OrderService.class)
                .myOrderBarcodeInfo(getIntent().getStringExtra("id"))
                .compose(RxScheduler.observeOnMainThread())
                .as(RxScheduler.bindLifecycle(this))
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        InputStream inputStream = responseBody.byteStream();//得到图片的流
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        mIv.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        RetrofitManager.build().create(OrderService.class).myOrderSheetInfo(getIntent().getStringExtra("id"))
                .compose(RxScheduler.<BaseResponse<OrderSheetInfo>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<OrderSheetInfo>>bindLifecycle(this))
                .subscribe(new BaseObserver<OrderSheetInfo>() {
                    @Override
                    public void onSuccess(OrderSheetInfo coreOrderList) {
//                        mTvZdh.setText(coreOrderList.getWebsiteNo());//站点
                        ToolMdDetailsActivity.this.coreOrderList = coreOrderList;
                        mTvZdh.setText("享指同城");//站点
                        tv_websiteName.setText(coreOrderList.getWebsiteName());
                        String sjyxx = coreOrderList.getConsigneeName()+"  "+coreOrderList.getConsigneeMobile()+"\n"+coreOrderList.getMailingAddress();
                        String jjyxx = coreOrderList.getMailingName()+"  "+coreOrderList.getMailingMobile()+"\n"+coreOrderList.getMailingAddress();
                        mTvSjr1.setText(sjyxx);//收件人
                        mTvJjr1.setText(jjyxx);//寄件人
                        mTvSjr2.setText(sjyxx);//收件人
//                        mTvJjr2.setText("李四 1311111112\n"+coreOrderList.getConsigneeAddress());//寄件人
                        mTvSjr3.setText(sjyxx);//收件人
                        mTvJjr3.setText(jjyxx);//寄件人

//                        mTvDdxx.setText("运单号：123456   □订单号：Ltp13245");
                        mTvDdxx.setText("运单编号号："+coreOrderList.getOrderNo());
                        mTvJjnr.setText(coreOrderList.getExpressName());//"内容品名"
                        mTvJrsm.setText(coreOrderList.getExpressName());//"内容品名"
                        mTvSm1.setText("数量："+coreOrderList.getExpressNum());
                        mTvSl.setText("数量："+coreOrderList.getExpressNum());
                        mTvZl.setText("重量："+coreOrderList.getExpressWeight()+"kg");
                        mTvGm1.setText("重量："+coreOrderList.getExpressWeight()+"kg");
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                    }
                });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // If BT is not on, request that it be enabled
//        // setupChat() will then be called during onActivityRe//sultsetupChat
//        if (!mBluetoothAdapter.isEnabled()) {
//            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
//        }
//
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (D) {
            Log.d(TAG, "onActivityResult " + resultCode);
        }
        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {
                    if (isConnected & (printPP_cpcl != null)) {
                        printPP_cpcl.disconnect();
                        isConnected = false;
                    }
                    String sdata = data.getExtras()
                            .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                    address = sdata.substring(sdata.length() - 17);
                    name = sdata.substring(0, (sdata.length() - 17));
                    if (!isConnected) {
                        if (printPP_cpcl.connect(name, address)) {
                            isConnected = true;
                            mTitle.setText(R.string.title_connected_to);
                            mTitle.append(name);
                        } else {
                            isConnected = false;
                        }
                    }

                }
                break;
            case REQUEST_ENABLE_BT:

        }
    }

    @OnClick({R.id.search, R.id.button_send, R.id.button_dk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                Intent serverIntent = new Intent(ToolMdDetailsActivity.this, DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
                break;
            case R.id.button_dk:
                printPP_cpcl.disconnect();
                if (!printPP_cpcl.isConnected()) {
                    isConnected = false;
                    mTitle.setText("");
                }
                break;
            case R.id.button_send:
                if (!isSending &&coreOrderList !=null &&bitmap!=null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            isSending = true;
                            if (isConnected) {
                                PrintLabel pl = new PrintLabel();
                                pl.Lable(printPP_cpcl,coreOrderList);
                            }
                            try {
                                interval = 0;
                                Thread.sleep(interval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            isSending = false;
                        }
                    }).start();
                }
                break;
        }
    }
}


