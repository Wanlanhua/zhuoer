$(function() {
  //显示模态框
  function showDataBox() {
    $("#dataBox").css("visibility","visible");
  }
  //隐藏模态框
  function closeDataBox() {
    $("#dataBox").css("visibility","hidden");
  }
  //添加按钮
  var addDom = $("#addDom");
  //修改按钮
  var updateDom = $(".updateDom");
  //关闭按钮
  var dataClose = $("#dataClose");
  //取消按钮
  var resetDom = $("#dataCancle");
  dataClose.on("click",function() {
    closeDataBox();
  })
  resetDom.on("click",function() {
    closeDataBox();
  })
  addDom.on("click",function() {
   $("#dataBoxTitle").text("添加");
   $("#dataBox form").attr("action",addActionTarget);
   recoverFrom($(this))
   showDataBox();
  })
  updateDom.each(function() {
    $(this).on("click",function() {
      $("#dataBoxTitle").text("修改");
      recoverFrom($(this));
      showDataBox();
      transformData($(this));
      $("#dataBox form").attr("action",updateActionTarget+"?id="+$("#dataBox input[name='id']").val());
    })
  })
  //恢复表单
  function recoverFrom(clickDom) {
    var formDom = $("#dataBox");
    //所有input清空
    formDom.find("input[type='text']").each(function() {
      $(this).val("");
      $(this).css("border","#ebebeb 1px solid");
      $(this).removeAttr("disabled");
      $(this).parent().parent().css("display","table-row");
    })
    formDom.find("input[type='file']").each(function() {
      $(this).val("");
      $(this).css("border","#ebebeb 1px solid");
      $(this).removeAttr("disabled");
      $(this).parent().parent().css("display","table-row");
    })
    //所有textarea清空
    formDom.find("textarea").each(function() {
      $(this).val("");
      $(this).css("border","#ebebeb 1px solid");
      $(this).removeAttr("disabled");
      $(this).parent().parent().css("display","table-row");
    })
    formDom.find("img").each(function() {
      $(this).attr("src","")
      $(this).removeAttr("disabled");
      $(this).parent().parent().css("display","table-row");
    })
    domOpration(clickDom);
  }
  function domOpration(clickDom) {
    $("#dataBox input[type='submit']").parent().parent().prevAll().each(function(index) {
      var targetDom = $(this).find("td:last-child").children();
      if(clickDom.text() == "编辑") {
        if(targetDom.attr("updateDisabled")=="") {
          targetDom.attr("disabled","disabled");
        }
        if(targetDom.attr("updateNone")==""){
          targetDom.parent().parent().css("display","none");
        }
      }
      if(clickDom.text().indexOf("添加")!=-1) {
        if(targetDom.attr("addDisabled")=="") {
          targetDom.attr("disabled","disabled");
        }
        if(targetDom.attr("addNone")=="") {
          targetDom.parent().parent().css("display","none");
        }
      }
      if(targetDom.attr("dataNull")!=""&&targetDom.attr("type")!="file") {
        targetDom.parent().attr("class","dataNotNull");
      }
    });
    var id = $("select[name='department']").val();
	var locationhref = window.location.href;
    var end = locationhref.lastIndexOf("/");
	var locationhref = locationhref.substring(0,end);
	var url = locationhref+"/GetAreaNameByDepartmentId";
	var $that = $("select[name='department']");
  }
  //传递数据
  function transformData(clickDom) {
    var parentDom = clickDom.parent().parent();
    //拿到按钮这一行所有数据
    var childreDom = parentDom.children("th");
    var dataFrom = $("#dataBox form table tr:lt("+childreDom.length+")");
    var state = 0;
    dataFrom.each(function(index) {
      var targetDom = $(this).find("td:nth-child(2)").children();
      var content = $(childreDom[state]).text();
      if(targetDom.attr("type")!="file") {
        if(targetDom.attr("alt")==null) {
          targetDom.val(content);
        }
        state++;
      }
      else {
        state++;
      }
      if(index==15) {
    	  console.log($(this))
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
		        if(res!="0") {
		        	for(var i=0; i<arr.length-1; i++) {
		            	if(i%10==0 && i!=0) {
		            		html+="<br>"
		            	}
		            	var a = arr[i]
		            	if(content.indexOf(a)!=parseInt("-1")) {
		            		html+="<input type='checkbox' checked name='area' value="+arr[i]+">"+arr[i];
		            	}
		            	else {
		            		html+="<input type='checkbox' name='area' value="+arr[i]+">"+arr[i];
		            	}
		            	
		            }
		        	$that.parent().parent().next().find("td:nth-child(2)").html(html);
		        }
		      }
		   }); 
      }
    });
    
  }
  //提交数据
  $("#dataBox form").on("submit",function() {
    var flag = true;
    $("#dataBox input[type='submit']").parent().parent().prevAll().each(function(index) {
      var targetDom = $(this).find("td:last-child").children();
      if(targetDom.attr("dataNull")!="" && targetDom.attr("type")!="file" && targetDom.attr("name")!="quyu") {
        targetDom.css("border","1px solid red");
        if(targetDom.val()=="") {
          alert(targetDom.parent().prev().text()+"不能为空");
          flag = false;
        }
        else {
        	if(targetDom.attr("name")=="phone"){
        		 flag = isPoneAvailable(targetDom.val());
            }
            else if(targetDom.attr("name")=="cardID") {
            	flag = isCardNo(targetDom.val());
            }
            else {
              targetDom.css("border","#ebebeb 1px solid");
            }
        }
        var department = $("select[name='department']").val();
        if(department=="") {
        	alert("请选择部门，若没有请先去添加！");
        	flag = false;
        }
        return flag; 
      }
    });
    if(flag == true) {
      $("#dataBox input[type='text']").each(function() {
        $(this).removeAttr("disabled");
      })
      $("#dataBox select").each(function() {
        $(this).removeAttr("disabled");
      });
    }
    return flag;
  });
  function isPoneAvailable(poneInput) {  
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;  
    if (!myreg.test(poneInput)) { 
        alert("电话不正确"); 
        return false;  
    } else {  
        return true;  
    }  
  }
  function isCardNo(card) { 
    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X 
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
    if(!reg.test(card)) 
    { 
      alert("身份证号不正确");
      return false; 
    }
    else {
      return true;
    }
  }
  $(".deleteData").each(function(){
    $(this).on("click",function() {
      if(confirm("确定要删除吗？"))
        return true;
      else return false;
    })
  })
  $("select[name='department']").change(function() {
   var $that = $(this);
   var id = $(this).val();
   var locationhref = window.location.href;
   var end = locationhref.lastIndexOf("/");
   var locationhref = locationhref.substring(0,end);
   var url = locationhref+"/GetAreaNameByDepartmentId";
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
        	for(var i=0; i<arr.length; i++) {
            	if(i%10==0 && i!=0) {
            		html+="<br>"
            	}
            	html+="<input type='checkbox' name='area'>"+arr[i];
            }
        	$that.parent().parent().next().find("td:nth-child(2)").html(html);
        }
        /*else {
        	alert("该部门"+id+"暂无区域，请先去添加")
        	closeDataBox();
        }*/
      }
   }); 
  });
  //图像回显
  $("input[type='file']").each(function() {
    $(this).on("change",function() {
      var content = $(this).val();
      var fileDom = $(this); 
      var imgSize = 1024*1024;
      if(content!="") {
        var fileObj = fileDom.get(0).files[0];
        var size = fileObj.size;
        if(size<imgSize) {
          var imgSrc = window.URL.createObjectURL(fileObj);
          $("img").each(function() {
            $(this).attr("src",imgSrc)
          })
        }
        else {
            alert("文件超过1MB了，请重新选择");
        }
      }
    })
  });
  $("#main-tab span[name='upload'").each(function() {
	  var $that = $(this);
	  $(this).on("click",function() {
		  $("#uploadFile").css("visibility","visible");
		  var parent = $(this).parent().parent();
		  var card = parent.find("th").eq(5).text();
		  var id = parent.find("th").eq(1).text();
		  $("#uploadFile").find("input[name='cardID']").val(card);
		  $("#uploadFile").find("input[name='id']").val(id);
		  $("#uploadFile input[type='button']").off().on("click",function() {
		    var locationhref = window.location.href;
		    var end = locationhref.lastIndexOf("/");
		    var locationhref = locationhref.substring(0,end);
		    var url = locationhref+"/uploadImage";
		    var formData = new FormData($("#uploadFile form")[0]);
			   $.ajax({
				    type:"post",
				    async:false,
				    cache:false,
				    processData:false,
				    contentType:false,
			        url:url,
			        data:formData,
			        success:function(res) {
			        	if(res) {
			        		alert("修改成功");
			        		$("#uploadFile img").attr("src","");
			     		   $("#uploadFile").css("visibility","hidden");
			        	}
			        	else {
			        		alert("修改失败");
			        	}
			        }
			   }); 
		  })
	  });
  })
  $("#uploadFile input[type='reset']").on("click",function() {
	  $("#uploadFile").css("visibility","hidden");
	  $("#uploadFile img").attr("src","");
  })
});
