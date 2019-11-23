<%@page import="com.zhuoer.newCareDevice.bean.Pdsgy"%>
<%@page import="com.zhuoer.newCareDevice.service.util.GetJsonShebei"%>
<%@page import="com.zhuoer.customer_management.Bean.CustomerInfo"%>
<%@page import="com.zhuoer.aJsonAPI.No"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.zhuoer.newCareDevice.util.*" %>
    
<%
	
	int thisPage = 1;
	int pageSize = 1;
	int role = Integer.parseInt(session.getAttribute("role").toString());
	if(session.getAttribute("pageSize") != null )
		pageSize = Integer.parseInt(session.getAttribute("pageSize").toString());
	if(session.getAttribute("thispdsgyPage")!=null)
		thisPage = Integer.parseInt(session.getAttribute("thispdsgyPage").toString());
	if(pageSize%15==0)
		pageSize = pageSize/15;
	else pageSize = pageSize/15+1;
	Map<String, String> map = (Map)request.getSession().getAttribute("operate");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主要内容区</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/dataBox.css" />
<link rel="stylesheet" href="css/jquery.css">
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<style>
.ui-autocomplete {
	position: absolute;
	top: 0;
	left: 0;
	cursor: default;
	overflow-y:scroll;
	overflow-x:hidden; 
	max-height: 200px; 
}
</style>
</head>
<body>
<table width="100%" border="0" cellspPDSGYng="0" cellpadding="0" id="searchmain" >
  <tr>
    <td width="99%" align="left" valign="top">您的位置：配电室高压</td>
  </tr>
  <tr>
  <%
  	String classNone = "";
  %>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspPDSGYng="0" cellpadding="0" id="search">
  		<tr>
   		 <td width="90%" align="left" valign="middle">
	         <form id="form" method="post" action="<%=request.getContextPath() %>/newCareDevice/GetPDSGY?page=1">
	         <span>设备号：</span>
	         <input type="text" id="findByNo" name="shebei" value="" class="text-word">
	          <%
	         	if(map.get("find").equals("0"))
	         		classNone = "none";
	         	else classNone = "block";
	         %>
	         <%
	         	List<CustomerInfo> dlist = No.getDepartmentIdAndName(request.getSession().getAttribute("name").toString());
	         %>
	         <span>部门：</span>
         	 <select name="departmentid">
	         <%
	         	for(int i=0; i<dlist.size(); i++) {
	         %>
	         	<option value="<%=dlist.get(i).getDepartmentid()%>"><%=dlist.get(i).getDepartmentid() %> <%=dlist.get(i).getDepartmentname() %></option>
	         <%
	         	}
			%>
	         </select>
	         <span >开始时间：</span>
         	<input type="text" name="starttime" id="starttime" readonly="readonly" class="text-word" />
         	<span >结束时间：</span>
         	<input type="text" name="endtime" id="endtime" readonly="readonly" class="text-word"/>
	         <input type="submit" value="查询" class="text-but" style="display:<%=classNone%>;">
	         <input  type="button" name="export" class="text-but" id="export" value="excel">
	         </form>
         </td>
         <%
         	if(map.get("input").equals("0"))
         		classNone = "none";
         	else classNone = "table-cell";
         %>
  		  <td width="10%" align="center" valign="middle" style="text-align:right; width:200px;display:<%=classNone%>">
  		  	<a href="Pdsgy.html?no=<%=request.getSession().getAttribute("name").toString() %>" target="mainFrame" onFocus="this.blur()" class="add" id="addDom">配电室高压</a>
  		  </td>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    
    <table width="100%" border="0" cellspPDSGYng="0" cellpadding="0" id="main-tab">
      <tr  class="bggray">
        <th align="center" valign="middle">审核状态</th>
        <th align="center" valign="middle" style="display: none;" >id</th>
        <th align="center" valign="middle">时间</th>
        <th align="center" valign="middle">设备</th>
        <th align="center" valign="middle">金额</th>
        <th align="center" valign="middle">有功总</th>
        <th align="center" valign="middle">有功需量</th>
        <th align="center" valign="middle">有功尖值</th>
        <th align="center" valign="middle">有功峰值</th>
        <th align="center" valign="middle">有功平值</th>
        <th align="center" valign="middle">有功谷值</th>
        <th align="center" valign="middle">无功1</th>
        <th align="center" valign="middle">无功2</th>
        <th align="center" valign="middle">无功3</th>
        <th align="center" valign="middle">无功4</th>
        <th align="center" valign="middle">功率因数</th>
        <th align="center" valign="middle">实际功率因数</th>
        <th align="center" valign="middle">温度</th>
        <th align="center" valign="middle">湿度</th>
        <th align="center" valign="middle">倍率</th>
        <th align="center" valign="middle">电压</th>
        <th align="center" valign="middle">电流</th>
        <th align="center" valign="middle">记录人</th>
        <th align="center" valign="middle">审核意见</th>
        <th align="center" valign="middle">备注</th>
        <%
        	if(map.get("modify").equals("1") || map.get("del").equals("1")) {
        		classNone = "block";
        	}
        	else classNone = "none";
        %>
        <th align="center" valign="middle" style="display:<%=classNone%>">操作</th>
      </tr>
      
      <%
      		List<Pdsgy> list = (List)request.getSession().getAttribute("pdsgylist");
      		if(list != null) {
      			for(int i=0; i<list.size(); i++) {
      %>
      
			      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
			      	<%
			      		String state = "审核中";
			      		if(Integer.valueOf(list.get(i).getStatus()) == 1) {
			      			state = "通过";
			      		}
			      		else if(Integer.valueOf(list.get(i).getStatus()) == 2){
			      			state = "未通过";
			      		}
			      	%>
			        <th align="center" valign="middle" ><%=state %></th>
			        <th align="center" valign="middle" style="display: none;"><%=list.get(i).getId() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getDatetime() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getShebei() %></th>
			        
			        <th align="center" valign="middle"><%=list.get(i).getj() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getYougongzong() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getYougongxuliang() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getYougongjianzhi() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getYougongfengzhi() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getYougongpingzhi() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getYougongguzhi() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getWugong1() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getWugong2() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getWugong3() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getWugong4() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getGonglvyinshu() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getShijigonglvyinshu() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getWendu() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getShidu() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getBeilv() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getDianya() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getDianliu() %></th>
			        
			        <th align="center" valign="middle"><%=list.get(i).getJiluren() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getShenheyijian() %></th>
			        <th align="center" valign="middle"><%=list.get(i).getMark() %></th>
				        <td align="center" valign="middle" class="borderbottom">
				         <%
				         	if(map.get("modify").equals("0") || list.get(i).getStatus().equals("1"))
				         		classNone = "none";
				         	else classNone = "inline";
				         %>
				        	<a href="Pdsgy.html?no=<%=request.getSession().getAttribute("name").toString() %>&id=<%=list.get(i).getId() %>" disabled target="mainFrame" onFocus="this.blur()" class="updateData" style="display:<%=classNone%>">编辑</a>
				        	<span class="gray" style="display:<%=classNone%>">&nbsp;|&nbsp;</span>
				        	 <%
					         	if(map.get("del").equals("0"))
					         		classNone = "none";
					         	else classNone = "inline";
					         %>
				        	<a href="<%=request.getContextPath() %>/DeletePDSGY?id=<%=list.get(i).getId() %>" style="display:<%=classNone%>" onclick="return delDate(<%=list.get(i).getId() %>, this)" target="mainFrame" onFocus="this.blur()" class="DeleteeteData">删除</a>
				        	<!--
				            	作者：923501316@qq.com
				            	时间：2018-04-18
				            	描述：如果该记录被审核，则允许再次审核
				            -->
				            <%
				            	if(map.get("auditing").equals("1") && list.get(i).getStatus().equals("0")) classNone = "inline";
				            	else classNone="none";
				            %>
				        	<span class="gray" style="display:<%=classNone %>">&nbsp;|&nbsp;</span>
				        	<a href="<%=request.getContextPath() %>/SHPDSGY?id=<%=list.get(i).getId() %>&status=1" target="mainFrame" onFocus="this.blur()" class="stateData" style="display:<%=classNone %>">通过</a>
				        	<span class="gray" style="display:<%=classNone %>">&nbsp;|&nbsp;</span>
				        	<a href="<%=request.getContextPath() %>/SHPDSGY?id=<%=list.get(i).getId() %>&status=2" target="mainFrame" onFocus="this.blur()" class="stateData" style="display:<%=classNone %>">不通过</a>
				        </td>
		        <%
			        	}
		        	}
		        %>
		        
		      </tr>
      
    </table>
    </td>
    </tr>
    <% 
    	if(list!=null) {
    %>
		  <tr>
		    <td align="left" valign="top" class="fenye">
		    
		    	<span id="count"><%=list.size() %></span> 条数据 <%=thisPage %>/<%=pageSize %> 页&nbsp;&nbsp;
	    		<a href="<%=request.getContextPath() %>/newCareDevice/GetPDSGY?page=1" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
		    	<%
		    		int beforePage = thisPage - 1;
		    		if(thisPage>1) {
		    		
		    	%>
		    		<a href="<%=request.getContextPath() %>/newCareDevice/GetPDSGY?page=<%=beforePage %>" target="mainFrame" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;
		    	<% 
		    		}
		    	%>
		    	<%
		    		int afterPage = thisPage + 1;
		    		if(thisPage<pageSize) {
		    	%>
		    		<a href="<%=request.getContextPath() %>/newCareDevice/GetPDSGY?page=<%=afterPage %>" target="mainFrame" onFocus="this.blur()">下一页</a>&nbsp;&nbsp;
		    	<%
		    		}
		    	%>
		    	
		    	<%
		    		if(thisPage != pageSize && pageSize>1) {
		    	%>
		    		<a href="<%=request.getContextPath() %>/newCareDevice/GetPDSGY?page=<%=pageSize %>" target="mainFrame" onFocus="this.blur()">尾页</a></td>
		    	<%
		    		}
		    	%>
		  </tr>
		  
	<%
    	}
	%>
