//使用时：定义一个<div id="pp">要导出的页面内容</div>,调用时：exportExcel(pp)

//导出页面区域到Excel中
function exportExcel(areaId) 
{
	var oXL = new ActiveXObject("Excel.Application"); 
	var oWB = oXL.Workbooks.Add(); 
	var oSheet = oWB.ActiveSheet; 
	var sel=document.body.createTextRange();
	sel.moveToElementText(areaId);
	sel.select();
	sel.execCommand("Copy");
	oSheet.Paste();
	oXL.Visible = true;
}
//导出table到Excel中
//tableId：table的id
function exportTableToExcel(tableId) 
{
	var oXL = new ActiveXObject("Excel.Application"); 
	var oWB = oXL.Workbooks.Add(); 
	var oSheet = oWB.ActiveSheet; 
	var Lenr = tableId.rows.length;
	for (i=0;i<Lenr;i++) 
	{ 
		var Lenc = PrintA.rows(i).cells.length; 
		for (j=0;j<Lenc;j++) 
		{ 
			oSheet.Cells(i+1,j+1).value = PrintA.rows(i).cells(j).innerText; 
		} 
	} 
	oXL.Visible = true; 
}
//导出页面区域到word中去
function exportWord(areaId)
{
	var oWD = new ActiveXObject("Word.Application");
	var oDC = oWD.Documents.Add("",0,1);
	var oRange =oDC.Range(0,1);
	var sel = document.body.createTextRange();
	sel.moveToElementText(areaId);
	sel.select();
	sel.execCommand("Copy");
	oRange.Paste();
	oWD.Application.Visible = true;
}