package org.example.studentjavaweb.file;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.*;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UpFileServlet" ,urlPatterns = "/UpFileServlet")
public class UpFileServlet extends HttpServlet {
    private final File fileDirectory = new File("/tempFile");

    private ServletFileUpload fileUpload;

    private List<FileItem> fileItems;

    public void init() {
        //创建缓存路径
        if (!fileDirectory.exists()){
            fileDirectory.mkdirs();
        }

        //创建文件缓存对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(fileDirectory);
        //设置上传文件 和 文件编码
        fileUpload = new ServletFileUpload(factory);
        fileUpload.setHeaderEncoding("utf-8");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException{
        response.setContentType("text/html;charset=utf-8");

        //通过设置用户的提交表单
        try {
            fileItems = fileUpload.parseRequest(new Rc(request));
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        }
        //遍历
        fileItems.forEach(fileItem -> {
            String name = fileItem.getName();

            if (fileItem.isFormField()){
                //普通字段
                if (name != null && name.equals("upFileName") && !fileItem.equals("")){
                    try {
                        //获取作者信息
                        String value = fileItem.getString("utf-8");
                        System.out.printf("[ %s ]: 上传者: %s \n" ,name ,value);
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }
            }else {
                //文件字段
                if (name != null && !name.isEmpty()){
                    System.out.printf("[ %s ]: 上传文件名: %s \n" ,name ,name);

                    //原文件名
                    String fileName = name.substring(name.lastIndexOf("\\") + 1);
                    fileName = UUID.randomUUID() + "_" + fileName;

                    //文件保存路径
                    String webPath = getServletContext().getRealPath("/upLoad/" + fileName);

                    //保存文件
                    inputFile(fileItem ,webPath);
                    System.out.printf("[ %s ]: 上传文件: %s 完成!\n" ,name ,name);
                }
            }
        });

        //完成传输后跳转回原页面
        response.sendRedirect("jsp/ELAndJSTL/inputFileHtml/file.jsp");
    }

    private void inputFile(FileItem fileItem, String path) {
        //创建路径对应的文件地址
        File file = new File(path);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();

            //构建文件读取输入流
            InputStream in = fileItem.getInputStream();
            FileOutputStream out = new FileOutputStream(file);

            //复制文件内容
            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) > 0){
                out.write(buffer, 0, len);
            }

            //收尾 关闭文件流
            in.close();
            out.close();
            fileItem.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    public void destroy() {
    }
}

