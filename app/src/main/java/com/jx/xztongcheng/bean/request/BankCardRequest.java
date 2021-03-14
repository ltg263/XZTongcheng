package com.jx.xztongcheng.bean.request;


/**
 * Create by Sxl on 2020/12/21.
 */
public class BankCardRequest {

    /**
     * accountNo :
     * bank :
     * bankNo : 0
     * cashOutType : 0
     * createTime :
     * id : 0
     * mobile : 0
     * name :
     * status : 0
     * userId : 0
     */

    private String accountNo;
    private String bank;
    private Integer bankNo;
    private Integer cashOutType;
    private String createTime;
    private Integer id;
    private String mobile;
    private String name;
    private Integer status;
    private Integer userId;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Integer getBankNo() {
        return bankNo;
    }

    public void setBankNo(Integer bankNo) {
        this.bankNo = bankNo;
    }

    public Integer getCashOutType() {
        return cashOutType;
    }

    public void setCashOutType(Integer cashOutType) {
        this.cashOutType = cashOutType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
