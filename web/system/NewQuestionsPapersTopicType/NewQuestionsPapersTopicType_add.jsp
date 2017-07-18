<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

 <!--3.3框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->

<!-- 表单验证start -->
<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
<!-- 表单验证end -->
	 
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：新增<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/NewQuestionsPapersTopicType.do?act=add" method="post" id="listForm" >
			<input type="text" name="nqtpId" value="${nqtpId }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<tr>
								<td width="15%" align="right">
									模块类型：
								</td>
								<td width="35%" align="left">
									<select name="subjectType"  class="default" >
										${type }
									</select>
								</td>
								<td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left">
									<input type="text" name="sort" class="validate[required] iptClass" inputMode="numberOnly"  watermark="限制输入正整数"/>
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									模块名称：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="tName" class="validate[required] iptClass" style="width: 96%" maxNum="50"/>
									<span class="star">*</span>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									播放链接：
								</td>
								<td width="35%" colspan="3" align="left">
									<input type="text" name="tUrl" class="validate[required] iptClass" style="width: 96%"/>
									<span class="star">* </span>
								</td>
							</tr>
						</table>
						<table class="tableStyle" transMode="true" footer="normal" id="mytable">
							<tr>
								<td height="25"  align="center" class="DataTable_Field" title="">段落内容</td>
								<td height="25"  align="center" class="DataTable_Field" title="">开始时间</td>
								<td height="25"  align="center" class="DataTable_Field" title="" style="display: none">结束时间</td>
								<td height="25"  align="center" class="DataTable_Field" title="">排序</td>
								<td><input type="button" value="点击新增" onclick="addNewRow()"/></td>
							</tr>
						</table>
					</fieldset>
					  
				<!-- ---- -->
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="button" value=" 提 交 " onclick="getTabl()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 关闭 " onclick="back()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						</td>
					</tr>
				</table>
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
		</form>
		</div>
</body>
<script language="javascript" type="text/javascript">
 
 
	function back(){
		top.Dialog.close();
	}
	function addNewRow(){
		//创建选项
		var $textInput=$('<textarea style="width: 100%;height: 30px" ></textarea>');
		var $sortInput=$('<input type="number" />');
		var $startimeInput=$('<input type="number" />');
		var $endtimeInput=$('<input type="number" />');
		//创建选项内容
		//创建排序
		//创建是否正确答案
		//创建按钮
		var $delBtn=$('<input type="button" value="删除该行" class="button"/>');
		//创建表格行
		var $tr=$("<tr><td></td><td></td><td style='display: none'></td><td></td><td></td></tr>");
		$tr.find("td").eq(0).append($textInput);
		$tr.find("td").eq(1).append($startimeInput);
		$tr.find("td").eq(2).append($endtimeInput);
		$tr.find("td").eq(3).append($sortInput);
		$tr.find("td").eq(4).append($delBtn);
		$("#mytable").append($tr); 
		
		$delBtn.click(function(){
			//将所在的行删除
			$(this).parents("tr").remove();
		})
	}
	
	function getTabl(){
	    
	    var num = 0; 
	     var arrbills = "";
	   	$("#mytable").find("tr").each(function(){
		   	if(num>0){
		        var tdArr = $(this).children();
		        var name = tdArr.eq(0).find("textarea").val();//
		        var content = tdArr.eq(1).find("input").val();//
		        var sort = tdArr.eq(2).find("input").val();//
		        var answer = tdArr.eq(3).find("input").val();
		        if(arrbills==""){
		        		arrbills = "&arrbills="+name+"/"+content+"/"+sort+"/"+answer;
		       		}else{
		       			arrbills = arrbills+"&arrbills="+name+"/"+content+"/"+sort+"/"+answer;
		       		}
		   	}
		   	num++;
		   	
		   	
   	 });
   	 if($("#sNumber").val()==''){
   	 	return;
   	 }
   	 if($("#score").val()==''){
   	 	return;
   	 }
   	  listForm.action="business/NewQuestionsPapersTopicType.do?act=add"+arrbills;
	  listForm.submit();
	  //top.Dialog.close();
	  top.frmright.window.location.reload();
	}
	</script>
	 


</html>
