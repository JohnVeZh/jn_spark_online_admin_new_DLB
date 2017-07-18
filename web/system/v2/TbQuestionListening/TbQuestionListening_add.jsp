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
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>

	<!-- 异步上传 -->
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>

	<!--基本选项卡start-->
	<script type="text/javascript" src="<%=path%>/libs/js/nav/basicTab.js"></script>
	<!--基本选项卡end-->
	<!--缩略图样式-->
	<link href="<%=path%>/js/index.css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
	<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：增加<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="<%=path%>/business/TbQuestionListening.do?act=add" method="post" id="listForm" >
			<input type="hidden" name="id" value="${id }" style="display: none"/>
			<input type="hidden" name="parentid" value="${parentid }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset>
					<legend>基本信息</legend>
					<table class="tableStyle" transMode="true" footer="normal">
						<tr>
							<td width="10%" align="right">
								目录名称：
							</td>
							<td width="85%" align="left" >
								<input type="text" name="name" id="name" class="validate[] iptClass" style="width: 95%" />
								<span class="star"></span>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right">
								音频地址：
							</td>
							<td width="85%" align="left">
								<input type="text" name="audioUrl" id="audioUrl" class="validate[] iptClass" style="width: 70%" value="" />
								<audio style="width: 15%;" controls="controls"><source src="" /></audio>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right">
								题型：
							</td>
							<td width="35%" align="left" >
								<select name="PId" id="id">
									<option value = "">全部</option>
									${nameStatus }
								</select>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right">
								试用范围：
							</td>
							<td width="35%" align="left" >
								<select name="target" id="id">
									<option value = "${TbQuestionListeningActionForm.target}"
											<c:if test="${'0'.equals(TbQuestionListeningActionForm.target)}">
												selected
											</c:if>
									>全部</option>
									<option value = "${TbQuestionListeningActionForm.target}"
											<c:if test="${'1'.equals(TbQuestionListeningActionForm.target)}">
												selected
											</c:if>
									>专项</option>
									<option value = "${TbQuestionListeningActionForm.target}"
											<c:if test="${'2'.equals(TbQuestionListeningActionForm.target)}">
												selected
											</c:if>
									>试卷</option>
								</select>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right">
								目录排序：
							</td>
							<td width="85%" align="left">
								<input type="text" name="sortOrder" id="sortOrder" class="validate[required] iptClass" />
							</td>
						</tr>
						<tr>
							<td width="10%" align="right">
								题干：
							</td>
							<td width="85%" align="left">
								<%--<input type="text" name="title" id="title" class="validate[] iptClass" style="width: 95%" value="${TbQuestionListeningActionForm.title }"/>--%>
								<textarea rows="1" cols="1" style="width: 80%" name="title" id="title" ></textarea>
							</td>
						</tr>
					</table>
					<br/>
					<div class="basicTab">
						<c:choose>
							<c:when test="${textName=='听力原文' }">
								<div name="听力原文" id="listening" style="width:100%;min-height: 450px">
									<input type="text" name="mptwId" value="${TbQuestionListeningActionForm.id }" style="display: none"/>
									<div style="min-height: 450px">
										<h3>文本内容</h3>
										<textarea  id="ue_wContent" name="tapescripts" style="width: 100%;min-height: 450px"  ></textarea>
									</div>
								</div>
							</c:when>
							<c:when test="${textName=='翻译' }">
								<div name="翻译" id="translate" style="width:100%;min-height: 450px" >
									<input type="text" name="translate" value="${TbQuestionListeningActionForm.id }" style="display: none"/>
									<div style="min-height: 450px">
										<h3>文本内容</h3>
										<textarea  id="ue_wTest" name="translation" style="width: 100%;min-height: 450px"  ></textarea>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div name="听力原文" id="listening" style="width:100%;min-height: 450px">
									<input type="text" name="id" value="${TbQuestionListeningActionForm.id }" style="display: none"/>
									<div style="min-height: 450px">
										<h4>文本内容</h4>
										<textarea  id="ue_wContent" name="tapescripts" style="width: 100%;min-height: 450px"  ></textarea>
									</div>
								</div>

								<div name="翻译" id="translate" style="width:100%;min-height: 450px" >
									<input type="text" name="id" value="${TbQuestionListeningActionForm.id }" style="display: none"/>
									<div style="min-height: 450px">
										<h4>文本内容</h4>
										<textarea  id="ue_wTest" name="translation" style="width: 100%;min-height: 450px"  ></textarea>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</fieldset>
				<!-- ---- -->
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="submit" value=" 提 交 " onclick="flush()"/>
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
   
    function flush(){
      top.frmright.window.location.reload();
    }
	function back(){
		top.Dialog.close();
	}

    $(function(){
        UE.getEditor('ue_wContent', {initialFrameWidth:'100%',initialFrameHeight : 300});
        UE.getEditor('ue_wTest', {initialFrameWidth:'100%',initialFrameHeight : 300});
        UE.getEditor('ue_tContent', {initialFrameWidth:'100%',initialFrameHeight : 300});
        UE.getEditor('ue_tTest', {initialFrameWidth:'100%',initialFrameHeight : 300});
        UE.getEditor('ue_wAnalysis', {initialFrameWidth:'100%',initialFrameHeight : 300});
        UE.getEditor('ue_tAnalysis', {initialFrameWidth:'100%',initialFrameHeight : 300});


        $("#ue_wContent").attr("class","");
        $("#ue_wTest").attr("class","");
        $("#ue_tContent").attr("class","");
        $("#ue_tTest").attr("class","");
        $("#ue_wAnalysis").attr("class","");
        $("#ue_tAnalysis").attr("class","");
    });


</script>


</html>
