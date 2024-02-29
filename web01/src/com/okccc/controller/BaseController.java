package com.okccc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Author: okccc
 * @Date: 2023/12/7 15:20:54
 * @Desc: Controller层通用代码
 *
 * 添加用户的请求  /user/add
 * 查询用户的请求  /user/find
 * 修改用户的请求  /user/update
 * 删除用户的请求  /user/remove
 * ...
 */
public class BaseController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求路径中解析出方法名,比如/user/add
        String uri = request.getRequestURI();
        String[] arr = uri.split("/");
        String methodName = arr[arr.length - 1];

        // 版本1：硬编码有点傻
//        if ("add".equals(methodName)) {
//            // 增加请求
//            add(request, response);
//        } else if ("find".equals(methodName)) {
//            // 查找请求
//            find(request, response);
//        } else if ("update".equals(methodName)) {
//            // 修改请求
//            update(request, response);
//        } else if ("remove".equals(methodName)) {
//            // 删除请求
//            remove(request, response);
//        } else {
//            other(request, response);
//        }

        // 版本2：既然方法名不确定,那就使用反射动态获取
        // 可以将这段通用代码抽取出来放到BaseController父类
        Class<? extends BaseController> clazz = this.getClass();
        try {
            // 获取方法
            Method declaredMethod = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            System.out.println("methodName = " + methodName + ", declaredMethod = " + declaredMethod);
            // 暴力破解方法的访问修饰符权限
            declaredMethod.setAccessible(true);
            // 调用方法
            declaredMethod.invoke(this, request, response);
        } catch (Exception e) {
            // 在Server端打印异常
//            e.printStackTrace();
            // 直接将异常抛给前端页面
            throw new RuntimeException(e);
        }
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write("执行add方法");
    }

    protected void find(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write("执行find方法");
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write("执行update方法");
    }

    protected void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write("执行remove方法");
    }

    protected void other(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write("请求路径错误");
    }

}
