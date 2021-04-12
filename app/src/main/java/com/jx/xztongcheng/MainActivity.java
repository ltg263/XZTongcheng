package com.jx.xztongcheng;

import android.content.Intent;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.bean.clazz.JWebSocketResp;
import com.jx.xztongcheng.bean.event.ExpressEvent;
import com.jx.xztongcheng.bean.event.LoginEvent;
import com.jx.xztongcheng.ui.fragment.HomeFragment;
import com.jx.xztongcheng.ui.fragment.MineFragment;
import com.jx.xztongcheng.utils.CommonUtils;
import com.jx.xztongcheng.utils.DialogHelper;
import com.jx.xztongcheng.utils.GetLogLatUtils;
import com.jx.xztongcheng.utils.PermissionHelper;
import com.jx.xztongcheng.utils.TTSUtils;
import com.luck.picture.lib.config.PictureConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements JWebSocketClient.WebSocketConnectListener {

    @BindView(R.id.bottomView)
    BottomNavigationViewEx bottomView;

    HomeFragment homeFragment;
    MineFragment mineFragment;

    Fragment mFragment;
    AMapLocation aMapLocation = null;

    public JWebSocketClient jWebSocketClient;

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=60042f2b");
//        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=60042f2b"+ SpeechConstant.FORCE_LOGIN + "=true");
        EventBus.getDefault().register(this);
        bottomView.enableAnimation(false);
        bottomView.enableShiftingMode(false);
        bottomView.enableItemShiftingMode(false);

        initBottomBar();
        getLocation();

        TTSUtils.getInstance().init();

        jWebSocketClient = new JWebSocketClient();
        jWebSocketClient.setWebSocketConnectListener(this);
        jWebSocketClient.doConnect();


        DialogHelper.getVersionUpdating(this, new DialogHelper.UploadFileInterface() {
            @Override
            public void succeed(String path) {

            }

            @Override
            public void failure() {

            }
        });

    }

    private void getLocation() {
        PermissionHelper.requestLocation(new PermissionHelper.OnPermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                Location location = GetLogLatUtils.getLocation(MainActivity.this);
                if (location != null) {
                    App.lat = location.getLatitude();
                    App.lon = location.getLongitude();
                }
//                //初始化定位
//                mLocationClient = new AMapLocationClient(MainActivity.this);
//                //设置定位回调监听
//                mLocationClient.setLocationListener(mLocationListener);
//
//                //初始化AMapLocationClientOption对象
//                mLocationOption = new AMapLocationClientOption();
//                //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
//                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
//
//                mLocationOption.setMockEnable(true);
//                //给定位客户端对象设置定位参数
//                mLocationClient.setLocationOption(mLocationOption);
//
//                mLocationClient.startLocation();
            }
        });

    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                amapLocation.setCityCode(String.valueOf(Integer.parseInt(amapLocation.getAdCode()) / 100 * 100));
                aMapLocation = amapLocation;
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    double locationType = amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    App.lat = amapLocation.getLatitude();
                    App.lon = amapLocation.getLongitude();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
                mLocationClient.stopLocation();
            }
        }
    };
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    public void initData() {

    }

    private void initBottomBar() {
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frameLayout, homeFragment).commit();

        mFragment = homeFragment;

        bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab1:
                        switchFragment(homeFragment);
                        break;
                    case R.id.tab2:
                        mineFragment.refreshData();
                        switchFragment(mineFragment);
                        break;
                    default:
                }
                return true;
            }
        });

        setCurrent(0);

    }

    public void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if (mFragment != fragment) {
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(mFragment).add(R.id.frameLayout, fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(mFragment).show(fragment).commit();
            }
            mFragment = fragment;
        }
    }

    public void setCurrent(int item) {
        bottomView.setCurrentItem(item);
    }

    public BottomNavigationViewEx getBottomView() {
        return bottomView;
    }

    @Override
    public void onBackPressed() {
        ToastUtils.showShort("再按一次退出程序");
//        TTSUtils.getInstance().speak("再按一次退出程序");
        if (CommonUtils.isSlowDoubleClick()) {
            this.finish();
            System.exit(0);
        } else {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        jWebSocketClient.close();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginEvent(LoginEvent loginEvent) {
        if (mineFragment != null) {
            mineFragment.refreshData();
        }
//        if (jWebSocketClient != null) {
//            jWebSocketClient.close();
//            jWebSocketClient = null;
//        }
//        jWebSocketClient = new JWebSocketClient();
//        jWebSocketClient.setWebSocketConnectListener(this);
//        jWebSocketClient.doConnect();
    }


    @Override
    public void onClose() {
        LogUtils.eTag("WebSocket","onClose");
    }

    @Override
    public void onConnect() {
        LogUtils.eTag("WebSocket","onConnect");

    }

    @Override
    public void authFinish(int status) {

    }

    @Override
    public void refreshData(String resp, int type) {
        JWebSocketResp socketResp = new Gson().fromJson(resp, JWebSocketResp.class);
        Log.w("socketResp.getAction","socketResp.getAction()"+socketResp.getAction());
        String ss = null;
        if (socketResp.getAction() == 4) {
            ToastUtils.showShort("您有新的指派订单");
            ss = "您有新的指派订单";
        } else if (socketResp.getAction() == 5) {
            ToastUtils.showShort("您的转单已被受理");
            ss = "您的转单已被受理";
        } else if (socketResp.getAction() == 6) {
            ToastUtils.showShort("您的转单失败");
            ss = "您的转单失败";
        } else if (socketResp.getAction() == 7) {
            ToastUtils.showShort("您有新的转单订单");
            ss = "您有新的转单订单";
        } else if (socketResp.getAction() == 8) {
            ToastUtils.showShort("您有新的订单");
            ss = "您有新的订单";
        }
        if (!StringUtils.isEmpty(ss)) {
            TTSUtils.getInstance().speak(ss);
        }
        EventBus.getDefault().post(new ExpressEvent());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case PictureConfig.CHOOSE_REQUEST:
                        mineFragment.updateAvatar(data);
                        break;
                    default:
                }
                break;
        }
    }
}
