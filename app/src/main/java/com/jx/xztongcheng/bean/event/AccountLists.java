package com.jx.xztongcheng.bean.event;

import java.io.Serializable;
import java.util.List;

public class AccountLists implements Serializable {

    /**
     * totalCount : 1
     * list : [{"id":3,"cashOutType":1,"userId":51,"accountNo":"2345667uu","name":"566778","bank":"乳房吞吞吐吐","createTime":"2021-03-14 15:58:04","status":1,"bankNo":"乳房吞吞吐吐","mobile":"12356"}]
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

    public static class ListBean implements Serializable {
        /**
         * id : 3
         * cashOutType : 1
         * userId : 51
         * accountNo : 2345667uu
         * name : 566778
         * bank : 乳房吞吞吐吐
         * createTime : 2021-03-14 15:58:04
         * status : 1
         * bankNo : 乳房吞吞吐吐
         * mobile : 12356
         */

        private int id;
        private int cashOutType;
        private int userId;
        private String accountNo;
        private String name;
        private String bank;
        private String createTime;
        private int status;
        private String bankNo;
        private String mobile;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCashOutType() {
            return cashOutType;
        }

        public void setCashOutType(int cashOutType) {
            this.cashOutType = cashOutType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getBankNo() {
            return bankNo;
        }

        public void setBankNo(String bankNo) {
            this.bankNo = bankNo;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
