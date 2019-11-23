<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowStr = format.format(now);
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(now);
		calendar.add(calendar.DATE,-3);//把日期往后增加一天.整数往后推,负数往前移动
		Date before=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		String beforeStr = format.format(before);
	%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<link rel="stylesheet" href="css/jquery.css">
<link href="css/css.css" type="text/css" rel="stylesheet" />
<style>
#thingslist {
	widows: 98%;
	background-color: #27a9e3;
	color: white;
	height: 50px;
	text-align: center;
	line-height: 50px;
	margin: 0 auto;
	font-size: 20px;
}
#main-tabR{ font-size:12px;
border-collapse:collapse;  
border-right:1px solid #eaeaea; 
border-bottom:1px solid #eaeaea;}
#main-tabR th{ height:40px; line-height:40px;border-left:1px solid #eaeaea;
border-top:1px solid #eaeaea; }
#main-tabR td{ font-size:12px; line-height:40px;border-left:1px solid #eaeaea;border-top:1px solid #eaeaea;}
#main-tabR td a{ font-size:12px; color:#548fc9;}
#main-tabR td a:hover{color:#565656; text-decoration:underline;}
#main-tabR input[type=text],
#main-tabR input[type=number],
#main-tabR input[type=password],
#main-tabR select{ width:310px; height:36px; line-height:36px; border:#ebebeb 1px solid;;padding:0 10px; margin:10px;}
#main-tabR input.text-but{ width:100px; height:40px; line-height:30px; border: 1px solid #cdcdcd; background:#e6e6e6; color:#969696; float:left; margin:0 10px 0 0; display:inline; cursor:pointer; font-size:14px; font-weight:bold;margin:10px;}
#main-tabR input[type=submit],#main-tabR input[type=button],#main-tabR  input[type=reset] {  width:100px; height:40px; line-height:30px;  cursor: pointer; color:#fff; font-size:14px; font-weight:bold;background-color: #27a9e3; border-radius: 3px; border: none; -webkit-appearance: none; outline:none; margin:10px; }
#main-tabR input[type=submit]:hover,#main-tabR input[type=button]:hover,#main-tabR  input[type=reset]:hover {  width:100px; height:40px; line-height:30px;  cursor: pointer; color:#fff; font-size:14px; font-weight:bold;background-color: #0c83b8; border-radius: 3px; border: none; -webkit-appearance: none; outline:none;  }

#main-tabM{ font-size:12px;
border-collapse:collapse;  
border-right:1px solid #eaeaea; 
border-bottom:1px solid #eaeaea;}
#main-tabM th{ height:40px; line-height:40px;border-left:1px solid #eaeaea;
border-top:1px solid #eaeaea; }
#main-tabM td{ font-size:12px; line-height:40px;border-left:1px solid #eaeaea;border-top:1px solid #eaeaea;}
#main-tabM td a{ font-size:12px; color:#548fc9;}
#main-tabM td a:hover{color:#565656; text-decoration:underline;}
#main-tabM input[type=text],
#main-tabM input[type=number],
#main-tabM input[type=password],
#main-tabM select{ width:310px; height:36px; line-height:36px; border:#ebebeb 1px solid;;padding:0 10px; margin:10px;}
#main-tabM input.text-but{ width:100px; height:40px; line-height:30px; border: 1px solid #cdcdcd; background:#e6e6e6; color:#969696; float:left; margin:0 10px 0 0; display:inline; cursor:pointer; font-size:14px; font-weight:bold;margin:10px;}
#main-tabM input[type=submit],#main-tabM input[type=button],#main-tabM  input[type=reset] {  width:100px; height:40px; line-height:30px;  cursor: pointer; color:#fff; font-size:14px; font-weight:bold;background-color: #27a9e3; border-radius: 3px; border: none; -webkit-appearance: none; outline:none; margin:10px; }
#main-tabM input[type=submit]:hover,#main-tabM input[type=button]:hover,#main-tabM  input[type=reset]:hover {  width:100px; height:40px; line-height:30px;  cursor: pointer; color:#fff; font-size:14px; font-weight:bold;background-color: #0c83b8; border-radius: 3px; border: none; -webkit-appearance: none; outline:none;  }

