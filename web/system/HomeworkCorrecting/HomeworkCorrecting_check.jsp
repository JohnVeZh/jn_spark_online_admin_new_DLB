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
	 <!-- 配置文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：作业修改<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <form  action="business/HomeworkCorrecting.do?act=check" method="post" id="listForm" name="listForm" target="frmright">
			<input type="text" name="id" value="${HomeworkCorrectingActionForm.id } "style="display:none"/>
			<input type="text" name="sysUserId" value="${HomeworkCorrectingActionForm.sysUserId }" style="display:none"></text>
			<input type="text" name="userId" value="${HomeworkCorrectingActionForm.userId }" style="display:none"></text>  
			<textarea name="reviewed" style="width:80%;display:none" style=""> ${HomeworkCorrectingActionForm.reviewed }</textarea>     	
			<input type="text" name="content" value="${HomeworkCorrectingActionForm.content}" style="display:none"/>
			
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>修改作业基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<tr>
							<td width="10%" align="right">
								是否加急：
							</td>
							<td width="90%" align="left">
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
								<td width="10%" align="right">
									作文内容：
								</td>
								<td width="90%" align="left" colspan="3">		
								 ${HomeworkCorrectingActionForm.content}							 
								</td>
							</tr>
							<tr>
								<td width="10%" align="right">
									全文批改：
								</td>
								<td width="90%" align="left" colspan="3">		
									<div style=" height:450px; overflow:auto; ">
									 	<textarea  id="ue_hcValue" name="hcValue" style="width: 100%"  >${HomeworkCorrectingActionForm.hcValue }</textarea> 
									</div>
								</td>
							</tr>
						</table>
						<table class="tableStyle" transMode="true" footer="normal" id="mytable">
							<tr>
								<td height="25"  align="center" class="DataTable_Field" title="">需要批改的内容</td>
								<td height="25"  align="center" class="DataTable_Field" title="">批改建议</td>
								<td><input type="button" value="点击新增" onclick="addNewRow()"/></td>
								<td style="display: none"></td>
							</tr>
							<c:forEach items="${record }" var="rec">
								<tr>
									<td height="25"  align="center" class="DataTable_Field" title="">
									  	<input type="text" value="${rec.cContent }" style="width: 90%;height: 30px"/>
									</td>
									<td height="25"  align="center" class="DataTable_Field" title="">
									 	<textarea style="width: 90%;height: 30px" >${rec.cTest }</textarea>
									</td>
									<td><input type="button" value="删除该行" class="button" onclick="delTr('${rec.id }',this)"/></th>
									<td height="25"  align="center" class="DataTable_Field" title="" style="display: none ">
									  	<input type="text" value="${rec.id }"/>
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
     $(function(){
	  		UE.getEditor('ue_hcValue', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		$("#ue_hcValue").attr("class","");
	  		
	  	});
	 
	 function addNewRow(){
		//创建选项
		var $idInput=$('<input type="text" />');
		var $cContentInput=$('<input type="text" style="width: 90%;height: 30px"/>');
		var $cTestInput=$('<textarea  style="width: 90%;height: 30px"/></textarea>');
		var $delBtn=$('<input type="button" value="删除该行" class="button"/>');
		//创建表格行
		var $tr=$("<tr><td></td><td></td><td></td><td style='display: none'></td></tr>");
		$tr.find("td").eq(0).append($cContentInput);
		$tr.find("td").eq(1).append($cTestInput);
		$tr.find("td").eq(2).append($delBtn);
		$tr.find("td").eq(3).append($idInput);
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
		        var id = tdArr.eq(3).find("input").val();
		       	if(id!=''){
		        	arrbills.push(cContent+"/"+cTest+"/"+id);
		       	}else{
		       		arrbills.push(cContent+"/"+cTest+"/"+"/noId");
		       	}
		   	}
		   	num++;
		   	
   	 });
   	  listForm.action="business/HomeworkCorrecting.do?act=check&arrbills="+arrbills;
	  listForm.submit();
	  top.frmright.window.location.reload();
	}

	function delTr(id,ts){
		if(id!=''){
		  $.post("business/HomeworkCorrecting.do?act=AjasRealdelete", {id:id},
		   function(data){
		     //alert(data.succeed);
		    if(data.succeed='000'){
			     //将所在的行删除
				$(ts).parents("tr").remove();
		    }
		   }, "json");
		}

	}
</script>
</html>
