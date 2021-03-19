// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NameAuthenticationActivity_ViewBinding implements Unbinder {
  private NameAuthenticationActivity target;

  private View view2131230973;

  private View view2131230972;

  private View view2131231181;

  @UiThread
  public NameAuthenticationActivity_ViewBinding(NameAuthenticationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NameAuthenticationActivity_ViewBinding(final NameAuthenticationActivity target,
      View source) {
    this.target = target;

    View view;
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.my_toolbar, "field 'myToolbar'", Toolbar.class);
    target.ivCard1 = Utils.findRequiredViewAsType(source, R.id.iv_card1, "field 'ivCard1'", ImageView.class);
    target.ivCard2 = Utils.findRequiredViewAsType(source, R.id.iv_card2, "field 'ivCard2'", ImageView.class);
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", EditText.class);
    target.etCode = Utils.findRequiredViewAsType(source, R.id.et_code, "field 'etCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.ll_sfzzm, "method 'onViewClicked'");
    view2131230973 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_sfzfm, "method 'onViewClicked'");
    view2131230972 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_btn2, "method 'onViewClicked'");
    view2131231181 = view;
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
    NameAuthenticationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myToolbar = null;
    target.ivCard1 = null;
    target.ivCard2 = null;
    target.etName = null;
    target.etCode = null;

    view2131230973.setOnClickListener(null);
    view2131230973 = null;
    view2131230972.setOnClickListener(null);
    view2131230972 = null;
    view2131231181.setOnClickListener(null);
    view2131231181 = null;
  }
}
