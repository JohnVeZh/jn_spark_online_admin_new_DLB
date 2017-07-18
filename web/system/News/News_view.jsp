<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.News.NewsActionForm"%>
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
	<!-- 日期选择框start -->
	<script type="text/javascript" src="<%=path%>/js/form/datePicker/WdatePicker.js"></script>
	
	
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
					<span>当前位置：修改新闻信息<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  action="business/News.do?act=update" method="post" enctype="multipart/form-data" onsubmit="return sub(this)">
			<html:text style="display:none" property="id" ></html:text>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>新闻基本信息</legend> 
						<table class="tableStyle" >
							<tr>
								<td width="10%" align="right">
									标题：
								</td>
								<td width="90%" align="left" colspan="3">
									${NewsActionForm.title }	
								</td>
							</tr>	
							<tr>
								<td width="10%" align="right">
									帖子链接：
								</td>
								<td width="90%" align="left" colspan="3">
									http://dy.sparke.cn/phoneMobile.do?act=shareNewsDetail&id=${NewsActionForm.id }	
								</td>
							</tr>					 
							<tr>
								<td width="10%" align="right">
									简介：
								</td>
								<td width="40%" align="left" colspan="3">	
									${NewsActionForm.subtitle }			 
								</td>
								</tr>
							<tr>
								<td width="10%" align="right">
									列表图片：
								</td>
								<td width="10%" align="left" >									 
									<div id="PhoneDiv" style="float: left;padding-right: 20px">
									<c:if test="${NewsActionForm.phoneImg != null && NewsActionForm.phoneImg != '' }">
										<a href="javascript:;" ref="${NewsActionForm.phoneImg }" class="preview">
											<img src="<%=path%>/${NewsActionForm.phoneImg }" style="width: 80px;"/>
										</a>
									</c:if>
									</div>
								</td>
								<td></td>
								<td></td>
								<%-- <td width="10%" align="right">
									图片（Pad端）：
								</td>
								<td width="10%" align="left" >									 
									<div id="PadDiv" style="float: left;padding-right: 20px">
										<c:if test="${NewsActionForm.padImg != null && NewsActionForm.padImg != '' }">
											<a href="javascript:;" ref="${NewsActionForm.padImg }" class="preview">
										 	 <img src="<%=path%>/${NewsActionForm.padImg }" style="width: 80px;"/>
											</a>
										</c:if>
									</div>
								</td> --%>
							</tr>
							<tr >
									<td align="right">
										标签:
									</td>
									<td colspan="3">
									  <c:forEach items="${lbs }" var="lb">
									    <div style="padding: 5px 5px 5px 5px; float: left;">
									    	${lb.lableName }
									    </div>
									  </c:forEach>
									</td>
								</tr>
								<tr>
								<td width="10%" align="right" >
									新闻内容：
								</td>
								<td colspan="7">
								</td>
							</tr>
							<tr>
								<td width="10%" align="right" >
								</td>
								<td width="90%" colspan="7">
									<div style=" height:450px; overflow:auto; " >
										${NewsActionForm.content}
									</div>
								</td>							
								
						</table>
					</fieldset>
					  
				<!-- ---- -->
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="reset" value=" 关闭 " onclick="back()" />
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
	 top.Dialog.close();
	}

	  	
</script>
</html>
