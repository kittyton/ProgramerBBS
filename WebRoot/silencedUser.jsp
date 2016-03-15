<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body
{
	background-image:url(css/4.jpg);
    background-position:top;
    background-repeat: repeat;
    font-size:1.1em;
}

td
{
	width:3cm;
}
</style>

</head>
<body>
<jsp:include page="topbar_logged.jsp"></jsp:include>


	<% 
		if(request.getAttribute("alterSuc") != null)
	   	{
	   		out.print("<script>alert('"+request.getAttribute("alterSuc")+"');</script>") ;
	   	}
   		request.getSession().removeAttribute("alterSuc") ;
	%>
		
<div style="position:fixed; top:20%; left:40%;">	
	<table>
	 <tr><td>用户名</td></tr>
	 <s:iterator value="#request.silenceList" id="curr">
		 <tr><td>${curr.userName}</td>
		 <td>
		 <form action="silence">				 	
			<input type="hidden" name="userName" value="${curr.userName}"></input>
			<input type="hidden" name="type" value="open"/>
			<input type="submit" value="解除禁言"/>
		 </form>
		 </td></tr>
		 </s:iterator>	
	</table>
</div>

</body>
</html>