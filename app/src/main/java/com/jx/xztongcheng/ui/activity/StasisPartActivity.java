package com.jx.xztongcheng.ui.activity;

import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.ViewGroup;

import com.blankj.utilcode.util.BarUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseActivity;
import com.jx.xztongcheng.base.BaseFragment;
import com.jx.xztongcheng.ui.fragment.StatisticsPartFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StasisPartActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;

    private List<BaseFragment> mFragments;
    private String[] mTitle = new String[3];

    @Override
    public int intiLayout() {
        return R.layout.activity_stasis_part;
    }

    @Override
    public void initView() {
        BarUtils.setStatusBarColor(this, getResources().getColor(R.color.theme_color));
        setToolbar(mToolbar, "数量统计", true);
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mToolbar.getLayoutParams();
        params.topMargin = BarUtils.getStatusBarHeight();
        mFragments = new ArrayList<>();
        mFragments.add(StatisticsPartFragment.newInstance(1));
        mFragments.add(StatisticsPartFragment.newInstance(2));
        mFragments.add(StatisticsPartFragment.newInstance(3));
        mTitle[0] = "收件";
        mTitle[1] = "收入";
        mTitle[2] = "客户数";
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
