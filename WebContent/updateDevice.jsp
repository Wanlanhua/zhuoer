<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String deviceId = request.getParameter("id");
    	String customerNo = request.getParameter("customerNo");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改设备信息</title>
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link rel="stylesheet" href="css/jquery.css">
<script type="text/javascript">
	/* 
		网页加载完成加载函数
	*/
	$(function() {
		selectOption();
		selectDeviceById();
	});
	function selectDeviceById() {
		$.ajax({
			dataType : "json", //数据类型为json格式
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "GET",
			url : "Z_DeviceSelectOnly",
			async: false,
			data : {
				id : <%=deviceId%>
			},
			statusCode : {
				404 : function() {
					alert('page not found');
				}
			},
			success : function(data, textStatus) {
				$("#no").val(data[0].no);
				$("#deviceName").val(data[0].name);
				$("#area").val(data[0].area);
				$("#address").val(data[0].address);
				$("#createDate").val(data[0].createDate);
				$("#mark").val(data[0].mark);
			}
		});
	}
	function selectOption(){
		$("#area").find("option").remove();
		$.ajax({
			dataType : "json", //数据类型为json格式
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "GET",
			url : "DeviceSearchSevlet",
			async: false,
			data : {
				type : "selectArea",
				department : "<%=customerNo%>",//测试数据,真实中是用session获取
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
	function updateDevice() {
		if ($("#deviceName").val()=="") {
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
			url : "Z_DeviceUpdate",
			data:{
				id:<%=deviceId%>,
				no:$("#no").val(),
				name:$("#deviceName").val(),
				area:$("#area").val(),
				address:$("#address").val(),
				createDate:$("#createDate").val(),
				mark:$("#mark").val()
			},
			statusCode : {
				404 : function() {
					alert('page not found');
				}
			},
			success : function(data, textStatus) {
				if(data.message){
					alert("修改成功!");
					window.location.href="deviceMain.jsp";
				}else{
					alert("修改失败!");
				}
			}
		});
	}
	</script>
<link href="css/css.css" type="text/css" rel="stylesheet" />
</head>
<body>
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：设备信息管理&nbsp;&nbsp;>&nbsp;&nbsp;修改设备</td>
  </tr>
  <tr>
    <td align="left" valign="top" >
   <h2 class="add blue">修改设备信息</h2>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <form method="post" action="">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备编号：</td>
        <td align="left" valign="middle" >
        <input type="text" id="no" disabled="disabled" style="width: 288px;height: 34px;">
        </td>
        </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备名称：</td>
        <td align="left" valign="middle"  class="dataNotNull">
        <input type="text"id="deviceName" style="width: 288px;height: 34px;">
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
        <td align="left" valign="middle"  class="dataNotNull">
        <input type="text" id="address" style="width: 288px;height: 34px;">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备生产日期：</td>
        <td align="left" valign="middle"  class="dataNotNull">
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
        <input name="" type="button" value="修改" onclick="updateDevice()">
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