package com.re.utils;

import com.re.model.Password;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @author RE
 * @version 1.0.0
 * @projectName test_20190315
 * @package com.re.utils
 * @className PasswordGenerator
 * @description 密码生成工具类
 * @createDate 2019/3/18 10:07
 * @updateUser
 * @updateDate 2019/3/18 10:07
 * @updateRemark
 * @since 1.8
 */
public class PasswordGenerator {

    /**
     * 生成MD5的编码格式
     */
    private static final String MD5_ENCODING = "UTF-8";
    /**
     * 生成MD5密码的长度
     */
    private static final int MD5_LENGTH_16 = 16;
    private static final int MD5_LENGTH_32 = 32;
    private static final int MD5_LENGTH_64 = 64;

    /**
     * 生成盐、索引、加密密码
     *
     * @param password 密码实体类
     */
    public static void generate(Password password) {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        passwordGenerator.generateSalt(password);
        passwordGenerator.generateIndex(password);
        passwordGenerator.generatePassword(password);
    }

    /**
     * 更新密码（盐代码、索引会相应生成）
     *
     * @param password    密码实体类
     * @param newPassword 新密码
     */
    public static void updatePassword(Password password, String newPassword) {
        password.setOriginPassword(newPassword);
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        passwordGenerator.generateSalt(password);
        passwordGenerator.generateIndex(password);
        passwordGenerator.generatePassword(password);
    }

    /**
     * 检查密码是否正确
     *
     * @param password      密码实体类
     * @param checkPassword 需要检验的密码
     * @return 是否正确
     */
    public static boolean check(Password password, String checkPassword) {
        boolean flag = false;
        if (password != null) {
            String md5password = generatePassword(password.getSalt(), password.getIndex(), checkPassword);
            if (password.getPassword().equals(md5password)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 生成构建加密密码所需的盐字符串
     *
     * @param password 密码实体类
     */
    private void generateSalt(Password password) {
        String salt = UUIDGenerator.generateUUIDs(1, false)[0];
        password.setSalt(salt);
    }

    /**
     * 生成随机索引，用于指定在salt中插入原密码的位置
     *
     * @param password 密码实体类
     */
    private void generateIndex(Password password) {
        int min = 0, max = 31;
        int index = (int) (new Random().nextFloat() * (max - min));
        password.setIndex(index);
    }

    /**
     * 生成未加密的加盐密码(根据随机的索引截断盐代码，将原密码插入)
     *
     * @param password 密码实体类
     */
    private void generatePassword(Password password) {
        password.setPassword(generatePassword(password.getSalt(), password.getIndex(), password.getOriginPassword()));
    }

    /**
     * 根据指定的盐代码、索引、原密码生成未加密的加盐密码(根据随机的索引截断盐代码，将原密码插入)
     * 本方法用于生成加盐密码和验证
     *
     * @param salt           盐代码
     * @param index          索引
     * @param originPassword 原密码
     * @return 生成的加密代码
     */
    private static String generatePassword(String salt, int index, String originPassword) {
        StringBuilder sb = new StringBuilder();
        String salt1 = salt.substring(0, index);
        String salt2 = salt.substring(index);
        sb.append(salt1);
        sb.append(originPassword);
        sb.append(salt2);
        return strToMd5(sb.toString(), false, MD5_LENGTH_32);
    }

    /**
     * 将加盐密码加密为MD5
     *
     * @param pwd     加盐密码
     * @param isUpper 加密后是否大写
     * @param bit     生成密码位数，16、32、64
     * @return 加密后的密码
     */
    private static String strToMd5(String pwd, boolean isUpper, Integer bit) {
        String md5 = "";
        try {
            // 创建加密对象
            MessageDigest md = MessageDigest.getInstance("md5");
            if (bit == MD5_LENGTH_64) {
                BASE64Encoder bw = new BASE64Encoder();
                md5 = bw.encode(md.digest(pwd.getBytes(MD5_ENCODING)));
            } else {
                // 计算MD5函数
                md.update(pwd.getBytes());
                byte[] b = md.digest();
                int i;
                StringBuilder sb = new StringBuilder();
                for (byte b1 : b) {
                    i = b1;
                    if (i < 0) {
                        i += 256;
                    } else if (i < 16) {
                        sb.append("0");
                    }
                    sb.append(Integer.toHexString(i));
                }
                md5 = sb.toString();
                if (bit == MD5_LENGTH_16) {
                    //截取32位md5为16位
                    md5 = md5.substring(8, 24);
                    if (isUpper) {
                        md5 = md5.toUpperCase();
                    }
                    return md5;
                }
            }
            //转换成大写
            if (isUpper) {
                md5 = md5.toUpperCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("md5加密抛出异常！");
        }

        return md5;
    }

}