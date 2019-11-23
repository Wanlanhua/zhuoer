<%@page import="com.zhuoer.customer_management.Bean.CustomerInfo"%>
<%@page import="com.zhuoer.aJsonAPI.No"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zhuoer.careDevice.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.zhuoer.careDevice.util.*" %>
    
<%
	
	int thisPage = 1;
	int pageSize = 1;
	int role = Integer.parseInt(session.getAttribute("role").toString());
	if(session.getAttribute("pageSizeACI") != null )
		pageSize = Integer.parseInt(session.getAttribute("pageSizeACI").toString());
	if(session.getAttribute("thisPageACI")!=null)
		thisPage = Integer.parseInt(session.getAttribute("thisPageACI").toString());
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
<script>
	var addActionTarget = "<%=request.getContextPath() %>/careDevice/InsertACI";
	var updateActionTarget = "<%=request.getContextPath() %>/careDevice/UpdateACI";
	var stateActionTarget = "<%=request.getContextPath() %>/careDevice/StateACI";
	var deleteActionTarget = "<%=request.getContextPath() %>/careDevice/DelACI";
</script>
</head>
<body>
<table width="150%" border="0" cellspacing="0" cellpadding="0" id="searchmain" >
  <tr>
    <td width="99%" align="left" valign="top">您的位置：空气压缩机运行信息</td>
  </tr>
  <tr>
  <%
  	String classNone = "";
  %>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		<tr>
   		 <td width="90%" align="left" valign="middle">
	         <form method="post" action="<%=request.getContextPath() %>/careDevice/GetACI?page=1">
	         <span>设备单位：</span>
	         <input type="text" id="findByNo" name="qname" value="" class="text-word">
	          <%
	         	if(map.get("find").equals("0"))
	         		classNone = "none";
	         	else classNone = "block";
	         %>
	         <input type="submit" value="查询" class="text-but" style="display:<%=classNone%>;">
	         </form>
         </td>
         <%
         	if(map.get("input").equals("0"))
         		classNone = "none";
         	else classNone = "table-cell";
         %>
  		  <td width="10%" align="center" valign="middle" style="text-align:right; width:200px;display:<%=classNone%>"><a href="javascript:void(0);" target="mainFrame" onFocus="this.blur()" class="add" id="addDom">空气压缩机运行信息</a></td>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr  class="bggray">
        <th align="center" valign="middle">审核状态</th>
        <th align="center" valign="middle" style="display: none;" >id</th>
        <th align="center" valign="middle">项目部编号</th>
        <th align="center" valign="middle">时间</th>
        <th align="center" valign="middle">排气压力</th>
        <th align="center" valign="middle">系统压力</th>
        <th align="center" valign="middle">冷却水压力</th>
        <th align="center" valign="middle">润滑油压力</th>
        <th align="center" valign="middle">1#排气温度</th>
        <th align="center" valign="middle">2#排气温度</th>
        <th align="center" valign="middle">系统温度</th>
        <th align="center" valign="middle">环境温度</th>
        <th align="center" valign="middle">润滑油温度</th>
        <th align="center" valign="middle">冷却水温度</th>
        <th align="center" valign="middle">主电机前轴承温度</th>
        <th align="center" valign="middle">主电机后轴承温度</th>
        <th align="center" valign="middle">运转电压</th>
        <th align="center" valign="middle">电流指示</th>
        <th align="center" valign="middle">累计运行时间</th>
        <th align="center" valign="middle">累计负荷时间</th>
        <th align="center" valign="middle">记录人</th>
        <th align="center" valign="middle">负责人</th>
        <th align="center" valign="middle">二级审核意见</th>
        <th align="center" valign="middle" style="display: none;">备注</th>
        <%
        	if(map.get("modify").equals("1") || map.get("del").equals("1")) {
        		classNone = "block";
        	}
        	else classNone = "none";
        %>
        <th align="center" valign="middle" style="display:<%=classNone%>">操作</th>
      </tr>
      
      <%
      		List<AirCompressorInfo> list = (List)request.getSession().getAttribute("listACI");
      		if(list != null) {
      			for(int i=0; i<list.size(); i++) {
      %>
      
			      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
			      	<%
			      		String state = "审核中";
			      		if(Integer.valueOf(list.get(i).getState()) == 1) {
			      			state = "通过";
			      		}
			      		else if(Integer.valueOf(list.get(i).getState()) == 2){
			      			state = "未通过";
			      		}
			      	%>
			        <th align="center" valign="middle" ><%=state %></th>
			        <th align="center" valign="middle" style="display: none;"><%=list.get(i).getId() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getCustomerNo() %></th>
			        <th align="center" valign="middle" ><%=TimeUtilToSql.getSdfTime(list.get(i).getMaintenanceDate()) %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getExhaust() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getSystem() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getCoolingWater() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getLube() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getExhaust1T() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getExhaust2T() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getSystemT() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getAmbientT() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getLubeT() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getCoolingWaterT() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getFrontBearingT() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getRearBearingT() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getOperatingVoltage() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getCurrentIndication() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getRuntime() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getLoadTime() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getRecorder() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getPerson() %></th>
			        <th align="center" valign="middle" ><%=list.get(i).getAuditOpinion2() %></th>
			        <th align="center" valign="middle" style="display: none;"><%=list.get(i).getMark() %></th>
				        <td align="center" valign="middle" class="borderbottom">
				         <%
				         	if(map.get("modify").equals("0"))
				         		classNone = "none";
				         	else classNone = "inline";
				         %>
				        	<a href="javascript:void(0);" target="mainFrame" onFocus="this.blur()" class="updateData" style="display:<%=classNone%>">编辑</a>
				        	<span class="gray" style="display:<%=classNone%>">&nbsp;|&nbsp;</span>
				        	 <%
					         	if(map.get("del").equals("0"))
					         		classNone = "none";
					         	else classNone = "inline";
					         %>
				        	<a href="<%=request.getContextPath() %>/careDevice/DelACI?id=<%=list.get(i).getId() %>" style="display:<%=classNone%>" onclick="return deleteData()" target="mainFrame" onFocus="this.blur()" class="deleteData">删除</a>
				        	<!--
				            	作者：923501316@qq.com
				            	时间：2018-04-18
				            	描述：如果该记录被审核，则允许再次审核
				            -->
				            <%
				            	if(map.get("auditing").equals("1") && list.get(i).getState().equals("0")) classNone = "inline";
				            	else classNone="none";
				            %>
				        	<span class="gray" style="display:<%=classNone %>">&nbsp;|&nbsp;</span>
				        	<a href="javascript:void(0);" target="mainFrame" onFocus="this.blur()" class="stateData" style="display:<%=classNone %>">审核</a>
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
		    
		    	<%=list.size() %> 条数据 <%=thisPage %>/<%=pageSize %> 页&nbsp;&nbsp;
	    		<a href="<%=request.getContextPath() %>/careDevice/GetACI?page=1" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
		    	<%
		    		int beforePage = thisPage - 1;
		    		if(thisPage>1) {
		    		
		    	%>
		    		<a href="<%=request.getContextPath() %>/careDevice/GetACI?page=<%=beforePage %>" target="mainFrame" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;
		    	<% 
		    		}
		    	%>
		    	<%
		    		int afterPage = thisPage + 1;
		    		if(thisPage<pageSize) {
		    	%>
		    		<a href="<%=request.getContextPath() %>/careDevice/GetACI?page=<%=afterPage %>" target="mainFrame" onFocus="this.blur()">下一页</a>&nbsp;&nbsp;
		    	<%
		    		}
		    	%>
		    	
		    	<%
		    		if(thisPage != pageSize && pageSize>1) {
		    	%>
		    		<a href="<%=request.getContextPath() %>/careDevice/GetACI?page=<%=pageSize %>" target="mainFrame" onFocus="this.blur()">尾页</a></td>
		    	<%
		    		}
		    	%>
		  </tr>
		  
	<%
    	}
	%>
</table>

<div id="dataBox">
	
	<div style="text-align: center;width:45%;">
		<div class="data_head">
			<h2><span id="dataBoxTitle"></span>空气压缩机运行信息</h2>
			<span class="data_close" id="dataClose">x</span>
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr>
		    	<td align="left" valign="top">
			    	<form method="post" action="">
				    	<table width="90%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
				      
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">审核状态：</td>
								<td align="left" valign="middle">
									<input type="text" name="state" value="" addNone stateDisabled updateDisabled dataNull>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">id：</td>
								<td align="left" valign="middle">
									<input type="text" name="id" value="" updateNone addNone stateNone deleteNone dataNull>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">项目部编号：</td>
								<td align="left" valign="middle">
									<select name="customerNo" updateDisabled stateDisabled>
										<%
											List<CustomerInfo> clist = No.getDepartmentIdAndName(request.getSession().getAttribute("name").toString());
											for(int i=0; i<clist.size(); i++) {
											
										%>
										<option value="<%=clist.get(i).getDepartmentid()%>"><%=clist.get(i).getDepartmentid() %> | <%=clist.get(i).getDepartmentname() %></option>
										<%
											}
										%>
									</select>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">时间：</td>
								<td align="left" valign="middle">
									<input type="text" name="maintenanceDate" readOnly="readOnly" id="dateTime" value="" stateDisabled >
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">排气压力：</td>
								<td align="left" valign="middle">
									<input type="text" name="exhaust" value="" stateDisabled >
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">系统压力：</td>
								<td align="left" valign="middle">
									<input type="text" name="system" value="" stateDisabled >
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">冷却水压力：</td>
								<td align="left" valign="middle">
									<input type="text" name="coolingWater" value="" stateDisabled >
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">润滑油压力：</td>
								<td align="left" valign="middle">
									<input type="text" name="lube" value="" stateDisabled >
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">1#排气温度：</td>
								<td align="left" valign="middle">
									<input type="text" name="exhaust1T" value="" stateDisabled >
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">2#排气温度：</td>
								<td align="left" valign="middle">
									<input type="text" name="exhaust2T" value="" stateDisabled >
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">系统温度：</td>
								<td align="left" valign="middle">
									<input type="text" name="systemT" value="" stateDisabled >
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">环境温度：</td>
								<td align="left" valign="middle">
									<input type="text" name="ambientT" value="" stateDisabled >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">润滑油温度：</td>
								<td align="left" valign="middle">
									<input type="text" name="lubeT" value="" stateDisabled >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">冷却水温度：</td>
								<td align="left" valign="middle">
									<input type="text" name="coolingWaterT" value="" stateDisabled >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">主电机前轴承温度：</td>
								<td align="left" valign="middle">
									<input type="text" name="frontBearingT" value="" stateDisabled >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">主电机后轴承温度：</td>
								<td align="left" valign="middle">
									<input type="text" name="rearBearingT" value="" stateDisabled >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">运转电压：</td>
								<td align="left" valign="middle">
									<input type="text" name="operatingVoltage" value="" stateDisabled >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">电流指示：</td>
								<td align="left" valign="middle">
									<input type="text" name="currentIndication" value="" stateDisabled >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">累计运行时间：</td>
								<td align="left" valign="middle">
									<input type="text" name="runtime" value="" stateDisabled >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">累计负荷时间：</td>
								<td align="left" valign="middle">
									<input type="text" name="loadTime" value="" stateDisabled >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">记录人：</td>
								<td align="left" valign="middle">
									<input type="text" name="recorder" value="" stateDisabled >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">负责人：</td>
								<td align="left" valign="middle">
									<input type="text" name="person" value="" stateDisabled >
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">二级审核意见：</td>
								<td align="left" valign="middle">
									<input type="text" name="auditOpinion2" value="" addDisabled updateDisabled dataNull>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">备注：</td>
								<td align="left" valign="middle">
									<input type="text" name="mark" value="" updateNone addNone stateNone deleteNone dataNull>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">&nbsp;</td>
								<td align="left" valign="middle">
									<input name="" id="dataCommit" type="submit" value="确定">
									<input name="" id="dataCanCle" type="reset" value="取消"></td>
							</tr>
				    	</table>
				    </form>
			    </td>
		    </tr>
		</table>
	</div>
	
	<script type="text/javascript" src="js/careDevice.js" ></script>
	<script>
    $(function() {
    	<%
    		String name = request.getSession().getAttribute("name").toString();
    		if(name.equals("admin")) {
    	%>
    			var availableTags = <%=No.getAllJsonDepartmentIdAndName()%>
 		<%
    		}
    		else {
    	%>
    			var availableTags = <%=No.getByNoDepartmentIdAndName(name)%>
    	<%
    		}
    	%>
    	
	    $( "#findByNo" ).autocomplete({
	      source: availableTags
	    });
	    
        $( "#dateTime" ).datepicker({//添加日期选择功能  
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
	            var hour = currentDate.getHours();
	            var minutes = currentDate.getMinutes();
	            var seconds = currentDate.getSeconds();
	            selectedDate += " "+hour +":"+minutes+":"+seconds;
	            this.value = selectedDate;
            }  
          });  
      });
 	</script>
</div>

</body>
</html>