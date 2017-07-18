/*===============================================================================*/
//document.write("<script type='text/javascript' src='../script/calendar.js'></script>"); //引入日历脚本
document.write("<div id='toolsDynamicDiv' class='oDynamic' onmouseout='hideDiv();'></div>"); //创建一个通用的层
/*===============================================================================*/
//mod为1改变当前行颜色，mod为0恢复当前行颜色
function changeRowColor(mod,color)
{
	var myElement;

	myElement = window.event.srcElement;
	if (mod == 0) {
		if (color == null) color = "#DDDDF4";
	} else {
		if (color == null) color = "#FFFFFF";
	}

	while (myElement.tagName != "TR")
	{
		myElement = myElement.parentElement;
		if (myElement.tagName == "TABLE") return;//防止死循环
	}

	if (mod == 0)//鼠标移出当前行
	{
		//恢复当前行的背景颜色
		myElement.style.backgroundColor = color;
	}
	else//鼠标移入当前行
	{
		//改变当前行背景颜色
		myElement.style.backgroundColor = color;
	}
}
/*===============================================================================*/
//设置obj的color
function setColor(obj,color)
{
	if (obj != null)
	{
		obj.style.color = color;
	}
}
/*===============================================================================*/
//设置obj的backgroundcolor
function setbgColor(obj,color)
{
	if (obj != null)
	{
		obj.style.backgroundColor = color;
	}
}
/*===============================================================================*/
//弹出一个日期选择窗口，进行日期选择
function selectDate(targetObj)
{
	if (targetObj == null) return;

	var selectedDate;
	selectedDate = openDialog('/crm/sygl/common/dateSelect.jsp',225,284);
	if (selectedDate == null||selectedDate == "") return;
	targetObj.value = selectedDate + targetObj.value.substring(10,targetObj.value.length);	
}
/*===============================================================================*/
//弹出一个人员选择窗口，进行人员选择
function selectPerson(targetObj,mode,role)
{
	if (targetObj == null) return;
	if (role == null) role = "5";

	var selectedPerson,retArray;
	selectedPerson = openDialog('/crm/sygl/common/person_select.jsp?mode='+mode+'&role='+role,400,500);
	if (selectedPerson == null||selectedPerson == "") return;
	if (mode == "single") targetObj.value = selectedPerson;
	else if (mode == "multi") targetObj.value += selectedPerson;
	else if (mode == "singleId") {
		retArray = selectedPerson.split("|");
		targetObj.value = retArray[1];
		targetObj.personId = retArray[0];
	}
	if (targetObj.postEvent != null) {
		eval(targetObj.postEvent);
	}
}
/*===============================================================================*/
//打开指定的页面，并使页面居中
function openWindow(url,windowWidth,windowHeight,openParam,windowName)
{
	var windowLeft,windowTop;
	
	if (url == null) return;
	if (windowWidth == null) windowWidth = 700;
	if (windowHeight == null) windowHeight = 500;
	if (windowWidth > screen.availWidth+8) windowWidth = screen.availWidth+8;
	if (windowHeight > screen.availHeight+4) windowHeight = screen.availHeight+4;

	windowLeft = (screen.availWidth - windowWidth - 10)/2; //绝对居中
	windowTop = (screen.availHeight - windowHeight - 35)/2; //绝对居中
	if (windowLeft < -4) windowLeft = -4;
	if (windowTop < -4) windowTop = -4;

	if (windowName == null) windowName = "popup"+genRandom();
	if (openParam == null) openParam = "resizable=no";
	
	openParam = "left="+windowLeft+",top="+windowTop+",width="+windowWidth+",height="+windowHeight+",location=no,menubar=no,scrollbars=no,status=no,toolbar=no,"+openParam;

	window.open(url,windowName,openParam);
}
/*===============================================================================*/
//打开指定的窗口，以非模态方式
function openDialogLess(url,dialogWidth,dialogHeight,param)
{
	var returnValue;

	if (url == null) return;
	if (url.indexOf("?") > 0)
		url += "&randomStr="+genRandom();
	else
		url += "?randomStr="+genRandom();

	if (dialogWidth == null) dialogwidth = "";
	if (dialogHeight == null) dialogHeight = "";

	returnValue = window.showModelessDialog(url,param,'dialogWidth:'+dialogWidth+'px;dialogHeight:'+dialogHeight+'px;help:no;status:no;scroll:no;');

	return returnValue;
}
/*===============================================================================*/
//打开指定的窗口，以模态方式
function openDialog(url,dialogWidth,dialogHeight,param)
{
	var returnValue;

	if (url == null) return;

	if (url.indexOf("?") > 0)
		url += "&randomStr="+genRandom();
	else
		url += "?randomStr="+genRandom();

	if (dialogWidth == null) dialogwidth = "";
	if (dialogHeight == null) dialogHeight = "";

	returnValue = window.showModalDialog(url,param,'dialogWidth:'+dialogWidth+'px;dialogHeight:'+dialogHeight+'px;help:no;status:no;scroll:no;');

	return returnValue;
}
/*===============================================================================*/
//扩展String类，添加trim方法
String.prototype.trim=function() {
	return this.replace(/(^\s*)|(\s*$)/g,"");
}


