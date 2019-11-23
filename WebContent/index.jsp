<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<meta HTTP-EQUIV="expires" CONTENT="0">
<title>山东奥晟达后台管理系统</title>
<link rel="shortcut icon" href="images/favicon.ico" />
<link href="css/css.css" type="text/css" rel="stylesheet" />
</head>
<!--框架样式-->
<frameset rows="65,*" cols="*" frameborder="no" border="0" framespacing="0">
<!--top样式-->
	<frame src="top.jsp" name="topframe" scrolling="no" noresize id="topframe" title="topframe" />
<!--contact样式-->
	<frameset id="attachucp" framespacing="0" border="0" frameborder="no" cols="188,12,*" rows="*"><!--cols="194,12,*" -->
		<frame scrolling="auto" noresize="" frameborder="no" name="leftFrame" src="Left.jsp"></frame>
		<frame id="leftbar" scrolling="no" noresize="" name="switchFrame" src="swich.html"></frame> 
		<%
			if(request.getSession().getAttribute("role")!=null) {
				String role = request.getSession().getAttribute("role").toString();
				if(role.equals("0") ) {
						
			%>
			<!-- 用户访问显示 -->
			<frame scrolling="auto" noresize="" border="0" name="mainFrame" src="<%=request.getContextPath() %>/password.jsp" style="padding:15px"></frame>
			<%
				}
				else {
			%>
			<!-- 操作员访问显示 -->
			<frame scrolling="auto" noresize="" border="0" name="mainFrame" src="<%=request.getContextPath() %>/thingsList.jsp" style="padding:15px"></frame>
			<%
				}
			}
			else {
			%>
			<script>
				window.top.location="<%=request.getContextPath()%>index.jsp";
			</script>
			<%
			}
			%>
	</frameset>
<noframes></noframes>
<!--不可以删除-->
</html>