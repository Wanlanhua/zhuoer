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
<!-- <script src="//code.jquery.com/jquery-1.9.1.js"></script> -->
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link rel="stylesheet" href="css/jquery.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>设备基本信息管理</title>
	<link href="css/css.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript">  
	var thisPage = 1;
	var endPage = 1;
	$(function(){
		hint();
	});
	function hint() {
		hintDeviceNo();
		selectType1();
	}
	$(function() {
		/* 首页监听事件 */
		$("body").delegate("#first","click",function(){
			thisPage=1;
			selectDeviceFitting();
			return false;
		});
		/* 下一页监听事件 */
		$("body").delegate("#next","click",function(){
			if (thisPage<endPage) {
				thisPage++;
				selectDeviceFitting();
			}
			return false;
		});
		/* 上一页监听事件 */
		$("body").delegate("#previous","click",function(){
			if (thisPage>1) {
				thisPage--;
				selectDeviceFitting();
			}
			return false;
		});
		/* 尾页监听事件 */
		$("body").delegate("#endP","click",function(){
			thisPage=endPage;
			selectDeviceFitting();
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
						url:"Z_DeviceFittingInfoDelete",
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
				        	selectDeviceFitting();
				        	alert(data.message);
				        	}
				        }); 
				}
			 return false;
		});
	});
	/* 设备号提示下拉 */
    function hintDeviceNo(){ 
		thisPage = 1;
       $.ajax({
	       dataType:"json",    //数据类型为json格式
	       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	       type:"GET",  
	       url:"DeviceFittingSearchServlet", 
	       async: false,
	       data:{
	    	   	type:"selcetDeviceFitting",
	       		department:$("#department").val(),
	       		type1:$("#type1").val(),
	       		type2:$("#type2").val(),
	       		DeviceFittingName:$("#DeviceFittingName").val()
	       },
	       statusCode: {404: function() {  
	            alert('page not found'); }
	         },      
	       success:function(data,textStatus){ 
	    	   $("#DeviceFittingName").autocomplete({
		    	      source: data
		       });
	       }  
       }); 
     }  
    /* 动态拉取type1*/
	function selectType1() {
		$("#type1").find("option").remove();
		$("#type2").find("option").remove();
		$("#DeviceFittingName").val("");
		$.ajax({
			dataType : "json", //数据类型为json格式
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "GET",
			url : "DeviceFittingSearchServlet",
			async: false,
			data : {
				type : "selectType1",
				department : $("#department").val(),
			},
			statusCode : {
				404 : function() {
					alert('page not found');
				}
			},
			success : function(data, textStatus) {
				for (var i = 0; i < data.length; i++) {
					 $("#type1").append("<option value='"+data[i]+"'>"+data[i]+"</option>");
				}
				selectType2();
			}
		});
	}
    /* 动态拉取type2 */
	function selectType2() {
		$("#type2").find("option").remove();
		$.ajax({
			dataType : "json", //数据类型为json格式
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "GET",
			url : "DeviceFittingSearchServlet",
			async: false,
			data : {
				type : "selectType2",
				department : $("#department").val(),
				type1:$("#type1").val()
			},
			statusCode : {
				404 : function() {
					alert('page not found');
				}
			},
			success : function(data, textStatus) {
				for (var i = 0; i < data.length; i++) {
					 $("#type2").append("<option value='"+data[i]+"'>"+data[i]+"</option>");
				}
			}
		});
	}
    /* 查询数据 */
    function selectDeviceFitting() {
		var tableHead = "<tr class=\"bggray\">\r\n" + 
		"					<th align=\"center\" valign=\"middle\">配件编号</th>" + 
		"					<th align=\"center\" valign=\"middle\">配件名称</th>" + 
		"					<th align=\"center\" valign=\"middle\">类型一</th>" + 
		"					<th align=\"center\" valign=\"middle\">类型二</th>" + 
		"					<th align=\"center\" valign=\"middle\">配件型号</th>" + 
		"					<th align=\"center\" valign=\"middle\">计量单位</th>" + 
		"					<th align=\"center\" valign=\"middle\">厂家</th>" + 
		"					<th align=\"center\" valign=\"middle\">备注</th>" + 
		"					<th align=\"center\" valign=\"middle\">操作</th>" + 
		"				</tr>";
		$.ajax({
			dataType:"json",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			type:"GET", 
			url:"Z_DeviceFittingInfoSelect",
			async: false,
			data:{
				page:thisPage,
				customerNo:$("#department").val(),
	       		type1:$("#type1").val(),
	       		type2:$("#type2").val(),
	       		name:$("#DeviceFittingName").val()
			},
			statusCode: {
				404: function() {  
	            	alert('page not found'); 
	            }  
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
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].no+"</td>";
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].name+"</td>";
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].type1+"</td>";
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].type2+"</td>";
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].type+"</td>";
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].qty+"</td>";
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].manufactor+"</td>";
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].mark+"</td>";
					table += "<td align=\"center\" valign=\"middle\" class=\"borderbottom\">" + 
					<%
						if(operate.get("modify").equals("1")){
					%>
					"			<a href=\"updateDeviceFitting.jsp?id="+data.data[i].id+"\">编辑</a><span class=\"gray\">&nbsp;|&nbsp;</span>" +
					<%}%>
					<%
						if(operate.get("del").equals("1")){
					%>
					"			<a href=\"Z_DeviceDelete?id="+data.data[i].id+"\" class=\"delete\">删除</a><span class=\"gray\">&nbsp;&nbsp;</span>" + 
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
	</script>
</head>
<body>
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
		<tr>
			<td width="99%" align="left" valign="top">您的位置：设备配件信息管理</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
					<tr>
						<td width="90%" align="left" valign="middle">
							<form method="post" action="" >
								<span>项目部：</span>
								<select id="department" onchange="selectType1()">
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
								<span>类型一：</span>
									<select id="type1" onchange="selectType2()">
									</select>
								<span>类型二：</span>
									<select id="type2">
									</select>
								<span>配件名称：</span>
								<input type="text" id="DeviceFittingName" class="text-word" onkeyup="hintDeviceNo()"/>
								<%
									if(operate.get("find").equals("1")){
								%>
								<input id="search" type="button" value="查询" class="text-but" onclick="selectDeviceFitting()">
								<%}%>
							</form>
						</td>
						<td width="10%" align="center" valign="middle" style="text-align:right; width:150px;">
						<%
							if(operate.get("input").equals("1")){
						%>
							<a href="addDeviceFitting.jsp" class="add">新增设备</a>
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