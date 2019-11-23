<%@page import="java.util.Map"%>
<%@page import="com.zhuoer.aJsonAPI.No"%>
<%@page import="com.zhuoer.customer_management.Bean.CustomerInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	Map<String,String> operate = (Map<String,String>)session.getAttribute("operate");
    	//{input=1, modify=1, auditing=1, auditing2=1, del=1, find=1}
    	// 添加		修改		  一级审核	  二级审核	     删除	    查询
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>设备部位信息</title>
	<link href="css/css.css" type="text/css" rel="stylesheet" />
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="css/jquery.css">
	<script>
	var thisPage = 1;
	var endPage = 1;
	$(function(){
		hintMaintenance();
	});
	$(function() {
		/* 首页监听事件 */
		$("body").delegate("#first","click",function(){
			thisPage=1;
			selectDevicePart();
			return false;
		});
		/* 下一页监听事件 */
		$("body").delegate("#next","click",function(){
			if (thisPage<endPage) {
				thisPage++;
				selectDevicePart();
			}
			return false;
		});
		/* 上一页监听事件 */
		$("body").delegate("#previous","click",function(){
			if (thisPage>1) {
				thisPage--;
				selectDevicePart();
			}
			return false;
		});
		/* 尾页监听事件 */
		$("body").delegate("#endP","click",function(){
			thisPage=endPage;
			selectDevicePart();
			return false;
		});
		/* 删除监听事件 */
		$("body").delegate(".delete","click",function() {
			 /*  e.preventDefault();*/
			 var obj = this.href;
			 var index=obj.lastIndexOf("=");
			 obj=obj.substring(index+1,obj.length);
			 var flag = confirm("确定删除?");
			 if (flag) {
				 $.ajax({
						dataType:"json",
						contentType:"application/x-www-form-urlencoded; charset=utf-8",
						type:"GET", 
						url:"Z_DevicePartInfoDelete",
						data:{
							id:obj
						},
						async: false,
						statusCode: {
							404: function() {  
				            	alert('page not found'); 
				            }  
				        },     
				        success:function(data,textStatus){
				        	selectDevicePart();
				        	alert(data.message);
				        	}
				        }); 
				}
			 return false;
		});
	});
	function selectDevicePart() {
		var tableHead = "<tr class=\"bggray\">\r\n" + 
		"						<th align=\"center\" valign=\"middle\">设备部位名称</th>" + 
		"						<th align=\"center\" valign=\"middle\">备注</th>" + 
		"						<th align=\"center\" valign=\"midd le\">操作</th>" + 
		"					</tr>";
		$.ajax({
		       dataType:"json",    //数据类型为json格式
		       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		       type:"GET",  
		       url:"Z_DevicePartInfoSelect", 
		       data:{
		    	    page:thisPage,
		    	    customerNo:$("#department").val(),
		    	    name:$("#devicePart").val()
		       },
		       statusCode: {404: function() {  
		            alert('page not found'); }  
		         },      
		       success:function(data,textStatus){ 
		    	    thisPage=data.pageNumber;
		        	endPage = data.totalPage;
		        	var table=tableHead;
		        	var paging = "";
		        	for (var i = 0; i < data.data.length; i++) {
						if (i%2==0) {
		        			table += "<tr onMouseOut=\"this.style.backgroundColor='#ffffff'\" onMouseOver=\"this.style.backgroundColor='#edf5ff'\">";
						}else{
							table += "<tr class=\"bggray\" onMouseOut=\"this.style.backgroundColor='#f9f9f9'\" onMouseOver=\"this.style.backgroundColor='#edf5ff'\">";
						}
						table += "<td align=\"center\" valign=\"middle\">"+data.data[i].name+"</td>";
						table += "<td align=\"center\" valign=\"middle\">"+data.data[i].mark+"</td>";
						table += "<td align=\"center\" valign=\"middle\" class=\"borderbottom\">" + 
						<%
							if(operate.get("modify").equals("1")){
						%>
						"			<a href=\"updateDevicePart.jsp?id="+data.data[i].id+"\">编辑</a><span class=\"gray\">&nbsp;|&nbsp;</span>" + 
						<%}%>
						<%
							if(operate.get("del").equals("1")){
						%>
						"			<a href=\"Z_DeviceDelete?id="+data.data[i].id+"\" class=\"delete\">删除</a><span class=\"gray\"></span>" +
						<%}%>
						"		  </td>";
						table += "</tr>";
					}
		        	paging+="<td align=\"left\" valign=\"top\" class=\"fenye\">"+data.data.length+" 条数据 "+
		        	data.pageNumber+"/"+data.totalPage+" 页&nbsp;&nbsp;" + 
					"				<a href=\"#\" id=\"first\">首页</a>&nbsp;&nbsp;" + 
					"				<a href=\"#\" id=\"previous\">上一页</a>&nbsp;&nbsp;" + 
					"				<a href=\"#\" id=\"next\">下一页</a>&nbsp;&nbsp;" + 
					"				<a href=\"#\" id=\"endP\">尾页</a>" + 
					"			</td>";
		        	$("#main-tab").html(table);
		        	$("#paging").html(paging);
		       }  
	       }); 
	}
	function hintMaintenance(){
		thisPage = 1;
		$.ajax({
		       dataType:"json",    //数据类型为json格式
		       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		       type:"GET",  
		       url:"DevicePartSearchServlet", 
		       data:{
		       		department:$("#department").val(),
		       		devicePart:$("#devicePart").val()
		       },
		       statusCode: {404: function() {  
		            alert('page not found'); }  
		         },      
		       success:function(data,textStatus){ 
		    	   $("#devicePart").autocomplete({
			    	      source: data
			       });
		       }  
	       }); 
	}
	</script>
</head>
<body>
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
		<tr>
			<td width="99%" align="left" valign="top">您的位置：设备部位信息</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
					<tr>
						<td width="90%" align="left" valign="middle">
							<form method="post" action="" >
								<span>项目部：</span>
								<select id="department">
								<%
									String no = session.getAttribute("name").toString();
									List<CustomerInfo> list = No.getDepartmentIdAndName(no);
									for(int i = 0; i <list.size();i++){
										CustomerInfo ci = list.get(i);
								%>
									<option value="<%=ci.getDepartmentid()%>">&nbsp;&nbsp;<%=ci.getDepartmentname()%></option>	
								<%
									}
								%>
								</select>
								<span>部位名称：</span>
								<input type="text" class="text-word" id="devicePart" onkeyup="hintMaintenance()">
								<%
									if(operate.get("find").equals("1")){
								%>
								<input name="" type="button" value="查询" class="text-but" onclick="selectDevicePart()">
								<%} %>
							</form>
						</td>
						<td width="10%" align="center" valign="middle" style="text-align:right; width:150px;">
						<%
							if(operate.get("input").equals("1")){
						%>
							<a href="addDevicePart.jsp" class="add">新增部位</a>
						<%} %>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top">

				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
				</table>
			</td>
		</tr>
		<tr id="paging">
		</tr>
	</table>
</body>
</html>