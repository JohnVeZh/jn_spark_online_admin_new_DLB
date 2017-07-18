<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.BookActivationCode.BookActivationCodeActionForm"%>
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
					<span>当前位置：修改页面<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  action="business/BookActivationCode.do?act=update" method="post"  onsubmit="return sub(this)">
			<html:text property="id" style="display:none"></html:text>  
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>激活码基本信息</legend> 
						<table class="tableStyle" >
							<tr>
								<td width="15%" align="right">
									激活码：
								</td>
								<td width="35%" align="left">
								  <html:text readonly="true" property="code" style="width:250px" styleClass="validate[required] iptClass"></html:text>								 
								</td>
								<td width="15%" align="right">
									关联图书：
								</td>
								<td width="35%" align="left">
								 <html:text readonly="true" property="productId" style="width:250px" styleClass="validate[required] iptClass"></html:text>								 
								</td>
							</tr>
							<tr>
							 <td width="15%" align="right">
									生成日期：
								</td>
								<td width="35%" align="left">
								 <html:text readonly="true" property="createtime" style="width:250px" styleClass="validate[required] iptClass"></html:text>								 
								</td>
								<td width="15%" align="right">
									使用时间：
								</td>
								<td width="35%" align="left">									 
									<html:text property="useTime" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
							</tr>
							<tr>
							<td width="15%" align="right">
									生成人：
								</td>
								<td width="35%" align="left">		
									<html:text readonly="true" property="sysUserId" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
								<td width="25%" align="right">
									使用次数：
								</td>
								<td width="35%" align="left">									 
									<html:text property="useNum" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">
									使用账号：
								</td>
								<td width="35%" align="left">									 
									<html:text property="userId" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
								<td width="15%" align="right">
									使用地区：
								</td>
								<td width="35%" align="left">									 
									<html:text property="useRegion" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
							</tr>
								<!-- 
								<tr>
								<td width="15%" align="right">
									是否已导出  0:否  1：是：
								</td>
								<td width="35%" align="left">									 
									<html:text property="isExport" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：int 长度:10</span>
								</td>
							</tr>
							<tr>
								<td width="15%">
									：
								</td>
								<td width="35%">									 
									<html:text property="id" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：String 长度:32</span>
								</td>
							</tr> 
							<tr>
								<td width="15%" align="right">
									是否使用  0：否  1：是：
								</td>
								<td width="35%" align="left">									 
									<html:text property="isUse" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：int 长度:10</span>
								</td>
							</tr>
							 <tr>
								<td width="15%" align="right">
									是否删除 0:否 1:是：
								</td>
								<td width="35%" align="left">									 
									<html:text property="isDel" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：int 长度:10</span>
								</td>
							</tr>-->
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
                            <input type="button" value=" 关闭" onclick="back()"/> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
  </html:form>
		</div>
</body>
<script language="javascript" type="text/javascript">
 
	function back(){
		//listForm.action="business/BookActivationCode.do?act=list";
		//listForm.submit();
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
	  	});
</script>
</html>
