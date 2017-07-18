<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.common.util.CommUtil"%>
<%@page import="com.business.Dlb.ActivationCode.ActivationCode"%>
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
				top.frmright.window.location.reload();
				top.Dialog.close();
			}
		})
		
		//添加
		function preAdd(){       
		    top.Dialog.open({URL:"<%=path%>/system/Dlb/ActivationCode/ActivationCode_in.jsp",ID:"a1",Width:1080,Height:350,Title:"批量导入激活码"});
		}
		//清空查询数据
		function qing(){
			document.getElementById("code").value="";
			document.getElementById("instarttime").value="";
			document.getElementById("inendtime").value="";
			document.getElementById("userstarttime").value="";
			document.getElementById("userendtime").value="";
			document.getElementById("address").value="";
			document.getElementById("mobile").value="";
		}
		function updateTotal(code,total,type){
			if(type==""){
				top.Dialog.alert('<h3 align="center">操作信息有误，请联系管理员</h3>')
				return;
			}
			if(total=='0' || total=='null'){
				top.Dialog.open({URL:"<%=path%>/business/ActivationCode.do?act=toAdd&code="+code+"&type="+type,ID:"a1",Width:1080,Height:350,Title:"添加批改机会"});
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
		<form name="listForm" scope="request" action="<%=path%>/business/ActivationCode.do?act=list" method="post" >
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr style="float: left">
					<td>
						<div style="float: left">
							<a href="javascript:;" onclick="preAdd('')" title="导入激活码"> <span class="img_export"></span>
							</a>
						</div>
							
						<div style="float: left">
							激活码：<input type="text" name="code" id="code" value="${code }"/>&nbsp;
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
							导入时间：<input  class="date" type="text" name="instarttime" value="${instarttime }" id="instarttime" dateFmt="yyyy-MM-dd HH:mm:ss"onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'inendtime\')}'})"/>
                            	-<input  class="date" type="text" name="inendtime" value="${inendtime }" id="inendtime" dateFmt="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'instarttime\')}'})"/>
						</div>
							
						<div style="float: left;">
							使用时间：<input  class="date" type="text" name="userstarttime" value="${userstarttime }" id="userstarttime" dateFmt="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'userendtime\')}'})"/>
                            -<input  class="date" type="text" name="userendtime" value="${userendtime }" id="userendtime" dateFmt="yyyy-MM-dd HH:mm:ss" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'userstarttime\')}'})"/>&nbsp;
						</div>
						<br></br>
						<div style="float: left">
							手机号：<input type="text" name="mobile" id="mobile" value="${mobile }"/>&nbsp;
						</div>
						<div style="float: left">
							使用地区：<input type="text" name="address" id="address" value="${address }"/>&nbsp;
						</div>
						
						<div style="float: left">
							<div style="float: left;margin-top:3px">&nbsp;&nbsp;使用状态&nbsp;</div>
							 <select name="isActivatedType" id="isActivatedType">
						 		<option value="">全部</option>
						 		<option value="1" <c:if test="${isActivatedType=='1'}">selected="selected"</c:if> >激活</option>
						 		<option value="0" <c:if test="${isActivatedType=='0'}">selected="selected"</c:if>>未激活</option>
							 </select>
						</div>
						<div style="float: left">
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" />&nbsp;&nbsp; <input type="button" value="清空" onclick="qing()" /></td>
						</div>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" rowspan="2" height="25" align="center" class="DataTable_Field">
						</th>
						<th height="25" rowspan="2" align="center" class="DataTable_Field" title="序号 ">序号</th>
						<th height="25" rowspan="2" align="center" class="DataTable_Field" title="激活码">激活码</th>
						<th height="25" rowspan="2" align="center" class="DataTable_Field" title="学段">学段</th>								 
						<th height="25" rowspan="2" align="center" class="DataTable_Field" title="导入时间 ">导入时间</th>								 
						<th height="10" rowspan="2" align="center" class="DataTable_Field" title="激活次序">激活次序</th>								 
						<th height="10" rowspan="2"  align="center" class="DataTable_Field" title="操作人 ">操作人 </th>								 
						<th height="25" rowspan="2" align="center" class="DataTable_Field" title="激活时间">激活时间</th>								 
						<th height="25" rowspan="2" align="center" class="DataTable_Field" title="使用地区  ">使用地区</th>								 
						<th height="25" rowspan="2" align="center" class="DataTable_Field" title="手机号 ">手机号</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="翻译批改  " rowspan="1" colspan="3">翻译批改</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="写作批改  " colspan="3">写作批改</th>
						
					</tr>
					<tr >
						<th height="25"  align="center" class="DataTable_Field" title="手机号 ">学前</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="翻译批改  ">学中</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="写作批改  ">学末</th>
						<th height="25"  align="center" class="DataTable_Field" title="手机号 ">学前</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="翻译批改  ">学中</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="写作批改  ">学末</th>
					</tr>
					 
				 		 
			<%
	 int sn=lc.getIndex();
	List list=lc.getList();
	ActivationCode po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (ActivationCode)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getCode() %>"><%= po.getCode() %></td>								
		<td class="DataTable_Content" align="left">
		 <% if(po.getSection().equals("01")){ out.print("四级"); }else if(po.getSection().equals("02")){ out.print("六级"); } %>
		 </td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCreateDate() %>"><%= po.getCreateDate() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getActivateSort() %>"><% if(CommUtil.isObject(po.getActivateSort()))out.print(po.getActivateSort());else out.print("未激活"); %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getUsername() %>"><% if(CommUtil.isObject(po.getUsername()))out.print(po.getUsername());else out.print(""); %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getActivateTime() %>"><% if(CommUtil.isObject(po.getActivateTime()))out.print(po.getActivateTime());else out.print(""); %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getAddress() %>"><% if(CommUtil.isObject(po.getAddress()))out.print(po.getAddress());else out.print(""); %></td>	
		<td class="DataTable_Content" align="left" title="<%= po.getMobile() %>"><% if(CommUtil.isObject(po.getMobile()))out.print(po.getMobile());else out.print(""); %></td>	
		<!-- 批改次数 -->
		<% 
       		if(po.getIsActivated()==1){
       	%>
			<td class="DataTable_Content" align="left" ><a href="javascript:;" style="color: blue;" onclick="updateTotal('<%= po.getCode() %>','<%= po.getPreTranslateTotal() %>','pre_translate_total')">
				<% if(CommUtil.isObject(po.getPreTranslateTotal()))out.print(po.getPreTranslateTotal());else out.print("0"); %>/<% if(CommUtil.isObject(po.getPreTranslateUse()))out.print(po.getPreTranslateUse());else out.print("0"); %>
			</a></td>	
			<td class="DataTable_Content" align="left" ><a href="javascript:;" style="color: blue;" onclick="updateTotal('<%= po.getCode() %>','<%= po.getMidTranslateTotal() %>','mid_translate_total')">
				<% if(CommUtil.isObject(po.getMidTranslateTotal()))out.print(po.getMidTranslateTotal());else out.print("0"); %>/<% if(CommUtil.isObject(po.getMidWriteTotal()))out.print(po.getMidWriteTotal());else out.print("0"); %>
			</a></td>	
			<td class="DataTable_Content" align="left" ><a href="javascript:;" style="color: blue;" onclick="updateTotal('<%= po.getCode() %>','<%= po.getPostTranslateTotal() %>','post_translate_total')">
				<% if(CommUtil.isObject(po.getPostTranslateTotal()))out.print(po.getPostTranslateTotal());else out.print("0"); %>/<% if(CommUtil.isObject(po.getPostTranslateUse()))out.print(po.getPostTranslateUse());else out.print("0"); %>
			</a></td>	
			<td class="DataTable_Content" align="left" ><a href="javascript:;" style="color: blue;" onclick="updateTotal('<%= po.getCode() %>','<%= po.getPreWriteTotal() %>','pre_write_total')">
				<% if(CommUtil.isObject(po.getPreWriteTotal()))out.print(po.getPreWriteTotal());else out.print("0"); %>/<% if(CommUtil.isObject(po.getPreWriteUse()))out.print(po.getPreWriteUse());else out.print("0"); %>
			</a></td>	
			<td class="DataTable_Content" align="left" ><a href="javascript:;" style="color: blue;" onclick="updateTotal('<%= po.getCode() %>','<%= po.getMidWriteTotal() %>','mid_write_total')">
				<% if(CommUtil.isObject(po.getMidWriteTotal()))out.print(po.getMidWriteTotal());else out.print("0"); %>/<% if(CommUtil.isObject(po.getMidWriteUse()))out.print(po.getMidWriteUse());else out.print("0"); %>
			</a></td>	
			<td class="DataTable_Content" align="left" ><a href="javascript:;" style="color: blue;" onclick="updateTotal('<%= po.getCode() %>','<%= po.getPostWriteTotal() %>','post_write_total')">
				<% if(CommUtil.isObject(po.getPostWriteTotal()))out.print(po.getPostWriteTotal());else out.print("0"); %>/<% if(CommUtil.isObject(po.getPostWriteUse()))out.print(po.getPostWriteUse());else out.print("0"); %>
			</a></td>
		<% 
       		}else{
       	%>	
       		<td></td>
       		<td></td>
       		<td></td>
       		<td></td>
       		<td></td>
       		<td></td>
		<% 
       		}
       	%>	
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

