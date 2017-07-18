<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.*"%>
<%@page import="com.business.Users.Users"%>
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
	<link href="<%=path%>/js/index.css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
	<!--缩略图样式-->
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
	<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>

<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>

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
	
		//删除数据
		function doDels() {
			var idVal = isSel1();
			if(idVal!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="Users.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="Users.do?act=realdelete&id="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){    
		   top.Dialog.open({URL:"<%=path%>/business/Users.do?act=preAdd",ID:"a1",Width:1024,Height:768,Title:"新增"});
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/Users.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});
			  //listForm.action="Users.do?act=preUpdate&id="+id;
			  //listForm.submit();
			}
		}
		//详情
		function view(id) {
			if(id!=""){
		  	    listForm.action="Users.do?act=view&id="+id;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("nameStr").value="";
			document.getElementById("phone").value="";
			document.getElementById("email").value="";
			document.getElementById("starttime").value="";
			document.getElementById("endtime").value="";
		}
		
		//地址
		function addressList(userId){
			if(userId!=""){
			    top.Dialog.open({URL:"<%=path%>/business/UserAddress.do?act=list&userId="+userId,ID:"a1",Width:1024,Height:768,Title:"收货地址"});
//			    listForm.action="<%=path %>/business/UserAddress.do?act=list&userId="+userId;
//		    	listForm.submit();
			}
		}
		
		//生词本
		function wordsList(userId){
		 if(userId!=""){
		   top.Dialog.open({URL:"<%=path%>/business/NewWords.do?act=list&userId="+userId,ID:"a1",Width:1024,Height:768,Title:"生词本"});
//		    listForm.action="<%=path %>/business/NewWords.do?act=list&userId="+userId;
//		    listForm.submit();
		 }
		}
		
	function getCity_Area(id,id1,type){
		var Fid = $(id).val();
		//alert(type);
		$.post("Users.do?act=province_city_area_List",{Fid:Fid,type:type},
			  function (data){
				$(id1).show();			  	
			  	$(id1).empty();
			  	$(id1).append("<option value=''>请选择</option>");
			  	$(id1).append(data.dataList);
			  	$(id1).setValue();
			  },
		  	 "json"
		);
		addstr();
	}	

	function addstr(){
	    var str= $("#select").find("option:selected").text();
	    var str1= $("#select1").find("option:selected").text()
	    var str2= $("#select2").find("option:selected").text();
	  // 	$("#districtCn").val(str+str1+str2);
	  
	} 
	$(function(){
        top.Dialog.close();
        })
        
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：用户管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/Users.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<div style="float: left;">
					<a href="javascript:;" onclick="preAdd('')" title="新增"> <span
							class="img_add"></span>
					</a>
					  <!-- <a href="javascript:;" onclick="top.Dialog.open({URL:'<%=path%>/system/Users/Users_upload.jsp',Title:'写作',Width:400,Height:200});" >
					  <span class="icon_import">导入</span></a>
						<div class="box_tool_line"></div>  -->
					<a id="del_mfp"
						href="javascript:;" onclick="doDels()" title="批量删除"> <span
							class="img_delete"></span>
					</a>
						<%-- 账号：<input type="text" name="accountStr" id="account" value="${accountStr }"/> --%>
						用户名：<input type="text" name="nameStr" style="width: 80px;" id="nameStr" value="${nameStr }"/>
						手机号：<input type="text" name="phoneStr"  style="width: 80px;" id="phone" value="${phoneStr }"/>
						邮箱：<input type="text" name="emailStr"  style="width: 120px;" id="email" value="${emailStr }"/>&nbsp;
						</div>
						<div style="float: left;">
						  <div style="float: left;">收货地址:</div>
						  <div style="float: left;">
						  <select  name="provinceIdStr" id="select" class="default" onchange="getCity_Area('#select','#select1','city')">
      	 						<option value="">请选择省份</option>
      							${provinceList}
      	 					</select>
      	 					<select name="cityIdStr" id="select1" class="default" onchange="getCity_Area('#select1','#select2','area')" >
      	 						<option value="">请选择</option>
      	 					</select>
      						<select name="areaIdStr" id="select2" class="default" style="display: none;" onchange="addstr()" >
      							<option value="">请选择</option>
      						 </select>
						  </div>
						</div>
						<!--  
						 起始时间：<input onfocus="showCalendar(this)" class="cusDate" type="text" name="starttime" value="${starttime }" id="starttime"  />-<input onfocus="showCalendar(this)" class="cusDate" type="text" name="endtime" value="${endtime }" id="endtime"  />
						-->&nbsp;&nbsp;
						<div style="float: left;">
							<div style="float: left;">
								订单来源：
							</div>
							<div style="float: left;">
								<select name="fromType" selWidth="80" id="fromType">
									<option  value="" <c:if test="${fromType == null}" >selected</c:if> >全部</option>
									<option  value="0" <c:if test="${fromType == 0}" >selected</c:if>>星火</option>
									<option  value="4" <c:if test="${fromType == 4}" >selected</c:if>>知米APP</option>
								</select>
							</div>
						</div>&nbsp;&nbsp;
						<input type="submit" value="查询" />&nbsp;&nbsp; <input
						type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field"></th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="">序号</th>
						<th   align="center" class="DataTable_Field" title=" ">会员头像</th>
						<th height="25"  align="center" class="DataTable_Field" title="">名称</th>								 
						<!-- <th height="25"  align="center" class="DataTable_Field" title=" * 类型：String 长度:32">账号</th>	 -->							 
						<th height="25"  align="center" class="DataTable_Field" title=" ">手机号</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="">注册时间</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="">最后一次在线时间</th>
						<th height="25"  align="center" class="DataTable_Field" title="">注册来源</th>
						<th height="25"  align="center" class="DataTable_Field" title=" ">状态</th>	
						<th height="25"  align="center" class="DataTable_Field" title=" ">操作</th>							 
					</tr>
					 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	Users po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (Users)list.get(i);
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="DataTable_Content"  align="center">
		 <%  if(po.getIcon()!=null&&!po.getIcon().equals("")){
		 %>
		 	<div style="float: center;padding: 5px 5px 5px 5px">
			   <a href="javascript:thumbImgsDiv('<%=po.getIcon()%>',0)" >
			      <img src="<%= po.getIcon()%>" width="24px" height="24px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
			 </div>
		 <%
		 }
		 %>
			 
		</td>
		<td class="DataTable_Content" align="left" title="<%= po.getUsername() %>"><%= CommUtil.isString(po.getUsername()) %></td>								
		<%-- <td class="DataTable_Content" align="left"><%= po.getAccount() %></td> --%>								
		<td class="DataTable_Content" align="left" title="<%= CommUtil.isString(po.getMobile()) %>"><%= CommUtil.isString(po.getMobile()) %></td>								
		<td class="DataTable_Content" align="left" title="<%=CommUtil.isString(po.getCreateTime())%>"><% if(po.getCreateTime()==null)out.print("");else out.print(po.getCreateTime()); %></td>								
		<td class="DataTable_Content" align="left" title="<%=CommUtil.isString(po.getLoginTime())%>"><% if(po.getLoginTime()==null)out.print("");else out.print(po.getLoginTime()); %></td>
		<td class="DataTable_Content" align="left" >
			<%  if(po.getFromType() == 0){
				out.print("星火");
			}else if(po.getFromType() == 4){
				out.print("知米APP");
			}else{
				out.print("未定义类型");
			}%>
		</td>
		<td class="DataTable_Content" align="left" ><% if(po.getIsDisable()==0)out.print("正常");else out.print("禁用"); %></td>
 		<td class="DataTable_Content" align="left">
 			<a href="javascript:;" title="详情修改"
							onclick="preUpdate('<%=po.getId()%>')"> <span class="img_edit"></span>
					</a>
		   	 <a href="javascript:addressList('<%=po.getId()%>')" title="收货地址" > <span class="img_view"></span></a>
				<a id="del_mfp"
					href="javascript:;" onclick="doDelsById('<%=po.getId()%>')" title="删除"> <span
						class="img_delete"></span>
				</a>
			 <a href="javascript:;" onclick="wordsList('<%=po.getId()%>')" title="生词本">
			   <span class="img_page"></span>
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
