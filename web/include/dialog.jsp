<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.easecom.common.util.*"%>
<%
	String title="对话框错误";
    String msg="";
    String nexturl="javascript:history.back();";
    String butname="返回";
    try{
      	WebDialogBox dialog=(WebDialogBox)request.getAttribute("DialogBox");
        msg=dialog.getMessage();
        nexturl=dialog.getNextUrl();
        butname=dialog.getButtonName();
		title = dialog.getTitle();
    }catch(NullPointerException e){
        e.printStackTrace();
        msg="对话框不能获取WebDialgoBox对象！";
    }catch(Exception ex){
        ex.printStackTrace();
        msg=ex.getMessage();
    }
%>
<html>
<head>
<meta http-equiv="Expires" CONTENT="0">        
<meta http-equiv="Cache-Control" CONTENT="no-cache">        
<meta http-equiv="Pragma" CONTENT="no-cache"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=title%></title>
<%@ include file="/include/css.jsp" %>
</head>

<body leftmargin="0" topmargin="0">
	<br>
	<table width="70%"  border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td height="143" align="center" class="DataTable_Title" valign="middle"><img src="<%=contextPath%>/style/normal/images/infoimage.gif" width="18" height="18"><%=title%></td>
  </tr>         <td height="43"><tr>
          <td ><table width="100%"  border="0" cellpadding="0"    align="center">
					<tr>
        				<td>
							<div align="center"><span class="l2"><%=msg%><BR>
              				<BR>
<%
	if(!butname.equals("返回")){
%>
              					<A href="<%=nexturl%>"><%=butname%></A></span>
<%	}else{%>
			  					<span><A href="#" onClick="<%=nexturl%>"><%=butname%></A></span>
<%	}%>
							</div>
						</td>
      				</tr>
    			</table>
			</td>
  		</tr>
	</table>
</body>
</html>