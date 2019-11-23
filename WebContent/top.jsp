<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.zhuoer.userLogin.*" %>
<%@ page contentType="text/html;charset=utf-8"%> 
<% request.setCharacterEncoding("utf-8");%>   
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台页面头部</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
</head>
<body onselectstart="return false" oncontextmenu=return(false) style="overflow-x:hidden;">
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="header">
  <tr>
    <td id="logo"><img src="images/main/logo1.png" width="40" height="35"></td>
    <td align="left" >
    <table height="60" width="100%" border="0" cellspacing="0" cellpadding="0" >
      <tr>
        <td width="20%" align="left" valign="middle" ><h1 class="blue yahei">山东奥晟达机械技术服务有限公司</h1></td>
        <td width="40%" align="left" valign="middle" id="version"></td>
        <td width="30%" align="right" valign="middle" id="header-right">
        	<a href="<%=request.getContextPath()%>/SafetyExist" target="_top" onFocus="this.blur()" class="admin-out">安全退出</a>
        	<a onFocus="this.blur()" class="admin-index"><%=session.getAttribute("name")%></a>    	
        </td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>