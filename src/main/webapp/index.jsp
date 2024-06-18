<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>
    <%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="${pageContext.request.contextPath}/MyServlet"> MyServlet</a>
<a href="${pageContext.request.contextPath}/TestServlet">Test</a>
<a href="${pageContext.request.contextPath}/MyCookie">Cookie</a>

<form action="${pageContext.request.contextPath}/hello-servlet" method="post">
    <label>
        账号名字:
        <input type="text" name="name">
    </label><br>
    <label>
        账号密码:
        <input type="password" name="passWord">
    </label>
    <input type="submit" name="提交">
</form>
</body>
</html>