#main-tabL{ font-size:12px;
border-collapse:collapse;  
border-right:1px solid #eaeaea; 
border-bottom:1px solid #eaeaea;}
#main-tabL th{ height:40px; line-height:40px;border-left:1px solid #eaeaea;
border-top:1px solid #eaeaea; }
#main-tabL td{ font-size:12px; line-height:40px;border-left:1px solid #eaeaea;border-top:1px solid #eaeaea;}
#main-tabL td a{ font-size:12px; color:#548fc9;}
#main-tabL td a:hover{color:#565656; text-decoration:underline;}
#main-tabL input[type=text],
#main-tabL input[type=number],
#main-tabL input[type=password],
#main-tabL select{ width:310px; height:36px; line-height:36px; border:#ebebeb 1px solid;;padding:0 10px; margin:10px;}
#main-tabL input.text-but{ width:100px; height:40px; line-height:30px; border: 1px solid #cdcdcd; background:#e6e6e6; color:#969696; float:left; margin:0 10px 0 0; display:inline; cursor:pointer; font-size:14px; font-weight:bold;margin:10px;}
#main-tabL input[type=submit],#main-tabL input[type=button],#main-tabL  input[type=reset] {  width:100px; height:40px; line-height:30px;  cursor: pointer; color:#fff; font-size:14px; font-weight:bold;background-color: #27a9e3; border-radius: 3px; border: none; -webkit-appearance: none; outline:none; margin:10px; }
#main-tabL input[type=submit]:hover,#main-tabL input[type=button]:hover,#main-tabL  input[type=reset]:hover {  width:100px; height:40px; line-height:30px;  cursor: pointer; color:#fff; font-size:14px; font-weight:bold;background-color: #0c83b8; border-radius: 3px; border: none; -webkit-appearance: none; outline:none;  }
</style>
<script type="text/javascript"> 
	var thisPageR = 1;
	var endPageR = 1;
	var thisPageM = 1;
	var endPageM = 1;
	var thisPageL = 1;
	var endPageL = 1;
	$(function(){
		<%if(!session.getAttribute("role").equals("0")){%>
			ThingsListR();
			ThingsListM();
			ThingsListL();
		<%}%>
	});
	$(function() {
		
		$("#fromR").datepicker({
		      	defaultDate:new Date(),
		      	numberOfMonths:1,//显示几个月  
			    showButtonPanel:true,//是否显示按钮面板  
			    dateFormat: 'yy-mm-dd',//日期格式  
			    clearText:"清除",//清除日期的按钮名称  
			    closeText:"关闭",//关闭选择框的按钮名称  
			    yearSuffix: '年', //年的后缀  
			    changeYear: true,
			    showMonthAfterYear:true,//是否把月放在年的后面  
			   	monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
				dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
				dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
				dayNamesMin: ['日','一','二','三','四','五','六'],  
		      	onClose: function(selectedDate) {
			      $("#toR").datepicker( "option", "minDate",selectedDate);
			    }
		    });
		    $("#toR").datepicker({
		    	defaultDate:new Date(),
			      numberOfMonths:1,//显示几个月  
				    showButtonPanel:true,//是否显示按钮面板  
				    dateFormat: 'yy-mm-dd',//日期格式  
				    clearText:"清除",//清除日期的按钮名称  
				    closeText:"关闭",//关闭选择框的按钮名称  
				    yearSuffix: '年', //年的后缀  
				    changeYear: true,
				    showMonthAfterYear:true,//是否把月放在年的后面  
				    monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
					dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
					dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
					dayNamesMin: ['日','一','二','三','四','五','六'],  
		      onClose: function(selectedDate) {
		        $("#fromR").datepicker( "option", "maxDate",selectedDate);
		      }
		    });
		$("#fromL").datepicker({
		      defaultDate:new Date(),
		      numberOfMonths:1,//显示几个月  
			    showButtonPanel:true,//是否显示按钮面板  
			    dateFormat: 'yy-mm-dd',//日期格式  
			    clearText:"清除",//清除日期的按钮名称  
			    closeText:"关闭",//关闭选择框的按钮名称  
			    yearSuffix: '年', //年的后缀  
			    changeYear: true,
			    showMonthAfterYear:true,//是否把月放在年的后面  
			   monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
				dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
				dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
				dayNamesMin: ['日','一','二','三','四','五','六'],  
		      onClose: function(selectedDate) {
		        $("#toL").datepicker( "option", "minDate",selectedDate);
		      }
		    });
		    $("#toL").datepicker({
		    	defaultDate:new Date(),
			      numberOfMonths:1,//显示几个月  
				    showButtonPanel:true,//是否显示按钮面板  
				    dateFormat: 'yy-mm-dd',//日期格式  
				    clearText:"清除",//清除日期的按钮名称  
				    closeText:"关闭",//关闭选择框的按钮名称  
				    yearSuffix: '年', //年的后缀  
				    changeYear: true,
				    showMonthAfterYear:true,//是否把月放在年的后面  
				    monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
					dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
					dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
					dayNamesMin: ['日','一','二','三','四','五','六'],  
		      onClose: function(selectedDate) {
		        $("#fromL").datepicker( "option", "maxDate",selectedDate);
		      }
		    });
		$("#fromM").datepicker({
		      defaultDate:new Date(),
		      numberOfMonths:1,//显示几个月  
			    showButtonPanel:true,//是否显示按钮面板  
			    dateFormat: 'yy-mm-dd',//日期格式  
			    clearText:"清除",//清除日期的按钮名称  
			    closeText:"关闭",//关闭选择框的按钮名称  
			    yearSuffix: '年', //年的后缀  
			    changeYear: true,
			    showMonthAfterYear:true,//是否把月放在年的后面  
			   monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
				dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
				dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
				dayNamesMin: ['日','一','二','三','四','五','六'],  
		      onClose: function(selectedDate) {
		        $("#toM").datepicker( "option", "minDate",selectedDate);
		      }
		    });
		    $("#toM").datepicker({
		    	defaultDate:new Date(),
			      numberOfMonths:1,//显示几个月  
				    showButtonPanel:true,//是否显示按钮面板  
				    dateFormat: 'yy-mm-dd',//日期格式  
				    clearText:"清除",//清除日期的按钮名称  
				    closeText:"关闭",//关闭选择框的按钮名称  
				    yearSuffix: '年', //年的后缀  
				    changeYear: true,
				    showMonthAfterYear:true,//是否把月放在年的后面  
				    monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
					dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
					dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
					dayNamesMin: ['日','一','二','三','四','五','六'],  
		      onClose: function(selectedDate) {
		        $("#fromM").datepicker("option", "maxDate",selectedDate);
		      }
		    });
		
		
		/* 首页监听事件 */
		$("body").delegate("#firstR","click",function(){
			thisPageR=1;
			ThingsListR();
			return false;
		});
		/* 下一页监听事件 */
		$("body").delegate("#nextR","click",function(){
			if (thisPageR<endPageR) {
				thisPageR++;
				ThingsListR();
			}
			return false;
		});
		/* 上一页监听事件 */
		$("body").delegate("#previousR","click",function(){
			if (thisPageR>1) {
				thisPageR--;
				ThingsListR();
			}
			return false;
		});
		/* 尾页监听事件 */
		$("body").delegate("#endR","click",function(){
			thisPageR=endPageR;
			ThingsListR();
			return false;
		});
		
		/* 首页监听事件 */
		$("body").delegate("#firstM","click",function(){
			thisPageM=1;
			ThingsListM();
			return false;
		});
		/* 下一页监听事件 */
		$("body").delegate("#nextM","click",function(){
			if (thisPageM<endPageM) {
				thisPageM++;
				ThingsListM();
			}
			return false;
		});
		/* 上一页监听事件 */
		$("body").delegate("#previousM","click",function(){
			if (thisPageM>1) {
				thisPageM--;
				ThingsListM();
			}
			return false;
		});
		/* 尾页监听事件 */
		$("body").delegate("#endM","click",function(){
			thisPageM=endPageM;
			ThingsListM();
			return false;
		});
		
		/* 首页监听事件 */
		$("body").delegate("#firstL","click",function(){
			thisPageL=1;
			ThingsListL();
			return false;
		});
		/* 下一页监听事件 */
		$("body").delegate("#nextL","click",function(){
			if (thisPageL<endPageL) {
				thisPageL++;
				ThingsListL();
			}
			return false;
		});
		/* 上一页监听事件 */
		$("body").delegate("#previousL","click",function(){
			if (thisPageL>1) {
				thisPageL--;
				ThingsListL();
			}
			return false;
		});
		/* 尾页监听事件 */
		$("body").delegate("#endL","click",function(){
			thisPageL=endPageL;
			ThingsListL();
			return false;
		});
	});
	/* 查询监听事件 */
	function ThingsListR() {
		var tableHeadR = "<tbody><tr class='bggray'>"+
	        "<th align='center' valign='middle'>设备编号</th>"+
	        "<th align='center' valign='middle'>设备名称</th>"+
	        "<th align='center' valign='middle'>提报来源</th>"+
	        "<th align='center' valign='middle'>报修日期</th>"+
	        "<th align='center' valign='middle'>报修部门</th>"+
	        "<th align='center' valign='middle'>报修时间</th>"+
	        "<th align='center' valign='middle'>故障现象描述</th>"+
	        "<th align='center' valign='middle'>领取状态</th>"+
	        "<th align='center' valign='middle'>完成状态</th>"+
	        "<th align='center' valign='middle'>领取人</th>"+
	        "<th align='center' valign='middle'>审核状态</th>"+
	        "<th align='center' valign='middle'>领取时间</th>"+
	    "</tr>";
		$.ajax({
			dataType:"json",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			type:"GET", 
			url:"W_thingList_r",
			async: false,
			data:{
				page:thisPageR,
			},
			statusCode: {
				404: function() {  
	            	alert('page not found'); 
	            }  
	        },     
	        success:function(data,textStatus){
	        	thisPageR=data.pageNumber;
	        	endPageR = data.totalPage;
	        	var tableR=tableHeadR;
	        	var pagingR = "";
	        	for (var i = 0; i < data.data.length; i++) {
	        		if(data.totalPage!=0)
                	{
                		var endstate;
                		if((data.data[i].state)=="0")
                		{
                			endstate="提交";
                		}
                		if((data.data[i].state)=="1")
                		{
                			endstate="一级审核通过";
                		}
                		if((data.data[i].state)=="2")
                		{
                			endstate="一级审核未通过";
                		}
                		if((data.data[i].state)=="3")
                		{
                			endstate="二级审核通过";
                		}
                		if((data.data[i].state)=="4")
                		{
                			endstate="二级审核未通过";
                		}
                		var stateone;
                		if((data.data[i].state1)=="0")
                		{
                			stateone="未领取";
                		}
                		if((data.data[i].state1)=="1")
                		{
                			stateone="领取";
                		}
                		var statetwo;
                		if((data.data[i].state2)=="0")
                		{
                			statetwo="未完成";
                		}
                		if((data.data[i].state2)=="1")
                		{
                			statetwo="完成";
                		}
                		if((data.data[i].state2)=="2")
                		{
                			statetwo="未完成转计划";
                		}
	        		}
					if (i%2==0) {
	        			tableR += "<tr onMouseOut=\"this.style.backgroundColor='#ffffff'\" onMouseOver=\"this.style.backgroundColor='#edf5ff'\">";
					}else{
						tableR += "<tr class=\"bggray\" onMouseOut=\"this.style.backgroundColor='#f9f9f9'\" onMouseOver=\"this.style.backgroundColor='#edf5ff'\">";
					}
					tableR+= "<tr>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].deviceNo+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].name+"</td>"+/*
                    "<td align=\"center\" valign=\"middle\">"+data.data.content+"</td>"+*/
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].source+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].repairDate+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].repairDepartment+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].repairTime+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].faultPhenomenon+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+stateone+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+statetwo+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].maintenancePerson+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+endstate+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].date1+"</td>"+
                    "</tr>";
				}
	        	pagingR+="<td align=\"left\" valign=\"top\" class=\"fenye\">"+data.data.length+" 条数据 "+
	        	data.pageNumber+"/"+data.totalPage+" 页&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"firstR\">首页</a>&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"previousR\">上一页</a>&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"nextR\">下一页</a>&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"endR\">尾页</a>" + 
				"			</td>";
	        	$("#main-tabR").html(tableR);
	        	$("#pagingR").html(pagingR);
	        }
		});
	}
	/* 查询监听事件 */
	function ThingsListM() {
		var tableHeadM = "<tbody><tr class='bggray'>"+
		"<th align='center' valign='middle'>设备编号</th>"+
		"<th align='center' valign='middle'>设备名称</th>"+
        "<th align='center' valign='middle' style='width:180px;'>保全项目</th>"+
        "<th align='center' valign='middle' style='width:400px;'>标准与工时</th>"+/*
        "<th align='center' valign='middle'>润滑部位</th>"+ 
        "<th align='center' valign='middle'>油品规格</th>"+
        "<th align='center' valign='middle'>加注方式</th>"+
        "<th align='center' valign='middle'>加注量</th>"+
        "<th align='center' valign='middle'>保全方法</th>"+
        "<th align='center' valign='middle'>判断方法</th>"+ */
        "<th align='center' valign='middle'>指派人</th>"+/* 
        "<th align='center' valign='middle'>检查标准</th>"+ 
        "<th align='center' valign='middle'>安全风险</th>"+
        "<th align='center' valign='middle'>工具</th>"+ 
        "<th align='center' valign='middle'>人数</th>"+ 
        "<th align='center' valign='middle'>分钟数</th>"+*/
        "<th align='center' valign='middle'>定保周期</th>"+
        "<th align='center' valign='middle'>定保日期</th>"+
        "<th align='center' valign='middle'>领取状态</th>"+
        "<th align='center' valign='middle'>完成状态</th>"+
        "<th align='center' valign='middle'>领取人</th>"+
		"<th align='center' valign='middle'>审核状态</th>"+
        "<th align='center' valign='middle'>领取时间</th>"+
	    "</tr>";
		$.ajax({
			dataType:"json",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			type:"GET", 
			url:"W_thingList_m",
			async: false,
			data:{
				page:thisPageM,
			},
			statusCode: {
				404: function() {  
	            	alert('page not found'); 
	            }  
	        },     
	        success:function(data,textStatus){
	        	thisPageM=data.pageNumber;
	        	endPageM = data.totalPage;
	        	var tableM=tableHeadM;
	        	var pagingM = "";
	        	for (var i = 0; i < data.data.length; i++) {
	        		if(data.totalPage!=0)
                	{
                		var endstate;
                		if((data.data[i].state)=="0")
                		{
                			endstate="提交";
                		}
                		if((data.data[i].state)=="1")
                		{
                			endstate="一级审核通过";
                		}
                		if((data.data[i].state)=="2")
                		{
                			endstate="一级审核未通过";
                		}
                		if((data.data[i].state)=="3")
                		{
                			endstate="二级审核通过";
                		}
                		if((data.data[i].state)=="4")
                		{
                			endstate="二级审核未通过";
                		}
                		var stateone;
                		if((data.data[i].state1)=="0")
                		{
                			stateone="未领取";
                		}
                		if((data.data[i].state1)=="1")
                		{
                			stateone="领取";
                		}
                		var statetwo;
                		if((data.data[i].state2)=="0")
                		{
                			statetwo="未完成";
                		}
                		if((data.data[i].state2)=="1")
                		{
                			statetwo="完成";
                		}
                		if((data.data[i].state2)=="2")
                		{
                			statetwo="未完成转计划";
                		}
	        		}
					if (i%2==0) {
	        			tableM += "<tr onMouseOut=\"this.style.backgroundColor='#ffffff'\" onMouseOver=\"this.style.backgroundColor='#edf5ff'\">";
					}else{
						tableM += "<tr class=\"bggray\" onMouseOut=\"this.style.backgroundColor='#f9f9f9'\" onMouseOver=\"this.style.backgroundColor='#edf5ff'\">";
					}
					tableM+= "<tr>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].deviceNo+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].name+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].content+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].auditOpinion4+"</td>"+/* 
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].part+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].oil+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].type+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].fill+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].method+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].tpye+"</td>"+*/
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].reason+"</td>"+ /* 
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].standard+"</td>"+ 
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].security+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].tool+"</td>"+ 
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].pnumber+"</td>"+ 
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].mnumber+"</td>"+*/
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].cycle+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].maintenanceDate+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+stateone+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+statetwo+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].maintenancePerson+"</td>"+
					"<td align=\"center\" valign=\"middle\">"+endstate+"</td>"+
					"<td align=\"center\" valign=\"middle\">"+data.data[i].date1+"</td>"+
                    "</tr>";
				}
	        	pagingM+="<td align=\"left\" valign=\"top\" class=\"fenye\">"+data.data.length+" 条数据 "+
	        	data.pageNumber+"/"+data.totalPage+" 页&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"firstM\">首页</a>&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"previousM\">上一页</a>&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"nextM\">下一页</a>&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"endM\">尾页</a>" + 
				"			</td>";
	        	$("#main-tabM").html(tableM);
	        	$("#pagingM").html(pagingM);
	        }
		});
	}
	/* 查询监听事件 */
	function ThingsListL() {
		var tableHeadL = "<tbody><tr class='bggray'>"+
		"<th align='center' valign='middle'>设备编号</th>"+
		"<th align='center' valign='middle'>设备名称</th>"+
        "<th align='center' valign='middle' style='width:180px;'>润滑项目</th>"+
        "<th align='center' valign='middle' style='width:400px;'>标准与工时</th>"+/*
        "<th align='center' valign='middle'>润滑部位</th>"+ 
        "<th align='center' valign='middle'>油品规格</th>"+
        "<th align='center' valign='middle'>加注方式</th>"+
        "<th align='center' valign='middle'>加注量</th>"+
        "<th align='center' valign='middle'>润滑方法</th>"+
        "<th align='center' valign='middle'>判断方法</th>"+ */
        "<th align='center' valign='middle'>指派人</th>"+/* 
        "<th align='center' valign='middle'>检查标准</th>"+ */
        /*"<th align='center' valign='middle'>安全风险</th>"+
        "<th align='center' valign='middle'>工具</th>"+
        "<th align='center' valign='middle'>人数</th>"+ 
        "<th align='center' valign='middle'>分钟数</th>"+*/
        "<th align='center' valign='middle'>润滑周期</th>"+
        "<th align='center' valign='middle'>润滑日期</th>"+
        "<th align='center' valign='middle'>领取状态</th>"+
        "<th align='center' valign='middle'>完成状态</th>"+
        "<th align='center' valign='middle'>领取人</th>"+
		"<th align='center' valign='middle'>审核状态</th>"+
        "<th align='center' valign='middle'>领取时间</th>"+
	    "</tr>";
		$.ajax({
			dataType:"json",
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			type:"GET", 
			url:"W_thingList_l",
			async: false,
			data:{
				page:thisPageL,
			},
			statusCode: {
				404: function() {  
	            	alert('page not found'); 
	            }  
	        },     
	        success:function(data,textStatus){
	        	thisPageL=data.pageNumber;
	        	endPageL = data.totalPage;
	        	var tableL=tableHeadL;
	        	var pagingL = "";
	        	for (var i = 0; i < data.data.length; i++) {
	        		if(data.totalPage!=0)
                	{
                		var endstate;
                		if((data.data[i].state)=="0")
                		{
                			endstate="提交";
                		}
                		if((data.data[i].state)=="1")
                		{
                			endstate="一级审核通过";
                		}
                		if((data.data[i].state)=="2")
                		{
                			endstate="一级审核未通过";
                		}
                		if((data.data[i].state)=="3")
                		{
                			endstate="二级审核通过";
                		}
                		if((data.data[i].state)=="4")
                		{
                			endstate="二级审核未通过";
                		}
                		var stateone;
                		if((data.data[i].state1)=="0")
                		{
                			stateone="未领取";
                		}
                		if((data.data[i].state1)=="1")
                		{
                			stateone="领取";
                		}
                		var statetwo;
                		if((data.data[i].state2)=="0")
                		{
                			statetwo="未完成";
                		}
                		if((data.data[i].state2)=="1")
                		{
                			statetwo="完成";
                		}
                		if((data.data[i].state2)=="2")
                		{
                			statetwo="未完成转计划";
                		}
	        		}
					if (i%2==0) {
	        			tableL += "<tr onMouseOut=\"this.style.backgroundColor='#ffffff'\" onMouseOver=\"this.style.backgroundColor='#edf5ff'\">";
					}else{
						tableL += "<tr class=\"bggray\" onMouseOut=\"this.style.backgroundColor='#f9f9f9'\" onMouseOver=\"this.style.backgroundColor='#edf5ff'\">";
					}
					tableL+= "<tr>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].deviceNo+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].name+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].content+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].auditOpinion4+"</td>"+/* 
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].part+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].oil+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].type+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].fill+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].method+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].tpye+"</td>"+*/
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].reason+"</td>"+ /* 
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].standard+"</td>"+ */
                    /*"<td align=\"center\" valign=\"middle\">"+data.data[i].security+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].tool+"</td>"+ 
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].pnumber+"</td>"+ 
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].mnumber+"</td>"+*/
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].cycle+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].maintenanceDate+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+stateone+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+statetwo+"</td>"+
                    "<td align=\"center\" valign=\"middle\">"+data.data[i].maintenancePerson+"</td>"+
					"<td align=\"center\" valign=\"middle\">"+endstate+"</td>"+
					"<td align=\"center\" valign=\"middle\">"+data.data[i].date1+"</td>"+
                    "</tr>";
				}
	        	pagingL+="<td align=\"left\" valign=\"top\" class=\"fenye\">"+data.data.length+" 条数据 "+
	        	data.pageNumber+"/"+data.totalPage+" 页&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"firstL\">首页</a>&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"previousL\">上一页</a>&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"nextL\">下一页</a>&nbsp;&nbsp;" + 
				"				<a href=\"#\" id=\"endL\">尾页</a>" + 
				"			</td>";
	        	$("#main-tabL").html(tableL);
	        	$("#pagingL").html(pagingL);
	        }
		});
	}
	/*推送PushR()*/
	function PushR() {
		$.ajax({
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			type:"GET", 
			url:"W_AutoRepairInfo",
			data:{
				start:$("#fromR").val(),
				end:$("#toR").val()
			},
			async: false,
			statusCode: {
				404: function() {  
	            	alert('推送失败'); 
	            }  
	        },     
	        success:function(data,textStatus){
	        	alert('推送成功!');
        	}
        });
	}
	/*推送PushR()*/
	function PushM() {
		$.ajax({
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			type:"GET", 
			url:"W_AutoMaintenanceRecordsInfo",
			data:{
				start:$("#fromM").val(),
				end:$("#toM").val()
			},
			async: false,
			statusCode: {
				404: function() {  
	            	alert('推送失败'); 
	            }  
	        },     
	        success:function(data,textStatus){
	        	alert('推送成功!');
        	}
        });
	}
	/*推送PushR()*/
	function PushL() {
		$.ajax({
			contentType:"application/x-www-form-urlencoded; charset=utf-8",
			type:"GET", 
			url:"W_AutoLubricationRecordsInfo",
			data:{
				start:$("#fromL").val(),
				end:$("#toL").val()
			},
			async: false,
			statusCode: {
				404: function() {  
	            	alert('推送失败'); 
	            }  
	        },     
	        success:function(data,textStatus){
	        	alert('推送成功!');
        	}
        });
	}
	</script> 
