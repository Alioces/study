package org.example.studentjavaweb.test.cookieAndSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@WebServlet(name = "MyCookie",value = "/MyCookie")
public class MyCookie extends HttpServlet {
    @Override
    public void init() {

    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = req.getCookies();

        boolean isMyCookies = false;
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            if (cookie.getName().equals("myName")){
                System.out.printf("解码前:%s\n",cookie.getValue());
                resp.getWriter().write("上次登录时间: " + URLDecoder.decode(cookie.getValue(),StandardCharsets.UTF_8));
                resp.addCookie(getCookie(cookie));

                isMyCookies = true;
                break;
            }
        }

        if (!isMyCookies){
            Cookie cookie = getCookie(null);
            resp.addCookie(cookie);
            resp.getWriter().write("首次登录!");
            System.out.println(cookie);
        }
    }

    private static Cookie getCookie(Cookie cookie) {
        String date = new SimpleDateFormat("yyyy年MM月dd日:HH:mm:ss").format(new Date());
        System.out.printf("编码前:%s\n",date);
        date = URLEncoder.encode(date, StandardCharsets.UTF_8);
        if (cookie == null){
            cookie = new Cookie("myName",date);
        }else {
            cookie.setValue(date);
        }
        cookie.setMaxAge(60);
        return cookie;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doPost(req,resp);
    }
    @Override
    public void destroy() {

    }
}
