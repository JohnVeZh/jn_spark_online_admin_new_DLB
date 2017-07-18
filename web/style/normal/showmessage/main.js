var isInitLoad = 0;
var v_p_permissionDeptId = "";
var v_p_permissionUserId = "";



function clickRow(row){
	var rowcell = row.all;
	for(i=0;i<rowcell.length;i++){
		var tdcell = rowcell(i);
		if(tdcell.type=='checkbox'||tdcell.type=='radio')
			tdcell.click();
	}
}

function pasteData(){
	//event.returnValue = false;
	var obj = event.srcElement;
	var ele = document.getElementsByName(obj.name);
	if(ele.length<=1) return false;
	for(var j=0;j<ele.length;j++){
		if(ele[j]==obj) break;
	}
	var temp = window.clipboardData.getData("Text");
	var array = temp.split('\r\n');	
	if(array.length<=1) return false;	
	for(var i=0;i<array.length;i++){
		if(ele[j+i])
			ele[j+i].value = array[i];
	}
}

//字符串temp，左括号位置start，返回对应的右括号位置。无则返回0
function getRight(temp,start){
	if(arguments.length<2)return -1;
	if(typeof(arguments[0])!="string"||typeof(arguments[1])!="number")
		return -1;
	if(temp.length<(start+1)||temp.charAt(start)!='(') return -1;

	var zcount = 0,ycount=0;
	var ch,j=0;
	for(j=start;j<temp.length;j++){
		ch = temp.charAt(j);
		if(ch=='(') zcount++;
		else if(ch==')') ycount++;
		if(ycount!=0&&zcount==ycount) break;
	}
	if(zcount!=ycount) return -1;
	else return j;
}

function getJs(gs){
	if(!gs||!gs.length||gs.length==0) return "";
	var left,middle,right;
	var temp = gs;
	if(temp.substr(0,1)=='=') temp = temp.substr(1);
	var le = temp.indexOf('and(');
	var re;
	if(le!=-1){
		re = getRight(temp,le+3);
		if(re!=-1){
			var str = temp.substring(le+4,re);
			var tem = str.split(',');
			if(tem.length==2) 
				temp=temp.substring(0,le)+tem[0]+'&&'+tem[1]+temp.substring(re+1,temp.length);
		}
	}

	
	if(temp.indexOf('if(')==0&&temp.lastIndexOf(')')==temp.length-1){ 
		temp = temp.substring(3,temp.length-1);
		array = temp.split(',');
		if(array.length<2) return 'df='+temp+';';
		
		left=array[0];
		
		temp='';			
		for(var i=1;i<array.length;i++){
			if(temp!='') temp+=',';
			temp += array[i];
		} 
		
		if(array[1].indexOf('if(')==0){
			var zcount = 0,ycount=0;
			var ch,j=0;
			for(j=0;j<temp.length;j++){
				ch = temp.charAt(j);
				if(ch=='(') zcount++;
				else if(ch==')') ycount++;
				if(ycount!=0&&zcount==ycount) break;
			}
			if(zcount!=ycount) return 'df='+temp+';';
			else{
			 middle = temp.substring(0,j+1);
			 right = temp.substring(j+2);		
			}
		}else{
			middle = array[1];
			temp='';			
			for(var i=2;i<array.length;i++){
				if(temp!='') temp+=',';
				temp += array[i];
			} 
			right = temp;
		}
//		alert('left='+left);
//		alert('middle='+middle);
//		alert('right='+right);
		if(right=='') return 'if('+left+'){'+getJs(middle)+'}';
		else return 'if('+left+'){'+getJs(middle)+'}else{'+getJs(right)+'}';

	}
	else return 'df='+temp+';';

}

