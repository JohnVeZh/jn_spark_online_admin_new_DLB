function setTab(name,cursel,n){
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById("con_"+name+"_"+i);
  menu.className=i==cursel?"hover":"";
 }
}



function buffer(a,b,c){
		var d;
		return function(){
			if(d)
			return;
			d=setTimeout(function(){
				a.call(this),d=undefined
			},b)
		}
	}
	(function(){
		function e(){
			var d=document.body.scrollTop||document.documentElement.scrollTop;
			d>b?(a.className="lib_Menubox lib_Menubox2",c&&(a.style.top=d-b+"px")):a.className="lib_Menubox"
		}
		var a=document.getElementById("float");
		if(a==undefined)
		return!1;
		var b=0,c,d=a;
		while(d)b+=d.offsetTop,d=d.offsetParent;
		c=window.ActiveXObject&&!window.XMLHttpRequest;
		if(!c||!0)
		window.onscroll=buffer(e,150,this)
	})();


