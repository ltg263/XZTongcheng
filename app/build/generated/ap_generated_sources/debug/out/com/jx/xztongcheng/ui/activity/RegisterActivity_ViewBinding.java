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

public class RegisterActivity_ViewBinding implements Unbinder {
  private RegisterActivity target;

  private View view2131230758;

  private View view2131231215;

  private View view2131231176;

  private View view2131231195;

  @UiThread
  public RegisterActivity_ViewBinding(RegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegisterActivity_ViewBinding(final RegisterActivity target, View source) {
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
    target.etPass = Utils.findRequiredViewAsType(source, R.id.et_pass, "field 'etPass'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_register, "field 'tvRegister' and method 'onViewClicked'");
    target.tvRegister = Utils.castView(view, R.id.tv_register, "field 'tvRegister'", TextView.class);
    view2131231215 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.cb = Utils.findRequiredViewAsType(source, R.id.cb, "field 'cb'", TextView.class);
    target.tvInfo = Utils.findRequiredViewAsType(source, R.id.tv_info, "field 'tvInfo'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_forget, "method 'onViewClicked'");
    view2131231176 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_login, "method 'onViewClicked'");
    view2131231195 = view;
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
    RegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.myToolbar = null;
    target.etAccount = null;
    target.etVerify = null;
    target.authCode = null;
    target.etPass = null;
    target.tvRegister = null;
    target.cb = null;
    target.tvInfo = null;

    view2131230758.setOnClickListener(null);
    view2131230758 = null;
    view2131231215.setOnClickListener(null);
    view2131231215 = null;
    view2131231176.setOnClickListener(null);
    view2131231176 = null;
    view2131231195.setOnClickListener(null);
    view2131231195 = null;
  }
}
