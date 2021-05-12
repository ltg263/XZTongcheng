package com.jx.xztongcheng.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Create by Sxl on 2020/12/5.
 */
public class OrderListBean implements Serializable {

    private int expressGeneralOrderId;
    private String generalOrderNo;
    private double lng;
    private double lat;
    private String createTime;
    private int createUserId;
    private int count;
    private int takeStatus;
    private int takeModel;
    private int expressType;
    private int profit;
    private int price;
    private int generalOrderAmount;
    private String updateTime;
    private int expressCreateType;
    private int fastStatus;
    private double totalAmount;
    private double totalBonus;
    private List<?> bonusOrderDTOList;
    private List<OrderListBean.ExpressOrderDTOSBean> expressOrderDTOS;
    private int nextTransportStatus;
    private String appointmentTime;
    private String weight;
    private String fee;
    private String packageFee;
    private String insuredFee;
    private String otherFee;
    private String expressNo;
    private String websiteName;
    private String cost;
    private int transferStatus;
    private String transferMobile;
    private double discount;
    private int isPrint = -1;

    private String couponAmount;
    private int calculationType;

    public void setCalculationType(int calculationType) {
        this.calculationType = calculationType;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public int getCalculationType() {
        return calculationType;
    }

    public String getCouponAmount() {
        return couponAmount;
    }
    public void setCouponAmount(String couponAmount) {
        this.couponAmount = couponAmount;
    }

    public void setIsPrint(int isPrint) {
        this.isPrint = isPrint;
    }

    public int getIsPrint() {
        return isPrint;
    }

    public int getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(int transferStatus) {
        this.transferStatus = transferStatus;
    }

    public String getTransferMobile() {
        return transferMobile;
    }

    public void setTransferMobile(String transferMobile) {
        this.transferMobile = transferMobile;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getPackageFee() {
        return packageFee;
    }

    public void setPackageFee(String packageFee) {
        this.packageFee = packageFee;
    }

    public String getInsuredFee() {
        return insuredFee;
    }

    public void setInsuredFee(String insuredFee) {
        this.insuredFee = insuredFee;
    }

    public String getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(String otherFee) {
        this.otherFee = otherFee;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }


    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }



    public int getNextTransportStatus() {
        return nextTransportStatus;
    }

    public void setNextTransportStatus(int nextTransportStatus) {
        this.nextTransportStatus = nextTransportStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalBonus() {
        return totalBonus;
    }

    public void setTotalBonus(double totalBonus) {
        this.totalBonus = totalBonus;
    }

    public int getExpressGeneralOrderId() {
        return expressGeneralOrderId;
    }

    public void setExpressGeneralOrderId(int expressGeneralOrderId) {
        this.expressGeneralOrderId = expressGeneralOrderId;
    }

    public String getGeneralOrderNo() {
        return generalOrderNo;
    }

    public void setGeneralOrderNo(String generalOrderNo) {
        this.generalOrderNo = generalOrderNo;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTakeStatus() {
        return takeStatus;
    }

    public void setTakeStatus(int takeStatus) {
        this.takeStatus = takeStatus;
    }

    public int getTakeModel() {
        return takeModel;
    }

    public void setTakeModel(int takeModel) {
        this.takeModel = takeModel;
    }

    public int getExpressType() {
        return expressType;
    }

    public void setExpressType(int expressType) {
        this.expressType = expressType;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGeneralOrderAmount() {
        return generalOrderAmount;
    }

    public void setGeneralOrderAmount(int generalOrderAmount) {
        this.generalOrderAmount = generalOrderAmount;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getExpressCreateType() {
        return expressCreateType;
    }

    public void setExpressCreateType(int expressCreateType) {
        this.expressCreateType = expressCreateType;
    }

    public int getFastStatus() {
        return fastStatus;
    }

    public void setFastStatus(int fastStatus) {
        this.fastStatus = fastStatus;
    }

    public List<?> getBonusOrderDTOList() {
        return bonusOrderDTOList;
    }

    public void setBonusOrderDTOList(List<?> bonusOrderDTOList) {
        this.bonusOrderDTOList = bonusOrderDTOList;
    }

    public List<OrderListBean.ExpressOrderDTOSBean> getExpressOrderDTOS() {
        return expressOrderDTOS;
    }

    public void setExpressOrderDTOS(List<OrderListBean.ExpressOrderDTOSBean> expressOrderDTOS) {
        this.expressOrderDTOS = expressOrderDTOS;
    }

    public static class ExpressDTOBean implements Serializable{

        private int amount;
        private int cost;
        private String createTime;
        private int distance;
        private String expressCategory;
        private int expressCategoryId;
        private int expressId;
        private String expressName;
        private String expressNo;
        private int expressSize;
        private int expressStatus;
        private int expressType;
        private String expressVolume;
        private double expressWeight;
        private int fastStatus;
        private int insuredFee;
        private int profit;
        private int proxyId;
        private String remark;
        private String sysRemrk;
        private int transportStatus;
        private String transportStatusStr;
        private String updateTime;
        private int userId;
        private int websiteId;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public String getExpressCategory() {
            return expressCategory;
        }

        public void setExpressCategory(String expressCategory) {
            this.expressCategory = expressCategory;
        }

        public int getExpressCategoryId() {
            return expressCategoryId;
        }

        public void setExpressCategoryId(int expressCategoryId) {
            this.expressCategoryId = expressCategoryId;
        }

        public int getExpressId() {
            return expressId;
        }

        public void setExpressId(int expressId) {
            this.expressId = expressId;
        }

        public String getExpressName() {
            return expressName;
        }

        public void setExpressName(String expressName) {
            this.expressName = expressName;
        }

        public String getExpressNo() {
            return expressNo;
        }

        public void setExpressNo(String expressNo) {
            this.expressNo = expressNo;
        }

        public int getExpressSize() {
            return expressSize;
        }

        public void setExpressSize(int expressSize) {
            this.expressSize = expressSize;
        }

        public int getExpressStatus() {
            return expressStatus;
        }

        public void setExpressStatus(int expressStatus) {
            this.expressStatus = expressStatus;
        }

        public int getExpressType() {
            return expressType;
        }

        public void setExpressType(int expressType) {
            this.expressType = expressType;
        }

        public String getExpressVolume() {
            return expressVolume;
        }

        public void setExpressVolume(String expressVolume) {
            this.expressVolume = expressVolume;
        }

        public double getExpressWeight() {
            return expressWeight;
        }

        public void setExpressWeight(double expressWeight) {
            this.expressWeight = expressWeight;
        }

        public int getFastStatus() {
            return fastStatus;
        }

        public void setFastStatus(int fastStatus) {
            this.fastStatus = fastStatus;
        }

        public int getInsuredFee() {
            return insuredFee;
        }

        public void setInsuredFee(int insuredFee) {
            this.insuredFee = insuredFee;
        }

        public int getProfit() {
            return profit;
        }

        public void setProfit(int profit) {
            this.profit = profit;
        }

        public int getProxyId() {
            return proxyId;
        }

        public void setProxyId(int proxyId) {
            this.proxyId = proxyId;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getSysRemrk() {
            return sysRemrk;
        }

        public void setSysRemrk(String sysRemrk) {
            this.sysRemrk = sysRemrk;
        }

        public int getTransportStatus() {
            return transportStatus;
        }

        public void setTransportStatus(int transportStatus) {
            this.transportStatus = transportStatus;
        }

        public String getTransportStatusStr() {
            return transportStatusStr;
        }

        public void setTransportStatusStr(String transportStatusStr) {
            this.transportStatusStr = transportStatusStr;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWebsiteId() {
            return websiteId;
        }

        public void setWebsiteId(int websiteId) {
            this.websiteId = websiteId;
        }
    }
    public static class ExpressOrderDTOSBean implements Serializable{

        private String advertisingImage;
        private int expressOrderId;
        private String orderNo;
        private String code;
        private double realAmount;
        private String createTime;
        private int codeStatus;
        private String images;
        private String remark;
        private int categoryId;
        private String appointmentTime;
        private int expressGeneralOrderId;
        private int delTf;
        private int userId;
        private int expressId;
        private double weight;
        private double fee;
        private double packageFee;
        private double insuredFee;
        private double otherFee;
        private int checkStatus;
        private String updateTime;
        private int cancelUserId;
        private String reason;
        private int orderStatus;
        private int mailType;
        private int insuredStatus;
        private int courierUserId;
        private int profit;
        private int expressOrderType;
        private ExpressAddressDTOS expressAddressDTOS;
        private String payStatus;
        private String waybillNumber;
        private String payType;
        private String money;
        private ExpressDTOBean expressDTO;
        private String totalBonus;
        private String totalAmount;

        public void setExpressOrderType(int expressOrderType) {
            this.expressOrderType = expressOrderType;
        }

        public int getExpressOrderType() {
            return expressOrderType;
        }

        public String getAdvertisingImage() {
            return advertisingImage;
        }

        public void setAdvertisingImage(String advertisingImage) {
            this.advertisingImage = advertisingImage;
        }
        public String getWaybillNumber() {
            return waybillNumber;
        }

        public void setWaybillNumber(String waybillNumber) {
            this.waybillNumber = waybillNumber;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMoney() {
            return money;
        }

        public String getTotalBonus() {
            return totalBonus;
        }

        public void setTotalBonus(String totalBonus) {
            this.totalBonus = totalBonus;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public ExpressDTOBean getExpressDTO() {
            return expressDTO;
        }

        public void setExpressDTO(ExpressDTOBean expressDTO) {
            this.expressDTO = expressDTO;
        }


        public int getExpressOrderId() {
            return expressOrderId;
        }

        public void setExpressOrderId(int expressOrderId) {
            this.expressOrderId = expressOrderId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public double getRealAmount() {
            return realAmount;
        }

        public void setRealAmount(double realAmount) {
            this.realAmount = realAmount;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCodeStatus() {
            return codeStatus;
        }

        public void setCodeStatus(int codeStatus) {
            this.codeStatus = codeStatus;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public String getAppointmentTime() {
            return appointmentTime;
        }

        public void setAppointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
        }

        public int getExpressGeneralOrderId() {
            return expressGeneralOrderId;
        }

        public void setExpressGeneralOrderId(int expressGeneralOrderId) {
            this.expressGeneralOrderId = expressGeneralOrderId;
        }

        public int getDelTf() {
            return delTf;
        }

        public void setDelTf(int delTf) {
            this.delTf = delTf;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getExpressId() {
            return expressId;
        }

        public void setExpressId(int expressId) {
            this.expressId = expressId;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public double getPackageFee() {
            return packageFee;
        }

        public void setPackageFee(double packageFee) {
            this.packageFee = packageFee;
        }

        public double getInsuredFee() {
            return insuredFee;
        }

        public void setInsuredFee(double insuredFee) {
            this.insuredFee = insuredFee;
        }

        public double getOtherFee() {
            return otherFee;
        }

        public void setOtherFee(double otherFee) {
            this.otherFee = otherFee;
        }

        public int getCheckStatus() {
            return checkStatus;
        }

        public void setCheckStatus(int checkStatus) {
            this.checkStatus = checkStatus;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getCancelUserId() {
            return cancelUserId;
        }

        public void setCancelUserId(int cancelUserId) {
            this.cancelUserId = cancelUserId;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getMailType() {
            return mailType;
        }

        public void setMailType(int mailType) {
            this.mailType = mailType;
        }

        public int getInsuredStatus() {
            return insuredStatus;
        }

        public void setInsuredStatus(int insuredStatus) {
            this.insuredStatus = insuredStatus;
        }

        public int getCourierUserId() {
            return courierUserId;
        }

        public void setCourierUserId(int courierUserId) {
            this.courierUserId = courierUserId;
        }

        public int getProfit() {
            return profit;
        }

        public void setProfit(int profit) {
            this.profit = profit;
        }

        public ExpressAddressDTOS getExpressAddressDTOS() {
            return expressAddressDTOS;
        }

        public void setExpressAddressDTOS(ExpressAddressDTOS expressAddressDTOS) {
            this.expressAddressDTOS = expressAddressDTOS;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }
    }
}

