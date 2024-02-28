package com.okccc.dao;

import com.google.common.base.CaseFormat;
import com.okccc.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/12/7 16:31:43
 * @Desc: Dao层通用增删改查方法
 */
public class BaseDao {

    /**
     * 通用查询方法,返回单个对象(单行单列)
     */
    public <T> T baseQueryObject(String sql, Object ... args) {
        T t = null;

        // 获取连接
        Connection conn = JDBCUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            // 设置sql语句参数
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 执行查询
            rs = ps.executeQuery();
            if (rs.next()) {
                t = (T) rs.getObject(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.close(ps, rs);
            // 释放连接
            JDBCUtil.releaseConnection();
        }

        // 返回结果
        return t;
    }

    /**
     * 通用查询方法,返回对象集合
     */
    public <T> List<T> baseQuery(Class<T> clazz, String sql, Boolean underlineToCamel, Object ... args){
        // 存放结果集的列表
        List<T> list = new ArrayList<>();

        // 获取连接
        Connection conn = JDBCUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            // 设置sql语句参数
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 执行查询,返回结果集
            rs = ps.executeQuery();

            // 获取结果集的元数据信息
            ResultSetMetaData md = rs.getMetaData();

            // 行遍历,将行数据转换成T类型对象
            while (rs.next()) {
                // 通过反射创建对应封装类型的对象
                T obj = clazz.getDeclaredConstructor().newInstance();

                // 列遍历,给T类型对象赋值
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    // 从元数据获取字段名称,从结果集获取字段值
                    String columnName = md.getColumnLabel(i);
                    Object value = rs.getObject(columnName);

                    // guava可以实现下划线(Mysql)和驼峰(JavaBean)的相互转换,update_time -> updateTime
                    if (underlineToCamel) {
                        columnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName.toLowerCase());
                    }

                    // 给字段赋值
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(obj, value);
                }
                // 添加到列表
                list.add(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            JDBCUtil.close(ps, rs);
            // 释放连接
            JDBCUtil.releaseConnection();
        }

        // 返回结果集
        return list;
    }

    /**
     * 通用增删改方法
     */
    public int baseUpdate(String sql,Object ... args) {
        // 获取连接
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement ps = null;
        int rows = 0;
        try {
            ps = conn.prepareStatement(sql);
            // 设置sql语句参数
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 执行增删改操作
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源(可选)
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            // 释放连接
            JDBCUtil.releaseConnection();
        }
        // 返回的是影响数据库记录数
        return rows;
    }

}
