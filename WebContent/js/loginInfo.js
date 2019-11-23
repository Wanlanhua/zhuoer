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
	
	
	var dataBoxTitle = document.getElementById("dataBoxTitle");
	
	var selectDom = document.getElementsByTagName("select");
	
	selectDom[0].onchange = function() {
		var that = this;
		var thatSelect = selectDom[1];
		thatSelect.innerHTML = "";
		if(this.value==0) {
			for(var i=0; i<customer.length; i++) {
				thatSelect.innerHTML += "<option value="+customer[i].no+">"+customer[i].no +" "+customer[i].name+"</option>"
			}
		}
		else {
			for(var i=0; i<employee.length; i++) {
				thatSelect.innerHTML += "<option value="+employee[i].no+">"+employee[i].no +" "+employee[i].name+"</option>"
			}
		}
	}
	
	dataCloseDom.onclick = function() {
		closeDataBox();
	};
	dataCanCleDom.onclick = function() {
		closeDataBox();
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

	for(var i=0; i<updateDom.length; i++) {
		updateDom[i].onclick = function() {
			updateData(this, updateActionTarget);
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
		recoverEvent();
		dataBoxTitle.innerHTML = "修改";
		showDataBox();
		var dataRoot = dom.parentNode.parentNode;
		var dataitems = dataRoot.getElementsByTagName("th");
		var idText = dataitems[3].innerHTML;
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
		for(var i=0; i<selectDom.length;i++) {
			if(selectDom[i].getAttribute("deleteDisabled")=="") {
				selectDom[i].setAttribute("disabled","disabled");
				selectDom[i].style.backgroundColor="rgba(128,128,128,.3)";
				selectDom[i].style.color="white";
			}
		}
		
	}
	
	function addData(addActionTarget) {
		recoverForm();
		recoverEvent();
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
	
	function deleteData() {
		if(confirm("确定要删除吗？")){
			return true;
		}
		else {
			return false;
		}
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
			if(dataInputsDom[i].getAttribute("dataNull")==null){
				dataInputsDom[i].parentNode.setAttribute("class","dataNotNull");
			}
		}
		for(var i=0; i<selectDom.length; i++) {
			selectDom[i].removeAttribute("disabled");
			selectDom[i].style.backgroundColor = "white";
			selectDom[i].style.color="black";
		}
		dataCommitDom.value="确定";
		dataCanCleDom.value="取消";
	}
	
	//传递数据
	function transferenceData(datadoms) {
		for(var i=0; i<dataInputsDom.length-2; i++) {
			dataInputsDom[i].value=datadoms[i+2].innerHTML;
		}
		selectDom[0].value= datadoms[0].innerHTML;
		selectDom[1].value= datadoms[1].innerHTML;
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
