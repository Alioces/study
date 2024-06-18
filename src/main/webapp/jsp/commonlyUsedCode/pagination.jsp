<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--
  Created by IntelliJ IDEA.
  User: 雾都
  Date: 2024/5/29
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<script src="./../../html/jquery-3.7.1/jquery-3.7.1.js"></script>
<script>
var page = 0
$(document).ready(
  function (){
    $("#页码").html(page + 1);
  }
)
function upPage() {
  page--;
  if (page < 0){
      alert("最后一页!")
      page = 0
  }
  //发起Ajax请求 翻到对应的页面
  $('#imageData').load('http://localhost:8080/studentJavaweb_war_exploded/jsp/commonlyUsedCode/imageDate.jsp',{page:page})
  $("#页码").html(page + 1);
}
function nextPage(){
  page++;
  //发起Ajax请求 翻到对应的页面
  $('#imageData').load('http://localhost:8080/studentJavaweb_war_exploded/jsp/commonlyUsedCode/imageDate.jsp',{page:page})
  $("#页码").html(page + 1);
}
</script>
<%--需要要被分页的数据--%>
<fieldset id="imageData">
    <jsp:include page="imageDate.jsp" >
        <jsp:param name="page" value="0"/>
    </jsp:include>
</fieldset>
<%--分页控制器--%>
<fieldset class="clearFloat">
  <div class="center">
    <input type="button" value="上一页" id="上一页" onclick="upPage()">
    <span id="页码"></span>
    <input type="button" value="下一页" id="下一页" onclick="nextPage()">
  </div>
</fieldset>

