<%@page import="java.util.List"%>
<%@page import="com.zhuoer.aJsonAPI.GetDepartment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<title>Document</title>
	<link href="css/css.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="css/jquery.css">
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<style type="text/css">
		#select {
			height: 24px;
		    line-height: 24px;
		    width: 180px;
		    margin: 8px 0 6px 0;
		    padding: 0 0px 0 10px;
		    float: left;
		    border: 1px solid #FFF;
		}
	</style>
</head>
<body>
	<body>
<table width="100% !important" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：日志信息</td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		 <tr>
   		 <td width="90%" align="left" valign="middle">
	         <form method="post" action="<%=request.getContextPath()%>/GetEmployeeInfo">
	         	<span> 部门编号：</span>
	         	<select name="department" class="text-word" id="select">
	         	<%
	         		if(request.getSession().getAttribute("name").equals("admin")) {
	         	%>
	         		<option value="">全部</option>
	         	<%
	         		}
	         	%>
	         	<%
	         		List<String> list = GetDepartment.getDepartment(request.getSession().getAttribute("name").toString());
	         		for(int i=0; i<list.size(); i++) {
	         	%>
	         		<option value="<%=list.get(i)%>"><%=list.get(i)%></option>
	         	<%
	         		}
	         	%>
	         	</select>
	         	<span >员工编号：</span>
	         	<input type="text" name="no" class="text-word" id="findByNo" />
	         	<span >开始时间：</span>
	         	<input type="text" name="starttime" id="starttime" readonly="readonly" class="text-word" />
	         	<span >结束时间：</span>
	         	<input type="text" name="endtime" id="endtime" readonly="readonly" class="text-word"/>
	         	<input  type="button" name="submit1" value="查询" class="text-but">
	         </form>
         	</td>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top"> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr class="bggray" style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;">
        <th align="center" valign="middle" >部门编号</th> 
        <th align="center" valign="middle" >员工编号</th>
        <th align="center" valign="middle" >员工姓名</th>
        <th align="center" valign="middle" >操作描述</th>
        <th align="center" valign="middle" >时间</th>
      </tr>
    </table>
    </td>
    </tr>
</table>
<script>
	var dfhtml = $("#main-tab").html();
	var nowData = new Date();
	var preDate = new Date(nowData.getTime() - 3*24*60*60*1000); //前三天
	var year = preDate.getFullYear();
	var month = preDate.getMonth()+1;
	var day = preDate.getDate();
	var dateString = year+"-"+month+"-"+day;
	var nyear = nowData.getFullYear();
	var nmonth = nowData.getMonth()+1;
	var nday = nowData.getDate();
	var ndateString = nyear+"-"+nmonth+"-"+nday;
    $(function() {
    	var basePath = "<%=request.getContextPath()%>";
		var defaultDom = $("#main-tab");
		var defaultHTML = defaultDom.html();
	    var availableTags = <%=GetDepartment.getEmployeeinfo(list) %>
	    $( "#findByNo" ).autocomplete({
	      source: availableTags
	    });
    	$("#starttime").val(dateString)
    	$("#endtime").val(ndateString)
    	 $( "#starttime" ).datepicker({//添加日期选择功能  
    		 changeYear: true,
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
    	 
           $( "#endtime" ).datepicker({//添加日期选择功能  
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
            onSelect: function(selectedDate,inst) {//选择日期后执行的操作
            	var sdate = this.value;
            	var currentDate = new Date();
            	var starttime = $("#starttime").val();
            	var stime = new Date(starttime);
            	var etime = new Date(selectedDate);
            	if(stime.getTime()<etime.getTime()) {
            		this.value = selectedDate;
            	}
            	else {
            		alert("结束时间不能早于开始时间")
            		this.value = inst.lastVal;
            	}
            },
            
          });
           $("input[type='button']").on("click",function() {
   	 		$.ajax({
   	 			type:"post",
   	 			url:"<%=request.getContextPath()%>/QSelectOpLogInfo",
   	 			timeout:3000,
   	 			data:{"department":$("select[name='department']").val(),"no":$("input[name='no']").val(),"starttime":$("input[name='starttime'").val()+" 00:00:00","endtime":$("input[name='endtime']").val()+" 23:59:59"},
   	 			dataType:"json",
   	 			success:function(res) {
	   	 			var html = dfhtml;
					for(var data in res){
						html+='<tr class="bggray mhover" style="background: rgb(255, 255, 255) none repeat scroll 0% 0%;">'+
					        '<th align="center"  valign="middle">'+res[data]["department"]+'</th>'+
					       	'<th align="center" valign="middle" >'+res[data]["no"]+'</th>'+
					       	'<th align="center" valign="middle" >'+res[data]["name"]+'</th>'+
					        '<th align="center" valign="middle" >'+res[data]["content"]+'</th>'+
					        '<th align="center" valign="middle" >'+res[data]["stamp"]+'</th>'+
					      '</tr>'	
					}
					$("#main-tab").html(html);
				 	$(".mhover").mouseout(function() {
						$(this).css("background","#ffffff");
					})
					$(".mhover").mouseover(function() {
						$(this).css("background","#edf5ff");
					})
   				}
   	 		});
   	 	}); 
      });
 </script>
</body>
</body>
</html>