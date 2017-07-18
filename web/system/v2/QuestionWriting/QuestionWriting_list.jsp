<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.v2.special.TbSpecialCatalog"%>
<%@page import="com.easecom.common.util.Tool"%>
<%
	String path = request.getContextPath();
	path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <!--3.3框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>

	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
	 $(function(){
	  top.Dialog.close();
	 })
		//添加
		function preAdd(){  
		    var Pid=$("#Pid").val();
		    if(Pid!=""){
		      top.Dialog.open({URL:"<%=path%>/business/QuestionWriting.do?act=preAdd&Pid="+Pid,ID:"a1",Width:1080,Height:768,Title:"新增"});     
		    }else{
		    	top.Dialog.alert("请在左侧选择类型",285,285);
			     return "";
		    }
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			    top.Dialog.open({URL:"<%=path%>/business/QuestionWriting.do?act=preUpdate&id="+id,ID:"a1",Width:1080,Height:768,Title:"修改"});     
			}
		}
		//删除
		function preDel(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
					   	function() {
						listForm.action="QuestionWriting.do?act=realdeleteById&id="+id;
							listForm.submit();
						}
					)     
			}
		}
		function preDown(){
			listForm.action="QuestionWriting.do?act=preDown";
			listForm.submit();
		} 
		//批量导入
		function batchAdd() {
			top.Dialog.open({URL:'<%=path%>/system/v2/QuestionWriting/QuestionWriting_export.jsp',Title:'写作导入',Width:600,Height:200});
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：写作<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/QuestionWriting.do?act=list" method="post">
			<input type="text" name="Pid" id="Pid" value="${parentid }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<div style="float: left;">
					<a href="javascript:;" onclick="preAdd()" title="新增写作题"> <span
							class="img_add"></span>
					</a>
					</div> &nbsp;&nbsp; 
					<div style="float: left;">
						<a id="a1" title="批量导入"
							href="javascript:;" onclick="batchAdd()"> <span class="img_xls"></span>
						</a>
					</div> &nbsp;&nbsp; 
					<div style="float: left;">
						<a href="javascript:;" onclick="preDown()" > <span>下载模板</span></a>
					</div>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title=" * 类型：String 长度:32">序号</th>								 
						<th height="25"  align="center" class="DataTable_Field" title=" * 类型：String 长度:100">目录名称</th>								 
						<th height="25"  align="center" class="DataTable_Field" >目录排序</th>								 
						<th height="25"  align="center" class="DataTable_Field" >学段</th>
						<th height="25"  align="center" class="DataTable_Field" >创建时间</th>
						<th height="25"  align="center" class="DataTable_Field" >操作</th>
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	TbSpecialCatalog po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (TbSpecialCatalog)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left"><%= po.getName() == null ? "":po.getName() %></td>
		<td class="DataTable_Content" align="left"><%= po.getSortOrder() %></td>	
		<td class="DataTable_Content" align="left"> 
			<%if(po.getPId().equals("0105")){out.print("四级");} else if(po.getPId().equals("0205")){out.print("六级");} %>
		</td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCreateTime() %>"><%= po.getCreateTime() %></td>	
		<td class="DataTable_Content" align="left">
			<a href="javascript:;"	onclick="preUpdate('<%=po.getId()%>')" title="修改"> 
				<span class="img_edit"></span>	
			</a>
			<a href="javascript:;"	onclick="preDel('<%=po.getId()%>')" title="删除"> 
				<span class="img_delete"></span>	
			</a>
		</td>		
 	</tr>
<%
	}
%>
			</table>
			<div class="box_tool_min padding_top2 padding_bottom2 padding_right">
			<div class="center">
				<div class="right">
				<%@include file="../../../include/listpage.jsp"%> 
					</div>
				</div>
			</div>
				<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
		</form>
	</div>
	</body>
</html>
