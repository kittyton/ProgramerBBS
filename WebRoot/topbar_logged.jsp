<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>论坛首页</title>
	
<style type="text/css">
#maintop
{
	background-image:url(css/top3.jpg);
	background-repeat:repeat-x;
    background-position:top;
    font-weight:bold;
    font-size:1.1em ;
    height:5cm;
}

#logo
{
	width:3.8cm;
	height:3cm;
	cursor:pointer;
	display:inline;
}

a
{
	cursor:pointer;
}

h1
{
	color:#FF0066;
	display:inline;
	font-family:华文行楷;
	font-size:3em;
}

p
{
	display:inline;
	margin:1cm;
}
</style>	
</head>
  

<body>
<div id="maintop">  
	<div id="logo">
		<a href="index.jsp"><img src="css/logo.jpg" border="0"/></a>
	</div>

	<div style="position:relative; top:1.5em; float:right;">
		<h1>程序猿论坛欢迎您</h1>   
			<p>欢迎您，<s:property value="#session.username"/></p>				
		    <a style="padding:0.1cm;" href="lanchvote.jsp">发起投票</a>
		    <a style="padding:0.1cm;" href="Showvotes.action">参与投票</a>
		    <a style="padding:0.1cm;" href="Selfmanage.action">个人信息</a>
		    <a style="padding:0.1cm;" href="Exit.action">退出</a>
	</div>
</div>

</body>
</html>
