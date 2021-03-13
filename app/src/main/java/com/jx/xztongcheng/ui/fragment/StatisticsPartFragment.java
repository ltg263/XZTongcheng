package com.jx.xztongcheng.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.ToastUtils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.base.BaseFragment;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.bean.response.StatisResponse;
import com.jx.xztongcheng.net.BaseObserver;
import com.jx.xztongcheng.net.BaseResponse;
import com.jx.xztongcheng.net.RetrofitManager;
import com.jx.xztongcheng.net.RxScheduler;
import com.jx.xztongcheng.net.service.OrderService;
import com.jx.xztongcheng.ui.adpter.TimeDataAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsPartFragment extends BaseFragment {

    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.rv_data)
    RecyclerView rvData;
    @BindView(R.id.tv_begin_time)
    TextView tvBeginTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_value)
    TextView tvValue;

    private TimeDataAdapter timeDataAdapter;
    private int type = 1;
    Calendar mCalendar;

    SimpleDateFormat mDateFormat;
    SimpleDateFormat mDateFormatMonth;
    SimpleDateFormat mDateFormatYear;
    private Date beginDate, endDate;
    private String beginDaeString = null, endDataString = null;


    public StatisticsPartFragment() {
        // Required empty public constructor
    }

    public static StatisticsPartFragment newInstance(int type) {
        StatisticsPartFragment fragment = new StatisticsPartFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_statistics_part;
    }

    @Override
    protected void initView() {
        type = getArguments().getInt("type");
        if (type == 1) {
            tvValue.setText("收件量");
        } else if (type == 2) {
            tvValue.setText("收入");
        } else {
            tvValue.setText("客户数");
        }
        mCalendar = Calendar.getInstance();
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        mDateFormatMonth = new SimpleDateFormat("yyyy-MM");
        mDateFormatYear = new SimpleDateFormat("yyyy");
//        tvBeginTime.setText(mDateFormat.format(mCalendar.getTime()));
//        tvEndTime.setText(mDateFormat.format(mCalendar.getTime()));

        endDate = mCalendar.getTime();
        mCalendar.add(Calendar.DAY_OF_MONTH, -7);
        beginDate = mCalendar.getTime();

        timeDataAdapter = new TimeDataAdapter(null);
        rvData.setAdapter(timeDataAdapter);
    }

    private void dateCheck(TextView textView, int beginOrEnd) {
        boolean[] types;
        SimpleDateFormat dateFormatPick;

//        if (type == 1) {
        types = new boolean[]{true, true, true, false, false, false};
        dateFormatPick = new SimpleDateFormat("yyyy-MM-dd");
//        } else if (type == 2) {
//            types = new boolean[]{true, true, false, false, false, false};
//            dateFormatPick = new SimpleDateFormat("yyyy-MM");
//        } else {
//            types = new boolean[]{true, false, false, false, false, false};
//            dateFormatPick = new SimpleDateFormat("yyyy");
//        }
        TimePickerView timePickerView = new TimePickerBuilder(getActivity(), (date, v1) -> {//选中事件回调
            textView.setText(dateFormatPick.format(date));
            if (beginOrEnd == 1) {
                beginDaeString = date.toString();
                beginDate = date;
                if (!TextUtils.isEmpty(endDataString)) {
                    loadData(4);
                }
            } else {
                endDataString = date.toString();
                endDate = date;
                if (!TextUtils.isEmpty(beginDaeString)) {
                    loadData(4);
                }
            }
        })
                .setType(types)// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText("选择时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        timePickerView.show();
    }

    @Override
    protected void initData() {
        loadData(2);
    }

    private void loadData(int dataType) {
        Observable<BaseResponse<StatisResponse>> observable;
        if (type == 1) {
            observable = RetrofitManager.build().create(OrderService.class).orderStatistic(dataType, beginDaeString, endDataString);
        } else if (type == 2) {
            observable = RetrofitManager.build().create(OrderService.class).incomeStatistic(dataType, beginDaeString, endDataString);
        } else {
            observable = RetrofitManager.build().create(OrderService.class).customerStatistic(dataType, beginDaeString, endDataString);
        }
        observable
                .compose(RxScheduler.<BaseResponse<StatisResponse>>observeOnMainThread())
                .as(RxScheduler.<BaseResponse<StatisResponse>>bindLifecycle(getActivity()))
                .subscribe(new BaseObserver<StatisResponse>() {
                    @Override
                    public void onSuccess(StatisResponse coreOrderList) {
                        timeDataAdapter.setNewData(coreOrderList.getStatisticalGraphDTOList());
                    }

                    @Override
                    public void onFail(int code, String msg) {
                        super.onFail(code, msg);
                    }
                });
    }

    @OnClick({R.id.tv_begin_time, R.id.tv_end_time, R.id.tv_week, R.id.tv_month, R.id.tv_year})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_begin_time:
                dateCheck(tvBeginTime, 1);
                break;
            case R.id.tv_end_time:
                dateCheck(tvEndTime, 2);
                break;
            case R.id.tv_week:
                setUI(tvWeek, tvMonth, tvYear);
                break;
            case R.id.tv_month:
                setUI(tvMonth, tvWeek, tvYear);
                break;
            case R.id.tv_year:
                setUI(tvYear, tvMonth, tvWeek);
                break;
        }
    }

    private void setUI(TextView view1, TextView view2, TextView view3) {
        view1.setTextColor(getResources().getColor(R.color.white));
        view2.setTextColor(getResources().getColor(R.color.color_666666));
        view3.setTextColor(getResources().getColor(R.color.color_666666));
        view1.setBackgroundColor(getResources().getColor(R.color.theme_color));
        view2.setBackgroundColor(getResources().getColor(R.color.white));
        view3.setBackgroundColor(getResources().getColor(R.color.white));
    }
}
