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
    private String money;
    private Integer rechargeType;
    private Integer orderId;
    private Integer expressOrderId;

    public void setExpressOrderId(Integer expressOrderId) {
        this.expressOrderId = expressOrderId;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getExpressOrderId() {
        return expressOrderId;
    }

    public String getMoney() {
        return money;
    }

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