//为了处理指标公式中季度年度计算不同
function updateGs(gs){
	var temp = gs;
	le = temp.indexOf('(niandu:');
	if(le==-1) le = temp.indexOf('(jidu:');
	while(le!=-1){
		re = getRight(temp,le);
		if(re!=-1){
			var str = temp.substring(le+1,re);
			var tem = str.split(' ');
			if(tem.length==2){
				var isYue = false;
				var abc = tem[0].split(':');
				if(abc.length==2){
					if(abc[0]=='jidu'){
						if(isJidu()){
							isYue = true;
							temp = temp.substring(0,le)+abc[1]+temp.substring(re+1,temp.length);
						}
					}
					else if(abc[0]=='niandu'){
						if(isNiandu()){
							temp = temp.substring(0,le)+abc[1]+temp.substring(re+1,temp.length);
							isYue = true;
						}
					}
				}
				abc = tem[1].split(':');
				if(abc.length==2){
					if(abc[0]=='jidu'){
						if(isJidu()){
							temp = temp.substring(0,le)+abc[1]+temp.substring(re+1,temp.length);
							isYue = true;
						}
					}
					else if(abc[0]=='niandu'){
						if(isNiandu()){
							temp = temp.substring(0,le)+abc[1]+temp.substring(re+1,temp.length);
							isYue = true;
						}
					}
				}
				if(!isYue) temp = temp.substring(0,le)+'1'+temp.substring(re+1,temp.length);
			}
		}
		le = temp.indexOf('(niandu:');
		if(le==-1) le = temp.indexOf('(jidu:');
	}
	return temp;
}


function isNiandu(){
	var yue;
	var tbny = document.getElementById('tbny');
	if(tbny&&tbny.value.length==6) yue = tbny.value.substring(4,6);
	else{
		var date = new Date();
		yue = date.getMonth();
		if(yue==0) yue=12;
	}
	if(yue==12) return true;
	else return false;
}

function isJidu(){
	var yue;
	var tbny = document.getElementById('tbny');
	if(tbny&&tbny.value.length==6) yue = tbny.value.substring(4,6);
	else{
		var date = new Date();
		yue = date.getMonth();
		if(yue==0) yue=12;
	}
	if(yue==3||yue==6||yue==9) return true;
	else return false;
}



// Ajax共用方法
function AjaxSubmit(url, params, onComplete) {
	var __response;
	var __exception;
	
	var ajaxReqOnComplete = function(request) {
	
		var ajaxResponse = request.responseXML.selectSingleNode("//ajax-response");
		
   	if (ajaxResponse != null){
			var respElem  = request.responseXML.selectSingleNode("//ajax-response/result");
			var excepElem = request.responseXML.selectSingleNode("//ajax-response/exception");
			
			try {
				eval("__response = " + respElem.firstChild.nodeValue+";");			
			}
			catch(E){
				try {
					__response = respElem.firstChild.nodeValue;
				}
				catch(E){
					__response = null;
				}
			}				
			try{
				__exception = excepElem.firstChild.nodeValue;
			}
			catch(E){}
		}		
		return {results : __response, exception : __exception};
	};
	
	var ajaxReq = new Ajax.Request(
		url,
		{method : "post", asynchronous : (onComplete!=null), parameters : params, onComplete : ((onComplete!=null)?function(request) { var a = ajaxReqOnComplete(request); onComplete.apply(this, [a.parameters, a.results, a.exception]); }:null) }
	);
	
	if (onComplete==null) {
		
		return ajaxReqOnComplete(ajaxReq.transport);
	}
}

/**
 * 
 * @param cid     container id
 * @param url     动态网页的网址
 * @param params  网页参数
 * 
 *
 */
var DynamicLoadPage = function(cid, url, params, method) {
	try {
		$(cid).innerHTML = "&nbsp;";
	}
	catch(ex) {
	}
	
	try {
		var onFailed = function(request) {
			$(cid).innerHTML = "<span style='background-color:red;color:white'>Load Page Failed.....</span>";
		};
		var obj = new Ajax.Updater(cid, url, 
			{method: 'post', parameters: (params!=null)?params:"", evalScripts: true, onComplete : method, onFailure: onFailed }
		);
	}
	catch(ex) {
	}
};

