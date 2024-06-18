<%--
  Created by IntelliJ IDEA.
  User: 雾都
  Date: 2024/5/21
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%request.setCharacterEncoding("utf-8");%>
  <jsp:useBean id="User" class="org.example.studentjavaweb.test.javaBean.User">
    <jsp:setProperty name="User" property="*"/>
  </jsp:useBean>
  <table>
    <tr>
      <td>名称:<jsp:getProperty name="User" property="name"/></td>
    </tr>
    <tr>
      <td>密码:<jsp:getProperty name="User" property="passWord"/></td>
    </tr>
  </table>
</body>
</html>
