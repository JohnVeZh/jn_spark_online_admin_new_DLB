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
	<script type="text/javascript" src="<%=path%>/js/common/reload.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->

 

	<%
		ListContainer lc = (ListContainer) request.getAttribute("lc");
	%>
	<script type="text/javascript">
		$(function(){
			var msg="";//jstl $ { msg}
			if("200"==msg){
				top.Dialog.alert("删除成功.");
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
	
		
			//搭配
		function addProduct(idVal) {
			if(idVal!=""){
			 var pcId = $("#pcId").val();
				$("#scrollContent").mask("表单提交中！");
			 $.post("<%=path%>/business/TbCouponProduct.do?act=addProduct",{pId:idVal,pcId:pcId,type:1},function(data){
               if(data.result){
				   reload("TbCouponProduct.do?act=list");
                   window.location.reload(true,function () {
					   $("#scrollContent").unmask();
				   });
               } 
             });  
			}else{
                top.Dialog.alert("请至少选中一项！");
            }
		}
		function addProducts() {
			var inps = document.getElementsByName('id');
			var idVal = "";
			for(var i = 0; i <inps.length; i++){
				if(inps[i].checked){
					if(idVal==""){
						idVal = inps[i].value;
					}else{
					 	idVal = idVal + "," + inps[i].value;
					}
				}
			}
		if(idVal != ""){
            var pcId = $("#pcId").val();
			$("#scrollContent").mask("表单提交中！");
            $.post("<%=path%>/business/TbCouponProduct.do?act=addProducts",{pId:idVal,pcId:pcId,type:1},function(data){
                if(data.result){
					reload("TbCouponProduct.do?act=list");
                    window.location.reload(true,function () {
						$("#scrollContent").unmask();
					});
                }
            });
            }else{
            top.Dialog.alert("请至少选中一项！");
        }
		}
		function back(){
			top.Dialog.close();
		}
	</script>
	<body>
	<div id="scrollContent">
		<form name="listForm" scope="request" action="<%=path%>/business/TbCouponProduct.do?act=net_list" method="post" >
			<input type="text" name="pcId" id="pcId" value="${pcId }" style="display: none"/>
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<!-- <div style="float: left">
							<a href="javascript:;" title="搭配"
						onclick="addProducts()"> <span class="img_add"></span>
					</a>
					</div> -->
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
						type="button" value="清空" onclick="qing()" />
                        &nbsp;&nbsp; <input type="button" value="批量搭配" onclick="addProducts()" />
                    </td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="20" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="50" height="25"  align="center" class="DataTable_Field" title="序号">序号</th>
						<th height="25"  align="center" class="DataTable_Field" title="网课名称 ">网课名称</th>
						<th height="25"  width="100" align="center" class="DataTable_Field" title="简介 ">网课简介</th>
						<th height="25" width="100" align="center" class="DataTable_Field" title="销售量 ">销售量 </th>
						<th height="25" width="100" align="center" class="DataTable_Field" title="课程价格">课程价格</th>
						<th height="25" width="100" align="center" class="DataTable_Field" title="课程类型  ">课程类型</th>
						<th height="25" width="100" align="center" class="DataTable_Field" title="">操作</th>
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
		<td class="DataTable_Content" align="left" title="<%= po.getNetworkName() %>"><%= po.getNetworkName() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getBrief() %>"><%= po.getBrief() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getSaleNum() %>"><%= po.getSaleNum() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getNetworkMoney() %>"><%= po.getNetworkMoney() %></td>								
		<td class="DataTable_Content" align="left"><% if(po.getNetworkType()==0)out.print("录播");else out.print("直播"); %></td>								
		<td class="DataTable_Content" align="left">
			 <a href="javascript:;" title="搭配"
				onclick="addProduct('<%=po.getId()%>')"> <span class="img_add"></span>
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
	</body>
</html>
