<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.HomeworkCorrecting.HomeworkCorrectingActionForm"%>
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
					<span>当前位置：作业批改<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <form  action="business/HomeworkCorrecting.do?act=update" method="post" id="listForm" name="listForm" >
			<input type="text" name="id" value="${HomeworkCorrectingActionForm.id } "style="display:none"/>
			<input type="text" name="sysUserId" value="${HomeworkCorrectingActionForm.sysUserId }" style="display:none"></text>
			<input type="text" name="userId" value="${HomeworkCorrectingActionForm.userId }" style="display:none"></text>  
			<textarea name="reviewed" style="width:80%;display:none" style=""> ${HomeworkCorrectingActionForm.reviewed }</textarea>     	
			<input type="text" name="content" value="${HomeworkCorrectingActionForm.content}" style="display:none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>批改作业基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<tr>
							<td width="15%" align="right">
								是否加急：
							</td>
							<td width="85%" align="left">
							 <c:choose>
							   <c:when test="${HomeworkCorrectingActionForm.isUrgent==0}">
							   <input type="radio" name="isUrgent" value="0" checked="checked"/>否
							   </c:when>
							   <c:when test="${HomeworkCorrectingActionForm.isUrgent==1}">
							   <input type="radio" name="isUrgent" value="1" checked="checked"/>是
							   </c:when>
							 </c:choose>
							</td>
							</tr>
							<tr> 
							<td width="15%" align="right">
								用户点评：
							</td>
							<td width="35%" align="left" colspan="3">									 
							   <textarea name="reviewed" style="width:80%" styleClass="validate[required] iptClass">${HomeworkCorrectingActionForm.reviewed }</textarea>     	
							</td>
							</tr>
							<tr>
							<td width="15%" align="right">
								作文内容：
							</td>
							<td width="85%" align="left" colspan="3">
									${HomeworkCorrectingActionForm.content}
							</td>
							</tr>
						</table>
						<table class="tableStyle" transMode="true" footer="normal" id="mytable">
							<tr>
								<td height="25"  align="center" class="DataTable_Field" title="">需要批改的内容</td>
								<td height="25"  align="center" class="DataTable_Field" title="">批改建议</td>
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
							<input type="submit" value=" 提 交 " onclick="getTabl()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " /> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" value="关闭" onclick="back()"/>
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
		//listForm.action="business/News.do?act=list";
		//listForm.submit();
		top.Dialog.close();
	}
	 function addNewRow(){
		//创建选项
		var $cContentInput=$('<input type="text" style="width: 100%;height: 30px" />');
		//创建选项内容
		var $cTestInput=$('<textarea style="width: 100%;height: 30px" ></textarea>');
		//创建按钮
		var $delBtn=$('<input type="button" value="删除该行" class="button"/>');
		//创建表格行
		var $tr=$("<tr><td></td><td></td><td></td></tr>");
		$tr.find("td").eq(0).append($cContentInput);
		$tr.find("td").eq(1).append($cTestInput);
		$tr.find("td").eq(2).append($delBtn);
		$("#mytable").append($tr); 
		
		$delBtn.click(function(){
			//将所在的行删除
			$(this).parents("tr").remove();
		})
	}
	
	function getTabl(){
	     var num = 0; 
	     var arrbills = [];
	   	$("#mytable").find("tr").each(function(){
		   	if(num>0){
		        var tdArr = $(this).children();
		        var cContent = tdArr.eq(0).find("input").val();//
		        var cTest = tdArr.eq(1).find("textarea").val();//
		        arrbills.push(cContent+"/"+cTest);
		   	}
		   	num++;
		   	
   	 });
   	 
   	  listForm.action="business/HomeworkCorrecting.do?act=update&arrbills="+arrbills;
	  listForm.submit();
	}

	  $(function(){
	  		var msg='';//jstl的标签 $，{，msg}
	  		if(msg==200){
	  			top.Dialog.alert("添加成功。");
	  		}else if(msg==201){
	  			top.Dialog.alert("添加失败。");
	  		}else if(msg==202){
	  			top.Dialog.alert("添加失败!");
	  		}
	  	});
</script>
</html>
