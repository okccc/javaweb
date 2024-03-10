package com.okccc.listener;

import jakarta.servlet.*;

/**
 * @Author: okccc
 * @Date: 2023/12/13 14:06:46
 * @Desc: Listener专门监听三大域对象,是观察者模式的典型案例,但是监听器在项目中很少用
 *
 * 观察者模式：当被观察对象发生变化时,观察者会自动触发相关代码执行,类似js中的事件
 *
 * 监听器按照监听对象分为应用域监听器、会话域监听器、请求域监听器
 */
//@WebListener
public class ApplicationListener implements ServletContextListener, ServletContextAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 项目启动时执行顺序：Listener - Filter - Servlet
        ServletContext application = sce.getServletContext();
        System.out.println("application " + application.hashCode() + " init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 项目关闭时执行顺序：Servlet - Filter - Listener
        ServletContext application = sce.getServletContext();
        System.out.println("application " + application.hashCode() + " destroy");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        // 应用域添加数据时会触发该方法执行
        ServletContext application = scae.getServletContext();
        String name = scae.getName();
        Object value = scae.getValue();
        System.out.println("application " + application.hashCode() + " add " + name + ": " + value);
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        // 应用域修改数据时会触发该方法执行
        ServletContext application = scae.getServletContext();
        String name = scae.getName();
        Object value = scae.getValue();  // 旧值
        Object newValue = application.getAttribute(name);  // 新值
        System.out.println("application " + application.hashCode() + " change " + name + ": " + value + " to " + newValue);
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        // 应用域删除数据时会触发该方法执行
        ServletContext application = scae.getServletContext();
        String name = scae.getName();
        Object value = scae.getValue();
        System.out.println("application " + application.hashCode() + " remove " + name + ": " + value);
    }

}
