package com.jx.xztongcheng.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.ui.activity.NameAuthenticationActivity;

/**
 * Create by Sxl on 2020/12/1.
 */
public class FullScreenDialog extends Dialog {
    private OnDialogClickListener mListener;


    public FullScreenDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        initView(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.height = d.getHeight();
        p.width = d.getWidth();
        getWindow().setAttributes(p);
    }

    public void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_name_verify, null, false);
        view.findViewById(R.id.tv_verify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.startActivity(NameAuthenticationActivity.class);
            }
        });
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        setContentView(view);
    }

    public void setOnDialogClickListener(OnDialogClickListener listener) {
        mListener = listener;
    }

    public interface OnDialogClickListener {

        void onBtn1Click();

        void onBtn2Click();
    }

}