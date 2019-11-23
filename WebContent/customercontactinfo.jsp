	<%@page import="java.util.Map"%>
<%@page import="com.zhuoer.aJsonAPI.No"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="java.util.List" %>
	<%@ page import="com.zhuoer.customer_management.Bean.*" %>
	<%
			
	
		int thisPage = 1;
		int pageSize = 1;
		int role = Integer.parseInt(session.getAttribute("role").toString());
		if(request.getAttribute("pageSizeCCI") != null )
			pageSize = Integer.parseInt(request.getAttribute("pageSizeCCI").toString());
		if(request.getAttribute("thisPageCCI")!=null)
			thisPage = Integer.parseInt(request.getAttribute("thisPageCCI").toString());

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
		var addActionTarget = "<%=request.getContextPath() %>/InsertCustomerContact";
		var updateActionTarget = "<%=request.getContextPath() %>/UpdateCustomerContact";
	</script>
	</head>
	<body>
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain" >
	  <tr>
	    <td width="99%" align="left" valign="top">您的位置：联系人信息</td>
	  </tr>
	  <tr>
	    <td align="left" valign="top">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
	  		<tr>
	  		<%
	  			String classNone = "none";
	  		%>
	   		 <td width="80%" align="left" valign="middle">
		         <form method="post" action="<%=request.getContextPath() %>/GetCustomerContact?page=1">
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
	  		  <a href="javascript:void(0);" target="mainFrame" onFocus="this.blur()" class="add" id="addDom">联系人信息</a></td>
	  		</tr>
		</table>
	    </td>
	  </tr>
	  <tr>
	    <td align="left" valign="top">
	    
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
	      <tr  class="bggray">
	        <th align="center" valign="middle" style="display: none;" >id</th>
	        <th align="center" valign="middle" >项目部编号</th>
	        <th align="center" valign="middle" >是否启用</th>
	        <th align="center" valign="middle" >设为主要联系人</th>
	        <th align="center" valign="middle" >联系人</th>
	        <th align="center" valign="middle">联系人称谓</th>
	        <th align="center" valign="middle">联系人职务</th>
	        <th align="center" valign="middle">手机</th>
	        <th align="center" valign="middle">电话</th>
	        <th align="center" valign="middle">传真</th>
	        <th align="center" valign="middle">email</th>
	        <th align="center" valign="middle">QQ</th>
	        <th align="center" valign="middle">微信</th>
	        <th align="center" valign="middle">MSN</th>
	        <th align="center" valign="middle">网络电话</th>
	        <th align="center" valign="middle">生日</th>
	        <th align="center" valign="middle">地址</th>
	        <th align="center" valign="middle" style="display: none;">备注</th>
	        <%
	        	if(map.get("modify").equals("1") || map.get("del").equals("1")) {
	        		classNone = "block";
	        	}
	        	else classNone = "none";
	        %>
	        <th align="center" valign="middle" style="display:<%=classNone%>">操作</th>
	      </tr>
	      
	      <%
	      		List<CustomerContactInfo> list = (List<CustomerContactInfo>)request.getSession().getAttribute("listCCI");
	      		if(list != null) {
	      			for(int i=0; i<list.size(); i++) {
	      %>
				      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	
				       
				        <th align="center" valign="middle" ><%=list.get(i).getNo() %></th>
				        <th align="center" valign="middle" style="display: none;"><%=list.get(i).getId() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getIsEnable() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getPrimaryContact() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getName() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getAppellation() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getPost() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getPhone() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getTelephone() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getFax() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getEmail() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getQQ() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getWeChat() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getMSN() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getSkype() %></th>
				        <th align="center" valign="middle" ><%=list.get(i).getBirthday().toString() %></th>
				        
				        <th align="center" valign="middle" ><%=list.get(i).getAddress() %></th>
				        
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
					        	<a href="<%=request.getContextPath() %>/DeleteCustomerContact?id=<%=list.get(i).getId() %>" style="display:<%=classNone%>;" target="mainFrame" onFocus="this.blur()" class="deleteData">删除</a>
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
		    		<a href="<%=request.getContextPath() %>/CustomerContactSelect_servlet?page=1" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
			    	<%
			    		int beforePage = thisPage - 1;
			    		if(thisPage>1) {
			    		
			    	%>
			    		<a href="<%=request.getContextPath() %>/CustomerContactSelect_servlet?page=<%=beforePage %>" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;
			    	<% 
			    		}
			    	%>
			    	<%
			    		int afterPage = thisPage + 1;
			    		if(thisPage<pageSize) {
			    	%>
			    		<a href="<%=request.getContextPath() %>/CustomerContactSelect_servlet?page=<%=afterPage %>" onFocus="this.blur()">下一页</a>&nbsp;&nbsp;
			    	<%
			    		}
			    	%>
			    	
			    	<%
			    		if(thisPage != pageSize && pageSize >1) {
			    	%>
			    		<a href="<%=request.getContextPath() %>/CustomerContactSelect_servlet?page=<%=pageSize %>" onFocus="this.blur()">尾页</a></td>
			    	<%
			    		}
			    	%>
			  </tr>
			  
		<%
	    	}
		%>
	</table>
	
	<div id="dataBox">
		
		<div style="text-align: center;width:690px;">
			<div class="data_head">
				<h2><span id="dataBoxTitle"></span>客户信息</h2>
				<span class="data_close" id="dataClose">x</span>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
			  	<tr>
			    	<td align="left" valign="top">
				    	<form method="post" action="">
					    	<table width="90%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
	
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">项目部编号：</td>
									<td align="left" valign="middle" class="dataNotNull">
										<select name="no" value=""  >
											<%
												List<CustomerInfo> clist = No.getCustomer();
												for(int i=0; i<clist.size(); i++) {
											%>
												<option value="<%=clist.get(i).getNo()%>"><%=clist.get(i).getName() %></option>
											<%
												}
											%>
										</select>
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">id:</td>
									<td align="left" valign="middle" >
										<input type="text" name="id" value="" updateNone addNone stateNone deleteNone dataNull>
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">是否启用：</td>
									<td align="left" valign="middle">
										<input type="text" name="isEnable" value=""  dataNull>
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">设为主要联系人：</td>
									<td align="left" valign="middle">
										<input type="text" name="primaryContact" value=""  dataNull>
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">联系人：</td>
									<td align="left" valign="middle">
										<input type="text" name="name" value=""  dataNull>
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">联系人称谓：</td>
									<td align="left" valign="middle">
										<input type="text" name="appellation" value=""  dataNull>
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">联系人职务：</td>
									<td align="left" valign="middle">
										<input type="text" name="post" value=""  dataNull>
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">手机：</td>
									<td align="left" valign="middle">
										<input type="text" name="phone" value=""  dataNull>
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">电话：</td>
									<td align="left" valign="middle">
										<input type="text" name="telephone" value=""  dataNull>
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">传真：</td>
									<td align="left" valign="middle">
										<input type="text" name="fax" value=""  dataNull>
									</td>
								</tr>
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">email：</td>
									<td align="left" valign="middle">
										<input type="text" name="email" value=""  dataNull>
									</td>
								</tr>
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">QQ：</td>
									<td align="left" valign="middle">
										<input type="text" name="QQ" value=""  dataNull>
									</td>
								</tr>
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">WeChat：</td>
									<td align="left" valign="middle">
										<input type="text" name="WeChat" value=""  dataNull>
									</td>
								</tr>
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">MSN：</td>
									<td align="left" valign="middle">
										<input type="text" name="MSN" value=""  dataNull>
									</td>
								</tr>
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">网络电话：</td>
									<td align="left" valign="middle">
										<input type="text" name="skype" value=""  dataNull>
									</td>
								</tr>
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">生日：</td>
									<td align="left" valign="middle">
										<input type="text" name="birthday" value="" id="dateTimeNo" >
										
										<script>
								    $(function() {
								        $( "#dateTimeNo" ).datepicker({//添加日期选择功能  
								        	changeYear: true,
								            numberOfMonths:1,//显示几个月  
								            showButtonPanel:true,//是否显示按钮面板  
								            dateFormat: 'yy-mm-dd',//日期格式  
								            clearText:"清除",//清除日期的按钮名称  
								            closeText:"关闭",//关闭选择框的按钮名称  
								            yearSuffix: '年', //年的后缀  
								            showMonthAfterYear:true,//是否把月放在年的后面  
								            monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
								            dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
								            dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
								            dayNamesMin: ['日','一','二','三','四','五','六'],  
								            onSelect: function(selectedDate) {//选择日期后执行的操作
									            this.value = selectedDate;
								            }  
								          }); 
								      });
								</script>
										
									</td>
								</tr>
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">地址：</td>
									<td align="left" valign="middle">
										<input type="text" name="address" value="" stateDisabled dataNull>
									</td>
								</tr>
							
								
								<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
									<td align="right" valign="middle" class=" bggray">备注：</td>
									<td align="left" valign="middle">
										<input type="text" name="mark" value="" updateNone addNone stateNone deleteNone dataNull>
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
		
		<script type="text/javascript" src="js/customercontact.js" ></script>
	</div>
	</body>
	</html>