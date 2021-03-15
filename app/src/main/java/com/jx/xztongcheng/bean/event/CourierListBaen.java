package com.jx.xztongcheng.bean.event;

import java.util.List;

public class CourierListBaen {

    /**
     * totalCount : 2
     * list : [{"cashoutId":1,"orderNo":"52345345","userId":51,"amount":10,"fee":0,"type":1,"accountNo":"52345234","name":"哈哈哈哈","bankName":"中国","bankCode":"2543345","mobile":"25345","status":3,"createTime":"2021-03-01 19:15:18","handlerTime":"2021-03-01 19:15:24","reason":"1111","realAmount":10,"adminId":5},{"cashoutId":2,"orderNo":"435234","userId":51,"amount":100,"fee":1,"type":1,"accountNo":"3452452345","name":"上飞机啊","bankName":"中国","bankCode":"523452","mobile":"253452","status":4,"createTime":"2021-03-01 19:22:24","handlerTime":"2021-03-01 19:22:29","reason":"","realAmount":99,"adminId":5}]
     */

    private int totalCount;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * cashoutId : 1
         * orderNo : 52345345
         * userId : 51
         * amount : 10
         * fee : 0
         * type : 1
         * accountNo : 52345234
         * name : 哈哈哈哈
         * bankName : 中国
         * bankCode : 2543345
         * mobile : 25345
         * status : 3
         * createTime : 2021-03-01 19:15:18
         * handlerTime : 2021-03-01 19:15:24
         * reason : 1111
         * realAmount : 10
         * adminId : 5
         */

        private int cashoutId;
        private String orderNo;
        private int userId;
        private int amount;
        private int fee;
        private int type;
        private String accountNo;
        private String name;
        private String bankName;
        private String bankCode;
        private String mobile;
        private int status;
        private String createTime;
        private String handlerTime;
        private String reason;
        private int realAmount;
        private int adminId;

        public int getCashoutId() {
            return cashoutId;
        }

        public void setCashoutId(int cashoutId) {
            this.cashoutId = cashoutId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getHandlerTime() {
            return handlerTime;
        }

        public void setHandlerTime(String handlerTime) {
            this.handlerTime = handlerTime;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getRealAmount() {
            return realAmount;
        }

        public void setRealAmount(int realAmount) {
            this.realAmount = realAmount;
        }

        public int getAdminId() {
            return adminId;
        }

        public void setAdminId(int adminId) {
            this.adminId = adminId;
        }
    }
}
