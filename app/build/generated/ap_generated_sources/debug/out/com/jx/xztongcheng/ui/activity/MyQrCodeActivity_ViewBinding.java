// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyQrCodeActivity_ViewBinding implements Unbinder {
  private MyQrCodeActivity target;

  @UiThread
  public MyQrCodeActivity_ViewBinding(MyQrCodeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyQrCodeActivity_ViewBinding(MyQrCodeActivity target, View source) {
    this.target = target;

    target.ivCode = Utils.findRequiredViewAsType(source, R.id.iv_code, "field 'ivCode'", ImageView.class);
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.my_toolbar, "field 'myToolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyQrCodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivCode = null;
    target.myToolbar = null;
  }
}
