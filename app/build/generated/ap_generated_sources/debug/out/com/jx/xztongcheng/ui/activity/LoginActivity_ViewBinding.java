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

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131230758;

  private View view2131231211;

  private View view2131231163;

  private View view2131231179;

  private View view2131231218;

  private View view2131231198;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
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
    target.tvInfo = Utils.findRequiredViewAsType(source, R.id.tv_info, "field 'tvInfo'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_phone, "field 'tvPhone' and method 'onViewClicked'");
    target.tvPhone = Utils.castView(view, R.id.tv_phone, "field 'tvPhone'", TextView.class);
    view2131231211 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_code, "field 'tvCode' and method 'onViewClicked'");
    target.tvCode = Utils.castView(view, R.id.tv_code, "field 'tvCode'", TextView.class);
    view2131231163 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_forget, "method 'onViewClicked'");
    view2131231179 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_register, "method 'onViewClicked'");
    view2131231218 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_login, "method 'onViewClicked'");
    view2131231198 = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.myToolbar = null;
    target.etAccount = null;
    target.etVerify = null;
    target.authCode = null;
    target.tvInfo = null;
    target.tvPhone = null;
    target.tvCode = null;

    view2131230758.setOnClickListener(null);
    view2131230758 = null;
    view2131231211.setOnClickListener(null);
    view2131231211 = null;
    view2131231163.setOnClickListener(null);
    view2131231163 = null;
    view2131231179.setOnClickListener(null);
    view2131231179 = null;
    view2131231218.setOnClickListener(null);
    view2131231218 = null;
    view2131231198.setOnClickListener(null);
    view2131231198 = null;
  }
}
