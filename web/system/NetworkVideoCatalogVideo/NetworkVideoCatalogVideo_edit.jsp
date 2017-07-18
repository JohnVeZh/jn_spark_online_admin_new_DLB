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
 <!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 日期选择框end -->
 <!-- 异步上传图片 -->
<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>


</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：新增<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/NetworkVideoCatalogVideo.do?act=update" method="post" id="listForm" >
			<input type="text" name="nvId" value="${nvId }" style="display: none"/>
			<input type="text" name="nvcId" value="${nvcId }" style="display: none"/>
			<input type="text" name="id" value="${NetworkVideoCatalogVideoActionForm.id }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<tr>
								<td width="15%" align="right">
									课时名称：
								</td>
								<td width="35%" align="left">
									<input type="text" name="catalogName" class="validate[required] iptClass" style="width: 96%" value="${NetworkVideoCatalogVideoActionForm.catalogName }"/>
									<span class="star">*</span>
								</td>
								<td width="15%" align="right">
									课时时长：
								</td>
								<td width="35%" align="left">
									<input type="text" name="longTime" class="validate[required] iptClass" value="${NetworkVideoCatalogVideoActionForm.longTime }"/>
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
						    	<td width="15%" align="right">
									类型：
								</td>
								<td width="35%" align="left">
									<input type="radio" name="networkType" value="0" <c:if test="${NetworkVideoCatalogVideoActionForm.networkType == 0 }">checked="checked"</c:if> onclick="ntChk(0);" />录播
									<input type="radio" name="networkType" value="1" <c:if test="${NetworkVideoCatalogVideoActionForm.networkType == 1 }">checked="checked"</c:if> onclick="ntChk(1);"/>直播
								</td>
								<td width="15%" align="right">
									开始时间：
								</td>
								<td width="35%" align="left">
									<input type="text" name="startTime" id="start" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" value="${NetworkVideoCatalogVideoActionForm.startTime }" disabled="disabled"/>
									<span class="star"></span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									课程视频ID：
								</td>
								<td width="35%" align="left">
									<input type="text" name="networkRecordLinkId" class="validate[] iptClass" style="width: 96%" value="${NetworkVideoCatalogVideoActionForm.networkRecordLinkId }"/>
									<span class="star"></span>
								</td>
								<td width="15%" align="right">
									课程链接：
								</td>
								<td width="35%" align="left">
									<input type="text" name="networkLiveLink" class="validate[] iptClass" style="width: 96%" value="${NetworkVideoCatalogVideoActionForm.networkLiveLink }"/>
									<span class="star"></span>
								</td>
							</tr>
								
							<tr>
								<td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left">
									<input type="text" name="sort" class="validate[] iptClass"  inputMode="numberOnly"  watermark="限制输入正整数" value="${NetworkVideoCatalogVideoActionForm.sort }"/>
									<span class="star"></span>
								</td>
								<td width="15%" align="right">
									<!-- 选取讲师： -->
								</td>
								<td width="35%" align="left" >
									<%-- <select name="teacherId">
									<option value="">请选择老师</option>
									  ${teachers }
									</select> --%>
								</td>
							</tr>
						</table>
					</fieldset>
					  
				<!-- ---- -->
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="button" value=" 提 交 " onclick="flush()"/>
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
	
	function flush(){
	    $("#scrollContent").mask("表单正在提交...");
		$('#listForm').ajaxSubmit(function(data){
		    if(data.result){
		    	top.frmright.window.location.reload();
		    	top.Dialog.close();
		    }
		   })
	}
	
    function ntChk(value){
      if(value==0){
		  $("#start").attr("disabled","disabled");
      }else{
        $("#start").removeAttr("disabled");
      }
     
    }
    $(function(){
    	ntChk(${NetworkVideoCatalogVideoActionForm.networkType});
    })
 
	
	</script>

</script>


</html>
