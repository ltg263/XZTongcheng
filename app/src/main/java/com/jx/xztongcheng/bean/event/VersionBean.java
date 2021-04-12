package com.jx.xztongcheng.bean.event;

public class VersionBean {

    /**
     * content : 123
     * createTime : 2021-03-25 14:59:06
     * fileUrl : https://www.juxiangnb.com/zhuangxiu/upload/45EB4B32170446A015C0CB3F087FD0F0.docx
     * id : 1902
     * type : 1
     * typeStr : 安卓
     * version : 1.0.0
     */

    private String content;
    private String createTime;
    private String fileUrl;
    private int id;
    private int type;
    private String typeStr;
    private String version;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
