// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MultiExpressActivity_ViewBinding implements Unbinder {
  private MultiExpressActivity target;

  private View view2131231226;

  @UiThread
  public MultiExpressActivity_ViewBinding(MultiExpressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MultiExpressActivity_ViewBinding(final MultiExpressActivity target, View source) {
    this.target = target;

    View view;
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'myToolbar'", Toolbar.class);
    target.rvExpress = Utils.findRequiredViewAsType(source, R.id.rv_express, "field 'rvExpress'", RecyclerView.class);
    target.cnYf = Utils.findRequiredViewAsType(source, R.id.cn_yf, "field 'cnYf'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_status, "field 'tvStatus' and method 'onViewClicked'");
    target.tvStatus = Utils.castView(view, R.id.tv_status, "field 'tvStatus'", TextView.class);
    view2131231226 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rlBtm = Utils.findRequiredViewAsType(source, R.id.rl_btm, "field 'rlBtm'", RelativeLayout.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MultiExpressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myToolbar = null;
    target.rvExpress = null;
    target.cnYf = null;
    target.tvStatus = null;
    target.rlBtm = null;
    target.tvPrice = null;

    view2131231226.setOnClickListener(null);
    view2131231226 = null;
  }
}
