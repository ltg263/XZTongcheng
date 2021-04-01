package com.jx.xztongcheng.bean.request;

import com.jx.xztongcheng.net.BaseResponse;

/**
 * Create by Sxl on 2020/11/20.
 */
public class LoginRequest extends BaseResponse {

    private String grantType;
    private String password;
    private String tokenInfo;
    private String username;
    private String verifyCode;
    private String androidId;

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getAndroidId() {
        return androidId;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
