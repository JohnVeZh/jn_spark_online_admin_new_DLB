<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ page import="java.util.*"%>
<%@ page import="com.easecom.common.util.*"%>
<%@ page import="com.easecom.system.model.*"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>系统参数设置</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <!--框架必需start-->
	<script type="text/javascript" src="<%=path %>/js/jquery-1.4.js"></script>
	<script type="text/javascript" src="<%=path %>/js/framework.js"></script>
	<link href="<%=path %>/css/import_basic.css" rel="stylesheet" type="text/css"/>
	<link  rel="stylesheet" type="text/css" id="skin" prePath="../"/>
	<!--框架必需end-->
    <script type="text/javascript">
    	$(function(){
    		$("#dxzhset").click(function(){
    			var diag = new top.Dialog();
    			diag.Title = "短信账号设置";
    			diag.URL = "system/smspara.do?act=initAdd";
    			diag.Width = 500;
    			diag.Height = 270
    			diag.show();
    		});
    		$("#dxgnset").click(function(){
    			var diag = new top.Dialog();
    			diag.Title = "短信功能设置";
    			diag.URL = "system/smspara.do?act=initSet";
    			diag.Width = 500;
    			diag.Height = 150
    			diag.show();
    		});
    		
    		$("#openCloseAccredit").click(function(){
    			var diag = new top.Dialog();
				diag.Title = "开启/关闭授权管理";
				diag.URL = "<%=path%>/system/sysconfig.do?act=preOpenAccredit";
				diag.Width = 450;
				diag.Height = 150;
				diag.show();
    		});
    		
    		$("#hjset").click(function(){
    			var diag = new top.Dialog();
				diag.Title = "环节提醒设置";
				diag.URL = "demo/txsz/hjset.html";
				diag.Width = 450;
				diag.Height = 150;
				diag.show();
    		});
    		
    		$("#dxmbset").click(function(){
    			var diag = new top.Dialog();
    			diag.Title = "短信模板设置";
    			diag.URL = "demo/dxgl/dxmb.html";
    			diag.Width = 600;
    			diag.Height = 400
    			diag.show();
    		});
    		
    		$("#gjflset").click(function(){
    			var diag = new top.Dialog();
    			diag.Title = "国际分类设置";
    			diag.URL = "demo/txsz/gjfl.html";
    			diag.Width = 600;
    			diag.Height = 400
    			diag.show();
    		});
    	});
    </script>
    <style type="text/css">
    	body,div,img,ul,li,a{
    		margin: 0;
    		padding: 0;
    	}
    	
    	body{
    		background: rgb(249,251,240);
    	}
    	.outter{
    		margin-left: auto;
    		margin-right: auto;
    		margin-top: 100px;
    		width: 500px;
    	}
    	
    	.outter-one{
    		float: left;
    		width: 200px;
    		height: 100px;
    		margin-top: 10px;
    		margin-right: 50px;
    	}
    	
    	.outter-one-img{
    		float: left;
    	}
    	
    	.outter-one-img img{
    		width: 55px;
    		height: 55px;
    		border: 0;
    	}
    	
    	.outter-one-text{
    		float: left;
    		margin-left: 5px;
    	}
    	
    	.outter-one-text-title a{
    		text-decoration: none;
    		font-weight: bold;
    		font-size: 15px;
    		color: rgb(0,169,29);
    	}   	
    	
    	.outter-one-text-title a:hover{
    		text-decoration: underline;
    	}
    	
    	.outter-one-text-list{
    		margin-top: 3px;
    	}
    	
    	.outter-one-text-list ul{
    		list-style: none;
    	}
    	
    	.outter-one-text-list a{
    		text-decoration: none;
    		font-size: 13px;
    		font-weight: 500;
    		color: rgb(7,138,255);
    		margin-left: 5px;
    	}
    	
    	.outter-one-text-list a:hover{
    		text-decoration: underline;
    	}
    </style>
  </head>  
  <body>
  	<div class="outter">
  		<div class="outter-one">
  			<div class="outter-one-img">
  				<a href="#"><img src="<%=path %>/icons/png/sms.png"/></a>
  			</div>
  			<div class="outter-one-text">
  				<div class="outter-one-text-title">
  					<a href="#">短信设置</a>
  				</div>
  				<div class="outter-one-text-list">
  					<ul>
  						<li><a href="#" >暂无</a></li>
						 <li><a href="#" >暂无</a></li> 
  					</ul>
			  </div>
  			</div>
  		</div>
  		
  		<div class="outter-one">
  			<div class="outter-one-img">
  				<a href="<%=path %>/system/sysconfig.do?act=list"><img src="<%=path %>/icons/png/tanping.png"/></a>
  			</div>
  			<div class="outter-one-text">
  				<div class="outter-one-text-title">
  					<a href="javascript:void(0);">系统参数设置</a>
  				</div>
  				<div class="outter-one-text-list">
  					<ul>
  						<li><a href="<%=path %>/system/sysconfig.do?act=list">参数列表</a></li>
  						<li><a href="<%=path%>/system/listtype.do?act=list" >类别设置</a></li>
  					</ul>
  				</div>
  			</div>
  		</div>
  		
  		<div class="outter-one">
  			<div class="outter-one-img">
  				<a href="#"><img src="<%=path %>/icons/png/backup.png"/></a>
  			</div>
  			<div class="outter-one-text">
  				<div class="outter-one-text-title">
  					<a href="#">授权管理</a>
  				</div>
  				<div class="outter-one-text-list">
  					<ul>
  						<li><a   >暂无</a></li>
  						<li><a >暂无</a></li>
  						
  					</ul>
  				</div>
  			</div>
  		</div>
  		
  		<div class="outter-one">
  			<div class="outter-one-img">
  				<a href="#"><img src="<%=path %>/icons/png/hangye.png"/></a>
  			</div>
  			<div class="outter-one-text">
  				<div class="outter-one-text-title">
  					<a href="#">暂无</a>
  				</div>
  				<div class="outter-one-text-list">
  					<ul>
  						<li><a href="#"  >暂无</a></li>
  					</ul>
  				</div>
  			</div>
  		</div>
		<div class="outter-one">
  			<div class="outter-one-img">
  				<a href="#"><img src="<%=path %>/icons/png/business_contact.png"/></a>
  			</div>
  			<div class="outter-one-text">
  				<div class="outter-one-text-title">
  					<a href="#">暂无</a>
  				</div>
  				<div class="outter-one-text-list">
  					<ul>
  						<li><a href="#"  >暂无</a></li>
  						<li><a href="#"  >暂无</a></li>
  						
  					</ul>
  				</div>
  			</div>
  		</div>
  		
  		<div class="outter-one">
  			<div class="outter-one-img">
  				<a href="#"><img src="<%=path %>/icons/png/user_control.png"/></a>
  			</div>
  			<div class="outter-one-text">
  				<div class="outter-one-text-title">
  					<a href="#">暂无</a>
  				</div>
  				<div class="outter-one-text-list">
  					<ul>
  						<li><a href="#"  >暂无</a></li>
  					</ul>
  				</div>
  			</div>
  		</div>
  	</div>
  </body>
</html>
