package com.jx.xztongcheng.bean.clazz;

import com.google.gson.annotations.SerializedName;

/**
 * Create by Sxl on 2020/11/20.
 */
public class UserInfo {

    private String avatar;
    private String createTime;
    private int status;
    private String updateTime;
    private int userId;
    private String username;
    private String nickname;
    private int cityId;
    private int provinceId;
    private String city;
    private String province;
    private int sex;
    private String token;
    private int authStatus;
    private String todayCount;
    private String todayAmont;
    private String huamanCount;
    private String balance;

    public String getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(String todayCount) {
        this.todayCount = todayCount;
    }

    public String getTodayAmont() {
        return todayAmont;
    }

    public void setTodayAmont(String todayAmont) {
        this.todayAmont = todayAmont;
    }

    public String getHuamanCount() {
        return huamanCount;
    }

    public void setHuamanCount(String huamanCount) {
        this.huamanCount = huamanCount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
