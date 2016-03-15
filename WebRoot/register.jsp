<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>注册</title>
<style type="text/css">
#main
{
	position:fixed;
	left:35%;
	top:25%;
}

body
{
	background-image:url(css/4.jpg);
    background-position:top;
    font-weight:bold;
    font-size:1.1em ;
    background-repeat: repeat;
}

.little
{
	padding:0.1cm;
}


</style>
</head>
<body>

<div id="main">
<a style="color:red; font-size:1.2em;" href="index.jsp">返回主页</a>
<s:form action="Register" enctype="multipart/form-data" >
	<div class="little"><s:textfield style="width:4.5cm;" name="username" label="用户名"/></div>
	<div class="little"><s:password style="width:4.5cm;" name="password" label="密码"/></div> 
	<div class="little"><s:password style="width:4.5cm;" name="rpassword" label="确认密码"/></div>
	<div class="little"><s:textfield style="width:4.5cm;" name="hobby" label="用户爱好"/></div>
	<div class="little"><s:file style="width:4.8cm;" name="image" label="上传头像"/></div>
	<div class="little"><s:textarea name="introduction" label="一句话介绍"/></div>
	<s:submit style="position:relative; top:1em;" value="注册"/>
</s:form>
</div>
</body>
</html>