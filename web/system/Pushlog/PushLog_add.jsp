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
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
 <!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>

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
					<span>当前位置：添加推送<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="business/PushLog.do?act=add" method="post" id="listForm" >
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" >
							<tr>
								<td width="15%" align="right">
									推送标题：
								</td>
								<td width="75%" align="left">
									<input type="text" name="title" id="title" style="width: 95%" class="validate[required] iptClass" />
									<span class="star">*</span>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									推送内容：
								</td>
								<td width="35%" align="left">
								<textarea  style="width: 95%;height: 80px" name="content" id="content"></textarea>
									<span class="star"></span>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									是否包含编辑内容 ：
								</td>
								<td width="35%" align="left">
									<input type="radio" checked="checked" name="ismap"  id="ismap1" value="0" onchange="check()"/>否
									<input type="radio" name="ismap" id="ismap2" value="1" onchange="check()"/>是
								</td>
							</tr>
								<tr style="display: none" id="ContentStr">
								<td width="15%" align="right">
									编辑的内容：
								</td>
								<td width="35%" align="left">
									<div style=" height:450px; overflow:auto; " >
										 <textarea id="ue_content"  name="editcontent" id="editcontent" style="width: 95%"  class="validate[] iptClass" ></textarea> 
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
						<!-- 	<input type="button" value=" 提 交 " onclick="add()"/> -->
							<input type="submit" value=" 提 交 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 返 回 " onclick="back()" />
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
		history.back();
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
	  		UE.getEditor('ue_content', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  	check();
	  	});

	function check(){
	 if($("#ismap1").attr("checked")){
	 	$("#ContentStr").attr("style","display:none");
	 }else{
	   $("#ContentStr").attr("style","");
	 }
	}
	
	function add(){
	 var title = $("#title").val();
	 var content = $("#content").val();
	 var ismap = 0;
	 var editcontent = $("#editcontent").val();
	 if($("#ismap1").attr("checked")){
	 	ismap = 0;
	 }else{
	  ismap = 1;
	 }
	 $.post("business/PushLog.do?act=add", {title:title,content:content,ismap:ismap,editcontent:editcontent},function(data){
	  	          if(data.flag){
	  	           alert("推送成功！");
	  	           window.location.href=window.location.href;
	  	          }else{
	  	           alert("推送失败！");
	  	            window.location.href=window.location.href;
	  	          }
				
	  	 },"json"
	  	 );   
	}
</script>


</html>
