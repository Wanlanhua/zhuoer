<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>绑定设备配件</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js"></script>
</head>
<body>
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：员工详情</td>
  </tr>
  <tr>
    <td align="left" valign="top" >
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <form method="post" action="">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr>
    	<td align="left" valign="top">
	    	<form method="post" action="" enctype="multipart/form-data">
		    	<table id="table" width="90%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
		      
					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="no">员工编号：</td>
						<td align="left" valign="middle">
						</td>
					</tr>
					
					<tr onMouseOut="this.style.backgroundColor='#ffffff'" style="display:none;" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="id">id：</td>
						<td align="left" valign="middle">
						</td>
					</tr>
					
					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="name">姓名：</td>
						<td align="left" valign="middle">
						</td>
					</tr>

					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="sex">性别：</td>
						<td align="left" valign="middle">
						</td>
					</tr>

					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="birthday">生日：</td>
						<td align="left" valign="middle">
						</td>
					</tr>

					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray sfzh" name="cardID">身份证号：</td>
						<td align="left" valign="middle">
						</td>
					</tr>

					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="leavedate">离职时间：</td>
						<td align="left" valign="middle">
						</td>
					</tr>

					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="contacts">联系人信息：</td>
						<td align="left" valign="middle">
						</td>
					</tr>

					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray dh" name="phone">电话：</td>
						<td align="left" valign="middle">
						</td>
					</tr>

					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="state">员工在职状态：</td>
						<td align="left" valign="middle">
						</td>
					</tr>	

					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="schrecord">学历：</td>
						<td align="left" valign="middle">
						</td>
					</tr>


					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="record">履历：</td>
						<td align="left" valign="middle" >
						</td>
					</tr>

					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="certificate">资格证书：</td>
						<td align="left" valign="middle">
						</td>
					</tr>
					
					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="department">所属部门：</td>
						<td align="left" valign="middle">
						</td>
					</tr>

					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="area">所属区域：</td>
						<td align="left" valign="middle">
							
						</td>
					</tr>
					
					<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
						<td align="right" valign="middle" class=" bggray" name="path">照片：</td>
						<td align="left" valign="middle"  style="padding: 10px 0;">
						</td>
					</tr>
    </table>
    </form>
    </td>
    </tr>
</table>
<script>
	var href=window.location.href;
	var begin = href.indexOf("?");
	href = href.substring(begin+1,href.length);
	$.ajax({
       dataType:"json",    //数据类型为json格式
       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
       type:"GET",  
       url:"GetCustomerById",
       async: false,
       data:{"id":href},
       success:function(data){ 
    	   var trdom = $("#table tr");
    	   trdom.each(function() {
    		   var attrname = $(this).find("td:first-child").attr("name");
    		   if(attrname!="path") {
    			   $(this).find("td:last-child").html(data[attrname])
    		   }
    		   else {
    			   $(this).find("td:last-child").html("<img style='width:90px;height:160px;' src="+data[attrname]+"></img>")
    		   }
    	   })
       }  
   }); 
	$(function() {
		$("#dataCommit").on("click",function(){
			window.location.href="GetEmployeeInfo"
		})
	})
</script>
</body>
</html>