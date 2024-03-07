package com.okccc.dao;

import com.okccc.pojo.User;

/**
 * @Author: okccc
 * @Date: 2023/12/7 15:19:12
 * @Desc:
 */
public interface UserDao {

    /**
     * 往数据库表添加一条用户记录,成功返回1 失败返回0
     */
    int addUser(User user);

    /**
     * 根据用户名查询完整用户信息
     */
    User findByUsername(String username);
}
