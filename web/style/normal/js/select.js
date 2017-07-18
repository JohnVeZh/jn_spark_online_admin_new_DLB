function  loaddate()
{
var dd = new Date();
var year = dd.getYear();
var month = dd.getMonth() + 1;
var days = dd.getDate();
if (month <10)
   month ="0"+month;
if (days< 10)
    {
    days = "0"+ days;   
    }
return(year+"-"+month+"-"+days);
}
function loadMonth(month1)
{
var dd = new Date();
var year = dd.getYear();
var month = dd.getMonth() + 1;
if (month <10)
   month ="0"+month;
return(month);
}
function loadDay(day1)
{
var dd = new Date();
var year = dd.getYear();
var day = dd.getDate();

if (day <10)
   day ="0"+day;
return(day);
}

function convert(ss,split1,split2)
{ 
  var Rstr="";
  var Str_array=ss.split(split1);

  for (j=0;j<Str_array.length;j++) 
  {
     Rstr = Rstr + Str_array[j];
     if (j < (Str_array.length-1)) {Rstr = Rstr + split2;}
  }
  if (ss==parseFloat(ss)) Rstr = ss;
  return Rstr;
}

//日期
function isBadDate(ss)
{
    var ite="";
    ite = ss.split("-");
    if (ite.length!=3) return true;
    for (i=0;i<3;i++)
        if (isNaN(ite[i])) return true;
    if ((ite[0]<1900)||(ite[0])>9999||(ite[1]<1)||(ite[1]>12)||(ite[2]<1)||(ite[2]>31)) 
        return true;
    m = parseInt(ite[1]);
    if ((m==2)&&(ite[2]>29)) return true;
       if( (m==2) && ite[2]==29)
          {
               y1=ite[0]%4;
               y2=ite[0]%100;
               y3=ite[0]%400;
               if (!(y1==0 && (y2!=0 || y3==0) ))return true;
          }
    if (((m==4)||(m==6)||(m==9)||(m==11))&&(ite[2]>30)) return true;
    return false;
}
//字符串
function isEmpty(theStr)
{ if ((theStr == null) ||(theStr.length ==0 )) 
            return true
  else return(false);
}
function isBetween(val,lo,hi)
{
if ((val < lo) || (val > hi))  
{return(false);}
else 
{return(true);}
}

function isCompare(lo,hi)
{
if (hi > lo)   
{return(true);}
else 
{return(false);}
}
//字符串
function isTime(theStr){
	var colonDex = theStr.indexOf(':');
	if ((colonDex<1) || (colonDex>2)) {return(false);}
	else {
		var hh= theStr.substring(0,colonDex);
		var ss= theStr.substring(colonDex+1,theStr.length);
		if ((hh.length<1) ||(hh.length>2) ||(!isInt(hh))) {return(false);}
		else if ((ss.length<1) || (ss.length>2) || (!isInt(ss))) {return(false);}
		else if ((!isBetween(hh,0,23)) || (!isBetween(ss,0,59))) {return(false);}
		else {return(true);}
	}
}
//Email
function isEmail(theStr) {
     var regu = "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]{2}|net|NET|com|COM|gov|GOV|mil|MIL|org|ORG|edu|EDU|int|INT)$"
     var re = new RegExp(regu);
          if (isEmpty(theStr)) {return(true);}
          if ((theStr.search(re) == -1)  )
          {return(false);}
          else
          {return(true);}
        }
 //整数       
function isInt(theStr){
var flag = true;
    
for (var i=0;i<theStr.length;i++){
    	
if ((theStr.substring(i,i+1)>=0) && (theStr.substring(i,i+1)<=9))
{
flag = true;
}
else
{
	
flag=false;
break;
}
             }
return(flag);
    }

