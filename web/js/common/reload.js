
/**
 * 打开多个弹窗情况下刷新某一个弹窗
 * @param srcKey  src 路径关键字 例如 TbCouponProduct.do?act=list
 */
function reload(srcKey) {
    var iframes =  top.document.getElementsByTagName("iframe") ;
    for(var i = 0;i<iframes.length;i++){
        if(iframes[i].src.replace(srcKey,"").length<iframes[i].src.length){
            iframes[i].contentWindow.location.reload();
        }
    }
}