// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StasisPartActivity_ViewBinding implements Unbinder {
  private StasisPartActivity target;

  @UiThread
  public StasisPartActivity_ViewBinding(StasisPartActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StasisPartActivity_ViewBinding(StasisPartActivity target, View source) {
    this.target = target;

    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", ViewPager.class);
    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.my_toolbar, "field 'mToolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StasisPartActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayout = null;
    target.viewPager = null;
    target.mToolbar = null;
  }
}
