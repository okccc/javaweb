package com.okccc.service.impl;

import com.okccc.dao.UserDao;
import com.okccc.dao.impl.UserDaoImpl;
import com.okccc.pojo.User;
import com.okccc.service.UserService;
import com.okccc.util.MD5Util;

/**
 * @Author: okccc
 * @Date: 2023/12/7 15:20:19
 * @Desc:
 */
public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public int register(User user) {
        // 对前端输入的明文密码进行加密
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        // 调用dao层方法将用户存入数据库
        return userDao.addUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
