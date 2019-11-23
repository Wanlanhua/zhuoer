	<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="java.util.List" %>
	<%@ page import="com.zhuoer.customer_management.Bean.*" %>
	<%
		int thisPage = 1;
		int pageSize = 1;
		int role = Integer.parseInt(session.getAttribute("role").toString());
		if(request.getAttribute("pageSizeCostomer") != null )
			pageSize = Integer.parseInt(request.getAttribute("pageSizeCostomer").toString());
		if(request.getAttribute("thisPageCostomer")!=null)
			thisPage = Integer.parseInt(request.getAttribute("thisPageCostomer").toString());

		Map<String, String> map = (Map)request.getSession().getAttribute("operate");
	%>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>主要内容区</title>
	<link href="css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="css/dataBox.css" />
	<script>
		var addActionTarget = "<%=request.getContextPath() %>/InserCustomerInfo";
		var updateActionTarget = "<%=request.getContextPath() %>/UpdateCustomerInfo";
	</script>
	 <link rel="stylesheet" href="css/jquery.css">
  	<script src="js/jquery.js"></script>
  	<script src="js/jquery-ui.min.js"></script>
	</head>
	<body>
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain" >
	  <tr>
	    <td width="99%" align="left" valign="top">您的位置：项目部门</td>
	  </tr>
	  <tr>
	  <%
	  	String classNone = "none";
	  %>
	    <td align="left" valign="top">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
	  		<tr>
	   		 <td width="80%" align="left" valign="middle">
		         <form method="post" action="<%=request.getContextPath() %>/GetCustomerInfo?page=1">
		         <span>客户名称：</span>
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
	  		  <td width="20%" align="center" style="display:<%=classNone%>;" valign="middle" style="text-align:right; width:180px;">
	  		  <a href="javascript:void(0);" target="mainFrame" onFocus="this.blur()" class="add" id="addDom">项目部门</a></td>
	  		</tr>
		</table>
	    </td>
	  </tr>
	  <tr>
	    <td align="left" valign="top">
	    
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
	      <tr  class="bggray">
	        <th align="center" valign="middle" style="display: none;" >id</th>
	        <th align="center" valign="middle" >客户名称</th>
	        <th align="center" valign="middle" >客户编号</th>
	        <th align="center" valign="middle" >经营地址</th>
	        <th align="center" valign="middle">税号</th>
	        <th align="center" valign="middle">开户银行</th>
	        <th align="center" valign="middle">银行账号</th>
	        <th align="center" valign="middle">电话</th>
	        <th align="center" valign="middle">项目部门编号</th>
	        <th align="center" valign="middle">项目部门名称</th>
	        <%
	        	if(map.get("modify").equals("1") || map.get("del").equals("1")) {
	        		classNone = "block";
	        	}
	        	else classNone = "none";
	        %>
	        <th align="center" valign="middle" style="display:<%=classNone%>">操作</th>
	      </tr>
	      
	      <%
	      		List<CustomerInfo> list = (List)request.getSession().getAttribute("listCostomer");
	      		if(list != null) {
	      			for(int i=0; i<list.size(); i++) {
	      %>
				      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	
				       
				        <th align="center" valign="middle" ><%=list.get(i).getName() %></th>
				        <th align="center" valign="middle" style="display: none;"><%=list.get(i).getId() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getNo() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getManagementAddress() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getTaxNumber() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getBank() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getAccount() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getPhone() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getDepartmentid() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getDepartmentname() %></th>
					        <td align="center" valign="middle" class="borderbottom">
					        	<%
						         	if(map.get("modify").equals("0"))
						         		classNone = "none";
						         	else classNone = "inline";
						         %>
					        	<a href="javascript:void(0);" style="display:<%=classNone%>;" target="mainFrame" onFocus="this.blur()" class="updateData">编辑</a>
					        	<%
						         	if(map.get("del").equals("0"))
						         		classNone = "none";
						         	else classNone = "inline";
						         %>
					        	<span class="gray" style="display:<%=classNone%>;">&nbsp;|&nbsp;</span>
					        	<a href="<%=request.getContextPath() %>/DeleteCustomerInfo?id=<%=list.get(i).getId() %>" style="display:<%=classNone%>;" target="mainFrame" onFocus="this.blur()" class="deleteData">删除</a>
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
		    		<a href="<%=request.getContextPath() %>/CustomerInfo_servlet?page=1"  onFocus="this.blur()">首页</a>&nbsp;&nbsp;
			    	<%
			    		int beforePage = thisPage - 1;
			    		if(thisPage>1) {
			    		
			    	%>
			    		<a href="<%=request.getContextPath() %>/CustomerInfo_servlet?page=<%=beforePage %>"  onFocus="this.blur()">上一页</a>&nbsp;&nbsp;
			    	<% 
			    		}
			    	%>
			    	<%
			    		int afterPage = thisPage + 1;
			    		if(thisPage<pageSize) {
			    	%>
			    		<a href="<%=request.getContextPath() %>/CustomerInfo_servlet?page=<%=afterPage %>"  onFocus="this.blur()">下一页</a>&nbsp;&nbsp;
			    	<%
			    		}
			    	%>
			    	
			    	<%
			    		if(thisPage != pageSize && thisPage > pageSize) {
			    	%>
			    		<a href="<%=request.getContextPath() %>/CustomerInfo_servlet?page=<%=pageSize %>"  onFocus="this.blur()">尾页</a></td>
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
				<h2><span id="dataBoxTitle"></span>项目部门</h2>
				<span class="data_close" id="dataClose">x</span>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  	<tr>
			    	<td align="left" valign="top">
				    	<form method="post" action="">
					    	<table width="90%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
	
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">客户名称：</td>
									<td align="left" valign="middle">
										<input type="text" name="name" value="">
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">id:</td>
									<td align="left" valign="middle">
										<input type="text" name="id" value="" updateNone addNone stateNone deleteNone dataNull>
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">客户编号：</td>
									<td align="left" valign="middle">
										<input type="text" name="no" value="" updateDisabled >
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">经营地址：</td>
									<td align="left" valign="middle">
										<input type="text" name="managementAddress" value="" >
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">税号：</td>
									<td align="left" valign="middle">
										<input type="text" name="taxNumber" value="" >
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">开户银行：</td>
									<td align="left" valign="middle">
										<input type="text" name="bank" value="" >
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">银行账号：</td>
									<td align="left" valign="middle">
										<input type="text" name="account" value="" >
									</td>
								</tr>
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">电话：</td>
									<td align="left" valign="middle">
										<input type="text" name="phone" value="" >
									</td>
								</tr>
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">部门编号：</td>
									<td align="left" valign="middle">
										<input type="text" name="departmentid" value="" >
									</td>
								</tr>
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">部门名称：</td>
									<td align="left" valign="middle">
										<input type="text" name="departmentname" value="" >
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
		
		<script type="text/javascript" src="js/commitData.js" ></script>
	</div>
	
	</body>
	</html>