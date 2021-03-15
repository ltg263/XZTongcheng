// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ToolCarListActivity_ViewBinding implements Unbinder {
  private ToolCarListActivity target;

  @UiThread
  public ToolCarListActivity_ViewBinding(ToolCarListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ToolCarListActivity_ViewBinding(ToolCarListActivity target, View source) {
    this.target = target;

    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'mToolbar'", Toolbar.class);
    target.mRvData = Utils.findRequiredViewAsType(source, R.id.rv_data, "field 'mRvData'", RecyclerView.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ToolCarListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mToolbar = null;
    target.mRvData = null;
    target.refresh = null;
  }
}
