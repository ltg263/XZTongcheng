package com.jx.xztongcheng.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Create by Sxl on 2020/11/24.
 */
public class GetLogLatUtils {

    public static Location getLocation(final Context context) {
        return getLastKnownLocation();
    }

    /**
     * 定位：得到位置对象
     * @return
     */
    private static Location getLastKnownLocation() {
        LogUtils.e("GetLogLatUtils：获取定位权限1 - 开始");
        //获取地理位置管理器
        LocationManager mLocationManager = (LocationManager) Utils.getApp().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        for (String provider : providers) {
            @SuppressLint("MissingPermission") Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        if (bestLocation != null) {
            //日志
            String locationStr = "维度：" + bestLocation.getLatitude() + "\n"
                    + "经度：" + bestLocation.getLongitude();
            LogUtils.e("GetLogLatUtils：经纬度：" + locationStr);
        } else {
            LogUtils.e("GetLogLatUtils：获取定位权限7 - " + "位置获取失败");
        }
        return bestLocation;
    }
}
