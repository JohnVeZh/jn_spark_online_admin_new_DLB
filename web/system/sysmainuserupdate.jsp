<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

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
	<script type="text/javascript" src="../js/jquery-1.4.js"></script>

	<%@ include file="../include/css.jsp"%>
	<%
		String  successFlag="";
	    successFlag = (String)request.getAttribute("successFlag");
		if("1".equals(successFlag)){
			request.removeAttribute("successFlag");
		%>
	<script>
			alert("更改成功");
			//window.location.href="/crm/tab.html";
		</script>
	<%
		}else{
		 if("0".equals(successFlag)){
	%>
		<script>
			alert("更改失败,原密码不正确");
			//window.location.href="/crm/tab.html";
		</script>
	<%	}}
	 %>
</head>
<body>
	<html:form action="system/sysUser.do?act=mainUpdate"  styleId="listForm" method="post">
		<table width="79%" border="0" cellpadding="0" cellspacing="0"
			align="center">
			<tr>
				<td height="30" align="center" class="DataTable_Title">
					修改用户信息
				<br></td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" align="center"
						class="Table">
						<tr>
							<td width="18%" class="DataTable_ID">
								<div align="left">
									用户登陆名称
								</div>
							<br></td>
							<td width="31%" class="DataTable_Content">
								<div align="left">
									<html:hidden property="id" />
									<html:text property="loginName" styleClass="Input0"
										readonly="true"  ></html:text>
								</div>
							<br></td>
							<td width="18%" class="DataTable_ID">
								<div align="left">
									用户名称
								</div>
							<br></td>
							<td width="31%" class="DataTable_Content">
								<div align="left">
									<html:hidden property="id" />
									<html:text property="name" styleId="name" styleClass="Input0"
										maxlength="90" readonly="true"></html:text>
								</div>
								<br>
							</td>
						</tr>
						<tr>
							<td width="18%" class="DataTable_ID">
								<div align="left">
									原密码
								</div>
							<br></td>
							<td width="31%" class="DataTable_Content">
								<div align="left">
									<html:hidden property="password" styleId="password" />
									<input type="text" name="passwordold" id="passwordold"
										class="Input0" >
								</div>
							<br></td>
							<td>
							<br></td>
							<td>
							<br></td>
						</tr>
						<tr>
							<td width="18%" class="DataTable_ID">
								<div align="left">
									新密码
								</div>
							<br></td>
							<td width="31%" class="DataTable_Content">
								<div align="left">
									<input type="text" name="passwordnew" id="passwordnew"
										class="Input0" onblur="onf()" >
								</div>
							<br></td>
							<td width="18%" class="DataTable_ID">
								<div align="left">
									确认密码
								</div>
							<br></td>
							<td width="31%" class="DataTable_Content">
								<div align="left">
									<input type="text" name="passwordnew2" id="passwordnew2"
										class="Input0">
								</div>
							<br></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div align="center">
			<input name="submit1" type="submit"   value="保 存"
				onClick="return checkForm()">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	</html:form>
</body>
<script language="javascript" type="text/javascript">
	function checkForm(){
			if(document.SysUserForm.name.value == "")
    {
            alert("用户名称不能为空!");
            document.SysUserForm.name.focus();
            document.SysUserForm.name.value="";
            return false;	
    }
  	
    if(document.SysUserForm.password.value=="" )
    {
            alert("原密码不能为空!");
            document.SysUserForm.passwordold.focus();
            document.SysUserForm.passwordold.value="";
            return false;	
    }
    if(document.SysUserForm.passwordnew.value == "")
    {
            alert("新密码不能为空!");
            document.SysUserForm.passwordnew.focus();
            document.SysUserForm.passwordnew.value="";
            return false;	
    }
    if(document.SysUserForm.passwordnew.value!= document.SysUserForm.passwordnew2.value)
    {
            alert("新密码与确认密码不一致!");
            document.SysUserForm.passwordnew2.focus();
            document.SysUserForm.passwordnew2.value="";
            return false;	
    }
    }
   
  	
</script>
</html:html>
