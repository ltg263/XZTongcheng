package com.jx.xztongcheng.bean.response;

import java.util.List;

/**
 * Create by Sxl on 2020/12/13.
 */
public class BannerListResponse {

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
         * adId : 2
         * image : https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3271541657,1775833159&fm=26&gp=0.jpg
         * title : 2222
         * seat : 1
         * message : 1
         * type : 4
         * createTime : 2020-11-30 13:24:44
         * orderNo : 0
         * delTf : 0
         * status : 1
         */

        private int adId;
        private String image;
        private String title;
        private int seat;
        private String message;
        private int type;
        private String createTime;
        private int orderNo;
        private int delTf;
        private int status;

        public int getAdId() {
            return adId;
        }

        public void setAdId(int adId) {
            this.adId = adId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(int orderNo) {
            this.orderNo = orderNo;
        }

        public int getDelTf() {
            return delTf;
        }

        public void setDelTf(int delTf) {
            this.delTf = delTf;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
