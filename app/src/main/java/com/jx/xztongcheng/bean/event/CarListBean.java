package com.jx.xztongcheng.bean.event;

import java.util.List;

public class CarListBean {
    /**
     * totalCount : 2
     * list : [{"websiteCarId":1,"websiteCarNo":"34234523","driverName":"蒋1","driverMobile":"18768515510","plateNumber":"浙B7788","websiteId":29,"delTf":0,"createTime":"2021-03-13 20:29:17"},{"websiteCarId":3,"websiteCarNo":"342344234","driverName":"蒋3","driverMobile":"1233452454","plateNumber":"浙B9910","websiteId":29,"delTf":0,"createTime":"2021-03-13 20:29:17"}]
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
         * websiteCarId : 1
         * websiteCarNo : 34234523
         * driverName : 蒋1
         * driverMobile : 18768515510
         * plateNumber : 浙B7788
         * websiteId : 29
         * delTf : 0
         * createTime : 2021-03-13 20:29:17
         */

        private String websiteCarId;
        private String websiteCarNo;
        private String driverName;
        private String driverMobile;
        private String plateNumber;
        private String websiteId;
        private String delTf;
        private String createTime;

        public String getWebsiteCarId() {
            return websiteCarId;
        }

        public void setWebsiteCarId(String websiteCarId) {
            this.websiteCarId = websiteCarId;
        }

        public String getWebsiteCarNo() {
            return websiteCarNo;
        }

        public void setWebsiteCarNo(String websiteCarNo) {
            this.websiteCarNo = websiteCarNo;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getDriverMobile() {
            return driverMobile;
        }

        public void setDriverMobile(String driverMobile) {
            this.driverMobile = driverMobile;
        }

        public String getPlateNumber() {
            return plateNumber;
        }

        public void setPlateNumber(String plateNumber) {
            this.plateNumber = plateNumber;
        }

        public String getWebsiteId() {
            return websiteId;
        }

        public void setWebsiteId(String websiteId) {
            this.websiteId = websiteId;
        }

        public String getDelTf() {
            return delTf;
        }

        public void setDelTf(String delTf) {
            this.delTf = delTf;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
