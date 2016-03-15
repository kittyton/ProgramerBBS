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
    
    <title>Home Page</title>
    
	 <link href="css/left_css.css" rel="stylesheet" type="text/css">


  </head> 
 <SCRIPT language=JavaScript>
        function showsubmenu(sid)
        {
            whichEl = eval("submenu" + sid);
            if (whichEl.style.display == "none")
            {
                eval("submenu" + sid + ".style.display=\"\";");
            }
            else
            {
                eval("submenu" + sid + ".style.display=\"none\";");
            }
        }
    </SCRIPT>
    <%!int i =0; %>
   <body style="background-image:url(css/4.jpg);background-position:top;background-repeat: repeat;">
        <table width="80%" border="0"  align="center" >
         <s:iterator value="mymap" id="column">  
        <%i++; %>     
            <tr>
                <td><TABLE width="97%" border="0"  align="center" >
                        <TBODY>
                        <tbody>
                            <TR>
                                <TD valign="middle" height="25">
                                    <table width="100%" border="0"  align="center">
                                        <tr style="background:url(css/act_btn.gif) no-repeat">                                                                                  
                                            <%if(i==1){ %>
                                            <TD nowrap="nowrap" align="left" class=STYLE1 style="CURSOR: hand;" onclick=showsubmenu(1);height=25><s:property value="#column.key.moduleName"/></TD>
                                            <%}else if(i==2) {%>
                                            <TD nowrap="nowrap" class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(2);height=25><s:property value="#column.key.moduleName"/></TD>
                                            <%}else if(i==3){ %>
                                            <TD nowrap="nowrap" class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(3);height=25><s:property value="#column.key.moduleName"/></TD>
                                            <%}else if(i==4){ %>
                                            <TD nowrap="nowrap" class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(4);height=25><s:property value="#column.key.moduleName"/></TD>
                                            <%}else if(i==5){ %>
                                            <TD nowrap="nowrap" class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(5);height=25><s:property value="#column.key.moduleName"/></TD>
                                            <%}else if(i==6){ %>
                                            <TD nowrap="nowrap" class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(6);height=25><s:property value="#column.key.moduleName"/></TD>
                                            <%}else if(i==7){ %>
                                            <TD nowrap="nowrap" class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(7);height=25><s:property value="#column.key.moduleName"/></TD>
                                            <%}else{ %>
                                            <TD nowrap="nowrap" class=STYLE1 style="CURSOR: hand" onclick=showsubmenu(8);height=25><s:property value="#column.key.moduleName"/></TD>
                                            <%} %>
                                             
                                             
                                        </tr>
                                    </table></TD>
                            </TR>
                            <TR>
                                <TD><form><TABLE id=submenu<%=i %>  align="center" width="100%" border="0" >
                                        <TBODY>                                     
                                        
                                      	 <s:iterator value="#column.value" status="s" id="ch"> 
                                            <TR>  
                                            <td></td>  
                                             <TD nowrap="nowrap" align="right" width="18%"><IMG src="css/closed.gif"></TD>   
                                                <TD nowrap="nowrap" class=STYLE2 height=35><A href="showTopic.action?id=<s:property value="childModuleId" />&name=<s:property value="childModuleName"/>" target=down><s:property value="childModuleName"/></A></TD>
                                            </TR>                                           
                                           </s:iterator>
                                        </TBODY>
                                    </TABLE>                                    
                                   </form>
                                 </TD>
                            </TR>
                        </TBODY>
                    </TABLE>
                    </td>
           </tr>
            </s:iterator>             
        </table>
    </body>
</html>
