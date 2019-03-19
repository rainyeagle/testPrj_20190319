package com.re.model;

import com.re.utils.PasswordGenerator;

/**
 * @author RE
 * @version 1.0.0
 * @projectName test_20190315
 * @package com.re.model
 * @className Password
 * @description 密码类
 * @createDate 2019/3/18 10:07
 * @updateUser
 * @updateDate 2019/3/18 10:07
 * @updateRemark
 * @since 1.8
 */
public class Password {
    /**
     * 密码表主键
     */
    private int passwordId;
    /**
     * 逻辑外键，关联用户主键
     */
    private int userId;
    /**
     * 原密码，不写入数据库
     */
    private String originPassword;
    /**
     * 经过加盐和MD5加密的密码，写入数据库作为密码
     */
    private String password;
    /**
     * 加盐值，随机生成，用于辅助生成加盐密码
     */
    private String salt;
    /**
     * 索引值，随机生成（0-31）,将原密码写入加盐值作为加盐密码
     */
    private int index;

    public int getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(int passwordId) {
        this.passwordId = passwordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 使用原始密码作为参数构建密码
     *
     * @param originPassword 原始密码
     */
    public Password(int userId, String originPassword) {
        this.setUserId(userId);
        this.setOriginPassword(originPassword);
        PasswordGenerator.generate(this);
    }

    public String getOriginPassword() {
        return originPassword;
    }

    public void setOriginPassword(String originPassword) {
        if (originPassword.trim().length() > 0) {
            this.originPassword = originPassword.trim();
        } else {
            this.originPassword = "123456";
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Password{" +
                "passwordId=" + this.getPasswordId() +
                ", userId=" + this.getUserId() +
                ", originPassword='" + this.getOriginPassword() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", salt='" + this.getSalt() + '\'' +
                ", index=" + this.getIndex() +
                '}';
    }
}