<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.easecom.system.web.SysConfigForm"%>
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
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
	<link href="<%=path%>/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/"/>
	<link href="<%=path%>/css/product_add.css" rel="stylesheet" type="text/css"/>
	<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
	<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script>
	
	<!-- 日期选择框start -->
	<script type="text/javascript" src="<%=path%>/js/form/datePicker/WdatePicker.js"></script>
	
	
	<!-- 配置文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>
	
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=path%>/js/domeURL.js"></script>
	
	 
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：修改信息<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form action="/System/SysConfig.do?act=update"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
			<html:text style="display:none" property="id" ></html:text>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
							<tr>
								<td width="15%" align="right">
									调用名：
								</td>
								<td width="35%" align="left">									 
									<html:text property="alias" style="width:250px" readonly="true" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">*</span>
									</td>
								<td width="50%" rowspan="5" align="left">
							    <div align="left">
								   <img src="<%=path%>/${imgPath }" id="imgPath" style="width: 300px;height: 380px;"/>
								</div>
							  </td>
								</tr>
								<tr>
								<td width="15%" align="right">
									名称：
								</td>
								<td width="35%" align="left">									 
									<html:text property="name" style="width:250px" readonly="true" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									类型：
								</td>
								<td width="35%" align="left">									 
									<html:text property="type" style="width:250px" readonly="true" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									图片路径：
								</td>
								<td width="35%" align="left" >									 
									<html:text property="value" styleId="valueId"  readonly="true" style="width:250px" styleClass="validate[required] iptClass"></html:text>  
									<a href="javascript:" onclick="fileClick()" style="text-align: center;">更换图片</a>   	
								</td>
							</tr>
							
							<tr style="display: none">
								<td width="15%" align="right">
									排序：
								</td>
								<td width="35%" align="left" >									 
									<html:text property="sort"  readonly="true" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
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
							<input type="reset" value=" 返 回 " onclick="back()" />
						</td>
					</tr>
				</table>
				<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
 		 </html:form>
		</div>
		<div style="display: none">
			<html:form action="/System/SysConfig.do?act=updateImgPath" styleId="formAction"  method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
				<html:file property="file" styleId="file" onchange="ajaxFile()"></html:file>
			</html:form>
		</div>

</body>
<script language="javascript" type="text/javascript">
 
	function back(){
		/* listForm.action="business/News.do?act=list";
		listForm.submit(); */
		history.back();
	}
 
 	function fileClick(){
  		$("#file").click();
 	}
	
	function ajaxFile(){
	 $('#formAction').ajaxSubmit(function(data){
	    if(data.result){
			$("#valueId").val(data.imgPath);    
	    	$("#imgPath").attr("src",DomeUrl+data.imgPath);
	    }
	    });
	}
	
</script>
</html>
