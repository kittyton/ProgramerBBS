<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
<%if(request.getSession().getAttribute("username") != null){%>
	<jsp:include page="topbar_logged2.jsp"></jsp:include>
<%}else{ %>
 	<jsp:include page="topbar_unlog.jsp"></jsp:include>
<%} %>  
    <base href="<%=basePath%>">
    
    <title>投票结果</title>
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
  <script type="text/javascript">
  function back(){
   history.back(-1);
}
 </script>
 
 <div style="position:relative;left:30%;">
 <s:form  theme="simple" >
  	<table >
  	 
  	<tr style="font-weight: bold;font-size:23px;font-family:verdana;color: red" >
  		<td nowrap="nowrap" height="15%" rowspan="1"  colspan="4">有关该投票的结果如下：</td>
  	</tr>
  	<tr height="10%" style="background:url(Images/frame.jpg);font-size:20px;font-weight: bold;font-family:verdana" >
  		 <td nowrap="nowrap"  align="left"  width="15%">投票主题：</td> 
         <td nowrap="nowrap" align="center"  width="65%"><s:property value="votetitle"/></td>  
  	</tr>
  	<tr style="font-size:18px;font-family:verdana" height="10%">      
         <td nowrap="nowrap" align="left"  width="60%">投票选项 </td> 
         <td nowrap="nowrap" align="center"  width="30%">投票结果</td>           
      </tr>
  	 <s:iterator value="resultlist" id="result">    
       
         <tr style="font-size:18px;font-family:verdana" height="10%">      
         <td nowrap="nowrap" align="left"  width="60%"><s:property value="#result.select_Des"/> </td> 
         <td nowrap="nowrap" align="center"  width="30%"><s:property value="#result.total_num"/></td>           
      </tr>      
  </s:iterator> 
  <tr>
<A href="Participatevote.action?voteID=<s:property value="%{voteID}"/>" >返回</A>
  </tr>
  	</table>
 </s:form>
 </div>
      </body>
</html>
