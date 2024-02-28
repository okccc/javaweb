package com.okccc.util;


import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {

    // 绑定Connection的ThreadLocal
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    // 数据库连接池
    private static final DataSource dataSource;

    // 初始化连接池
    static {
        // 读取配置文件
        Properties prop = new Properties();
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            prop.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取连接,绑定和ThreadLocal的关联
     */
    public static Connection getConnection() {
        // 先从本地线程获取
        Connection conn = threadLocal.get();
        // 没有再去连接池拿
        if (conn == null) {
            try {
                conn = dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // 绑定到本地线程
            threadLocal.set(conn);
        }
        return conn;
    }

    /**
     * 归还连接,解除和ThreadLocal的关联
     */
    public static void releaseConnection() {
        Connection conn = threadLocal.get();
        if (conn != null) {
            // 从本地线程释放
            threadLocal.remove();
            try {
                // 设置自动提交
                conn.setAutoCommit(true);
                // 归还到连接池
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 释放资源
     */
    public static void close(PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}