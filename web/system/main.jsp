<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="com.easecom.common.util.*" %>
<%
String path=request.getContextPath();
path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
String myerr = (String)request.getAttribute("myerrors");
myerr = (null == myerr)?"":myerr; 

SessionContainer sessionContainer = (SessionContainer)request.getSession().getAttribute("SessionContainer");
String  type = (String)sessionContainer.getUrltype(); // 1 2 3 4   日志  题库  星火在线  用户
String bs_navleft = "";
String bs_bannerleft = "";
if("1".equals(type)){
	bs_navleft = "bs_navleft_log";
	bs_bannerleft = "bs_bannerleft_log";
}else if("2".equals(type)){
	bs_navleft = "bs_navleft_question";
	bs_bannerleft = "bs_bannerleft_question";
}else if("3".equals(type)){
	bs_navleft = "bs_navleft";
	bs_bannerleft = "bs_bannerleft";
}else if("4".equals(type)){
	bs_navleft = "bs_navleft_user";
	bs_bannerleft = "bs_bannerleft_user";
}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=9" ></meta>
<title>星火在线</title>
<!--框架必需start-->
<link href="<%=path%>/libs/css/import_basic.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/libs/skins/modernBlue/style.css" rel="stylesheet" type="text/css" id="theme" themeColor="modernBlue" positionTarget="positionContent" selInputHeight="28" selButtonWidth="29" defaultSelWidth="160" fileBtnWidth="60" defaultFileInputWidth="222" defaultGridHeaderHeight="32" defaultGridRowHeight="32" defaultFontSize="14" defaultPageSelWidth="55" defaultFilterItemHeight="28" defaultFontFamily="微软雅黑"/>
<link href="<%=path%>/system/skin/style.css" rel="stylesheet" type="text/css" id="skin" skinPath="/system/skin/" scrollerY="true"/>
<script type="text/javascript" src="<%=path%>/libs/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/language/cn.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/main.js"></script>
<!--框架必需end-->

<!--弹窗组件start-->
<script type="text/javascript" src="<%=path%>/libs/js/popup/drag.js"></script>
<script type="text/javascript" src="<%=path%>/libs/js/popup/dialog.js"></script>
<!--弹窗组件end-->

<!--分隔条start-->
<script type="text/javascript" src="<%=path%>/libs/js/nav/spliter.js"></script>
<!--分隔条end-->
<script>
function bookmarksite(title, url){
    if (window.sidebar) // firefox
        window.sidebar.addPanel(title, url, "");
    else 
        if (window.opera && window.print) { // opera
            var elem = document.createElement('a');
            elem.setAttribute('href', url);
            elem.setAttribute('title', title);
            elem.setAttribute('rel', 'sidebar');
            elem.click();
        }
        else 
            if (document.all)// ie
                window.external.AddFavorite(url, title);
}
function windowClose(){
window.opener=null;
  window.open('', '_self'); //IE7必需的.
  window.close();
}
function backHome(){
	window.document.getElementById("frmright").contentWindow.location="<%=path%>/business/Statistics.do?act=list";
	<%-- top.Dialog.open({URL:"<%=path%>/business/Users.do?act=preAdd",ID:"a1",Width:1000,Height:450,Title:"新增"}); --%>
	
	
	document.getElementById("frmleft").contentWindow.homeHandler();
	top.positionType="simple";
	top.positionContent="【当前位置：系统主页】";
}

function exitSystem(){
	top.Dialog.confirm("确定退出系统吗？",function(){
			window.location.href='<%=path %>/login.do?act=logout';
		});
}

