package org.example.studentjavaweb.test.servletFilter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@WebFilter(filterName = "MyFilet" ,urlPatterns = "/MyServlet" ,dispatcherTypes = DispatcherType.REQUEST)
public class MyFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.printf("[ %S ]:触发拦截!\n",new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        System.out.println("hello Filter!");

        servletResponse.getWriter().write("How are you?");

        //使用在过滤器中封装的对象返回公共servlet变量空间 并进行一些初始化操作
        ServletContext context = filterConfig.getServletContext();
        context.setAttribute("useName", "null");
        context.setAttribute("usePassWord", "null");

        System.out.println(context.getAttribute("useName") + " : " + context.getAttribute("usePassWord"));
        System.out.println("---------------------------------------------------------------------------------");

        //进行转发
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
