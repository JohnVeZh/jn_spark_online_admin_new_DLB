<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.PreferentialCode.PreferentialCodeActionForm"%>
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

</head>
<body class="ali02">
<div id="scrollContent">
    <div class="position">
        <div class="center">
            <div class="left">
                <div class="right" align="left">
                    <span>当前位置：优惠券模板详情</span>
                </div>
            </div>
        </div>
    </div>
    <html:form  action="business/PreferentialCode.do?act=update" method="post"  onsubmit="return sub(this)">
        <div class="box1 center" whiteBg="true" id="form1">
            <fieldset>
                <legend>基本信息</legend>
                <table class="tableStyle" transMode="true" footer="normal">
                    <tr>
                        <td width="15%" align="right">
                            	模板名称：
                        </td>
                        <td width="85%" align="left" colspan="3">
                            ${item.title}
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            	模板类型：
                        </td>
                        <td width="35%" align="left">
	                        <c:choose>
		                		<c:when test="${item.type == 1}">图书网课</c:when>
		                		<c:when test="${item.type == 2}">其他</c:when>
		                	</c:choose>
                        </td>
                        <td width="15%" align="right">
                            	模板状态：
                        </td>
                        <td width="35%" align="left">
                        	<c:choose>
                        		<c:when test="${item.status == 1}">正常</c:when>
                        		<c:otherwise>停用</c:otherwise>
                        	</c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            	模板生效时间：
                        </td>
                        <td width="35%" align="left">
                            <fmt:formatDate value="${item.effectTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td width="15%" align="right">
                            	模板失效时间：
                        </td>
                        <td width="35%" align="left">
                            <fmt:formatDate value="${item.invalidTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            	优惠类型：
                        </td>
                        <td width="35%" align="left">
	                        <c:choose>
		                		<c:when test="${item.discountType == 1}">满减</c:when>
		                		<c:when test="${item.discountType == 2}">折扣</c:when>
		                	</c:choose>
                        </td>
                        <td width="15%" align="right" >
                            	折扣率：
                        </td>
                        <td width="35%" align="left">
                            <c:if test="${item.discountType == 2}"><fmt:formatNumber value="${item.discountRate}" pattern="0.00" /></c:if>
                        </td>
                    </tr>
                    <tr >
                        <td width="15%" align="right">
                            	满额：
                        </td>
                        <td width="35%" align="left">
                            <c:if test="${item.discountType == 1}">
                            	<fmt:formatNumber value="${item.minMoney}" pattern="0.00" />
                            </c:if>
                        </td>
                        <td width="15%" align="right">
                           	 	减额：
                        </td>
                        <td width="35%" align="left">
                            <c:if test="${item.discountType == 1}">
                            	<fmt:formatNumber value="${item.discountMoney}" pattern="0.00" />
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            	有效期限：
                        </td>
                        <td width="35%" align="left">
                            ${item.effectPeriod}天
                        </td>
                        <td width="15%" align="right">
                            	商品类型：
                        </td>
                        <td width="35%" align="left">
	                        <c:choose>
		                		<c:when test="${item.productType == 1}">图书</c:when>
		                		<c:when test="${item.productType == 2}">网课</c:when>
		                	</c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td width="15%" align="right">
                            	商品名称：
                        </td>
                        <td width="35%" align="left" colspan="3">
                            ${item.productName }
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
            <div class="diverror" align="left">友情提示:</br><!--< % out.write( MenuMgr.getMenuRemark("402890e31094bb9c011094c692ea0003"));%>--></div>
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