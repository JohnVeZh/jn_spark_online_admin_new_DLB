// JavaScript Document
 function Td_Over(Element1){
    Element1.className = 'tdover';
 }
function Td_Out(Element1){
    Element1.className = 'tdout';
 }
function Td_Down(Element1){
    Element1.className = 'tddown';
 }
function Td_Click(No){
    var url;
	if (No == 0){
	url = "Manage_Main.asp"; 
	}else if (No == 1){
	url = "ArticleManage.asp";
	}else if (No == 2){
	url = "Manage_Eshop.asp"; 
	}else if (No == 3){
	url = "index.jsp"; 
	}else if (No == 4){
	url = "Manage_News.asp"; 
	}else if (No == 5){
	url = "Manage_Book.asp";  
	}else if (No == 6){
	url = "Manage_Listdownload.asp?class=0";  
	}else if (No == 7){
	url = "Manage_job.asp";  
	}else if (No == 8){
	url = "Manage_sale.asp"; 
	}else if (No == 9){
	url = "VoteManage.asp"; 
	}else if (No == 10){
	url = "Manage_Link.asp"; 
	}else if (No == 11){
	url = "Loginout.asp"; 
	}else if (No == 12){
	url = "Manage.asp"; 
	}else if (No == 13){
	url = "Viewuser.asp"; 
	}    
	location.href=url;
 }