<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.common.util.CommUtil"%>
<%@page import="com.business.NetworkCourse.NetworkCourse"%>
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
 

	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
		
		$(function(){
			if(${msg =="200" }){
				top.Dialog.close();
			}
		})
		function isSel(){
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
			if (Number(j)>=2 || Number(j)<1) {
		      top.Dialog.alert("请选择一项进行操作",185,185);
		      	return "";
		    }else{
		    	return idVal; 
		    }	
		}
	
		//删除
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
					listForm.action="NetworkCourse.do?act=realdeleteById&id="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		    top.Dialog.open({URL:"<%=path%>/business/NetworkCourse.do?act=toAdd",ID:"a1",Width:1080,Height:768,Title:"添加网课"});
		}
		//修改网课详情
		function preUpdate(id) {
			if(id!=""){
			  top.Dialog.open({URL:"<%=path%>/business/NetworkCourse.do?act=preUpdate&id="+id,ID:"a1",Width:1080,Height:768,Title:"修改"});
			}
		}
		
		//清空查询数据
		function qing(){
			document.getElementById("ncNamelike").value="";
			document.getElementById("ncType").value="";
			document.getElementById("ncLevel").value="";
		}
		
		//修改网课状态
		function ncState(id,ncState) {
			if(id!=""){
		  		top.Dialog.open({URL:"<%=path%>/business/NetworkCourse.do?act=preCourseStateEdit&id="+id+"&ncState="+ncState,ID:"a1",Width:450,Height:200,Title:"修改网课状态"});
			}
		}
		//视频管理
		function catalogVideo(id) {
			if(id!=""){
		  	   top.Dialog.open({URL:"<%=path%>/business/NetworkCourseVideo.do?act=list&nvId="+id,ID:"a1",Width:'450',Height:'200',Title:"视频管理"});
			}
		}
		 
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：网课管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/NetworkCourse.do?act=list" method="post" >
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<div style="float: left">
						<a href="javascript:;" onclick="preAdd('')" title="新增"> <span
								class="img_add"></span>
						</a>
					</div>
					<div style="float: left">
						网课名称：<input type="text" name="ncNamelike" id="ncNamelike" value="${ncNamelike }"/>&nbsp;
					</div>
					<div style="float: left">
					<div style="float: left;margin-top:3px">&nbsp;&nbsp;网课级别&nbsp;</div>
						 <select name="ncLevel" id="ncLevel">
					 		<option value="">全部</option>
					 		<option value="cet4" <c:if test="${ncLevel=='cet4'}">selected="selected"</c:if> >四级</option>
					 		<option value="cet6" <c:if test="${ncLevel=='cet6'}">selected="selected"</c:if>>六级</option>
					 		<option value="pe" <c:if test="${ncLevel=='pe'}">selected="selected"</c:if>>考研</option>
						 </select>
					</div>
					<div style="float: left">
						<div style="float: left;margin-top:3px">&nbsp;&nbsp;&nbsp;&nbsp;网课类型&nbsp;</div>
						 <select name="ncLevelType" id="ncLevelType">
					 		<option value="">全部</option>
					 		<option value="system_course" <c:if test="${ncLevelType=='system_course'}">selected="selected"</c:if> >系统课</option>
					 		<option value="special_course" <c:if test="${ncLevelType=='special_course'}">selected="selected"</c:if> >专项课</option>
					 		<option value="public_course" <c:if test="${ncLevelType=='public_course'}">selected="selected"</c:if> >公开课</option>
					 		<option value="wei_course" <c:if test="${ncLevelType=='wei_course'}">selected="selected"</c:if> >微课</option>
						 </select>
					</div>
					<div style="float: left">
						<div style="float: left;margin-top:3px">&nbsp;&nbsp;&nbsp;&nbsp;网课形式&nbsp;</div>
						 <select name="ncType" id="ncType">
					 		<option value="">全部</option>
					 		<option value="1" <c:if test="${ncType=='1'}">selected="selected"</c:if> >直播</option>
					 		<option value="0" <c:if test="${ncType=='0'}">selected="selected"</c:if>>录播</option>
						 </select>
					</div>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th height="25" width="5%" align="center" class="DataTable_Field" title="序号 ">序号</th>
						<th height="25" width="10%" align="center" class="DataTable_Field" title="封面图片 ">封面图片</th>
						<th height="25" width="15%" class="DataTable_Field" title="网课名称 ">网课名称</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="网课级别 ">网课级别类型</th>								 
						<th height="10"  align="center" class="DataTable_Field" title="现价 ">现价</th>								 
						<th height="10"  align="center" class="DataTable_Field" title="实际销售量 ">实际销售量 </th>								 
						<th height="25"  align="center" class="DataTable_Field" title="网课形式">网课形式</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="网课状态  ">网课状态</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="开始时间 ">开始时间</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="结束时间  ">结束时间</th>								 
						<th height="25" width="10%" align="center" class="DataTable_Field" title="操作">操作</th>						 
					</tr>
					 
				 		 
			<%
	 int sn=lc.getIndex();
	List list=lc.getList();
	NetworkCourse po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (NetworkCourse)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="DataTable_Content" align="left">
        	<% 
        		if(po.getNcImg()!=null&&!po.getNcImg().equals("")){
        			if(po.getNcImg().startsWith("http://")){
        	%>
        		<div style="float: left;padding: 5px 5px 5px 5px">
				   <a href="javascript:thumbImgsDiv('<%=po.getNcImg()%>',0)" >
				      <img src="<%= po.getNcImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
				   </a>
				 </div>
        		  <% }else{
					%>
        		<div style="float: left;padding: 5px 5px 5px 5px">
			   <a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getNcImg()%>',0)" >
			      <img src="<%=path%>/<%= po.getNcImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
			 </div>
        	<% 
        		}}
        	%>
        </td>
		<td class="DataTable_Content" align="left" title="<%= po.getNcName() %>"><%= po.getNcName() %></td>								
		<td class="DataTable_Content" align="left">
		 <% if(po.getNcLevel().equals("cet4")){ out.print("四级"); }else if(po.getNcLevel().equals("cet6")){ out.print("六级"); }else if(po.getNcLevel().equals("pe")) {out.print("考研");} %>-
		 <% if(po.getNcLevelType().equals("system_course")){ out.print("系统课"); }else if(po.getNcLevelType().equals("special_course")){ out.print("专项课"); }else if(po.getNcLevelType().equals("public_course")) {out.print("公开课");}else if(po.getNcLevelType().equals("wei_course")) {out.print("微课");} %>
		 </td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCurrentPrice() %>"><%= po.getCurrentPrice() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getSaleNumber() %>"><%= po.getSaleNumber() %></td>								
		<td class="DataTable_Content" align="left"><% if(po.getNcType()==0){out.print("录播课");}else if(po.getNcType()==1){out.print("直播课");} %></td>								
		<td class="DataTable_Content" align="left">
		<% if(po.getNcState()==0){out.print("待发布");}
		else if(po.getNcState()==1) {out.print("发布中");} 
		else if(po.getNcState()==2) {out.print("预约中");}
		else if(po.getNcState()==3) {out.print("开售中");}
		else if(po.getNcState()==4) {out.print("已停售");}
		else if(po.getNcState()==5) {out.print("已下架");}%></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getNcLiveTime() %>"><% if(CommUtil.isObject(po.getNcLiveTime()))out.print(po.getNcLiveTime());else out.print(""); %></td>	
		<td class="DataTable_Content" align="left" title="<%= po.getNcEndTime() %>"><% if(CommUtil.isObject(po.getNcEndTime()))out.print(po.getNcEndTime());else out.print(""); %></td>	
		<td  align="left">
		 	<a href="javascript:;" title="修改网课详情"
				onclick="preUpdate('<%=po.getId()%>')"> <span class="img_edit"></span>
			</a>
			<a id="del_mfp" title="修改网课状态" 
				href="javascript:;" onclick="ncState('<%=po.getId()%>','<%=po.getNcState()%>')" > <span
					class="img_history" ></span>
			</a>
			<a id="del_mfp" title="视频管理"
				href="javascript:;" onclick="catalogVideo('<%=po.getId()%>')"> <span
					class="img_avi" ></span>
			</a>
			<a id="del_mfp" title="删除"
				href="javascript:;" onclick="doDelsById('<%=po.getId()%>')"> <span
					class="img_delete"></span>
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
					<%@include file="../../include/listpage.jsp"%>
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
