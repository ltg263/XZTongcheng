// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  private View view2131230955;

  private View view2131230966;

  private View view2131230949;

  private View view2131231205;

  private View view2131231224;

  private View view2131231164;

  private View view2131231248;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.my_toolbar, "field 'myToolbar'", Toolbar.class);
    target.bannerHome = Utils.findRequiredViewAsType(source, R.id.banner_home, "field 'bannerHome'", Banner.class);
    target.ivKjgl1 = Utils.findRequiredViewAsType(source, R.id.iv_kjgl1, "field 'ivKjgl1'", ImageView.class);
    target.tvKjgl1 = Utils.findRequiredViewAsType(source, R.id.tv_kjgl1, "field 'tvKjgl1'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_kjgl1, "field 'llKjgl1' and method 'onViewClicked'");
    target.llKjgl1 = Utils.castView(view, R.id.ll_kjgl1, "field 'llKjgl1'", LinearLayout.class);
    view2131230955 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivZpjgl2 = Utils.findRequiredViewAsType(source, R.id.iv_zpjgl2, "field 'ivZpjgl2'", ImageView.class);
    target.tvZpjgl2 = Utils.findRequiredViewAsType(source, R.id.tv_zpjgl2, "field 'tvZpjgl2'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_zpjgl2, "field 'llZpjgl2' and method 'onViewClicked'");
    target.llZpjgl2 = Utils.castView(view, R.id.ll_zpjgl2, "field 'llZpjgl2'", LinearLayout.class);
    view2131230966 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ivJjgl3 = Utils.findRequiredViewAsType(source, R.id.iv_jjgl3, "field 'ivJjgl3'", ImageView.class);
    target.tvJjgl3 = Utils.findRequiredViewAsType(source, R.id.tv_jjgl3, "field 'tvJjgl3'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_jjgl3, "field 'llJjgl3' and method 'onViewClicked'");
    target.llJjgl3 = Utils.castView(view, R.id.ll_jjgl3, "field 'llJjgl3'", LinearLayout.class);
    view2131230949 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_md, "method 'onViewClicked'");
    view2131231205 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_rk, "method 'onViewClicked'");
    view2131231224 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_ck, "method 'onViewClicked'");
    view2131231164 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_xd, "method 'onViewClicked'");
    view2131231248 = view;
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
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.myToolbar = null;
    target.bannerHome = null;
    target.ivKjgl1 = null;
    target.tvKjgl1 = null;
    target.llKjgl1 = null;
    target.ivZpjgl2 = null;
    target.tvZpjgl2 = null;
    target.llZpjgl2 = null;
    target.ivJjgl3 = null;
    target.tvJjgl3 = null;
    target.llJjgl3 = null;

    view2131230955.setOnClickListener(null);
    view2131230955 = null;
    view2131230966.setOnClickListener(null);
    view2131230966 = null;
    view2131230949.setOnClickListener(null);
    view2131230949 = null;
    view2131231205.setOnClickListener(null);
    view2131231205 = null;
    view2131231224.setOnClickListener(null);
    view2131231224 = null;
    view2131231164.setOnClickListener(null);
    view2131231164 = null;
    view2131231248.setOnClickListener(null);
    view2131231248 = null;
  }
}
