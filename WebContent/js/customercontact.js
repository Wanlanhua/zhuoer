window.onload = function() {
	
	var dataBoxDom = document.getElementById("dataBox");
	
	var dataCloseDom = document.getElementById("dataClose");
	
	var dataFormDom = dataBoxDom.getElementsByTagName("form")[0];
	
	var dataInputsDom = dataBoxDom.getElementsByTagName("input");
	
	//提交
	var dataCommitDom = document.getElementById("dataCommit");
	
	//取消
	var dataCanCleDom = document.getElementById("dataCanCle");
	
	//删除
	var deleteDom = document.getElementsByClassName("deleteData");
	
	//更新
	var updateDom = document.getElementsByClassName("updateData");
	
	//审核
	var stateDom = document.getElementsByClassName("stateData");
	
	var dataBoxTitle = document.getElementById("dataBoxTitle");
	
	dataCloseDom.onclick = function() {
		closeDataBox();
	};
	dataCanCleDom.onclick = function() {
		closeDataBox();
	}
	
	for(var i=0; i<updateDom.length; i++) {
		updateDom[i].onclick = function() {
			updateData(this, updateActionTarget);
		};
	}

	for(var i=0; i<stateDom.length; i++) {
		stateDom[i].onclick = function() {
			stateData(this, stateActionTarget);
		}
	}
	
	//删除
	for(var i=0; i<deleteDom.length; i++) {
		deleteDom[i].onclick = function() {
			if(confirm("确定要删除吗？")){
				return true;
			}
			else {
				return false;
			}
		};
	}
	
	//添加
	document.getElementsByClassName("add")[0].onclick = function() {
		addData(addActionTarget);
		recoverEvent();
	}
	
	stopE();
	
	function updateData(dom, updateActionTarget) {
		recoverForm();
		dataBoxTitle.innerHTML = "修改";
		showDataBox();
		var dataRoot = dom.parentNode.parentNode;
		var dataitems = dataRoot.getElementsByTagName("th");
		var idText = dataitems[1].innerHTML;
		transferenceData(dataitems);
		dataFormDom.setAttribute("action",updateActionTarget+"?id="+idText);
		for(var i=0; i<dataInputsDom.length-2; i++) {
			if(dataInputsDom[i].getAttribute("updateNone")=="") {
				dataInputsDom[i].parentNode.parentNode.style.display = "none";
			}
			if(dataInputsDom[i].getAttribute("updateDisabled")=="") {
				dataInputsDom[i].setAttribute("readonly","readonly");
				dataInputsDom[i].style.backgroundColor="rgba(128,128,128,.3)";
			}
		}
		
	}
	
	function addData(addActionTarget) {
		recoverForm();
		dataBoxTitle.innerHTML = "增加";
		showDataBox();
		dataFormDom.setAttribute("action",addActionTarget);
		for(var i=0; i<dataInputsDom.length-2; i++) {
			if(dataInputsDom[i].getAttribute("addNone")=="") {
				dataInputsDom[i].parentNode.parentNode.style.display = "none";
			}
			if(dataInputsDom[i].getAttribute("addDisabled")=="") {
				dataInputsDom[i].setAttribute("readonly","readonly");
				dataInputsDom[i].style.backgroundColor="rgba(128,128,128,.3)";
			}
		}
	}
	
	function stateData(dom,stateActionTarget) {
		recoverForm();
		dataBoxTitle.innerHTML = "审核";
		showDataBox();
		var dataRoot = dom.parentNode.parentNode;
		var dataitems = dataRoot.getElementsByTagName("th");
		var idText = dataitems[1].innerHTML;
		transferenceData(dataitems);
		dataFormDom.setAttribute("action",stateActionTarget);
		for(var i=0; i<dataInputsDom.length-2; i++) {
			if(dataInputsDom[i].getAttribute("stateNone")=="") {
				dataInputsDom[i].parentNode.parentNode.style.display = "none";
			}
			if(dataInputsDom[i].getAttribute("stateDisabled")=="") {
				dataInputsDom[i].setAttribute("readonly","readonly");
				dataInputsDom[i].style.backgroundColor="rgba(128,128,128,.3)";
			}
		}
		dataCommitDom.value="通过";
		dataCanCleDom.value="不通过";
		dataCommitDom.onclick = function() {
			dataFormDom.setAttribute("action",stateActionTarget+"?id="+idText+"&code=1");
		}
		dataCanCleDom.onclick = function() {
			dataFormDom.setAttribute("action",stateActionTarget+"?id="+idText+"&code=0");
			dataFormDom.submit();
			closeDataBox();
		}
	}
	
	function deleteData() {
		if(confirm("确定要删除吗？"))
			return true;
		else return false;
	}
	
	function showDataBox() {
		dataBoxDom.style.visibility = "visible";
		for(var i=0; i<dataInputsDom.length-2; i++) {
			dataInputsDom[i].parentNode.parentNode.style.display = "table-row";
		}
	}
	
	function closeDataBox() {
		dataBoxDom.style.visibility = "hidden";
		for(var i=0; i<dataInputsDom.length-2; i++) {
			dataInputsDom[i].parentNode.parentNode.style.display = "hidden";
		}
	}
	
	//点弹出层外取消弹出层
	function stopE() {
		var childBoxDom = dataBoxDom.getElementsByTagName("div")[0];
		dataBoxDom.onclick = function(ev) {
			var oEvent = ev || event;
			oEvent.cancelable = true;
			oEvent.stopPropagation();
		}
		
		childBoxDom.onclick = function(ev) {
			var oEvent = ev || event;
			oEvent.cancelable = true;
			oEvent.stopPropagation();
		}
	}
	
	function recoverEvent() {
		
		dataCommitDom.onclick = null;
		
		dataCloseDom.onclick = function() {
			closeDataBox();
		}
		
		dataCanCleDom.onclick = function() {
			closeDataBox();
		}
	}
	
	function recoverForm() {
		recoverEvent();
		var len = dataInputsDom.length;
		for(var i=0; i<len-2; i++) {
			dataInputsDom[i].value="";
			dataInputsDom[i].removeAttribute("readonly");
			dataInputsDom[i].style.backgroundColor ="white";
			//添加*号提示必填字段
			if(dataInputsDom[i].getAttribute("dataNull")==null){
				dataInputsDom[i].parentNode.setAttribute("class","dataNotNull");
			}
		}
		dataCommitDom.value="确定";
		dataCanCleDom.value="取消";
		
		var currentDate = new Date();
		var year = currentDate.getFullYear();
		var month = currentDate.getMonth()+1;
		var day = currentDate.getUTCDate();
		var hour = currentDate.getHours();
		var minutes = currentDate.getMinutes();
		var seconds = currentDate.getSeconds();
		
		var dateDom = document.getElementById("dateTime");
		if(dateDom) {
			dateDom.value=year+"-"+month+"-"+day+" "+hour+":"+minutes+":"+seconds;
		}
		
	}
	
	//传递数据
	function transferenceData(datadoms) {
		for(var i=0; i<datadoms.length; i++) {
			dataInputsDom[i].value=datadoms[i].innerHTML;
		}
		$("#dataBox select").val(datadoms[0].innerHTML);
	}
	
	dataFormDom.onsubmit = function() {
		var inputs = dataFormDom.getElementsByTagName("input");
		for(var i=0; i<inputs.length; i++) {
			var domAttr = inputs[i].getAttribute("dataNull");
			if(domAttr==null) {
				if(inputs[i].value=="") {
					inputs[i].style.border = "1px solid red";
					var parentDom = inputs[i].parentNode;
					var previous = parentDom.previousElementSibling;
					var context = previous.innerHTML;
					var length = context.length;
					context = context.substr(0,length-1);
					alert(context+"不允许为空");
					return false;
				}
				else {
					inputs[i].style.border = "1px solid #ebebeb"
				}
			}
		}
		return true;
	}
	
}
