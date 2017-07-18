<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.easecom.common.util.*" %>
<%@ page import="com.easecom.system.model.*" %>
<%@ page import="java.util.*" %>
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
	<%@ include file="../include/css.jsp" %> 
  </head>  
  <body>
  <table width="95%"  border="0" cellpadding="0" cellspacing="0" align="center">
        <tr>
          <td height="30" align="center" class="DataTable_Title">功能详细信息</td>
        </tr>
        <tr>
          <td>
             <table width="100%"  border="0" cellpadding="0"   align="center" class="Table">
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">上级功能编码</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
		                  <bean:write name="SysFunctionForm" property="parentcode"/>
		               </div>
		           </td>
		           <td width="18%" class="DataTable_ID"><div align="left">功能编码</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
                           <bean:write name="SysFunctionForm" property="code"/>
		               </div>
		            </td>  
                </tr> 
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">功能名称</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
                          <bean:write name="SysFunctionForm" property="name"/>
		               </div>
		           </td>
		           <td width="18%" class="DataTable_ID"><div align="left">功能全称</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
							<bean:write name="SysFunctionForm" property="fullname"/>
		               </div>
		            </td>  
                </tr>
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">功能URL</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
		                  <bean:write name="SysFunctionForm" property="url"/>
		               </div>
		           </td>
		           <td width="18%" class="DataTable_ID"><div align="left">排序值</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
		                   <bean:write name="SysFunctionForm" property="sort" />
		               </div>
		            </td>  
                </tr>
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">是否目录</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
		                  <bean:write name="SysFunctionForm" property="isdir"/>
		               </div>
		           </td>
		           <td width="18%" class="DataTable_ID"><div align="left">是否菜单显示</div></td>
                   <td width="31%" class="DataTable_Content">
                       <div align="left">
		                   <bean:write name="SysFunctionForm" property="isview"/>
		               </div>
		            </td>  
                </tr>
                <tr>
                   <td width="18%" class="DataTable_ID"><div align="left">备注</div></td>
                   <td width="80%" class="DataTable_Content" colspan=3>
                       <div align="left">
		                  <bean:write name="SysFunctionForm" property="remark"/>
		               </div>
		           </td>
                </tr>
             </table>
          </td>
        </tr>
      </table>
      <div align="center">
         <input name="submit2" type="button"   value="返 回" onClick="history.back();">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
     </div>
  </body>
</html:html>
     