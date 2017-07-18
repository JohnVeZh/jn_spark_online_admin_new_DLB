<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.easecom.common.util.PageAction"%>
<table width="98%"  border="0" align="center" cellspacing="0" cellpadding="0" style="display:none">
    <tr>
      <td><input type="hidden" name="pageAction" value="">
          <input type="hidden" name="currentPage" value="<%=lc.getCurrentPage()%>">
                   第<%=lc.getCurrentPage()%>页/共<%=lc.getTotalPages()%>页
          <input type="hidden" name="totalPage" value="<%=lc.getTotalPages()%>">
          记录总数：<input type="hidden" name="totalItem" value=""><%=lc.getTotalItem()%> (每页<%=lc.getItemsInPage()%>条)
      </td>
      <td>
         <span style="cursor:hand;" onclick="javascript:allData('<%=PageAction.JUMP.toString()%>','<%=lc.getTotalItem()%>')"><a href="#">全部数据</a></span>
      </td>
      <td>
         <div align="right">
              <a href="javascript:jumpPage('<%=PageAction.JUMP.toString()%>')">跳至</a>第
              <input name="jumpPage" type="text" class="textfield20" size="3" value="<%=lc.getCurrentPage()%>">
              <span class="16f">页&nbsp;</span>
              <a href="javascript:goPage('<%=PageAction.FIRST.toString()%>')">首页</a>
              <a href="javascript:goPage('<%=PageAction.PREVIOUS.toString()%>')">上页</a>
              <a href="javascript:goPage('<%=PageAction.NEXT.toString()%>')">下页</a>
              <a href="javascript:goPage('<%=PageAction.LAST.toString()%>')">末页</a>
         </div>
      </td>
   </tr>
</table>

<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="77"><img src="images/fanye_01.gif" width="77" height="30"  style="display:none"  onclick="javascript:goPage('<%=PageAction.PREVIOUS.toString()%>')"/></td>
                <td style="color:white;">第<%=lc.getCurrentPage()%>页/共<%=lc.getTotalPages()%>页</td>
                <td width="77"><img src="images/fanye_02.gif" width="77" height="30" style="display:none" onclick="javascript:goPage('<%=PageAction.NEXT.toString()%>')" /></td>
              </tr>
            </table>
 
<SCRIPT LANGUAGE="JavaScript">
var myform;
/*分页*/
function goPage(pageAction){
    var pageActionObj = document.all("pageAction");
    var currentPage = document.all("currentPage");
    var totalPage = document.all("totalPage");
    if(pageAction=="FIRST" || pageAction=="PREVIOUS"){
        if(parseInt(currentPage.value)==1){
			alert("您已经在第一页了！");
        }else{
            pageActionObj.value=pageAction;
			if(myform==null){
			   listForm.submit();
			}else{
              myform.submit();
			}
        }
    }else if(pageAction=="LAST" || pageAction=="NEXT"){
        if(parseInt(currentPage.value)==parseInt(totalPage.value)){
            alert("您已经在最后页了！");
        }else{
            pageActionObj.value=pageAction;
			if(myform==null){
			   listForm.submit();
			}else{
              myform.submit();
			}
        }
    }
}
/**全部数据*/
function allData(pageAction,totalItemCount){
    var pageActionObj = document.all("pageAction");
    var totalItem = document.all("totalItem");
    var jumpPage = document.all("jumpPage");
    pageActionObj.value=pageAction;
    jumpPage.value=1;
	if(totalItemCount==0)totalItemCount=15;
    totalItem.value=totalItemCount;
	if(myform==null){
	   listForm.submit();
	 }else{
       myform.submit();
	 }
}
/**跳页*/
function jumpPage(pageAction){
    var pageActionObj = document.all("pageAction");
    var currentPage = document.all("currentPage");
    var totalPage = document.all("totalPage");
    var jumpPage = document.all("jumpPage");
    var tt=parseInt(jumpPage.value);
    if(tt>parseInt(totalPage.value)){
        alert("共有"+totalPage.value+"页,您要去的第"+tt+"页并不存在！");
    }else if(tt>0){
        pageActionObj.value=pageAction;
		if(myform==null){
		   listForm.submit();
	    }else{
            myform.submit();
	    }
    }
}
</SCRIPT>