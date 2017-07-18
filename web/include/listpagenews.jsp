<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="com.easecom.common.util.PageAction"%>
  <div style="height:35px;">
<div class="float_left padding5"><input type="hidden" name="pageAction" id="pageAction" value="">
          <input type="hidden" name="currentPage" id="currentPage" value="<%=lc.getCurrentPage()%>">
                   第<%=lc.getCurrentPage()%>页/共<%=lc.getTotalPages()%>页
          <input type="hidden" name="totalPage"  id="totalPage" value="<%=lc.getTotalPages()%>">
          记录总数：<input type="hidden" name="totalItem" value=""><%=lc.getTotalItem()%> (每页<%=lc.getItemsInPage()%>条)<span style="cursor:hand;display:none;" onclick="javascript:allData('<%=PageAction.JUMP.toString()%>','<%=lc.getTotalItem()%>')"><a href="#">全部数据</a></span></div>
<div class="float_right padding5">
	 <div class="float_left padding_top4">
	<span class="icon_page_first hand" onclick="javascript:goPage('<%=PageAction.FIRST.toString()%>')" id="d1">第一页</span> <span class="icon_page_prev hand" onclick="javascript:goPage('<%=PageAction.PREVIOUS.toString()%>')" id="s1">上一页</span>
	<span class="icon_page_next hand" onclick="javascript:goPage('<%=PageAction.NEXT.toString()%>')" id="x1">下一页</span> <span class="icon_page_last hand" onclick="javascript:goPage('<%=PageAction.LAST.toString()%>')" id="h1">最后一页</span><span><a href="javascript:jumpPage('<%=PageAction.JUMP.toString()%>')">跳至</a>第<input name="jumpPage" id="jumpPage" onfocus="this.select();" type="text" style="width:30px;" size="2" value="<%=lc.getCurrentPage()%>">页</span>
	</div>
	 
	 <div class="clear"></div>
</div>
<div class="clear"></div>
</div>
<SCRIPT LANGUAGE="JavaScript">
var myform;
/*分页*/
function goPage(pageAction){
    var pageActionObj = document.getElementById("pageAction");
    var currentPage = document.getElementById("currentPage");
    var totalPage = document.getElementById("totalPage");
    if(pageAction=="FIRST" || pageAction=="PREVIOUS"){
        if(parseInt(currentPage.value)==1){
			top.Dialog.alert("您已经在第一页了！");
        }else{
            pageActionObj.value=pageAction;
			if(myform==null){
			   document.listForm.submit();
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
			   document.listForm.submit();
			}else{
              myform.submit();
			}
        }
    }
}
/**全部数据*/
function allData(pageAction,totalItemCount){
    var pageActionObj = document.getElementById("pageAction");
    var totalItem = document.getElementById("totalItem");
    var jumpPage = document.getElementById("jumpPage");
    pageActionObj.value=pageAction;
    jumpPage.value=1;
	if(totalItemCount==0)totalItemCount=15;
    totalItem.value=totalItemCount;
	if(myform==null){
	   document.listForm.submit();
	 }else{
       myform.submit();
	 }
}
/**跳页*/
function jumpPage(pageAction){
    var pageActionObj = document.getElementById("pageAction");
    var currentPage = document.getElementById("currentPage");
    var totalPage = document.getElementById("totalPage");
    var jumpPage = document.getElementById("jumpPage");
    var tt=parseInt(jumpPage.value);
    if(tt>parseInt(totalPage.value)){
        top.Dialog.alert("共有"+totalPage.value+"页,您要去的第"+tt+"页并不存在！");
    }else if(tt>0){
        pageActionObj.value=pageAction;
		if(myform==null){
		   document.listForm.submit();
	    }else{
            myform.submit();
	    }
    }
}
</SCRIPT>
 