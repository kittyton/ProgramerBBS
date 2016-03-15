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
    
    <title>My JSP 'test2.jsp' starting page</title>
    
<style type="text/css">
body
{
	background-image:url(css/4.jpg);
    background-position:top;
    font-weight:bold;
    font-size:1.1em ;
    background-repeat: repeat;
}


</style>
  </head>
  
  
  <body>
	<jsp:include page="topbar_logged2.jsp"></jsp:include>
 <s:form  theme="simple" action="Handlevote">
  	<table >
  	 
  	<tr style="font-weight: bold;font-size:23px;font-family:verdana;color: red" >
  		<td nowrap="nowrap" height="15%" rowspan="1"  colspan="4">投票详细信息</td>
  	</tr>
  	  <tr>
       <td nowrap="nowrap" align="left"  width="15%">投票标题 </td>
       <td nowrap="nowrap" align="left"  width="80%"><s:property value="vote_title"/> </td> 
      </tr>
        <tr>
       <td nowrap="nowrap" align="left"  width="15%">投票描述（要求）</td>
       <td nowrap="nowrap" align="left"  width="80%"><s:property value="vote_des"/> </td> 
      </tr>   
      <tr style="font-size:18px;font-family:verdana" height="10%">      
         <td nowrap="nowrap" align="left"  width="45%"> 
         <s:checkboxlist name="selectItem" list="voteselect"/> 
         </td> 
          </tr>      
  	</table>
  	<s:submit value="确认投票" align="center"/>
  	<s:hidden name="voteID" value="%{voteID}"></s:hidden>
 </s:form>
 <s:form action="Showvoteresult">
 <s:submit value="查看结果"  align="center"/>
 <s:hidden name="voteID" value="%{voteID}"></s:hidden>
 </s:form>
  <A href="Showvotes.action" > 返回</A>   
      </body>
     
</html>
