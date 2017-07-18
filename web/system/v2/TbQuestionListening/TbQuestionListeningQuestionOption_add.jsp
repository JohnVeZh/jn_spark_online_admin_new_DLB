<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.CommonType.CommonType"%>
<%@ page import="com.business.v2.question.TbQuestionListeningQuestionOption" %>
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
			listForm.action="TbQuestionListening.do?act=addOption"
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
	
		//删除单个数据
		function doDelOption(id){
		  if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="CommonType.do?act=realdelete&id="+id;
						listForm.submit();
					}
				)
			}
		}
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right" align="left">
					<span>当前位置：选项新增<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="<%=path%>/business/TbQuestionListening.do?act=addOption" method="post" onsubmit="return sub(this)">
			<input type="hidden" name="questionId" value="${questionId }" />
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset>
					<legend>听力题目选项</legend>
					<table class="tableStyle" transMode="true" footer="normal">
						<tr>
							<td width="15%" align="right">
								选项：
							</td>
							<td width="85%" align="left" colspan="2">
								<input type="text" name="prefix" id="title" style="width: 50%" placeholder="单个大写英文字幕" style="text-transform:uppercase;" />
								<span class="star"></span>
							</td>

						</tr>
						<tr>
							<td width="15%" align="right">
								选项内容：
							</td>
							<td width="85%" align="left">
								<%--<input type="text" name="content" id="sortOrder" style="width: 100%"  />--%>
								<textarea rows="1" cols="1" style="width: 100%" name="content" ></textarea>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right">
								是否答案：
							</td>
							<td width="85%" align="left" colspan="2">
								<input type="radio" name="isAnswer" value="1"/>是
								<input type="radio" name="isAnswer" value="0"/>否
								<span class="star"> </span>
							</td>
						</tr>
					</table>
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
		</form>
	</div>
	</body>
</html>
