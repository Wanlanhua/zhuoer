<%@page import="java.util.Map"%>
<%@page import="com.zhuoer.customer_management.Bean.CustomerInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.zhuoer.aJsonAPI.No"%>
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
	<link href="css/css.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript"> 
	var thisPage = 1;
	var endPage = 1;
	$(function(){
		hiht();
		$("#ui-id-1").delegate(".ui-menu-item-wrapper","click",function(){
			var content = $(this).text();
			selectDeviceName(content);
		});
	});
	function selectDeviceName(content) {
		$.ajax({
		       dataType:"json",    //数据类型为json格式
		       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		       type:"GET",  
		       url:"DeviceSearchSevlet",
		       async: false,
		       data:{
		    	   	type:"selectDeviceName",
		       		deviceNo:content
		       },
		       statusCode: {404: function() {  
		            alert('page not found'); }  
		         },      
		       success:function(data,textStatus){ 
		    	   $("#DeviceName").text(data[0]);
		       }  
		   }); 
	}
	function hiht() {
		clickArea();
		$("#deviceNo").keyup();
	}
	$(function() {
		/* 首页监听事件 */
		$("body").delegate("#first","click",function(){
			thisPage=1;
			SearchDevice();
			return false;
		});
		/* 下一页监听事件 */
		$("body").delegate("#next","click",function(){
			if (thisPage<endPage) {
				thisPage++;
				SearchDevice();
			}
			return false;
		});
		/* 上一页监听事件 */
		$("body").delegate("#previous","click",function(){
			if (thisPage>1) {
				thisPage--;
				SearchDevice();
			}
			return false;
		});
		/* 尾页监听事件 */
		$("body").delegate("#endP","click",function(){
			thisPage=endPage;
			SearchDevice();
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
						url:"Z_DeviceDelete",
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
				        	$("#searchDevice").click();
				        	alert(data.message);
				        	}
				        }); 
				}
			 return false;
		});
	});
	/* 查询监听事件 */
	function SearchDevice() {
		var tableHead = "<tr class=\"bggray\">\r\n" + 
		"						<th align=\"center\" valign=\"middle\">设备编号</th>" + 
		"						<th align=\"center\" valign=\"middle\">设备名称</th>" + 
		"						<th align=\"center\" valign=\"middle\">设备地点</th>" + 
		"						<th align=\"center\" valign=\"middle\">设备区域</th>" + 
		"						<th align=\"center\" valign=\"middle\">设备生产日期</th>" + 
		"						<th align=\"center\" valign=\"middle\">备注</th>" + 
		"						<th align=\"center\" valign=\"middle\">操作</th>" + 
		"					</tr>";
		$.ajax({
			dataType:"json",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			type:"GET", 
			url:"Z_DeviceSelect",
			async: false,
			data:{
				page:thisPage,
				customerNo:$("#department").val(),
	       		area:$("#area").val(),
	       		no:$("#deviceNo").val()
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
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].address+"</td>";
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].area+"</td>";
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].createDate+"</td>";
					table += "<td align=\"center\" valign=\"middle\">"+data.data[i].mark+"</td>";
					table += "<td align=\"center\" valign=\"middle\" class=\"borderbottom\">" + 
					<%
						if(operate.get("modify").equals("1")){
					%>
					"	<a href=\"updateDevice.jsp?id="+data.data[i].id+"&customerNo="+data.data[i].customerNo+"\">编辑</a><span class=\"gray\">&nbsp;|&nbsp;</span>"+
					<%}%>  
					<%
						if(operate.get("del").equals("1")){
					%>
					"			<a href=\"Z_DeviceDelete?id="+data.data[i].id+"\" class=\"delete\">删除</a><span class=\"gray\">&nbsp;|&nbsp;</span>" + 
					<%}%> 
					"			<a href=\"QR/"+data.data[i].no+".jpg\" download=\"\">下载二维码</a>" + 
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
	/* 自动提示设备号监听事件 */
    function hintDeviceNo(){ 
		$("#DeviceName").text("");
    	thisPage = 1;
       $.ajax({
	       dataType:"json",    //数据类型为json格式
	       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	       type:"GET",  
	       url:"DeviceSearchSevlet",
	       async: false,
	       data:{
	    	   	type:"selectDeviceNo",
	       		department:$("#department").val(),
	       		area:$("#area").val(),
	       		deviceNo:$("#deviceNo").val()
	       },
	       statusCode: {404: function() {  
	            alert('page not found'); }  
	         },      
	       success:function(data,textStatus){ 
	    	   $("#deviceNo").autocomplete({
		    	      source: data
		       });
	       }  
       }); 
     }  
    /* 动态拉取区域监听事件 */
	function clickArea() {
		$("#area").find("option").remove();
		$("#deviceNo").val("");
		$.ajax({
			dataType : "json", //数据类型为json格式
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "GET",
			url : "DeviceSearchSevlet",
			async: false,
			data : {
				type : "selectArea",
				department : $("#department").val(),
			},
			statusCode : {
				404 : function() {
					alert('page not found');
				}
			},
			success : function(data, textStatus) {
				var len = data.length;
				if(len > 0) {
					$("#area").append("<option value='全部'>全部</option>");
					for (var i = 0; i < data[0].length; i++) {
						 $("#area").append("<option value='"+data[0][i]+"'>"+data[1][i]+"</option>");
					}
				}
			}
		});
	}
	</script>
</head>
<body>
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
		<tr>
			<td width="99%" align="left" valign="top">您的位置：设备信息管理</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
					<tr>
						<td width="90%" align="left" valign="middle">
							<form method="post" action="" >
								<span>项目部：</span>
								<select id="department" onchange="clickArea()">
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
								<span>区域：</span>
								<select id="area">
								</select>
								<span>设备号：</span>
								<input type="text" id="deviceNo" class="text-word" onkeyup="hintDeviceNo()" onchange="selectDeviceName(this.value)"/>
								<span id="DeviceName"></span>
								<%
									if(operate.get("find").equals("1")){
								%>
								<input id="searchDevice" type="button" value="查询" class="text-but" onclick="SearchDevice()">
								<%} %>
							</form>
						</td>
						<td width="10%" align="center" valign="middle" style="text-align:right; width:150px;">
						<%
							if(operate.get("input").equals("1")){
						%>
							<a href="addDevice.jsp" class="add">新增设备</a>
						<%
							}
						%>
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
	<script type="text/javascript">
</script>
</body>
</html>