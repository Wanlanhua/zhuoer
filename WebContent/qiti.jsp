<%@page import="com.zhuoer.newCareDevice.bean.Qiti"%>
<%@page import="com.zhuoer.customer_management.Bean.CustomerInfo"%>
<%@page import="com.zhuoer.aJsonAPI.No"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zhuoer.careDevice.entity.*" %>
<%@ page import="java.util.List" %>
    
<%
	
	int role = Integer.parseInt(session.getAttribute("role").toString());
	Map<String, String> map = (Map)request.getSession().getAttribute("operate");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主要内容区</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/dataBox.css" />
<link rel="stylesheet" href="css/jquery.css">
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script>
	var addActionTarget = "<%=request.getContextPath() %>/InsertQITI";
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="searchmain" >
  <tr>
    <td width="99%" align="left" valign="top">您的位置：气体类型</td>
  </tr>
  <tr>
  <%
  	String classNone = "";
  %>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		<tr>
   		 <td width="90%" align="left" valign="middle">
	         <form method="post" action="<%=request.getContextPath() %>/newCareDevice/GetQITI">
	         <span>设备单位：</span>
	         <input type="text" id="findByNo" name="shebei" value="" class="text-word">
	          <%
	         	if(map.get("find").equals("0"))
	         		classNone = "none";
	         	else classNone = "block";
	         %>
	         <input type="submit" value="查询" class="text-but" style="display:<%=classNone%>;">
	         </form>
	         </td>
         <%
         	if(map.get("input").equals("0"))
         		classNone = "none";
         	else classNone = "table-cell";
         %>
  		  <td width="10%" align="center" valign="middle" style="text-align:right; width:200px;display:<%=classNone%>">
  		  	<a href="javascript:void(0);" target="mainFrame" onFocus="this.blur()" class="add" id="addDom">气体信息</a>
  		  </td>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr  class="bggray">
        <th align="center" valign="middle">气体名称</th>
        <th align="center" valign="middle" style="display: none;" >id</th>
        <%
        	if(map.get("modify").equals("1") || map.get("del").equals("1")) {
        		classNone = "block";
        	}
        	else classNone = "none";
        %>
        <th align="center" valign="middle" style="display:<%=classNone%>">操作</th>
      </tr>
      
      <%
      		List<Qiti> list = (List)request.getSession().getAttribute("qtlist");
      		if(list != null) {
      			for(int i=0; i<list.size(); i++) {
      %>
      
			      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
			        <th align="center" valign="middle" ><%=list.get(i).getName() %></th>
			        <th align="center" valign="middle" style="display: none;"><%=list.get(i).getId() %></th>
			        <td align="center" valign="middle" class="borderbottom">
			        	 <%
				         	if(map.get("del").equals("0"))
				         		classNone = "none";
				         	else classNone = "inline";
				         %>
			        	<a href="<%=request.getContextPath() %>/DeleteQITI?id=<%=list.get(i).getId() %>" style="display:<%=classNone%>" onclick="return deleteData()" target="mainFrame" onFocus="this.blur()" class="deleteData">删除</a>
		        	</td>
		        <%
			        	}
		        	}
		        %>
		        
		      </tr>
      
    </table>
    </td>
    </tr>
</table>

<div id="dataBox">
	
	<div style="text-align: center;width:45%;">
		<div class="data_head">
			<h2><span id="dataBoxTitle"></span>气体类型</h2>
			<span class="data_close" id="dataClose">x</span>
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr>
		    	<td align="left" valign="top">
			    	<form method="post" action="">
				    	<table width="90%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
				      
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">气体名称：</td>
								<td align="left" valign="middle">
									<input type="text" name="name" value="">
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">id：</td>
								<td align="left" valign="middle">
									<input type="text" name="id" value="" updateNone addNone stateNone deleteNone dataNull>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">&nbsp;</td>
								<td align="left" valign="middle">
									<input name="" id="dataCommit" type="submit" value="确定">
									<input name="" id="dataCanCle" type="reset" value="取消"></td>
							</tr>
				    	</table>
				    </form>
			    </td>
		    </tr>
		</table>
	</div>
	
	<script type="text/javascript" src="js/careDevice.js" ></script>
</div>

</body>
</html>