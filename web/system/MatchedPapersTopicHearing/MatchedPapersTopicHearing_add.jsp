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
				<div class="right">
					<span>当前位置：新增<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/MatchedPapersTopicHearing.do?act=add" method="post" id="listForm" >
			<input type="text" name="mpId" value="${mpId }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							
							<tr>
								<td width="10%" align="right">
									模块名称：
								</td>
								<td width="90%" align="left" colspan="3">
									<input type="text" name="hName" class="validate[required] iptClass" style="width: 96%" maxNum="50"/>
									<span class="star">*</span>
								</td>
							</tr>
								<tr>
								<td width="10%" align="right">
									模块类型：
								</td>
								<td width="40%" align="left">
									<c:forEach items="${type }" var="t" varStatus="s">
									 <input type="radio" name="subjectType" value="${t.VALUE }" <c:if test="${s.index==0}">checked="checked"</c:if> />${t.NAME }
									</c:forEach>
								</td>
								<td width="10%" align="right">
									排序：
								</td>
								<td width="40%" align="left">
									<input type="text" name="sort" class="validate[required] iptClass" inputMode="numberOnly"  watermark="限制输入正整数"/>
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="10%" align="right" align="right">
									播放内容：
								</td>
								<td width="90%" align="left" colspan="3" >
									<div style="height:420px; overflow:auto; " >
									 	<textarea  id="hContent" name="hContent" style="width: 100%;"  ></textarea> 
									</div>
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
							<input type="button" value=" 提 交 " onclick="flush()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 关闭" onclick="back()" />
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
    listForm.action="business/MatchedPapersTopicHearing.do?act=add";
	listForm.submit();
    top.frmright.window.location.reload();
	}
	
	  $(function(){
	 	 UE.getEditor('hContent', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		$("#hContent").attr("class","");
	  	});
	  	

</script>


</html>
