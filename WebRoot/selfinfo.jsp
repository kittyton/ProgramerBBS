<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    
    <title>个人信息</title>
<style type="text/css">
body
{
	background-image:url(css/4.jpg);
	background-repeat:repeat;
    background-position:top;
    font-weight:bold;
    font-size:1.1em ;
}

</style>    

</head>
   
<body>
<%if(request.getSession().getAttribute("username") != null){%>
	<jsp:include page="topbar_logged.jsp"></jsp:include>
<%}else{ %>
 	<jsp:include page="topbar_unlog.jsp"></jsp:include>
<%} %> 


<div style="position:relative;left:40%;top:30%;">
<table>
	
    <tr><td>你的用户名为：</td><td>${name}</td></tr> 
    <tr><td>你的爱好是：</td><td>${ho}</td></tr>
    <tr><td>你的用户等级为:</td><td>${lev}</td></tr>
   <tr><td>你的个性签名：</td><td>${intro}</td></tr>
    <tr><td>你的头像：</td><td>
 <img src=${pic} width="90px" height="100px"/></td></tr> 
 </table>
 <s:a style="color:red; position:relative; top:1cm;" href="modifyselfinfo.jsp">修改个人信息</s:a>
</div>
  </body>
</html>
