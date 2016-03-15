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
    
    <title>title</title>
    
<style type="text/css">
body
{
	background-image:url(css/4.jpg);
	background-repeat:repeat;
    background-position:top;
    font-weight:bold;
}

table
{
	position:relative;
	top:-2cm;
}
</style>
  </head>
  
  <script type="text/javascript" language="JavaScript">
  
    function locate()
        {
             document.form1.text.focus();          
             
        }
        function check()
		{
			var str=document.getElementById("text").value;
			var str2 = document.getElementById("reply").innerHTML;
			if(str.length==0||str=="@"+str2+"\n"){
			     alert("输入框不能为空!");
			     document.form1.text.focus();
			     return;
			    }
		  }
    
 </script>
  <body>
  <%
  if(request.getAttribute("nologin")!=null)
	   	{
	   		out.print("<script>alert('对不起,还没有登录，请先登录！！');</script>") ;
	   	}
   		request.getSession().removeAttribute("nologin") ;
    %>
<%if(request.getSession().getAttribute("username") != null){%>
	<jsp:include page="topbar_logged2.jsp"></jsp:include>
<%}else{ %>
 	<jsp:include page="topbar_unlog.jsp"></jsp:include>
<%} %>  
 
   <table style="width:25cm;" align="center">
   <tr> 
    	<td  style="font-family:verdana;font-size:1.6em;height:2cm;"  align="center"  width="100%" colspan="4">${title }</td>
   </tr> 
   		
    <tr> 
    	
    	<td style="font-family:verdana;font-size:15px;color:green" nowrap="nowrap" align="right"  width="47%">楼主： ${myname } </td>
    	<td style="font-family:verdana;font-size:15px;color:green" nowrap="nowrap" align="center"  width="6%">回复数：  ${size }</td>
    	<td style="font-family:verdana;font-size:15px;color:green" nowrap="nowrap" align="left"  width="47%" colspan="2">发布时间： ${mytcio.topicPublicTime } </td>
    </tr> 
     <tr> 
    	<td  style="font-family:verdana;font-size:1.3em; border-style:solid; border-width:1px; border-color:#FFCC66;" align="center"  width="100%" colspan="4">${mytcio.topicContent }</td>
   </tr> 
    <s:iterator value="teinfo" id="t" status="s"> 
     
      <tr>  
      <td style="font-family:verdana;font-size:15px;color:green" nowrap="nowrap" align="right"  width="47%">用户：</td>   	 
      	<td  id="reply" style="font-family:verdana;font-size:15px;color:green"  align="left"  width="6%"><s:property value="ryurinfo.userName"/> </td>
       <td style="font-family:verdana;font-size:15px;color:green" nowrap="nowrap" align="left"  width="47%">时间： <s:property value="myryinfo.replyTime"/> </td> 
      
      
      <%if(request.getSession().getAttribute("admin") != null){ %> 
       <td ><form style="display:inline;" action="silence">
          		<input type="hidden" name="userName" value=<s:property value="ryurinfo.userName"/> />
          		<input type="hidden" name="type" value="silence" />
          		<input type="submit" value="禁言"/>
	   	   </form>
	   </td>
      <%} %>
      
      <!--  <td style="cursor: hand;font-size:15px;font-weight:bold; color:#4893A7;" align="right" width="43%"  onclick="locate();" > 回复</td> -->   	
           
      </tr>  
      <s:if test="#s.even">
       <tr bgcolor="#4893A7" style="font-size:18px;font-family:verdana">      
          <td style="width:25cm;"  nowrap="nowrap" align="left" colspan="4"> <s:property value="myryinfo.replyContent"/> </td>           
      </tr> 
    </s:if>       
       <s:if test="#s.odd">   
      <tr bgcolor="#4893A7" style="font-size:18px;font-family:verdana">      
          <td style="width:25cm;"align="left" colspan="4"><s:property value="myryinfo.replyContent"/></td>            
      </tr>  
      </s:if>      
  </s:iterator> 
    <s:form id="form1" action="doReply" onsubmit="check();"  >
	  <tr>
	  	<td id="test" style="width:25cm" align="center" width="100%" colspan="4"><div style="width:80px"> <textarea id="text" name="content" cols="80" rows="16"></textarea></div></td>
	  </tr>
	  <tr>
	  <td  width="1%"></td>
	 
	 
   	  <td style="font-family:verdana;font-size:15px" align="left"  width="47%"><input id="repliedId" type="hidden" name="tid" value="${tid}" > </td>  
   	  <td style="font-family:verdana;font-size:15px" align="left"  width="47%"><input type="hidden" name="size" value="${size }"></td>
	  <td style="font-family:verdana;font-size:15px"align="right"  width="6%">  <s:submit name="ok" value="提交"></s:submit></td>
	  	  
	  </tr>
	  </s:form>
	  
   </table>
  
  </body>
</html>
