<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zhuoer.careDevice.entity.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.zhuoer.careDevice.util.*" %>
<%@ page import="com.zhuoer.longinInfo.entity.*" %>
<%@ page import="com.zhuoer.longinInfo.dao.*" %>
    
<%
	
	int thisPage = 1;
	int pageSize = 1;
	int role = Integer.parseInt(session.getAttribute("role").toString());
	if(session.getAttribute("liPage") != null ) {
		pageSize = Integer.parseInt(session.getAttribute("liPage").toString());
	}
	if(session.getAttribute("liThisPage")!=null) {
		thisPage = Integer.parseInt(session.getAttribute("liThisPage").toString());
	}
	if(pageSize%15==0)
		pageSize = pageSize/15;
	else pageSize = pageSize/15+1;
	Map map = (Map)request.getSession().getAttribute("operate");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主要内容区main</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/dataBox.css" />
<script>
	var addActionTarget = "<%=request.getContextPath() %>/employeeAndCustomerInfo/InsertLI";
	var deleteActionTarget = "<%=request.getContextPath() %>/employeeAndCustomerInfo/DeleteLI";
	var updateActionTarget = "<%=request.getContextPath() %>/employeeAndCustomerInfo/UpdateLI";
	//操作员信息
	var employee = <%=getEmployeeInfoDao.getJsonEmployees() %>
	//客户信息
	var customer = <%=getEmployeeInfoDao.getJsonCustomers() %>
</script>
</head>
<body>
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：用户信息</td>
  </tr>
  <tr>
  <%
  String classNone = "none";
  %>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		<tr>
   		 <td width="90%" align="left" valign="middle">
	         <form method="post" action="<%=request.getContextPath() %>/employeeAndCustomerInfo/GetLI?page=1">
	         <span>用户：</span>
	         <input type="text" name="qname" value="" class="text-word">
	          <%
	         	if(map.get("find").equals("0"))
	         		classNone = "none";
	         	else classNone = "block";
	         %>
	         <input type="submit" style="display:<%=classNone%>;" value="查询" class="text-but">
	         </form>
         </td>
         <%
	         if(map.get("input").equals("0"))
	      		classNone = "none";
	      	 else classNone = "table-cell";
         %>
  		  <td width="10%" style="display:<%=classNone%>;" align="center" valign="middle" style="text-align:right; width:150px;"><a href="javascript:void(0);" target="mainFrame" onFocus="this.blur()" class="add" id="addDom">添加用户</a></td>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr  class="bggray">
        <th align="center" valign="middle" >权限</th>
        <th align="center" valign="middle" >登陆号</th>
        <th align="center" valign="middle" style="display: none;">备注</th>
        <th align="center" valign="middle" style="display: none;" >id</th>
        <th align="center" valign="middle" >密码</th>
        <%
        	if(map.get("modify").equals("1") || map.get("del").equals("1")) {
        		classNone = "block";
        	}
        	else classNone = "none";
        %>
        <th align="center" valign="middle" style="display:<%=classNone%>">操作</th>
      </tr>
      
      <%
      		List<LoginInfo> list = (List)session.getAttribute("liList");
      		if(list != null) {
      			for(int i=0; i<list.size(); i++) {
      %>
      
			      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
			      	<%
			      		int irole = Integer.valueOf(list.get(i).getRole());
			      		String srole = "";
			      		switch (irole) {
				      		case 0 : srole = "观察员";
				      		break;
				      		case 1 : srole = "员工";
				      		break;
				      		case 2 : srole = "班长";
				      		break;
				      		case 3 : srole = "项目经理";
				      		break;
				      		case 4 : srole = "总经理";
				      		break;
				      		case 5 : srole = "超级管理员";
				      		break;
			      		}
			      	%>
			        <th align="center" valign="middle" ><%=srole %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getName() %></th>
			        <th align="center" valign="middle" style="display: none;"><%=list.get(i).getMark() %></th>
			        <th align="center" valign="middle" style="display: none;"><%=list.get(i).getId() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getPassword() %></th>
			        <td align="center" valign="middle" class="borderbottom">
			        	<%
				         	if(map.get("modify").equals("0"))
				         		classNone = "none";
				         	else classNone = "inline";
				         %>
		        		<a href="javascript:void(0);" style="display:<%=classNone%>;" target="mainFrame" onFocus="this.blur()" class="updateData">编辑</a>
			        	<%
				         	if(map.get("del").equals("0") || list.get(i).getName().equals("admin"))
				         		classNone = "none";
				         	else classNone = "inline";
				         %>
			        	<span class="gray" style="display:<%=classNone%>;">&nbsp;|&nbsp;</span>
			        	<a href="<%=request.getContextPath() %>/employeeAndCustomerInfo/DeleteLI?id=<%=list.get(i).getId() %>" style="display:<%=classNone%>;" target="mainFrame" onFocus="this.blur()" onclick="return deleteData()" class="deleteData">删除</a>
			        </td>
		        <%
		        	}
		        %>
		        
		      </tr>
      
      <%
      		}
      %>
      
    </table>
    </td>
    </tr>
    <% 
    	if(list!=null) {
    %>
		  <tr>
		    <td align="left" valign="top" class="fenye">
		    
		    	<%=list.size() %> 条数据 <%=thisPage %>/<%=pageSize %> 页&nbsp;&nbsp;
	    		<a href="<%=request.getContextPath() %>/employeeAndCustomerInfo/GetLI?page=1" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
		    	<%
		    		int beforePage = thisPage - 1;
		    		if(thisPage>1) {
		    		
		    	%>
		    		<a href="<%=request.getContextPath() %>/employeeAndCustomerInfo/GetLI?page=<%=beforePage %>" target="mainFrame" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;
		    	<% 
		    		}
		    	%>
		    	<%
		    		int afterPage = thisPage + 1;
		    		if(thisPage<pageSize) {
		    	%>
		    		<a href="<%=request.getContextPath() %>/employeeAndCustomerInfo/GetLI?page=<%=afterPage %>" target="mainFrame" onFocus="this.blur()">下一页</a>&nbsp;&nbsp;
		    	<%
		    		}
		    	%>
		    	
		    	<%
		    		if(thisPage != pageSize) {
		    	%>
		    		<a href="<%=request.getContextPath() %>/employeeAndCustomerInfo/GetLI?page=<%=pageSize %>" target="mainFrame" onFocus="this.blur()">尾页</a></td>
		    	<%
		    		}
		    	%>
		  </tr>
		  
	<%
    	}
	%>
