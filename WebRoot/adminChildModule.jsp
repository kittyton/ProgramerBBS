<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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
	<script language="JavaScript">
	  	function if_rmv()
		{
			var r=confirm("确认删除此模块？");
			return r ;
		}
		
		function closeWin(){
	 	window.close();
		}
	</script>
	
	<%if(request.getAttribute("removeSuc") != null) {
	%>
		<div style="position:relative;left:3.5cm;top:0.2cm;">
			删除成功<br/>
			<input type="button" value="确定" onclick="closeWin()">
		</div>
	<%	request.getSession().removeAttribute("removeSuc") ; }
	else {
	%>
	
<div style="position:relative; left:2em;top:-2em;">	
	
	<h2 style="color:#336600;">
		父模块：${fatherName}
	</h2>
	
	<br/>
	

	<h3>增加子模块：</h3>
	<form action="addChildModule">
	<s:actionerror/>
	<s:actionmessage/>
		请输入子模块名称：<input type="text" name="currName"/>
		<input type="hidden" name="fatherId" value="${id}" />
		<input type="submit" value="添加"/>
	</form>
	
	<div style="border-bottom-style:dotted; margin-bottom:1cm; margin-top:0.5cm;border-color:#336600;position:relative; left:-2em;"></div>
	
	
	<div style="overflow-y:scroll;overflow-x:hidden; height:10cm;">
	<s:iterator value="#request.childModule_list" id="curr">
		<div>
		<form action="alterChildModule">
			<table>
			<tr><td>子模块名称</td>
			<s:actionerror/>
			<s:actionmessage/>
				<td><input type="text" name="childName" value="${curr.childModuleName}"/></td>
				<td><input type="hidden" name="childId" value="${curr.childModuleId}"/></td>
				<td><input type="hidden" name="id" value="${curr.childModuleId}"/></td>
				<td><input type="submit" value="修改"/></td>
				<td><input type="reset" value="重置"/></td></tr>
			</table>
		</form>
		</div>
		
		<div style="position:relative;left:11cm;top:-0.7cm;">
		<form action="removeChildModule" onsubmit="if_rmv()">
			<input type="hidden" name="childId" value="${curr.childModuleId}"/>
			<input type="submit" value="删除此子模块"/>
		</form>
		</div>
	</s:iterator>
	</div>
	<%} %>
	
</div>
</body>
</html>