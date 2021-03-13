package com.jx.xztongcheng.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;


import com.jx.xztongcheng.R;
import com.jx.xztongcheng.utils.LoadingDialog;

import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2018/8/25.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    private LoadingDialog mLoading;

    protected String tag = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //设置布局
        setTheme(R.style.AppTheme);//恢复原有的样式
        setContentView(intiLayout());
        ButterKnife.bind(this);

        //初始化控件
        initView();
        //设置数据
        initData();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int intiLayout();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

    public void readyGoActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    protected void setStatusBar() {
//        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.theme_color));
    }

    public void setToolbar(Toolbar mToolbar, String title) {
        this.setToolbar(mToolbar, title, true);
    }

    public void setToolbar(Toolbar mToolbar, String title, Boolean isBack) {
        TextView mViewToolBarTitle = mToolbar.findViewById(R.id.toolbar_title);
        mViewToolBarTitle.setText(title);
        if (isBack) {
            mToolbar.setNavigationIcon(R.mipmap.icon_common_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    public void showLoading() {
        if (mLoading != null && !mLoading.isShowing()) {
            mLoading.show();
        } else {
            mLoading = LoadingDialog.show(this, R.string.loading_text, false, null);
        }
    }

    public void showLoading(String name) {
        if (mLoading != null && !mLoading.isShowing()) {
            mLoading.show();
        } else {
            mLoading = LoadingDialog.show(this, name, false, null);
        }
    }


    public void hideLoading() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }

    /**
     * 点击屏幕隐藏软键盘方法
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    //处理Editext的光标隐藏、显示逻辑
                    v.clearFocus();
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
    }

}
