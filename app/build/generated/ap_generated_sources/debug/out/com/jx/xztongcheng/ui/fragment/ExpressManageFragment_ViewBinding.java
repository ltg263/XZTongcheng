// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExpressManageFragment_ViewBinding implements Unbinder {
  private ExpressManageFragment target;

  @UiThread
  public ExpressManageFragment_ViewBinding(ExpressManageFragment target, View source) {
    this.target = target;

    target.orderRv = Utils.findRequiredViewAsType(source, R.id.expressRv, "field 'orderRv'", RecyclerView.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExpressManageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.orderRv = null;
    target.refresh = null;
  }
}
