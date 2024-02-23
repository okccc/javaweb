package com.okccc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/5 14:57:01
 * @Desc:
 */
@WebServlet(value = "/servlet10", name = "servlet10")
public class Servlet10 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--------------- 测试转发和重定向 ---------------");
        // 获取请求参数
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        // 获取请求域中的数据
        String value = (String) request.getAttribute("orc");
        System.out.println("orc = " + value);
        // 做出响应
        response.getWriter().write("Servlet10 Response");
    }

}
