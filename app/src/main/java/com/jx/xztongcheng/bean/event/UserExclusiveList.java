package com.jx.xztongcheng.bean.event;

import java.util.List;

public class UserExclusiveList {

    /**
     * totalCount : 3
     * list : [{"exclusiveId":6,"courierId":8,"userId":68,"createDate":"2021-03-13 00:00:00","courierUserId":0},{"exclusiveId":7,"courierId":2,"userId":62,"createDate":"2021-03-15 00:00:00","courierUserId":0},{"exclusiveId":8,"courierId":2,"userId":64,"createDate":"2021-03-15 00:00:00","courierUserId":0}]
     */

    private String totalCount;
    private List<ListBean> list;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
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
         * exclusiveId : 6
         * courierId : 8
         * userId : 68
         * createDate : 2021-03-13 00:00:00
         * courierUserId : 0
         */

        private String exclusiveId;
        private String courierId;
        private String userId;
        private String userName;
        private String userMobile;
        private String createDate;
        private String courierUserId;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserMobile() {
            return userMobile;
        }

        public void setUserMobile(String userMobile) {
            this.userMobile = userMobile;
        }

        public String getExclusiveId() {
            return exclusiveId;
        }

        public void setExclusiveId(String exclusiveId) {
            this.exclusiveId = exclusiveId;
        }

        public String getCourierId() {
            return courierId;
        }

        public void setCourierId(String courierId) {
            this.courierId = courierId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCourierUserId() {
            return courierUserId;
        }

        public void setCourierUserId(String courierUserId) {
            this.courierUserId = courierUserId;
        }
    }
}
