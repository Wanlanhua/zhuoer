<%@page import="com.zhuoer.aJsonAPI.No"%>
<%@page import="com.zhuoer.customer_management.Bean.CustomerInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增保养项</title>
<script src="js/jquery.js"></script>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
	function addDevicePart() {
		if ($("#department").val()=="") {
			alert("请选择项目部！");
			return false;
		}
		if ($("#devicePartName").val()=="") {
			alert("请输入设备部位！");
			return false;
		}
		$.ajax({
		       dataType:"json",    //数据类型为json格式
		       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		       type:"GET",  
		       url:"Z_DevicePartInfoInsert", 
		       data:{
		    	    customerNo:$("#department").val(),//项目部
					name:$("#devicePartName").val(),
			       	mark:$("#devicePartMark").val()
		       },
		       statusCode: {404: function() {  
		            alert('page not found'); }  
		       },      
		       success:function(data,textStatus){ 
		    	   alert(data.message);
		       }
	       }); 
	}
</script>
</head>
<body>
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：设备部位信息&nbsp;&nbsp;>&nbsp;&nbsp;添加设备部位</td>
  </tr>
  <tr>
    <td align="left" valign="top" >
   <h2 class="add blue">新增设备部位</h2>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <form method="post" action="">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray" >项目部：</td>
        <td align="left" valign="middle" class="dataNotNull">
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
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备部位名称：</td>
        <td align="left" valign="middle" class="dataNotNull">
        <input type="text" id="devicePartName" style="width: 288px;height: 34px;">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">备注：</td>
        <td align="left" valign="middle" >
        <input type="text" id="devicePartMark" style="width: 288px;height: 34px;">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">&nbsp;</td>
        <td align="left" valign="middle" >
        <input name="" type="button" value="提交" onclick="addDevicePart()">
        <input name="" type="button" value="返回" onclick="javascript:history.go(-1);"></td>
        </tr>
    </table>
    </form>
    </td>
    </tr>
</table>
</body>
</html>