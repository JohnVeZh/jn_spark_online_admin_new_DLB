$(document).ready(function() {
			$('.fancy').fancybox();
			$('.fancybox-thumbs').fancybox({
				prevEffect : 'none',
				nextEffect : 'none',
	
				closeBtn  : false,
				arrows    : false,
				nextClick : true,
	
				helpers : {
					thumbs : {
						width  : 50,
						height : 50
					}
				}
			});
});

//带缩略图的照片墙多张图片
function thumbImgsDivList(paths,stat,url){
	$("#imgsDiv").html("");
	var path = paths.split(",");
	var str = "";
	for(i=0;i<path.length;i++){
		if(path[i] != ""){
			str += '<div class="col-xs-12 col-sm-4 col-md-3  fancybox img-responsive">';
		    str += '  <a id="img'+i+'" class="fancybox-thumbs" data-fancybox-group="thumb" href="'+url+"/"+path[i]+'" >';
		    str += '  </a>';
	        str += '</div>';
		}
	}
	$("#imgsDiv").append(str);
	$("#img"+stat).click();
	
}

//带缩略图的照片墙
function thumbImgsDiv(paths,stat){
	$("#imgsDiv").html("");
	var path = paths.split(",");
	var str = "";
	for(i=0;i<path.length;i++){
		if(path[i] != ""){
			str += '<div class="col-xs-12 col-sm-4 col-md-3  fancybox img-responsive">';
		    str += '  <a id="img'+i+'" class="fancybox-thumbs" data-fancybox-group="thumb" href="'+path[i]+'" >';
		    str += '  </a>';
	        str += '</div>';
		}
	}
	$("#imgsDiv").append(str);
	$("#img"+stat).click();
	
}

//带左右箭头的照片墙
function galleryImgsDiv(paths,stat){
	$("#imgsDiv").html("");
	var path = paths.split(",");
	var str = "";
	for(i=0;i<path.length;i++){
		if(path[i] != ""){
			str += '<div class="col-xs-12 col-sm-4 col-md-3  fancybox img-responsive">';
			str += '  <a id="img'+i+'" class="fancy" data-fancybox-group="gallery" href="'+path[i]+'" >';
			str += '  </a>';
			str += '</div>';
		}
	}
	$("#imgsDiv").append(str);
	$("#img"+stat).click();
	
}