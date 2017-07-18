<!--
function setTab(name,cursel,n,href){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  menu.className=i==cursel?"renz_cas_0"+i:"renz_0"+i;
  con.style.display=i==cursel?"block":"none";
  window.location.href = href;
 }
}
//-->