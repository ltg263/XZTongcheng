// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PayForImageActivity_ViewBinding implements Unbinder {
  private PayForImageActivity target;

  private View view2131230911;

  private View view2131230868;

  @UiThread
  public PayForImageActivity_ViewBinding(PayForImageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PayForImageActivity_ViewBinding(final PayForImageActivity target, View source) {
    this.target = target;

    View view;
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.my_toolbar, "field 'myToolbar'", Toolbar.class);
    target.ivImage = Utils.findRequiredViewAsType(source, R.id.iv_image, "field 'ivImage'", ImageView.class);
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.tvWeightPrice = Utils.findRequiredViewAsType(source, R.id.tv_weight_price, "field 'tvWeightPrice'", TextView.class);
    target.tvPrice = Utils.findRequiredViewAsType(source, R.id.tv_price, "field 'tvPrice'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_camera, "field 'ivCamera' and method 'onViewClicked'");
    target.ivCamera = Utils.castView(view, R.id.iv_camera, "field 'ivCamera'", ImageView.class);
    view2131230911 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.finishGet, "method 'onViewClicked'");
    view2131230868 = view;
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
    PayForImageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myToolbar = null;
    target.ivImage = null;
    target.tvContent = null;
    target.tvWeightPrice = null;
    target.tvPrice = null;
    target.ivCamera = null;

    view2131230911.setOnClickListener(null);
    view2131230911 = null;
    view2131230868.setOnClickListener(null);
    view2131230868 = null;
  }
}
