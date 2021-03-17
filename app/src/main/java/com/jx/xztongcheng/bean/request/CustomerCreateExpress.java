package com.jx.xztongcheng.bean.request;

import java.math.BigDecimal;
import java.util.List;

public class CustomerCreateExpress {

    /**
     * confirm : 0
     * cost : 0
     * expressBusinessType : 0
     * expressInfoForms : [{"amount":0,"cost":0,"distance":0,"endAddressId":0,"expressName":"","images":[],"insuredFee":0,"totalBonus":0,"weight":0}]
     * fastStatus : 0
     * insuredFee : 0
     * insuredStatus : 0
     * mailType : 0
     * orderType : 0
     * pickUpTime : 
     * remark : 
     * startAddressId : 0
     */

    private String confirm;
    private String cost;
    private String expressBusinessType;
    private String fastStatus;
    private String insuredFee;
    private String insuredStatus;
    private String mailType;
    private String orderType;
    private String pickUpTime;
    private String remark;
    private String startAddressId;
    private List<ExpressInfoFormsBean> expressInfoForms;
    /**
     * 寄件姓名
     */
//    @ApiModelProperty("寄件姓名")
    String mailName;
    /**
     * 寄件手机号
     */
//    @ApiModelProperty("寄件手机号")
    String mailMobile;
    /**
     * 寄件地址
     */
//    @ApiModelProperty("寄件地址")
    String mailAddress;
    /**
     * 寄件省份
     */
//    @ApiModelProperty("寄件省份")
    String mailProvince;
    /**
     * 寄件城市
     */
//    @ApiModelProperty("寄件城市")
    String mailCity;
    /**
     * 寄件地区
     */
//    @ApiModelProperty("寄件地区")
    String mailDistrict;
    /**
     * 寄件经度
     */
//    @ApiModelProperty("寄件经度")
    BigDecimal mailLng;
    /**
     * 寄件纬度
     */
//    @ApiModelProperty("寄件纬度")
    BigDecimal mailLat;
    /**
     * 收件姓名
     */
//    @ApiModelProperty("收件姓名")
    String toName;
    /**
     * 收件手机号
     */
//    @ApiModelProperty("收件手机号")
    String toMobile;
    /**
     * 收件地址
     */
//    @ApiModelProperty("收件地址")
    String toAddress;
    /**
     * 收件省份
     */
//    @ApiModelProperty("收件省份")
    String toProvince;
    /**
     * 收件城市
     */
//    @ApiModelProperty("收件城市")
    String toCity;
    /**
     * 收件地区
     */
//    @ApiModelProperty("收件地区")
    String toDistrict;
    /**
     * 收件经度
     */
//    @ApiModelProperty("收件经度")
    BigDecimal toLng;
    /**
     * 收件纬度
     */
//    @ApiModelProperty("收件纬度")
    BigDecimal toLat;


    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }

    public String getMailMobile() {
        return mailMobile;
    }

    public void setMailMobile(String mailMobile) {
        this.mailMobile = mailMobile;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMailProvince() {
        return mailProvince;
    }

    public void setMailProvince(String mailProvince) {
        this.mailProvince = mailProvince;
    }

    public String getMailCity() {
        return mailCity;
    }

    public void setMailCity(String mailCity) {
        this.mailCity = mailCity;
    }

    public String getMailDistrict() {
        return mailDistrict;
    }

    public void setMailDistrict(String mailDistrict) {
        this.mailDistrict = mailDistrict;
    }

    public BigDecimal getMailLng() {
        return mailLng;
    }

    public void setMailLng(BigDecimal mailLng) {
        this.mailLng = mailLng;
    }

    public BigDecimal getMailLat() {
        return mailLat;
    }

    public void setMailLat(BigDecimal mailLat) {
        this.mailLat = mailLat;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToMobile() {
        return toMobile;
    }

    public void setToMobile(String toMobile) {
        this.toMobile = toMobile;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getToProvince() {
        return toProvince;
    }

    public void setToProvince(String toProvince) {
        this.toProvince = toProvince;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getToDistrict() {
        return toDistrict;
    }

    public void setToDistrict(String toDistrict) {
        this.toDistrict = toDistrict;
    }

    public BigDecimal getToLng() {
        return toLng;
    }

    public void setToLng(BigDecimal toLng) {
        this.toLng = toLng;
    }

    public BigDecimal getToLat() {
        return toLat;
    }

    public void setToLat(BigDecimal toLat) {
        this.toLat = toLat;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getExpressBusinessType() {
        return expressBusinessType;
    }

    public void setExpressBusinessType(String expressBusinessType) {
        this.expressBusinessType = expressBusinessType;
    }

    public String getFastStatus() {
        return fastStatus;
    }

    public void setFastStatus(String fastStatus) {
        this.fastStatus = fastStatus;
    }

    public String getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(String insuredFee) {
        this.insuredFee = insuredFee;
    }

    public String getInsuredStatus() {
        return insuredStatus;
    }

    public void setInsuredStatus(String insuredStatus) {
        this.insuredStatus = insuredStatus;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStartAddressId() {
        return startAddressId;
    }

    public void setStartAddressId(String startAddressId) {
        this.startAddressId = startAddressId;
    }

    public List<ExpressInfoFormsBean> getExpressInfoForms() {
        return expressInfoForms;
    }

    public void setExpressInfoForms(List<ExpressInfoFormsBean> expressInfoForms) {
        this.expressInfoForms = expressInfoForms;
    }

    public static class ExpressInfoFormsBean {
        /**
         * amount : 0
         * cost : 0
         * distance : 0
         * endAddressId : 0
         * expressName : 
         * images : []
         * insuredFee : 0
         * totalBonus : 0
         * weight : 0
         */

        private String amount;
        private String cost;
        private String distance;
        private String endAddressId;
        private String expressName;
        private String insuredFee;
        private String totalBonus;
        private String weight;
        private List<?> images;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getEndAddressId() {
            return endAddressId;
        }

        public void setEndAddressId(String endAddressId) {
            this.endAddressId = endAddressId;
        }

        public String getExpressName() {
            return expressName;
        }

        public void setExpressName(String expressName) {
            this.expressName = expressName;
        }

        public String getInsuredFee() {
            return insuredFee;
        }

        public void setInsuredFee(String insuredFee) {
            this.insuredFee = insuredFee;
        }

        public String getTotalBonus() {
            return totalBonus;
        }

        public void setTotalBonus(String totalBonus) {
            this.totalBonus = totalBonus;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public List<?> getImages() {
            return images;
        }

        public void setImages(List<?> images) {
            this.images = images;
        }
    }
}
