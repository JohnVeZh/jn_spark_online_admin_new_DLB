<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.CommonType.CommonType"%>
<%@ page import="com.business.v2.question.TbQuestionReadingQuestionOption" %>
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

        function flush(){
            top.frmright.window.location.reload();
        }

        function back(){
            top.Dialog.close();
        }

        function submitForm() {
			listForm.action="TbQuestionReading.do?act=updateSubject"
			listForm.submit();
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
				   		listForm.action="CommonType.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		//删除单个数据
		function doDelOption(id){
		  if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="TbQuestionReading.do?act=deleteOption&optionId="+id;
				   		listForm.submit()
					}
				)
			}
		}
		//添加preAddOption
        function preAddOption(id) {
            if(id!=""){
                top.Dialog.open({URL:"<%=path%>/business/TbQuestionReading.do?act=preAddOption&questionId="+id,ID:"a001",Width:1024,Height:768,Title:"查看"});
            }
        }

		//修改
		function preUpdate(id) {
			//var idVal = isSel();
			if(id !=""){
			  listForm.action="CommonType.do?act=preUpdate&id="+id;
			  listForm.submit();
			}
		}
		//详情
		function view(id) {
			//var idVal = isSel();
			if(id!=""){
		  	   listForm.action="CommonType.do?act=view&id="+id;
		  		listForm.submit();
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("typeName").value="";
			document.getElementById("sel").value="";
		}
		
		function checkWords(id){
		   if(id!=""){
		  	    listForm.action="CommonWords.do?act=list&ctId="+id;
		  		listForm.submit();
			}
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right" align="left">
					<span>当前位置：配题修改<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/TbQuestionReading.do?act=updateSubject" method="post" onsubmit="return sub(this)">
			<input type="hidden" name="id" value="${questionForm.id }" />
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset>
					<legend>题目基本信息</legend>
					<table class="tableStyle" transMode="true" footer="normal">
						<tr>
							<td width="15%" align="right">
								题目名称：
							</td>
							<td width="85%" align="left" >
								<%--<input type="text" name="title" id="title" style="width: 100%" <c:if test="${ not empty questionForm.title}"> value="${questionForm.title}" </c:if> />--%>
									<textarea rows="1" cols="1" style="width: 80%" name="title" id="title" ><c:if test="${ not empty questionForm.title}">${questionForm.title}</c:if></textarea>
							</td>

						</tr>
						<tr>
							<td width="10%" align="right">
								题号：
							</td>
							<td width="85%" align="left" colspan="2">
								<input type="text" name="sortOrder" id="sortOrder" style="width: 20%" placeholder="只能是数字" value="${questionForm.sortOrder}"
									   onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,'')"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right">
								题目解析：
							</td>
							<td width="85%" align="left" colspan="2">
								<%--<input type="text" name="analysis" id="analysis" value="${questionForm.analysis}" iptClass" style="width: 95%" "/>--%>
								<textarea rows="1" cols="1" style="width: 80%" name="analysis" id="analysis" >${questionForm.analysis}</textarea>
							</td>
						</tr>
					</table>
				</fieldset>
			</div>
		</form>
		<div class="box1 center" whiteBg="true" id="form1">
			<fieldset>
				<legend>选项列表</legend>
				<div class="box2" panelTitle="功能面板" roller="false">
					<%--<input type="text" name="parentid" value="${parentid }" id="parentid" style="display: none"/>--%>
					<table style="width:100%">
						<tr>
							<td align="left"><a href="javascript:;" onclick="preAddOption('${questionForm.id }')" title="新增" > <span
									class="img_add"></span>
							</a>
							</td>
						</tr>
					</table>
				</div>
				<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
					<tr >
						<th width="3%" height="25" align="center" class="DataTable_Field">
						</th>
						<th width="5%" height="25"  align="center" class="DataTable_Field" title="选项">选项</th>
						<th height="25"  align="center" class="DataTable_Field" title="选项内容">选项内容</th>
						<th height="25" width="5%" align="center" class="DataTable_Field" title="是否正确答案">答案</th>
						<th height="25"  width="6%" align="center" align="center" class="DataTable_Field" title="" >操作</th>
					</tr>
					<%
						List list=lc.getList();
						TbQuestionReadingQuestionOption po = null;
						for (int i = 0; i < list.size(); i++)
						{
							po = (TbQuestionReadingQuestionOption) list.get(i);
					%>
					<tr  >
						<td align="center"><input type="checkbox" id="id" value="<%=po.getId()%>" ></td>
						<td class="DataTable_Content" align="left" title="<%= po.getPrefix() %>"><%= po.getPrefix() %></td>
						<td class="DataTable_Content" align="left" title="<%= po.getContent() %>"><%= po.getContent() %></td>
						<td class="DataTable_Content" align="left" >

								   <% if (po.getIsAnswer().equals("1")){%> 是 <%}%>
								   <% if (po.getIsAnswer().equals("0")){%> 否 <%}%>

						</td>
						<td>
							<a id="del_mfp" href="javascript:;" title="删除" onclick="doDelOption('<%=po.getId()%>')">
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
							<%@include file="../../../include/listpage.jsp"%>
						</div>
					</div>
				</div>
			</fieldset>
			<table class="tableStyle"
				   style="width: 800px; margin: 0px auto; border: none"
				   formMode="true">
				<tr>
					<td colspan="4" style="border: none;" align="center">
						<input type="button" value=" 提 交 " onclick="submitForm()"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						<input type="reset" value=" 关闭 " onclick="back()" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					</td>
				</tr>
			</table>
		</div>
	</div>
	</body>
</html>
