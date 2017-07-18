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

    /*function addnv(questionId,questionName){
     top.document.getElementById("frmright").contentWindow.document.getElementById("questionId").value = questionId;
     top.document.getElementById("frmright").contentWindow.document.getElementById("questionName").innerHTML = questionName;
     top.Dialog.close();
     }*/

    //多选
    function isSel1(){
        var inps = document.getElementsByName('id');
        var idVal = "";
        var j =0 ;
        for(var i = 0; i <inps.length; i++){
            if(inps[i].checked){
                idVal +=inps[i].value+",";
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

    function isSel3() {
        var inpt = document.getElementsByName("sort");
        var inps = document.getElementsByName('id');
        var sort = "";
        var j =0 ;
        for(var i = 0; i <inps.length; i++){
            if(inps[i].checked){
                sort +=inpt[i].value+";";
                j++;
            }else{
                continue;
            }
        }
        return sort;
    }

    //修改作业
    function preUpdate(id,questionType,execriseId) {
//        top.Dialog.open({URL:"/business/Dlb/HomeworkQuestion.do?act=preUpdate&id="+id,ID:"a1",Width:500,Height:150,Title:"修改作业"});
        listForm.action="/business/Dlb/HomeworkQuestion.do?act=preUpdate&id="+id+"&questionType="+questionType+"&execriseId="+execriseId;
        listForm.submit();
    }

    //清空查询数据
    function qing(){
        document.getElementById("qing").value="";
    }

    //作业和题目解绑
    function deleteById(id,questionType,execriseId){
        listForm.action="<%=path%>/business/Dlb/HomeworkQuestion.do?act=delete&id="+id+"&execriseId="+execriseId+"&questionType="+questionType;
        listForm.submit();
    }

    //为作业添加题目
    function preAdd(execriseId,questionType){
        <%--top.Dialog.open({URL:"<%=path%>/business/Dlb/Homework.do?act=toAdd",ID:"a1",Width:1080,Height:768,Title:"添加网课"});--%>
        listForm.action="<%=path%>/business/Dlb/HomeworkQuestion.do?act=preAdd&execriseId="+execriseId+"&questionType="+questionType;
        listForm.submit();
    }

    //配题
    function matchSubject(id) {
        if(id!=""){
            top.Dialog.open({URL:"<%=path%>/business/TbQuestionListening.do?act=matchSubject&catalogId="+id,ID:"a2",Width:1024,Height:768,Title:"查看"});
        }
    }

    function addnv(execriseId){
        var idVal = isSel1();
        var nameVal = isSel2();
        var sortVal = isSel3();
//        alert(idVal);
//        alert(nameVal);
//        alert(sortVal);
        $.ajax({
            type:"POST",
            url:"<%=path%>/business/Dlb/HomeworkQuestion.do?act=Add&idVal="+idVal+"&nameVal="+nameVal+"&sortVal="+sortVal+"&execriseId="+execriseId,
            dataType:"json",
            success:function (data) {
                if(data.result){
                    top.document.getElementById("frmright").contentWindow.document.getElementById("value").value 	= idVal;
                    top.document.getElementById("frmright").contentWindow.document.getElementById("questionName").value 	= nameVal;
                    top.document.getElementById("frmright").contentWindow.document.getElementById("vlSpan").innerHTML = nameVal;
                    top.Dialog.close();
                }
            }
        })
    }
</script>
<body>
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right">
                    <span>当前位置：网课列表>>作业列表>>写作题目列表<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" scope="request" action="<%=path%>/business/Dlb/HomeworkQuestion.do?act=listenList" method="post" >
        <input type="text" id="execriseId" name="execriseId" value="${execriseId }" style="display: none"/>
        <div class="box2" panelTitle="功能面板" roller="false">
            <table style="width:100%">
                <tr>
                    <td>
                        <div style="float: left">
                            <a href="javascript:;" onclick="preAdd('${execriseId }','${questionType}')" title="新增"> <span
                                    class="img_add"></span>
                            </a>
                        </div>
                        <%--<div style="float: left">
                            <a href="javascript:;" title="绑定题目" onclick="addnv('${execriseId }')" >
                                绑定题目
                            </a>
                        </div>--%>
                        <div style="float: left">
                            题目名称：<input type="text" value="${questionName }" name="questionName" id="questionName"/>&nbsp;
                        </div>
                        &nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" />&nbsp;&nbsp; <input
                            type="button" value="清空" id="qing" onclick="qing()" />
                    </td>
                </tr>
            </table>
        </div>
        <table  class="tableStyle" mode="list" useCheckbox="true" selectRowButtonOnly="false" id="listTable">

            <tr >
                <th height="25" width="3" align="center" class="DataTable_Field" title="选择题目">选择题目</th>
                <th height="25" width="5" align="center" class="DataTable_Field" title="序号 ">序号</th>
                <th height="25" width="20" align="center" class="DataTable_Field" title=" ">题目名称</th>
                <th height="25" width="5" align="center" class="DataTable_Field" title="目录排序">目录排序</th>
                <th height="25" width="5" align="center" align="center" class="DataTable_Field" title="" >操作</th>
            </tr>
            <%
                String execriseId = (String) request.getAttribute("execirseId");
                int sn=lc.getIndex();
                List list=lc.getList();
                Object[] obj = null;
                for (int i = 0; i < list.size(); i++)
                {
                    obj = (Object[])list.get(i);
            %>
            <tr  >
                <td align="center">
                    <input type="checkbox" name="id" id="id" value="<%= obj[2] %>"
                        <% if (obj[1].toString().equals(execriseId)){%>
                           checked="checked"
                        <%}%>
                           onclick="event.cancelBubble=true;">
                    <input type="checkbox" name="name" id="name" style="display: none"
                        <% if (obj[1].toString().equals(execriseId)){%>
                           checked="checked"
                        <%}%>
                           value="<%= obj[3] %>" >
                </td>
                <td align="center"><%=++sn%></td>
                <td class="DataTable_Content" align="left" title="<%= obj[3] %>"><%= obj[3] %></td>
                <td class="DataTable_Content" align="left" title="<%= obj[4] %>">
                    <%--<input type="hidden" name="sort" id="sort" value="<%= obj[4] %>"/>--%>
                    <input type="checkbox" name="sort" id="sort" style="display: none"
                        <% if (obj[1].toString().equals(execriseId)){%>
                           checked="checked"
                        <%}%>
                           value="<%= obj[4] %>" >
                    <%= obj[4] %>
                </td>
                <td>
                    <a href="javascript:;" onclick="preUpdate('<%=obj[0]%>','${questionType}','${execriseId}')" title="修改">
                        <span class="img_edit"></span>
                    </a>
                    <a id="del_mfp" title="解绑题目"
                       href="javascript:;" onclick="deleteById('<%=obj[0]%>','${questionType}','${execriseId}')"> <span
                            class="img_delete"></span>
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
        <div class="diverror">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
    </form>
</div>
</body>
</html>
