<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.NewQuestionsPapersTopic.NewQuestionsPapersTopicActionForm"%>
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
					<span>当前位置：修改题目<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <form  action="business/NewQuestionsPapersTopic.do?act=update" method="post" id="listForm" name="listForm">
			<input type="text" name="nqpttId" value="${nqpttId }" style="display: none"/>
			<input type="text" name="id" value="${NewQuestionsPapersTopicActionForm.id }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>配套题目基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
								<tr>
								<td width="15%" align="right">
									题号：
								</td>
								<td width="35%" align="left">
									<input type="text" name="tNum" class="validate[required] iptClass" inputMode="numberOnly"  watermark="限制输入正整数" value="${NewQuestionsPapersTopicActionForm.tNum }"/>
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									分值：
								</td> 
								<td width="35%" align="left">
									<input type="text" name="score" class="validate[required] iptClass" inputMode="numberOnly"  watermark="限制输入正整数" value="${NewQuestionsPapersTopicActionForm.score }"/>
									<span class="star">*</span>
								</td>
								<tr>
								<td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left">
								   <input type="text" name="sort" class="validate[required] iptClass" value="${NewQuestionsPapersTopicActionForm.sort }" />								 
								</td>
							</tr>
								<td width="15%" align="right">
									问题：
								</td>
								<td width="35%" aling="left" colspan="3">
								   <textarea name="tSubject" class="validate[required] iptClass" style="width:100%" >${NewQuestionsPapersTopicActionForm.tSubject }</textarea>									 
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									问题解析：
								</td>
								<td width="35%" align="left" colspan="3">	
								  <div style=" height:420px; overflow:auto; " >
									 	<textarea  id="tAnalysis" name="tAnalysis" style="width: 100%;"  >${NewQuestionsPapersTopicActionForm.tAnalysis }</textarea> 
									</div>								 
								</td>
							</tr>
								<tr> 
								<td width="15%" align="right">
									问题文本：
								</td>
								<td width="35%" align="left" colspan="3">
								    <div style=" height:420px; overflow:auto; " >
									 	<textarea  id="tText" name="tText" style="width: 100%;"  >${NewQuestionsPapersTopicActionForm.tText }</textarea> 
									</div>										 
								</td>
							</tr>
						</table>
						<table class="tableStyle" transMode="true" footer="normal" id="mytable">
							<tr>
								<td height="25"  align="center" class="DataTable_Field" title="">选项</td>
								<td height="25"  align="center" class="DataTable_Field" title="">选项内容</td>
								<td height="25"  align="center" class="DataTable_Field" title="">排序</td>
								<td height="25"  align="center" class="DataTable_Field" title="">是否正确答案</td>
								<td><input type="button" value="点击新增" onclick="addNewRow()"/></td>
								<td style="display: none"></td>
							</tr>
							<c:forEach items="${choices }" var="cho">
								<tr>
									<td height="25"  align="center" class="DataTable_Field" title="">
									  	<input type="text" value="${cho.cName }"/>
									</td>
									<td height="25"  align="center" class="DataTable_Field" title="">
									 	<textarea style="width: 100%;height: 30px" >${cho.cContent }</textarea>
									</td>
									<td height="25"  align="center" class="DataTable_Field" title="">
										 <input type="text" value="${cho.cSort }"/>
									</td>
									<td height="25"  align="center" class="DataTable_Field" title="">
									  <c:if test="${cho.cIsAnswer==0 }">
									   <input type="radio" name="cn" />是
									  </c:if>
									  <c:if test="${cho.cIsAnswer==1 }">
									   <input type="radio" name="cn" checked="checked"/>是
									  </c:if>
									</td>
									<td><input type="button" value="删除该行" class="button" onclick="delTr('${cho.id }',this)"/></th>
									<td height="25"  align="center" class="DataTable_Field" title="" style="display: none">
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
	  		 UE.getEditor('tAnalysis', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		$("#tAnalysis").attr("class","");
	  		 UE.getEditor('tText', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		$("#tText").attr("class","");
	  		
	  	});
	 
	  	function addNewRow(){
		//创建选项
		var $idInput=$('<input type="text" />');
		var $textInput=$('<input type="text" />');
		var $sortInput=$('<input type="text" />');
		//创建选项内容
		var $cInput=$('<textarea style="width: 100%;height: 30px" ></textarea>');
		//创建排序
		var $cecbox=$('<input type="radio" name="cn" /><span>是</span>');
		//创建是否正确答案
		//创建按钮
		var $delBtn=$('<input type="button" value="删除该行" class="button"/>');
		//创建表格行
		var $tr=$("<tr><td></td><td></td><td></td><td></td><td></td><td style='display: none'></td></tr>");
		$tr.find("td").eq(0).append($textInput);
		$tr.find("td").eq(1).append($cInput);
		$tr.find("td").eq(2).append($sortInput);
		$tr.find("td").eq(3).append($cecbox);
		$tr.find("td").eq(4).append($delBtn);
		$tr.find("td").eq(5).append($idInput);
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
		        var name = tdArr.eq(0).find("input").val();//
		        var content = tdArr.eq(1).find("textarea").val();//
		        var sort = tdArr.eq(2).find("input").val();//
		        var answer = tdArr.eq(3).find("input").attr("checked");
		        var id = tdArr.eq(5).find("input").val();
		        var isAnswer = 0;
		       	if(answer=='checked'){
		       		isAnswer = 1;
		       	}
		       	if(id!=''){
		        	arrbills.push(name+"/"+content+"/"+sort+"/"+isAnswer+"/"+id);
		       	}else{
		       		arrbills.push(name+"/"+content+"/"+sort+"/"+isAnswer+"/noId");
		       	}
		   	}
		   	num++;
   	 });
   	 if($("#tNum").val()==''){
   	 	return;
   	 }
   	 if($("#score").val()==''){
   	 	return;
   	 }
   	  listForm.action="business/NewQuestionsPapersTopic.do?act=update&arrbills="+arrbills;
	  listForm.submit();
	  top.frmright.window.location.reload();
	}

	function delTr(id,ts){
		if(id!=''){
		  $.post("business/NewQuestionsPapersTopic.do?act=AjasRealdelete", {id:id},
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
