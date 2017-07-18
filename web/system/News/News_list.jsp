<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.common.util.CommUtil"%>
<%@page import="com.business.News.News"%>
<%@ page import="com.easecom.common.util.DateUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
				   		listForm.action="News.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelById(id){
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="News.do?act=realdeleteById&newId="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){ 
		   top.Dialog.open({URL:"<%=path%>/business/News.do?act=preAdd",ID:"a1",Width:1024,Height:768,Title:"新增"});      
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/News.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});
			}
		}
		//详情
		function view(id) {
			if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/News.do?act=view&id="+id,ID:"a1",Width:1024,Height:768,Title:"查看"});
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("titleStr").value="";
			document.getElementById("authorStr").value="";
			document.getElementById("starttimeStr").value="";
			document.getElementById("endtimeStr").value="";
			
		}
		
		//置顶
		function toTop(id,enable){
		  if(id!=""){
		   		listForm.action="News.do?act=toTop&id="+id+"&enable="+enable;
				listForm.submit();
			}
		}
		//是否推荐
		function enable(id,enable) {
			if(id!=""){
		   		listForm.action="News.do?act=enable&id="+id+"&enable="+enable;
				listForm.submit();
			}
		}
		//相关评论
		function comment(id){
		  if(id != ""){
		   top.Dialog.open({URL:"<%=path%>/business/NewsMessage.do?act=list&newsId="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});
//			listForm.action="NewsMessage.do?act=comment&newsId="+id;
//		    listForm.submit();
		  }
		}
	
		function release(id,ptime) {
			$("#scrollContent").mask("信息提交中...");
			$.ajax({
            type: 'POST',
            url: 'News.do?act=release',
            data: {'id':id,'ptime':ptime},
            dataType:"json",
            success: function (data) {
				$("#scrollContent").unmask();
				if(!data.result){
					top.Dialog.alert("发布失败!");
				}
                window.location.reload();
            },
            error:function () {
                top.Dialog.alert("服务器异常，请稍后再试！");
				window.location.reload();
            }
        });
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：新闻管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/News.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td>
						<div  style="float: left;">
						<a href="javascript:;" onclick="preAdd('')" title="新增"> <span
							class="img_add"></span>
					</a>
					<a id="del_mfp"
						href="javascript:;" onclick="doDels()" title="删除"> <span
							class="img_delete"></span>
					</a>
						</div>
					<div style="float: left;">
						标题：<input type="text" id="titleStr" name="titleStr" value="${titleStr }"/>
						发布人：<input type="text" id="authorStr" value="${author }" name="authorStr"/>
						起始时间：<input  class="date" type="text" name="starttimeStr" value="${starttime }" id="starttimeStr"  />-<input  class="date" type="text" name="endtimeStr" value="${endtime }" id="endtimeStr"  />
					</div>
						<div style="float: left;">
							<span style="float: left;">发布状态：</span><div style="float: left;">
							<select id="searchStetus" name="searchStetus">
								<option value="all" <c:if test="${searchStetus == 'all'}">selected ="selected" </c:if> >不限</option>
								<option value="0" <c:if test="${searchStetus == '0'}">selected ="selected" </c:if> >草稿</option>
								<option value="1" <c:if test="${searchStetus == '1'}">selected ="selected" </c:if> >已发布</option>
							</select>
						</div>
						</div>
						<div style="float: left;">
							&nbsp;&nbsp;<input type="submit" value="查询" />&nbsp;&nbsp;
							<input type="button" value="清空" onclick="qing()" />
						</div>
					</td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="序号 ">序号 </th>	
						<th height="25"  align="center" class="DataTable_Field" title="">列表图片</th>	
						<th height="25"  align="center" class="DataTable_Field" title="标题 ">标题 </th>								 
						<th height="25"  align="center" class="DataTable_Field" title="发布时间 ">发布时间</th>
						<th height="25"  align="center" class="DataTable_Field" title="发布状态 ">发布状态</th>
						<th height="25"  align="center" class="DataTable_Field" title="发布人">发布人</th>
						<th height="25"  align="center" class="DataTable_Field" title="阅读量">阅读量</th>
						<th height="25"  align="center" class="DataTable_Field" title="是否置顶">是否置顶</th>
						<th height="25"  align="center" class="DataTable_Field" title="置顶时间">置顶时间</th>
						<th height="25"  align="center" class="DataTable_Field" title="是否推荐">是否推荐</th>
						<th height="25"  align="center" class="DataTable_Field" title="推荐时间">推荐时间</th>
						<th height="25"  align="center" class="DataTable_Field" title="操作">操作</th>								 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	News po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (News)list.get(i);
		String show = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //格式化为 hhmmss
		Date currTime = new Date();
		Date ptime = null;
		if(po.getIsShow()==0){
			show = "草稿待发布";
		}else{
			try{
				ptime = sdf.parse(po.getPtime());
				if(ptime.before(currTime)){
					show = "发布已展示";
				}else{
					show = "发布待展示";
				}
			}catch (Exception e){
				show = "数据错误，发布时间未正确填写！";
			}
		}
%>
	<tr >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="DataTable_Content" align="left">
        <% if(po.getPhoneImg()!=null&&!po.getPhoneImg().equals("")){
        	if(po.getPhoneImg().startsWith("http")){
        %>
        	<div style="float: left;padding: 5px 5px 5px 5px">
			   <a href="javascript:thumbImgsDiv('<%=po.getPhoneImg()%>',0)" >
			      <img src="<%= po.getPhoneImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
			 </div>
          <% }else{
			%>
        	<div style="float: left;padding: 5px 5px 5px 5px">
			   <a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getPhoneImg()%>',0)" >
			      <img src="<%=path%>/<%= po.getPhoneImg()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
			 </div>
        <%
        	}}
         %>
        </td>
		<td class="DataTable_Content" align="left" title="<%=CommUtil.isString(po.getTitle())%>"><%= po.getTitle() == null ? "":po.getTitle() %></td>								
		<td class="DataTable_Content" align="left" title="<%=CommUtil.isString(po.getPtime())%>"><%= po.getPtime() == null ? "":po.getPtime() %></td>

		<td class="DataTable_Content" align="left" title="<%= show%>"><%= show%></td>
		<td class="DataTable_Content" align="left" title="<%= po.getAuthor() %>"><%= po.getAuthor() %>
		</td>
		<td class="DataTable_Content" align="left" title="<%=po.getCount() %>"><%=po.getCount() %></td>
		<td class="DataTable_Content" align="left">
		<% if(po.getIsTop()==0)out.print("否"); else out.print("是"); %>
		
		</td>	
		<td class="DataTable_Content" align="left" title="<%=CommUtil.isString(po.getTopTime())%>"><%= po.getTopTime()== null ? "":po.getTopTime() %></td>
		<td class="DataTable_Content" align="left">
		<% if(po.getIsRecommend()==0)out.print("否"); else out.print("是"); %>
		</td>			
		<td class="DataTable_Content" align="left" title="<%=CommUtil.isString(po.getRecommendTime())%>"><%=po.getRecommendTime()== null ? "":po.getRecommendTime() %></td>				
		<td  align="left">
			 
		<a href="javascript:;" title="修改"	onclick="preUpdate('<%=po.getId()%>')">
			  <span class="img_edit"></span>
			</a>
		  <a href="javascript:;" title="查看" onclick="view('<%=po.getId()%>')"> <span class="img_view"></span>
		  </a>
		  <% if(po.getIsShow()>0){%>
		<%
		 if(po.getIsTop()==1 ){
		 %>
		    <!--取消置顶  -->
			<a title="取消置顶" href="javascript:;" onclick="toTop('<%=po.getId()%>','0')"><span class="img_jing"></span></a>
		<% 
		 }else{
		%>
		   <!-- 置顶 -->
		   <a title="置顶" href="javascript:;" onclick="toTop('<%=po.getId()%>','1')"><span class="img_btn_up2"></span></a>
		 <%
		 }
		%>	
		<%
      	 if(po.getIsRecommend()==1){
		 %> <!-- 取消推荐 -->
			<a title="取消推荐" href="javascript:;" onclick="enable('<%=po.getId()%>','0')"> 
			<span class="img_no"></span>
			</a>
		<% 
		 }else{
		 %>  
		    <!-- 推荐 -->
			 <a title="推荐"  href="javascript:;" onclick="enable('<%=po.getId()%>','1')"> 
			<span class="img_finger"></span>
			</a>
		 <%
		 }
		%>	
		<!-- 相关评论 -->
		<a href="javascript:;" title="相关评论" onclick="comment('<%=po.getId()%>')"><span class="img_item"></span></a>
			<%  }else{%>
			<a href="javascript:;" title="发布" onclick="release('<%=po.getId()%>','<%=po.getPtime()%>')"><span class="img_unknown"></span></a>
			<% }%>
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
		</form>
	</div>
		<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
	</body>
</html>
