<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.easecom.common.util.CommUtil"%>
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
<script type="text/javascript" src="<%=path%>/libs/js/table/detailTable.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--3.3框架必需end-->

<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>

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



		//添加
		function preAdd(videoId,ncId){
            top.Dialog.open({URL:"<%=path%>/business/Dlb/message.do?act=preAdd&a="+Math.random(),ID:"a1",Width:1024,Height:768,Title:"新增"})
		}

		//修改
		function preUpdate(id) {
            top.Dialog.open({URL:"<%=path%>/business/Dlb/message.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"})
        }

		//清空查询数据
		function qing(){
			document.getElementById("ncNamelike").value="";
		}

		function del(id) {
            top.Dialog.confirm("您确信要删除吗?",
                function() {
                    listForm.action="/business/Dlb/message.do?act=del&id="+id;
                    listForm.submit();
                }
            )
        }

        function view(id) {
            top.Dialog.open({URL:"<%=path%>/business/Dlb/message.do?act=view&id="+id,ID:"a1",Width:1024,Height:768,Title:"查看"})

        }

	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right">
					<span>当前位置：网课视频列表<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/Dlb/message.do?act=list" method="post" >
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
						推送标题：<input type="text" name="titleCondition" id="title" value="${titleCondition }"/>&nbsp;
					</div>

                    <div style="float: left">
                        添加时间：<input type="text" name="creatTimeStart" id="creatTimeStart" value="${creatTimeStart }" class="date" dateFmt="yyyy-MM-dd HH:mm:ss"/>&nbsp;--
                        <input type="text" name="creatTimeEnd" id="creatTimeEnd" value="${creatTimeEnd }" class="date" dateFmt="yyyy-MM-dd HH:mm:ss"/>&nbsp;
                    </div>
                        <div style="float: left">
                            推送时间：<input type="text" name="pushTimeStart" id="pushTimeStart" value="${pushTimeStart }" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" />&nbsp;--
                            <input  type="text" name="pushTimeEnd" id="pushTimeEnd" value="${pushTimeEnd }" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" />&nbsp;
                        </div>
                        <div style="float: left">
                            添加人：<input type="text" name="createBy" id="createBy" value="${createBy }"/>
                        </div>
						&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" />&nbsp;&nbsp;
					</tr>
				</table>
			</div>
            <table  class="detailTable" mode="list" useCheckbox="true"  selectRowButtonOnly="false" id="listTable">
					<tr >
						<th height="15" width="1" align="center" class="DataTable_Field" title="序号 ">序号</th>
						<th height="15" width="10" align="center" class="DataTable_Field" title="推送标题 ">推送标题</th>
						<th height="15" width="30" align="center" class="DataTable_Field" title="推送内容 ">推送内容</th>
						<th height="15" width="8" align="center" class="DataTable_Field" title="添加时间 ">添加时间</th>
						<th height="15" width="5" align="center" class="DataTable_Field" title="添加人">添加人</th>
						<th height="15" width="8" align="center" class="DataTable_Field" title="推送时间 ">推送时间 </th>
						<th height="15" width="8" align="center" class="DataTable_Field" title="推送时间 ">状态 </th>
                        <th height="15" width="8" align="center" class="DataTable_Field" title="操作 ">操作</th>
					</tr>
		            <c:forEach items="${ml}" var="c" varStatus="s">
                        <tr>
                            <td class="DataTable_Content" align="left">${s.index+1 }</td>
                            <td class="DataTable_Content" align="left"title="${c.title}">${c.title}</td>
                            <td class="DataTable_Content" align="left" title="${c.intro}">${c.intro}</td>
                            <td class="DataTable_Content" align="left" title="<fmt:formatDate value="${c.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" >
                                <fmt:formatDate value="${c.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td class="DataTable_Content" align="left" title="${c.createBy}">${c.createBy}</td>
                            <td class="DataTable_Content" align="left" title="<fmt:formatDate value="${c.pushTime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
                                <fmt:formatDate value="${c.pushTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td class="DataTable_Content" align="left" >
								<c:if test="${c.pushStatus==0}">未推送</c:if>
								<c:if test="${c.pushStatus==1}">已推送</c:if>
							</td>

                            <td class="DataTable_Content" align="left" title="操作">
                                 <c:choose>
                                     <c:when test="${c.pushStatus==0 && c.delFlag==0}">

										 <a href="javascript:;" title="详情修改"
											onclick="preUpdate('${c.id}')"> <span class="img_edit"></span>
										 </a>

										 <a id="del_mfp"
											href="javascript:;" onclick="del('${c.id}')" title="删除"> <span
												 class="img_delete"></span>
										 </a>
                                     </c:when>
                                     <c:otherwise>
										 <a href="javascript:;" onclick="view('${c.id}')" title="查看"> <span class="img_view"></span>
										 </a>
                                     </c:otherwise>

                                 </c:choose>
                            </td>
                        </tr>

                    </c:forEach>
	<%--<tr  >
		<td  align="left">
		 	<a href="javascript:;" title="修改"
				onclick="preUpdate('<%=po[7].toString()%>')"> <span class="img_edit"></span>
			</a>
			<a id="del_mfp" title="视频管理"
				href="javascript:;" onclick="catalogVideo('<%=po[8].toString()%>')"> <span
					class="img_avi" ></span>
			</a>
            <a id="del_mfp" title="绑定作业"
				href="javascript:;" onclick="ncState('<%=po[7]%>','<%=Integer.parseInt(String.valueOf(po[2]))%>')" > <span
					class="img_history" ></span>
			</a>
			<a id="del_mfp" title="删除"
				href="javascript:;" onclick="doDelsById('<%=po[7]%>')"> <span
					class="img_delete"></span>
			</a>
		</td>
 	</tr>--%>

			</table>
			 <div class="box_tool_min padding_top2 padding_bottom2 padding_right">
			<div class="center">
				<div class="right">
					<%@include file="../../../include/listpage.jsp"%>
					</div>
				</div>
			</div>
		</form>
	</div>
		<!-- 图片展示div -->
<div id="imgsDiv" style="display: none" ></div>
	</body>
</html>
