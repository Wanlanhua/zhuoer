<%@page import="com.zhuoer.customer_management.Bean.CustomerInfo"%>
<%@page import="com.zhuoer.customer_management.Bean.AreaInfo"%>
<%@page import="com.zhuoer.aJsonAPI.No"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.zhuoer.employeeAndCustomerInfo.entity.*" %>
<%@ page contentType="text/html;charset=utf-8"%> 
<% request.setCharacterEncoding("utf-8");%>   
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<%
	int thisPage=1;
	int pageSize=1;
	
	if(session.getAttribute("pageSizeEI")!=null)
	{
		pageSize=Integer.parseInt(session.getAttribute("pageSizeEI").toString());
	}
	if(session.getAttribute("thisPageEI")!=null)
	{
		thisPage=Integer.parseInt(session.getAttribute("thisPageEI").toString());
	}
	if(pageSize%15==0)
	{
		pageSize=pageSize/15;
	}
	else
	{
		pageSize=pageSize/15+1;
	}
	Map<String, String> map = (Map)request.getSession().getAttribute("operate");
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>主要内容区main</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/dataBox.css" />
<link rel="stylesheet" href="css/jquery.css">
<link rel="stylesheet" href="css/lightbox.css">
<script src="js/jquery.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/lightbox.js"></script>
<script>
	var addActionTarget = "<%=request.getContextPath()%>/InsertEmployeeInfo";
	var updateActionTarget = "<%=request.getContextPath()%>/UpdateEmployeeInfo";
