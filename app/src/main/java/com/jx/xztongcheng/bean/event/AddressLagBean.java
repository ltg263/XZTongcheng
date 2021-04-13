package com.jx.xztongcheng.bean.event;

import java.util.List;

public class AddressLagBean {


    /**
     * count : 1
     * geocodes : [{"adcode":"330212","building":{"name":[],"type":[]},"city":"宁波市","citycode":"0574","country":"中国","district":"鄞州区","formatted_address":"浙江省宁波市鄞州区润玉园","level":"兴趣点","location":"121.612899,29.842965","neighborhood":{"name":[],"type":[]},"number":[],"province":"浙江省","street":[],"township":[]}]
     * info : OK
     * infocode : 10000
     * status : 1
     */

    private String count;
    private String info;
    private String infocode;
    private String status;
    private List<GeocodesBean> geocodes;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GeocodesBean> getGeocodes() {
        return geocodes;
    }

    public void setGeocodes(List<GeocodesBean> geocodes) {
        this.geocodes = geocodes;
    }

    public static class GeocodesBean {
        /**
         * adcode : 330212
         * building : {"name":[],"type":[]}
         * city : 宁波市
         * citycode : 0574
         * country : 中国
         * district : 鄞州区
         * formatted_address : 浙江省宁波市鄞州区润玉园
         * level : 兴趣点
         * location : 121.612899,29.842965
         * neighborhood : {"name":[],"type":[]}
         * number : []
         * province : 浙江省
         * street : []
         * township : []
         */

        private String adcode;
        private String city;
        private String citycode;
        private String country;
        private String district;
        private String formatted_address;
        private String level;
        private String location;
        private String province;

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }


        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

    }
}
