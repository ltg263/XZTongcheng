package com.jx.xztongcheng.bean.response;

import com.jx.xztongcheng.net.BaseResponse;

/**
 * Create by Sxl on 2020/11/20.
 */
public class LoginResponse extends BaseResponse {

    /**
     * access_token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mbyI6IntcImF2YXRhclwiOlwiaHR0cHM6Ly9zczIuYmRzdGF0aWMuY29tLzcwY0Z2blNoX1ExWW54R2twb1dLMUhGNmhoeS9pdC91PTEzNjE3MTM1ODcsMjkyMDIwNjIwMyZmbT0xMSZncD0wLmpwZ1wiLFwiY3JlYXRlVGltZVwiOjE2MDU4Mzg2NDcwMDAsXCJzdGF0dXNcIjoxLFwidXBkYXRlVGltZVwiOjE2MDU4Mzg2NDcwMDAsXCJ1c2VySWRcIjoxNCxcInVzZXJuYW1lXCI6XCIxODc2ODUxNTUxMFwifSIsImV4cCI6MTYwNTg0Nzk1MiwidXNlcl9uYW1lIjoie1widXNlcm5hbWVUeXBlXCI6MSxcInVzZXJuYW1lXCI6XCIxODc2ODUxNTUxMFwifSIsImp0aSI6ImNLQl9VYUhTX0Z6ekNaVDVqcFBlanh1RVlJayIsImNsaWVudF9pZCI6InRlc3RfY2xpZW50Iiwic2NvcGUiOlsicmVhZCJdfQ.FHDfyN2ASCvWrd3j-IiizVz7I07OZlhU7eBdla7nlWw
     * token_type : bearer
     * refresh_token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySW5mbyI6IntcImF2YXRhclwiOlwiaHR0cHM6Ly9zczIuYmRzdGF0aWMuY29tLzcwY0Z2blNoX1ExWW54R2twb1dLMUhGNmhoeS9pdC91PTEzNjE3MTM1ODcsMjkyMDIwNjIwMyZmbT0xMSZncD0wLmpwZ1wiLFwiY3JlYXRlVGltZVwiOjE2MDU4Mzg2NDcwMDAsXCJzdGF0dXNcIjoxLFwidXBkYXRlVGltZVwiOjE2MDU4Mzg2NDcwMDAsXCJ1c2VySWRcIjoxNCxcInVzZXJuYW1lXCI6XCIxODc2ODUxNTUxMFwifSIsInVzZXJfbmFtZSI6IntcInVzZXJuYW1lVHlwZVwiOjEsXCJ1c2VybmFtZVwiOlwiMTg3Njg1MTU1MTBcIn0iLCJzY29wZSI6WyJyZWFkIl0sImF0aSI6ImNLQl9VYUhTX0Z6ekNaVDVqcFBlanh1RVlJayIsImV4cCI6MTYwNTk0ODc1MiwianRpIjoianNSLU9qNlVzczNXMTZzQTFPcFB3dWpzWURrIiwiY2xpZW50X2lkIjoidGVzdF9jbGllbnQifQ.vV-I54xUeIdCokKKICtWdyzoFzCTGK3tP-e0ycFEqmg
     * expires_in : 7199
     * userInfo : {"avatar":"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1361713587,2920206203&fm=11&gp=0.jpg","createTime":1605838647000,"status":1,"updateTime":1605838647000,"userId":14,"username":"18768515510"}
     */

    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String userInfo;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
}
