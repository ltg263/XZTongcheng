// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyWalletActivity_ViewBinding implements Unbinder {
  private MyWalletActivity target;

  private View view2131230882;

  private View view2131230972;

  @UiThread
  public MyWalletActivity_ViewBinding(MyWalletActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyWalletActivity_ViewBinding(final MyWalletActivity target, View source) {
    this.target = target;

    View view;
    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.my_toolbar, "field 'myToolbar'", Toolbar.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tv_amount, "field 'tvAmount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.goToDeposit, "method 'onViewClicked'");
    view2131230882 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.logRl, "method 'onViewClicked'");
    view2131230972 = view;
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
    MyWalletActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.myToolbar = null;
    target.tvAmount = null;

    view2131230882.setOnClickListener(null);
    view2131230882 = null;
    view2131230972.setOnClickListener(null);
    view2131230972 = null;
  }
}
