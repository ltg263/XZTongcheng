// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SwitchMapFragment_ViewBinding implements Unbinder {
  private SwitchMapFragment target;

  private View view2131230878;

  private View view2131230760;

  private View view2131231245;

  private View view2131230788;

  @UiThread
  public SwitchMapFragment_ViewBinding(final SwitchMapFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.gaodeTv, "field 'gaodeTv' and method 'onViewClicked'");
    target.gaodeTv = Utils.castView(view, R.id.gaodeTv, "field 'gaodeTv'", TextView.class);
    view2131230878 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.baiduTv, "field 'baiduTv' and method 'onViewClicked'");
    target.baiduTv = Utils.castView(view, R.id.baiduTv, "field 'baiduTv'", TextView.class);
    view2131230760 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.txTv, "field 'txTv' and method 'onViewClicked'");
    target.txTv = Utils.castView(view, R.id.txTv, "field 'txTv'", TextView.class);
    view2131231245 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cancel, "method 'onViewClicked'");
    view2131230788 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SwitchMapFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.gaodeTv = null;
    target.baiduTv = null;
    target.txTv = null;

    view2131230878.setOnClickListener(null);
    view2131230878 = null;
    view2131230760.setOnClickListener(null);
    view2131230760 = null;
    view2131231245.setOnClickListener(null);
    view2131231245 = null;
    view2131230788.setOnClickListener(null);
    view2131230788 = null;
  }
}
