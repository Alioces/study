<%--
  Created by IntelliJ IDEA.
  User: 雾都
  Date: 2024/5/26
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--  上传文件--%>
<form class="inputFrom center" name="fileFrom" action="<c:url value="/UpFileServlet"/>" method="post" enctype="multipart/form-data">
    <h4>文件上传</h4>
    <ul>
        <li>
            <span>上传者:</span><input type="text" name="upFileName" size="8"/>
        </li>
        <li>
            <span>上传文件:</span><input type="file" name="file" />
        </li>
        <input type="submit" value="开始上传" />
    </ul>
</form>

