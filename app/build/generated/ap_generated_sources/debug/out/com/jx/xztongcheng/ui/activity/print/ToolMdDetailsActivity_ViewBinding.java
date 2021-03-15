// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity.print;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ToolMdDetailsActivity_ViewBinding implements Unbinder {
  private ToolMdDetailsActivity target;

  private View view2131230786;

  private View view2131231069;

  private View view2131230784;

  @UiThread
  public ToolMdDetailsActivity_ViewBinding(ToolMdDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ToolMdDetailsActivity_ViewBinding(final ToolMdDetailsActivity target, View source) {
    this.target = target;

    View view;
    target.mToolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'mToolbar'", Toolbar.class);
    target.mTitle = Utils.findRequiredViewAsType(source, R.id.title_right_text, "field 'mTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.button_send, "field 'mSendButton' and method 'onViewClicked'");
    target.mSendButton = Utils.castView(view, R.id.button_send, "field 'mSendButton'", Button.class);
    view2131230786 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvZdh = Utils.findRequiredViewAsType(source, R.id.tv_zdh, "field 'mTvZdh'", TextView.class);
    target.mIv = Utils.findRequiredViewAsType(source, R.id.iv, "field 'mIv'", ImageView.class);
    target.mTvSjr1 = Utils.findRequiredViewAsType(source, R.id.tv_sjr_1, "field 'mTvSjr1'", TextView.class);
    target.mTvJjr1 = Utils.findRequiredViewAsType(source, R.id.tv_jjr_1, "field 'mTvJjr1'", TextView.class);
    target.mTvDdxx = Utils.findRequiredViewAsType(source, R.id.tv_ddxx, "field 'mTvDdxx'", TextView.class);
    target.mTvSjr2 = Utils.findRequiredViewAsType(source, R.id.tv_sjr_2, "field 'mTvSjr2'", TextView.class);
    target.mTvJjnr = Utils.findRequiredViewAsType(source, R.id.tv_jjnr, "field 'mTvJjnr'", TextView.class);
    target.mTvSm1 = Utils.findRequiredViewAsType(source, R.id.tv_sm1, "field 'mTvSm1'", TextView.class);
    target.mTvGm1 = Utils.findRequiredViewAsType(source, R.id.tv_gm1, "field 'mTvGm1'", TextView.class);
    target.mTvSjr3 = Utils.findRequiredViewAsType(source, R.id.tv_sjr_3, "field 'mTvSjr3'", TextView.class);
    target.mTvJjr3 = Utils.findRequiredViewAsType(source, R.id.tv_jjr_3, "field 'mTvJjr3'", TextView.class);
    target.mTvJrsm = Utils.findRequiredViewAsType(source, R.id.tv_jrsm, "field 'mTvJrsm'", TextView.class);
    target.mTvSl = Utils.findRequiredViewAsType(source, R.id.tv_sl, "field 'mTvSl'", TextView.class);
    target.mTvZl = Utils.findRequiredViewAsType(source, R.id.tv_zl, "field 'mTvZl'", TextView.class);
    target.tv_websiteName = Utils.findRequiredViewAsType(source, R.id.tv_websiteName, "field 'tv_websiteName'", TextView.class);
    target.ll_bj = Utils.findRequiredViewAsType(source, R.id.ll_bj, "field 'll_bj'", LinearLayout.class);
    target.mTvXf = Utils.findRequiredViewAsType(source, R.id.tv_xf, "field 'mTvXf'", TextView.class);
    target.mTvDaif = Utils.findRequiredViewAsType(source, R.id.tv_daif, "field 'mTvDaif'", TextView.class);
    target.mTvDf = Utils.findRequiredViewAsType(source, R.id.tv_df, "field 'mTvDf'", TextView.class);
    view = Utils.findRequiredView(source, R.id.search, "method 'onViewClicked'");
    view2131231069 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.button_dk, "method 'onViewClicked'");
    view2131230784 = view;
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
    ToolMdDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mToolbar = null;
    target.mTitle = null;
    target.mSendButton = null;
    target.mTvZdh = null;
    target.mIv = null;
    target.mTvSjr1 = null;
    target.mTvJjr1 = null;
    target.mTvDdxx = null;
    target.mTvSjr2 = null;
    target.mTvJjnr = null;
    target.mTvSm1 = null;
    target.mTvGm1 = null;
    target.mTvSjr3 = null;
    target.mTvJjr3 = null;
    target.mTvJrsm = null;
    target.mTvSl = null;
    target.mTvZl = null;
    target.tv_websiteName = null;
    target.ll_bj = null;
    target.mTvXf = null;
    target.mTvDaif = null;
    target.mTvDf = null;

    view2131230786.setOnClickListener(null);
    view2131230786 = null;
    view2131231069.setOnClickListener(null);
    view2131231069 = null;
    view2131230784.setOnClickListener(null);
    view2131230784 = null;
  }
}
