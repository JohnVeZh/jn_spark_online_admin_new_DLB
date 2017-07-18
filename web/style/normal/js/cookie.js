//增加cookie
function addCookie(name,value,delayDays)
{
    var str=name+"="+escape(value);
    if (delayDays>0){
        var expires = new Date();
	    expires.setTime(expires.getTime() + delayDays * 24 * 3600* 1000);
        str=str+"; expires=" + expires.toGMTString();
    }
    document.cookie=str;
}
//删除cookeie
function deleteCookie(name)
{
	var expires = new Date();
	expires.setTime(expires.getTime() - 10000);
	document.cookie = name+"=; expires=" + expires.toGMTString();
}
//得到cookie的值
function getCookie(name)
{
	var cookieString = document.cookie;
	var str=unescape(cookieString);
	var arr=str.split("; ");
	for(var i=0;i<arr.length;i++){
	   var b=arr[i].split("=");
	   if (b[0]==name) return b[1];
	}
	return "";
}	