// 级联菜单共用方法
function selectChange(oElement, tElemnt, url, param, isEmpty, emptyValue,defaultValue) {
	var tElemntObj=tElemnt.options;
	for (i=0;i<tElemntObj.length;i++) {
		tElemntObj.remove(i);
		i=i-1;
	}
	
	if (emptyValue != null && emptyValue != undefined) {
		var oOption=document.createElement("OPTION");
		oOption.text=emptyValue;
		oOption.value="";
		tElemnt.options.add(oOption);
	}
	
 	if (isEmpty != false) {
 		var oValue=oElement.value;
  	if (oValue=="") return;
 	}
  var ajaxResponse = AjaxSubmit(url, param);
  
	if (ajaxResponse.results == undefined || ajaxResponse.results == null || ajaxResponse.results == "") return false;
	setSelectOptions(ajaxResponse.results, tElemnt, defaultValue);
}

function setSelectOptions(results, element, defaultValue) {
	for (var i=0;i<results.length;i++) {
		var touserValue=results[i].id;
		var touserText=results[i].name;
		var oOption=document.createElement("OPTION");
		oOption.text=touserText;
		oOption.value=touserValue;
		element.options.add(oOption);
	}
	
	if (isInitLoad == 0) {
		element.value=defaultValue;
		isInitLoad = 1;
	}
}

// 选中所有select的值
function optionsSelected(element) {
	var selectedComs=element.options;
	for(var i=0;i<selectedComs.length;i++){
		selectedComs[i].selected = true;
	}
}

//添加一笔数据
function copyToList(from,to) //from表示:包含可选择项目的select对象名字, to表示:列出可选择项目的select对象名字 //你可以根据你的具体情况修改
{
  fromList = eval('document.forms[0].' + from);
  toList = eval('document.forms[0].' + to);
  if (toList.options.length > 0 && toList.options[0].value == ''){
    toList.options.length = 0;
  }
  for (i=0;i<fromList.options.length;i++){
    var current = fromList.options[i];
    if (current.selected){
      if (current.value == ''){
        continue;
      }
      txt = current.text;
      val = current.value;
      var StrSS="";
      var strr="";
      if(!empty(StrSS)) continue;
      if(toList.options.length==0){
       toList.options[0] = new Option(txt,val);
      }
      else{
       toList.options[toList.length] = new Option(txt,val);
      }
      fromList.options[i] = null;
    }
  }
}
// 添加所有数据
function copyAllToList(from,to) //from表示:包含可选择项目的select对象名字, to表示:列出可选择项目的select对象名字 //你可以根据你的具体情况修改
{
  fromList = eval('document.forms[0].' + from);
  toList = eval('document.forms[0].' + to);
  if (toList.options.length > 0 && toList.options[0].value == ''){
    toList.options.length = 0;
  }
  for (i=0;i<fromList.options.length;){
    var current = fromList.options[i];
    if (current.value == ''){
      continue;
      i++;
    }
    txt = current.text;
    val = current.value;
    if(toList.options.length==0){
      toList.options[0] = new Option(txt,val);
    }
    else{
      toList.options[toList.length] = new Option(txt,val);
    }
    fromList.options[i] = null;
  }
}

// 操作权限验证
function checkPermission(btnCode) {
	if (v_p_permissionDeptId == "" && v_p_permissionUserId == "") return true; 
	if (v_p_permissionDeptId != "") {
		var vperDeptValue = document.getElementById(btnCode+"_check_PreDepts").value;
		var arrValues = vperDeptValue.split(",");
		for (var i=0;i<arrValues.length;i++) {
			if (arrValues[i]==v_p_permissionDeptId) return true;
		}
	}
	if (v_p_permissionUserId != "") {
		var vperUserValue = document.getElementById(btnCode+"_check_PreUser").value;
		if (vperUserValue==v_p_permissionUserId) return true;
	}
	
	alert("无此操作的权限！");
	return false;
}