function sysConfig(){
	top.Dialog.confirm("coming soon");
}
</script>
<style>
a {
	behavior:url(<%=path%>/libs/js/method/focus.htc)
}
</style>
</head>
<body>
<div id="mainFrame">
<!--头部与导航start-->
<div id="hbox">
	<div id="bs_bannercenter">
	<div id="bs_bannerright">
	<div id="<%=bs_bannerleft%>">	
		<!-- <input type="button" value="" class="functionBtn1"  onclick="sysConfig()"/> -->
		<div class="weather"></div>
		<input type="button" value="" class="functionBtn3" onclick="backHome()"/>
		<input type="button" value="" class="functionBtn2"  onclick="exitSystem()"/>
	</div>
	</div>
	</div>
	<div id="bs_navcenter">
	<div id="<%=bs_navleft %>">
	<div id="bs_navright">
		<div class="bs_nav">
			<div class="bs_navleft">
				<li class="fontTitle">字号:</li>
				<li class="fontChange"><span><a href="javascript:;" setFont="16">大</a></span></li>
				<li class="fontChange"><span><a href="javascript:;" setFont="14">中</a></span></li>
				<li class="fontChange"><span><a href="javascript:;" setFont="12">小</a></span></li>
				<div class="clear"></div>	
			</div>	
			<div class="user_info">欢迎 <%=sessionContainer.getOrgName() %>部门 <%=sessionContainer.getRoleName() %>-<%=sessionContainer.getUserName() %>[<%=sessionContainer.getLoginName() %>] </div>
			<div class="user_info2">
				<div class="icon_label_5">系统用户数：<%out.print(Tool.getIntValue("select count(id) from sys_user ")); %></div>
				<div class="icon_label_6">
					【今天是
				<script>
					var weekDayLabels = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
					var now = new Date();
				    var year=now.getFullYear();
					var month=now.getMonth()+1;
					var day=now.getDate()
				    var currentime = year+"年"+month+"月"+day+"日 "+weekDayLabels[now.getDay()]
					document.write(currentime)
				</script>】
				</div>
				<div class="clear"></div>
			</div>
			<div class="user_info5" id="positionContent"></div>
			
			
		</div>
	</div>
	</div>
	</div>
</div>
<!--头部与导航end-->

 <div id="mainLayout" leftWidth="180">
    <div position="left">
			<div id="lbox">
				<div id="lbox_topcenter">
				<div id="lbox_topleft">
				<div id="lbox_topright">
					<div class="lbox_title">
						操作菜单
					</div>
				</div>
				</div>
				</div>
				<div id="lbox_middlecenter">
				<div id="lbox_middleleft">
				<div id="lbox_middleright">
						<div id="bs_left" style="width:100%;">
						<IFRAME height="100%" width="100%"  frameBorder=0 id=frmleft name=frmleft src="<%=path%>/system/left.jsp"  allowTransparency="true"></IFRAME>
						</div>
						<!--更改左侧栏的宽度需要修改id="bs_left"的样式-->
				</div>
				</div>
				</div>
				<div id="lbox_bottomcenter">
				<div id="lbox_bottomleft">
				<div id="lbox_bottomright">
					<div class="lbox_foot"></div>
				</div>
				</div>
				</div>
			</div>
    </div>
    <div position="center">
   		<div class="ali01 ver01"  width="100%">
			<div id="rbox">
				<div id="rbox_topcenter">
				<div id="rbox_topleft">
				<div id="rbox_topright">
				</div>
				</div>
				</div>
				<div id="rbox_middlecenter">
				<div id="rbox_middleleft">
				<div id="rbox_middleright">
					<div id="bs_right">
					     <IFRAME height="100%" width="100%"  frameBorder=0 id=frmright name=frmright src="<%=path%>/business/Statistics.do?act=list"  allowTransparency="true"></IFRAME>
					</div>
				</div>
				</div>
				</div>
				<div id="rbox_bottomcenter" >
				<div id="rbox_bottomleft">
				<div id="rbox_bottomright">

				</div>
				</div>
				</div>
			</div>
		</div>
    </div>
</div> 

<!--尾部区域start-->
<div id="fbox">
	<div id="bs_footcenter">
	<div id="bs_footleft">
	<div id="bs_footright">
		星火在线 Online 技术客服电话400-888-999
	</div>
	</div>
	</div>
</div>
</div>
<!--尾部区域end-->

<!--浏览器resize事件修正start-->
<div id="resizeFix"></div>
<!--浏览器resize事件修正end-->

<!--窗口任务栏区域start-->
<div id="dialogTask" class="dialogTaskBg" style="display:none;"></div>
<!--窗口任务栏区域end-->

<!--载进度条start-->
<div class="progressBg" id="progress" style="display:none;"><div class="progressBar"></div></div>
<!--载进度条end-->
</body>
</html>
