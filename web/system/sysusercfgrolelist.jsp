<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ page import="java.util.*"%>
<%@ page import="com.easecom.common.util.*"%>
<%@ page import="com.easecom.system.model.*"%>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
	<html:base />
 	 <!--框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="false"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--框架必需end-->
	<title>角色列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		td{
			font-size: 12px;
		}
	</style>
</head>
<%
	String userIds = (String) request.getAttribute("userIds");//得到用户id
	String orgId = (String) request.getAttribute("orgId");
		//String ownroleid =(String) request.getAttribute("ownroleid");//得到用户已分配角色id
		ListContainer lc = (ListContainer) request.getAttribute("lc");
%>
<body>
  <div class="position">
	<div class="center">
	<div class="left">
	<div class="right">
		<span>当前位置：人员管理 >>用户配置角色列表</span>
	</div>	
	</div>	
	</div>
</div>
	<form name="listForm" scope="request"
		action="/crm/system/sysUser.do?act=roleList&userIds=<%=userIds%>"
		method="post">
		<div class="box2" panelWidth="100%"  panelTitle="用户配置角色列表">
		<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
		<div class="center">
			<div class="left">
				<div class="right">
					<div class="padding_top5 padding_left10">
						<a name="Submit1" onclick="saveRoleCfg()">
							<span class="icon_save">保存角色</span>
						 </a>
						<div class="box_tool_line"></div>
						<a name="back" name="back" onclick="history.back()">
							<span class="icon_back">返回</span> 
						</a>
					<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	<div class="clear"></div>
	</div>
		<table class="tableStyle" useCheckBox="true" useMultColor="true">
			<thead>
				<tr class="TrTlitBar">
					<td width="4%" height="25" align="center" class="DataTable_Field">
						<input type="radio" name="ids" value="radio" disabled>
					</td>
					<td width="10%" height="25" align="center" class="DataTable_Field">
						序号
					</td>
					<td width="25%" height="25" align="center" class="DataTable_Field">
						角色名称
					</td>
					<td height="15% class="DataTable_Field"><div align="center">查看权限</div></td>
				</tr>
			</thead>
			<%
					int sn = lc.getIndex();
					List list = lc.getList();
					SysRole po = null;
					String isSelected = "";
					
					for (int i = 0; i < list.size(); i++) {
						po = (SysRole) list.get(i);
						String urId = Tool.getValue("select ur.id from sys_user_role ur where ur.user_id='"+userIds+"' and ur.role_id='"+po.getId()+"'");
						if (urId != null && !urId.equals("")) {
							isSelected = "checked";
						}else{
							isSelected ="";
						}
			%>
		<tr class="TrBG1" onmouseover=changeRowColor(this,0); onmouseout=changeRowColor(this,1); onclick=selectedcolor(document.getElementById('listTable'),this,'SelectBG')  id="tr1">
				<td align="center">
					<input type="radio" name="id" value="<%=po.getId()%>" 
					<%=isSelected%>	onclick="if(this.c==1){this.c=0;this.checked=0} else   this.c=1">
				</td>
				<td align="center"><%=++sn%></td>
				<td class="DataTable_Content" align="center"><%=po.getName()%></td>
				<td width="5%" class="DataTable_Content">
					<div align="center">
						<a href="sysrole.do?act=cfgFunView&id=<%=po.getId()%>"><img
								src="<%=request.getContextPath() %>/icons/checkAllOff.gif" alt="查看权限" width="18" height="13"
								border="0">
						</a>
					</div>
				</td>
			</tr>
			<%
				}
			%>
		</table>
<script type="text/javascript">
	var st = new SortableTable(document.getElementById("listTable"),["None", "Number", "String", "String","None"]);
</script>
<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
	<div class="center">
		<div class="right">
			<%@include file="../include/listpage.jsp"%>
		</div>
	</div>
</div>
</div>
</form>
</body>
<SCRIPT LANGUAGE="JavaScript">
function saveRoleCfg() {
//if (isSelected(document.all('ID'))) {
            listForm.action="sysUser.do?act=saveRoleCfg&userIds=<%=userIds%>&orgId=<%=orgId%>";
            listForm.submit();
            //}
}
</SCRIPT>
</html:html>