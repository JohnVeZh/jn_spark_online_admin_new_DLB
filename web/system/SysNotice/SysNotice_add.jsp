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
	<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
	<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
	<link href="<%=path%>/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/"/>
	<link href="<%=path%>/css/product_add.css" rel="stylesheet" type="text/css"/>
	<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
	<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script>
	 
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
					<span>当前位置：新增<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
		<form name="listForm" action="system/SysNotice.do?act=add" method="post" id="listForm" >
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							
							<tr>
								<td width="15%" align="right">
									公告标题：
								</td>
								<td width="75%" align="left">
									<input type="text" name="title" style="width: 95%" class="validate[required] iptClass" />
									<span class="star">*</span>
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									公告类型：
								</td>
								<td width="75%" align="left">
								<c:if test="${isFF }">
									<input type="radio" name="type" id="type1" checked="checked"  value="0" onclick="$('#idsTr').attr('style','display: none')"/>全部
									<input type="radio" name="type" id="type2" value="1" onclick="$('#idsTr').attr('style','display: ')"/>区域
								</c:if>
								<c:if test="${!isFF }">
									<input type="radio" name="type" id="type2" checked="checked" value="1" onclick="$('#idsTr').attr('style','display: ')"/>区域
								</c:if>
								</td>
							</tr>
							<tr style="display: none" id="idsTr">
								<td width="15%" align="right">
								</td>
								<td width="75%" align="left">
									<c:forEach items="${ShopList }" var="shop">
									 <div style="float: left;padding: 5px 5px 5px 5px; text-align: left;"> 
									    <input type="checkbox" name="sids" value="${shop.ID }"/>${shop.NAME }
									 </div>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									内容：
								</td>
								<td width="75%" align="left">
									<div style=" height:450px; overflow:auto; " >
										 <textarea id="ue_content" name="content" style="width: 100%"  class="validate[] iptClass" ></textarea> 
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
							<input type="submit" value=" 提 交 "/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 返 回 " onclick="back()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						</td>
					</tr>
				</table>
				<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
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
	  		UE.getEditor('ue_content', {initialFrameWidth:'100%',initialFrameHeight : 300});
	  		if(!${isFF}){
	  		 $("#idsTr").attr("style","");
	  		}
	  	});
	  	

</script>


</html>