</script>
</head>
<body>
<table width="150% !important" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：员工管理</td>
  </tr>
  <tr>
  <%
  	String classNone = "none";
  %>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		 <tr>
   		 <td width="80%" align="left" valign="middle">
	         <form method="post" action="<%=request.getContextPath()%>/GetEmployeeInfo">
	         	<span> 工号：</span>
	         	<input type="text" name="qname" value="" class="text-word">
	          <%
	         	if(map.get("find").equals("0"))
	         		classNone = "none";
	         	else classNone = "block";
	         %>
	         	<input  type="submit" style="display:<%=classNone%>;" name="submit1" value="查询" class="text-but">
	         </form>
         	</td>
         <%
	         if(map.get("input").equals("0"))
	      		classNone = "none";
	      	 else classNone = "table-cell";
         %>
  		  <td width="20%" align="center" valign="middle" style="text-align:right; width:150px;display:<%=classNone%>;"><a href="javascript:void(0);" target="mainFrame" onFocus="this.blur()" class="add" id="addDom">添加员工</a></td>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top"> 
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr  class="bggray">
        <th align="center" valign="middle" >用户名</th> 
        <th align="center" valign="middle" style="display:none" >id</th>
        <th align="center" valign="middle" >姓名</th>
        <th align="center" valign="middle" >性别</th>
        <th align="center" valign="middle" >生日</th>
        <th align="center" valign="middle" >身份证号</th>
        <th align="center" valign="middle" >入职时间</th>
        <th align="center" valign="middle" >离职时间</th>
        <th align="center" valign="middle" >联系人信息</th>
        <th align="center" valign="middle" >电话</th>
        <th align="center" valign="middle" >员工在职状态</th>
        <th align="center" valign="middle" >学历</th>
        <th align="center" valign="middle" style="display:none" >照片路径</th>
        <th align="center" valign="middle" >履历</th>
        <th align="center" valign="middle" >资格证书</th>
        <th align="center" valign="middle" >所属部门</th>
        <th align="center" valign="middle" >所属区域</th>
        <th align="center" valign="middle">照片</th>
        <%
        	if(map.get("modify").equals("1") || map.get("del").equals("1")) {
        		classNone = "block";
        	}
        	else classNone = "none";
        %>
		<th align="center" valign="middle" style="display:<%=classNone%>">操作</th>   
		
      </tr> 
      
     <%
     	List<EmployeeInfo> list=(List<EmployeeInfo>)request.getSession().getAttribute("listEI");
     	if( list != null ) {
     		for(int i=0; i<list.size(); i++) {
     	
      %>
      
      <tr class="bggray" onMouseOut="this.style.backgroundColor='#f9f9f9'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        
        <th align="center"  valign="middle" ><%=list.get(i).getNo() %></th>
       <th align="center" valign="middle" style="display:none" ><%=list.get(i).getId() %></th>
       	<th align="center" valign="middle" ><%=list.get(i).getName() %></th>
       	<th align="center" valign="middle" ><%=list.get(i).getSex() %></th>
        <th align="center" valign="middle" ><%=list.get(i).getBirthday() %></th>
        <th align="center" valign="middle" ><%=list.get(i).getCardID() %></th>
        <th align="center" valign="middle" ><%=list.get(i).getFactorytime() %></th>
        <th align="center" valign="middle" ><%=list.get(i).getLeavedate() %></th>
        <th align="center" valign="middle" ><%=list.get(i).getContacts() %></th>
        <th align="center" valign="middle" ><%=list.get(i).getPhone() %></th>
       	<th align="center" valign="middle" ><%=list.get(i).getState() %></th>
       	<th align="center" valign="middle" ><%=list.get(i).getSchrecord() %></th>
       
        <th align="center" valign="middle" ><%=list.get(i).getRecord() %></th>
        <th align="center" valign="middle" ><%=list.get(i).getCertificate() %></th>
        <th align="center" valign="middle" ><%=list.get(i).getDepartment() %></th>
        <th align="center" valign="middle" ><%=list.get(i).getArea() %></th>	
        <th align="center" valign="middle">
       		<a class="example-image-link" href="<%=request.getContextPath() %><%=list.get(i).getPath() %>" data-lightbox="example-set" data-title="<%=list.get(i).getName() %>">查看图片</a>
       		<span name="upload" style="cursor:pointer;">修改图片</span>
       	</th>
        <td align="center" valign="middle" class="borderbottom">
        	<%
	         	if(map.get("modify").equals("0"))
	         		classNone = "none";
	         	else classNone = "inline";
	         %>
        	<a href="javascript:void(0)" style="display:<%=classNone%>;" target="mainFrame" onFocus="this.blur()" class="updateDom">编辑</a>
        	<span class="gray" style="display:<%=classNone%>;">&nbsp;|&nbsp;</span>
        	<%
	         	if(map.get("del").equals("0"))
	         		classNone = "none";
	         	else classNone = "inline";
	         %>
        	<a href="<%=request.getContextPath() %>/DeleteEmployeeInfo?id=<%=list.get(i).getId() %>" style="display:<%=classNone%>;" target="mainFrame" onFocus="this.blur()" class="deleteData">删除</a>
        	<span class="gray" style="display:<%=classNone%>;">&nbsp;|&nbsp;</span>
        	<a href="<%=request.getContextPath() %>/customerDetails.jsp?<%=list.get(i).getId() %>" target="_blank" class="dt" style="cursor:pointer;">详情</a>
        </td>
      </tr>   
       <%   
       		}
     	}
        %>
    </table>
    </td>
    </tr>
 <% 
    	if(list!=null) {
    %>
		  <tr>
		    <td align="left" valign="top" class="fenye">
		    
		    	<%=list.size()%> 条数据 <%=thisPage %>/<%=pageSize %> 页&nbsp;&nbsp;
	    		<a href="<%=request.getContextPath() %>/GetEmployeeInfo?page=1" target="mainFrame" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
		    	<%
		    		int beforePage = thisPage - 1;
		    		if(thisPage>1) {
		    		
		    	%>
		    		<a href="<%=request.getContextPath() %>/GetEmployeeInfo?page=<%=beforePage %>" target="mainFrame" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;
		    	<% 
		    		}
		    	%>
		    	<%
		    		int afterPage = thisPage + 1;
		    		if(thisPage<pageSize) {
		    	%>
		    		<a href="<%=request.getContextPath() %>/GetEmployeeInfo?page=<%=afterPage %>" target="mainFrame" onFocus="this.blur()">下一页</a>&nbsp;&nbsp;
		    	<%
		    		}
		    	%>
		    	
		    	<%
		    		if(thisPage != pageSize && pageSize>1) {
		    	%>
		    		<a href="<%=request.getContextPath() %>/GetEmployeeInfo?page=<%=pageSize %>" target="mainFrame" onFocus="this.blur()">尾页</a></td>
		    	<%
		    		}
		    	%>
		  </tr>
		  
	<%
    	}
	%>
