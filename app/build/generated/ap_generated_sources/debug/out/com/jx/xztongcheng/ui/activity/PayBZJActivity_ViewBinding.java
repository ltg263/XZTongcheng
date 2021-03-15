// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PayBZJActivity_ViewBinding implements Unbinder {
  private PayBZJActivity target;

  @UiThread
  public PayBZJActivity_ViewBinding(PayBZJActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PayBZJActivity_ViewBinding(PayBZJActivity target, View source) {
    this.target = target;

    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.my_toolbar, "field 'myToolbar'", Toolbar.class);
    target.ivWx = Utils.findRequiredViewAsType(source, R.id.iv_wx, "field 'ivWx'", ImageView.class);
    target.rlWx = Utils.findRequiredViewAsType(source, R.id.rl_wx, "field 'rlWx'", RelativeLayout.class);
    target.ivAlipay = Utils.findRequiredViewAsType(source, R.id.iv_alipay, "field 'ivAlipay'", ImageView.class);
    target.rlAlipay = Utils.findRequiredViewAsType(source, R.id.rl_alipay, "field 'rlAlipay'", RelativeLayout.class);
    target.rbWx = Utils.findRequiredViewAsType(source, R.id.rb_wx, "field 'rbWx'", RadioButton.class);
    target.rbZfb = Utils.findRequiredViewAsType(source, R.id.rb_zfb, "field 'rbZfb'", RadioButton.class);
    target.rgbPay = Utils.findRequiredViewAsType(source, R.id.rgb_pay, "field 'rgbPay'", RadioGroup.class);
    target.button_send = Utils.findRequiredViewAsType(source, R.id.button_send, "field 'button_send'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PayBZJActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myToolbar = null;
    target.ivWx = null;
    target.rlWx = null;
    target.ivAlipay = null;
    target.rlAlipay = null;
    target.rbWx = null;
    target.rbZfb = null;
    target.rgbPay = null;
    target.button_send = null;
  }
}
