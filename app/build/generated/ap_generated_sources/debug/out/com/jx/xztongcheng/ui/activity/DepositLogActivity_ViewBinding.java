// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DepositLogActivity_ViewBinding implements Unbinder {
  private DepositLogActivity target;

  @UiThread
  public DepositLogActivity_ViewBinding(DepositLogActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DepositLogActivity_ViewBinding(DepositLogActivity target, View source) {
    this.target = target;

    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'myToolbar'", Toolbar.class);
    target.logRv = Utils.findRequiredViewAsType(source, R.id.logRv, "field 'logRv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DepositLogActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myToolbar = null;
    target.logRv = null;
  }
}
