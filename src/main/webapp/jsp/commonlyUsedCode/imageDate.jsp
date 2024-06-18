<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.example.studentjavaweb.test.JDBC.DataBean" %>
<%@ page import="org.example.studentjavaweb.test.javaBean.ImageBean" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="org.example.studentjavaweb.test.JDBC.ImageCategory" %>
<%--
  Created by IntelliJ IDEA.
  User: 雾都
  Date: 2024/5/31
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%!
    //图片数据集合
    List<ImageBean> imageBeans;
    //页面数据
    int start;
    int count = 6;
%>
<%

    System.out.println(request.getParameter("page"));
    //检查页码数据
    if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
        start = Integer.parseInt(request.getParameter("page"));
        start = Math.max(start, 0);
    } else {
        start = 0;
    }

    //按照页面格式 从数据库拉取到对应的数据 并封装到集合中
    try {
        DataBean jdbc = new DataBean();

        imageBeans = jdbc.toList(jdbc.executeSelect(connection -> {
            PreparedStatement sql = connection.prepareStatement("select * from imageinfo where imageID > ? limit ?");
            sql.setInt(1,start * 6);
            sql.setInt(2,count);
            return sql;
        }));

        jdbc.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    //额外需要显示的图片类别数据
    request.setAttribute("list", ImageCategory.imageCategory);
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--对集合的遍历--%>
<c:forEach var="imageBean" items="<%= imageBeans %>">
    <div class="box textLeft">
        <span>${imageBean.name}</span>
        <hr>
        <img src="${pageContext.request.contextPath}${imageBean.address.substring(2)}" alt="图片" class="img">
        <hr>
        <ul>
            <li>
                <span>${list.get(imageBean.categoryID - 1)}</span>
            </li>
        </ul>
    </div>
</c:forEach>

