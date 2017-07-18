<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page import="com.business.Problem.ProblemActionForm"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
						<span>当前位置：查看基本信息<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<html:form action="business/Problem.do?act=update" method="post" onsubmit="return sub(this)" disabled="true">
			<input style="display:none" type="text" name="id" value="${ProblemActionForm.id }"/>
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset>
					<legend>基本信息</legend>
					<table class="tableStyle" >
					<tr>
							<td width="10%" align="right" >问题内容：</td>
							<td width="95%" align="left" overflow:auto;>
							${ProblemActionForm.content }
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" >回复内容：</td>
							<td width="95%" align="left"  overflow:auto;>
							${ProblemActionForm.replyContent }
							</td>
						</tr>
						<tr>
							<td width="25%" align="right" >回复人员：</td>
							<td width="35%" align="left"  overflow:auto;>
							${ProblemActionForm.sysUserId }
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" >是否满意： </td>
							<td width="35%" align="left" >
							<c:if test="${ProblemActionForm.isSatisfied ==0}">未评价 </c:if>
					        <c:if test="${ProblemActionForm.isSatisfied ==1}">满意 </c:if>
					        <c:if test="${ProblemActionForm.isSatisfied ==2}">不满意</c:if>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" >是否回复：</td>
							<td width="60%" align="left" >
							<c:if test="${ProblemActionForm.isReply==0}">未回复 </c:if>
							<c:if test="${ProblemActionForm.isReply==1}">已回复</c:if>
							</td>
						</tr>
					</table>
				</fieldset>
				<!-- ---- -->
				<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;"><input type="reset"
							value=" 关闭 " onclick="back()" /></td>
					</tr>
				</table>
				<div class="diverror" align="left">
					友情提示:</br>
					<!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>-->
				</div>
				<br /> <br />
			</div>
		</html:form>
	</div>
</body>
</html>
<script language="javascript" type="text/javascript">
	function back() {
		//document.getElementById('listForm').action="business/News.do?act=list";
		//document.getElementById('listForm').submit();
		top.Dialog.close();
	}
</script>