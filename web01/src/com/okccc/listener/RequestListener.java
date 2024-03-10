package com.okccc.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

/**
 * @Author: okccc
 * @Date: 2023/12/14 14:17:33
 * @Desc: 请求域监听器
 */
@WebListener
public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        // 任何请求域对象的创建都会触发该方法执行
        ServletRequest request = sre.getServletRequest();
        System.out.println("request " + request.hashCode() + " init");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        // 任何请求域对象的销毁都会触发该方法执行
        ServletRequest request = sre.getServletRequest();
        System.out.println("request " + request.hashCode() + " destroy");
    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        // 任何请求域添加数据都会触发该方法执行
        ServletRequest request = srae.getServletRequest();
        String name = srae.getName();
        Object value = srae.getValue();
        System.out.println("request " + request.hashCode() + " add " + name + ": " + value);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        // 任何请求域修改数据都会触发该方法执行
        ServletRequest request = srae.getServletRequest();
        String name = srae.getName();
        Object value = srae.getValue();  // 旧值
        Object newValue = request.getAttribute(name);  // 新值
        System.out.println("request " + request.hashCode() + " change " + name + ": " + value + " to " + newValue);
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        // 任何请求域删除数据都会触发该方法执行
        ServletRequest request = srae.getServletRequest();
        String name = srae.getName();
        Object value = srae.getValue();
        System.out.println("request " + request.hashCode() + " remove " + name + ": " + value);
    }

}