</table>

<div id="dataBox">
	
	<div style="text-align: center;width:50% !important;">
		<div class="data_head">
			<h2><span id="dataBoxTitle"></span>员工</h2>
			<span class="data_close" id="dataClose">x</span>
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr>
		    	<td align="left" valign="top">
			    	<form method="post" action="" enctype="multipart/form-data">
				    	<table width="90%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
				      
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">员工编号：</td>
								<td align="left" valign="middle">
									<input type="text" name="no" value="" updateDisabled class="dataNotNull">
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">id：</td>
								<td align="left" valign="middle">
									<input type="text" name="id" value="" addNone updateNone dataNull>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">姓名：</td>
								<td align="left" valign="middle">
									<input type="text" name="name" value="" >
								</td>
							</tr>

							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">性别：</td>
								<td align="left" valign="middle">
									<select name="sex" dataNull>
										<option value="男" >男</option>
										<option value="女">女 </option>
									</select>
								</td>
							</tr>

							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">生日：</td>
								<td align="left" valign="middle">
									<input type="text" name="birthday" readOnly="readOnly" id="dateTime1" value="" >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray sfzh">身份证号：</td>
								<td align="left" valign="middle">
									<input type="text" name="cardID" value="" >
								</td>
							</tr>
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">入职时间：</td>
								<td align="left" valign="middle">
									<input type="text" name="factorytime" readOnly="readOnly" id="factorytime" value="" >
								</td>
							</tr>

							

							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">离职时间：</td>
								<td align="left" valign="middle">
									<input type="text" name="leavedate" readOnly="readOnly" id="dateTime3" value="" dataNull >
								</td>
							</tr>

							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">联系人信息：</td>
								<td align="left" valign="middle">
									<input type="text" name="contacts" value="" deleteNone >
								</td>
							</tr>

							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray dh">电话：</td>
								<td align="left" valign="middle">
									<input type="text" name="phone" value="">
								</td>
							</tr>

							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">员工在职状态：</td>
								<td align="left" valign="middle">
									<select  name="state" dataNull>
										<option value="在职">在职</option>
										<option value="离职">离职 </option>
									</select>
								</td>
							</tr>	

							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">学历：</td>
								<td align="left" valign="middle">
									<select name="schrecord" value="" dataNull>
										<option>初中</option>
										<option>高中</option>
										<option>中专</option>
										<option>大专</option>
										<option>本科</option>
										<option>硕士</option>
										<option>博士</option>
									</select>
								</td>
							</tr>


							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">履历：</td>
								<td align="left" valign="middle" >
									<textarea rows="5" cols="43" name="record" style="resize:none" maxlength="299" dataNull>
									</textarea>
								</td>
							</tr>

							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">资格证书：</td>
								<td align="left" valign="middle">
									<input type="text" name="certificate" maxlength="100" value=""  dataNull>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">所属部门：</td>
								<td align="left" valign="middle">
									<select  name="department" dataNull>
							            <%
							            	List<CustomerInfo> alist = No.getDepartmentId();
							            	for(int i=0; i<alist.size(); i++) {
							            		if(i==0) {
							            %>  
							            			<option value="<%=alist.get(i).getDepartmentid() %>" selected><%=alist.get(i).getDepartmentname() %> </option>
							            <%
							            		}
							            		else {
							            			
				            			%>
				            					<option value="<%=alist.get(i).getDepartmentid() %>"><%=alist.get(i).getDepartmentname() %> </option>
				            			<%
							            		}
							            	}
							            %>
							        </select> 
								</td>
							</tr>

							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">所属区域：</td>
								<td align="left" valign="middle" name="quyu" dataNull>
									<script>
										var id = $("select[name='department']").val();
										var locationhref = window.location.href;
									    var end = locationhref.lastIndexOf("/");
										var locationhref = locationhref.substring(0,end);
										var url = locationhref+"/GetAreaNameByDepartmentId";
										var $that = $("select[name='department']");
									   $.ajax({
									    type:"get",
									      url:url,
									      async:false,
									      data:{"data":id},
									      success:function(res) {
									    	$that.parent().parent().next().find("td:nth-child(2)").html("");
									        var dt = res;
									        var arr = dt.split("-");
									        var thisNext = $that.parent().parent().next();
									        var html = "";
									        if(res!=false) {
									        	for(var i=0; i<arr.length-1; i++) {
									            	if(i%10==0 && i!=0) {
									            		html+="<br>"
									            	}
									            	html+="<input dataNull type='checkbox' name='area' value="+arr[i]+">"+arr[i];
									            }
									        	$that.parent().parent().next().find("td:nth-child(2)").html(html);
									        }
									      }
									   }); 
									</script>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">照片：</td>
								<td align="left" valign="middle" class="dataNotNull" style="padding: 10px 0;">
									<input type="file" name="path" accept="image/jpeg" id="uploadimg" style="visibility: hidden;width:10px;" dataNull updateNone>
									<label for="uploadimg" style="border: 1px solid rgb(235, 235, 235);display: inline-block;width: 315px;margin: 0 10px;">选择文件(不要超过1MB)</label>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">照片预览：</td>
								<td align="left" valign="middle" >
									<img src="" alt="预览图片" style="width:90px;height:160px;" dataNull updateNone>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">&nbsp;</td>
								<td align="left" valign="middle">
									<input name="" id="dataCommit" type="submit" value="确定">
									<input name="" id="dataCancle" type="reset" value="取消"></td>
							</tr>
				    	</table>
				    </form>
			    </td>
		    </tr>
		</table>
	</div>
