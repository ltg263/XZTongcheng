// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WebViewWithBackActivity_ViewBinding implements Unbinder {
  private WebViewWithBackActivity target;

  @UiThread
  public WebViewWithBackActivity_ViewBinding(WebViewWithBackActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WebViewWithBackActivity_ViewBinding(WebViewWithBackActivity target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'toolbar'", Toolbar.class);
    target.wv_web = Utils.findRequiredViewAsType(source, R.id.wv_web, "field 'wv_web'", WebView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WebViewWithBackActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.wv_web = null;
  }
}