//报表排序
function JM_jumpPage(direct,page,rows){
page=(direct=='up')?page-1:page+1;
document.location='openings.php?page0='+page+'&rowsLimit='+rows;
}
function JM_HLTr(hStyle){
eSrcObject=event.srcElement
if (eSrcObject.tagName=="TABLE")
return
while(eSrcObject.tagName!="TR"&&eSrcObject.onyes!='head')
eSrcObject=eSrcObject.parentElement
if (eSrcObject.className!=hStyle&&eSrcObject.onyes!='head'&&eSrcObject.id!="ignore"&&eSrcObject.className!='delStyle'&&eSrcObject.className!='listTableHead'&&eSrcObject.className!='listTableHead0'){
eSrcObject.className=hStyle}
if (eSrcObject.onyes=='head'&&eSrcObject.className!='listHeadClicked'&&eSrcObject.className!='listHeadClicked0'){
eSrcObject.className='listTableHeadO';
}
}
function JM_HLTrRestore(sStyle){
if (event.fromElement.contains(event.toElement)||eSrcObject.contains(event.toElement)||eSrcObject.id=="ignore"||eSrcObject.className=='delStyle')
return
if (event.toElement!=eSrcObject){
if (event.srcElement.onyes=='head'&&eSrcObject.className!='listHeadClicked'&&eSrcObject.className!='listHeadClicked0'){
event.srcElement.className='listTableHead'}
else if(eSrcObject.className!='listHeadClicked'&&eSrcObject.className!='listHeadClicked0'){
eSrcObject.className=sStyle}
}
}
function JM_PowerList(colNum){
headEventObject=event.srcElement
while(headEventObject.tagName!="TR"){
headEventObject=headEventObject.parentElement}
for (i=0;i<headEventObject.children.length;i++){
if (headEventObject.children[i]!=event.srcElement){
headEventObject.children[i].className='listTableHead'}
}
var tableRows=0;
trObject=DataTable.children[0].children
for (i=0;i<trObject.length;i++){
Object=DataTable.children[0].children[i];
tableRows=(trObject[i].id=='ignore')?tableRows:tableRows+1;
}
var trinnerHTML=new Array(tableRows)
var tdinnerHTML=new Array(tableRows)
var tdNumber=new Array(tableRows)
var i0=0
var i1=0
for (i=0;i<trObject.length;i++){
if (trObject[i].id!='ignore'){
trinnerHTML[i0]=trObject[i].innerHTML;
tdinnerHTML[i0]=trObject[i].children[colNum].innerHTML;
tdNumber[i0]=i;
i0++;
}
}
sourceHTML=clearStart.children[0].outerHTML;
//alert(sourceHTML);
for (bi=0;bi<tableRows;bi++){
for (i=0;i<tableRows;i++){
if(tdinnerHTML[i]>tdinnerHTML[i+1]){
t_s=tdNumber[i+1];
t_b=tdNumber[i];
tdNumber[i+1]=t_b;
tdNumber[i]=t_s;
temp_small=tdinnerHTML[i+1];
temp_big=tdinnerHTML[i];
tdinnerHTML[i+1]=temp_big;
tdinnerHTML[i]=temp_small;
}
}
}
var showshow='';
var numshow='';
for (i=0;i<tableRows;i++){
showshow=showshow+tdinnerHTML[i]+'\n';
numshow=numshow+tdNumber[i]+'|';
}
sourceHTML_head=sourceHTML.split("<TBODY>");
numshow=numshow.split("|");
var trRebuildHTML='';
if (event.srcElement.className=='listHeadClicked'){
for (i=0;i<tableRows;i++){
trRebuildHTML=trRebuildHTML+trObject[numshow[tableRows-1-i]].outerHTML;

}
event.srcElement.className='listHeadClicked0';
}else{
for (i=0;i<tableRows;i++){
trRebuildHTML=trRebuildHTML+trObject[numshow[i]].outerHTML;
}
event.srcElement.className='listHeadClicked';
}
var DataRebuildTable='';
DataRebuildTable=sourceHTML_head[0]+trObject[0].outerHTML+trRebuildHTML+trObject[tableRows+1].outerHTML+'</TABLE>';
clearStart.innerHTML='';
clearStart.innerHTML=DataRebuildTable;
}
//键盘输入
  function MM_keypress(oElement){
	  var sFilter=oElement.getAttribute("filter");
	  if(sFilter){
	   var sKey=String.fromCharCode(event.keyCode);
	   var re=new RegExp(sFilter);
	   // Do not filter out ENTER!
	   if(sKey!="\r" && !re.test(sKey))
	     event.returnValue=false;
	   event.keyCode=sKey.charCodeAt(0);
	  }
	}
