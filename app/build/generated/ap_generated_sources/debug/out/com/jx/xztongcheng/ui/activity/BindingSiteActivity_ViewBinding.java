// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BindingSiteActivity_ViewBinding implements Unbinder {
  private BindingSiteActivity target;

  private View view2131231161;

  @UiThread
  public BindingSiteActivity_ViewBinding(BindingSiteActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BindingSiteActivity_ViewBinding(final BindingSiteActivity target, View source) {
    this.target = target;

    View view;
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'myToolbar'", Toolbar.class);
    target.rvSite = Utils.findRequiredViewAsType(source, R.id.rv_site, "field 'rvSite'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tv_bind, "method 'onViewClicked'");
    view2131231161 = view;
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
    BindingSiteActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myToolbar = null;
    target.rvSite = null;

    view2131231161.setOnClickListener(null);
    view2131231161 = null;
  }
}
