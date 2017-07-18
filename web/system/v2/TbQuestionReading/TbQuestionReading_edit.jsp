<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<!--3.3框架必需start-->
	<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
	<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
	<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
	<link rel="stylesheet" type="text/css" id="theme"/>
	<!--3.3框架必需end-->

	<!-- 表单验证start -->
	<script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
	<script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
	<!-- 表单验证end -->
	<!-- 配置文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"></script>

	<!-- 异步上传 -->
	<script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>

	<!--基本选项卡start-->
	<script type="text/javascript" src="<%=path%>/libs/js/nav/basicTab.js"></script>
	<!--基本选项卡end-->
	<!--缩略图样式-->
	<link href="<%=path%>/js/index.css" rel="stylesheet">
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox.js "></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.fancybox-thumbs.js"></script>
	<script type="text/javascript" src="<%=path%>/js/imgs.js"></script>


</head>
<body class="ali02">
<div id="scrollContent">
	<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：修改<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div>
	<form name="listForm" action="${pageContext.request.contextPath}/business/TbQuestionReading.do?act=update" method="post" id="listForm" onsubmit="return sub(this)" target="main" >
		<input type="hidden" name="catalogId" value="${TbSpecialCatalogActionForm.id }" style="display: none"/>
		<div class="box1 center" whiteBg="true" id="form1">
			<fieldset>
				<legend>基本信息</legend>
				<table class="tableStyle" transMode="true" footer="normal">
					<tr>
						<td width="10%" align="right">
							目录名称：
						</td>
						<td width="85%" align="left" >
							<input type="text" name="name" id="name" class="validate[] iptClass" style="width: 95%" value="${TbSpecialCatalogActionForm.name }"  />
						</td>
					</tr>
					<tr>
						<td width="10%" align="right">
							目录排序：
						</td>
						<td width="85%" align="left">
							<input type="text" name="sortOrder" id="sortOrder" class="validate[required] iptClass" value="${TbSpecialCatalogActionForm.sortOrder }"/>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							题型：
						</td>
						<td width="35%" align="left" >
							<select name="PId" id="id">
								<option value = "">全部</option>
								${nameStatus }
							</select>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right">
							试用范围：
						</td>
						<td width="35%" align="left" >
							<select name="target" id="id">
								<option value = "${TbQuestionListeningActionForm.target}"
										<c:if test="${'0'.equals(TbQuestionListeningActionForm.target)}">
											selected
										</c:if>
								>全部</option>
								<option value = "${TbQuestionListeningActionForm.target}"
										<c:if test="${'1'.equals(TbQuestionListeningActionForm.target)}">
											selected
										</c:if>
								>专项</option>
								<option value = "${TbQuestionListeningActionForm.target}"
										<c:if test="${'2'.equals(TbQuestionListeningActionForm.target)}">
											selected
										</c:if>
								>试卷</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="10%" align="right">
							题干：
						</td>
						<td width="85%" align="left">
							<%--<input type="text" name="title" id="title" class="validate[] iptClass" style="width: 95%" value=""/>--%>
								<textarea rows="1" cols="1" style="width: 80%" name="title" id="title" >${TbQuestionReadingActionForm.title }</textarea>
						</td>
					</tr>
				</table>
				<br/>
				<div class="basicTab">
					<c:choose>
					<c:when test="${textName=='阅读原文' }">
					<div name="阅读原文" id="listening" style="width:100%;min-height: 450px">
						<input type="text" name="readingId" value="${TbQuestionReadingActionForm.id }" style="display: none"/>
						<div style="min-height: 450px">
							<h3>文本内容</h3>
							<textarea  id="ue_wContent" name="content" style="width: 100%;min-height: 450px"  >${TbQuestionReadingActionForm.content }</textarea>
						</div>
					</div>
					</c:when>
					<c:when test="${textName=='参考译文' }">
					<div name="参考译文" id="translate" style="width:100%;min-height: 450px" >
						<input type="text" name="readingId" value="${TbQuestionReadingActionForm.id }" style="display: none"/>
						<div style="min-height: 450px">
							<h3>文本内容</h3>
							<textarea  id="ue_wTest" name="translation" style="width: 100%;min-height: 450px"  >${TbQuestionReadingActionForm.translation }</textarea>
						</div>
					</div>
					</c:when>
					<c:otherwise>
					<div name="阅读原文" id="listening" style="width:100%;min-height: 450px">
						<input type="text" name="readingId" value="${TbQuestionReadingActionForm.id }" style="display: none"/>
						<div style="min-height: 450px">
							<h4>文本内容</h4>
							<textarea  id="ue_wContent" name="content" style="width: 100%;min-height: 450px"  >${TbQuestionReadingActionForm.content }</textarea>
						</div>
					</div>

					<div name="参考译文" id="translate" style="width:100%;min-height: 450px" >
						<input type="text" name="readingId" value="${TbQuestionReadingActionForm.id }" style="display: none"/>
						<div style="min-height: 450px">
							<h4>文本内容</h4>
							<textarea  id="ue_wTest" name="translation" style="width: 100%;min-height: 450px"  >${TbQuestionReadingActionForm.translation }</textarea>
						</div>
					</div>
					</c:otherwise>
					</c:choose>
				</div>
			</fieldset>

			<!-- ---- -->
			<table class="tableStyle"
				   style="width: 800px; margin: 0px auto; border: none"
				   formMode="true">
				<tr>
					<td colspan="4" style="border: none;">
						<input type="submit" value=" 提 交 " onclick="flush()"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value=" 重 置 " />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="reset" value=" 关闭 " onclick="back()" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					</td>
				</tr>
			</table>
			<div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
			<br />
			<br />
		</div>
	</form>
