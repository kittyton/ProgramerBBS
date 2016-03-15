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
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<style type="text/css">
table
{
 	font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
  	width:35%;
	position:relative;
	left:30%;
	top:5%;
	font-weight:bold;
}

body
{
	background-image:url(css/4.jpg);
	background-repeat:repeat;
    background-position:top;
    font-weight:bold;
    font-size:1.1em ;
}
</style>
	
</head>
  
<body>
<%if(request.getSession().getAttribute("username") != null){%>
	<jsp:include page="topbar_logged.jsp"></jsp:include>
<%}else{ %>
 	<jsp:include page="topbar_unlog.jsp"></jsp:include>
<%} %>
  
  
 <s:form  theme="simple" >
  	<table>
  	 
  	<tr style="font-weight: bold;font-size:23px;font-family:verdana;color: red" >
  		<td nowrap="nowrap" height="15%" rowspan="1"  colspan="4">所有投票</td>
  	</tr>
  	<tr height="10%" style="background:url(Images/frame.jpg);font-size:20px;font-weight: bold;font-family:verdana" >
  		 <td nowrap="nowrap"  align="left"  width="60%">投票主题</td> 
         <td nowrap="nowrap" align="center"  width="15%">  发起人</td> 
         <td nowrap="nowrap" align="center"  width="10%"> 已参与人数</td> 
         <td nowrap="nowrap" align="center" width="25%">发起时间</td> 
  	</tr>
  <s:iterator value="list" id="votes">
  <tr style="font-size:18px;font-family:verdana" height="10%">      
         <td nowrap="nowrap" align="left"  width="45%"><A href="Participatevote.action?voteID=<s:property value="#votes.voteID"/>" target="_top"> <s:property value="#votes.vote_title"/></A> </td> 
         <td nowrap="nowrap" align="center"  width="15%"><s:property value="#votes.vote_creater"/></td> 
         <td nowrap="nowrap" align="center"  width="10%"><s:property value="#votes.part_num"/></td> 
         <td nowrap="nowrap" align="left"  width="35%"><s:property value="#votes.create_time"/></td>          
      </tr>       
  </s:iterator> 
  	</table> 	
 	
<div style="position:relative; left:12cm; top:2cm;">  	
 <!-- 下面加分页的功能 -->
 <!-- 首页 -->
  <s:url id="url_first" value="Showvotes.action">
   <s:param name="pageNow" value="1"></s:param>
  </s:url>
  <!-- 上一页 -->
  <s:url id="url_pre" value="Showvotes.action">
   <s:param name="pageNow" value="pageNow-1"></s:param>
  </s:url>
  <!-- 下一页 -->
  <s:url id="url_next" value="Showvotes.action">
   <s:param name="pageNow" value="pageNow+1"></s:param>
  </s:url>
  <!-- 末页 -->
  <s:url id="url_last" value="Showvotes.action">
   <s:param name="pageNow" value="pageTotle"></s:param>
  </s:url>
  <!-- 如果不是首页则提供首页的链接,如果是首页则不提供链接,以下类似 -->
  
  <s:if test="pageNow != 1">
            [<s:a href="%{url_first}">首页</s:a>]   
              </s:if>
  <s:else>
            [首页]
        </s:else>
  <s:if test="pageNow>1">
            [<s:a href="%{url_pre}">上一页</s:a>] 
        </s:if>
  <s:else>
            [上一页] 
        </s:else>
  <s:if test=" pageTotle > pageNow ">
            [<s:a href="%{url_next}">下一页</s:a>]
        </s:if>
  <s:else>
            [下一页]
        </s:else>
  <s:if test="pageTotle != pageNow">
            [<s:a href="%{url_last}">末页</s:a>]
        </s:if>
  <s:else>
            [末页]
        </s:else>
  第${pageNow}页/ 共${pageTotle}页
 </div> 
  
   </s:form>
      </body>
</html>
