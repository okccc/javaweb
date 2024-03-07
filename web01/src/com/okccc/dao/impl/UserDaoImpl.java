package com.okccc.dao.impl;

import com.okccc.dao.BaseDao;
import com.okccc.dao.UserDao;
import com.okccc.pojo.User;

import java.util.List;

/**
 * @Author: okccc
 * @Date: 2023/12/7 15:19:35
 * @Desc:
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public int addUser(User user) {
        String sql = "insert into user values(null,?,?)";
        return baseUpdate(sql, user.getUsername(), user.getUserPwd());
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select * from user where username = ?";
        List<User> list = baseQuery(User.class, sql, true, username);
        return !list.isEmpty() ? list.get(0) : null;
    }

}
