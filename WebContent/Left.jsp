<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.zhuoer.employeeAndCustomerInfo.dao.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>左侧导航</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/sdmenu.js"></script>
<script type="text/javascript">
	// <![CDATA[
	var myMenu;
	window.onload = function() 
	{
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	};
	// ]]>
</script>
<style type=text/css>
body{overflow-x:hidden; background:url(images/main/leftbg.png) left top repeat-y #f8f8f8; width:188px;}
</style>
</head>
<body onselectstart="return false;" ondragstart="return false;" oncontextmenu="return false;">
    <div style="float: left" id="my_menu" class="sdmenu">
    <% 
    	String id=request.getSession().getAttribute("name").toString();
    	Map<String,ArrayList<LinkedHashMap<String,String>>> menu = PowerMenuDao.getMenu(id);
   	%>
   	<%
	   	Iterator<String> iterator = menu.keySet().iterator();
		while(iterator.hasNext()) {
			//获得一级菜单名字
			String name = iterator.next().toString();
			//获得一级菜单对应的二级菜单
			/**
			* html
			*/
	%>
		<div id="menu1" class="collapsed" >
	       	<span><%=name %></span>
	<%
			ArrayList<LinkedHashMap<String, String>> list = menu.get(name);
			//遍历二级菜单
			for(int i=0; i<list.size(); i++) {
				//得到一级菜单对应的二级菜单集合
				LinkedHashMap<String, String> map = list.get(i);
				//得到二级菜单名字的数组
				Object[] array = map.keySet().toArray();
				for(Object obj : array) {
					//得到二级菜单名字
					String mname = obj.toString();
					//得到二级菜单链接
					String href = map.get(mname);
	%>
			<a href="<%=request.getContextPath() %>/<%=href %>" target="mainFrame" onFocus="this.blur()"><%=mname %></a>
	<%
				}
	
			}
	%>
			</div>
	<%
		}
     %>
    </div>
 
</body>
</html>