// 取得操作验证值
function permission_onclick(permissionDeptId,permissionUserId) {
	v_p_permissionDeptId = permissionDeptId;
	v_p_permissionUserId = permissionUserId;
}
  var http_request = false;
  
  function send_request(url,returnMethodName){
    http_request = false;
    if(window.XMLHttpRequest){
      http_request = new XMLHttpRequest();
      if(http_request.overrideMimeType){
        http_request.overrideMimeType('text/xml');
      }
    }else if(window.ActiveXObject){
      try{
        http_request = new ActiveXObject("Msxml2.XMLHTTP");
      }catch (e){
        try{
          http_request = new ActiveXObject("Microsoft.XMLHTTP");
        }catch (e){}
      }
    }
    if(!http_request){
      window.alert("不能创建XMLHttpRequest对象实例.");
      return false;
    }
    eval('http_request.onreadystatechange = '+returnMethodName);
    http_request.open("GET", url, true);
    http_request.send(null);
  }
//两数相除  
function accDiv(arg1,arg2){
    var t1=0,t2=0,r1,r2;
    try{t1=arg1.toString().split(".")[1].length}catch(e){}
    try{t2=arg2.toString().split(".")[1].length}catch(e){}
    with(Math){
        r1=Number(arg1.toString().replace(".",""))
        r2=Number(arg2.toString().replace(".",""))
        return (r1/r2)*pow(10,t2-t1);
    }
}
//数值相乘
function accMul(arg1,arg2)
{
    var m=0,s1=arg1.toString(),s2=arg2.toString();
    try{m+=s1.split(".")[1].length}catch(e){}
    try{m+=s2.split(".")[1].length}catch(e){}
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}
//数值相加
function accAdd(arg1,arg2){	
	if(arg1.toString().substr(0,1)=='-'&&arg2.toString().substr(0,1)=='-'){
		return accMul("-1",accAdd(arg1.toString().substr(1),arg2.toString().substr(1)));
	}
	else if(arg1.toString().substr(0,1)=='-'&&arg2.toString().substr(0,1)!='-')
		return accSub(arg2,arg1.toString().substr(1));
	else if(arg1.toString().substr(0,1)!='-'&&arg2.toString().substr(0,1)=='-')
		return accSub(arg1,arg2.toString().substr(1));
    var r1,r2,m;
    try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
    try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
    m=Math.pow(10,Math.max(r1,r2))
    return (arg1*m+arg2*m)/m
}
//数值相减
function accSub(arg1,arg2){
	 if(arg1==Infinity&&arg2) return -arg2;
	 else if(arg1&&arg2==Infinity) return arg1;
	 else if(arg1==Infinity&&arg2==Infinity) return 0;
     var r1,r2,m,n;
     try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
     try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
     m=Math.pow(10,Math.max(r1,r2));
     //last modify by deeka
     //动态控制精度长度
     n=(r1>=r2)?r1:r2;
     return ((arg1*m-arg2*m)/m).toFixed(n);
}

/*
 * 级联菜单共用方法
 * url： 查询下级菜单的路径
 * param: 参数
 * element: 下级菜单目标元素
 * sIndex: 开始下标
 */
function cascadeSelect(url, param, element, sIndex) {
	var eOptions=element.options;
	
	if (!(sIndex)) {
		sIndex = 0;
	}
	
	// 清除原来的下拉框中的数据
	for (var i = sIndex;i < eOptions.length; i++) {
		eOptions.remove(sIndex);
		i=i-1;
	}
	
	// 取得下拉框中的数据
  var ajaxResponse = AjaxSubmit(url, param);
	if (ajaxResponse.results) {
		// 新增下拉框中的数据
		ajaxResponse.results.each(function(rsObj){
			var vOption = document.createElement("OPTION");
			vOption.text=rsObj.name;
			vOption.value=rsObj.id;
			vOption.title=rsObj.name;
			element.options.add(vOption);
		});
	}
}