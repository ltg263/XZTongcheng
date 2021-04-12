package com.jx.xztongcheng.utils;

import android.os.CountDownTimer;
import androidx.core.content.ContextCompat;
import android.widget.TextView;

import com.jx.xztongcheng.R;

/**
 * Create by Sxl on 2020/11/20.
 */
public class CountDownTimerUtils extends CountDownTimer {
    private TextView mTextView;

    public CountDownTimerUtils(TextView textView, long millisInFuture) {
        super(millisInFuture, 1000);
        this.mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false); //设置不可点击
        mTextView.setTextColor(ContextCompat.getColor(mTextView.getContext(), R.color.colorButtonDisable));
        mTextView.setText(millisUntilFinished / 1000 + "秒后重新获取"); //设置倒计时时间
    }

    @Override
    public void onFinish() {
        mTextView.setText("再次获取");
        mTextView.setTextColor(ContextCompat.getColor(mTextView.getContext(), R.color.color_blue_theme));
        mTextView.setClickable(true);//重新获得点击
    }
}
