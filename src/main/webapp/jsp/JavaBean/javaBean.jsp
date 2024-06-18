<%--
  Created by IntelliJ IDEA.
  User: 雾都
  Date: 2024/5/21
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            background-color: antiquewhite;
        }
        form{
            background-color: bisque;
            margin: auto;
            width: 25%;
        
        }
    </style>
</head>
<body>
    <jsp:useBean id="User" class="org.example.studentjavaweb.test.javaBean.User"/>
    <jsp:setProperty name="User" property="name" value="曹鹏翔"/>
    <jsp:setProperty name="User" property="passWord" value="mcwudu"/>
    <fieldset>
        <ul>
            <li>
                用户名字:
                <jsp:getProperty name="User" property="name"/>
            </li>
            <li>
                用户密码:
                <jsp:getProperty name="User" property="passWord"/>
            </li>
        </ul>
    </fieldset>
    <fieldset>
        <h3>
            通过表单设置JavaBean的值:
        </h3>
        <hr>
        <form action="fromInputJavaBean.jsp" method="post">
            <label for="name">
                <h5>
                    名字:
                </h5>
                <hr>
                <input type="text" name="name" id="name">
            </label>
            <label for="passWord">
                <h5>
                    密码:
                </h5>
                <hr>
                <input type="text" name="passWord" id="passWord">
            </label>
            <br>
            <input type="submit" value="提交">
        </form>
    </fieldset>
</body>
</html>
