package com.jx.xztongcheng.ui.activity.print;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.Glide;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.bean.response.OrderSheetInfo;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.ui.activity.ToolMdListActivity;
import com.jx.xztongcheng.utils.GlideImageLoader;
import com.qr.print.PrintPP_CPCL;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

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
    @BindView(R.id.tv_xf)
    TextView mTvXf;
    @BindView(R.id.tv_daif)
    TextView mTvDaif;
    @BindView(R.id.tv_df)
    TextView mTvDf;
    @BindView(R.id.tv_ydh)
    TextView tv_ydh;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.iv_gg)
    ImageView iv_gg;

    // Layout Views
    private int interval;
    private boolean isSending = false;
    OrderSheetInfo coreOrderList;

    ArrayList<String> expressOrderIds = new ArrayList<>();

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
        expressOrderIds.clear();
        expressOrderIds.add(getIntent().getStringExtra("id"));
    }

    Bitmap bitmapR;
    Bitmap bitmapGg;

    @Override
    public void initData() {

        bitmapR = ((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_logo)).getBitmap();
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
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
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
                        mTvZdh.setText("热线电话：400-6898-588");//站点
                        tv_websiteName.setText(coreOrderList.getWebsiteName());
                        String sjyxx = coreOrderList.getConsigneeName() + "  " + coreOrderList.getConsigneeMobile() + "\n" + coreOrderList.getMailingAddress();
                        String jjyxx = coreOrderList.getMailingName() + "  " + coreOrderList.getMailingMobile() + "\n" + coreOrderList.getMailingAddress();
                        mTvSjr1.setText(sjyxx);//收件人
                        mTvJjr1.setText(jjyxx);//寄件人
                        mTvSjr2.setText(sjyxx);//收件人
//                        mTvJjr2.setText("李四 1311111112\n"+coreOrderList.getConsigneeAddress());//寄件人
                        mTvSjr3.setText(sjyxx);//收件人
                        mTvJjr3.setText(jjyxx);//寄件人
                        switch (coreOrderList.getType()){//1线上(不用操作)2现金支付3到付4待收货跨
                            case "1":
                                break;
                            case "2":
                                mTvXf.setText("现付："+coreOrderList.getAmount()+"元");
                                break;
                            case "3":
                                mTvXf.setText("到付："+coreOrderList.getAmount()+"元");
                                break;
                            case "4":
                                mTvXf.setText("代收："+coreOrderList.getAmount()+"元");
                                break;
                        }
                        tv_ydh.setText(coreOrderList.getWaybillNumber());
//                        mTvDdxx.setText("运单号：123456   □订单号：Ltp13245");
                        mTvDdxx.setText("运单编号号：" + coreOrderList.getOrderNo());
                        mTvJjnr.setText(coreOrderList.getExpressName());//"内容品名"
                        mTvJrsm.setText(coreOrderList.getExpressName());//"内容品名"
                        mTvSm1.setText("数量：" + coreOrderList.getExpressNum());
                        mTvSl.setText("数量：" + coreOrderList.getExpressNum());
                        mTvZl.setText("重量：" + coreOrderList.getExpressWeight() + "kg");
                        mTvGm1.setText("重量：" + coreOrderList.getExpressWeight() + "kg");
                        tv_time.setText(PrintLabel.getTimeToYMD(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"));
                        if(!StringUtils.isEmpty(coreOrderList.getAdvertisingImage())){
                            bitmapGg = getBitmap(coreOrderList.getAdvertisingImage());
                            Glide.with(ToolMdDetailsActivity.this).load(coreOrderList.getAdvertisingImage())
                                    .into(iv_gg);
                        }
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                    }
                });
    }

    public static Bitmap getBitmap(String url) {
        Bitmap bm = null;
        try {
            URL iconUrl = new URL(url);
            URLConnection conn = iconUrl.openConnection();
            HttpURLConnection http = (HttpURLConnection) conn;

            int length = http.getContentLength();

            conn.connect();
            // 获得图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();// 关闭流
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }

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
                RetrofitManager.build().create(OrderService.class)
                        .updatePrintStatus(expressOrderIds)
                        .compose(RxScheduler.observeOnMainThread())
                        .as(RxScheduler.bindLifecycle(this))
                        .subscribe(new BaseObserver<EmptyResponse>() {
                            @Override
                            public void onSuccess(EmptyResponse emptyResponse) {
                                startPrint();
                            }
                        });
                break;
        }
    }

    private void startPrint() {
        if (!isSending && coreOrderList != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    isSending = true;
                    if (isConnected) {
                        PrintLabel pl = new PrintLabel();
                        bitmapR = zoomImage(bitmapR,540,70);
                        bitmapGg = zoomImage(bitmapGg,540,70);
                        pl.Lable(printPP_cpcl, bitmapR,bitmapGg, coreOrderList);
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
    }

    public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }
}


