<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.Apkversions.ApkversionsActionForm"%>
<%@page import="com.easecom.common.util.Tool"%>
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
    <script type="text/javascript" src="<%=path%>/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
    <script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
    <link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
    <link rel="stylesheet" type="text/css" id="theme"/>
    <!--3.3框架必需end-->
    <!-- 日期选择框start -->
    <script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
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
                    <span>当前位置：版本详情<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
                </div>
            </div>
        </div>
    </div>
    <html:form  action="business/AppVersions.do?act=view" method="post" styleId="formActionphone" onsubmit="return sub(this)">
        <html:text property="id" style="display:none"></html:text>
        <div class="box1 center" whiteBg="true" id="form1">
            <fieldset>
                <legend>基本信息</legend>
                <table class="tableStyle" >
                    <tr>
                        <td width="15%" align="right">
                            APP类型：
                        </td>
                        <td width="35%" align="left">
                        	<c:if test="${apk.type == 0}">Android</c:if>
                			<c:if test="${apk.type == 1}">iOS</c:if>
                        </td>
                        <td width="15%" align="right">
                            应用名称：
                        </td>
                        <td width="35%" align="left">
                                ${apk.apkname }
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            版本号：
                        </td>
                        <td width="35%" align="left">
                        	${apk.apkversion }
                        </td>
                        <td width="15%" align="right">
                            版本名称：
                        </td>
                        <td width="35%" align="left">
                                ${apk.versionName }
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" >
                            文件大小：
                        </td>
                        <td width="35%" align="left" >
                                ${apk.filesize }
                        </td>
                        <td width="15%" align="right" >
                            更新时间：
                        </td>
                        <td width="35%" align="left" >
                                ${apk.updatetime }
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            商店地址：
                        </td>
                        <td width="85%" align="left" colspan="3">
                                ${apk.apkurl }
                        </td>
                   </tr>
                   <tr>
                        <td width="15%" align="right">
                            下载地址：
                        </td>
                        <td width="85%" align="left" colspan="3">
                                ${apk.downloadUrl }
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right" >
                            更新说明：
                        </td>
                        <td width="35%" align="left" colspan="3">
                                ${apk.remark }
                        </td>
                    </tr>
                </table>
            </fieldset>

            <!-- ---- -->
            <table class="tableStyle"
                   style="width: 800px; margin: 0px auto; border: none"
                   formMode="true">
                <tr>
                    <td colspan="4" style="border: none;">
                        <input type="reset" value=" 关闭 " onclick="back()" />
                    </td>
                </tr>
            </table>
            <div class="diverror" align="left">友情提示：</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
            <br />
            <br />
        </div>
    </html:form>
</div>
</body>
</html>
<script language="javascript" type="text/javascript">
    function back(){
        top.Dialog.close();
    }
</script>