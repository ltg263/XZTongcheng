package com.jx.xztongcheng;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.StringUtils;
import com.jx.xztongcheng.app.App;


/**
 * Create by Sxl on 2020/3/4.
 */
public class MapService extends Service {

    private LocalBinder mLocalBinder = new LocalBinder();
    private Activity activity;
    AMapLocation aMapLocation;

    private String TAG = "MapService";
    //声明AMapLocationClient类对象
    AMapLocationClient mLocationClient = null;
    // 声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    String lngAndLat;


    @Override
    public void onCreate() {
        super.onCreate();
        getPosition();

    }

    public void getPosition() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        // 设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        // 初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        // 设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(1000);
        // 获取一次定位结果： //该方法默认为false。
        mLocationOption.setOnceLocation(false);
        mLocationOption.setOnceLocationLatest(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        // 启动定位
        mLocationClient.startLocation();
    }

    // 声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation == null) {
                Log.i(TAG, "amapLocation is null!");
                return;
            }
            if (amapLocation.getErrorCode() != 0) {
                Log.i(TAG, "amapLocation has exception errorCode:" + amapLocation.getErrorCode());
                return;
            }
            aMapLocation = amapLocation;
            Double longitude = amapLocation.getLongitude();//获取经度
            Double latitude = amapLocation.getLatitude();//获取纬度
            String longitudestr = String.valueOf(longitude);
            String latitudestr = String.valueOf(latitude);
            Log.i(TAG, "longitude:" + longitude + ",latitude：" + latitude);


            if (amapLocation != null) {
                amapLocation.setCityCode(String.valueOf(Integer.parseInt(amapLocation.getAdCode()) / 100 * 100));
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
//                    double locationType = amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                    lngAndLat = String.valueOf(amapLocation.getLongitude()) + "," + amapLocation.getLatitude();//获取纬度
//                    SPUtils.getInstance().put(ConstValues.LAT, (float) amapLocation.getLatitude());
//                    SPUtils.getInstance().put(ConstValues.LON, (float) amapLocation.getLongitude());
//                    SPUtils.getInstance().put("localCity", amapLocation.getCity());
//                    Log.e("Amap==经度：纬度", "locationType:" + locationType + ",lngAndLat:" + lngAndLat);
//                    EventBus.getDefault().post(new CityEvent(amapLocation.getCity()));
//                    ConstValues.sAddressMap.setCityId(Integer.parseInt(amapLocation.getCityCode()));

                    App.getInstance().lat = amapLocation.getLatitude();
                    App.getInstance().lon = amapLocation.getLongitude();

//                    getQueryCity();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
//                    getQueryCity();
                }
                if (StringUtils.isEmpty(lngAndLat)) {
                    lngAndLat = "121.158731,30.060169";
                }
            }
        }
    };

    AlertDialog cityDialog;


    public class LocalBinder extends Binder {
        public MapService getService(Activity mActivity) {
            activity = mActivity;
            return MapService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBinder;
    }

}
