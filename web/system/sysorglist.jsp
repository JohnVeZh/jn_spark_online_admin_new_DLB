<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="java.util.*" %>
<%@ page import="com.easecom.common.util.*" %>
<%@ page import="com.easecom.system.model.*" %>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
  	<%@ include file="../include/css.jsp" %> 
    <html:base />
	 <!--框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--框架必需end-->
    
    <title></title>   
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    
    
  </head>
 <%
  ListContainer lc=(ListContainer)request.getAttribute("lc");	
  String parentid=(String)request.getAttribute("parentid");
  String parentname=Tool.getValue("select name from sys_org where id='"+parentid+"'");	
  
   
 %>
  <body style="height: 100%"> 
  
  <div class="position">
		<div class="center">
		<div class="left">
		<div class="right">
			<span>当前位置：<%=parentname%>的下级部门列表</span>
		</div>	
		</div>	
		</div>
	</div>	
	
	<form  name="listForm" scope="request" action="sysOrg.do?act=list&parentid=<%=parentid%>" method="post">
   <!-- 
   		 <div class="box_tool padding_bottom3 padding_right5">
			<div class="center">
			<div class="left">
			<div class="right">
				<a href="javascript:;">
				<div class="navIconTool" onClick="preUpdate()">
					<img src="../icons/navIcon/tool_02.png"/><br />
					修改
				</div>
				</a>
				<a href="javascript:;">
				<div class="navIconTool" onClick="preAdd('sysOrg.do?act=preAdd&parentid=<%=parentid%>')">
					<img src="../icons/navIcon/tool_05.png"/><br />
					增加
				</div>
				</a>
				<a href="javascript:;">
				<div class="navIconTool" onClick="doDels()" title="注意： ① 删除部门前，请先删除该部门下的所有人员。使用中的部门不允许被删除。" >
					<img src="../icons/navIcon/tool_06.png"/><br />
					删除
				</div>
				</a>
				<div class="clear"></div>
			</div>		
			</div>	
			</div>
		</div>
   		 
    -->
   
		<div class="box_tool_min padding_top2 padding_bottom2 padding_right5">
			<div class="center">
			<div class="left">
			<div class="right">
				<div class="padding_top5 padding_left10">
					<a href="javascript:;" onClick="preAdd('sysOrg.do?act=preAdd&parentid=<%=parentid%>')" title="新增"><span class="img_add"></span></a>
					<div class="box_tool_line"></div>
					<a href="javascript:;" onClick="preUpdate()" title="修改"><span class="img_edit"></span></a>
					<div class="box_tool_line"></div>
					<a id="del_mfp" href="javascript:;" onClick="doDels()" ><span title="<font color=red>注意： ① 删除部门前，请先删除该部门下的所有人员。使用中的部门不允许被删除。</font>" class="img_delete"></span></a>
					<div class="clear"></div>
				</div>
			</div>		
			</div>	
			</div>
			<div class="clear"></div>
		</div>
		
		 
		 <div id="scrollContent" class="border_gray">
	<table class="tableStyle" id="listTable" >
		<thead>
		<tr>
			<th  width="3%" height="40px"><input type="checkbox" name="ids" value="checkbox" onClick="doAllSelect(this, document.all('id'))"></th>
			<td width="15%" align="center" height="25px">序号</td>
			<td width="" align="center">部门</td>
			<td width="15%" align="center">排序值</td>
		</tr>
		</thead>
		<%
			int sn=lc.getIndex();
			List list=lc.getList();
			SysOrg po = null;
			String name = "";
			for (int i = 0; i < list.size(); i++) 
			{
				po = (SysOrg)list.get(i);
				name = po.getName();
				if(name!=null&&!"".equals(name)) {
					name = name.length()>29?name.substring(0,28)+"...":name;
				} else {
					name= "";
				}
				
		%>
		<tr class="TrBG1" onmouseover=changeRowColor(this,0); onmouseout=changeRowColor(this,1); 
		onclick="this.childNodes[0].firstChild.checked=!this.childNodes[0].firstChild.checked;"+
				"selectedcolor(document.getElementById('listTable'),this,'SelectBG');"  id="tr1">
			<td ><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
	        <td align="center"><%=++sn%></td>
	        <td class="DataTable_Content" align="center" title="<%=po.getName() %>"><%= name %></td>
	        <td align="center" class="DataTable_Content"><%=po.getSort()%></td>
		</tr>
	<%
	}
%>	
	</table>
	</div>
	<div class="margin_top10">
		<%@include file="../include/listpage.jsp"%>
	</div>
		 <script type="text/javascript">
			var st = new SortableTable(document.getElementById("listTable"),["None", "Number", "String","Number"]);
		</script>
	</form>
	
	
</body>

<SCRIPT LANGUAGE="JavaScript">
function doDels() {
    if (isSelected(document.all('id'))) {
        //if(confirm("您确信要删除吗？")) {
        //    listForm.action="sysOrg.do?act=delete&parentid=<%=parentid%>";
        //    listForm.submit();
        //}
        top.Dialog.confirm("删除前请确认部门下没有人员，您确定要删除吗？",function(){
        	var idarr=document.getElementsByName('id');
        	var str="";
        	for(var i=0;i<idarr.length;i++){
        		if(str==""){
       				str+="'"+idarr[i].value+"'";
       			}else{
       				str+=",'"+idarr[i].value+"'";
       			}
        	}
        	//调用Ajax查看是否有企业用户
        	//getSendAreas(str.substr(0,str.lastIndexOf(',')))
        	 listForm.action="sysOrg.do?act=deleteByIds&ids="+str+"&parentid=<%=parentid%>";
        	 listForm.submit();
        })
    }else{
    	top.Dialog.alert('请选择至少一条数据');
    }
}
function getSendAreas(str){
		 var url="sysOrg.do?act=validauser";
	     var param = "str="+str;
	     new Ajax.Request(url,{method: "post",parameters: param,asynchronous: false,onComplete: doSendAreas});
		}
function doSendAreas(response){
	        var responseText=response.responseText;
            if('true'==responseText){
            	top.Dialog.alert('请先删除部门下的人员');
            }else{
            	    listForm.action="sysOrg.do?act=delete&parentid=<%=parentid%>";
        		    listForm.submit();
            }
 		}
 function preUpdate() {
 //   if (isSelected(document.all('id'))) {
        var input2 = document.getElementsByName('id');
        var idVal="";  
        var m=0;   
        for(var i = 0; i <input2.length; i++) {
          if (input2[i].checked) {
            idVal +="&id="+ input2[i].value;
           m++;
         } else {
        continue;
        }
      }
      if(Number(m)>=2 || Number(m)<1){
      	top.Dialog.alert("请选择一项进行操作！");
      	return false;
      	} 
    listForm.action="sysOrg.do?act=preUpdate"+idVal;
    listForm.submit();
//  }
}
function doDel(url) {
    if(confirm("您确信要删除吗？")) {
    	listForm.action=url;
      	listForm.submit();
    }
}
function preAdd(url) 
{       
    	listForm.action=url;
      	listForm.submit();
}
</SCRIPT>
</html:html>


