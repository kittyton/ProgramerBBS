<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'errorvote.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<!-- <script type="text/javascript">
function error(){
alert("对不起，您不能重复投票，请返回查看投票结果");
history.back();
}
</script> -->
<% String temp=(String)(application.getAttribute("hasvoted"));
System.out.println("&&&&&1");
System.out.println("temp="+temp);
System.out.println("&&&&&2");
if(temp.equals("true"))
{
out.print("<script>alert('对不起，您不能重复投票，请返回查看投票结果');history.back();</script>") ;
}
 %>  
 </body>
</html>
