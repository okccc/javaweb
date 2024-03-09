package com.okccc.filter;

import com.okccc.pojo.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/12 18:56:55
 * @Desc: 登录校验,未登录状态下不允许访问首页相关资源
 */
@WebFilter(urlPatterns = {"/showSchedule.html", "/schedule/*"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 向下转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取session对象
        HttpSession session = request.getSession();

        // 从session域获取登录用户
        User loginUser = (User) session.getAttribute("loginUser");

        // 判断是否为空
        if (loginUser == null) {
            // 未登录,先去登录
            response.sendRedirect("/login.html");
        } else {
            // 已登录,放行
            filterChain.doFilter(request, response);
        }
    }

}