/*===============================================================================*/
//扩展String类，添加replaceAll方法
String.prototype.replaceAll=function(sString1,sString2) {
	var sString;
	sString = this;
	while (sString.search(sString1) != -1)
	{
		sString = sString.replace(sString1,sString2);
	}
	return sString;
}


/*===============================================================================*/
//模拟java的StringBuffer
function StringBuffer(sString) {
	this.length = 0;
	this.append = function(sString) {
		this.length += (this._parts[this._current++] = String(sString)).length;
		this._string = null;
		return this;
	}
	this.toString=function() {
		if(this._string!=null) return this._string;
		var s = this._parts.join("");
		this._parts = [s];
		this._current = 1;
		return this._string = s;
	}
	this._current = 0;
	this._parts = [];
	this._string = null;
	if(sString != null) this.append(sString);
}
/*===============================================================================*/
//对表单的数据进行验证，包括input、select、textarea三种元素，需在元素属性中指定dataType、dataName和dataNull
function checkForm(theForm) {
	var patternsDict = new Object();
	var patternsType = new Object();
	var allElements = theForm.elements;
	var i=0;
	var tempValue;
	
	patternsDict.date = /^\d{4}\-\d{1,2}\-\d{1,2}$/;  //判断日期的表达式，只对日期格式做判断，不对日期有效性进行验证.
	patternsDict.time = /^\d{1,2}\:\d{1,2}\:\d{1,2}$/; //判断时间的表达式，只对时间格式做判断，不对时间有效性进行验证.
	patternsDict.dateTime = /^\d{4}\-\d{1,2}\-\d{1,2}\s{1}\d{1,2}\:\d{1,2}\:\d{1,2}$/; //判断日期时间的表达式，只对时间格式做判断，不对时间有效性进行验证.
	patternsDict.integer = /^\-*\d+$/;  //判断整型变量
	patternsDict.decimal = /^\-*\d+(\.\d+)*$/; //判断浮点型变量
	
	patternsType.date = "日期，例如：\n    2003-07-18";
	patternsType.time = "24小时制时间，例如：\n    09:35:23";
	patternsType.dateTime = "日期 时间，例如：\n    2003-07-18 09:35:23";
	patternsType.integer = "整数型数字，例如：\n    32767";
	patternsType.decimal = "浮点型数字，例如：\n    3.14";
	
	for (i=0;i<allElements.length;i++) {
		allElements(i).value = allElements(i).value.trim();
		if (allElements(i).dataNull == "false") {
			if (allElements(i).value == "") { //不允许为空，但没有输入数据
				doAlertStr(i,"此处不允许为空，请输入一个有效的值");
				return false;
			}
		} else {
			if (allElements(i).value == "") continue; //允许为空且值为空，不作验证
		}
		if (!(allElements(i).dataType in patternsDict)) continue;//没有指定数据类型或数据类型不在验证的范围类
		if (patternsDict[allElements(i).dataType].test(allElements(i).value)) { //通过数据类型检查，进一步检查数据有效性
			if (allElements(i).dataType == "date") {
				tempValue = allElements(i).value.split("-");
				if (parseInt(tempValue[0]) == 0||parseInt(tempValue[1]) == 0||parseInt(tempValue[1]) > 12||parseInt(tempValue[2]) == 0|| parseInt(tempValue[2]) > 31) {
					doAlertStr(i,"这是一个无效的日期，请重新输入");
					return false;
				}
			}
			if (allElements(i).dataType == "time") {
				tempValue = allElements(i).value.split(":");
				if (parseInt(tempValue[0]) > 23||parseInt(tempValue[1]) > 59||parseInt(tempValue[2]) > 59) {
					doAlertStr(i,"这是一个无效的时间，请重新输入");
					return false;
				}
			}
			if (allElements(i).dataType == "dateTime") {
				tempValue = allElements(i).value.split(" ");
				tempValue = tempValue[0].split("-");
				if (parseInt(tempValue[0]) == 0||parseInt(tempValue[1]) == 0||parseInt(tempValue[1]) > 12||parseInt(tempValue[2]) == 0|| parseInt(tempValue[2]) > 31) {
					doAlertStr(i,"这是一个无效的日期，请重新输入");
					return false;
				}
				tempValue = allElements(i).value.split(" ");
				tempValue = tempValue[1].split(":");
				if (parseInt(tempValue[0]) > 23||parseInt(tempValue[1]) > 59||parseInt(tempValue[2]) > 59) {
					doAlertStr(i,"这是一个无效的时间，请重新输入");
					return false;
				}
			}
		} else { //没有通过数据类型检查
			doAlert(i);
			return false;
		}
	}
	
	function doAlert(i) //弹出错误信息提示
	{
		if (!allElements(i).dataName) //没有指定数据名称，用name属性代替
			alert("在["+allElements(i).name+"]中\n→您输入的数据是：\n    "+allElements(i).value+"\n→应该输入"+patternsType[allElements(i).dataType]);
		else
			alert("在["+allElements(i).dataName+"]中\n→您输入的数据是：\n    "+allElements(i).value+"\n→应该输入"+patternsType[allElements(i).dataType]);
			
		allElements(i).focus(); //让出错的对象获得焦点
	}
	
	function doAlertStr(i,errStr) //弹出错误信息提示
	{
		if (!allElements(i).dataName) //没有指定数据名称，用name属性代替
			alert("在["+allElements(i).name+"]中\n→您输入的数据是：\n    "+allElements(i).value+"\n→"+errStr);
		else
			alert("在["+allElements(i).dataName+"]中\n→您输入的数据是：\n    "+allElements(i).value+"\n→"+errStr);
			
		allElements(i).focus(); //让出错的对象获得焦点
	}
	return true;
}
/*===============================================================================*/
//显示一个动态的层，anchor弹出区域，dynamicDiv弹出的层，offsetX横向位移，offsetY纵向位移
//默认弹出层的位置在指定的弹出区域的正下方
function showDiv(anchor,displayText,offsetX,offsetY,offsetWidth,offsetHeight) {
	if (displayText == null||trim(displayText) == "") return;//若要显示的文本为空则返回
	if (offsetX == null) offsetX = 0;//默认x位移为0
	if (offsetY == null) offsetY = 0;//默认y位移为0
	if (offsetWidth == null) offsetWidth = 160;
	if (offsetHeight == null) offsetHeight = 50;

	if (toolsDynamicDiv != null)
	{
		toolsDynamicDiv.style.width = offsetWidth;
		toolsDynamicDiv.style.height = offsetHeight;
		toolsDynamicDiv.innerHTML = displayText;
		
		var dw = toolsDynamicDiv.offsetWidth;
		var dh = toolsDynamicDiv.offsetHeight;
		var aw = anchor.offsetWidth;
		var ah = anchor.offsetHeight;
		var x = anchor.offsetLeft + offsetX + aw*2/3;
		var y = anchor.offsetTop + offsetY - ah/2;
		var bw = document.body.clientWidth;
		var bh = document.body.clientHeight;
		var bsl = document.body.scrollLeft;
		var bst = document.body.scrollTop;
		var el = anchor;

		while (el.tagName != "BODY")//将x，y转化为绝对位置
		{
			el = el.offsetParent;
			x = x + el.offsetLeft;
			y = y + el.offsetTop;
		}
		if (x + dw > bw + bsl)
		{
			toolsDynamicDiv.style.left = x - dw - aw*2/3;
		}
		else
		{
			toolsDynamicDiv.style.left = x;
		}

		if (y + ah + dh > bh + bst)
		{
	 		toolsDynamicDiv.style.top = y + ah - dh;
		} 
		else
		{
			toolsDynamicDiv.style.top = y + ah;
		}
		toolsDynamicDiv.style.visibility = "visible";
	}
}
function showTrialDiv(anchor,sjxbm,ccz,scz,bhl,ycqk1,ycqk2,ycqk3) {
	offsetX = 0;//默认x位移为0
	offsetY = 0;//默认y位移为0
	offsetWidth = 160;
	offsetHeight = 50;

	if (toolsDynamicDiv != null) {
		toolsDynamicDiv.style.width = offsetWidth;
		toolsDynamicDiv.style.height = offsetHeight;
		strBuf = new StringBuffer();
		strBuf.append("<b>出厂值：</b>");
		strBuf.append(ccz);
		strBuf.append("<br><b>上次值：</b>");
		strBuf.append(scz);
		strBuf.append("<br><b>较上次变化率：</b>");
		strBuf.append(bhl);
		strBuf.append("<hr><b>提醒</b>");
		strBuf.append(ycqk1);
		strBuf.append("<br><b>规程</b>");
		strBuf.append(ycqk2);
		strBuf.append("<br><b>警告</b>");
		strBuf.append(ycqk3);
		strBuf.append("<br><img src=/crm/sygl/img/find20.gif align=absbottom><font class=textLink onclick=doCriterion('");
		strBuf.append(sjxbm);
		strBuf.append("')>查看判据...</font><hr><b>曲线对比</b><li><font class=textLink onclick=doCompareV('");
		strBuf.append(sjxbm);
		strBuf.append("')>试验数据纵向对比</font><li><font class=textLink onclick=doCompareH('");
		strBuf.append(sjxbm);
		strBuf.append("')>试验数据横向对比</font>");
		toolsDynamicDiv.innerHTML = strBuf.toString();
		
		var dw = toolsDynamicDiv.offsetWidth;
		var dh = toolsDynamicDiv.offsetHeight;
		var aw = anchor.offsetWidth;
		var ah = anchor.offsetHeight;
		var x = anchor.offsetLeft + offsetX + aw*2/3;
		var y = anchor.offsetTop + offsetY - ah/2;
		var bw = document.body.clientWidth;
		var bh = document.body.clientHeight;
		var bsl = document.body.scrollLeft;
		var bst = document.body.scrollTop;
		var el = anchor;

		while (el.tagName != "BODY") {
			el = el.offsetParent;
			x = x + el.offsetLeft;
			y = y + el.offsetTop;
		}
		if (x + dw > bw + bsl) {
			toolsDynamicDiv.style.left = x - dw - aw*2/3;
		} else {
			toolsDynamicDiv.style.left = x;
		}

		if (y + ah + dh > bh + bst) {
	 		toolsDynamicDiv.style.top = y + ah - dh;
		} else {
			toolsDynamicDiv.style.top = y + ah;
		}
		toolsDynamicDiv.style.visibility = "visible";
	}
}
/*===============================================================================*/
//隐藏弹出的动态层
function hideDiv()
{
	var toObj;
	toObj = window.event.toElement;
	if (toObj == null) return;
	if (toObj.id == "toolsDynamicDiv") return;
	while (toObj.tagName != "BODY") {
		toObj = toObj.parentElement;
		if (toObj.id == "toolsDynamicDiv") return;
	}
	toolsDynamicDiv.style.visibility = "hidden";
	toolsDynamicDiv.innerHTML = "";
}
/*===============================================================================*/
//产生一个随机数
function genRandom()
{
	var randomStr;
	randomStr = Math.random();
	randomStr = randomStr.toString().substring(2,randomStr.length);
	
	return randomStr;
}
/*===============================================================================*/
//产生网页�?
function genTitle(titleText) {
	document.write("<img src='/crm/sygl/img/document.gif'>");
	document.write("<div style='position:absolute;top:20px;left:40px;width:500;z-index:1;'><b>"+titleText+"</b></div>");
	document.write("<hr>");
}
/*===============================================================================*/
//计算指定的字符串应该显示的长�?
function calcWordLength(word) {
	var i = 0,retValue = 0,enCharCount = 0;
	
	for (i=0;i<word.length;i++) {
		if (word.substring(i,i+1) <= "~") enCharCount++;
	}
	retValue = (word.length - enCharCount/2 + 1)*13;
	return retValue;
}

