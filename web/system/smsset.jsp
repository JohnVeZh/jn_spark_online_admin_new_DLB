<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="java.util.*"%>
<%@ page import="com.easecom.common.util.*"%>
<%@ page import="com.easecom.base.model.*"%>
<%
	String path = request.getContextPath();
	String isAdd = request.getAttribute("isAdd") == null ? "" : request.getAttribute("isAdd").toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!--框架必需start-->
		<script type="text/javascript" src="<%=path %>/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=path %>/js/framework.js"></script>
		<script type="text/javascript" src="<%=path %>/js/md5.js"></script>
		<link href="<%=path %>/css/import_basic.css" rel="stylesheet"
			type="text/css" />
		<link rel="stylesheet" type="text/css" id="skin" prePath="../" />
		<!--框架必需end-->
		<script type="text/javascript">
		     $(function(){
		     	if("<%=isAdd%>"=="yes"){
		     		top.Dialog.alert("保存成功！",function(){
		     			top.Dialog.close();
		     		});
		     	}
		     	$("#resetBtn").click(function(){
		     		$("#form1").get(0).reset();
		     	});
		     	
		     	$("#sbtBtn").click(function(){
			     	document.getElementById("form1").submit();
			     });
		     });
		     
		</script>
		<style type="text/css">
		#myup_div{
			width: 100%;
			height: 100%;
			position: absolute;
			top: 0;
			left: 0px;
			background-color: #000;
			filter: alpha(opacity=50);
			z-index: 2;
			display: none;
		}
		#form_div{
			position: absolute;
			z-index: 3;
			width: 260px;
			height: 50px;
			display: none;
		}
		</style>
	</head>
	<body>
		<div class="static_box1">
			<div class="box1_topcenter2">
				<div class="box1_topleft2">
					<div class="box1_topright2"></div>
				</div>
			</div>
			<div class="box1_middlecenter">
				<div class="box1_middleleft2">
					<div class="box1_middleright2">
						<div style="padding: 0 20px 0 20px;">
							<html:form action="system/smspara.do?act=set" method="post" styleId="form1" onsubmit="checkForm();">
							<html:hidden property="id"/>
							<table class="tableStyle" formMode="true">
								<tr>
									<td>功能开启：<html:radio property="isopen" value="1" /></td>
									<td>功能关闭：<html:radio property="isopen" value="0" /></td>
								</tr>
								<tr>
									<td colspan="2">
										<input type="button" value=" 提 交 " id="sbtBtn"/>
										<input type="button" value=" 重 置 " id="resetBtn"/>
									</td>
								</tr>
							</table>
							</html:form>
						</div>
					</div>
				</div>
			</div>
			<div class="box1_bottomcenter2">
				<div class="box1_bottomleft2">
					<div class="box1_bottomright2"></div>
				</div>
			</div>
		</div>
		<div id="myup_div">
			
		</div>
		<div id="form_div">
			<table class="tableStyle">
				<tr>
					<td>
						<input type="text" name="originalPassword" id="originalPassword"/>
						<input type="button" value="md5" id="md5btn"/>
						<input type="button" value="取消" id="cancelbtn"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
