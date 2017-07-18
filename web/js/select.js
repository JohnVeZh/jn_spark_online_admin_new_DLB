// JavaScript Document
$(function(){
	
	//select 模拟框
	$("#options").hover(function(){
		$("#options dd,#options b").show();
	}, function() {
		$("#options dd").hide();
	});
	
	$("#options ,#options div").hover(function(){
		$(this).addClass("hover");
	},function(){
		$(this).removeClass("hover");
	});
	
	$("#keyType").val(this.id);
	
	$("#options div").click(function(){
		$("#keywords").val($(this).html());
		$("#keyType").val(this.id);		
		$("#options dd").hide();
	});
	
	//keywords text
	var keyword = "请输入商户名或优惠券名";
    $(".birds").val(keyword).bind("focus",function(){
		if(this.value == keyword){
			this.value = "";
			this.className = "focus_text"
		}
	}).bind("blur",function(){
		if(this.value == ""){
			this.value = keyword;
			this.className = "blur_text"
		}
	});
	
});

//最后获取 text 文本框的值
$("#text").val($("#").html());