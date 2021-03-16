// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddBankCardActivity_ViewBinding implements Unbinder {
  private AddBankCardActivity target;

  private View view2131230790;

  private View view2131230780;

  private View view2131230909;

  @UiThread
  public AddBankCardActivity_ViewBinding(AddBankCardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddBankCardActivity_ViewBinding(final AddBankCardActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'toolbar'", Toolbar.class);
    target.username = Utils.findRequiredViewAsType(source, R.id.username, "field 'username'", EditText.class);
    target.phone = Utils.findRequiredViewAsType(source, R.id.phone, "field 'phone'", EditText.class);
    target.cardNo = Utils.findRequiredViewAsType(source, R.id.cardNo, "field 'cardNo'", EditText.class);
    target.bankName = Utils.findRequiredViewAsType(source, R.id.bankName, "field 'bankName'", EditText.class);
    view = Utils.findRequiredView(source, R.id.cardImage, "field 'cardImage' and method 'onViewClicked'");
    target.cardImage = Utils.castView(view, R.id.cardImage, "field 'cardImage'", ImageView.class);
    view2131230790 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnSubmit, "field 'btnSubmit' and method 'onViewClicked'");
    target.btnSubmit = Utils.castView(view, R.id.btnSubmit, "field 'btnSubmit'", Button.class);
    view2131230780 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv, "field 'mIv' and method 'onViewClicked'");
    target.mIv = Utils.castView(view, R.id.iv, "field 'mIv'", ImageView.class);
    view2131230909 = view;
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
    AddBankCardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.username = null;
    target.phone = null;
    target.cardNo = null;
    target.bankName = null;
    target.cardImage = null;
    target.btnSubmit = null;
    target.mIv = null;

    view2131230790.setOnClickListener(null);
    view2131230790 = null;
    view2131230780.setOnClickListener(null);
    view2131230780 = null;
    view2131230909.setOnClickListener(null);
    view2131230909 = null;
  }
}
