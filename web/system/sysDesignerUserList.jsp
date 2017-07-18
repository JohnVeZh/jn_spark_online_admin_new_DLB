<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="java.util.*" %>
<%@ page import="com.easecom.common.util.*" %>
<%@ page import="com.easecom.system.model.*" %>
<%@ page import="com.easecom.system.business.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    <%@ include file="../include/css.jsp" %> 
    <script type="text/javascript" src="../js/jquery-1.4.js"></script>
	<script type="text/javascript" src="../js/framework.js"></script>
	<link href="../css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link  rel="stylesheet" type="text/css" id="skin"/>
	<!--截取文字start-->
	<script type="text/javascript" src="../js/text/text-overflow.js"></script>
	<!--截取文字end-->
	
  </head>
 <%
  ListContainer lc=(ListContainer)request.getAttribute("lc");	
  String orgId=(String)request.getAttribute("orgId");
  String orgName=Tool.getValue("select name from sys_org where id='"+orgId+"'");	
  String orgType=Tool.getValue("select type from sys_org where id='"+orgId+"'");
  String topOrgId=Tool.getValue("select parent_id from sys_org where id='"+orgId+"'");
  request.setAttribute("orgId",orgId);
 %>
  <body>  
  <div class="position">
	<div class="center">
	<div class="left">
	<div class="right">
		<span>当前位置：选择设计师</span>
	</div>	
	</div>	
	</div>
</div>	 
   <form name="listForm" action="<%=basePath %>/system/sysUser.do?act=designerlist" method="post" id="listForm" name="listForm">
	<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
		<div class="center">
			<div class="left">
				<div class="right">
					<div class="padding_top5 padding_left10">
						<!-- <div class="box_tool_line"></div>
						<a name="SubmitDelete" onclick="ImportExcel();">
							<span class="icon_mark">批量导入</span>
						</a> -->
						<div class="box_tool_line"></div>
						姓名：<input type="text" name="name" value="${name }" />
						账号：<input type="text" name="loginName" value="${loginName }" />
						<input type="submit" value="查询"/>
					<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	<div class="clear"></div>
	</div>
  <table class="tableStyle" useCheckBox="true" useMultColor="true" id="listTable" sortMode="true" headFixMode="true" useMultColor="true">
	<thead>
       	<tr class="TrTlitBar" >
       		<td width="4%" height="25"  align="center" class="DataTable_Field"><input type="checkbox" name="IDs" id="IDs" value="checkbox" onClick="doAllSelect(this, document.all('id'))"/></td>
       		<td width="10%" height="25" align="center"  class="DataTable_Field"><span>序号</span></td>
       		<td width="20%" height="25" align="center" class="DataTable_Field">用户登录账号</td>
       		<td width="22%" height="25" align="center" class="DataTable_Field">真实姓名</td>
       		<td width="15%" height="25" align="center" class="DataTable_Field">操作</td>
      	</tr>
    </thead>
<%
	int sn=lc.getIndex();
	List list=lc.getList();
	SysUser po = null;
	String loginName = "";
	String name = "";
	for (int i = 0; i < list.size(); i++) 
	{
		po = (SysUser)list.get(i);
		loginName = po.getLoginName();
		if (loginName!=null&&!"".equals(loginName)) {
		
			loginName = loginName.length()>20?loginName.substring(0,19)+"...":loginName;
		} else {
			loginName = "";
		}
		name = po.getName();
		if(name!=null&&!"".equals(name)) {
			name = name.length()>20?name.substring(0,19)+"...":name;
		} else {
			name = "";
		}
%>
	<tr class="TrBG1" onmouseover=changeRowColor(this,0); onmouseout=changeRowColor(this,1); 
		onclick="this.firstChild.firstChild.checked=!this.firstChild.firstChild.checked;selectedcolor(document.getElementById('listTable'),this,'SelectBG')"  id="tr1">
    	<td align="center">
    		<input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"/>
    	</td>
        <td align="center"><%=++sn%></td>
         <td class="DataTable_Content" align="center" title="<%=po.getLoginName() %>"><div align="center"><%= loginName%><%= "0".equals(po.getIsvalid())?"<font color=red><已禁用></font>":""%></div></td>
        <td class="DataTable_Content" align="center"><div align="center" title="<%=po.getName() %>"><%= name%></div></td>
        <td class="DataTable_Content" align="center">
          <input type="button" value="确定" onclick="check('<%=po.getId()%>','<%=po.getName() %>','<%=po.getShopId() %>')">
          </td>
        </tr>
<%
	}
%>
  </table>
  
<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
<div class="center">
	<div class="right">
		<%@include file="../include/listpage.jsp"%>
	</div>
</div>
</div>
</form>
</body>
<script type="text/javascript">
  function check(sysUserId,name,shopId){
    	top.document.getElementById("frmright").contentWindow.document.getElementById("sysUserId").value=sysUserId;
    	top.document.getElementById("frmright").contentWindow.document.getElementById("nameStr").innerHTML="已选择设计师："+name;
    	top.document.getElementById("frmright").contentWindow.document.getElementById("shopId").value=shopId;
    	top.Dialog.close();
  }
</script>
</html:html>