</div>
</body>
<script language="javascript" type="text/javascript">


    function back(){
        top.Dialog.close();
    }

    function flush(){
        top.frmright.window.location.reload();
    }

    $(function(){
        UE.getEditor('content', {initialFrameWidth:'100%',initialFrameHeight : 300});
        $("#content").attr("class","");
    });

    $(function(){
        UE.getEditor('ue_wContent', {initialFrameWidth:'100%',initialFrameHeight : 300});
        UE.getEditor('ue_wTest', {initialFrameWidth:'100%',initialFrameHeight : 300});
        UE.getEditor('ue_tContent', {initialFrameWidth:'100%',initialFrameHeight : 300});
        UE.getEditor('ue_tTest', {initialFrameWidth:'100%',initialFrameHeight : 300});
        UE.getEditor('ue_wAnalysis', {initialFrameWidth:'100%',initialFrameHeight : 300});
        UE.getEditor('ue_tAnalysis', {initialFrameWidth:'100%',initialFrameHeight : 300});

        $("#ue_wContent").attr("class","");
        $("#ue_wTest").attr("class","");
        $("#ue_tContent").attr("class","");
        $("#ue_tTest").attr("class","");
        $("#ue_wAnalysis").attr("class","");
        $("#ue_tAnalysis").attr("class","");
    });

    function addNewRow(){
        //创建选项
        var $idInput=$('<input type="text" />');
        var $textInput=$('<textarea style="width: 100%;height: 30px" ></textarea>');
        var $sortInput=$('<input type="number" />');
        var $startimeInput=$('<input type="number" />');
        var $endtimeInput=$('<input type="number" value="0"/>');
        //创建选项内容
        //创建排序
        //创建是否正确答案
        //创建按钮
        var $delBtn=$('<input type="button" value="删除该行" class="button"/>');
        //创建表格行
        var $tr=$("<tr><td></td><td></td><td style='display: none'></td><td></td><td></td><td style='display: none'></td></tr>");
        $tr.find("td").eq(0).append($textInput);
        $tr.find("td").eq(1).append($startimeInput);
        $tr.find("td").eq(2).append($endtimeInput);
        $tr.find("td").eq(3).append($sortInput);
        $tr.find("td").eq(4).append($delBtn);
        $tr.find("td").eq(5).append($idInput);

        $("#mytable").append($tr);

        $delBtn.click(function(){
            //将所在的行删除
            $(this).parents("tr").remove();
        })
    }

    function getTabl(){
        var num = 0;
        var arrbills = "";
        $("#scrollContent").mask("表单正在提交...");
        $("#mytable").find("tr").each(function(){
            if(num>0){
                var tdArr = $(this).children();
                var text = tdArr.eq(0).find("textarea").val();//
                var startTime = tdArr.eq(1).find("input").val();//
                var endTime = tdArr.eq(2).find("input").val();//
                var sort = tdArr.eq(3).find("input").val();
                var id = tdArr.eq(5).find("input").val();
                if(id!=''){
                    if(arrbills==""){
                        arrbills = text+"#"+startTime+"#"+endTime+"#"+sort+"#"+id;
                    }else{
                        arrbills = arrbills+"&"+text+"#"+startTime+"#"+endTime+"#"+sort+"#"+id;
                    }
                }else{
                    if(arrbills==""){
                        arrbills =text+"#"+startTime+"#"+endTime+"#"+sort+"#noId";
                    }else{
                        arrbills = arrbills+"&"+text+"#"+startTime+"#"+endTime+"#"+sort+"#noId";
                    }
                }

            }
            num++;

        });

		/*  listForm.action="business/JQrSubtitleListening.do?act=update&arrbills="+arrbills;
		 listForm.submit(); */
        // top.frmright.window.location.reload();
        var id = $("#id").val();
        var mpName = $("#mpName").val();
        var hearUrl = $("#hearUrl").val();
        var sort = $("#sort").val();
        var qrCode = $("#qrCode").val();
        var levelType = $("#levelType").val();
        $.post("business/JQrSubtitleListening.do?act=update",
            {   id:id,
                mpName:mpName,
                hearUrl:hearUrl,
                qrCode:qrCode,
                sort:sort,
                levelType:levelType,
                arrbills:arrbills
            },
            function(data){
                if(data.result){
                    top.frmright.window.location.reload()
                    top.Dialog.close();
                }
            }, "json");
    }

    function delTr(id,ts){
        if(id!=''){
            $.post("business/JQrSubtitleListening.do?act=AjasRealdelete", {id:id},
                function(data){
                    if(data.succeed='000'){
                        //将所在的行删除
                        $(ts).parents("tr").remove();
                    }
                }, "json");
        }

    }
</script>


</html>
