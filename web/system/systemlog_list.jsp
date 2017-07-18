<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.easecom.common.util.*"%>
<%@ page import="com.easecom.system.model.SystemLog"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	// 获取所有的操作类型
	Collection opetypes = com.easecom.common.util.DictionaryManage.getOpeTypeValues();
	pageContext.setAttribute("opetypes" , opetypes);
	path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	 <!--框架必需start-->
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/framework.js"></script>
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" id="skin" prePath="<%=path%>/" scrollerY="true"/>
<link rel="stylesheet" type="text/css" id="theme"/>
 <!--框架必需end-->

		<!--截取文字start-->
		<script type="text/javascript"
			src="<%=path%>/js/text/text-overflow.js"></script>
		<!--截取文字end-->
		
		<!-- 日期选择框start -->
<script type="text/javascript" src="<%=path%>/libs/js/form/datePicker/WdatePicker.js"></script>
<!-- 日期选择框end -->
		
	</head>
	<bean:define id="lc" name="lc" type="ListContainer"></bean:define>
	<body>

		<form name="listForm" scope="request"
			action="<%=path%>/base/systemlog.do?act=list" method="post">
			<legend>系统日志管理</legend>
	<div class="box_tool_mid padding_top5 padding_right5">
		<div class="center">
		<div class="left">
		<div class="right">
			<div class="padding_top8 padding_left10">
			<table>
				<tr>
					<td>
							时间：
						</td>
						<td>
							<input type="text" class="date" id="cx_stime" name="cx_stime"  dateFmt="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							至
						</td>
						<td>
							<input type="text" class="date" id="cx_etime" name="cx_etime"  dateFmt="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							操作人：
						</td>
						<td>
							<input type="text" id="cx_name" name="cx_name" />
						</td>
						<td>
							操作内容：
						</td>
						<td>
							<input type="text" id="cx_opecontent" name="cx_opecontent"/>
						</td>
						<td>类型：</td>
						<td>
							<select id="cx_opetype" name="cx_opetype">
								<option selected="selected" value="">全部</option>
								<logic:iterate id="opetype" name="opetypes">
									<option value="${opetype}">${opetype}</option>
								</logic:iterate>								
							</select>
						</td>
						<td><button  onclick="sub()"><span class="icon_find">查询</span></button></td>
						<td >
							 
							
							<input   type="reset" value="清空" />
						</td>
					
				</tr>
			</table>
			</div>			
		</div>		
		</div>	
		</div>
		<div class="clear"></div>
	</div>     	
	</fieldset>
			
			<div class="box_tool_min padding_top2 padding_bottom2 padding_right5">
				<div class="center">
					<div class="left">
						<div class="right">
							<div class="padding_top5 padding_left10">
							<!--  -->
							<a href="javascript:deleteApp('deleteWeek');"><span class="icon_delete">删除本周记录</span>
							</a>
							<div class="box_tool_line"></div>
								<a href="javascript:void(0);" onclick="return deleteApp('deleteMonth');"><span class="icon_delete">删除本月记录</span>
								</a>
								
								<div class="box_tool_line"></div>
								
								<a href="javascript:void(0);" onclick="return deleteApp('deleteAll');"><span class="icon_delete">删除全部记录</span>
								</a>
								<div class="box_tool_line"></div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<div id="scrollContent" class="border_gray">
				<table class="tableStyle" useMultColor="true" id="table_list">
					<tr>
						<th width="5%">
							编号
						</th>
						<th width="13%">
							操作时间
						</th>
						<th width="20%">
							内容
						</th>
						<th width="10%">
							操作编号
						</th>
						<th width="10%">
							操作人
						</th>
						<th width="10%">
							IP
						</th>
						<th width="6%">
							类型
						</th>
					</tr>
					
					<logic:iterate id="po" name="lc" property="list" type="SystemLog">
					
					<tr>
						<td>
							<bean:write name="po" property="id" />
						</td> 
						<td>
							<logic:notEmpty name="po" property="opetime">
								<bean:write name="po" property="opetime"/>
							</logic:notEmpty>
						</td>
						<td>
							<span class="text_slice" title="${po.content }">
								<logic:notEmpty name="po" property="content">
									<bean:write name="po" property="content"/>
								</logic:notEmpty>
							</span>
						</td>
						<td>
							<span class="text_slice" title="${po.operid }">
								<logic:notEmpty name="po" property="operid">
									<bean:write name="po" property="operid"/>
								</logic:notEmpty>	
							</span>
						</td>
						<td>
							<span class="text_slice" title="${po.oper }">
								<logic:notEmpty name="po" property="oper">
									<bean:write name="po" property="oper"/>
								</logic:notEmpty>
							</span>
						</td>
						<td>
							<span class="text_slice" title="${po.ipaddress }">
								<logic:notEmpty name="po" property="ipaddress">
									<bean:write name="po" property="ipaddress"/>
								</logic:notEmpty>	
							</span>
						</td>
						<td>
							<span class="text_slice" title="<%=po.getType() == null ? "" : com.easecom.common.util.DictionaryManage.getOpeType(java.lang.Integer.parseInt(po.getType()))%>">
									<%=po.getType() == null ? "" : com.easecom.common.util.DictionaryManage.getOpeType(java.lang.Integer.parseInt(po.getType()))%>
							</span>
							
						</td>
					</tr>
					</logic:iterate>
				</table>
				
				<%@include file="../include/listpagenews.jsp"%>
				
			</div>
		</form>
		<script>
		function sub(){
			listForm.action="<%=path%>/base/systemlog.do?act=list";
			listForm.submit();
		}
		/*
		function aclear(){
			   $("#cx_name").val("");
			   $("#cx_etime").val("");
			   $("#cx_stime").val("");
			   $("#cx_opecontent").val("");
			   
			   document.getElementById("cx_opetype").options[0].selected = true;
			   document.getElementById("cx_opetype").text="全部";
		}*/
		
		
		function deleteApp(action){
			var msg='';
			
			if(action == 'deleteWeek'){
				msg='您确信要删除本周的日志记录吗？';
			}else if(action == "deleteMonth"){
				msg='您确信要删除本月的日志记录吗？';
			}else if(action == "deleteAll"){
				msg='您确信要删除全部的日志记录吗？';
			}
			
 			top.Dialog.confirm(msg,function(){
 				listForm.action="<%=path%>/base/systemlog.do?act=deleteWeekMonthALL&action=" + action;
				listForm.submit();
 			});
		}	
		</script>
		<script>
		
			document.listForm.cx_name.value="${cx_name}";    	
			document.listForm.cx_stime.value="${cx_stime}"; 
			document.listForm.cx_etime.value="${cx_etime}"; 
			document.listForm.cx_opecontent.value="${cx_opecontent}";
			var sel = document.listForm.cx_opetype;
			for(var i=0 ; i<sel.length ; i++)
			{
				if(sel.options[i].text == "${cx_opetype}")
				{
					sel.options[i].selected = true;
				}
			}
		</script>
	</body>
</html>
