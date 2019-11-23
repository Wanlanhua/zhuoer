<%@page import="com.zhuoer.aJsonAPI.No"%>
<%@page import="com.zhuoer.customer_management.Bean.CustomerInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增设备配件</title>
<script src="js/jquery.js"></script>
<link href="css/css.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript">
	function addDevicePart() {
		if ($("#department").val()=="") {
			alert("请选择项目部门！");
			return false;
		}
		if ($("#no").val()=="") {
			alert("请输入配件编号！");
			return false;
		}
		if ($("#type1").val()=="") {
			alert("请输入类型一！");
			return false;
		}
		if ($("#type2").val()=="") {
			alert("请输入类型二！");
			return false;
		}
		if ($("#name").val()=="") {
			alert("请输入配件名称！");
			return false;
		}
		if ($("#type").val()=="") {
			alert("请输入配件型号！");
			return false;
		}
		if ($("#qty").val()=="") {
			alert("请输入计量单位！");
			return false;
		}
		if ($("#manufactor").val()=="") {
			alert("请输入厂家！");
			return false;
		}
		$.ajax({
		       dataType:"json",    //数据类型为json格式
		       contentType: "application/x-www-form-urlencoded; charset=utf-8",
		       type:"GET",  
		       url:"Z_DeviceFittingInfoInsert", 
		       data:{
		    	   customerNo:$("#department").val(),//项目部
		    	   no:$("#no").val(),
		    	   type1:$("#type1").val(),
		    	   type2:$("#type2").val(),
		    	   name:$("#name").val(),
		    	   type:$("#type").val(),
		    	   qty:$("#qty").val(),
		    	   manufactor:$("#manufactor").val(),
		    	   mark:$("#mark").val()
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
    <td width="99%" align="left" valign="top">您的位置：设备配件信息&nbsp;&nbsp;>&nbsp;&nbsp;添加设备配件</td>
  </tr>
  <tr>
    <td align="left" valign="top" >
   <h2 class="add blue">新增设备配件</h2>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <form method="post" action="">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray" >项目部：</td>
        <td align="left" valign="middle"  class="dataNotNull">
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
        <td align="right" valign="middle" class=" bggray">配件编号：</td>
        <td align="left" valign="middle"  class="dataNotNull">
        <input type="text" id="no" style="width: 288px;height: 34px;">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">类型一：</td>
        <td align="left" valign="middle"  class="dataNotNull">
        	<!-- <input type="text" id="type1" style="width: 288px;height: 34px;"> -->
        	<select id="type1">
        		<option value="电气">电气</option>
        		<option value="机械">机械</option>
        	</select>
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">类型二：</td>
        <td align="left" valign="middle"  class="dataNotNull">
        <input type="text" id="type2" style="width: 288px;height: 34px;">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">配件名称：</td>
        <td align="left" valign="middle"  class="dataNotNull">
        <input type="text" id="name" style="width: 288px;height: 34px;">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">配件型号：</td>
        <td align="left" valign="middle"  class="dataNotNull">
        <input type="text" id="type" style="width: 288px;height: 34px;">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">计量单位：</td>
        <td align="left" valign="middle"  class="dataNotNull">
        <input type="text" id="qty" style="width: 288px;height: 34px;">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">厂家：</td>
        <td align="left" valign="middle"  class="dataNotNull">
        <input type="text" id="manufactor" style="width: 288px;height: 34px;">
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