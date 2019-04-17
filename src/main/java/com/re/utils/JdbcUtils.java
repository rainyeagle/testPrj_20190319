package com.re.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.*;

/**
 * @author RE
 * @version 1.0.0
 * @projectName testPrj_20190319
 * @package com.re.utils
 * @className JDBCUtils
 * @description JDBC类工具
 * @createDate 2019/3/20 8:45
 * @updateUser
 * @updateDate 2019/3/20 8:45
 * @updateRemark
 * @since 1.8
 */
public class JdbcUtils {
    private static ComboPooledDataSource ds = null;

    /**
     * 构造方法
     *
     * @param name 配置名称(配置来源为c3p0-config.xml)，如果为空字符串则为默认配置
     */
    public JdbcUtils(String name) {
        if (name.isEmpty()) {
            ds = new ComboPooledDataSource();
        } else {
            ds = new ComboPooledDataSource(name);
        }
    }

    /**
     * 根据数据源获取数据库连接
     *
     * @return 数据库连接
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 返回数据源对象
     *
     * @return 数据源对象
     */
    public DataSource getDataSource() {
        return ds;
    }

    /**
     * 关闭数据库连接等
     *
     * @param conn 数据库连接
     * @param pst  预编译语句
     * @param rs   结果集
     */
    public static void close(Connection conn, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}