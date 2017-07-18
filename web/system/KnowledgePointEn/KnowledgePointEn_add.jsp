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
					<span>当前位置：知识点新增<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/KnowledgePointEn.do?act=add" method="post" id="listForm" target="frmright">
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>知识点基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<!-- <tr>
							<td width="15%" align="right">
								对应目录ID：
							</td>
							<td width="35%" align="left">
								<input type="text" name="kpeSubjectDirectoryId" class="validate[required] iptClass" />
							</td>
						    </tr>
							 -->
							<tr>
							<td width="15%" align="right">
								单词名称：
							</td>
							<td width="35%" align="left">
								<input type="text" name="kpeName" class="validate[required] iptClass" />
						 	</td>
						 	<td width="15%" align="right">
								音标：
							</td>
							<td width="35%" align="left">
								<input type="text" name="kpePhonogram" class="validate[required] iptClass" />
							</td>
						    </tr>
							<tr>
							<td width="15%" align="right">
								排序值：
							</td>
							<td width="35%" align="left">
								<input type="text" name="kpeSort" class="validate[required] iptClass" />
							</td>
							<td width="15%" align="right">
								考频：
							</td>
							<td width="35%" align="left">
								<input type="text" name="kpeTestFrequency" class="validate[required] iptClass" />
							</td>
						    </tr>
							
						    <tr>
							<td width="15%" align="right">
								简介：
							</td>
							<td width="35%" align="left" colspan="3">
								<input type="text" name="kpeIntro" style="width:80%" class="validate[required] iptClass" />
							</td>
						    </tr>
							<tr>
							<td width="15%" align="right">
								音频地址：
							</td>
							<td width="35%" align="left" colspan="3">
								<input type="text" name="kpeAudioPath" style="width:80%" class="validate[required] iptClass" />
							</td>
						    </tr>
						    <tr>
							<td width="15%" align="right">
								内容：
							</td>
							<td width="35%" align="left" colspan="3">
								<textarea  name="kpeContent" style="width:80%" class="validate[required] iptClass" ></textarea>
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
    function flush(){
    listForm.action="business/KnowledgePointEn.do?act=add";
	listForm.submit();
    top.frmright.window.location.reload();
   }
	function back(){
		//document.getElementById('listForm').action="business/News.do?act=list";
		//document.getElementById('listForm').submit();
		top.Dialog.close();
	}
	
	</script>
	  <script>
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