//打开模式窗口
function openModalDialog(url,width,height)
 {
    window.showModalDialog(url,window,"dialogHeight: "+height+"px; dialogWidth: "+width+"px; dialogTop: 150px; dialogLeft: 150px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
}
//打开无模式窗口	
function openModelessDialog(url,width,height)
{
   window.showModelessDialog(url,window,"dialogWidth: "+width+"px;dialogHeight: "+height+"px;dialogLeft:200px;dialogTop:150px;center:Yes;help:No;resizable:No;status:No;");
}
function openDefaultDialog(url)
{
	var left = left;
	var top = top;
    window.showModalDialog(url,window,"dialogHeight: 300px; dialogWidth: 370px; dialogTop: "+top+"px; dialogLeft: "+left+"px; edge: Raised; center: Yes; help: No; resizable: No; status: No;");
}

if(document.all.Layer1==null) 
{
  document.write("<DIV onmousedown=MDown(Layer1) title='选中标题栏可拖动' id=Layer1 style='Z-INDEX: 100; LEFT: 0px; VISIBILITY: hidden; WIDTH: 0px; POSITION: absolute; TOP: 0px; HEIGHT: 0px; BACKGROUND-COLOR: #d4d0c8'></DIV>");
}


var Obj=''
document.onmouseup=MUp
document.onmousemove=MMove

function MDown(Object){
Obj=Object.id
document.all(Obj).setCapture()
pX=event.x-document.all(Obj).style.pixelLeft;
pY=event.y-document.all(Obj).style.pixelTop;
}

function MMove(){
if(Obj!=''){
	document.all(Obj).style.left=event.x-pX;
	document.all(Obj).style.top=event.y-pY;
	}
}

function MUp(){
if(Obj!=''){
	document.all(Obj).releaseCapture();
	Obj='';
	}
}



function openURL(input1, input2, funcionURL,functionName)
{
  if (input1 == null || input1=="" || input2==null || input2=="") return;
  var input1Val = document.all(input1).value;
  var input2Val = document.all(input2).value;
  var ctl = document.all(input2);
  var width=370;
  var height=260;
  var left=0;
  var top=0;
  var posLeft = ((ctl.offsetLeft + left)< 10 ? 10 : (ctl.offsetLeft + left));
  var posTop = ((ctl.offsetTop + ctl.offsetHeight + 2 + top) < 10 ? 10 : (ctl.offsetTop + ctl.offsetHeight + 2 + top));
  posTop = posTop - height;
  posTop = posTop<0?10:posTop;
  posLeft = posLeft-width;
  posLeft = posLeft<0?10:posLeft;
  Layer1.style.left= posLeft;
  Layer1.style.top = posTop;
  Layer1.style.width=width;
  Layer1.style.height=height;
  //Layer1.innerHTML="<table width=370 height=260 border=0 style='BACKGROUND-COLOR: #5a92de' cellpadding=1px cellspacing=0><tr><td><table border=0 cellpadding=0 cellspacing=0 height=18><tr><th height=18 onMouseDown=initializedragie(Layer1) style=cursor:hand width=100% style='BACKGROUND-COLOR: #5a92de' onselectstart='return false'><layer width=100% onMouseOver=dragswitch=1;drag_dropns(Layer1) onMouseOut=dragswitch=0 left=-6 top=2 onselectstart='return false'><font color=#FFFFFF>"+functionName+"</font></layer></th><td onselectstart='return false'><img id=img_close src=images/close.gif style='border:1px outset;' onclick=close_window();></td></tr></table><table cellpadding=0 cellspacing=0 border=0 width=100%><tr><td style=padding:1px width=100% height=260><iframe name=subwindow width=100% height=100% noresize scrolling=auto frameborder=5 marginheight=0 marginwidth=0   src='"+funcionURL+(funcionURL.indexOf("?")>0?"&":"?")+"input1="+input1+"&input2="+input2+"&input1Val="+input1Val+"&input2Val="+input2Val+"'></iframe></td></tr></table></td></tr></table><iframe src='javascript:false' style='position:absolute; visibility:inherit; border:0px; top:0px; left:0px; width:370px; height:260px; z-index:-1; filter='progid:XImageTransform.Microsoft.Alpha(style=0,opacity=0)';'></iframe>";
  
    Layer1.innerHTML="<table width=370 height=260 border=0 style='BACKGROUND-COLOR: #56B5A9' cellpadding=1px cellspacing=0><tr><td><table border=0 cellpadding=0 cellspacing=0 height=18><tr><th height=18 style=cursor:hand width=100% style='BACKGROUND-COLOR: #56B5A9' onselectstart='return false'><layer width=100% onMouseOver=dragswitch=1;drag_dropns(Layer1) onMouseOut=dragswitch=0 left=-6 top=2 onselectstart='return false'><font color=#FFFFFF>"+functionName+"</font></layer></th><td onselectstart='return false'><img id=img_close src=images/close.gif style='border:1px outset;' onclick=close_window();></td></tr></table><table cellpadding=0 cellspacing=0 border=0 width=100%><tr><td style=padding:1px width=100% height=260><iframe name=subwindow width=100% height=100% noresize scrolling=auto frameborder=5 marginheight=0 marginwidth=0   src='"+funcionURL+(funcionURL.indexOf("?")>0?"&":"?")+"input1="+input1+"&input2="+input2+"&input1Val="+input1Val+"&input2Val="+input2Val+"'></iframe></td></tr></table></td></tr></table><iframe src='javascript:false' style='position:absolute; visibility:inherit; border:0px; top:0px; left:0px; width:370px; height:260px; z-index:-1; filter='progid:XImageTransform.Microsoft.Alpha(style=0,opacity=0)';'></iframe>";
  Layer1.style.visibility="visible";
}

function openURL_1(funcionURL,functionName,width,height,input1, input2)
{
  var input1Val = "";
  var input2Val = "";
  var ctl = "";
  
  if (input1 != null && input1!="" && input2!=null && input2!="") {
	input1Val = document.all(input1).value;
  	input2Val = document.all(input2).value;
  	ctl = document.all(input2);
  }
  var left=0;
  var top=0;
  var posLeft = 0;
  var posTop = 0;
  posTop = posTop - height;
  posTop = posTop<0?10:posTop;
  posLeft = posLeft-width;
  posLeft = posLeft<0?10:posLeft;
  Layer1.style.left= posLeft;
  Layer1.style.top = posTop;
  Layer1.style.width=width;
  Layer1.style.height=height;
  Layer1.innerHTML="<table width="+width+" height="+height+" border=0 style='BACKGROUND-COLOR:  #56B5A9' cellpadding=1px cellspacing=0><tr><td><table border=0 cellpadding=0 cellspacing=0 height=18><tr><th height=18 style=cursor:hand width=100% style='BACKGROUND-COLOR: #56B5A9' onselectstart='return false'><layer width=100% onMouseOver=dragswitch=1;drag_dropns(Layer1) onMouseOut=dragswitch=0 left=-6 top=2 onselectstart='return false'><font color=#FFFFFF>"+functionName+"</font></layer></th><td onselectstart='return false'><img id=img_close src=images/close.gif style='border:1px outset;' onclick=close_window();></td></tr></table><table cellpadding=0 cellspacing=0 border=0 width=100%><tr><td style=padding:1px width=100% height="+(height-18)+"><iframe name=subwindow width=100% height=100% noresize scrolling=auto frameborder=5 marginheight=0 marginwidth=0 src='"+funcionURL+(funcionURL.indexOf("?")>0?"&":"?")+"input1="+input1+"&input2="+input2+"&input1Val="+input1Val+"&input2Val="+input2Val+"'></iframe></td></tr></table></td></tr></table><iframe src='javascript:false' style='position:absolute; visibility:inherit; border:0px; top:0px; left:0px; width:"+width+"px; height:"+height+"px; z-index:-1; filter='progid:XImageTransform.Microsoft.Alpha(style=0,opacity=0)';'></iframe>";
  Layer1.style.visibility="visible";
}

function openURL_2(funcionURL,functionName,width,height,input1,input2,input3,input4)
{
  var input1Val = "";
  var input2Val = "";
  var input3Val = "";
  var input4Val = "";
  var ctl = "";
  if (input1 != null && input1!="" && input2!=null && input2!="") {
	input1Val = document.all(input1).value;
  	input2Val = document.all(input2).value;
	input3Val = document.all(input3).value;
  	input4Val = document.all(input4).value;
  	ctl = document.all(input2);
  }
  var left=0;
  var top=0;
  var posLeft = 0;
  var posTop = 0;
  posTop = posTop - height;
  posTop = posTop<0?10:posTop;
  posLeft = posLeft-width;
  posLeft = posLeft<0?10:posLeft;
  Layer1.style.left= posLeft;
  Layer1.style.top = posTop;
  Layer1.style.width=width;
  Layer1.style.height=height;
  Layer1.innerHTML="<table width="+width+" height="+height+" border=0 style='BACKGROUND-COLOR:  #56B5A9' cellpadding=1px cellspacing=0><tr><td><table border=0 cellpadding=0 cellspacing=0 height=18><tr><th height=18 style=cursor:hand width=100% style='BACKGROUND-COLOR: #56B5A9' onselectstart='return false'><layer width=100% onMouseOver=dragswitch=1;drag_dropns(Layer1) onMouseOut=dragswitch=0 left=-6 top=2 onselectstart='return false'><font color=#FFFFFF>"+functionName+"</font></layer></th><td onselectstart='return false'><img id=img_close src=images/close.gif style='border:1px outset;' onclick=close_window();></td></tr></table><table cellpadding=0 cellspacing=0 border=0 width=100%><tr><td style=padding:1px width=100% height="+(height-18)+"><iframe name=subwindow width=100% height=100% noresize scrolling=auto frameborder=5 marginheight=0 marginwidth=0 src='"+funcionURL+(funcionURL.indexOf("?")>0?"&":"?")+"input1="+input1+"&input2="+input2+"&input1Val="+input1Val+"&input2Val="+input2Val+"&input3="+input3+"&input4="+input4+"&input3Val="+input3Val+"&input4Val="+input4Val+"'></iframe></td></tr></table></td></tr></table><iframe src='javascript:false' style='position:absolute; visibility:inherit; border:0px; top:0px; left:0px; width:"+width+"px; height:"+height+"px; z-index:-1; filter='progid:XImageTransform.Microsoft.Alpha(style=0,opacity=0)';'></iframe>";
  Layer1.style.visibility="visible";
}

function doClear(input1,input2){
  var input1Field = document.all(input1);
  var input2Field = document.all(input2);
  if (input1Field!=null)
  { 
    input1Field.value = "";
  }
  (input2Field!=null)
  { 
    input2Field.value = "";
  }
}

function close_window(){
    document.all.Layer1.style.visibility="hidden";
    document.all.Layer1.innerHTML="";
}

var  x=0;
var  y=0;
var  sx=0;
var  sy=0;

var dragswitch=0
var nsx
var nsy
var nstemp
var whichIt = null;
var dragapproved=false

function drag_dropns(name){
    temp=eval(name)
    temp.captureEvents(Event.MOUSEDOWN | Event.MOUSEUP)
    temp.onmousedown=gons
    temp.onmousemove=dragns
    temp.onmouseup=stopns
}

function gons(e){
    temp.captureEvents(Event.MOUSEMOVE)
    nsx=e.x
    nsy=e.y
}
function dragns(e){
    if (dragswitch==1){
        temp.moveBy(e.x-nsx,e.y-nsy)
        return false
    }
}

function stopns(){
    temp.releaseEvents(Event.MOUSEMOVE)
}

function drag_dropie(){
    if (dragapproved==true){
        temp.style.pixelLeft=tempx+event.clientX-iex
        temp.style.pixelTop=tempy+event.clientY-iey
        return false
    }
}
function initializedragie(name){
    whichIt=name
    temp=whichIt
    iex=event.clientX
    iey=event.clientY
    tempx=temp.style.pixelLeft
    tempy=temp.style.pixelTop
    if (dragapproved){
		dragapproved=false
	}else{
		dragapproved=true
	}
    document.onmousemove=drag_dropie
}

function confirmact(url,action,flag,card){

	var whichIt =event.srcElement;
	var e = whichIt;
	var acttype = e.acttype;
	var target = e.target;
	var sName;
	if (acttype==null || acttype.toLowerCase()=='undefined') acttype = "sysact";
	if (target==null || target.toLowerCase()=='undefined') target = "_self";

	sName="CheckedStr"+card;

	while	(whichIt.tagName.indexOf("FORM") ==	-1)	
	{	whichIt	=	whichIt.parentElement;
		if (whichIt	== null) { return	true;	}
	}

	var length = whichIt.elements.length
	var tocheck = whichIt.SelAll.checked
	var haveselected=false;

	if (acttype.toLowerCase()=='useract')
		whichIt.action=url;
	else if (acttype.toLowerCase()=='sysact')		
		whichIt.action="/cards/action/"+url+"?act="+action+"&card="+card;
	else
		return false;
	whichIt.target=target;

	sValue=GetCookie(sName);
	if (sValue.toLowerCase()!="undefined" && sValue!=""){
		//---从 cookie 中获得选中的数据
		haveselected=true;
		whichIt.selectedItems.value = sValue;
	}
	else{
		//---从选择的 checkbox 中获得选中的数据
		sValue="";
		for (var i=0; i<length; i++)
		{
			if (whichIt.elements[i].name.indexOf("T") != -1){
				if(whichIt.elements[i].checked ==true){
					haveselected=true;
					 sValue += ","+whichIt.elements[i].value;
				}
			}
		}
		whichIt.selectedItems.value = sValue
	}
	if(haveselected!=true){
		alert("请选择数据！")
		return false
	}
	if(flag>0){
		if ( confirm("请确认是否执行 “"+action+"” ？") ){
			 //alert("测试批量动作是否执行！");
			 whichIt.submit();
			}
	}else{
		whichIt.submit();
	}

	return false;
}

function All() 
{
	var whichIt = event.srcElement;
	while	(whichIt.tagName.indexOf("FORM") ==	-1)	
	{	whichIt	=	whichIt.parentElement;
		if (whichIt	== null) { return	true;	}
	}
 
	var length = whichIt.elements.length 
	var tocheck = whichIt.SelAll.checked 
	for (var i=0; i<length; i++) 
	{ 
		if (whichIt.elements[i].name.indexOf("T") != -1) 
		whichIt.elements[i].checked = tocheck 
	} 
	return; 
}

function SetAll() 
{
	var whichIt = event.srcElement;
	while	(whichIt.tagName.indexOf("FORM") ==	-1)	
	{	whichIt	=	whichIt.parentElement;
		if (whichIt	== null) { return	true;	}
	}

	if (1 == whichIt.SelAll.checked) 
	{ 
		whichIt.SelAll.checked = 0 
	} 
	else 
	{ 
		whichIt.SelAll.checked = 1 
	} 
	All() 
	return; 
}

function OpenDialog() 
{
	window.showModalDialog("/menu/about.php?crc=1","NewWin","dialogHeight: 308px; dialogWidth: 392px; dialogTop: px; dialogLeft: px; edge: raised; center: Yes; help: No; resizable: No; status: No; scroll: No;");
}
function CheckInputText(){

}
//改变颜色
var oMouseoverTR;
function changeRowColor(obj,flag)
{
	if (oMouseoverTR!=null)
	{	oMouseoverTR.style.backgroundColor='';
	}

	if (flag==0)
	{	
		if (obj!=null)
		{	oMouseoverTR=obj;
		}
		//var color='#C6CFDE';
		var color='#D9F7EF';
		if(document.all)
		{	if(obj.style.backgroundColor=='')
			{	obj.style.backgroundColor=color;
			}
			else
			{	obj.style.backgroundColor='';
			}
		}
	}
}
//onclick后变颜色
function selectedcolor(tabId,obj,classNm)
{ 
	for (var i=1; i<tabId.rows.length; i++) 
	{ 
		tabId.rows(i).className="TrBG1";
	} 	
/*
    document.all.tr1.className="TrBG1";
	document.all.tr2.className="TrBG1";
	document.all.tr3.className="TrBG1";	
	document.all.tr4.className="TrBG1";	
	document.all.tr5.className="TrBG1";	
    document.all.tr6.className="TrBG1";
	document.all.tr7.className="TrBG1";
	document.all.tr8.className="TrBG1";	
	document.all.tr9.className="TrBG1";*/
	obj.className=classNm;
}
/*
** lmf modify 2003-2-11
** intent create EXCEL file
*/
function confirmactexcel(url,action,flag,card){
	var whichIt = event.srcElement;
	while	(whichIt.tagName.indexOf("FORM") ==	-1)	
	{	whichIt	=	whichIt.parentElement;
		if (whichIt	== null) { return	true;	}
	}

	var length = whichIt.elements.length
	var tocheck = whichIt.SelAll.checked
	var haveselected=false
	var sign=whichIt.sign.value;
	if(sign =='') sign=0;
	whichIt.action="/cards/action/"+url+"?act="+action+"&card="+card+"&connect="+sign;
	for (var i=0; i<length; i++)
	{
		if (whichIt.elements[i].name.indexOf("T") != -1){
			if(whichIt.elements[i].checked ==true)haveselected=true
		}
	}
	if(haveselected!=true && sign!=0){//sign=1是关联数据库的excel，sign=0是主界面的excel
		alert("请选择数据！")
		return false
	}
	if(flag>0){
		if ( confirm("请确认是否执行 “"+action+"” ？") ){
			
			whichIt.submit();		   
			return false;
		}else{
			return false;
		}
	}else{
		whichIt.submit();
		return false;
	}
}
function SetCookie(sName, sValue)
{
  document.cookie = sName + "=" + escape(sValue) ;
}

function GetCookie(sName)
{
  // cookies are separated by semicolons
  var aCookie = document.cookie.split("; ");
  //alert(document.cookie);
  for (var i=0; i < aCookie.length; i++)
  {
    // a name/value pair (a crumb) is separated by an equal sign
    var aCrumb = aCookie[i].split("=");
    if (sName == aCrumb[0]) 
      return unescape(aCrumb[1]);
  }

  // a cookie with the requested name does not exist
  return "";
}

//展开查询条件
function expand1(imgDir,divNm)
{
 //if(window.divsearch.style.display=="none"){
  //window.divsearch.style.display="block";
  if(divNm.style.display=="none"){
     divNm.style.display="block";
  document.all.img1.src=imgDir+"images/minus_1.gif";
  }
  else
  {
  //divsearch.style.display="none"; 
  divNm.style.display="none"
  document.all.img1.src=imgDir+"images/minus_2.gif";
  }
}

//展开与关闭层
function expand(divNm)
{
	if(divNm.style.display=="none")
  {
     divNm.style.display="block";
  }
  else
  {
  divNm.style.display="none";
  }
}

//checkbox 全选

function doAllSelect(ckboxAll, ckbox){

  if (ckbox == null) return;

  if (ckbox.length != null) {

    for(var i = 0; i <ckbox.length; i++) {

      if (ckboxAll.checked == true) ckbox[i].checked = true;

      else ckbox[i].checked = false;

    }

  } else {

    if (ckboxAll.checked == true) ckbox.checked = true;

    else ckbox.checked = false;

  }

}

 

//checkBox 是否被选择

function isSelected(ckbox) {

  if (ckbox == null) return false;

  var flag = false;

  if (ckbox.length != null) {

    for(var i = 0; i <ckbox.length; i++) {

      if (ckbox[i].checked == true) {flag = true;break;}

    }

  } else {

    if (ckbox.checked == true) flag = true;

  }

  return flag;

}

function selChange(control,form,ItemArray,GroupArray,ValueArray){
	for(var i=0;i<ItemArray.length;i++){
		if(GroupArray[i] == control.value){
			for(var j=0;j<form.length;j++){
				var input = form[j];
				if(input != null){
					var name = input.name;
					if(name.indexOf("@") >= 0 && name.substring(0,name.indexOf("@"))==ItemArray[i]){
						if(name.substring(name.indexOf("@")+1) == "C"){
							for(var x=0;x<input.length;x++){
								if(input.options[x].value == ValueArray[i])
									input.options[x].selected = true;
							}
						}else{
							input.value = (ValueArray[i]=="null"?"":ValueArray[i]);
						}
					}
				}
			}
		}
	}
}

function reset(control,form,ItemArray){
	for(var i=0;i<ItemArray.length;i++){
		for(var j=0;j<form.length;j++){
			var input = form[j];
			if(input != null){
				var name = input.name;
				if(name.indexOf("@") >= 0 && name.substring(name.indexOf("@")+1) != "C"){
					input.value = "";
				}
			}
		}
	}
}

/*
function confirmactexcel(url,action,flag,card){
	var length = document.frmList.elements.length
	var tocheck = document.frmList.SelAll.checked
	var haveselected=false
	var j=0;
	var len;

	document.frmList.action="/cards/action/"+url+"?act="+action+"&card="+card+"&where=VDP_Var{where}";
	for (var i=0; i<length; i++)
	{
		if (document.frmList.elements[i].name.indexOf("T") != -1){
			haveselected=true
		}
	}
	if(haveselected!=true){
		alert("当前无数据！")
		return false
	}
	document.frmList.sa.value="";
	len=document.frmList.elements.length;
	while(j<len)
	{	var oElement=document.frmList.elements[j];
		if(((oElement.type=='checkbox') || (oElement.type=='CHECKBOX')) && oElement.name=="T[]"){
			document.frmList.sa.value+=oElement.value+"|";
		}
		j++;
	}

	if(flag>0){
		if ( confirm("请确认是否执行 “"+action+"” ？") ){
			document.frmList.submit();
			return false;
		}else{
			return false;
		}
	}else{
		document.frmList.submit();
		return false;
	}
}
*/

function openurl(url,dialogWidth,dialogHeight)
{
	window.open (url, '', 'height='+dialogHeight+', width='+dialogWidth+', top=30, left=55, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=no, status=no') 
}