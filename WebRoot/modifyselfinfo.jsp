<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="gb2312"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    
    <title>ÐÞ¸Ä¸öÈËÐÅÏ¢</title>
    
<style type="text/css">
#main
{
	position:fixed;
	left:35%;
	top:30%;
	width:10cm;
	height:5cm;
}

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

<div id="main">

<s:form action="ModifySelfInfo" enctype="multipart/form-data">
	<s:password style="width:4.5cm;" name="rpass" label="ÖØÖÃÃÜÂë"/> 
	<s:password style="width:4.5cm;" name="rrpass" label="ÇëÔÙÊäÒ»´Î"/>
	<s:textfield style="width:4.5cm;" name="rhobby" label="ÓÃ»§°®ºÃ"/>
	<s:file style="width:4.5cm;" name="rimage" label="ÐÞ¸ÄÍ·Ïñ"/>
	<s:textarea style="width:4.5cm;" name="rintroduction" label="Ò»¾ä»°½éÉÜ"/>
	<s:submit value="±£´æ"/>
</s:form>
</div>
</body>
</html>
