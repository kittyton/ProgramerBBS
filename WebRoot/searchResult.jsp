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
    
    <title>My JSP 'test1.jsp' starting page</title>
<style type="text/css">    
body
{
	background-image:url(css/4.jpg);
	background-repeat:repeat;
    background-position:top;
}
</style>

  </head>
  <body>
    <table width="100%">
   <tr style=" font-size:20px;height:1.3cm;font-weight: bold;font-family:verdana">
  		<td><font color="red">找到 ${size}条相关主题 </font> </td>
  	</tr> 
  	<tr bgcolor=white style="font-size:20px;height:1.3cm;font-weight: bold;font-family:verdana" bgcolor=white style="font-size:20px;font-weight: bold;font-family:verdana" >
  		 <td nowrap="nowrap"  align="left"  width="80%">主题</td>  		         
         <td nowrap="nowrap" align="center" width="20%">发布时间</td> 
  	</tr>
    <s:iterator value="topiclist" id="topiclt" status="tlt">   
      <tr style="font-size:20px;height:1.3cm;font-family:verdana">   
        <td nowrap="nowrap"  align="left"  width="80%"><A href="titleInfo.action?tid=<s:property value="topicId" />" target="_top"><s:property value="topicTitle"/></A></td>          
        <td nowrap="nowrap" align="center" width="20%"><s:property value="topicPublicTime"/></td>           
      </tr>      
  </s:iterator> 
    
   	 
    </table>
  </body>
</html>
