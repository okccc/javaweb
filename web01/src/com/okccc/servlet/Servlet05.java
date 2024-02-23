package com.okccc.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/5 14:31:51
 * @Desc: 请求转发和响应重定向是Servlet控制页面跳转的两种手段
 *
 * 转发：张三找李四借钱,李四没钱于是找王五借给张三
 * 重定向：张三找李四借钱,李四没钱于是让张三自己去找王五借钱
 *
 * 转发是通过HttpServletRequest对象获取请求转发器实现
 * 转发是服务器内部行为,对客户端是屏蔽的
 * 转发目标：动态资源、静态资源、WEB-INF下的受保护资源,但不能是外部资源
 * 客户端只发送一次请求,所以地址栏url不变,服务器只产生一对请求和响应对象,所以请求参数和请求域数据可以传递
 *
 * 重定向是通过HttpServletResponse对象的sendRedirect()方法实现
 * 重定向会响应302状态码和Location告诉客户端自己去找资源,所以是在服务器提示下的客户端行为
 * 重定向目标：动态资源、静态资源、外部资源,但不能是WEB-INF下的受保护资源(只能通过请求转发访问)
 * 客户端发送了两次请求,所以地址栏url会变,服务器会产生多对请求和响应对象,所以请求参数和请求域数据不可以传递
 *
 * 注意：如果只是实现页面跳转没有业务逻辑,优先使用重定向
 */
// http://localhost:8088/servlet05?username=grubby
@WebServlet(value = "/servlet05")
public class Servlet05 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--------------- 测试请求转发 ---------------");
        // 获取请求参数
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 往请求域放数据
        request.setAttribute("orc","grubby");
        String value = (String) request.getAttribute("orc");
        System.out.println("orc = " + value);

        // 转发给动态资源(Y)
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("servlet10");

        // 转发给静态资源(Y)
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");

        // 转发给WEB-INF下的受保护资源(Y)
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/templates/a.html");

        // 转发给外部资源(N)
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("https://www.baidu.com");

        // 执行转发动作
        requestDispatcher.forward(request, response);

        System.out.println("--------------- 测试响应重定向 ---------------");
        // sendRedirect()会包含setStatus()和setHeader()两个步骤
//        response.setStatus(302);
//        response.setHeader("Location", "目标资源");

        // 重定向到动态资源(Y)
//        response.sendRedirect("servlet10");

        // 重定向到静态资源(Y)
//        response.sendRedirect("index.html");

        // 重定向到WEB-INF下的受保护资源(N),404要么访问路径不对要么访问了WEB-INF下的受保护资源
//        response.sendRedirect("WEB-INF/templates/a.html");

        // 重定向到外部资源(Y)
//        response.sendRedirect("https://www.baidu.com");
    }

}
