<%@page import="com.zhuoer.aJsonAPI.No"%>
<%@page import="com.zhuoer.customer_management.Bean.CustomerInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加设备</title>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link rel="stylesheet" href="css/jquery.css">
<link href="css/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	$(function(){
		$("#area").find("option").remove();
		$.ajax({
			dataType : "json", //数据类型为json格式
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "GET",
			url : "DeviceSearchSevlet",
			data : {
				type : "selectArea",
				department : $("#department").val(),//测试数据,真实中是用session获取
			},
			statusCode : {
				404 : function() {
					alert('page not found');
				}
			},
			success : function(data, textStatus) {
				var len = data.length;
				if(len > 0) {
					for (var i = 0; i < data[0].length; i++) {
						 $("#area").append("<option value='"+data[0][i]+"'>"+data[1][i]+"</option>");
					}
				}
			}
		});
	});
	function insertDevice() {
		if ($("#department").val()=="") {
			alert("请选择项目部！");
			return false;
		}
		if ($("#no").val()=="") {
			alert("请输入设备号！");
			return false;
		}
		if ($("#name").val()=="") {
			alert("请输入设备名称！");
			return false;
		}
		if ($("#area").val()=="") {
			alert("请选择区域！");
			return false;
		}
		if ($("#address").val()=="") {
			alert("请输入设备地点！");
			return false;
		}
		if ($("#createDate").val()=="") {
			alert("请输入设备生产日期！");
			return false;
		}
		$.ajax({
			dataType : "json", //数据类型为json格式
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "GET",
			url : "Z_DeviceInsert",
			data : {
				customerNo: $("#department").val(),
				no:$("#no").val(),
				name:$("#name").val(),
				address:$("#address").val(),
				area:$("#area").val(),
				createDate:$("#createDate").val(),
				mark:$("#mark").val(),
			},
			statusCode : {
				404 : function() {
					alert('page not found');
				}
			},
			success : function(data, textStatus) {
				alert(data.message);
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
    <td width="99%" align="left" valign="top">您的位置：设备信息管理&nbsp;&nbsp;>&nbsp;&nbsp;添加设备</td>
  </tr>
  <tr>
    <td align="left" valign="top" >
   <h2 class="add blue">新增设备</h2>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <form method="post" action="Z_DeviceInsert">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray" >项目部：</td>
        <td align="left" valign="middle" class="dataNotNull">
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
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray" >设备编号：</td>
        <td align="left" valign="middle" class="dataNotNull">
        <input type="text" id="no" style="width: 288px;height: 34px;">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备名称：</td>
        <td align="left" valign="middle" class="dataNotNull">
        <input type="text" id="name" style="width: 288px;height: 34px;">
        </td>
        </tr>
       <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">区域名称：</td>
        <td align="left" valign="middle" class="dataNotNull">
        <select id="area">
        </select>
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备地点：</td>
        <td align="left" valign="middle" class="dataNotNull">
        <input type="text" id="address" style="width: 288px;height: 34px;">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备生产日期：</td>
        <td align="left" valign="middle" class="dataNotNull">
        <input type="text" id="createDate" style="width: 288px;height: 34px;" readonly="readonly">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">备注：</td>
        <td align="left" valign="middle" >
        <input type="text" id="mark" style="width: 288px;height: 34px;">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">&nbsp;</td>
        <td align="left" valign="middle" >
        <input name="" type="button" value="提交" onclick="insertDevice()">
        <input name="" type="button" value="返回" onclick="javascript:history.go(-1);"></td>
        </tr>
    </table>
    </form>
    </td>
    </tr>
</table>
</body>
<script type="text/javascript">
	$("#createDate").datepicker({//添加日期选择功能  
	    numberOfMonths:1,//显示几个月  
	    showButtonPanel:true,//是否显示按钮面板  
	    dateFormat: 'yy-mm-dd',//日期格式  
	    clearText:"清除",//清除日期的按钮名称  
	    closeText:"关闭",//关闭选择框的按钮名称  
	    yearSuffix: '年', //年的后缀  
	    showMonthAfterYear:true,//是否把月放在年的后面  
	    changeYear:true,
	    monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
	    dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
	    dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
	    dayNamesMin: ['日','一','二','三','四','五','六'],  
	    onSelect: function(selectedDate) {//选择日期后执行的操作
	    	var currentDate = new Date();
	    	this.value = selectedDate;
	    }  
	  });  
</script>
</html>