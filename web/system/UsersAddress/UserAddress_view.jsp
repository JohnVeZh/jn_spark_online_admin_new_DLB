<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@page import="com.business.UserAddress.UserAddressActionForm"%>
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
					<span>当前位置：用户地址<!--< % out.write( MenuMgr.getMenuNavigation("402890e31094bb9c011094c692ea0003"));%>--></span>
				</div>
			</div>
		</div>
	</div> 
 		  <html:form  action="business/UserAddress.do?act=update" method="post"  onsubmit="return sub(this)">
			<html:text style="display:none" property="id" ></html:text> 
			<div class="box1 center" whiteBg="true" id="form1">
				<fieldset> 
					<legend>地址信息</legend> 
						<table class="tableStyle" >
							<tr>
								<td width="10%" align="right">
									收货人姓名：
								</td>
								<td width="10%" align="left">	
									${UserAddressActionForm.realname }								 
								</td>
								<td width="10%" align="right">
									电话：
								</td>
								<td width="10%" align="left">
									${UserAddressActionForm.phone }									 
								</td>
									<td class="titleCn" width="10%" align="right">
										省/市/区县：
									</td>
									<td class="conCn" align="left" width="10%">
										${provinceName}/${cityName }/${citysName }
									</td>
								<td width="10%" align="right">
									邮政编码：
								</td>
								<td width="10%" align="left">
									${UserAddressActionForm.zipcode }									 
								</td>
							</tr>
								<tr>
								<td width="10%" align="right">
									省市区全名：
								</td>
								<td width="10%" align="left">
									${UserAddressActionForm.districtCn }									 
								</td>
								<td width="10%" align="right">
									详细地址：
								</td>
								<td width="10%" align="left">
								<div>
									${UserAddressActionForm.detailAddress }									 
								</div>
								</td>
								<td width="10%" align="right">
									创建时间：
								</td>
								<td width="10%" align="left">
									${UserAddressActionForm.createtime }									 
								</td>
								<td width="10%" align="right">
									是否显示：
								</td>
								<td width="10%" align="left">
									${UserAddressActionForm.isView == '0' ? "不显示":"显示" }									 
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
							<input type="reset" value=" 关闭" onclick="back()" />
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
