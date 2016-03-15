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
    
    <title>My JSP 'lookFor.jsp' starting page</title>

	<style type="text/css">
		A{
			color:black;
			padding:0.8cm;
		}
	</style>

  </head>
  <SCRIPT language=JavaScript>
        function getvalue()
        {
             var myvalue = document.getElementById("mysearch").value;
             document.getElementById("myform").href = "dosearch.action?search="+myvalue;             
        }
    </SCRIPT>
  <body style="background-image:url(css/top3.jpg);background-position:top;">  
  <s:form theme="simple">	 
  	<table align="right">  
  	 <tr align="right"> 	
  	 
<%if(request.getSession().getAttribute("username") != null) 
{%>  	 	 
	<td style="font-weight: bold;font-size:18px;font-family:verdana" align="left" nowrap="nowrap" height="15%" rowspan="1"  colspan="1"><A href="doPo.action" target="_top" >发帖</A></td>
  		<td style="font-weight: bold;font-size:18px;font-family:verdana" align="left" nowrap="nowrap" height="15%" rowspan="1"  colspan="1"><A href="lanchvote.jsp" target="_top" >发起投票</A></td>
  		<td style="font-weight: bold;font-size:18px;font-family:verdana" align="left" nowrap="nowrap" height="15%" rowspan="1"  colspan="1"><A href="Showvotes.action" target="_top" >参与投票</A></td>
  		<td style="font-weight: bold;font-size:18px;font-family:verdana" align="left" nowrap="nowrap" height="15%" rowspan="1"  colspan="1"><A href="Selfmanage.action" target="_top" >个人信息</A></td>
		
<%if(request.getSession().getAttribute("admin") != null){ %>
		<td style="font-weight: bold;font-size:18px;font-family:verdana" align="left" nowrap="nowrap" height="15%" rowspan="1"  colspan="1"><A href="silence.action" target="_top" >查看禁言用户</A></td>
<%} %>
		
		<td style="font-weight: bold;font-size:18px;font-family:verdana" align="left" nowrap="nowrap" height="15%" rowspan="1"  colspan="1"><A href="Exit.action" target="_top" >退出</A></td>

<%}else {%>
		<td style="font-weight: bold;font-size:18px;font-family:verdana" align="left" nowrap="nowrap" height="15%" rowspan="1"  colspan="1"><A href="register.jsp" target="_top" >用户注册</A></td>
		<td style="font-weight: bold;font-size:18px;font-family:verdana" align="left" nowrap="nowrap" height="15%" rowspan="1"  colspan="1"><A href="login.jsp" target="_top" >用户登陆</A></td>
<%} %>
  		
  		<td align="right" width="95%" rowspan="1">
  			<s:textfield name="mysearch" value="在此输入关键字搜索"  onFocus="if (value =='在此输入关键字搜索'){value ='';}" onBlur="if (value ==''){value='在此输入关键字搜索';}"></s:textfield>	
  		<td style="font-weight: bold;font-size:13px;font-family:verdana" align="left" width="5%" nowrap="nowrap" colspan="1">  			 
  			<A id="myform" href="jsp/t.jsp" onclick=getvalue() target=down>搜索</A> 
  		</td>  		
  	</tr>     	 	 
  	</table> 
 </s:form> 
      </body>
</html>
