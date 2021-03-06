package com.jx.xztongcheng.base;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jx.xztongcheng.R;
import com.jx.xztongcheng.utils.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;

/**
 * Created by Administrator on 2018/8/25.
 */

public abstract class BaseFragment extends Fragment {

    private View mContentView;
    public Context mContext;
    Unbinder unbinder;

    protected final String TAG = this.getClass().getSimpleName();
    private LoadingDialog mLoading;

    public BaseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContentView = inflater.inflate(setLayoutResourceID(),container,false);
        mContext = getActivity();
        unbinder = ButterKnife.bind(this, mContentView);
        initView();
        return mContentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID
     *
     * @return 布局文件资源ID
     */
    protected abstract int setLayoutResourceID();

    protected abstract void initView();

    protected abstract void initData();



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void showLoading() {
        if (mLoading != null && !mLoading.isShowing()) {
            mLoading.show();
        } else {
            mLoading = LoadingDialog.show(mContext, R.string.loading_text, false, null);
        }
    }

    public void hideLoading() {
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }

}
