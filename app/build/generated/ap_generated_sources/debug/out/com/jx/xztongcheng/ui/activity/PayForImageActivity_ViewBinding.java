// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PayForImageActivity_ViewBinding implements Unbinder {
  private PayForImageActivity target;

  private View view2131231200;

  private View view2131230920;

  private View view2131230877;

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
    view = Utils.findRequiredView(source, R.id.tv_fkfs, "field 'tv_fkfs' and method 'onViewClicked'");
    target.tv_fkfs = Utils.castView(view, R.id.tv_fkfs, "field 'tv_fkfs'", TextView.class);
    view2131231200 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_camera, "field 'ivCamera' and method 'onViewClicked'");
    target.ivCamera = Utils.castView(view, R.id.iv_camera, "field 'ivCamera'", ImageView.class);
    view2131230920 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ll_rwm = Utils.findRequiredViewAsType(source, R.id.ll_rwm, "field 'll_rwm'", LinearLayout.class);
    target.ll_dsfy = Utils.findRequiredViewAsType(source, R.id.ll_dsfy, "field 'll_dsfy'", LinearLayout.class);
    target.et_dsfy = Utils.findRequiredViewAsType(source, R.id.et_dsfy, "field 'et_dsfy'", EditText.class);
    view = Utils.findRequiredView(source, R.id.finishGet, "method 'onViewClicked'");
    view2131230877 = view;
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
    target.tv_fkfs = null;
    target.ivCamera = null;
    target.ll_rwm = null;
    target.ll_dsfy = null;
    target.et_dsfy = null;

    view2131231200.setOnClickListener(null);
    view2131231200 = null;
    view2131230920.setOnClickListener(null);
    view2131230920 = null;
    view2131230877.setOnClickListener(null);
    view2131230877 = null;
  }
}
