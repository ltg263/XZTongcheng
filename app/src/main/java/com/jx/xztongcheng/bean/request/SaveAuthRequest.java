package com.jx.xztongcheng.bean.request;

/**
 * Create by Sxl on 2020/12/2.
 */
public class SaveAuthRequest {

    /**
     * courierAuthId : 0
     * courierAuthInfoForm : {"idCardUrl":"","idCardUrlBack":"","idcard":"","name":""}
     */

    private int courierAuthId;
    private CourierAuthInfoFormBean courierAuthInfoForm;

    public int getCourierAuthId() {
        return courierAuthId;
    }

    public void setCourierAuthId(int courierAuthId) {
        this.courierAuthId = courierAuthId;
    }

    public CourierAuthInfoFormBean getCourierAuthInfoForm() {
        return courierAuthInfoForm;
    }

    public void setCourierAuthInfoForm(CourierAuthInfoFormBean courierAuthInfoForm) {
        this.courierAuthInfoForm = courierAuthInfoForm;
    }

    public static class CourierAuthInfoFormBean {

        private String idCardUrl;
        private String idCardUrlBack;
        private String idcard;
        private String name;

        public String getIdCardUrl() {
            return idCardUrl;
        }

        public void setIdCardUrl(String idCardUrl) {
            this.idCardUrl = idCardUrl;
        }

        public String getIdCardUrlBack() {
            return idCardUrlBack;
        }

        public void setIdCardUrlBack(String idCardUrlBack) {
            this.idCardUrlBack = idCardUrlBack;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
