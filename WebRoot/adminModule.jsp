<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
	p
	{
		display:inline;
		width:4cm;
		padding:0.2cm;
	}
	body
	{
		background-image:url(css/b.jpg);
		background-repeat: repeat;
		position:relative;
		top:2em;
		font-weight:bold;
		color:#004400;
	}
	
</style>
</head>
<body>
<center>
<script type="text/javascript">
	function closeWin(){
	 	window.close();
	}
 
	function if_rmv() {
		var r=confirm("确认删除此模块？");
		return r ;
	}
</script>
	<%if(request.getAttribute("removeSuc") != null) {
	%>
		<div style="position:relative;left:3.5cm;top:0.2cm;">
			删除成功<br/>
			<input type="button" value="确定" onclick="closeWin()">
		</div>
	<%	request.getSession().removeAttribute("removeSuc") ; }
	  else if(request.getAttribute("alterSuc") == null) 
	  {
	%>
	<s:actionerror/>
	<div style="position:relative;top:1em;">
		<form action="removeModule" onsubmit="if_rmv()" style="position:fixed;top:0.2cm; right:0.2cm;">
			<input name="moduleId" type="hidden" value="${id}"/>
			<input type="submit" onclick="" value="删除此模块"/>
		</form>
		<s:form action="alterModule" theme="simple">
		<div style="margin:0.3cm;">
			<p>模块名称</p><input type="text" name="moduleName" value="${currName}" style="width:4.7cm;"/><br/>
		</div>
			<p style="position:relative;top:-1.5em;">模块简介</p><textarea name="moduleDesc" style="height:3em;">${currDesc}</textarea>
			<input name="moduleId" type="hidden" value="${id}"/>
			<div style="margin:0.3cm;position:relative;left:4.5em;">
			<input type="reset" value="重置"/>
			<input type="submit" value="修改"/>
			</div>
		</s:form>
	</div>
	<%}
	else 
	{%>
	<s:actionmessage/>
	<div style="position:relative;left:3.5cm;top:0.2cm;">
		<input type="button" value="确定" onclick="closeWin()">
	</div>
	<%	request.getSession().removeAttribute("alterSuc") ;   
	} %>

</center>
</body>
</html>