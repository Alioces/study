package org.example.studentjavaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
@WebServlet(name = "ToSessionTime",value = "/ToSessionTime")
public class ToSessionTime extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        System.out.println("ToSessionTime!!!");

        //获取用户的Session信息
        HttpSession session = request.getSession(false);

        //是否是新建的
        System.out.println(session.isNew());

        //获取登录时间
        Date time = (Date) session.getAttribute("useTime");

        //输出信息
        System.out.println("useTime: " + time + " SessionID: " + session.getId());
        System.out.println("------------------------------------");

        //request.getRequestDispatcher("/jsp/ELAndJSTL/testIndex.jsp").forward(request,response);
        //跳转提示页面
        response.sendRedirect("jsp/ELAndJSTL/testIndex.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    public void destroy() {
    }
}
