<%--
  Created by IntelliJ IDEA.
  User: 雾都
  Date: 2024/5/20
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%= "用户" %>
    <div>
        <form name="use" method="post" action="${pageContext.request.contextPath}/hello-servlet">
            <label>
                名&nbsp;&nbsp;字：
                <input name="name" type="text" />
            </label>
            <br>
            <label>
                用户简介:
                <input name="useText" type="text">
            </label>
            <input  type="submit" value="提交">
        </form>
    </div>
