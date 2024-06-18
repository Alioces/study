package org.example.studentjavaweb.test.servletListener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class MyListener implements
        ServletContextAttributeListener,
        HttpSessionAttributeListener,
        ServletRequestAttributeListener {
    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("ServletContext中的属性发生了更改");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
        System.out.println("ServletRequest中的属性发生了更改");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("Session中的属性发生了更改");
    }
}
