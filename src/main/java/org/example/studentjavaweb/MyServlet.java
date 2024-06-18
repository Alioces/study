package org.example.studentjavaweb;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

@WebServlet(name = "MyServlet", value = "/MyServlet")
public class MyServlet extends HttpServlet {
    private final Properties properties = new Properties();;

    public void init() {
        //读取资源文件
        try {
            FileInputStream file = new FileInputStream(this.getServletContext().getRealPath("WEB-INF/resources/use.properties"));
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        ServletContext context = this.getServletContext();

        String useName = properties.getProperty("useName");
        String usePassWord = properties.getProperty("usePassWord");
        //现在尝试登录的账号信息
        String use = "useName: " + context.getAttribute("useName") + ",usePassWord: " + context.getAttribute("usePassWord");

        System.out.println(useName + " " + usePassWord);
        System.out.println(use);

        //获取用户在本站的所有凭证
        Cookie[] cookies = request.getCookies();

        //是否有登录凭证
        boolean isMyCookies = false;
        //查找凭证
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            //已经有登录凭证
            if (cookie.getName().equals("use")){
                //查看信息
                System.out.printf("解码前:%s\n",cookie.getValue());
                System.out.println("上次登录账号信息: " + URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8));

                //重设凭证，并发送
                response.addCookie(getCookie(cookie, use));
                isMyCookies = true;
                break;
            }
        }

        if (!isMyCookies){
            //创建新凭证
            Cookie cookie = getCookie(null, use);
            //发送凭证
            response.addCookie(cookie);

            System.out.println("首次登录!");
        }

        //校验账号 并资源重定向
        if (context.getAttribute("useName").equals(useName) && context.getAttribute("usePassWord").equals(usePassWord)){
            System.out.println("登录成功");
            //强制新建session信息
            HttpSession session = request.getSession(true);
            System.out.println(session.isNew());

            //尝试获取上次登录时间，如果没有则添加
            Date time = (Date) session.getAttribute("useTime");
            if (time == null){
                session.setAttribute("useTime", new Date());
            }

            //输出信息
            System.out.println("useTime: " + time + " SessionID: " + session.getId());
            System.out.println("------------------------------------");
            //跳转提示页面
            response.sendRedirect("html/loginTrue.html");
        }else {
            System.out.println("登录失败");

            response.sendRedirect("html/loginFalse.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
       this.doGet(req, resp);
    }

    public void destroy() {
    }
    //更新或者创建cookie
    private static Cookie getCookie(Cookie cookie,String use) {
        String data = use;

        System.out.printf("账号信息:%s\n",data);
        //编码信息
        data = URLEncoder.encode(data, StandardCharsets.UTF_8);

        //创建或修改值
        if (cookie == null){
            cookie = new Cookie("use", data);
        }else {
            cookie.setValue(data);
        }

        //设置有效时期并返回
        cookie.setMaxAge(60);
        return cookie;
    }
}
