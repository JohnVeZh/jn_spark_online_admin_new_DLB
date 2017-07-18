<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.easecom.common.util.DateUtils" %>
<html:html lang="true">
  <head>
    <html:base />    
    <title>用户修改</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<base target="_self">
	
	<%@ include file="../include/css.jsp" %> 
  </head>
  <body>
  
   <html:form action="/system/sysUser.do?act=initModify" method="post">
    <br>
    <br>
      <table width="95%"  border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
          <td height="30" align="center" class="DataTable_Title">修改企业用户信息</td>
        </tr>
        <tr>
          <td>
             <table width="100%"  border="0" cellpadding="0"   align="center" class="Table">
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">企业名称:</div></td>
                   <td  class="DataTable_Content" >
                       <div align="left"> 
                         <html:hidden  property="id"/>
                         <html:hidden  property="orgId"/>
                         <html:hidden  property="userid"/>
                         <html:text property="name" styleClass="Input0"></html:text>
		               </div>		            
		            </td>  
                </tr>
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">手机号:</div></td>
                   <td  class="DataTable_Content" >
                       <div align="left">
                          <html:text property="loginName" styleClass="Input0"></html:text>  
		               </div>		            
		            </td>  
                </tr>    
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">邮箱:</div></td>
                   <td  class="DataTable_Content" >
                       <div align="left"> 
                         <html:text property="email" styleClass="Input0"></html:text> 
		               </div>		            
		            </td></tr>
             </table>
          </td>
        </tr>
      </table>
      <div align="center">
         <input name="submit1" type="button"   value="保 存" onClick="submitForm();">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <input name="submit2" type="button"   value="返 回" onClick="window.close();">
     </div>
    </html:form>
	</body>
	<script language="javascript" type="text/javascript">
		function submitForm(){
		
			if($('email').value.length>100){
		  		alert('邮箱长度过长');
		  		$('email').focus();
		  		return false;
		  	}
		  	
		  	SysUserForm.action="/crm/system/sysUser.do?act=initModify";
			SysUserForm.submit();
		  	
			window.returnValue=document.getElementById("id").value;
			window.close();
		}
	</script>
</html:html>
