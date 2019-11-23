<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>绑定设备配件</title>
<link rel="stylesheet" href="css/jquery.css">
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript">
$(function(){
	hintDeviceNo();
});
/* 自动提示设备号监听事件 */
function hintDeviceNo(content){ 
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
   selectDevicePart(content);
 }  
/* 自动填充设备名称监听事件 */
function hintDeviceName(content){ 
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
function autoInserDeviceFitOption(){ 
	$("#mainName").html("");
    $.ajax({
       dataType:"json",    //数据类型为json格式
       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
       type:"GET",  
       url:"DeviceFittingSearchServlet",
       data:{
    	   type:"selectDeviceFittingName",
    	   deviceFitName:$("#deviceFitSearch").val(),
       },
       async: false,
       statusCode: {404: function() {  
            alert('page not found'); }  
         },      
       success:function(data,textStatus){ 
    	   var mainOption="";
    	   var i=0;
    		   $.each(data,function(key,value){
    			   i++;
                   mainOption += "<input type='checkbox' name='deviceFitting' value="+key+" id='"+key+"'/>&nbsp;&nbsp;<label for='"+key+"'>"+value+" "+key+"&nbsp;&nbsp;<label/>&nbsp;&nbsp;&nbsp;&nbsp;";
                   if (i%3==0) {
                	   mainOption+="<br/>";
			       }
               });  
    	   $("#mainName").html(mainOption);
       }  
   }); 
 }  
function insertDandF(){ 
	if ($("#deviceNo").val()=="") {
		alert("请输入设备编号！");
		return false;
	}
 	if ($("#devicePart").val()=="") {
		alert("请选择部位名称！");
		return false;
	}
 	if ($("#deviceFitting").val()=="") {
		alert("请选择配件名称！");
		return false;
	}
 	var id_array=new Array();  
 	$('input[name="deviceFitting"]:checked').each(function(){  
 	    id_array.push($(this).val());//向数组中添加元素  
 	});  
     $.ajax({
       dataType:"json",    //数据类型为json格式
       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
       type:"GET",  
       url:"Z_DandFInfoInsertServlet",
       data:{
    	   deviceNo1:$("#deviceNo").val(),
    	   deviceNo:$("#devicePart").val(),
    	   no:id_array,
    	   mark:$("#mark").val()
       },
       traditional: true,
       async: false,
       statusCode: {404: function() {  
            alert('page not found'); }  
         },      
       success:function(data,textStatus){ 
    	   alert(data.message);
       }  
   });
 }  
function selectDevicePart(content) {
	$("#devicePart").find("option").remove();
	$.ajax({
	       dataType:"json",    //数据类型为json格式
	       contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	       type:"GET",  
	       url:"DevicePartSearchServlet",
	       data:{
	    	   type:"selectDevicePart",
	    	   deviceNo:content,
	       },
	       async: false,
	       statusCode: {404: function() {  
	            alert('page not found'); }  
	         },      
	       success:function(data,textStatus){ 
	    	   var devicePart = "";
	    	   for (var i = 0; i < data.length; i++) {
	    		   devicePart+="<option value="+data[i]+" >&nbsp;&nbsp;"+data[i]+"</option>";
			   }
	    	   $("#devicePart").html(devicePart)
	       }  
	   }); 
}
</script>
</head>
<body>
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：设备配件绑定管理&nbsp;&nbsp;>&nbsp;&nbsp;绑定设备配件</td>
  </tr>
  <tr>
    <td align="left" valign="top" >
   <h2 class="add blue">新增绑定配件</h2>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <form method="post" action="">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备编号：</td>
        <td align="left" valign="middle" class="dataNotNull">
        <input type="text" id="deviceNo" style="width: 288px;height: 34px;" onkeyup="hintDeviceNo(this.value)" onchange="selectDevicePart(this.value)">
        </td>
      </tr>
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备名称：</td>
        <td align="left" valign="middle" >
        <input type="text"id="deviceName" disabled="disabled" style="width: 288px;height: 34px;">
        </td>
        </tr>
       <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">设备部位：</td>
        <td align="left" valign="middle" >
        <select id="devicePart">
        </select>
        </td>
       </tr>
       <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">配件搜索：</td>
        <td align="left" valign="middle" >
        <input type="text" id="deviceFitSearch" style="width: 288px;height: 34px;">
        <input type="button" value="搜索" onclick="autoInserDeviceFitOption()">
        </td>
       </tr>
       <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="right" valign="middle" class=" bggray">配件名称：</td>
        <td align="left" valign="middle">
        <div id="mainName" style="padding: 10px;float: left;">
        </div>
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
        <input name="" type="button" value="提交" onclick="insertDandF()">
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
			selectDevicePart(content);
		})
	})
</script>
</body>
</html>