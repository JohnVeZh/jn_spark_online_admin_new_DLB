<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.Product.Product"%>
<%@page import="com.easecom.common.util.Tool"%>
<%
	String path = request.getContextPath();
	path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <!--3.3框架必需start-->

<script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->
<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>

	<link href="<%=path%>/js/index.css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
	<!--缩略图样式-->
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
	<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>
	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
		$(function(){
			if(${msg=="200"}){
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
				   		listForm.action="Product.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="Product.do?act=realdeleteById&pId="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加

		function preAdd(){  
			var pTypeId = $("#pTypeId").val();
		    if(pTypeId!=""){
		      //  listForm.action="Product.do?act=preAdd";
		  	  //	listForm.submit();
		        top.Dialog.open({URL:"<%=path%>/business/Product.do?act=preAdd&pTypeId="+pTypeId,ID:"a1",Width:1024,Height:768,Title:"新增"});

		    }else{
		    	alert("请先选取左侧类别");
		    }
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			 var pTypeId = $("#pTypeId").val();
			 top.Dialog.open({URL:"<%=path%>/business/Product.do?act=preUpdate&id="+id+"&pTypeId="+pTypeId,ID:"a1",Width:1024,Height:768,Title:"修改"});
			}
		}
		//详情
		function view() {
			var idVal = isSel();
			if(idVal!=""){
		  	   listForm.action="Product.do?act=view"+idVal;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
			document.getElementById("sel").value="";
		}
		
		//搭配套餐
		function prePackage (id) {
			//var pTypeId=$("#pTypeId").val();
			if(id!=""){
			  top.Dialog.open({URL:"<%=path%>/business/ProductCollocation.do?act=list&productFid="+id+"&type=0",ID:"a1",Width:1024,Height:768,Title:"搭配套餐"});
			 // listForm.action="ProductCollocation.do?act=list&productFid="+id+"&type=0";
			 // listForm.submit();
			}
		}
		//搭配试卷
		function preMatched(id){
			if(id != ""){
			top.Dialog.open({URL:"<%=path%>/business/ProductMatchedPaper.do?act=list&productId="+id,ID:"a1",Width:1024,Height:768,Title:"搭配试卷"});
			 //	listForm.action="ProductMatchedPaper.do?act=list&productId="+id;
			 // listForm.submit();
			}
		}
		//搭配网课
		function preNetwork(id){
		  if(id != ""){
		  top.Dialog.open({URL:"<%=path%>/business/ProductNetworkVideo.do?act=list&productId="+id,ID:"a1",Width:1024,Height:768,Title:"搭配网课"});
		//	listForm.action="ProductNetworkVideo.do?act=list&productId="+id;
		 //   listForm.submit();
		  }
		}
		 
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：商品管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/Product.do?act=list" method="post">
			<input type="text" name="pTypeId" id="pTypeId" value="${pTypeId }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<a href="javascript:;" onclick="preAdd('')" title="新增"> <span
							class="img_add"></span>
					</a>
					<a id="del_mfp" title="批量删除"
						href="javascript:;" onclick="doDels()"> <span
							class="img_delete"></span>
					</a>
					
						 名称：<input type="text" name="nameStr" id="nameStr" value="${nameStr }"/>
						批次：<input type="text" name="batchTime" id="batchTime" value="${batchTime }"/>
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
							<th width="5%" height="25"  align="center" class="DataTable_Field" title="">序号</th>
							<th height="25"  align="center" class="DataTable_Field" title="产品名称">产品名称</th>	
							<th height="25" width="100px" align="center" class="DataTable_Field" >列表图片</th>
							<th height="25" width="100px" align="center" class="DataTable_Field" >封面图片</th>
							<%-- <th height="25"  align="center" class="DataTable_Field" >详情多图片</th>--%>
							<th height="25" width="50px" align="center" class="DataTable_Field" title="原价 ">原价</th>
							<th height="25" width="50px" align="center" class="DataTable_Field" title="现价 ">现价</th>
							<th height="25" width="50px" align="center" class="DataTable_Field" title="邮费 ">邮费</th>
							<th height="25" width="50px" align="center" class="DataTable_Field" title="图书类型">类型</th>
							<th height="25" width="50px" align="center" class="DataTable_Field" title="图书批次">批次</th>
							<th height="25" width="150px" align="center" class="DataTable_Field" title="创建时间 ">创建时间</th>
							<th width="80px">创建人</th>
							<th height="25" width="130px"  align="center" class="DataTable_Field" title="">操作</th>
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	Product po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (Product)list.get(i);
		String typeName = Tool.getValue("select type_name from product_type where id='"+po.getpTypeId()+"'");
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getpName() %>"><%= po.getpName() %></td>
		<td class="DataTable_Content"  align="center">
		 <div style="float: left;padding: 5px 5px 5px 5px">
		   <% if(po.getpListImg().startsWith("http")){
		   %>
		    	<a href="javascript:thumbImgsDiv('<%=po.getpListImg()%>',0)" >
			      <img src="<%= po.getpListImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
		   <% }else{
		   %>
			   <a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getpListImg()%>',0)" >
			      <img src="<%=path%>/<%= po.getpListImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
		   <%} %>
		 </div>
		</td>
		<td class="DataTable_Content"  align="center">
			<div style="float: left;padding: 5px 5px 5px 5px">
			 <% if(po.getpCoverImg().startsWith("http://")){
		   %>
		    	<a href="javascript:thumbImgsDiv('<%=po.getpCoverImg()%>',0)" >
					<img src="<%= po.getpCoverImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			    </a>
		   <% }else{
		   %>
		   		<a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getpCoverImg()%>',0)" >
					<img src="<%=path%>/<%= po.getpCoverImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   	</a>
		   <%} %>
			   
			</div>
		</td><%-- 
		<td class="DataTable_Content"  align="center">
			<% if(po.getViewImgs()!=null && !po.getViewImgs().equals("")){
				String imgs[] = po.getViewImgs().split(",");
				  
				  for(int j = 0;j<imgs.length;j++){
			%>
			<div style="float: left;padding: 5px 5px 5px 5px">
			   <a href="javascript:thumbImgsDivList('<%=po.getViewImgs()%>',<%=j %>,'<%=path%>')" >
				<img src="<%=path%>/<%= imgs[j]%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
			</div>
			<%  
				  }
				}
			%>
			
		</td>--%>
		<td class="DataTable_Content" align="left" title="<%= po.getpOriginalPrice() %>"><%= po.getpOriginalPrice() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getpPresentPrice() %>"><%= po.getpPresentPrice() %></td>
		<td class="DataTable_Content" align="left" title="<%= po.getpPostage() %>"><%= po.getpPostage() %></td>									
		<td class="DataTable_Content" align="left" title="<%=typeName  %>"><%=typeName  %></td>
		<td class="DataTable_Content" align="left" title="<%=po.getBatchTime()  %>"><%=po.getBatchTime()  %></td>
		<td class="DataTable_Content" align="left" title="<%= po.getpCreatetime() %>"><%= po.getpCreatetime() %></td>	
		<td class="DataTable_Content" align="left">
		<% out.print(Tool.getValue("select name from sys_user where id='"+po.getSysUserId()+"'")); %>
		</td>	
		<td  align="center">
		    <a href="javascript:;" title="修改"
				onclick="preUpdate('<%=po.getId()%>')"> <span class="img_edit"></span>
			</a>
			<%-- 
				 <a href="javascript:;" title="搭配套餐"
				onclick="prePackage('<%=po.getId()%>')"> <span class="img_folder_doc"></span>
			</a>
			 --%>
			 <a href="javascript:;" title="试卷配套"
				onclick="preMatched('<%=po.getId()%>')"> <span class="img_page"></span>
			</a>
			<a href="javascript:;" onclick="preNetwork('<%=po.getId()%>')" title="网课配套" >
			   <span class="img_network"></span>
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
			<div class="box_tool_min padding_top2 padding_bottom2 padding_right ">
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
