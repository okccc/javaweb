package com.okccc.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @Author: okccc
 * @Date: 2023/12/11 16:44:55
 * @Desc: 三大域对象：用于共享数据的对象,不同域对象共享范围也不同
 */
@WebServlet(value = "/servlet09")
public class Servlet09 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("--------------- 测试域对象 ---------------");
//        System.out.println("Servlet09.service");
//        // 1.请求域(HttpServletRequest)：共享范围是本次请求及请求转发,使用场景：当前查询到的xx信息
//        request.setAttribute("requestScope", "request-data");
////        request.getRequestDispatcher("servlet10").forward(request, response);
//
//        // 2.会话域(HttpSession)：共享范围是本次会话,可以跨多个请求,会话关闭就失效,使用场景：记录用户的登录状态和操作历史
//        HttpSession session = request.getSession();
//        session.setAttribute("sessionScope", "session-data");
//
//        // 3.应用域(ServletContext)：共享范围是本应用,可以跨多个会话,项目关闭或重启就失效,使用场景：Spring框架的IOC容器
//        ServletContext application = getServletContext();
//        application.setAttribute("applicationScope", "application-data");

        System.out.println("--------------- 测试监听器 ---------------");
        // 往应用域放数据,会触发ServletContextListener执行
        ServletContext application = getServletContext();
        application.setAttribute("application_k1", "application_v1");
        application.setAttribute("application_k1", "application_v1_new");
        application.removeAttribute("application_k1");

        // 往会话域放数据,会触发HttpSessionListener执行
        HttpSession session = request.getSession();
        session.setAttribute("session_k1", "session_v1");
        session.setAttribute("session_k1", "session_v1_new");
        session.removeAttribute("session_k1");
        session.invalidate();

        // 往请求域放数据,会触发ServletRequestListener执行
        request.setAttribute("request_k1", "request_v1");
        request.setAttribute("request_k1", "request_v1_new");
        request.removeAttribute("request_k1");
    }

}
