<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.easecom.common.util.PageAction"%>
<table width="98%"  border="0" align="center" cellspacing="0" cellpadding="0">
    <tr >
      <td class="font_12"><input type="hidden" name="pageAction" value="">
          <input type="hidden" name="currentPage" value="<%=lc.getCurrentPage()%>">
                   第<%=lc.getCurrentPage()%>页/共<%=lc.getTotalPages()%>页
          <input type="hidden" name="totalPage" value="<%=lc.getTotalPages()%>">
          记录总数：<input type="hidden" name="totalItem" value=""><%=lc.getTotalItem()%> (每页<%=lc.getItemsInPage()%>条)
      </td>
      </tr>
      <tr>
      <td class="font_12">
         <div style="border: 0px thin #CCCCCC; float: left;" class="padding_right0">
              <div style="float: left;padding-top: 4px; border: thin 1px #FFFFFF;">
              <a href="javascript:goPage('<%=PageAction.FIRST.toString()%>')"><span class="icon_page_first">第一页</span></a>
              <a href="javascript:goPage('<%=PageAction.PREVIOUS.toString()%>')"><span class="icon_page_prev">上一页</span></a>
              <a href="javascript:goPage('<%=PageAction.NEXT.toString()%>')"><span class="icon_page_next">下一页</span></a>
              <a href="javascript:goPage('<%=PageAction.LAST.toString()%>')"><span class="icon_page_last">最后一页</span></a>
         	</div>
         </div>
      </td>
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
			top.Dialog.alert("您已经在第一页了！");
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
            top.Dialog.alert("您已经在最后页了！");
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
        top.Dialog.alert("共有"+totalPage.value+"页,您要去的第"+tt+"页并不存在！");
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