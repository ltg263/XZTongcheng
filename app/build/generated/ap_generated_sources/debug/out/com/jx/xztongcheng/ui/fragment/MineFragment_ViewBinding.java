// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MineFragment_ViewBinding implements Unbinder {
  private MineFragment target;

  private View view2131230961;

  private View view2131230962;

  private View view2131230964;

  private View view2131230976;

  private View view2131230971;

  private View view2131231062;

  private View view2131230965;

  private View view2131230977;

  private View view2131230924;

  private View view2131230956;

  private View view2131230968;

  private View view2131230954;

  @UiThread
  public MineFragment_ViewBinding(final MineFragment target, View source) {
    this.target = target;

    View view;
    target.ivHead = Utils.findRequiredViewAsType(source, R.id.iv_head, "field 'ivHead'", ImageView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.bannerMine = Utils.findRequiredViewAsType(source, R.id.banner_mine, "field 'bannerMine'", Banner.class);
    target.tvJrsj = Utils.findRequiredViewAsType(source, R.id.tv_jrsj, "field 'tvJrsj'", TextView.class);
    target.tvJrsk = Utils.findRequiredViewAsType(source, R.id.tv_jrsk, "field 'tvJrsk'", TextView.class);
    target.tv_dh = Utils.findRequiredViewAsType(source, R.id.tv_dh, "field 'tv_dh'", TextView.class);
    target.tvKhs = Utils.findRequiredViewAsType(source, R.id.tv_khs, "field 'tvKhs'", TextView.class);
    target.tvBalance = Utils.findRequiredViewAsType(source, R.id.tv_balance, "field 'tvBalance'", TextView.class);
    target.cvBanner = Utils.findRequiredViewAsType(source, R.id.cv_banner, "field 'cvBanner'", CardView.class);
    view = Utils.findRequiredView(source, R.id.ll_jrsj, "method 'onViewClicked'");
    view2131230961 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_jrsr, "method 'onViewClicked'");
    view2131230962 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_khs, "method 'onViewClicked'");
    view2131230964 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_wallet, "method 'onViewClicked'");
    view2131230976 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_setting, "method 'onViewClicked'");
    view2131230971 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_info, "method 'onViewClicked'");
    view2131231062 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_kjgl, "method 'onViewClicked'");
    view2131230965 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_zpj, "method 'onViewClicked'");
    view2131230977 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_code, "method 'onViewClicked'");
    view2131230924 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_code, "method 'onViewClicked'");
    view2131230956 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_qd, "method 'onViewClicked'");
    view2131230968 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_bzj, "method 'onViewClicked'");
    view2131230954 = view;
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
    MineFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivHead = null;
    target.tvName = null;
    target.bannerMine = null;
    target.tvJrsj = null;
    target.tvJrsk = null;
    target.tv_dh = null;
    target.tvKhs = null;
    target.tvBalance = null;
    target.cvBanner = null;

    view2131230961.setOnClickListener(null);
    view2131230961 = null;
    view2131230962.setOnClickListener(null);
    view2131230962 = null;
    view2131230964.setOnClickListener(null);
    view2131230964 = null;
    view2131230976.setOnClickListener(null);
    view2131230976 = null;
    view2131230971.setOnClickListener(null);
    view2131230971 = null;
    view2131231062.setOnClickListener(null);
    view2131231062 = null;
    view2131230965.setOnClickListener(null);
    view2131230965 = null;
    view2131230977.setOnClickListener(null);
    view2131230977 = null;
    view2131230924.setOnClickListener(null);
    view2131230924 = null;
    view2131230956.setOnClickListener(null);
    view2131230956 = null;
    view2131230968.setOnClickListener(null);
    view2131230968 = null;
    view2131230954.setOnClickListener(null);
    view2131230954 = null;
  }
}
