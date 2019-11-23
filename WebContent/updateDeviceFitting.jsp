<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String deviceFitId = request.getParameter("id");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改设备配件</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$(function() {
		$.ajax({
			dataType : "json", //数据类型为json格式
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			type : "GET",
			url : "Z_DeviceFittingInfoSelectOnly",
			data : {
				id : $("#id").val()
			},
			statusCode : {
				404 : function() {
					alert('page not found');
				}
			},
			success : function(data, textStatus) {
				$("#no").val(data[0].no),
				$("#name").val(data[0].name),
				$("#type1").val(data[0].type1),
				$("#type2").val(data[0].type2),
				$("#type").val(data[0].type),
				$("#qty").val(data[0].qty),
				$("#manufactor").val(data[0].manufactor),
				$("#mark").val(data[0].mark)
			}
		});
	});
	function updateDevicePart() {
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
		       url:"Z_DeviceFittingInfoUpdate", 
		       data:{
		    	   	id:$("#id").val(),
		    	    no:$("#no").val(),
					name:$("#name").val(),
					type1:$("#type1").val(),
					type2:$("#type2").val(),
					type:$("#type").val(),
					qty:$("#qty").val(),
					manufactor:$("#manufactor").val(),
					mark:$("#mark").val()
		       },
		       statusCode: {
		    	   404: function() {  
		            alert('page not found'); 
		           }  
		       },      
		       success:function(data,textStatus){ 
		    	   alert(data.message);
		    	   window.location.href="deviceFittingMain.jsp";
		       }  
	       }); 
	}
</script>
</head>
<body>
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：设备配件信息&nbsp;&nbsp;>&nbsp;&nbsp;修改设备配件</td>
  </tr>
  <tr>
    <td align="left" valign="top" >
   <h2 class="add blue">修改设备配件</h2>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <form method="post" action="">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">配件编号：</td>
        <td align="left" valign="middle" >
        <input type="text" id="no" disabled="disabled">
        </td>
        </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">类型一：</td>
        <td align="left" valign="middle" >
        <input type="text" id="type1">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">类型二：</td>
        <td align="left" valign="middle" >
        <input type="text" id="type2">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">配件名称：</td>
        <td align="left" valign="middle" >
        <input type="text" id="name">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">配件型号：</td>
        <td align="left" valign="middle" >
        <input type="text" id="type">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">计量单位：</td>
        <td align="left" valign="middle" >
        <input type="text" id="qty">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">厂家：</td>
        <td align="left" valign="middle" >
        <input type="text" id="manufactor">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">备注：</td>
        <td align="left" valign="middle" >
        <input type="text" id="mark">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">&nbsp;</td>
        <td align="left" valign="middle" >
        <input name="" type="button" value="修改" onclick="updateDevicePart()">
        <input name="" type="button" value="返回" onclick="javascript:history.go(-1);"></td>
        </tr>
    </table>
    </form>
    </td>
    </tr>
</table>
<input type="hidden" id="id" value="<%=deviceFitId%>">
</body>
</html>