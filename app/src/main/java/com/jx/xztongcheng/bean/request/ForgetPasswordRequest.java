package com.jx.xztongcheng.bean.request;

/**
 * Create by Sxl on 2020/12/7.
 */
public class ForgetPasswordRequest {


    /**
     * password :
     * smsCode :
     * username :
     * usernameType : 0
     */

    private String password;
    private String smsCode;
    private String username;
    private int usernameType;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUsernameType() {
        return usernameType;
    }

    public void setUsernameType(int usernameType) {
        this.usernameType = usernameType;
    }
}
