<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>绑定润滑项目</title>
<link rel="stylesheet" href="css/jquery.css">
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
$(function(){
	hintDeviceNo();
	autoInserMaintOption();
});
/* 自动提示设备号监听事件 */
function hintDeviceNo(){ 
	thisPage = 1;
   $.ajax({
       dataType:"json",    //数据类型为json格式
       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
       type:"GET",  
       url:"DeviceSearchSevlet",
       async: false,
       data:{
    	   	type:"selectDeviceNoOnly",
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
/* 自动填充设备名称监听事件 */
function hintDeviceName(content){ 
	thisPage = 1;
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
    	   $("#deviceName").val(data[0]);
       }  
   }); 
 }  
/* 自动填充保养项名称监听事件 */
function autoInserMaintOption(){ 
	thisPage = 1;
    $.ajax({
       dataType:"json",    //数据类型为json格式
       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
       type:"GET",  
       url:"LubricationContentSerchServlet",
       data:{
    	   content:$("#deviceMSearch").val()
       },
       async: false,
       statusCode: {404: function() {  
            alert('page not found'); }  
         },      
       success:function(data,textStatus){ 
    	   var mainOption="";
    	   for (var i = 0; i < data.length; i++) {
    	  		mainOption += "<option value="+data[i]+" >&nbsp;&nbsp;"+data[i]+"</option>"
			}
    	   $("#mainName").html(mainOption);
    	   getMark1();
       }  
   }); 
 }
/* 根据润滑项名称获得标准与工时的监听事件 */
function getMark1(){ 
    $.ajax({
       dataType:"json",    //数据类型为json格式
       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
       type:"GET",  
       url:"GetMark",
       data:{
    	   tableName:"lubricationInfo",
    	   content:$("#mainName").val()
       },
       async: false,
       statusCode: {404: function() {  
            alert('page not found'); }  
         },      
       success:function(data,textStatus){ 
    	   var mainOption="";
    	   
    	   mainOption = data[0];
			
    	   $("#mark1").val(mainOption);
       }  
   }); 
 } 
 function insertDandM() {
	 if ($("#deviceNo").val()=="") {
			alert("请输入设备编号！");
			return false;
		}
	 if ($("#mainName").val()=="") {
			alert("请选择润滑项目！");
			return false;
		}
	 $.ajax({
	       dataType:"json",    //数据类型为json格式
	       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	       type:"GET",  
	       url:"Z_IandMInfoInsertServlet",
	       data:{
				   deviceNo:$("#deviceNo").val(),
	    		   name:$("#mainName").val(),
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
    <td width="99%" align="left" valign="top">您的位置：设备润滑项目绑定管理&nbsp;&nbsp;>&nbsp;&nbsp;绑定润滑项目</td>
  </tr>
  <tr>
    <td align="left" valign="top" >
   <h2 class="add blue">新增绑定润滑项目</h2>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <form method="post" action="">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备编号：</td>
        <td align="left" valign="middle" class="dataNotNull">
        <input type="text" id="deviceNo" style="width: 288px;height: 34px;" onkeyup="hintDeviceNo()">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备名称：</td>
        <td align="left" valign="middle" >
        <input type="text"id="deviceName" disabled="disabled" style="width: 288px;height: 34px;">
        </td>
        </tr>
        <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">润滑搜索：</td>
        <td align="left" valign="middle" >
        <input type="text" id="deviceMSearch" style="width: 288px;height: 34px;">
        <input type="button" value="搜索" onclick="autoInserMaintOption()">
        </td>
       </tr>
       <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">润滑项目：</td>
        <td align="left" valign="middle" class="dataNotNull">
        <select id="mainName"  onchange="getMark1()">
        </select>
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">标准与工时：</td>
        <td align="left" valign="middle" >
        <input type="text" id="mark1" style="width: 1288px;height: 34px;"  readonly="readonly">
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
        <input type="button" value="提交" onclick="insertDandM()">
        <input name="" type="button" value="返回" onclick="javascript:history.go(-1);"></td>
        </tr>
    </table>
    </form>
    </td>
    </tr>
</table>
<script type="text/javascript">
	$(function() {
		$("#ui-id-1").delegate(".ui-menu-item-wrapper","click",function(){
			var content = $(this).text();
			hintDeviceName(content);
		})
	})
</script>
</body>
</html>