function ListValues(Flag, PageSize, FieldName1, FieldName2, Field1, Field2, TableName, PrimaryKey, FieldName, Where, Height, Width)
{  
   //alert(FieldName2.value);
   //使用模态IE窗口打开该页面  
   //var rtn = new Array();
   //rtn=showModalDialog("/YA/com/jery/LOV/ListValues.jsp?Flag=" + Flag + "&PageSize=" + PageSize + "&SourceField=" + Field1 + "&SourceCodeField=" + Field2 + "&TableName=" + TableName + "&PrimaryKey=" + PrimaryKey + "&FieldName=" + FieldName + "&Where=" + Where + "&OldValue=" + FieldName1.value + "&OldCodeValue=" + FieldName2.value, "" , "dialog-width:" + Width + "px; dialog-height:" + Height + "px;")  
   //alert(rtn[1]);
   //alert(rtn[2]);
   //FieldName1.value = rtn[1];
   //FieldName2.value = rtn[2];   
   /*****************************************************************************************************/
   //使用普通IE窗口打开该页�?
   window.open("/crm/util/LOV/ListValues.jsp?Flag=" + Flag + "&PageSize=" + PageSize + "&SourceField=" + Field1 + "&SourceCodeField=" + Field2 + "&TableName=" + TableName + "&PrimaryKey=" + PrimaryKey + "&FieldName=" + FieldName + "&Where=" + Where + "&OldValue=" + FieldName1.value + "&OldCodeValue=" + FieldName2.value, "MenuPopup" , "status=Yes, resizable=yes, scrollbars=yes, width=" + Width + ",height=" + Height); 
}

//===超级链接按钮行为控制代码===
function mouseovertd (o)
{
	o.style.borderStyle="solid"
	o.style.borderColor="#999999"
    o.style.backgroundColor="#C1C1E1"  //#D9D9D9
}

function mouseouttd (o)
{
	o.style.borderColor="#f1f1f1"
    o.style.backgroundColor="#f1f1f1"
}

//===数据表格鼠标移动效果===
function mouseovertr (o)
{
    o.style.backgroundColor="#D0E8FF"
	//o.style.cursor="hand";
}

function mouseouttr(o)
{
	o.style.backgroundColor=""
}
/*
function document.oncontextmenu()  //屏蔽除了文本输入框之外的右键菜单
{
  if ((event.srcElement.type=="text") || (event.srcElement.type=="textarea"))
	  return true;
  else
      return false;
}

function document.ondragstart()  //限制除了文本输入框之外的内容拖放
{
  if ((event.srcElement.type=="text") || (event.srcElement.type=="textarea"))
     return true;
  else
     return false;
}

function document.onselectstart() //限制除了文本输入框之外的内容选择
{
  if ((event.srcElement.type=="text") || (event.srcElement.type=="textarea") || (event.srcElement.className=="textarea"))
     return true;
  else
     return false;
}

function document.onmouseover()  //修饰button的鼠标经过样式
{
  if ((event.srcElement.type=="button") || (event.srcElement.type=="submit") || (event.srcElement.type=="reset"))
	 if (event.srcElement.className=="Button")
        event.srcElement.className="Button-o";
     else
		event.srcElement.className="SmallButton-o";
}

function document.onmouseout() //修饰button的鼠标退出样式
{
  if ((event.srcElement.type=="button") || (event.srcElement.type=="submit") || (event.srcElement.type=="reset"))
     if (event.srcElement.className=="Button-o")
        event.srcElement.className="Button";
     else
		event.srcElement.className="SmallButton";
}
*/