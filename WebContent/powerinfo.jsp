<%@page import="java.util.Map"%>
<%@page import="com.zhuoer.aJsonAPI.No"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Map<String, String> map = (Map)request.getSession().getAttribute("operate");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主要内容区main</title>
<link href="css/css.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/dataBox.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
</head>
<body>
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td width="99%" align="left" valign="top">您的位置：权限管理</td>
  </tr>
  <tr>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		<tr>
  		<%
  			String classNone = "none";
  		%>
   		 <td width="90%" align="left" valign="middle">
	         <form method="post" action="">
	         <span>登录号：</span>
	         <input type="text" name="" value="" class="text-word ui-widget" id="findByNo">
	          <%
	         	if(map.get("find").equals("0"))
	         		classNone = "none";
	         	else classNone = "block";
	         %>
	         <input name="" type="button" style="display:<%=classNone%>;" value="查询" class="text-but" id="submit">
	         </form>
         </td>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
		
		<tr  class="bggray">
        <th align="center" valign="middle" >登陆号</th>
        <th align="center" valign="middle" >姓名</th>
        <th align="center" valign="middle" >模块</th>
        <th align="center" valign="middle" >权限</th>
        <%
        	if(map.get("modify").equals("1") || map.get("del").equals("1")) {
        		classNone = "block";
        	}
        	else classNone = "none";
        %>
        <th align="center" valign="middle" style="display:<%=classNone%>">操作</th>
      </tr>
    </table>
    </td>
    </tr>
    <tr >
    <td height="200"></td>
    </tr>
</table>

<script>
	$(function() {
		var basePath = "<%=request.getContextPath()%>";
		var defaultDom = $("#main-tab");
		var defaultHTML = defaultDom.html();
	    var availableTags = <%=No.getJsonNo()%>
	    $( "#findByNo" ).autocomplete({
	      source: availableTags
	    });
			
			$("#submit").click(function() {
				$.ajax({
					type:"post",
					url:basePath+"/GetJsonPower",
					dataType:"json",
					data:{"no":$("#findByNo").val()},
					//success begin 
					success:function(res) {
						var data = res;
						var parentDom = defaultDom;
						parentDom.html(defaultHTML);
						var defaultHtml = parentDom.html();
						var template = "";
						var index = 3;
						for(var no in data["model"]) {
							console.log(no)
							console.log(data["name"][no])
							template += '<tr >'
								       +'<th align="center" valign="middle" >'+no+'</th>'
								       +' <th align="center" valign="middle" >'
								       +' <span>'+data["name"][no]+'</span>'
								       +' </th>'
								       +' <th align="center" valign="middle" >'
								       +' 	<input type="checkbox" name="model" id=m001'+index+' dataid="m001" data='+data["model"][no]["m001"]+'>'
								       +' 	<label for="m001'+index+'">事务提醒</label>'
								       +' 	<input type="checkbox" name="model" id=m002'+index+' dataid="m002" data='+data["model"][no]["m002"]+'>'
								       +' 	<label for="m002'+index+'">用户管理</label>'
								       +' 	<input type="checkbox" name="model" id=m003'+index+' dataid="m003" data='+data["model"][no]["m003"]+'>'
								       +' 	<label for="m003'+index+'">项目部门</label>'
								       +' 	<input type="checkbox" name="model" id=m004'+index+' dataid="m004" data='+data["model"][no]["m004"]+'>'
								       +' 	<label for="m004'+index+'">设备信息</label>'
								       +' 	<input type="checkbox" name="model" id=m005'+index+' dataid="m005" data='+data["model"][no]["m005"]+'>'
								       +' 	<label for="m005'+index+'">维修保养</label>'
								       +' 	<input type="checkbox" name="model" id=m006'+index+' dataid="m006" data='+data["model"][no]["m006"]+'>'
								       +' 	<label for="m006'+index+'">看护管理</label>'
								       +' 	<input type="checkbox" name="model" id=m007'+index+' dataid="m007" data='+data["model"][no]["m007"]+'>'
								       +' 	<label for="m007'+index+'">数据统计</label>'
								       +' 	<input type="checkbox" name="model" id=m008'+index+' dataid="m008" data='+data["model"][no]["m008"]+'>'
								       +' 	<label for="m008'+index+'">操作日志</label>'
								       +' </th>'
								       +' <th align="center" valign="middle" >'
								       +' 	<input type="checkbox" name="power" id="input'+index+'" dataid="input" data='+data["power"][no]["input"]+'>'
								       +' 	<label for="input'+index+'">添加</label>'
								       +' 	<input type="checkbox" name="power" id="modify'+index+'" dataid="modify" data='+data["power"][no]["modify"]+'>'
								       +' 	<label for="modify'+index+'">修改</label>'
								       +' 	<input type="checkbox" name="power" id="auditing'+index+'" dataid="auditing" data='+data["power"][no]["auditing"]+'>'
								       +' 	<label for="auditing'+index+'">一级审核</label>'
								       +' 	<input type="checkbox" name="power" id="auditing2'+index+'" dataid="auditing2" data='+data["power"][no]["auditing2"]+'>'
								       +'	<label for="auditing2'+index+'">二级审核</label>'
								       +' 	<input type="checkbox" name="power" id="find'+index+'" dataid="find" data='+data["power"][no]["find"]+'>'
								       +' 	<label for="find'+index+'">查询</label>'
								       +' 	<input type="checkbox" name="power" id="del'+index+'" dataid="del" data='+data["power"][no]["del"]+'>'
								       +' 	<label for="del'+index+'">删除</label>'
								       +' </th>'
								       +' <td align="center" valign="middle" class="borderbottom">'
								       +' 	<button class="layui-btn layui-btn-normal">保存</button>'
								       +' </td>'
								       +' </tr>'
								       index++;
						}
						
						parentDom.html(defaultHtml+template);
						
						$("#main-tab input").each(function() {
							var that = $(this);
							var flag = that.attr("data")
							if(flag == 1) {
								that.attr("checked","checked")
							}
						})
						
						$("#main-tab tr").mouseout(function() {
							$(this).css("background","#ffffff");
						})
						$("#main-tab tr").mouseover(function() {
							$(this).css("background","#edf5ff");
						})
						
						$("button").click(function() {
				    	var user = encapsulatedPower($(this).parent().parent().find("input"))
				    	$.ajax({
				    		url: basePath+'/UpdateJsonPower',
				    		type: 'post',
				    		dataType: 'json',
				    		data: user,
				    		success:function(res) {
				    			if(!res) {
				    				alert("修改失败")
				    				history.go(0);
				    			}
				    			else {
				    				alert("修改成功")
				    			}
				    		},
				    		error:function(res) {
				    			console.log(res)
				    		}
			    	});
				   });
					},
					//success end
					error:function(data) {
					}
				});
			
			})
			
	    function encapsulatedPower($dom) {
	    	var UserPower = {
	    		no:"",
    			m001:"",
	    		m002:"",
	    		m003:"",
	    		m004:"",
	    		m005:"",
	    		m006:"",
	    		m007:"",
					input:"",
	    		modify:"",
	    		auditing:"",
	    		auditing2:"",
	    		find:"",
	    		del:""
	    	}
	    	UserPower["no"] = $dom.parent().parent().children("th:first-child").text();
	    	$dom.each(function() {
	    		if($(this).is(":checked")) {
	    			UserPower[$(this).attr("dataid")] = "1";
	    		}
	    		else {
	    			UserPower[$(this).attr("dataid")] = "0";
	    		}
	    	})
	    	return UserPower;
	    }
		
		
	  });
</script>	
</body>
</html>