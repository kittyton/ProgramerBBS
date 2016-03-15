<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>super administrator</title>
	<link rel="stylesheet" media="screen" type="text/css" href="css/admin.css" />
	
	<style type="text/css">
		body
		{
			background-image:url(css/background.jpg);
			background-repeat: repeat;
		}
		
  		h2 
  		{
  			font-family:幼圆,"Trebuchet MS", Arial, Helvetica, sans-serif;
  			color:#330000;
  			padding:0.2cm,0cm,0.05cm,0cm;
  		}
  		
  		h3
  		{
  			width:3cm;
  			display:inline;
  		}
  		
  		p
  		{
  			display:inline;
			width:3.5cm;
  		}
  		
  		pre
  		{
  			display:inline;
			width:3.5cm;
			font-size:1em;
  		}
				
		a
		{
			cursor:pointer;
			padding:0.2cm;
		}
		#userAd input
		{
			width:4.5cm;
			position:relative;
			left:0.4cm;
			top:-0.15cm;
		}
		#message
		{
			width:7cm;
			color:red;
		}
		
		table
		{
			font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
			width:100%;
			border-collapse:collapse;
		}
		table, td, th
		{
			font-size:1em;
			border:1px solid #98bf21;
			padding:3px 7px 2px 7px;
		}
		th 
		{
		    font-size:1.1em;
		    text-align:left;
		    padding-top:5px;
		    padding-bottom:4px;
		    background-color:#A7C942;
		    color:#ffffff;
		}
  	</style>
</head>
<body>

	<% 
		if(request.getAttribute("rmvSuc") != null)
	   	{
	   		out.print("<script>alert('"+request.getAttribute("rmvSuc")+"');</script>") ;
	   	}
   		request.getSession().removeAttribute("rmvSuc") ;
   		
   		if(request.getAttribute("addSuc") != null)
	   	{
	   		out.print("<script>alert('"+request.getAttribute("addSuc")+"');</script>") ;
	   	}
   		request.getSession().removeAttribute("addSuc") ;
	%>
	
	<script language="JavaScript">
		function openPassWin(){
			var iHeight = 200 ;
			var iWidth = 300 ;
			var iTop = (window.screen.availHeight-iHeight)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-iWidth)/2; //获得窗口的水平位置;
	     	window.open("adminAlterPass.jsp","修改密码",
			"height="+iHeight+",width="+iWidth+",top="+iTop+", left="+iLeft+",status=yes,toolbar=no,menubar=no,location=no");
	  	}
	  	
	  	function submit(){
		    var myform=document.getElementByIdx("removeForm");
		    myform.submit();
		}
	  	
	  	function if_rmv()
		{
			var r=confirm("确认删除此管理员？");
			return r ;
		}
		
		function showModule(i)
		{
			var iHeight = 250 ;
			var iWidth = 400 ;
			var iTop = (window.screen.availHeight-iHeight)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-iWidth)/2; //获得窗口的水平位置;
			//额。。。。i附近的引号可真是让人头大。。。
	     	window.open("alterModuleShow.action?id="+i+"","修改模块信息",
			"height="+iHeight+",width="+iWidth+",top="+iTop+", left="+iLeft+",status=yes,toolbar=no,menubar=no,location=no");	
		}
		
		function showChildModule(i)
		{
			var iHeight = 550 ;
			var iWidth = 650 ;
			var iTop = (window.screen.availHeight-iHeight)/2; //获得窗口的垂直位置;
			var iLeft = (window.screen.availWidth-iWidth)/2; //获得窗口的水平位置;
	     	window.open("alterChildModuleShow.action?id="+i+"","修改子模块信息",
			"height="+iHeight+",width="+iWidth+",top="+iTop+", left="+iLeft+",status=yes,toolbar=no,menubar=no,location=no");	
		}
	</script>
	

	<div class="rightPart"> 	  
	 <h2>增加管理员</h2>
	 <div class="superAdmin">
	 <form action="addAdmin">
	 <div  id="userAd">
		<div style="padding:0.1cm;"><pre>请输入用户名   </pre><input type="text" name="adminName" ></input></div>
		<div style="padding:0.1cm;"><pre>请输入密码     </pre><input type="password" name="password"></input></div>
		<div style="padding:0.1cm;"><pre>请再次输入密码 </pre><input type="password" name="repassword"></input></div>
	 </div>	
	<br/>
		请选择此管理员所属的模块：
    		<select name="module">
   				<s:iterator value="#request.optionList" id="currOp">
   					<option value="${currOp.moduleId}">${currOp.moduleName}</option>	
   				</s:iterator>
    		</select>
    	
	<div class="little">	
		<input type="reset" value="重置"/>
		<input type="submit"  value="添加"/>
	</div>
	</form>
	</div>
	
	<br/>
	<h2>现有管理员账号</h2>
	 <div class="superAdmin2">
	 <table>
	 <tr><th>用户名</th><th>所属模块</th><th/></tr>
	 <s:iterator value="#request.dataList" id="curr">
		 <tr><td>${curr.currAdmin.userName}</td>
		 <td>${curr.currModule.moduleName}</td>
		 <td>
		 <form id="removeForm" action="removeAdmin" style="display:inline" onsubmit="if_rmv()">				 	
			<a href="javascript:;"  onclick="submit()">删除此管理员</a>
			<input type="hidden" name="adminId" value="${curr.currAdmin.userId}"></input>
		 </form>
		 </td></tr>
		 </s:iterator>	
	</table>
		 
	 </div>	 
	 </div>
	
	<div class="alterPass">
		<a href="javascript:;" onclick="openPassWin()">修改个人密码</a>
		<a href="Exit.action">退出</a>
	</div>	
	
<center>
	<div id="message">
	<s:actionerror/>
	<s:actionmessage/>
	</div>
</center>	

	<div class="leftPart">
		<h2>增加模块</h2>
		
		<div class="superAdmin">
		<form action="addModule">
			<div style="padding:0.2cm;">
				<p style="width:4cm;">请输入模块名称：</p>
				<input type="text" name="moduleName" style="width:4.75cm;"/><br/>
			</div>
			<div style="padding:0.2cm;">
				<p style="width:4cm;">请输入模块简介：</p>
				<textarea name="moduleDesc" style="height:6em;display:inline;"></textarea><br/>
			</div>
			<div class="little2">
				<input type="submit" value="重置"/>
				<input type="submit" value="添加"/>
			</div>
		</form>
		</div>
		
		
		<br/>
		<h2>修改模块信息</h2>
		
		<div class="superAdmin2">
		<form>
		<table style="position:relative;">
			<s:iterator value="#request.optionList" id="curr">
			 	<tr><th>${curr.moduleName}</th>
			 		<td><a href="javascript:;" onclick="showModule(${curr.moduleId})">修改模块信息</a></td>
			 		<td><a href="javascript:;" onclick="showChildModule(${curr.moduleId})">修改子模块信息</a></td></tr><br/>
			</s:iterator>
		</table>
		</form>
		</div>
		
		
	</div>
</body>
</html>