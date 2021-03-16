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

public class DepositActivity_ViewBinding implements Unbinder {
  private DepositActivity target;

  private View view2131230754;

  private View view2131231084;

  private View view2131230779;

  @UiThread
  public DepositActivity_ViewBinding(DepositActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DepositActivity_ViewBinding(final DepositActivity target, View source) {
    this.target = target;

    View view;
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'myToolbar'", Toolbar.class);
    target.etAmount = Utils.findRequiredViewAsType(source, R.id.etAmount, "field 'etAmount'", EditText.class);
    target.tvAmount = Utils.findRequiredViewAsType(source, R.id.tvAmount, "field 'tvAmount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.allDeposit, "field 'allDeposit' and method 'onViewClicked'");
    target.allDeposit = Utils.castView(view, R.id.allDeposit, "field 'allDeposit'", TextView.class);
    view2131230754 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.selectCard, "field 'selectCard' and method 'onViewClicked'");
    target.selectCard = Utils.castView(view, R.id.selectCard, "field 'selectCard'", TextView.class);
    view2131231084 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.slogon = Utils.findRequiredViewAsType(source, R.id.slogon, "field 'slogon'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnDeposit, "method 'onViewClicked'");
    view2131230779 = view;
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
    DepositActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myToolbar = null;
    target.etAmount = null;
    target.tvAmount = null;
    target.allDeposit = null;
    target.selectCard = null;
    target.slogon = null;

    view2131230754.setOnClickListener(null);
    view2131230754 = null;
    view2131231084.setOnClickListener(null);
    view2131231084 = null;
    view2131230779.setOnClickListener(null);
    view2131230779 = null;
  }
}
