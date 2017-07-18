<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.common.util.CommUtil"%>
<%@page import="com.business.Dlb.PeriodPaperQrcode.PeriodPaperQrcode"%>
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
<!--缩略图样式-->
<link href="<%=path%>/js/index.css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>
<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 日期选择框end -->
 

	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
		$(function(){
			if(${msg =="200" }){
				top.Dialog.close();
			}
		})
		//添加
		function preAdd(){       
		    top.Dialog.open({URL:"<%=path%>/system/Dlb/PeriodPaperQrcode/PaperQrcodeAnswer_add.jsp",ID:"a1",Width:1080,Height:768,Title:"添加试卷信息"});
		}
		//清空查询数据
		function qing(){
			document.getElementById("name").value="";
			document.getElementById("starttime").value="";
			document.getElementById("endtime").value="";
		}
		//修改详情
		function preView(id){
			if(id!=""){
				top.Dialog.open({URL:"<%=path%>/business/PeriodPaperQrcode.do?act=view&id="+id,ID:"a1",Width:1080,Height:768,Title:"详情信息"});
			}
		}
		//逻辑删除
		function preDel(id){
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
					listForm.action="PeriodPaperQrcode.do?act=preDel&id="+id;
						listForm.submit();
					}
				)
			}
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：激活码管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/PeriodPaperQrcode.do?act=list" method="post" >
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr style="float: left">
					<td>
						<div style="float: left">
							<a href="javascript:;" onclick="preAdd('')" title="新增"> <span class="img_add"></span>
							</a>
						</div>
							
						<div style="float: left">
							试卷名称：<input type="text" name="name" id="name" value="${name}"/>&nbsp;
						</div>
						
						<div style="float: left">
							<div style="float: left;margin-top:3px">&nbsp;&nbsp;学段&nbsp;</div>
							 <select name="section" id="section">
						 		<option value="">全部</option>
						 		<option value="01" <c:if test="${section=='01'}">selected="selected"</c:if> >四级</option>
						 		<option value="02" <c:if test="${section=='02'}">selected="selected"</c:if>>六级</option>
							 </select>&nbsp;
						</div>
						
						<div style="float: left;">
							添加时间：<input  class="date" type="text" name="starttime" value="${starttime }" id="starttime" dateFmt="yyyy-MM-dd HH:mm:ss"onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime\')}'})"/>
                            	-<input  class="date" type="text" name="endtime" value="${endtime }" id="endtime" dateFmt="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime\')}'})"/>
						</div>
							
						<div style="float: left">
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" />&nbsp;&nbsp; <input type="button" value="清空" onclick="qing()" /></td>
						</div>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th height="25" align="center" class="DataTable_Field" title="序号 ">序号</th>
						<th height="25" align="center" class="DataTable_Field" title="试卷名称">试卷名称</th>
						<th height="25" align="center" class="DataTable_Field" title="四六级">四六级</th>
						<th height="25" align="center" class="DataTable_Field" title="二维码地址">二维码地址</th>								 
						<th height="25" align="center" class="DataTable_Field" title="添加时间 ">添加时间</th>								 
						<th height="10" align="center" class="DataTable_Field" title="添加人">添加人</th>								 
						<th height="10" align="center" class="DataTable_Field" title="操作 ">操作 </th>								 
					</tr>
			<%
	 int sn=lc.getIndex();
	List list=lc.getList();
	PeriodPaperQrcode po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (PeriodPaperQrcode)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getName() %>"><%= po.getName() %></td>								
		<td class="DataTable_Content" align="left">
		 <% if(po.getSection().equals("01")){ out.print("四级"); }else if(po.getSection().equals("02")){ out.print("六级"); } %>
		 </td>								
		<td class="DataTable_Content" align="left" title="<%= po.getQrcodeContent() %>"><%= po.getQrcodeContent() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCreateDate() %>"><%= po.getCreateDate() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getUsername() %>"><%= po.getUsername() %></td>								
		<td  align="center">
			<a href="javascript:;" title="查看"
				onclick="preView('<%=po.getId()%>')"> <span class="img_view"></span>
			</a>
			<a href="javascript:;" title="删除"
				onclick="preDel('<%=po.getId()%>')"> <span class="img_delete"></span>
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
				<div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
		</form>
	</div>
		<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
	</body>
</html>

