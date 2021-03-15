// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BankCardManageActivity_ViewBinding implements Unbinder {
  private BankCardManageActivity target;

  private View view2131230751;

  @UiThread
  public BankCardManageActivity_ViewBinding(BankCardManageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BankCardManageActivity_ViewBinding(final BankCardManageActivity target, View source) {
    this.target = target;

    View view;
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'myToolbar'", Toolbar.class);
    target.cardRv = Utils.findRequiredViewAsType(source, R.id.cardRv, "field 'cardRv'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.addBankCard, "method 'onViewClicked'");
    view2131230751 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    BankCardManageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myToolbar = null;
    target.cardRv = null;

    view2131230751.setOnClickListener(null);
    view2131230751 = null;
  }
}