//老函数，下一版本将去掉=============================
function trim(myString) {
	if (myString.length == 0) return "";
	
	return rtrim(ltrim(myString));
}

function ltrim(myString) {
	if (myString.length == 0) return "";
	
	var pos = myString.indexOf(" ");
	while (pos == 0) {
		myString = myString.substring(1);
		pos = myString.indexOf(" ");
	}
	return myString;
}
function rtrim(myString) {
	if (myString.length == 0) return "";
	
	var pos = myString.lastIndexOf(" ");
	while (pos == myString.length-1) {
		myString = myString.substring(0,myString.length-1);
		pos = myString.lastIndexOf(" ");
	}
	return myString;
}
//老函数，下一版本将去掉=============================
//生成级联下拉列表
function selectChange(control, controlToPopulate, ItemArray, GroupArray,ValueArray,bz)
{
  var myEle ;
  var x ; 
  if(bz==1)
  {
  for (var q=controlToPopulate.options.length;q>=0;q--) controlToPopulate.options[q]=null;  
	for ( x = 0 ; x < ItemArray.length  ; x++ )
		{
		  if ( GroupArray[x] == control.value )
			{
			  myEle = document.createElement("option") ;
			  myEle.value = ValueArray[x] ;
			  myEle.text = ItemArray[x] ;
			  controlToPopulate.add(myEle) ;
			}
		}
	  }
	  else
	  {
		for ( x = 0 ; x < GroupArray.length  ; x++ )
		{
		  if ( GroupArray[x] == control.value )
			{
			  controlToPopulate.value=ValueArray[x];
			}
		}
	  }  
}

function openurl(url,dialogWidth,dialogHeight)
{
	window.open (url, '', 'height='+dialogHeight+', width='+dialogWidth+', top=30, left=55, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no') 
}
