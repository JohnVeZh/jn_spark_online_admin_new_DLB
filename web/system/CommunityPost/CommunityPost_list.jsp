<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.CommunityPost.CommunityPost"%>
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
				   		listForm.action="CommunityPost.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		function doDelsById(id) {
			if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="CommunityPost.do?act=realdeleteById&postId="+id;
						listForm.submit();
					}
				)
			}
		}
		//添加
		function preAdd(){   
		    top.Dialog.open({URL:"<%=path%>/business/CommunityPost.do?act=preAdd",ID:"a1",Width:1024,Height:768,Title:"添加"});    
		}
		//修改
		function preUpdate(id) {
			if(id!=""){
			 top.Dialog.open({URL:"<%=path%>/business/CommunityPost.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});
			}
		}
		//详情
		function view() {
			var idVal = isSel();
			if(idVal!=""){
		  	   listForm.action="CommunityPost.do?act=view"+idVal;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
			document.getElementById("sel").value="";
		}
		
		//查看评论
		
		function postmessage(id,num){ 
		  // alert(id);      
			if(id!=""){
			 top.Dialog.open({URL:"<%=path%>/business/CommunityPostMessage.do?act=list&postId="+id+"&num="+num,ID:"a1",Width:1024,Height:768,Title:"查看评论"});
//			 listForm.action="CommunityPostMessage.do?act=comment&postId="+id+"&num="+num;
//			 listForm.submit();
			}
		}
		
		
	    //是否推荐
		function enable(id,enable) {
			if(id!=""){
		   		listForm.action="CommunityPost.do?act=enable&id="+id+"&enable="+enable;
				listForm.submit();
			}
		}
		//是否置顶
		function toTop(id,enable){
		   if(id !=""){
		       listForm.action="CommunityPost.do?act=toTop&id="+id+"&enable="+enable;
			   listForm.submit();
		   }
		}
		//点赞、点差
		function good(id,type){
		  if(id!=""){
		   top.Dialog.open({URL:"<%=path%>/business/CommunityPostLikeRecord.do?act=list&id="+id+"&type="+type,ID:"a1",Width:1024,Height:768,Title:"赞/差"});
//			listForm.action="CommunityPostLikeRecord.do?act=list&id="+id;
//		    listForm.submit();
		  }
		}
		//关注
		function concern(id){
		  if(id!=""){
		    top.Dialog.open({URL:"<%=path%>/business/CommunityPostFollowRecord.do?act=list&id="+id,ID:"a1",Width:1024,Height:768,Title:"关注"});
//			 listForm.action="CommunityPostFollowRecord.do?act=list&id="+id;
//		     listForm.submit();
		  }
		}
		
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：社区管理<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/CommunityPost.do?act=list" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
				<table style="width:100%">
				<tr>
					<td><a href="javascript:;" onclick="preAdd('')" title="新增"> <span
							class="img_add"></span>
					</a>
					<a id="del_mfp" title="删除"
						href="javascript:;" onclick="doDels()"> <span
							class="img_delete"></span>
					</a>
						标题：<input type="text" name="Title" id="title" value="${Title }"/>&nbsp;&nbsp;	  
						<input type="submit" value="查询" />&nbsp;&nbsp; 
						<input type="button" value="清空" onclick="qing()" /></td>
					</tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				 
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="id * 类型：String 长度:32">序号</th>
						<th height="25"  align="center" class="DataTable_Field" title="id * 类型：String 长度:32">列表图片</th>
						<%--<th height="25"  align="center" class="DataTable_Field" title="id * 类型：String 长度:32">详情图片</th>--%>
						<th height="25"  align="center" style="width:20%" class="DataTable_Field" title="标题 * 类型：String 长度:200">标题 </th>								 
						<th height="25"  align="center" class="DataTable_Field" title="发布人 * 类型：String 长度:32">发布人</th>
						<th height="25"  align="center" class="DataTable_Field" title="客服展示  * 类型：int 长度:10">客服展示</th>
						<th height="25"  align="center" class="DataTable_Field" title="查看数量 * 类型：int 长度:10">点击数量</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="评论数量 * 类型：int 长度:10">评论数量</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="点赞数量 * 类型：int 长度:10">点赞数量</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="点差数量 * 类型：int 长度:10">点差数量</th>
						<th height="25"  align="center" class="DataTable_Field" title="关注数量 * 类型：int 长度:10">关注数量</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="是否推荐   * 类型：int 长度:10">是否推荐</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="是否置顶   * 类型：int 长度:10">是否置顶</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="是否加精  * 类型：int 长度:10">是否加精</th>								 
						<th height="25"  align="center" class="DataTable_Field" title="发布时间 * 类型：String 长度:50">发布时间 </th>	
						<th height="25"  align="center"  title="发布时间 * 类型：String 长度:50">操作</th>							 
					</tr>
					 
				 		 
			<%
	int sn=lc.getIndex();
	List list=lc.getList();
	CommunityPost po = null;
	for (int i = 0; i < list.size(); i++) 
	{
		po = (CommunityPost)list.get(i);
		String name = Tool.getValue("select name from sys_user where id='"+po.getSysUserId()+"'");
%>
	<tr  >
    	<td align="center"><input type="checkbox" name="id" value="<%=po.getId()%>" onclick="event.cancelBubble=true;"></td>
        <td align="center"><%=++sn%></td>
        <td class="DataTable_Content" align="left">
        <% 
        	if(po.getImgpathList()!=null&&!po.getImgpathList().equals("")){
				if(po.getImgpathList().startsWith("http")){
		%>
			<div style="float: left;padding: 5px 5px 5px 5px">
				<a href="javascript:thumbImgsDiv('<%=po.getImgpathList()%>',0)" >
					<img src="<%= po.getImgpathList()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
				</a>
			</div>
			<% }else{
		%>
        	<div style="float: left;padding: 5px 5px 5px 5px">
			   <a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getImgpathList()%>',0)" >
			      <img src="<%=path%>/<%= po.getImgpathList()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>
			   </a>
			 </div>
        <%
        	}}
         %>
        </td>		
 		<%--<td class="DataTable_Content" align="left">--%>
        <%--<% --%>
        	<%--if(po.getViewImgpath()!=null&&!po.getViewImgpath().equals("")){--%>
				<%--if(po.getViewImgpath().startsWith("http")){--%>
        <%--%>--%>
        	<%--<div style="float: left;padding: 5px 5px 5px 5px">--%>
			   <%--<a href="javascript:thumbImgsDiv('<%=po.getViewImgpath()%>',0)" >--%>
			      <%--<img src="<%= po.getViewImgpath()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>--%>
			   <%--</a>--%>
			 <%--</div>--%>
        <%--<%--%>
        	<%--}else{--%>
         <%--%>--%>
			<%--<div style="float: left;padding: 5px 5px 5px 5px">--%>
				<%--<a href="javascript:thumbImgsDiv('<%=path%>/<%=po.getViewImgpath()%>',0)" >--%>
					<%--<img src="<%=path%>/<%= po.getViewImgpath()%>" width="82px" height="82px" style="border:1px solid #ccc;padding:5px;"/>--%>
				<%--</a>--%>
			<%--</div>--%>
        <%--<% }}%>--%>
        <%----%>
        <%--</td>       --%>
        <td class="td" align="left">
		<%= po.getTitle() %>
		</td>								
		<td class="DataTable_Content" align="left" title="<%=name  %>"><%=name  %></td>
		<td class="DataTable_Content" align="left"><% if(po.getIsShow()==0)out.print("隐藏");else out.print("展示"); %></td>
		<td class="DataTable_Content" align="left" title="<%= po.getSeeNum() %>"><%= po.getSeeNum() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCommentNum() %>"><%= po.getCommentNum() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getLikeNum() %>"><%= po.getLikeNum() %></td>								
		<td class="DataTable_Content" align="left" title="<%= po.getDifferNum() %>"><%= po.getDifferNum() %></td>
		<td class="DataTable_Content" align="left" title="<%= po.getConcernNum() %>"><%= po.getConcernNum() %></td>								
		<td class="DataTable_Content" align="left">
		<% if(po.getIsRecommend()==0)out.print("否");else out.print("是"); %>
		</td>								
		<td class="DataTable_Content" align="left">
		<% if(po.getIsTop()==0)out.print("否"); else out.print("是"); %>
		</td>								
		<td class="DataTable_Content" align="left">
		<% if(po.getIsVary()==0)out.print("否");else out.print("是"); %>
		</td>								
		<td class="DataTable_Content" align="left" title="<%= po.getCreatetime() %>"><%= po.getCreatetime() %></td>								
		<td  align="left">
			
			<a href="javascript:;" title="编辑"
					onclick="preUpdate('<%=po.getId()%>')"> <span class="img_edit"></span>
				</a>
				<a href="javascript:;" title="评论"
					onclick="postmessage('<%=po.getId()%>','<%= po.getLikeNum() %>')"> <span class="img_item"></span>
				</a>
				<a id="del_mfp" title="删除"
					href="javascript:;" onclick="doDelsById('<%=po.getId()%>')"> <span
						class="img_delete"></span>
				</a>
				<%
		      	 if(po.getIsRecommend()==1){
				 %> <!-- 取消推荐 -->
					<a  href="javascript:;" title="推荐" onclick="enable('<%=po.getId()%>','0')"> 
					<span class="img_no"></span>
					</a>
				<% 
				 }else{
				 %>  
				    <!-- 推荐 -->
					 <a  href="javascript:;" title="推荐" onclick="enable('<%=po.getId()%>','1')"> 
					<span class="img_ok"></span>
					</a>
				 <%
				 }
				%>	
				
				<%
				 if(po.getIsTop()==1){
				%>
				<a  href="javascript:;" title="置顶" onclick="toTop('<%=po.getId()%>','0')"> 
				<span class="img_lock"></span>
				</a>
				<% 
				 }else{
				 %>  
				 <a  href="javascript:;" title="置顶" onclick="toTop('<%=po.getId()%>','1')"> 
				<span class="img_finger"></span>
				</a>
				 <%
				 }
				%>
			<%-- <a href="javascript:;" onclick="good('<%=po.getId()%>',0)">赞</a>	
			<a href="javascript:;" onclick="good('<%=po.getId()%>',1)">差</a>	
			<a href="javascript:;" onclick="concern('<%=po.getId()%>')">关注</a> --%>		
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
