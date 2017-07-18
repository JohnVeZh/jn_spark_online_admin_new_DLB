<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.Problem.ProblemActionForm"%>
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
	 
</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：problem_edit<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  action="business/Problem.do?act=update" method="post"  onsubmit="return sub(this)" >
			<html:text property="id" style="display:none"></html:text>  
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>problem基本信息</legend> 
						<table class="tableStyle"  >
							<tr>
								<td width="20%" align="right">
									问题内容：
								</td>
								<td width="35%" align="left">									 
									<html:text property="content" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* </span>
								</td>
							</tr>
							<tr>
								<td width="20%" align="right">
									回复内容：
								</td>
								<td width="35%" align="left">									 
									<html:text property="replyContent" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">*</span>
								</td>
							</tr>
							<tr>
								<td width="20%" align="right">
									回复人员id：
								</td>
								<td width="35%" align="left">									 
									<html:text  property="sysUserId" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* </span>
								</td>
							</tr>
								<tr>
								<td width="20%" align="right" >
									是否满意 :
								</td>
								<td width="35%" align="left"> 
								<input type="radio" name="isSatisfied" <c:if test="${ProblemActionForm.isSatisfied==0}"> checked</c:if> value="0" /> 未评价
							    <input type="radio" name="isSatisfied" value="1" <c:if test="${ProblemActionForm.isSatisfied==1}"> checked</c:if>/> 满意
							    <input type="radio" name="isSatisfied" value="2" <c:if test="${ProblemActionForm.isSatisfied==2}"> checked</c:if>/> 不满意	
								</td>
							</tr>
								<!--
								<tr>
								<td width="20%" align="right">
									是否删除:
								</td>
								<td width="35%" align="left">									 
									<html:text property="isDel" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<input type="radio" name="isDel" checked="checked" value="0" />未删除
							        <input type="radio" name="isDel" value="1" /> 删除
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									是否回复:
								</td>
								<td width="35%" align="left">									 
									<html:text property="isReply" style="width:250px" styleClass="validate[required] iptClass"></html:text>  	
									<input type="radio" name="isReply" checked="checked" value="1" />是
							        <input type="radio" name="isReply" value="0"/> 否
								</td>
							</tr>-->   
								<!--  <tr>
								<td width="15%" align="right">
									唯一标识：
								</td>
								<td width="35%" align="left">									 
									<html:text property="id" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：String 长度:32</span>
								</td>
							</tr>-->
								
								<tr style="display:none">
								<td width="15%" align="right">
									用户id：
								</td>
								<td width="35%" align="left">									 
									<html:text property="userId" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
									<span class="star">* 类型：String 长度:32</span>
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

						</td>
					</tr>
				</table>
				<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
				<br />
				<br />
			</div>
  </html:form>
		</div>
</body>
<script language="javascript" type="text/javascript">
 
	function back(){
		listForm.action="business/News.do?act=list";
		listForm.submit();
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
