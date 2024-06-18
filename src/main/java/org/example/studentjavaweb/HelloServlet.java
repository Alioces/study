package org.example.studentjavaweb;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("hello world!");

        //获取表单输入
        String useName = request.getParameter("name");
        String usePassWord = request.getParameter("passWord");


        //向公共Servlet变量空间传递数据
        ServletContext context = this.getServletContext();
        context.setAttribute("useName", useName);
        context.setAttribute("usePassWord", usePassWord);

        System.out.printf("Hello:%s - %s\n", useName, usePassWord);
        System.out.println("------------------------------------");

        //重定向
        RequestDispatcher dispatcher = request.getRequestDispatcher("/MyServlet");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    public void destroy() {
    }
}