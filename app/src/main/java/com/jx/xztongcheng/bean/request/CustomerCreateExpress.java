package com.jx.xztongcheng.bean.request;

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
