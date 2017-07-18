<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.easecom.system.model.SysOrg"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="java.util.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
<%
	String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":"
				+ request.getServerPort() + path + "/";
				
	String backMark = (String)request.getAttribute("backMark");
%>
<head>
	<html:base />
	<!--框架必需start-->
	<script type="text/javascript" src="<%=path%>/js/jquery-1.4.js"></script>
	<script type="text/javascript" src="<%=path%>/js/framework.js"></script>
	<link href="<%=path%>/css/import_basic.css" rel="stylesheet"
		type="text/css" />
	<link rel="stylesheet" type="text/css" id="skin" />
	<!--框架必需end-->
	<script type="text/javascript"
		src="<%=basePath%>js/tree/MzTreeView/MzTreeView12.js"></script>
	<title>rolefuncfg.jsp</title>

	<script type="text/javascript">

        function getNodeID(){
            alert("aa");
        }
        
        
        //确认选择
    function confirmSelect(){
       if ($("#treelist :checked").length==0){top.Dialog.alert("请先选数据");return false;}
       if ($("#treelist :checked").length >1){top.Dialog.alert("请选择一条数据");return false;}
       var names="" ;
       var ids="";
       $("[type='checkbox'][name='sel']").each(function(){
         if(this.checked){
	          id = $(this).attr("id");
	          id = id.substring(id.lastIndexOf("_")+1);
	          tcn = tree.node[id];
	          if(tcn.sid!='FFFFFF'){
	               names =$(this).next().text();
	               ids = tcn.sid;
	           }
	         }
    });
    var backMark='<%=backMark%>';
    if("userImport" == backMark){
	    top.document.getElementById("_DialogFrame_diag01").contentWindow.getRes(ids,names);
    }else{
   	 top.document.getElementById("frmright").contentWindow.main.getRes(ids,names);
   	 }
    top.Dialog.close();
    }
    </script>
</head>
<%
	List nodes = (List) request.getAttribute("nodes");
%>

<body>
	<script type="text/javascript">
	 var realHeight=300;             
    document.write('<div class="dtree" name="treelist" id="treelist" style="width:300;height:' + realHeight + ';overflow:auto;border-width:thin;background-color:white">');
             var tree = new MzTreeView("tree");
             tree.icons["property"] = "L1.gif";
             tree.icons["css"] = "L1.gif";
             tree.icons["book"]  = "L1.gif";
             tree.iconsExpand["book"] = "L1.gif"; //展开时对应的图片

             tree.setIconPath("<%=basePath%>js/tree/MzTreeView/img/"); //可用相对路径
              
             tree.N["0_FFFFFF"] = "T:组织目录;";
             
             <%
                SysOrg po = null;
                for(int i=0;i<nodes.size();i++){
                    po = (SysOrg)nodes.get(i);
                %>
                    tree.N["<%=po.getSysOrg().getId()%>_<%=po.getId()%>"] = "T:<%=po.getName()%>;ctrl:sel";
             <%   }
             %>
            
             document.write(tree.toString());    //亦可用 obj.innerHTML = tree.toString();
           document.write('</div>');
        </script>
	<input type="button" value="确认选择" onclick="confirmSelect()">
</body>
</html:html>
