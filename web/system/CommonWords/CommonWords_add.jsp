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
					<span>当前位置：新增常用词汇<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/CommonWords.do?act=add" method="post" id="listForm" >
			<input type="text" name="ctId" value="${ctId }" style="display: none"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>常用词汇基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<tr>
							<td width="15%" align="right">
								单词名称：
							</td>
							<td width="35%" align="left">
								<input type="text" name="word" class="validate[required] iptClass" />
							</td>
							<td width="15%" align="right">
								音标：
							</td>
							<td width="35%" align="left">
								<input type="text" name="phonogram" class="validate[required] iptClass" />
							</td>
						    </tr>
							<tr>
							<td width="15%" align="right">
								排序值：
							</td>
							<td width="35%" align="left">
								<input type="text" name="sort" class="validate[required] iptClass" />
							</td>
							<td width="15%" align="right">考频</td>
						    <td align="left" colspan="3">
						    <input type="text" style="width:" name="testFrequency" class="validate[required] iptClass">
						    </td>
						    </tr>
						    <tr>
							<td width="15%" align="right">
								音频地址：
							</td>
							<td width="35%" align="left" colspan="3" >
								<textarea style="width:80%;height:40px" name="audioPath" class="validate[required] iptClass" ></textarea>
							</td>
						    </tr>
							<tr>
			 				<td width="15%" align="right">
								详解：
							</td>
							<td width="35%" align="left" colspan="3">
								<textarea  style="width:80%;" name="intro" class="validate[required] iptClass" ></textarea>
							</td>
						    </tr>
						    <tr>
			 				<td width="15%" align="right">
								内容：
							</td>
							<td width="35%" align="left" colspan="3">
								<textarea  style="width:80%;" name="content" class="validate[required] iptClass" ></textarea>
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
      	listForm.action="business/CommonWords.do?act=add";
		listForm.submit();
     	//top.frmright.window.location.reload();
     }
 
	function back(){
		//document.getElementById('listForm').action="business/News.do?act=list";
		//document.getElementById('listForm').submit();
		//history.back();
		top.Dialog.close();
		
	}
	
	</script>
	  

</script>


</html>
