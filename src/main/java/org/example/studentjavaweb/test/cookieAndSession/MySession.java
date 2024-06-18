package org.example.studentjavaweb.test.cookieAndSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
@WebServlet(name = "MySession", value = "/MySession")
public class MySession extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        System.out.println(session.isNew());

        Date time = (Date) session.getAttribute("useTime");
        if (time == null){
            session.setAttribute("useTime", new Date());
            return;
        }

        resp.getWriter().write("useTime: " + time);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }
}
