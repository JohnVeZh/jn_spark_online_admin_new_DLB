//根据元素ID,直接打印输出
function CallPrint(strid)
{
	 var prtContent = document.getElementById(strid);
	 var WinPrint = window.open('','','letf=0,top=0,width=1,height=1,toolbar=0,scrollbars=0,status=0,menubar=0');
	 WinPrint.document.write(prtContent.innerHTML);
	 WinPrint.document.close();
	 WinPrint.focus();
	 WinPrint.print();
	 WinPrint.close();
}
function   AutomateExcel()   
{   

var   oXL   =   new   ActiveXObject( "Excel.Application ");  
var   oWB   =   oXL.Workbooks.Add();
var   oSheet   =   oWB.ActiveSheet;
var   table   =   document.all.chart;
var   hang   =   table.rows.length;
var   lie   =   table.rows(0).cells.length; 
for   (i=0;i <hang;i++){
    for   (j=0;j <lie;j++){ 
        oSheet.Cells(i+1,j+1).Font.Bold   =   true;
        oSheet.Cells(i+1,j+1).Font.Size   =   10; 
if(table.rows(i).cells(j).innerHTML.toLowerCase().indexOf( ' <img ')!=-1){ 
    oSheet.Cells(i+1,j+1).Select();
            oSheet.Pictures.Insert(table.rows(i).cells(j).getElementsByTagName( 'img ')[0].src);
} 
        else{ 
    oSheet.Cells(i+1,j+1).value   =   table.rows(i).cells(j).innerText;
} 
    }   
}   
oXL.Visible   =   true;   
oXL.UserControl   =   true;   
}   
//根据元素ID,导出Excel
function AllAreaExcel(strid) 
{ 
	var oXL = new ActiveXObject("Excel.Application"); 
	var oWB = oXL.Workbooks.Add(); 
	var oSheet = oWB.ActiveSheet; 
	var sel=document.body.createTextRange(); 
	sel.moveToElementText(strid); 
	sel.select(); 
	sel.execCommand("Copy"); 
	oSheet.Paste(); 
	oXL.Visible = true; 
} 
//根据元素ID,选择元素
function getSelectElement(strid){
	var sel=document.body.createTextRange(); 
	sel.moveToElementText(strid); 
	sel.select(); 
}

//格式化数字
function FormatNumber(srcStr,nAfterDot)        //nAfterDot小数位数
{
	var srcStr, nAfterDot;
	var resultStr, nTen;
	srcStr = "" + srcStr + "";
	strLen = srcStr.length;
	dotPos = srcStr.indexOf(".", 0);
	if (dotPos == -1) {
		resultStr = srcStr + ".";
		for (i = 0; i < nAfterDot; i++) {
			resultStr = resultStr + "0";
		}
		return resultStr;
	} else {
		if ((strLen - dotPos - 1) >= nAfterDot) {
			nAfter = dotPos + nAfterDot + 1;
			nTen = 1;
			for (j = 0; j < nAfterDot; j++) {
				nTen = nTen * 10;
			}
			resultStr = Math.round(parseFloat(srcStr) * nTen) / nTen;
			return resultStr;
		} else {
			resultStr = srcStr;
			for (i = 0; i < (nAfterDot - strLen + dotPos + 1); i++) {
				resultStr = resultStr + "0";
			}
			return resultStr;
		}
	}
}

//校验是否全由数字组成 

function isDigit(s) 
{ 
var patrn=/^(-?\d+)(\.\d+)?$/; 
if (!patrn.exec(s)) return false 
return true 
} 
