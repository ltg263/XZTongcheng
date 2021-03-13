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
    private int bankNo;
    private int cashOutType;
    private String createTime;
    private int id;
    private String mobile;
    private String name;
    private int status;
    private int userId;

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

    public int getBankNo() {
        return bankNo;
    }

    public void setBankNo(int bankNo) {
        this.bankNo = bankNo;
    }

    public int getCashOutType() {
        return cashOutType;
    }

    public void setCashOutType(int cashOutType) {
        this.cashOutType = cashOutType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
