package com.jx.xztongcheng.ui.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.base.BaseFragment;
import com.jx.xztongcheng.ui.fragment.ExpressManageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ExpressManageActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.include)
    Toolbar mToolbar;

    private List<BaseFragment> mFragments;
    private String[] mTitle = new String[5];
    private int expressType, fastStatus;


    @Override
    public int intiLayout() {
        return R.layout.activity_express_manage;
    }

    @Override
    public void initView() {
        expressType = getIntent().getIntExtra("expressType", 1);
        fastStatus = getIntent().getIntExtra("fastStatus", 0);
        if (expressType == 2) {
            setToolbar(mToolbar, "指派件管理", true);
        } else {
            setToolbar(mToolbar, "快件管理", true);
        }
        if (fastStatus == 1) {
            setToolbar(mToolbar, "急件管理", true);
        }

        mFragments = new ArrayList<>();
        mFragments.add(ExpressManageFragment.newInstance(2, 1, expressType,fastStatus));
        mFragments.add(ExpressManageFragment.newInstance(0, 2, expressType,fastStatus));
        mFragments.add(ExpressManageFragment.newInstance(3, 2, expressType,fastStatus));
        mFragments.add(ExpressManageFragment.newInstance(4, 2, expressType,fastStatus));
        mFragments.add(ExpressManageFragment.newInstance(6, 2, expressType,fastStatus));
        mTitle[0] = "最新";
        mTitle[1] = "全部";
        mTitle[2] = "进行中";
        mTitle[3] = "已收件";
        mTitle[4] = "取消";
        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mTitle.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle[position];
            }
        });

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(mFragments.size());

        viewPager.setCurrentItem(getIntent().getIntExtra("index", 0));
    }

    @Override
    public void initData() {

    }
}
