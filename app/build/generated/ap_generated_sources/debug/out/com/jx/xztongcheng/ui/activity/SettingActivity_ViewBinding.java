// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  private View view2131230802;

  private View view2131230801;

  private View view2131231209;

  private View view2131230771;

  private View view2131230965;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(final SettingActivity target, View source) {
    this.target = target;

    View view;
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.my_toolbar, "field 'myToolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.changePwd, "field 'changePwd' and method 'onViewClicked'");
    target.changePwd = Utils.castView(view, R.id.changePwd, "field 'changePwd'", LinearLayout.class);
    view2131230802 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.changePhone, "field 'changePhone' and method 'onViewClicked'");
    target.changePhone = Utils.castView(view, R.id.changePhone, "field 'changePhone'", LinearLayout.class);
    view2131230801 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_logout, "field 'tvLogout' and method 'onViewClicked'");
    target.tvLogout = Utils.castView(view, R.id.tv_logout, "field 'tvLogout'", TextView.class);
    view2131231209 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvSfzStatus = Utils.findRequiredViewAsType(source, R.id.tv_sfz_status, "field 'tvSfzStatus'", TextView.class);
    target.svQishou = Utils.findRequiredViewAsType(source, R.id.sv_qishou, "field 'svQishou'", Switch.class);
    view = Utils.findRequiredView(source, R.id.bind_site, "method 'onViewClicked'");
    view2131230771 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_verify, "method 'onViewClicked'");
    view2131230965 = view;
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
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myToolbar = null;
    target.changePwd = null;
    target.changePhone = null;
    target.tvLogout = null;
    target.tvSfzStatus = null;
    target.svQishou = null;

    view2131230802.setOnClickListener(null);
    view2131230802 = null;
    view2131230801.setOnClickListener(null);
    view2131230801 = null;
    view2131231209.setOnClickListener(null);
    view2131231209 = null;
    view2131230771.setOnClickListener(null);
    view2131230771 = null;
    view2131230965.setOnClickListener(null);
    view2131230965 = null;
  }
}
