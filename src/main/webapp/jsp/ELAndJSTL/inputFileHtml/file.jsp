<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 雾都
  Date: 2024/5/23
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../../Css/div.css">
</head>
<body>
  <%--  上传文件--%>
  <%@include file="uploadFile.jsp"%>
  <%--  下载文件--%>
  <%@include file="downloadFile.jsp"%>
</body>
</html>
