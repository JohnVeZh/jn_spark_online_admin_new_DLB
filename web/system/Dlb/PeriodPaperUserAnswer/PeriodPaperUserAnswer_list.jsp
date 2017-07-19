<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.common.util.CommUtil"%>
<%@page import="com.business.Dlb.PeriodPaperUserAnswer.PeriodPaperUserAnswer"%>
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
		//修改详情
		function preUpdate(id){
			if(id!=""){
				top.Dialog.open({URL:"<%=path%>/business/PeriodPaperUserAnswer.do?act=preUpdate&id="+id,ID:"a1",Width:1080,Height:768,Title:"批改详情"});
			}
		}
		function preView(id){
			if(id!=""){
				top.Dialog.open({URL:"<%=path%>/business/PeriodPaperUserAnswer.do?act=preView&id="+id,ID:"a1",Width:1080,Height:768,Title:"查看信息"});
			}
		}
		function exportPage(){
			listForm.action = "PeriodPaperUserAnswer.do?act=exportPageExcel&exportID="+0;
			listForm.submit()
		}
		function exportAll(){
			listForm.action = "PeriodPaperUserAnswer.do?act=exportPageExcel&exportID="+1;
			listForm.submit()
		}
		function sub(){
			listForm.action = "PeriodPaperUserAnswer.do?act=list";
			listForm.submit()
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：试卷批改管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/PeriodPaperUserAnswer.do?act=list" method="post" >
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr style="float: left">
					<td>
						<div style="float: left">
							手机号：<input type="text" name="mobile" id="mobile" value="${mobile}"/>&nbsp;
						</div>
						
						<div style="float: left">
							<div style="float: left;margin-top:3px">&nbsp;&nbsp;试卷类型&nbsp;</div>
							 <select name="period" id="period">
						 		<option value="">全部</option>
						 		<option value="1" <c:if test="${period=='1'}">selected="selected"</c:if> >学前测试</option>
						 		<option value="2" <c:if test="${period=='2'}">selected="selected"</c:if>>学中测试</option>
						 		<option value="3" <c:if test="${period=='3'}">selected="selected"</c:if>>学末测试</option>
							 </select>&nbsp;
						</div>
						<div style="float: left">
							<div style="float: left;margin-top:3px">&nbsp;&nbsp;作业类型&nbsp;</div>
							 <select name="type" id="type">
						 		<option value="">全部</option>
						 		<option value="3" <c:if test="${type=='3'}">selected="selected"</c:if> >翻译</option>
						 		<option value="4" <c:if test="${type=='4'}">selected="selected"</c:if>>写作</option>
							 </select>&nbsp;
						</div>
						<div style="float: left">
							<div style="float: left;margin-top:3px">&nbsp;&nbsp;批改状态&nbsp;</div>
							 <select name="state" id="state">
						 		<option value="">全部</option>
						 		<option value="1" <c:if test="${state=='1'}">selected="selected"</c:if> >已批改</option>
						 		<option value="2" <c:if test="${state=='2'}">selected="selected"</c:if>>未批改</option>
							 </select>&nbsp;
						</div>
						<div style="float: left">
							<div style="float: left;margin-top:3px">&nbsp;&nbsp;四六级&nbsp;</div>
							 <select name="section" id="section">
						 		<option value="">全部</option>
						 		<option value="01" <c:if test="${section=='01'}">selected="selected"</c:if> >四级</option>
						 		<option value="02" <c:if test="${section=='02'}">selected="selected"</c:if>>六级</option>
							 </select>&nbsp;
						</div>
						
						<div style="float: left;">
							提交时间：<input  class="date" type="text" name="createstarttime" value="${createstarttime }" id="createstarttime" dateFmt="yyyy-MM-dd HH:mm:ss"onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'createendtime\')}'})"/>
                            	-<input  class="date" type="text" name="createendtime" value="${createendtime }" id="createendtime" dateFmt="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'createstarttime\')}'})"/>
						</div>
						
						<div style="float: left;">
							批改时间：<input  class="date" type="text" name="replystarttime" value="${replystarttime }" id="replystarttime" dateFmt="yyyy-MM-dd HH:mm:ss"onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'replyendtime\')}'})"/>
                            	-<input  class="date" type="text" name="replyendtime" value="${replyendtime }" id="replyendtime" dateFmt="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'replystarttime\')}'})"/>
						</div>
							
						<div style="float: left">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="查询" onclick="sub()"/>&nbsp;&nbsp;
						<input type="button" value="导出本页" onclick="exportPage()" />&nbsp;&nbsp;
						<input type="button" value="导出全部" onclick="exportAll()" /></td>
						</div>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th height="25" align="center" class="DataTable_Field" title="序号 ">序号</th>
						<th height="25" align="center" class="DataTable_Field" title="手机号">手机号</th>
						<th height="25" align="center" class="DataTable_Field" title="试卷类型">试卷类型</th>
						<th height="25" align="center" class="DataTable_Field" title="作业类型">作业类型</th>								 
						<th height="25" align="center" class="DataTable_Field" title="四六级 ">四六级</th>								 
						<th height="10" align="center" class="DataTable_Field" title="提交时间">提交时间</th>								 
						<th height="10" align="center" class="DataTable_Field" title="批改时间">批改时间</th>								 
						<th height="10" align="center" class="DataTable_Field" title="批改人">批改人</th>								 
						<th height="10" align="center" class="DataTable_Field" title="操作 ">操作</th>								 
					</tr>
			<%
	 int sn=lc.getIndex();
	List list=lc.getList();
	PeriodPaperUserAnswer po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (PeriodPaperUserAnswer)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getMobile() %>"><%= po.getMobile() %></td>								
		<td class="DataTable_Content" align="left">
			<% if(po.getPeriod()==1){ out.print("学前测试"); }else if(po.getPeriod()==2){ out.print("学中测试"); }else if(po.getPeriod()==3){ out.print("学末测试"); } %>
		</td>								
		<td class="DataTable_Content" align="left">
			<% if(po.getQuestionType()==3){ out.print("翻译"); }else if(po.getQuestionType()==4){ out.print("写作"); } %>
		</td>								
		<td class="DataTable_Content" align="left" >
			<% if(po.getSection().equals("01")){ out.print("四级"); }else if(po.getSection().equals("02")){ out.print("六级"); } %>
		</td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCreateDate() %>"><%= po.getCreateDate() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getReplyDate() %>"><% if(CommUtil.isObject(po.getReplyDate()))out.print(po.getReplyDate());else out.print(""); %></td>								
		<td class="DataTable_Content" align="center" title="<%= po.getReplyDate() %>"><% if(CommUtil.isObject(po.getReplyName()))out.print(po.getReplyName());else out.print(""); %></td>								
		<td >
			<% if(po.getReplyUserId()==null){ %>
				<a href="javascript:;" title="批改"
						onclick="preUpdate('<%=po.getId()%>')"> <span class="img_edit"></span>
				</a>
			<%} %>
			<% if(po.getReplyUserId()!=null){ %>
				<a href="javascript:;" title="查看"
						onclick="preView('<%=po.getId()%>')"> <span class="img_view"></span>
				</a>
			<%} %>
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

