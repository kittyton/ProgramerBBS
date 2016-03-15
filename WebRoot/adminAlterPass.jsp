<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alter password</title>
</head>
<style type="text/css">
body
{
	background-image:url(css/b.jpg);
	background-repeat: repeat;
	position:relative;
	top:2em;
	font-weight:bold;
	color:#004400;
}

#button
{
	position:relative;
	left:4.5em;
	top:1em;
}	
	
</style>

<body>
<script type="text/javascript">
function closeWin(){
 	window.close();
 }
</script>

<center>
	<s:form action="adminAlterPass" theme="simple">
	
	<s:actionmessage/>
	<%if(request.getAttribute("alterSuc") == null) 
	  {
	%>
		请输入新密码&nbsp<s:password name="newPass"></s:password><br/>
		再次输入密码&nbsp<s:password name="renewPass"></s:password><br/>
		<s:actionerror/>
		<div id="button">
			<s:reset value="重置"></s:reset>
			<s:submit value="修改"></s:submit>
		</div>
	<%}
	else 
	{%>
		<div style="position:relative;left:1.5cm;top:0.2cm;">
		<input type="button" value="确定" onclick="closeWin()">
		</div>
	<%	request.getSession().removeAttribute("alterSuc") ;   
	} %>
		
	</s:form>
</center>
</body>
</html>