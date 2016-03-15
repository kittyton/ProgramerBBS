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
<style type="text/css">
body
{
	background-image:url(css/4.jpg);
	background-repeat:repeat;
    background-position:top;
    font-weight:bold;
}
a{color:black;}
</style>
  </head>
  
  <body bgcolor=#ADDFEB >
 <s:form  theme="simple" >
  	<table >
  	<tr>
  		<td style="font-weight: bold;font-size:23px;font-family:verdana;color: red" align="left" nowrap="nowrap" height="15%" rowspan="1"  colspan="3">最新发布</td>
  		<td style="font-weight: bold;font-size:18px;font-family:verdana" align="right" nowrap="nowrap" height="15%" rowspan="1"  colspan="1"></td>
  	</tr>
  	<tr bgcolor=white  style="font-size:20px;height:1.3cm;font-weight: bold;font-family:verdana;width:100%" >
  		 <td nowrap="nowrap"  align="left"  width="50%">主题</td> 
         <td nowrap="nowrap" align="center"  width="5%">发布人</td> 
         <td nowrap="nowrap" align="center"  width="5%">回复数</td> 
         <td nowrap="nowrap" align="center" width="25%">发布时间</td> 
  	</tr>
  	 <s:iterator value="term" id="tpcs">    
      <tr style="font-size:20px;height:1cm;font-family:verdana">      
         <td nowrap="nowrap" align="left"  width="50%"><A href="titleInfo.action?tid=<s:property value="key.topicId" />" target="_top"> <s:property value="#tpcs.key.topicTitle"/></A> </td>
          <td nowrap="nowrap" align="center"  width="5%"> <s:property value="value.value"/></td>  
         <td nowrap="nowrap" align="center"  width="5%"><s:property value="value.key"/></td> 
        <td nowrap="nowrap" align="left"  width="25%"><s:property value="#tpcs.key.topicPublicTime"/></td>          
      </tr>         
  </s:iterator> 
  	</table>
 </s:form>
      </body>
</html>
