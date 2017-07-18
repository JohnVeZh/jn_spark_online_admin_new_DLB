var intDiff = parseInt(60);//倒计时总秒数量
function timer(intDiff,i){
  var tim = window.setInterval(function(){
        console.log(intDiff);
    if(intDiff<=0){
   		 clearInterval(tim);
    	$("#ssslkjsdf").attr("disabled", false);
    }
    	
    var day=0,
        hour=0,
        minute=0,
        second=0;//时间默认值       
    if(intDiff > 0){
        day = Math.floor(intDiff / (60 * 60 * 24));
        hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
        minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
        second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
    }
    if (minute <= 9) minute = '0' + minute;
    if (second <= 9) second = '0' + second;
		$("#msg-boxs").html("<span class=\"Prompt\">请"+minute+"分"+second+"秒后再发送！</span>");
    intDiff--;
    }, 1000);
} 
function timer1(intDiff){
    window.setInterval(function(){
    var day=0,
        hour=0,
        minute=0,
        second=0;//时间默认值       
    if(intDiff > 0){
        day = Math.floor(intDiff / (60 * 60 * 24));
        hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
        minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
        second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
    }
    if (minute <= 9) minute = '0' + minute;
    if (second <= 9) second = '0' + second;
	$("#windown-content #newerr").html("请再"+minute+"分"+second+"后再次发送!");
    intDiff--;
    }, 1000);
}








