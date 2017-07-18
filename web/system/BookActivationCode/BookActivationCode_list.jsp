<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.BookActivationCode.BookActivationCode"%>
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
				   		listForm.action="BookActivationCode.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){       
		   top.Dialog.open({URL:"<%=path%>/business/BookActivationCode.do?act=preAdd",ID:"a10",Width:1024,Height:768,Title:"新增"});
		// listForm.action="BookActivationCode.do?act=preAdd"
	   //		listForm.submit();
		}
		//修改
		function preUpdate(id) {
			//var idVal = isSel1();
			if(id !=""){
			top.Dialog.open({URL:"<%=path%>/business/BookActivationCode.do?act=preUpdate&id=" + id,ID:"a1",Width:1024,Height:768,Title:"修改"});
		//  listForm.action="BookActivationCode.do?act=preUpdate&id=" + id;
		//	  listForm.submit();
			}
		}
		//详情
		function view(id) {
			//var idVal = isSel();
			if(id !=""){
			 top.Dialog.open({URL:"<%=path%>/business/BookActivationCode.do?act=view&id=" + id,ID:"a1",Width:1024,Height:768,Title:"详情"});
		//  listForm.action="BookActivationCode.do?act=view&id=" + id;
		 //	listForm.submit();
			}
		}
		//全部导出
		function findxml(type){
				top.Dialog.confirm("确认导出全部数据。",
				   	function() {
				   	//获取查询条件
				   		var productId=$('#productId').val();
				   		var userId=$('#userId').val();
				   		var useRegion=$('#region').val();
				   		var createtime=$('#createtime').val();
				   		var useTime=$('#useTime').val();
				   		var codes=$('codes').val();
				   		
				   		$.post("BookActivationCode.do?act=Findexcl&productId="+productId+"&userId="+userId+"&useRegion="+useRegion+"&createtime="+createtime+"&useTime="+useTime+"&codes="+codes+"&type="+type,
						function (data){
							 if(data.result){
							 	location.href= "<%=path %>/"+data.path;
							 }else{
							    alert("导出失败！");
							 }
						  },
						  "json"
						);
					}
				)
		}
		//批量导出数据
		function excel(type){
			var idVal = isSel1();
			if(idVal!=""){
				top.Dialog.confirm("确认导出已选择的数据。",
				   	function() {
				   		$.post("BookActivationCode.do?act=excl"+idVal+"&type="+type,null,
						function (data){
							 if(data.result){
							 	location.href= "<%=path %>/"+data.path;
							 }else{
							    alert("导出失败！");
							 }
						  },
						  "json"
						);
					}
				)
			}
		}
		//清空查询数据
		function qing(){
		    document.getElementById("createtime").value="";
		    document.getElementById("useTime").value="";
		    document.getElementById("region").value="";
			document.getElementById("productId").value="";
		}
		function export4() {
			top.Dialog.open({URL:'<%=path%>/system/BookActivationCode/BookCodeFour.jsp',Title:'导入四级激活码',Width:400,Height:220});
		}
	   function export6() {
		    top.Dialog.open({URL:'<%=path%>/system/BookActivationCode/BookCodeSix.jsp',Title:'导入六级激活码',Width:400,Height:220});
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
		<form name="listForm" scope="request" action="<%=path%>/business/BookActivationCode.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
					<a href="javascript:;" onclick="preAdd('')" title="新增"> 
					<span class="img_add"></span>
					</a>
					<a href="javascript:;" onclick="excel(0)" title="批量导出"> 
					<span class="img_export" ></span>
					</a>
					<a href="javascript:;" onclick="findxml(0)" title="全部导出"> 
					<span class="img_export"></span>
					</a>
					<a href="javascript:;" onclick="export4()" title="四级激活码导入">
					<span class="img_xls"></span>
					</a>
					<a href="javascript:;" onclick="export6()" title="六级激活码导入">
					<span class="img_xls"></span>
					</a>
						生成时间：<input  class="date" type="text" name="creatime" value="${createtime }" id="createtime"  />
						使用时间：<input  class="date" type="text" name="usetime" value="${usetime }" id="useTime"  />	  
						使用地区：<input type="text" id="region" name="region" value="${region }"/>
						图书名称：<input type="text" id="productId" name="productid" value="${productid }"/>
						激活码：<input type="text" id="code" name="code" title="请输入未加密前激活码"  value="${code }"/>
						<input type="submit" value="查询" />&nbsp;&nbsp; 
						<input type="button" value="清空" onclick="qing()" />
					</td>
				</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
				        <th width="5%">序号</th>
				        <th height="25"  align="center" class="DataTable_Field" title="激活码 * 类型：String 长度:30">激活码 </th>	
				        <th height="25"  align="center" class="DataTable_Field" title="关联图书 * 类型：String 长度:200">关联图书</th>
				        <th height="25"  align="center" class="DataTable_Field" title="生成日期 * 类型：String 长度:50">生成日期</th>
				        <th height="25"  align="center" class="DataTable_Field" title="使用时间 * 类型：String 长度:50">使用时间 </th>
				        <th height="25"  align="center" class="DataTable_Field" title="使用地区 * 类型：String 长度:200">使用地区 </th>
				        <th height="25"  align="center" class="DataTable_Field" title="使用账号 * 类型：String 长度:32">使用账号</th>
						
						<th height="25"  align="center" class="DataTable_Field" title="使用次数 * 类型：int 长度:10">使用次数 </th>	
						<th height="25"  align="center" class="DataTable_Field" title="是否已导出  0:否  1：是 * 类型：int 长度:10">是否已导出 </th>								 
						<th height="25"  align="center" class="DataTable_Field" title="生成人 * 类型：String 长度:32">生成人</th>								 
						<!--  <th height="25"  align="center" class="DataTable_Field" title="是否使用  0：否  1：是 * 类型：int 长度:10">是否使用</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="是否删除 0:否 1:是 * 类型：int 长度:10">是否删除 </th>		-->						 
						<th height="25"  align="center" class="DataTable_Field" >操作</th>								 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	BookActivationCode po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (BookActivationCode)list.get(i);
		String pName = Tool.getValue("select p_name from product where id='"+po.getProductId()+"'");
		String mobile = Tool.getValue("select mobile from users where id='"+po.getUserId()+"'");
		String name = Tool.getValue("select name from sys_user where id='"+po.getSysUserId()+"'");
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="DataTable_Content" align="left" title="<%= po.getCode() %>"><%= po.getCode() %></td>	
        <td class="DataTable_Content" align="left" title="<%=pName  %>"><%=pName  %></td>	
        <td class="DataTable_Content" align="left" title="<%= po.getCreatetime() %>"><%= po.getCreatetime() %></td>
        <td class="DataTable_Content" align="left" title="<%= po.getUseTime() %>"><%= po.getUseTime() %></td>
        <td class="DataTable_Content" align="left" title="<%= po.getUseRegion() %>"><%= po.getUseRegion() %></td>
        <td class="DataTable_Content" align="left" title="<%=mobile  %>"><%=mobile  %></td>
		<td class="DataTable_Content" align="left" title="<%= po.getUseNum() %>"></td>
		<td class="DataTable_Content" align="left">
		<% if(po.getIsExport() ==0) out.print("否"); else out.print("是");  %>
		</td>	
		<td class="DataTable_Content" align="left" title="<%=name  %>"><%=name  %></td>								
		<td>
		<a href="javascript:;" title="查看" onclick="view('<%=po.getId()%>')"> 
		<span class="img_view"></span></a>
		<a id="del_mfp" title="删除" href="javascript:;" onclick="doDels()">
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
					<%@include file="../../include/listpage.jsp"%>
					</div>
				</div>
			</div>
				<div class="diverror">友情提示: <span>只可以导入XLS、XLSX、TXT文件，格式为一列竖排，内容为激活码</span></br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
		</form>
	</div>
	</body>
</html>
