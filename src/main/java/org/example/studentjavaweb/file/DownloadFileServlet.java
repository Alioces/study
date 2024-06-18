package org.example.studentjavaweb.file;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "DownloadFileServlet" , value = "/DownloadFileServlet")
public class DownloadFileServlet extends HttpServlet {
    @Override
    public void destroy() {

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        this.doPost(req,resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        resp.setContentType("application/octet-stream");
        //通知浏览器以下载的方式打开
        resp.addHeader("Content-Disposition","attachment; fileName=\"" + URLEncoder.encode(req.getParameter("fileName"), StandardCharsets.UTF_8) + "\"");

        String fileName = req.getParameter("fileName");
        String directory = "/upLoad/";
        System.out.println(fileName);

        try {
            //获取下载的文件输入流
            InputStream in = getServletContext().getResourceAsStream(directory + fileName);
            //客户端的文件输出流
            OutputStream out = resp.getOutputStream();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("文件下载完成！！");
        System.out.println("-------------------------------------");
    }
}
