package com.jx.xztongcheng.ui.activity;

import android.support.v7.widget.Toolbar;

import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;

import butterknife.BindView;

public class GywmActivity extends BaseActivity {

    @BindView(R.id.include)
    Toolbar myToolbar;


    @Override
    public int intiLayout() {
        return R.layout.activity_mine_regards;
    }

    @Override
    public void initView() {

        setToolbar(myToolbar, "关于我们", true);
    }

    @Override
    public void initData() {
    }
}