</table>

<div id="dataBox">
	
	<div style="text-align: center;width:45%;">
		<div class="data_head">
			<h2><span id="dataBoxTitle"></span>用户</h2>
			<span class="data_close" id="dataClose">x</span>
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr>
		    	<td align="left" valign="top">
			    	<form method="post" action="">
				    	<table width="90%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
				      
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">角色：</td>
								<td align="left" valign="middle">
									<select name="role" value="" deleteDisabled>
										<option value="4">总经理</option>
										<option value="3">项目经理</option>
										<option value="2">班长</option>
										<option value="1">员工</option>
										<option value="0">观察员</option>
									</select>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">登陆号：</td>
								<td align="left" valign="middle">
									<select name="name" value="" deleteDisabled>
										<!-- 登录号，js动态获取 -->
										<%
											List<EmployeeInfo> clist = getEmployeeInfoDao.getLoginEmployees();
											for(int i=0; i<clist.size(); i++) {
										%>
											<option value="<%=clist.get(i).getNo() %>"><%=clist.get(i).getNo() %>  <%=clist.get(i).getName() %></option>
										<%
											}
										%>
									</select>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">备注：</td>
								<td align="left" valign="middle">
									<input type="text" name="mark" value="" updateNone addNone stateNone dataNull >
								</td>
							</tr>
							
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">id：</td>
								<td align="left" valign="middle">
									<input type="text" name="id" value="" updateNone addNone stateNone  dataNull >
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">密码：</td>
								<td align="left" valign="middle">
									<input type="text" name="password" value="" deleteDisabled >
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
	
	<script type="text/javascript" src="js/loginInfo.js" ></script>
</div>

</body>
</html>