package com.jx.xztongcheng.utils;

import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.iflytek.cloud.Setting;

import java.lang.reflect.Field;


/**
 * Created by ShiXL on 2018/1/26.
 */

public class CommonUtils {

    //需要定义一个全局变量 lastClickTime, 用来记录最后点击的时间.
    //每次点击前需要进行判断, 用lastClickTime 和当前时间想比较，并且更新最后点击时间，若小于临界值，则算无效点击，不触发事件
    private static long lastClickTime = 0;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 800) {
            return true;
        }

        lastClickTime = time;

        return false;
    }

    public static boolean isSlowDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 2000) {
            return true;
        }

        lastClickTime = time;

        return false;
    }

    public static void reflex(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = ConvertUtils.dp2px( 10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        int tabWidth = tabView.getWidth();
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        int padding = (tabWidth-width)/5*2;

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = ConvertUtils.dp2px( 46);
                        params.width = width;
                        params.leftMargin = padding-2;
                        params.rightMargin = padding-2;

                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void reflex2(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

//                    int dp10 = DisplayUtil.dp2px(tabLayout.getContext(), 10);
//                    Point p = ScreenUtils.getScreenSize(tabLayout.getContext());
//                    int nowWidth = p.x;
                    int nowWidth = ScreenUtils.getScreenWidth();

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        int tabWidth = tabView.getWidth();
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        int padding = (tabWidth-width)/5*2;

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = ConvertUtils.dp2px( 46);
                        params.width = width;
                        params.leftMargin = padding-2;
                        params.rightMargin = padding-2;
                        if (i == mTabStrip.getChildCount()-1) {
                            params.width = width+padding;
                            params.leftMargin = padding-2;
                            params.rightMargin = padding-2;
                            params.weight = ((float) nowWidth/(float) nowWidth*(float) mTabStrip.getChildCount());
                        }
//                       if (  tabWidth < width+(padding-2)*2  ) {
//                           params.width = width+padding-2;
//                           params.leftMargin = 0;
//                           params.rightMargin = 0;
//                       }

                        nowWidth = nowWidth - (padding-2)*2 - width; //屏幕剩余宽度
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static String getAndroid_Id(){
       String id = Settings.Secure.getString(Utils.getApp().getContentResolver(),Settings.Secure.ANDROID_ID);
        Log.w("---->>","getAndroid_Id："+id);
       return id==null?"":id;
    }

}
