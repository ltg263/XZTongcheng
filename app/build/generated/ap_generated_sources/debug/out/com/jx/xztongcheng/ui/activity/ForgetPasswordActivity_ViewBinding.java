// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgetPasswordActivity_ViewBinding implements Unbinder {
  private ForgetPasswordActivity target;

  private View view2131230758;

  private View view2131231174;

  @UiThread
  public ForgetPasswordActivity_ViewBinding(ForgetPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ForgetPasswordActivity_ViewBinding(final ForgetPasswordActivity target, View source) {
    this.target = target;

    View view;
    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.my_toolbar, "field 'myToolbar'", Toolbar.class);
    target.etAccount = Utils.findRequiredViewAsType(source, R.id.et_account, "field 'etAccount'", EditText.class);
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
    view = Utils.findRequiredView(source, R.id.tv_done, "field 'tvDone' and method 'onViewClicked'");
    target.tvDone = Utils.castView(view, R.id.tv_done, "field 'tvDone'", TextView.class);
    view2131231174 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etPassword = Utils.findRequiredViewAsType(source, R.id.et_password, "field 'etPassword'", EditText.class);
    target.etPassword2 = Utils.findRequiredViewAsType(source, R.id.et_password2, "field 'etPassword2'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgetPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.myToolbar = null;
    target.etAccount = null;
    target.etVerify = null;
    target.authCode = null;
    target.tvDone = null;
    target.etPassword = null;
    target.etPassword2 = null;

    view2131230758.setOnClickListener(null);
    view2131230758 = null;
    view2131231174.setOnClickListener(null);
    view2131231174 = null;
  }
}