</head>
<body>
<%if(session.getAttribute("role").equals("0")) {%>
	<div id="thingslist">欢迎使用奥晟达设备管理系统</div>
	<%}else{ %>
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td width="99%" align="left" valign="top">您的位置：代办事项</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="search">
					<tr>
						<td width="90%" align="left" valign="middle">
							<form method="post" action="">
								<span>维修代办事项</span> 
								<input type="text" class="text-word" id="fromR" style="margin-left: 10px" value="<%=beforeStr%>">
								<span style="float:left">至</span>
								<input type="text" class="text-word" id="toR" style="margin-left: 10px" value="<%=nowStr%>">
								<%if(!session.getAttribute("role").equals("1")) {%><input type="button"
									value="全部推送" class="text-but" onclick="PushR()"><%} %>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="main-tabR">
					
				</table>
			</td>
		</tr>
		<tr id="pagingR">
		</tr>
	</table>
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="search">
					<tr>
						<td width="90%" align="left" valign="middle">
							<form method="post" action="">
								<span>保养代办事项</span>
								<input type="text" class="text-word" id="fromM" style="margin-left: 10px" value="<%=beforeStr%>">
								<span style="float:left">至</span>
								<input type="text" class="text-word" id="toM" style="margin-left: 10px" value="<%=nowStr%>">
								 <%if(!session.getAttribute("role").equals("1")) {%><input type="button"
									value="全部推送" class="text-but" onclick="PushM()"><%} %>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="main-tabM">
					
				</table>
			</td>
		</tr>
		<tr id="pagingM">
		</tr>
	</table>
	<table width="99%" border="0" cellspacing="0" cellpadding="0"
		id="searchmain">
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="search">
					<tr>
						<td width="90%" align="left" valign="middle">
							<form method="post" action="">
								<span>润滑代办事项</span> 
								<input type="text" class="text-word" id="fromL" style="margin-left: 10px" value="<%=beforeStr%>">
								<span style="float:left">至</span>
								<input type="text" class="text-word" id="toL" style="margin-left: 10px" value="<%=nowStr%>">
								<%if(!session.getAttribute("role").equals("1")) {%><input type="button"
									value="全部推送" class="text-but" onclick="PushL()"><%} %>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					id="main-tabL">
					
				</table>
			</td>
		</tr>
		<tr id="pagingL">
		</tr>
	</table>
	<%} %>
</body>

</html>