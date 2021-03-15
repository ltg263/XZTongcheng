// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.utils.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyInfoActivity_ViewBinding implements Unbinder {
  private MyInfoActivity target;

  @UiThread
  public MyInfoActivity_ViewBinding(MyInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyInfoActivity_ViewBinding(MyInfoActivity target, View source) {
    this.target = target;

    target.headIcon = Utils.findRequiredViewAsType(source, R.id.head_icon, "field 'headIcon'", CircleImageView.class);
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'myToolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.headIcon = null;
    target.myToolbar = null;
  }
}