</table>

	<script>
		var availableTags = <%=GetJsonShebei.getJsonShebei(request.getSession().getAttribute("name").toString()) %>;
		var nowData = new Date();
		var preDate = new Date(nowData.getTime() - 3*24*60*60*1000); //前三天
		var year = preDate.getFullYear();
		var month = preDate.getMonth()+1;
		var day = preDate.getDate();
		if(month<10) {
			month = "0"+month;
		}
		if(day<10) {
			day = "0"+day;
		}
		var dateString = year+"-"+month+"-"+day;
		var nyear = nowData.getFullYear();
		var nmonth = nowData.getMonth()+1;
		var nday = nowData.getDate();
		if(nmonth<10) {
			nmonth = "0"+nmonth;
		}
		if(nday<10) {
			nday = "0"+nday;
		}
		var ndateString = nyear+"-"+nmonth+"-"+nday;
		$("#starttime").val(dateString)
    	$("#endtime").val(ndateString)
	    $( "#findByNo" ).autocomplete({
	      source: availableTags
	    });
	    
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
            onSelect: function(selectedDate,inst) {//选择日期后执行的操作
            	var sdate = this.value;
               	var currentDate = new Date();
               	var starttime = $("#starttime").val();
               	var stime = new Date(starttime);
               	var etime = $("#endtime").val();
               	etime = new Date(etime);
               	if(stime.getTime()<=etime.getTime()) {
               		this.value = selectedDate;
               	}
               	else {
               		alert("开始时间不能大于结束时间")
               		this.value = inst.lastVal;
               	}
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
           }
         });
          
          function delDate(id, that) {
  	    	var that = $(that);
  	    	var parent = that.parent().parent();
  			if(confirm("确定要删除吗？")) {
  				$.ajax({
  					url: 'DeletePDSGY',
  					type: 'get',
  					dataType:'json',
  					data:{"id":id},
  					success: function(res) {
  						if(res.status) {
  							parent.remove();
  							alert(res.message);
  							var count = $("#count").html();
  							count = parseInt(count);
  							count--;
  							$("#count").html(count);
  						} else {
  							alert(res.message);
  						}
  					}
  	           }) 
  			}
  			return false;
  		}
	    $("#export").on("click",function() {
      	  var params = $("#form").serialize();
      	  console.log(params)
	    		$.ajax({
					url: 'newCareDevice/GetPDSGYPage',
					type: 'get',
					data:params,
					success: function(res) {
						if(res.indexOf("empty")==-1) {
							window.open(res);
						}else {
							alert("查询结果为空，不能导出为excel")
						} 
					}
	           })
        });
 	</script>
</div>

</body>
</html>