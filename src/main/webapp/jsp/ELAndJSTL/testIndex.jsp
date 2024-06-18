<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 雾都
  Date: 2024/5/20
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <title>Title</title>

    <script>
        function toFile(){
            if(confirm('你将前往文件上传中心!')){
                window.location.href = './inputFileHtml/file.jsp'
            }
        }
    </script>
    <link rel="stylesheet" href="../Css/div.css">
</head>
<body>

<fieldset>
    <%@include file="use.jsp"%>
</fieldset>
<fieldset>
    访问时间：
    <%--        以http的形式进行参数传递   date.jsp?admin=123456--%>
    <jsp:include page="date.jsp">
        <jsp:param name="admin" value="123456"/>
    </jsp:include>
        <%-- 报错 原因是完整的html 导致的文件结构冲突--%>
        <%--    <%@include file="date.jsp"%>--%>
    <div class="box">
        <form action="${pageContext.request.contextPath}/jsp/ELAndJSTL/testIndex.jsp" method="post" class="inputFrom">
            <label for="item">item:</label>
            <input type="text" name="item" id="item" size="2"><input type="submit" value="提交">
        </form>
    </div>
</fieldset>
      EL
<%--        地址组装--%>
      <c:url var="myURL" value="http://localhost:8080/" scope="page">
          <c:param name="name" value="${pageContext.request.contextPath}" />
      </c:url>
    <input type="button" value="上传文件" onclick="toFile()">
    <div>
<%--        单判断--%>
        <c:if test="${not empty myURL}">
            <div class="box">
                <h5>当前地址</h5>
                <hr>
                <p>${myURL}</p>
            </div>
         </c:if>
<%--    多重判断--%>
        <c:choose>
            <c:when test="${param.item == 1}">
                <div class="box">
                    <h5>我是项目1</h5>
                    <hr>
                    <p>item 1</p>
                </div>
            </c:when>
            <c:when test="${param.item >1 and param.item <= 5}">
                <div class="box">
                    <h5>我是项目${param.item}</h5>
                    <hr>
                    <p>item ${param.item}</p>
                </div>
            </c:when>
<%--            全部不满足--%>
            <c:otherwise>
                <div class="box">
                    <h5>找不到item${param.item}</h5>
                    <hr>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <br>
    <div>
<%--        循环--%>
        <c:forEach var="key" items="${param.keySet()}">
            <div class="box">
                <p>${key}</p>
                <hr>
            </div>
        </c:forEach>
    </div>
<%--分页模块--%>
<div class="clearFloat">
    <fieldset id="pagination">
        <jsp:include page="./../commonlyUsedCode/pagination.jsp">
            <jsp:param name="page" value="0"/>
        </jsp:include>
    </fieldset>
</div>
</body>
</html>