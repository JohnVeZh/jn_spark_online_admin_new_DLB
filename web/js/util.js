// JavaScript Document
/***
 鼠标经过显示子菜单
**/
function Hover(obj){
	this.obj = obj;
	this.content = content;
	console.log(obj);
	for(var i=0;i<obj.length;i++){
		var _this = this;
		//console.log($(_this));
		(function(i){
			$(_this).hover(
			  function () {
			//	$(this).addClass("hover");
				alert("hover");
			  },
			  function () {
			//	$(_this).removeClass("hover");
				alert("remove ");
			  }); 
		})(i);
	}
}