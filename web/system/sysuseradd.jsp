<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<head>
<html:base />
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	 <!--框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--框架必需end-->

<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
<style type="text/css">
td {
	font-size: 12px;
}
</style>
</head>
<body>
	<div class="position">
		<div class="center">
			<div class="left">
				<div class="right">
					<span>当前位置：人员管理 >>用户信息增加</span>
				</div>
			</div>
		</div>
	</div>
	<!--   onsubmit="sub(this)";-->
	<html:form action="system/sysUser.do?act=add" method="post">
		<input type="hidden" name="state" value="0" />
		<div class="box2" panelWidth="100%" panelTitle="用户信息增加"
			showStatus="false">
			<fieldset>
				<legend>用户必填信息</legend>

				<table class="tableStyle" >
					<tr>
						<td width="20%">登录账号：</td>
						<td width="30%">
							<!-- class="validate[required,custom[noSpecialCaracters]]"  <span class="star">*</span>-->
							<input type="text" name="loginName" /></td>
						<td width="15%">真实姓名：</td>
						<td width="30%"><input type="text" name="name" class="validate[required]" /> <span class="star">*</span></td>
					</tr>
					<tr>
						<td>登录密码：</td>
						<td><input type="password" id="password" name="password"
							class="validate[required,length[6,11],custom[noSpecialCaracters]]" />
							<span class="star">*</span></td>
						<td>组织名称：</td>
						<td class="DataTable_Content"><html:hidden property="orgId" />
							<bean:write name="SysUserForm" property="orgName" /> <span
							class="star">*</span></td>
					</tr>
					 
				</table>
			</fieldset>
			<fieldset>
				<legend>用户选填信息</legend>
				<table class="tableStyle" >
					<tr>
						<td width="20%">性别：</td>
						<td width="30%"><div style="width:120px;">
								<label for="radio-1">男</label> <input type="radio" class="radio"
									checked="checked" id="sex" name="sex" value="男" /> <label
									for="radio-2">女</label> <input type="radio" id="sex" name="sex"
									class="radio" value="女" />
							</div></td>
						<td width="15%">年龄：</td>
						<td width="30%"><input type="text" name="age" class=""
							onblur="checkInput(this,'onlyNumber',2)"
							onkeypress="return (/[\d]/.test(String.fromCharCode(event.keyCode)))" />
						</td>
					</tr>
					<tr>
						<td>邮箱：</td>
						<td><input type="text" name="email" id="email" class=""
							onblur="checkInput(this,'email','')" /></td>
						<td>职务：</td>
						<td><input type="text" name="duty" /></td>
					</tr>
					<tr>
						<td>电话号码：</td>
						<td><input type="text" name="phoneCornet"
							onblur="checkInput(this,'mobilephone','')" /></td>
						<td>工号：</td>
						<td><input type="text" name="worknum" /></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td colspan="3"><span class="float_left"> <textarea
									style="width:300px" name="remark" id="remark"></textarea> </span> <span
							class="img_light" style="height:80px;" title="限制在200字以内"></span>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="padding_top10">
				<table class="tableStyle" transMode="true">
					<tr>
						<td colspan="4">
							<!-- --> <input type="submit" value=" 提 交 " /> <!-- 
					<input type="button" value="提交" onclick="subContent(this.form)"/>
					--> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset"
							value=" 重 置 " /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
							type="button" value="返回"
							onclick="javascript:returnUp('<bean:write name="SysUserForm" property="orgId" />');" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</html:form>
</body>
<script language="javascript" type="text/javascript">
	/**
	验证可选填信息
	@param inputField 需要验证的文本框
	@param reg  验证规则
	@cls  class
	@min 可输入的最小长度
	@max 可输入的最大长度
	 */
	function validateFieldRegex(inputField, reg, cls, min, max) {
		if (inputField.value.length != 0) {
			inputField.className = "validate[custom[" + cls + "]]";
		} else {
			//用户没有填写选填内容  	
		}
	}
	/**
	返回
	 */
	function returnUp(orgid) {
		if (orgid == 'FFFFFF') {
			//alert("sysUser.do?act=list&orgid="+orgid+"&isckmtype=0");
			window.location.href = "sysUser.do?act=list&orgid=" + orgid
					+ "&isckmtype=0";
		} else {
			//alert("sysUser.do?act=list&orgid="+orgid);
			window.location.href = "sysUser.do?act=list&orgid=" + orgid;
		}
	}
	/**
	提交
	 */
	function subContent(form) {
		form['loginName'].style.borderColor = "red";
		//	form.submit();
	}

	/**
	验证 
	@param inp 要验证的输入框
	@param cls  要加入的class
	 */
	function checkInput(inp, cls, len) {
		if (inp.value.length != 0) {
			if (len == '') {
				inp.className = "validate[custom[" + cls + "]]";
			} else {
				inp.className = "validate[length[0," + len + "],custom[" + cls
						+ "]]";
			}
		}
	}
</script>


</html:html>