</div>
<div style="text-align: center;" id="uploadFile">
	<div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
		    	<td align="left" valign="top">
			    	<form method="post" action="" enctype="multipart/form-data">
				    	<table width="90%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
				      		
				      		<tr style="display:none;" onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">id：</td>
								<td align="left" valign="middle">
									<input type="text" name="id" value="" addNone updateNone dataNull>
								</td>
							</tr>
				      		
							<tr style="display:none;" onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray sfzh">身份证号：</td>
								<td align="left" valign="middle">
									<input type="text" name="cardID" value="" updateDisabled>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">照片：</td>
								<td align="left" valign="middle" class="dataNotNull" style="padding: 10px 0;">
									<input type="file" name="path" accept="image/jpeg" id="uploadimg" style="width:10px;" dataNull updateNone>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">照片预览：</td>
								<td align="left" valign="middle" >
									<img src="" alt="预览图片" style="width:90px;height:160px;" dataNull updateNone>
								</td>
							</tr>
							
							<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
								<td align="right" valign="middle" class=" bggray">&nbsp;</td>
								<td align="left" valign="middle">
									<input name="" id="dataCommit" type="button" value="确定">
									<input name="" id="dataCancle" type="reset" value="取消">
								</td>
							</tr>
				    	</table>
				    </form>
			    </td>
		    </tr>
		</table>
	</div>
</div>
	<script type="text/javascript"  src="js/employeeinfo.js" >
	</script>
	<script>
	

	
    $(function() {
		
    	 $( "#dateTime1" ).datepicker({//添加日期选择功能  
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
    	 
    	 $( "#factorytime" ).datepicker({//添加日期选择功能  
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
    	 
           $( "#dateTime3" ).datepicker({//添加日期选择功能 
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
          
           lightbox.option({
      	      'resizeDuration': 200,
      	      'wrapAround': true
      	    })
           
      });
 </script>
</body>
</html>