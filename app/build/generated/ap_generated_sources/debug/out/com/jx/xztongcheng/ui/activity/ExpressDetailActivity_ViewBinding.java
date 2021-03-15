// Generated code from Butter Knife. Do not modify!
package com.jx.xztongcheng.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jx.xztongcheng.R;
import com.jx.xztongcheng.widget.MyMapView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ExpressDetailActivity_ViewBinding implements Unbinder {
  private ExpressDetailActivity target;

  @UiThread
  public ExpressDetailActivity_ViewBinding(ExpressDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ExpressDetailActivity_ViewBinding(ExpressDetailActivity target, View source) {
    this.target = target;

    target.myToolbar = Utils.findRequiredViewAsType(source, R.id.include, "field 'myToolbar'", Toolbar.class);
    target.mMap = Utils.findRequiredViewAsType(source, R.id.map, "field 'mMap'", MyMapView.class);
    target.mNsw = Utils.findRequiredViewAsType(source, R.id.nsw, "field 'mNsw'", NestedScrollView.class);
    target.tvGetAddress1 = Utils.findRequiredViewAsType(source, R.id.tv_get_address1, "field 'tvGetAddress1'", TextView.class);
    target.tvGetAddress2 = Utils.findRequiredViewAsType(source, R.id.tv_get_address2, "field 'tvGetAddress2'", TextView.class);
    target.tvToAddress1 = Utils.findRequiredViewAsType(source, R.id.tv_to_address1, "field 'tvToAddress1'", TextView.class);
    target.tvToAddress2 = Utils.findRequiredViewAsType(source, R.id.tv_to_address2, "field 'tvToAddress2'", TextView.class);
    target.tvContact = Utils.findRequiredViewAsType(source, R.id.tv_contact, "field 'tvContact'", TextView.class);
    target.tvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tvName'", TextView.class);
    target.tvPutTime = Utils.findRequiredViewAsType(source, R.id.tv_put_time, "field 'tvPutTime'", TextView.class);
    target.tvGetAddress = Utils.findRequiredViewAsType(source, R.id.tv_get_address, "field 'tvGetAddress'", TextView.class);
    target.tvRemark = Utils.findRequiredViewAsType(source, R.id.tv_remark, "field 'tvRemark'", TextView.class);
    target.etExpressCompany = Utils.findRequiredViewAsType(source, R.id.et_express_company, "field 'etExpressCompany'", EditText.class);
    target.etExpressNo = Utils.findRequiredViewAsType(source, R.id.et_express_no, "field 'etExpressNo'", EditText.class);
    target.etWeight = Utils.findRequiredViewAsType(source, R.id.et_weight, "field 'etWeight'", EditText.class);
    target.etPrice = Utils.findRequiredViewAsType(source, R.id.et_price, "field 'etPrice'", EditText.class);
    target.etPackagePrice = Utils.findRequiredViewAsType(source, R.id.et_package_price, "field 'etPackagePrice'", EditText.class);
    target.etBjAmount = Utils.findRequiredViewAsType(source, R.id.et_bj_amount, "field 'etBjAmount'", EditText.class);
    target.etBjPrice = Utils.findRequiredViewAsType(source, R.id.et_bj_price, "field 'etBjPrice'", EditText.class);
    target.etDsje = Utils.findRequiredViewAsType(source, R.id.et_dsje, "field 'etDsje'", EditText.class);
    target.etOtherPrice = Utils.findRequiredViewAsType(source, R.id.et_other_price, "field 'etOtherPrice'", EditText.class);
    target.tvBtn1 = Utils.findRequiredViewAsType(source, R.id.tv_btn1, "field 'tvBtn1'", TextView.class);
    target.tvBtn2 = Utils.findRequiredViewAsType(source, R.id.tv_btn2, "field 'tvBtn2'", TextView.class);
    target.llBtm = Utils.findRequiredViewAsType(source, R.id.ll_btm, "field 'llBtm'", LinearLayout.class);
    target.cvPrice = Utils.findRequiredViewAsType(source, R.id.cv_price, "field 'cvPrice'", CardView.class);
    target.tvPayType = Utils.findRequiredViewAsType(source, R.id.tv_pay_type, "field 'tvPayType'", TextView.class);
    target.llKdxx = Utils.findRequiredViewAsType(source, R.id.ll_kdxx, "field 'llKdxx'", LinearLayout.class);
    target.tvExpress = Utils.findRequiredViewAsType(source, R.id.tv_express, "field 'tvExpress'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ExpressDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myToolbar = null;
    target.mMap = null;
    target.mNsw = null;
    target.tvGetAddress1 = null;
    target.tvGetAddress2 = null;
    target.tvToAddress1 = null;
    target.tvToAddress2 = null;
    target.tvContact = null;
    target.tvName = null;
    target.tvPutTime = null;
    target.tvGetAddress = null;
    target.tvRemark = null;
    target.etExpressCompany = null;
    target.etExpressNo = null;
    target.etWeight = null;
    target.etPrice = null;
    target.etPackagePrice = null;
    target.etBjAmount = null;
    target.etBjPrice = null;
    target.etDsje = null;
    target.etOtherPrice = null;
    target.tvBtn1 = null;
    target.tvBtn2 = null;
    target.llBtm = null;
    target.cvPrice = null;
    target.tvPayType = null;
    target.llKdxx = null;
    target.tvExpress = null;
  }
}
