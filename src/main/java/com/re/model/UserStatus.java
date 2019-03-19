package com.re.model;

/**
 * @author RE
 * @version 1.0.0
 * @projectName test_20190315
 * @package com.re.model
 * @className UserStatus
 * @description 用户状态枚举
 * @createDate 2019/3/18 10:07
 * @updateUser
 * @updateDate 2019/3/18 10:07
 * @updateRemark
 * @since 1.8
 */
public enum UserStatus {
    /**
     * 用户状态为正常
     * 可以正常查看、办理业务
     */
    NORMAL("正常", 1),

    /**
     * 用户状态为停用
     * 保留用户账号
     * 随时可以通过启用来恢复至正常状态
     */
    BLOCK("停用", 2),

    /**
     * 用户状态为禁用
     * 保留用户账号，除管理员外其他人无法查看
     * 可以通过解禁来恢复至正常状态，需要进行审批
     */
    FORBIDDEN("禁用", 3),

    /**
     * 用户状态为删除（逻辑删除）
     * 除管理员外其他人无法查看
     * 无法恢复
     */
    DELETED("删除", 4);

    /**
     * 构造方法
     *
     * @param value 枚举值
     */
    UserStatus(String statusName, int value) {
        this.setStatusName(statusName);
        this.setValue(value);
    }

    /**
     * 状态名称
     */
    private String statusName;
    /**
     * 枚举值，用于存储于数据库
     */
    private int value;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UserStatus{" +
                "statusName='" + statusName + '\'' +
                ", value=" + value +
                '}';
    }}
