// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePhoneActivity_ViewBinding implements Unbinder {
  private ChangePhoneActivity target;

  private View view2131230758;

  private View view2131231180;

  @UiThread
  public ChangePhoneActivity_ViewBinding(ChangePhoneActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePhoneActivity_ViewBinding(final ChangePhoneActivity target, View source) {
    this.target = target;

    View view;
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.my_toolbar, "field 'myToolbar'", Toolbar.class);
    target.llMobile = Utils.findRequiredViewAsType(source, R.id.ll_mobile, "field 'llMobile'", LinearLayout.class);
    target.etMobile = Utils.findRequiredViewAsType(source, R.id.et_mobile, "field 'etMobile'", EditText.class);
    target.etVerify = Utils.findRequiredViewAsType(source, R.id.et_verify, "field 'etVerify'", EditText.class);
    view = Utils.findRequiredView(source, R.id.auth_code, "field 'authCode' and method 'onViewClicked'");
    target.authCode = Utils.castView(view, R.id.auth_code, "field 'authCode'", TextView.class);
    view2131230758 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.llEt = Utils.findRequiredViewAsType(source, R.id.ll_et, "field 'llEt'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_done, "field 'tvDone' and method 'onViewClicked'");
    target.tvDone = Utils.castView(view, R.id.tv_done, "field 'tvDone'", TextView.class);
    view2131231180 = view;
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
    ChangePhoneActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myToolbar = null;
    target.llMobile = null;
    target.etMobile = null;
    target.etVerify = null;
    target.authCode = null;
    target.llEt = null;
    target.tvDone = null;

    view2131230758.setOnClickListener(null);
    view2131230758 = null;
    view2131231180.setOnClickListener(null);
    view2131231180 = null;
  }
}
