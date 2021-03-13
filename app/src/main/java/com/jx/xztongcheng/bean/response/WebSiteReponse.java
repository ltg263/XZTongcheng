package com.jx.xztongcheng.bean.response;

import java.util.List;

/**
 * Create by Sxl on 2020/12/7.
 */
public class WebSiteReponse {

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
         * websiteId : 1
         * websiteNo : 0011
         * websiteAddress : 宁波
         * lng : 121.549792
         * lat : 29.868388
         * regionId : 330000
         * cityId : 330200
         * provinceId : 330212
         * poxryId : 1
         * websiteDate :
         * webstieStartTime : 8:00:00
         * websiteEndTime : 18:00:00
         * status : 1
         * websiteStatus : 1
         * dispatchStatus : 1
         * adminId : 3
         * createTime : 2020-11-23 11:38:55
         * leaderName : weng
         * leaderMobile : 18458794212
         * contactInformation :
         */

        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }


        private int websiteId;
        private String websiteNo;
        private String websiteAddress;
        private double lng;
        private double lat;
        private int regionId;
        private int cityId;
        private int provinceId;
        private int poxryId;
        private String websiteDate;
        private String webstieStartTime;
        private String websiteEndTime;
        private int status;
        private int websiteStatus;
        private int dispatchStatus;
        private int adminId;
        private String createTime;
        private String leaderName;
        private String leaderMobile;
        private String contactInformation;
        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWebsiteId() {
            return websiteId;
        }

        public void setWebsiteId(int websiteId) {
            this.websiteId = websiteId;
        }

        public String getWebsiteNo() {
            return websiteNo;
        }

        public void setWebsiteNo(String websiteNo) {
            this.websiteNo = websiteNo;
        }

        public String getWebsiteAddress() {
            return websiteAddress;
        }

        public void setWebsiteAddress(String websiteAddress) {
            this.websiteAddress = websiteAddress;
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

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getPoxryId() {
            return poxryId;
        }

        public void setPoxryId(int poxryId) {
            this.poxryId = poxryId;
        }

        public String getWebsiteDate() {
            return websiteDate;
        }

        public void setWebsiteDate(String websiteDate) {
            this.websiteDate = websiteDate;
        }

        public String getWebstieStartTime() {
            return webstieStartTime;
        }

        public void setWebstieStartTime(String webstieStartTime) {
            this.webstieStartTime = webstieStartTime;
        }

        public String getWebsiteEndTime() {
            return websiteEndTime;
        }

        public void setWebsiteEndTime(String websiteEndTime) {
            this.websiteEndTime = websiteEndTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getWebsiteStatus() {
            return websiteStatus;
        }

        public void setWebsiteStatus(int websiteStatus) {
            this.websiteStatus = websiteStatus;
        }

        public int getDispatchStatus() {
            return dispatchStatus;
        }

        public void setDispatchStatus(int dispatchStatus) {
            this.dispatchStatus = dispatchStatus;
        }

        public int getAdminId() {
            return adminId;
        }

        public void setAdminId(int adminId) {
            this.adminId = adminId;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getLeaderName() {
            return leaderName;
        }

        public void setLeaderName(String leaderName) {
            this.leaderName = leaderName;
        }

        public String getLeaderMobile() {
            return leaderMobile;
        }

        public void setLeaderMobile(String leaderMobile) {
            this.leaderMobile = leaderMobile;
        }

        public String getContactInformation() {
            return contactInformation;
        }

        public void setContactInformation(String contactInformation) {
            this.contactInformation = contactInformation;
        }
    }
}
