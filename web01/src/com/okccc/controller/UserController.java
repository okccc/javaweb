package com.okccc.controller;

import com.okccc.common.Result;
import com.okccc.common.ResultCodeEnum;
import com.okccc.pojo.User;
import com.okccc.service.UserService;
import com.okccc.service.impl.UserServiceImpl;
import com.okccc.util.MD5Util;
import com.okccc.util.WebUtil;
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

    /**
     * 注册时检查用户名是否被占用
     * AJAX(Asynchronous JavaScript and XML)：在不重新加载页面的情况下,与服务器交换数据并刷新部分网页内容
     * F12 - Network - All是看所有请求有点乱,Fetch/XHR是只看ajax请求
     *
     * Ajax实现方式
     * 1.js实现,代码繁琐且涉及回调函数问题,相当于jdbc
     * 2.第三方工具jquery实现,相当于BaseDao
     * 3.框架VUE axios实现,相当于Mybatis
     */
    protected void checkUsernameUsed(HttpServletRequest request, HttpServletResponse response) {
        // 1.接收客户端请求参数
        String username = request.getParameter("username");

        // 2.调用服务层方法,检查username是否已被占用
        User registerUser = userService.findByUsername(username);

        // 3.根据检查结果做出对应响应
        Result result;
        if (registerUser == null) {
            // 未占用,创建一个code为200的对象
            result = Result.build(ResultCodeEnum.SUCCESS);
        } else {
            // 已占用,创建一个code为505的对象
            result = Result.build(ResultCodeEnum.USERNAME_USED);
        }
        // 将result对象转换成json响应给客户端
        WebUtil.writeJson(response, result);
    }

}
