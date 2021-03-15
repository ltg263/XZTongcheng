// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StatisticsPartFragment_ViewBinding implements Unbinder {
  private StatisticsPartFragment target;

  private View view2131231237;

  private View view2131231199;

  private View view2131231241;

  private View view2131231157;

  private View view2131231173;

  @UiThread
  public StatisticsPartFragment_ViewBinding(final StatisticsPartFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_week, "field 'tvWeek' and method 'onViewClicked'");
    target.tvWeek = Utils.castView(view, R.id.tv_week, "field 'tvWeek'", TextView.class);
    view2131231237 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_month, "field 'tvMonth' and method 'onViewClicked'");
    target.tvMonth = Utils.castView(view, R.id.tv_month, "field 'tvMonth'", TextView.class);
    view2131231199 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_year, "field 'tvYear' and method 'onViewClicked'");
    target.tvYear = Utils.castView(view, R.id.tv_year, "field 'tvYear'", TextView.class);
    view2131231241 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rvData = Utils.findRequiredViewAsType(source, R.id.rv_data, "field 'rvData'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tv_begin_time, "field 'tvBeginTime' and method 'onViewClicked'");
    target.tvBeginTime = Utils.castView(view, R.id.tv_begin_time, "field 'tvBeginTime'", TextView.class);
    view2131231157 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_end_time, "field 'tvEndTime' and method 'onViewClicked'");
    target.tvEndTime = Utils.castView(view, R.id.tv_end_time, "field 'tvEndTime'", TextView.class);
    view2131231173 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvValue = Utils.findRequiredViewAsType(source, R.id.tv_value, "field 'tvValue'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StatisticsPartFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvWeek = null;
    target.tvMonth = null;
    target.tvYear = null;
    target.rvData = null;
    target.tvBeginTime = null;
    target.tvEndTime = null;
    target.tvValue = null;

    view2131231237.setOnClickListener(null);
    view2131231237 = null;
    view2131231199.setOnClickListener(null);
    view2131231199 = null;
    view2131231241.setOnClickListener(null);
    view2131231241 = null;
    view2131231157.setOnClickListener(null);
    view2131231157 = null;
    view2131231173.setOnClickListener(null);
    view2131231173 = null;
  }
}
