// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ToolCkListActivity_ViewBinding implements Unbinder {
  private ToolCkListActivity target;

  private View view2131231182;

  private View view2131231244;

  private View view2131231243;

  @UiThread
  public ToolCkListActivity_ViewBinding(ToolCkListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ToolCkListActivity_ViewBinding(final ToolCkListActivity target, View source) {
    this.target = target;

    View view;
    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'mToolbar'", Toolbar.class);
    target.mEtDdh = Utils.findRequiredViewAsType(source, R.id.et_ddh, "field 'mEtDdh'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_ck, "field 'mTvCk' and method 'onViewClicked'");
    target.mTvCk = Utils.castView(view, R.id.tv_ck, "field 'mTvCk'", TextView.class);
    view2131231182 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_sao, "method 'onViewClicked'");
    view2131231244 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_rk, "method 'onViewClicked'");
    view2131231243 = view;
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
    ToolCkListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mToolbar = null;
    target.mEtDdh = null;
    target.mTvCk = null;

    view2131231182.setOnClickListener(null);
    view2131231182 = null;
    view2131231244.setOnClickListener(null);
    view2131231244 = null;
    view2131231243.setOnClickListener(null);
    view2131231243 = null;
  }
}
