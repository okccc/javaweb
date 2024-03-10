package com.okccc.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;

/**
 * @Author: okccc
 * @Date: 2023/12/14 14:16:03
 * @Desc: 会话域监听器
 */
@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 任何会话域对象的创建都会触发该方法执行
        HttpSession session = se.getSession();
        System.out.println("session " + session.hashCode() + " create");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 任何会话域对象的销毁都会触发该方法执行
        HttpSession session = se.getSession();
        System.out.println("session " + session.hashCode() + " destroy");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        // 任何会话域添加数据都会触发该方法执行
        HttpSession session = se.getSession();
        String name = se.getName();
        Object value = se.getValue();
        System.out.println("session " + session.hashCode() + " add " + name + ": " + value);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // 任何会话域修改数据都会触发该方法执行
        HttpSession session = se.getSession();
        String name = se.getName();
        Object value = se.getValue();  // 旧值
        Object newValue = session.getAttribute(name);  // 新值
        System.out.println("session " + session.hashCode() + " change " + name + ": " + value + " to " + newValue);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        // 任何会话域删除数据都会触发该方法执行
        HttpSession session = se.getSession();
        String name = se.getName();
        Object value = se.getValue();
        System.out.println("session " + session.hashCode() + " remove " + name + ": " + value);
    }

}
