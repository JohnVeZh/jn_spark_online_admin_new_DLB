<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.ProductOrder.ProductOrderActionForm"%>
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
 <!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>

</head>
<body class="ali02">	
	<div id="scrollContent">
		<div class="position">
		<div class="center">
			<div class="left">
				<div class="right" align="left">
					<span>当前位置：订单修改<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  action="business/ProductOrder.do?act=update" method="post"  onsubmit="return sub(this)" target="frmright">
			<html:text property="id" style="display:none" ></html:text>  
			<html:text property="userId" style="display:none;" styleClass="validate[required] iptClass"></html:text>
			<html:text property="orderState" style="display:none;" styleClass="validate[required] iptClass"></html:text> 
			<html:text property="orderCode" style="display:none;" styleClass="validate[required] iptClass"></html:text>    	
								
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>订单基本信息</legend> 
						<table class="tableStyle" transMode="true" footer="normal">
								<tr>
								<td width="15%"  align="right">
									用户id：
								</td>
								<td width="35%" align="left">
								 <input type="text" value="${uName }" disabled="true"/>
								</td>
								<td width="15%"  align="right">
									收货人：
								</td>
								<td width="35%" align="left">									 
									<html:text property="consignee" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
							</tr>
							<tr>
								<td width="15%"  align="right">
									收货人电话：
								</td>
								<td width="35%" align="left">									 
									<html:text property="telephone" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
								<td width="15%" align="right">
									邮编：
								</td>
								<td width="35%" align="left">									 
									<html:text property="zipcode" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									订单金额：
								</td>
								<td width="35%" align="left">									 
									<html:text property="price" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
									<td width="15%" align="right">
										实付金额：
									</td>
									<td width="35%" align="left">
										<html:text property="payPrice" style="width:250px" styleClass="validate[required] iptClass"></html:text>
									</td>

							</tr>
								<tr>
								<td width="15%" align="right">
									订单状态：
								</td>
								<td width="35%" align="left">	
								  <input type="text" value=" ${state }" disabled="true"/>								 
								</td>
									<td width="15%" align="right">
										邮费：
									</td>
									<td width="35%" align="left">
										<html:text property="postage" style="width:250px" styleClass="validate[required] iptClass"></html:text>
									</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									详细地址：
								</td>
								<td width="35%" align="left" colspan="3">									 
									<html:text property="address" style="width:80%" styleClass="validate[required] iptClass"></html:text>     	
								</td>
							</tr>
								<tr style="display:none">
								<td width="35%" align="left">									 
									<html:text property="logisticscode" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
								<td width="35%" align="left">									 
									<html:text property="cityId" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
								<td width="35%" align="left">									 
									<html:text property="provinceId" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
								<td width="35%" align="left">									 
									<html:text property="areaId" style="width:250px" styleClass="validate[required] iptClass"></html:text>     	
								</td>
							</tr>
								<tr>
								<td width="15%" align="right">
									备注：
								</td>
								<td width="35%" align="left" colspan="3">									 
									<html:text property="remark" style="width:80%" styleClass="validate[required] iptClass"></html:text>     	
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
							<input type="submit" value=" 提 交 " onclick="flush()"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " /> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="button" value="关 闭" onclick="back()"/>
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
<script language="javascript" type="text/javascript">
    function flush(){
      top.frmright.window.location.reload();
    }
	function back(){
    	top.Dialog.close();
	}

	  $(function(){
	  		var msg='';//jstl的标签 $，{，msg}
	  		if(msg==200){
	  			top.Dialog.alert("添加成功。");
	  		}else if(msg==201){
	  			top.Dialog.alert("添加失败。");
	  		}else if(msg==202){
	  			top.Dialog.alert("添加失败!");
	  		}
	  	});
</script>
</html>
