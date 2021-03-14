package com.jx.xztongcheng.bean.request;

public class RechargeSaveBean {

    /**
     * amount : 0
     * payType :
     * rechargeOrderId : 0
     * rechargeType : 0
     */

    private Integer amount;
    private String payType;
    private Integer rechargeOrderId;
    private Integer rechargeType;
    private Integer orderId;

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getRechargeOrderId() {
        return rechargeOrderId;
    }

    public void setRechargeOrderId(Integer rechargeOrderId) {
        this.rechargeOrderId = rechargeOrderId;
    }

    public Integer getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(Integer rechargeType) {
        this.rechargeType = rechargeType;
    }
}
