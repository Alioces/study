<%--
  Created by IntelliJ IDEA.
  User: 雾都
  Date: 2024/5/26
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    //用于处理的文件相关信息
    List<List<String>> fileInfos = new ArrayList<>();

    /**
     * 输出文件名清除UUID编码后的文件名
     * @param name 使用UUID_文件名方式编码的文件名
     * @return 清除UUID编码后的文件名
     */
    public String toFileName(String name){
        String fileName = "";
        //分隔UUID的文件名
        String[] names = name.split("_");
        //对于可能错误分隔文件名的处理
        for (int i = 1; i < names.length; i++) {
            fileName += names[i];
        }
        return fileName;
    }
%>
<%
    //可下载文件所在的目录
    String directoryPath = "/upLoad";
    //得到在实际的绝对文件目录路径
    String webPath = request.getServletContext().getRealPath(directoryPath);

    File directory = new File(webPath);

    if (directory.isDirectory()){
        //获取目录下的所有文件
        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isFile()){
                List<String> fileInfo = new ArrayList<>(2);

                //可读的文件名称
                String fileName = toFileName(file.getName());
                System.out.printf("文件名称: %s \n", fileName);
                fileInfo.add(fileName);

                //完全文件名称编码URL
                fileInfo.add(file.getName());

                fileInfos.add(fileInfo);
            }
        }
    }
%>
<%--  下载文件--%>
<div>
    <h4>可下载的文件条目:</h4>
    <hr>
    <ul>
        <%--      遍历信息--%>
        <c:forEach var="fileInfo" items="<%= fileInfos %>">
            <li>
                <h6>文件名称: ${fileInfo.get(0)}</h6>
                <hr>
                <c:url var="downloadURL" value="/DownloadFileServlet">
                    <c:param name="fileName" value="${fileInfo.get(1)}" />
                </c:url>
                    <%--          组装文件下载地址--%>
                <!--未编码结果: ${pageContext.request.contextPath}/DownloadFileServlet?fileName=${fileInfo.get(1)} -->
                <a href="${downloadURL}">
                    下载文件
                </a>
            </li>
        </c:forEach>
    </ul>
</div>

