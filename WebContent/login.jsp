<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/login.css" rel="stylesheet" type="text/css">

  </head>
  
  <body>
   <script language="javaScript" type="text/javascript"  charset="utf-8">
    	function onValidate()
    	{
    		var name=document.getElementById("userName");
    		var password=document.getElementById("userPassword");
    		if(name.value.length==0)
    		{
    			window.alert("用户账号不能为空,请重新输入账号");
    			name.focus();
    			return false;
    		}
    		if(password.value.length==0)
    		{
    			window.alert("用户密码不能为空");
    			password.focus();
    			return false;
    		}
    	}
    
    </script>
<div class="login_box">
      <div class="login_l_img"><img src="images/login-img.png"></div>
      <div class="login">
          <div class="login_logo">
          	<img src="images/login_logo.png">
          </div>
          <div class="login_name">
               <p>后台管理系统</p>
          </div>
          <form action="UserLoginServlet" method="post" name="login_form">
          		<input name="userName" type="text" id="username_text"  value="用户名" onfocus="this.value=''" onblur="if(this.value==''){this.value='用户名';}"><br>
          		<span id="password_text" onclick="this.style.display='none';document.getElementById('password').style.display='block';document.getElementById('password').focus().select();" style="display: block;">密码</span>
				<input name="userPassword" type="password" id="password" style="display: none;" onblur="if(this.value==''){document.getElementById('password_text').style.display='block';this.style.display='none';};">
          		<input value="登录" style="width:100%;" type="submit" onclick="onValidate();">
          </form>
      </div>
      <div class="copyright"><img style="width:100px;height:100px" src="images/loadapk.png">  晟达机械服务有限公司 版权所有©2018-2025 技术支持电话：0535-88888888</div>
      
</div>

    
  </body>
</html>
