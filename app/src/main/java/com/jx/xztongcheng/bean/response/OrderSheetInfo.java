package com.jx.xztongcheng.bean.response;

public class OrderSheetInfo {

    /**
     * websiteNo : 990101
     * websiteName : 鄞州钟公庙中心站
     * parcelAffiliation : 宁波分拨包
     * orderNo : EO210312193917010039
     * consigneeName : 程真
     * consigneeMobile : 13512345678
     * consigneeAddress : 浙江省-宁波市-海曙区,顺德华庭(宁波市海曙区顺德路318号)408
     * mailingName : 程
     * mailingMobile : 15827313949
     * mailingAddress : 浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,浙江省-宁波市-鄞州区,鄞州科技信息孵化园(宁波市鄞州区)203
     * expressName : 哦哦哦
     * expressNum : 1
     * expressWeight : 1
     */

    private String websiteNo;
    private String websiteName;
    private String parcelAffiliation;
    private String orderNo;
    private String consigneeName;
    private String consigneeMobile;
    private String consigneeAddress;
    private String mailingName;
    private String mailingMobile;
    private String mailingAddress;
    private String expressName;
    private String expressNum;
    private String expressWeight;
    private String waybillNumber;
    private String amount;
    private String type;//1线上(不用操作)2现金支付3到付4待收货跨

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setWaybillNumber(String waybillNumber) {
        this.waybillNumber = waybillNumber;
    }

    public String getWaybillNumber() {
        return waybillNumber;
    }

    public String getWebsiteNo() {
        return websiteNo;
    }

    public void setWebsiteNo(String websiteNo) {
        this.websiteNo = websiteNo;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getParcelAffiliation() {
        return parcelAffiliation;
    }

    public void setParcelAffiliation(String parcelAffiliation) {
        this.parcelAffiliation = parcelAffiliation;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getMailingName() {
        return mailingName;
    }

    public void setMailingName(String mailingName) {
        this.mailingName = mailingName;
    }

    public String getMailingMobile() {
        return mailingMobile;
    }

    public void setMailingMobile(String mailingMobile) {
        this.mailingMobile = mailingMobile;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    public String getExpressWeight() {
        return expressWeight;
    }

    public void setExpressWeight(String expressWeight) {
        this.expressWeight = expressWeight;
    }

    @Override
    public String toString() {
        return "OrderSheetInfo{" +
                "websiteNo='" + websiteNo + '\'' +
                ", websiteName='" + websiteName + '\'' +
                ", parcelAffiliation='" + parcelAffiliation + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", consigneeName='" + consigneeName + '\'' +
                ", consigneeMobile='" + consigneeMobile + '\'' +
                ", consigneeAddress='" + consigneeAddress + '\'' +
                ", mailingName='" + mailingName + '\'' +
                ", mailingMobile='" + mailingMobile + '\'' +
                ", mailingAddress='" + mailingAddress + '\'' +
                ", expressName='" + expressName + '\'' +
                ", expressNum='" + expressNum + '\'' +
                ", expressWeight='" + expressWeight + '\'' +
                '}';
    }
}
