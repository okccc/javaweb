package com.okccc.service;

import com.okccc.pojo.User;

/**
 * @Author: okccc
 * @Date: 2023/12/7 15:20:02
 * @Desc:
 */
public interface UserService {

    /**
     * 注册用户,成功返回1 失败返回0
     */
    int register(User user);

    /**
     * 根据用户名查询完整用户信息
     */
    User findByUsername(String username);
}
