<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.NewQuestionsPapersTopicType.NewQuestionsPapersTopicTypeActionForm"%>
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
 <!-- 异步上传 -->
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>

</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：修改<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <form  action="business/NewQuestionsPapersTopicType.do?act=update" method="post" id="listForm" name="listForm"   onsubmit="return sub(this)">
			<input type="text" name="id" id="id" value="${NewQuestionsPapersTopicTypeActionForm.id }" style="display: none"/>
			<input type="text" name="nqtpId" id="nqtpId" value="${nqtpId }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>配套题库基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<tr>
								<td width="15%" align="right">
									模块类型：
								</td>
								<td width="35%" align="left">
									<select name="subjectType" id="subjectType"  class="default" >
										${type }
									</select>
								</td>
								<td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left">
									<input type="text" name="sort" id="sort" class="validate[required] iptClass" value="${NewQuestionsPapersTopicTypeActionForm.sort }" inputMode="numberOnly"  watermark="限制输入正整数"//>
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									模块名称：
								</td>
								<td width="35%" align="left" colspan="3">
									<input type="text" name="tName" id="tName" class="validate[required] iptClass" style="width: 96%" value="${NewQuestionsPapersTopicTypeActionForm.tName }" maxNum="50"/>
									<span class="star">*</span>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									播放链接：
								</td>
								<td width="35%" align="left" colspan="3">			
								    <input type="text" name="tUrl" id="tUrl" style="width: 96%" class="validate[required] iptClass" value="${NewQuestionsPapersTopicTypeActionForm.tUrl }"/>						 
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
								<td style='display: none'></td>
							</tr>
							<c:forEach items="${choices }" var="cho">
								<tr>
									<td height="25"  align="center" class="DataTable_Field" title="">
									  	<textarea style="width: 100%;height: 30px" >${cho.lyricText }</textarea>
									</td>
									<td height="25"  align="center" class="DataTable_Field" title="">
										<input type="number" value="${cho.statrTime }"/>
									</td>
									<td height="25"  align="center" class="DataTable_Field" title="" style="display: none">
										 <input type="number" value="${cho.endTime }"/>
									</td>
									<td height="25"  align="center" class="DataTable_Field" title="">
									  <input type="number" value="${cho.sort }"/>
									</td>
									<td><input type="button" value="删除该行" class="button" onclick="delTr('${cho.id }',this)"/></th>
									<td height="25"  align="center" class="DataTable_Field" title=""  style='display: none'>
									   	<input type="text" value="${cho.id }"/>
									</td>
								</tr>
							</c:forEach>
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
		top.Dialog.close();
	}

	  $(function(){
	  	});
	  	
	  	function addNewRow(){
		//创建选项
		var $idInput=$('<input type="text" />');
		var $textInput=$('<textarea style="width: 100%;height: 30px" ></textarea>');
		var $sortInput=$('<input type="text" />');
		var $startimeInput=$('<input type="text" />');
		var $endtimeInput=$('<input type="text" />');
		//创建选项内容
		//创建排序
		//创建是否正确答案
		//创建按钮
		var $delBtn=$('<input type="button" value="删除该行" class="button"/>');
		//创建表格行
		var $tr=$("<tr><td></td><td></td><td style='display: none'></td><td></td><td></td><td style='display: none'></td></tr>");
		$tr.find("td").eq(0).append($textInput);
		$tr.find("td").eq(1).append($startimeInput);
		$tr.find("td").eq(2).append($endtimeInput);
		$tr.find("td").eq(3).append($sortInput);
		$tr.find("td").eq(4).append($delBtn);
		$tr.find("td").eq(5).append($idInput);
		
		$("#mytable").append($tr); 
		
		$delBtn.click(function(){
			//将所在的行删除
			$(this).parents("tr").remove();
		})
	}
	
	function getTabl(){
	    $("#scrollContent").mask("表单正在提交...");
	    var num = 0; 
	     var arrbills = "";
	   	$("#mytable").find("tr").each(function(){
		   	if(num>0){
		        var tdArr = $(this).children();
		        var text = tdArr.eq(0).find("textarea").val();//
		        var startTime = tdArr.eq(1).find("input").val();//
		        var endTime = tdArr.eq(2).find("input").val();//
		        var sort = tdArr.eq(3).find("input").val();
		        var id = tdArr.eq(5).find("input").val();
		       	if(id!=''){
		        	if(arrbills==""){
		        		arrbills = text+"#"+startTime+"#"+endTime+"#"+sort+"#"+id;
		       		}else{
		       			arrbills = arrbills+"&"+text+"#"+startTime+"#"+endTime+"#"+sort+"#"+id;
		       		}
		       	}else{
		       		if(arrbills==""){
		       			arrbills =text+"#"+startTime+"#"+endTime+"#"+sort+"#noId";
		       		}else{
		       			arrbills = arrbills+"&"+text+"#"+startTime+"#"+endTime+"#"+sort+"#noId";
		       		}
		       	}
		     
		   	}
		   	num++;
		   	
   	 });
	  
	  var id = $("#id").val();
	  var subjectType = $("#subjectType").val();
	  var sort = $("#sort").val();
	  var tName = $("#tName").val();
	  var tUrl = $("#tUrl").val();
		 $.post("business/NewQuestionsPapersTopicType.do?act=ajaxUpdate",
		   {id:id,
		   subjectType:subjectType,
		   sort:sort,
		   tName:tName,
		   tUrl:tUrl,
		   arrbills:arrbills
		   },
			   function(data){
			    if(data.result='200'){
			    	top.Dialog.close();
			    }
			   }, "json");
	}
	
	function delTr(id,ts){
		if(id!=''){
		  $.post("business/NewQuestionsPapersTopicType.do?act=AjasRealdelete", {id:id},
		   function(data){
		    if(data.succeed='000'){
			     //将所在的行删除
				$(ts).parents("tr").remove();
		    }
		   }, "json");
		}

	}
</script>
</html>
