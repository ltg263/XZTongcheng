// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ToolMdListActivity_ViewBinding implements Unbinder {
  private ToolMdListActivity target;

  @UiThread
  public ToolMdListActivity_ViewBinding(ToolMdListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ToolMdListActivity_ViewBinding(ToolMdListActivity target, View source) {
    this.target = target;

    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'mToolbar'", Toolbar.class);
    target.tv_print = Utils.findRequiredViewAsType(source, R.id.tv_print, "field 'tv_print'", TextView.class);
    target.bnt_print = Utils.findRequiredViewAsType(source, R.id.bnt_print, "field 'bnt_print'", TextView.class);
    target.mRvData = Utils.findRequiredViewAsType(source, R.id.rv_data, "field 'mRvData'", RecyclerView.class);
    target.refresh = Utils.findRequiredViewAsType(source, R.id.refresh, "field 'refresh'", SwipeRefreshLayout.class);
    target.mTitle = Utils.findRequiredViewAsType(source, R.id.title_right_text, "field 'mTitle'", TextView.class);
    target.mTvYdy = Utils.findRequiredViewAsType(source, R.id.tv_ydy, "field 'mTvYdy'", TextView.class);
    target.mTvWdy = Utils.findRequiredViewAsType(source, R.id.tv_wdy, "field 'mTvWdy'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ToolMdListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mToolbar = null;
    target.tv_print = null;
    target.bnt_print = null;
    target.mRvData = null;
    target.refresh = null;
    target.mTitle = null;
    target.mTvYdy = null;
    target.mTvWdy = null;
  }
}
