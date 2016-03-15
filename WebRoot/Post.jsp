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
    
    <title>My JSP 'Post.jsp' starting page</title>
 
<style type="text/css">    
body
{
	background-image:url(css/4.jpg);
    background-position:top;
    font-weight:bold;
    background-repeat: repeat;
}
</style>

  </head>
  
 <body>
  <script type="text/javascript" language="JavaScript">
        function check()
		{
			var str=document.getElementById("head").value;
			var str2 = document.getElementById("body").value;
			if(str.length==0 ){
			     alert("帖子的标题不能为空!");
			     document.doPo.head.focus();
			     return;
			    }
			if(str2.length==0 ){
			     alert("帖子的标题不能为空!");
			     document.doPo.body.focus();
			     return;
			    }
		  }
    
 </script>
 <jsp:include page="topbar_logged2.jsp"></jsp:include>
  <s:form id="doPo" action="doPo" onsubmit="check();" >
   <table align="center" width="951" height="386" style="width: 951px; height: 386px;">
	   <tr> 
	    	<td>请选择分类： </td>
	    </tr>
	    <tr>
	    	<td align="left"><select name="choose">
		    	<s:iterator value="module" id="classes" status="s">
		    	<s:if test="#s.first">
					<option value=<s:property value="#classes.getChildModuleId()"/>> <s:property value="#classes.getChildModuleName()"/></option>
				</s:if>
				<s:else>
					<option value=<s:property value="#classes.getChildModuleId()"/>> <s:property value="#classes.getChildModuleName()"/></option>
				</s:else>
				</s:iterator>
			</select></td>
	   </tr> 
	   <tr>
	   		<td align="left">标题：</td>   
	   </tr>
	   <tr>
	   		<td align="center"><input type="text" name="head" maxlength="180" size="120" ></td>
	   </tr>
	    <tr>
	   		<td>内容：</td>   
	   </tr>
	   <tr>
	   		<td align="center"><textarea  name="body" cols="92" rows="5"></textarea></td>
	   </tr>
	   <tr>
	   		<td align="center"><input type="hidden" name="state" value="ok"></td>
	   </tr>
	   <tr >
	   		<td align="center"><s:submit name="ok" value="提交"></s:submit></td>
	   <tr>     
   </table>
   </s:form>
  </body>
</html>
