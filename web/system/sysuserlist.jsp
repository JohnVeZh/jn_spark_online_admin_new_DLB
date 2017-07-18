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
	 <!--框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--框架必需end-->
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
		<span>当前位置：人员管理 >><%if(orgName.equals("")){%>所有<%}else{%><%=orgName%><%}%>的用户列表</span>
	</div>	
	</div>	
	</div>
</div>	 
<!--  
 <div class="subhead"><center><%if(orgName.equals("")){%>所有<%}else{%><%=orgName%><%} %>的用户列表</center></div>
-->
   <form name="listForm" action="<%=basePath %>/system/sysUser.do?act=list&orgId=<%=orgId%>" method="post" id="listForm" name="listForm">
	<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
		<div class="center">
			<div class="left">
				<div class="right">
					<div class="padding_top5 padding_left10">
						<a name="SubmitAdd" title="添加" >
							<span class="img_add" onclick='return preAdd("sysUser.do?act=preAdd&orgId=<%= orgId%>")'></span>
						 </a>
						<div class="box_tool_line"></div>
						<a name="SubmitUpdate" onclick="return preUpdate()" title="修改">
							<span class="img_edit"></span> 
						</a>
						<div class="box_tool_line"></div>
						<a name="SubmitCfgRole" onclick="return preCfgRole()" title="角色配置">
							<span class="img_user_group"></span>
						</a>
						<div class="box_tool_line"></div>
						<a name="SubmitDelete" onclick="return doDels()" title="启用/禁用">
							<span class="img_no"></span> 
						</a>
						<div class="box_tool_line"></div>
						<a name="SubmitDelete" onclick="return Dels()" title="删除">
							<span class="img_delete"></span> 
						</a>
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
       		<td width="20%" height="25" align="center" class="DataTable_Field">角色</td>
       		<td width="15%" height="25" align="center" class="DataTable_Field">部门</td>
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
          <div align="center">
        	<%= Tool.getValue("select role_name from sys_user_role where user_id='"+po.getId()+"'")%>
       	  </div>
       	</td>
        <td align="center" class="DataTable_Content"><div align="center"><%= po.getOrgName()==null?"":po.getOrgName()%></div></td>
         <td align="center" class="DataTable_Content"><div align="center">
         	<input type="button" value="密码重置" onclick="reqPsd('<%=po.getId() %>')" />
         </div></td>
         
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
</form>
</body>
<script type="text/javascript">

//启用/禁用
function doDels() {
	var inps = document.getElementsByName('id');
	var idVal = "";
	var j =0 ;
	for(var i = 0; i <inps.length; i++){
		if(inps[i].checked){
			idVal +="&id="+ inps[i].value;
            j++;
		}else{
			continue;
		}
	}
	 if(Number(j)>=2 || Number(j)<1) {
     	top.Dialog.alert("请选择一项进行操作",185,185);
      	return false;
      }
    top.Dialog.confirm("请确认，您确定要（启用／禁用）该账号吗？",
    	function(){
    		var idarr=document.getElementsByName('id');
        	var str="";
        	for(var i=0;i<idarr.length;i++){
        		if(idarr[i].checked==true && ''!=idarr[i].value && null!=idarr[i].value){
        			str+="'"+idarr[i].value+"',";
        		}
        	}
        	//调用Ajax查看是否有企业用户
        	listForm.action="sysUser.do?act=delete&orgId=<%=orgId%>";
            listForm.submit();
    	},185,330)
}
//删除
function Dels() {
	var inps = document.getElementsByName('id');
	var idVal = "";
	var j =0 ;
	for(var i = 0; i <inps.length; i++){
		if(inps[i].checked){
			idVal +="&id="+ inps[i].value;
            j++;
		}else{
			continue;
		}
	}
	 if(Number(j)>=2 || Number(j)<1) {
      	top.Dialog.alert("请选择一项进行操作",185,185);
      	return false;
      }
    top.Dialog.confirm("确定删除此用户？",
    	function(){
    		listForm.action="sysUser.do?act=realdelete&orgId=<%=orgId%>";
      	  	listForm.submit();
    	});  
}

 

function preAdd(url){
    listForm.action=url;
    listForm.submit();
}
//角色配置
function preCfgRole() {
	var inps = document.getElementsByName('id');
	var idVal = "";
	var j =0 ;
	for(var i = 0; i <inps.length; i++){
		if(inps[i].checked){
			idVal +="&id="+ inps[i].value;
            j++;
		}else{
			continue;
		}
	}
	 if(Number(j)>=2 || Number(j)<1) {
      	top.Dialog.alert("请选择一项进行操作",185,185);
      	return false;
      }
    listForm.action="sysUser.do?act=roleList"+idVal;
    listForm.submit();
  }
 function preUpdate() {
//    if (isSelected(document.all('id'))) {
       var input2 = document.getElementsByName('id');
        var idVal="";  
        var m = 0; 
        for(var i = 0; i <input2.length; i++) {
          if (input2[i].checked) {
            idVal +="&id="+ input2[i].value;
            m++;
         } else {
        continue;
        }
      }
      if (Number(m)>=2 || Number(m)<1) {
      
      	top.Dialog.alert("请选择一项进行操作",185,185);
      	return false;
      }
    listForm.action="sysUser.do?act=preUpdate"+idVal;
    listForm.submit();
//  }

}
function reqPsd(id){
	var idVal="&id="+id;
	 top.Dialog.confirm("重置后密码为:123456，确定重置？",
			   function() {
						window.location.href = "<%=path%>/system/sysUser.do?act=reqPsw"+idVal;
				});

}
 function ImportExcel(){
  top.Dialog.open({URL:"<%=path%>/system/importExcel.jsp",ID:"diag01",Title:"批量导入",Width:600,Height:400});
}
</SCRIPT>
</html:html>


