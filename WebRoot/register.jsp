<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>ע��</title>
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
<a style="color:red; font-size:1.2em;" href="index.jsp">������ҳ</a>
<s:form action="Register" enctype="multipart/form-data" >
	<div class="little"><s:textfield style="width:4.5cm;" name="username" label="�û���"/></div>
	<div class="little"><s:password style="width:4.5cm;" name="password" label="����"/></div> 
	<div class="little"><s:password style="width:4.5cm;" name="rpassword" label="ȷ������"/></div>
	<div class="little"><s:textfield style="width:4.5cm;" name="hobby" label="�û�����"/></div>
	<div class="little"><s:file style="width:4.8cm;" name="image" label="�ϴ�ͷ��"/></div>
	<div class="little"><s:textarea name="introduction" label="һ�仰����"/></div>
	<s:submit style="position:relative; top:1em;" value="ע��"/>
</s:form>
</div>
</body>
</html>