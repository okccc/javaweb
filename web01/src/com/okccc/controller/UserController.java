package com.okccc.controller;

import com.okccc.pojo.User;
import com.okccc.service.UserService;
import com.okccc.service.impl.UserServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/7 15:23:40
 * @Desc:
 */
@WebServlet(value = "/user/*")
public class UserController extends BaseController {

    private final UserService userService = new UserServiceImpl();

    /**
     * 处理用户注册请求
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.接收客户端请求参数
        String username = request.getParameter("username");
        String userPwd = request.getParameter("userPwd");
        // 生产环境可能会有很多个参数,为了方便往下游传递将其封装成User对象
        User user = new User(null, username, userPwd);
        System.out.println("user = " + user);

        // 2.调用服务层方法,完成注册功能
        int rows = userService.register(user);

        // 3.根据注册结果做页面跳转
        if (rows == 1) {
            // 注册成功
            response.sendRedirect("/registerSuccess.html");
        } else {
            // 注册失败
            response.sendRedirect("/registerFail.html");
        }
    }

}
