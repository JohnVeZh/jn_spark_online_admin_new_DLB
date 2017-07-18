<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="com.easecom.common.util.ListContainer"%>
<%@page import="com.business.NetworkCourse.NetworkCourse"%>
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

		//判断
		function isSel1(){
			var inps = document.getElementsByName('id');
			var idVal = "";
			var j =0 ;
			for(var i = 0; i <inps.length; i++){
				if(inps[i].checked){
					idVal += inps[i].value + ",";
		            j++;
				}else{
					continue;
				}
			}
		    return idVal; 
		}

        function isSel2() {
            var inpt = document.getElementsByName("name");
            var inps = document.getElementsByName('id');
            var nameVal = "";
            var j =0 ;
            for(var i = 0; i <inps.length; i++){
                if(inps[i].checked){
                    nameVal +=inpt[i].value+";";
                    j++;
                }else{
                    continue;
                }
            }
            return nameVal;
        }

        //删除数据
		function doDels() {
			var idVal = isSel1();
			if(idVal!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="TbQuestionListening.do?act=realdelete"+idVal;
						listForm.submit();
					}
				)
			}
		}
		//删除单个数据
		function doDelById(id){
		  if(id!=""){
				top.Dialog.confirm("您确信要删除吗?",
				   	function() {
				   		listForm.action="TbQuestionListening.do?act=delete&id="+id;
						listForm.submit();
					}
				)
			}
		}
		//作业绑定题目
		function preAdd(){
		    var idVal = isSel1();
		    var nameVal = isSel2();
		    alert(idVal);
            alert(nameVal);
            top.document.getElementById("frmright").contentWindow.document.getElementById("value").value 	= idVal;
            top.document.getElementById("frmright").contentWindow.document.getElementById("vlSpan").innerHTML = nameVal;
            top.Dialog.close();
		}

		//作业绑定题目
        function subAdd(id,title){
            top.document.getElementById("frmright").contentWindow.document.getElementById("value").value 	= id;
            top.document.getElementById("frmright").contentWindow.document.getElementById("vlSpan").innerHTML = title;
            top.Dialog.close();
        }

		//修改
		function preUpdate(id) {
			if(id !=""){
			top.Dialog.open({URL:"<%=path%>/business/TbQuestionListening.do?act=preUpdate&id="+id,ID:"a1",Width:1024,Height:768,Title:"修改"});
			}
		}

		//配题
		function matchSubject(id) {
			if(id!=""){
			top.Dialog.open({URL:"<%=path%>/business/TbQuestionListening.do?act=matchSubject&catalogId="+id,ID:"a2",Width:1024,Height:768,Title:"查看"});
			}
		}
		//清空查询数据
		function qing(){
			document.getElementById("title").value="";
		}
//		  $(function(){
//        top.Dialog.close()
//        })
		
	</script>
	<body>
	<div id="scrollContent">
		<div class="position">
			<div class="center">
				<div class="left">
					<div class="right" align="left">
					<span>当前位置：试卷阅读列表<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
					</div>
				</div>
			</div>
		</div>
		<form name="listForm" scope="request" action="" method="post">
			<div class="box2" panelTitle="功能面板" roller="false">
			    <input type="text" name="parentid" value="${parentid }" id="parentid" style="display: none"/>
			    <input type="hidden" name="flag" value="${flag}" id="flag"/>
				<table style="width:100%">
                    <tr>
                        <td>
                            <%--<div style="float: left">
                                <a href="javascript:;" onclick="preAdd()" title="绑定题目">
                                    <span>绑定题目</span>
                                </a>
                                &nbsp;&nbsp;
                            </div>--%>
                            <div style="float: left">
                                <a id="" title="批量导入"
                                   href="javascript:;" onclick="top.Dialog.open({URL:'<%=path%>/system/v2/TbQuestionListening/TbQuestionListening_export.jsp',Title:'听力专区',Width:400,Height:200});"> <span
                                        class="img_xls"></span>批量导入
                                </a>
                                &nbsp;&nbsp;
                            </div>
                            <div style="float: left">
                                <a id="" title="模板下载"
                                   href="<%=path%>/business/TbQuestionListening.do?act=fileDownload&fileName=听力.xls&filePath=/template/training/听力.xls" >
                                    模板下载
                                </a>
                                &nbsp;&nbsp;
                            </div>
                            <div style="float: left">
                                题目名称：<input type="text" value="${title }" name="title" id="title"/>&nbsp;
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" />&nbsp;&nbsp; <input
                                type="button" value="清空" id="qing" onclick="qing()" />
                        </td>
                    </tr>
				</table>
			</div>
			<table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">
				<tr >
			        <th width="1" height="25"  align="center" >选择题目</th>
			        <th width="1" height="25"  align="center" class="DataTable_Field">序号</th>
					<th height="25" width="25" align="center" class="DataTable_Field" title="题目名称">题目名称</th>
					<th height="25" width="10" align="center" class="DataTable_Field" title="创建日期">创建日期</th>
					<th height="25"  width="6" align="center" align="center" class="DataTable_Field" title="" >操作</th>
				</tr>
				<c:forEach items="${dlbList}" var="l" varStatus="s">
					<tr>
						<td align="center">
                            <input type="checkbox" name="id" id="id" value="${l.id}" onclick="event.cancelBubble=true;">
                            <input type="checkbox" name="name" id="name" value="${l.title}" style="display: none">
                        </td>
						<td align="center">${s.index+1}</td>
						<td class="DataTable_Content" align="left" title="${l.title}">${l.title}</td>
						<td class="DataTable_Content" align="left" title=""><fmt:formatDate value="${l.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>
                            <a href="javascript:;" title="配套绑定"
                               onclick="subAdd('${l.id}','${l.title}')" > <span class="img_add"></span>
                            </a>
						<%--	<a href="javascript:;" onclick="matchSubject('${l.id}')" title="配题">
								<span class="img_txt"></span>
							</a>
							<a href="javascript:;" onclick="preUpdate('${l.id}')" title="修改">
								<span class="img_edit"></span>
							</a>
							<a id="del_mfp" href="javascript:;" title="删除" onclick="doDelById('${l.id}')">
								<span class="img_delete"></span>
							</a>--%>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="center">
				<div class="right">
					<%@include file="../../../include/listpage.jsp"%>
				</div>
			</div>
			<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
		</form>
	</div>
	</body>
</html>
