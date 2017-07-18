<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <!--tap页start-->
    <script type="text/javascript" src="<%=path%>/libs/js/nav/basicTab.js"></script>
    <link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
    <link rel="stylesheet" type="text/css" id="theme"/>
    <!--3.3框架必需end-->
    <!-- 日期选择框start -->
    <script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
    <!-- 日期选择框end -->
    <!-- 表单验证start -->
    <script src="<%=path%>/libs/js/form/validationRule.js" type="text/javascript"></script>
    <script src="<%=path%>/libs/js/form/validation.js" type="text/javascript"></script>
    <!-- 表单验证end -->
    <!-- 异步上传 -->
    <script type="text/javascript" src="<%=path%>/js/jquery-form.js"></script>
    <script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>


</head>
<body class="ali02">
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right" align="left">
                    <span>当前位置：视频</span>
                </div>
            </div>
        </div>
    </div>
    <form name="listForm" action="<%=path%>/business/MatchedPapers.do?act=updateVideo" method="post" id="listForm" >
        <input type="hidden" name="id" id="id" value="${item.id}" />
        <input type="hidden" name="mpId" id="mpId" value="${item.mpId}" />
        <div class="box1 center" whiteBg="true" id="form1">
            <fieldset>
                <legend>基本信息</legend>
                <table class="tableStyle" transMode="true" footer="normal">
                    <tr>
                        <td width="15%" align="right">
                            	视频名称：
                        </td>
                        <td width="85%" align="left" colspan="3">
                            <input type="text" name="videoTitle" id="videoTitle" style="width:80%" value="${item.videoTitle}" maxlength="127"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            	视频ID：
                        </td>
                        <td width="85%" align="left" colspan="3">
                            <input type="text" name="videoId" id="videoId" style="width:80%" value="${item.videoId}" maxlength="64"/>
                        </td>
                    </tr>
                    <%-- 
                    <tr>
                        <td width="15%" align="right">
                            	视频链接：
                        </td>
                        <td width="85%" align="left" colspan="3">
                            <input type="text" name="videoUrl" id="videoUrl" style="width:80%" value="${item.videoUrl}" maxlength="255"/>
                        </td>
                    </tr>
                     --%>
                </table>
            </fieldset>

            <!-- ---- -->
            <table class="tableStyle" style="width: 800px; margin: 0px auto; border: none" formMode="true">
                <tr>
                    <td colspan="4" style="border: none;">
                        <input type="button" value=" 提 交 " onclick="sub()"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <%-- 
                        <input type="button" value=" 关闭 " onclick="javascript:top.Dialog.close();" />
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         --%>
                        <input type="button" value=" 删除" onclick="javascript:del('${item.id}');" />
                    </td>
                </tr>
            </table>
            <div class="diverror" align="left">友情提示：点击“删除”按钮，可删除已维护的视频。</div>
            <br />
            <br />
        </div>
    </form>
</div>
</body>
<script language="javascript" type="text/javascript">
	function del(id) {
		if(id) {
			$.ajax({
				url : "<%=path%>/business/MatchedPapers.do?act=deleteVideo&id=" + id,
				dataType : "json",
				success : function(data) {
					if(data.result) {
						$("#id").val("");
						$("#videoTitle").val("");
						$("#videoId").val("");
						<%--
						$("#videoUrl").val("");
						--%>
					}
				}
			});
		}
	}

    function sub(){
        var videoTitle = $("#videoTitle").val();
        if(!videoTitle) {
            top.Dialog.alert("视频名称不能为空！");
            return;
        }
        
        <%--
        var videoId = $("#videoId").val();
        var videoUrl = $("#videoUrl").val();
        if(!videoId && !videoUrl) {
            top.Dialog.alert("视频ID和视频链接至少输入一项！");
            return;
        }
        --%>
        
        var videoId = $("#videoId").val();
        if(!videoId) {
            top.Dialog.alert("请输入视频ID！");
            return;
        }
	
        $("#scrollContent").mask("表单正在提交...");
        $('#listForm').ajaxSubmit(function(data){
            if(data.result){
  		      	//top.Dialog.alert("维护视频信息成功！");
	            top.Dialog.close();
            } else {
  		      	top.Dialog.alert("维护视频信息失败！");
            }
            $("#scrollContent").unmask();
        });
    }
</script>
</html>
