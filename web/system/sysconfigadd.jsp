<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
	<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
	<link href="<%=path%>/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/"/>
	<link href="<%=path%>/css/product_add.css" rel="stylesheet" type="text/css"/>
	<script src="<%=path%>/js/form/validationEngine-cn.js" type="text/javascript"></script>
	<script src="<%=path%>/js/form/validationEngine.js" type="text/javascript"></script>
</head>
<body>
	<div class="padding_top10 ali01">
	<form action="<%=path%>/System/SysConfig.do?act=add" method="post"  enctype="multipart/form-data">
	<input type="text" value="${type }" name="type" style="display: none"/>
		<div class="border_gray">
				<div class="static_box1" style="width: 100%">
					<div class="box1_topcenter2">
						<div class="box1_topleft2">
							<div class="box1_topright2"></div>
						</div>
					</div>
					<div class="box1_middlecenter">
						<div class="box1_middleleft2">
							<div class="box1_middleright2">
								<div style="padding: 0 20px 0 20px;">
									<table class="tableStyle" align="center" useHover="false"
										useClick="false">
										<tr>
											<td align="right">
												单位名称：
											</td>
											<td align="left">
												<input type="text" name="name" size="32" class="validate[required] iptClass" />
												<span class="star">*</span>
											</td>
											<td>
												排序值：
											</td>
											<td>
												<input type="text" name="sort" class="validate[required,length[0,10],custom[onlyNumber]] iptClass" />
												<span class="star">*</span>
											</td>
										</tr>
										<tr>
										  <td align="right">
												单位值：
											</td>
											<td align="left" colspan="3">
												<textarea style="width:45%;height: 80px"  name="value" class="validate[] iptClass"></textarea>
												<span class="star"></span>
											</td>
										</tr>
									</table>
									<table class="tableStyle"
					style="width: 800px; margin: 0px auto; border: none"
					formMode="true">
					<tr>
						<td colspan="4" style="border: none;">
							<input type="submit" value=" 提 交 "/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 " />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 返 回 " onclick="back()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

						</td>
					</tr>
				</table>
								</div>
							</div>
						</div>
					</div>
				</div>
	</form>
</div>

<script language="javascript" type="text/javascript">
 
	function back(){
		/* listForm.action="business/News.do?act=list";
		listForm.submit(); */
		history.back();
	}
	
	  	
</script>
</body>
</html>