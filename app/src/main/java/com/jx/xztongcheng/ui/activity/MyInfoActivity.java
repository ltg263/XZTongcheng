package com.jx.xztongcheng.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.utils.CircleImageView;

import butterknife.BindView;

public class MyInfoActivity extends BaseActivity {

    @BindView(R.id.head_icon)
    CircleImageView headIcon;
    @BindView(R.id.include)
    Toolbar myToolbar;

    @Override
    public int intiLayout() {
        return R.layout.activity_my_info;
    }

    @Override
    public void initView() {
        setToolbar(myToolbar, "个人信息", true);
    }

    @Override
    public void initData() {

    }
}
