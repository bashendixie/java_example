package com.home.skydance.entity;

import java.util.Date;

public class User {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户类型
     */
    private Short userFlag;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 处理状态（这里指定时任务的处理状态）
     * 0未处理 1已处理
     */
    private Short actionFlag;
    /**
     * 定时任务需要计算的值
     */
    private int svdValue;
    /**
     * 定时任务的线程名
     */
    private String threadNo;
    /**
     * 定时任务的更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Short getUserFlag() {
        return userFlag;
    }

    public void setUserFlag(Short userFlag) {
        this.userFlag = userFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Short getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(Short actionFlag) {
        this.actionFlag = actionFlag;
    }

    public int getSvdValue() {
        return svdValue;
    }

    public void setSvdValue(int svdValue) {
        this.svdValue = svdValue;
    }

    public String getThreadNo() {
        return threadNo;
    }

    public void setThreadNo(String threadNo) {
        this.threadNo = threadNo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
