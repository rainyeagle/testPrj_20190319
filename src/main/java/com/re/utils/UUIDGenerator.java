package com.re.utils;

import java.util.UUID;

/**
 * @author RE
 * @version 1.0.0
 * @projectName test_20190315
 * @package com.re.utils
 * @className UUIDGenerator
 * @description UUID生成类
 * @createDate 2019/3/18 16:40
 * @updateUser
 * @updateDate 2019/3/18 16:40
 * @updateRemark
 * @since 1.8
 */
public class UUIDGenerator {

    /**
     * 生成指定个数的UUID
     *
     * @param num             个数，大于等于1
     * @param connectorEnable 是否去掉连接符
     * @return UUID构成的数组
     */
    public static String[] generateUUIDs(int num, boolean connectorEnable) {
        String[] ids = new String[num];
        if (num >= 1) {
            for (int i = 0; i < num; i++) {
                String idstr = UUID.randomUUID().toString();
                if (!connectorEnable) {
                    idstr = idstr.replace("-", "");
                }
                ids[i] = idstr;
            }
        }
        return ids;
    }
}