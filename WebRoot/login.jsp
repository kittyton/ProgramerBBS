<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>登录</title>
<style type="text/css">
#main
{
	position:fixed;
	left:35%;
	top:35%;
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

pre
{
	display:inline;
	font-size:1em;
}

</style>

</head>
<body>


<% 
		if(request.getAttribute("illegal")!=null)
	   	{
	   		out.print("<script>alert('对不起,您还没有登录，请先登录');</script>") ;
	   	}
   		request.getSession().removeAttribute("illegal") ;
%>
<script type="text/javascript">  
    function changeValidateCode(obj) {  
        var currentTime= new Date().getTime();  
        obj.src = "rand.action?d=" + currentTime;  
    }  
</script>



<div id="main">
<s:actionerror/>
<a style="color:red; font-size:1.2em; position:relative; top:-2cm;" href="index.jsp">返回主页</a>
<form action="Login">
	<div class="little"><pre>请输入用户名 </pre><input style="width:3.8cm;" type="text" name="username"/></div>
	<div class="little"><pre>请输入密码   </pre><input style="width:3.8cm;" type="password" name="password"/></div>
	<div class="little"><pre>请输入验证码 </pre><input style="width:1.5cm;" type="text" name="code"/>
	<img src="rand.action" onclick="changeValidateCode(this)"/><br/></div> 
	<div style="position:relative; left:10em;top:2em;">
		<input type="submit" value="注册"/>
		<input type="submit" value="登录"/>
	</div>
</form>
</div>

</body>
</html>
