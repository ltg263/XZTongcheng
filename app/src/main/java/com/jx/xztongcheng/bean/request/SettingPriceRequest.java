package com.jx.xztongcheng.bean.request;

/**
 * Create by Sxl on 2020/12/5.
 */
public class SettingPriceRequest {
    /**
     * fee : 0
     * insuredAmount : 0
     * insuredFee : 0
     * orderId : 0
     * otherFee : 0
     * packageFee : 0
     * payType :
     * weight : 0
     */

    private String fee;
    private String insuredAmount;
    private String insuredFee;
    private int orderId;
    private String otherFee;
    private String packageFee;
    private String payType;
    private String weight;
    private String totalBonus;

    public String getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(String totalBonus) {
        this.totalBonus = totalBonus;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getInsuredAmount() {
        return insuredAmount;
    }

    public void setInsuredAmount(String insuredAmount) {
        this.insuredAmount = insuredAmount;
    }

    public String getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(String insuredFee) {
        this.insuredFee = insuredFee;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(String otherFee) {
        this.otherFee = otherFee;
    }

    public String getPackageFee() {
        return packageFee;
    }

    public void setPackageFee(String packageFee) {
        this.packageFee = packageFee;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
