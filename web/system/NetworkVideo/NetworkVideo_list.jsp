<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.NetworkVideo.NetworkVideo"%>
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
	
		//判断
		function isSel1(){
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
		    return idVal; 
		}
	
		//删除数据
		function doDels() {
			var idVal = isSel1();
			if(idVal!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="NetworkVideo.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="NetworkVideo.do?act=realdeleteById&networkId="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		    top.Dialog.open({URL:"<%=path%>/business/NetworkVideo.do?act=preAdd",ID:"a1",Width:1080,Height:768,Title:"新增"});
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			  top.Dialog.open({URL:"<%=path%>/business/NetworkVideo.do?act=preUpdate&id="+id,ID:"a1",Width:1080,Height:768,Title:"修改"});
			}
		}
		//详情
		function view() {
			var idVal = isSel();
			if(idVal!=""){
		  	   listForm.action="NetworkVideo.do?act=view"+idVal;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("nvNamelike").value="";
			document.getElementById("stateStr").value="";
		}
		
		//课时管理
		function catalog(id) {
			if(id!=""){
		  	   top.Dialog.open({URL:"<%=path%>/business/NetworkVideo.do?act=preAddByCateLog&nvId="+id,ID:"a01",Width:'100',Height:'100',Title:"课时管理"});
		//	listForm.action="NetworkVideo.do?act=preAddByCateLog&nvId="+id;
		  //		listForm.submit();
			}
		}
		//视频管理
		function catalogVideo(id) {
			if(id!=""){
		  	   top.Dialog.open({URL:"<%=path%>/business/NetworkVideo.do?act=preAddByCateLogVideo&nvId="+id,ID:"a01",Width:'100',Height:'100',Title:"视频管理"});
		//	 listForm.action="NetworkVideo.do?act=preAddByCateLogVideo&nvId="+id;
		//  		listForm.submit();
			}
		}
		 
		 //搭配套餐
		function prePackage (id) {
			if(id!=""){
			  top.Dialog.open({URL:"<%=path%>/business/ProductCollocation.do?act=list&productFid="+id+"&type=1",ID:"a01",Width:1024,Height:768,Title:"搭配套餐"});
		//	listForm.action="ProductCollocation.do?act=list&productFid="+id+"&type=1";
		//	  listForm.submit();
			}
		}
		//首页推荐
		function AjaxUpt(id,type){
			if(id!=''){
			top.Dialog.confirm("如已有首页推荐，将自动取消原有的推荐 ，是否继续?",
			function() {
			  $.post("NetworkVideo.do?act=AjaxUpt", {id:id,type:type},
			   function(data){
			    if(data.succeed='000'){
			      window.location.reload();
			    }
			   }, "json");
			});
		}
	}
		
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：课程管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/NetworkVideo.do?act=list" method="post" >
			<input type="text" name="nvId" value="${nvId }" style="display: none"/>
			<input type="text" name="nvcId" value="${nvcId }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<div style="float: left">
						<a href="javascript:;" onclick="preAdd('')" title="新增"> <span
								class="img_add"></span>
						</a>
						<a id="del_mfp" title="删除"
							href="javascript:;" onclick="doDels()"> <span
								class="img_delete"></span>
						</a>
					</div>
					<div style="float: left">
						网课名称：<input type="text" name="nvNamelike" id="nvNamelike" value="${nvNamelike }"/>&nbsp;
					</div>
					<div style="float: left">
						 <select name="stateStr" id="stateStr">
						 	<option value="">请选择类型</option>
					 		<option value="">全部</option>
					 		<option value="1" <c:if test="${stateStr==1 }">selected="selected"</c:if> >直播</option>
					 		<option value="0" <c:if test="${stateStr==0 }">selected="selected"</c:if>>录播</option>
						 </select>
					</div>
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th height="25" width="5%" align="center" class="DataTable_Field" title="序号 ">序号</th>
						<th height="25" width="10%" align="center" class="DataTable_Field" title="课程图片 ">课程图片</th>
						<th height="25" width="15%" class="DataTable_Field" title="网课名称 ">网课名称</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="简介 ">网课简介</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="销售量 ">销售量 </th>								 
						<th height="25"  align="center" class="DataTable_Field" title="课程价格 ">课程价格</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="课程类型  ">课程类型</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="是否免费">是否免费</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="创建时间 ">创建时间</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="是否限时免费  ">是否限时免费</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="排序 ">排序</th>		
						<th height="25" width="10%" align="center" class="DataTable_Field" title="操作">操作</th>						 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	NetworkVideo po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (NetworkVideo)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="DataTable_Content" align="left">
        	<% 
        		if(po.getNetworkImgpath()!=null&&!po.getNetworkImgpath().equals("")){
        			if(po.getNetworkImgpath().startsWith("http://")){
        	%>
        		<div style="float: left;padding: 5px 5px 5px 5px">
				   <a href="javascript:thumbImgsDiv('<%=po.getNetworkImgpath()%>',0)" >
				      <img src="<%= po.getNetworkImgpath()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
				   </a>
				 </div>
        		  <% }else{
					%>
        		<div style="float: left;padding: 5px 5px 5px 5px">
			   <a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getNetworkImgpath()%>',0)" >
			      <img src="<%=path%>/<%= po.getNetworkImgpath()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
			 </div>
        	<% 
        		}}
        	%>
        </td>
		<td class="DataTable_Content" align="left" title="<%= po.getNetworkName() %>"><%= po.getNetworkName() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getBrief() %>"><%= po.getBrief() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getSaleNum() %>"><%= po.getSaleNum() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getNetworkMoney() %>"><%= po.getNetworkMoney() %></td>								
		<td class="DataTable_Content" align="left"><% if(po.getNetworkType()==0)out.print("录播");else out.print("直播"); %></td>								
		<td class="DataTable_Content" align="left"><% if(po.getIsFree()==0)out.print("否");else out.print("是"); %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCreatetime() %>"><%= po.getCreatetime() %></td>								
		<td class="DataTable_Content" align="left"><% if(po.getIsLimitFree()==0)out.print("否");else out.print("是"); %></td>								
		<td class="DataTable_Content" align="left"><%= po.getSort() %></td>	
		<td  align="left">
		 	<a href="javascript:;" title="修改"
				onclick="preUpdate('<%=po.getId()%>')"> <span class="img_edit"></span>
			</a>
			<% if(po.getIsCatalog()==1){ %>
				<a id="del_mfp" title="课时管理" 
					href="javascript:;" onclick="catalog('<%=po.getId()%>')" > <span
						class="img_history" ></span>
				</a>
			<%} %>
			<a id="del_mfp" title="视频管理"
				href="javascript:;" onclick="catalogVideo('<%=po.getId()%>')"> <span
					class="img_avi" ></span>
			</a>
			<%-- 
			<a href="javascript:;"  title="搭配套餐"
						onclick="prePackage('<%=po.getId()%>')" > <span class="img_cart"></span>
			</a>
			 --%>
			<%-- <% if(po.getIsIndex()==0){%>
				<a href="javascript:;"  title="首页推荐"
							onclick="AjaxUpt('<%=po.getId()%>',1)" > <span class="img_finger"></span>
				</a>
			<%}else{%>
				<a href="javascript:;"  title="取消推荐"
							onclick="AjaxUpt('<%=po.getId()%>',0)" > <span class="img_remove"></span>
				</a>
			<%}%> --%>
			 
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
