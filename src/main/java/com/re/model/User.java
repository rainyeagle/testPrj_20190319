package com.re.model;

/**
 * @author RE
 * @version 1.0.0
 * @projectName test_20190315
 * @package com.re.model
 * @className User
 * @description 用户类
 * @createDate 2019/3/18 10:07
 * @updateUser
 * @updateDate 2019/3/18 10:07
 * @updateRemark
 * @since 1.8
 */
public class User {
    /**
     * 用户id
     */
    private int userId;
    /**
     * 用户登录名
     */
    private String userName;
    /**
     * 用户密码信息
     */
    private Password password;

    /**
     * 用户状态枚举值，默认为正常
     */
    private UserStatus userStatus = UserStatus.NORMAL;

    /**
     * 用户状态值，不能单独设置值
     */
    private int userStatusNumber = getUserStatus().getValue();

    /**
     * 初始化用户信息
     *
     * @param userId   用户id
     * @param userName 用户登录名
     * @param password 初始密码
     */
    public User(int userId, String userName, String password) {
        this.setUserId(userId);
        this.setUserName(userName);
        this.setPassword(password);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Password getPassword() {
        return password;
    }

    /**
     * 初始化时以初始密码设置新密码信息
     *
     * @param passwordStr 初始化密码字符串
     */
    public void setPassword(String passwordStr) {
        this.password = new Password(this.getUserId(), passwordStr);
    }

    /**
     * 以实体类的方式设置密码
     *
     * @param password 密码实体类
     */
    public void setPassword(Password password) {
        this.password = password;
    }


    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
        this.setUserStatusNumber();
    }

    public int getUserStatusNumber() {
        return userStatusNumber;
    }

    /**
     * 不允许直接修改用户状态值，只能在用户状态变化时随之变化
     */
    private void setUserStatusNumber() {
        this.userStatusNumber = getUserStatus().getValue();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password=" + password +
                ", userStatus=" + userStatus +
                '}';
    }
}