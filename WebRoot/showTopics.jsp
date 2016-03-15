<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">  

<title>Topics</title>
<style type="text/css">
body
{
	background-image:url(css/4.jpg);
	background-repeat:repeat;
    background-position:top;
    font-weight:bold;
}
a
{
	color:black;
}
</style>

</head>
  
  <body>
   <h3  style="font-weight: bold;font-size:20px" align="left">模块<font color="red" size="5px">${name}</font>包含的主题</h3>
  <table> 
  <tr bgcolor=white style="font-size:20px;font-weight: bold;font-family:verdana">
  		 <td nowrap="nowrap"  align="left"  width="50%">主题</td>  
  		  <td nowrap="nowrap" align="center"  width="5%">  发布人</td> 
         <td nowrap="nowrap" align="center"  width="5%"> 回复数</td>          
         <td nowrap="nowrap" align="center" width="20%">发布时间</td> 
  	</tr>
  	 <s:iterator value="mytopic" id="column">      
      <tr style="font-size:18px;font-family:verdana">      
        <td  align="left"  width="50%"><A href="titleInfo.action?tid=<s:property value="key.topicId" />" target="_top"><s:property value="key.topicTitle"/></A></td> 
         <td nowrap="nowrap" align="center"  width="15%"><s:property value="value.value"/></td> 
         <td nowrap="nowrap" align="center"  width="15%"><s:property value="value.key"/> </td> 
         <td nowrap="nowrap" align="center"  width="20%"><s:property value="key.topicPublicTime" /> </td>  
        
        
<%if(request.getSession().getAttribute("admin") != null){ %>        
       	  <td  nowrap="nowrap"> 
          	<a href="silence.action?type='silence'&userName=<s:property value="value.value" /> " target="_top">禁言</a> 
          </td>
				
          <td  nowrap="nowrap"> 
          	<a href="removemTopic.action?topicId=<s:property value="key.topicId"/>" target="_top">删帖</a> 
          </td> 
<%} %>     
                  
      </tr>        
   </s:iterator>     
  </table>
  </body>
</html>
