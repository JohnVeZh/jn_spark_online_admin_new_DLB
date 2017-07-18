//====================================================================================================
// [插件名称] jQuery ImgSlider
//----------------------------------------------------------------------------------------------------
//[描      述]  一个图片切换插件，可以预览，可以动态做出图片说明
//[使用方法]  生成一个DIV，包含需要幻灯演示的图片，然后用一个DIV包裹，并且用这个包裹的DIV直接调用ImgSlider方法
//			如：<div id="diva"><div class="divb">你的图片</div></div>
//简单调用：$("#diva").ImgSlider();
//[源码下载]  http://files.cnblogs.com/walkerwang/slideImg.rar
//----------------------------------------------------------------------------------------------------
// [作者网名] walkerwang
// [邮    箱] walkerwzy@gmail.com
// [作者博客] http://walkerwang.cnblogs.com
// [更新日期] 2010-06-24
// [版 本 号] ver1.0
//====================================================================================================
(function($) {
    $.fn.ImgSlider = function(options) {
        var options = $.extend({}, $.fn.ImgSlider.defaults, options);
        this.each(function() {
			var flag=0;//标识小图hover时是否执行切换事件,0未执行，默认
			var objH;//当前大图片对象句柄
			var m;//小图切换setinterval标识
			var th;//小图hover事件settimeout标识
			var forceIndex=-1;//强制移动到索引，-1表示不强制
			var indexbefore;//上一个图片的索引
			var thisWrap=this;
			var bigImgWrap;//大图片容器
			var titleWrap=$("<div class=\"imgtitle\"/>");//标题和介绍区容器，默认类别imgtitle
			var naviWrap=$("<div class=\"smallimgs\"/>");//导航区容器，默认类名smallimgs
			var naviNumWrap=$("<div/>",{"class":"numNavi"});//数字导航容器，默认类名numNavi
			var imgCollection;//切换的大图片集合
			var imgPrev;//小图片容器集合
			var numNavis;//数字导航对象集合
			//初始化
			bigImgWrap=$("."+options.wrapName,this).eq(0);
			imgCollection=bigImgWrap.find("img");
			$(this).append(titleWrap).append(naviWrap);//标题、导航容器加入DOM树
			titleWrap.append("<div class='it_bg'/>").append("<div class='it_title'/>").append("<div class='it_cont'/>");//title加入Dom树			
			
			//生成导航
			var imgCount=imgCollection.size();
			for(var i=0;i<imgCount;i++){
				//图片
				var imgWrap=$("<div class='imgs'/>");
				var imgElement=$("<img/>",{alt:'',src:imgCollection.eq(i).attr("src")});
				imgWrap.append(imgElement).appendTo(naviWrap);
				//数字
				var numElement=$("<span>"+(i+1)+"</span>");
				numElement.appendTo(naviNumWrap);
				//处理没有图片介绍属性（cont）的情况：每张图片有地址，标题，和描述三个属性
				//if(!imgCollection[i].hasAttribute("cont")) imgCollection.eq(i).attr("cont","");  //IE不支持
				if(typeof imgCollection.eq(i).attr("cont") =="undefined") imgCollection.eq(i).attr("cont","");
			}
			imgPrev=$(".imgs",naviWrap);//取得小图容器集合
			numNavis=$("span",naviNumWrap);//取得数字导航按钮集合
			
			//填充标题
			if(options.hideCaptial) $(".imgtitle",this).hide();
			else{
				objH=imgCollection.eq(0);
				titleWrap.find(".it_title").html(objH.attr("title")).end().find(".it_cont").html(objH.attr("cont"));
			}
			
			if(options.hidePreview){//判断是否显示导航，如不显示，则显示数字
				naviWrap.hide();
				$(this).append(naviNumWrap);
			}
			
			//css部分
			naviWrap.css({backgroundPosition: options.arrPosition+ "px 0"});//初始箭头位置
			$(this).css({width:options.wrapWidth,height:options.wrapHeight,border:"1px solid #ccc",position:"relative",overflow:"hidden"});
			bigImgWrap.css({height:options.wrapHeight,width:options.wrapWidth,overflow:"visible",whiteSpace:"nowrap", marginLeft:-options.wrapWidth});
			bigImgWrap.find("img").css({height:options.wrapHeight,width:options.wrapWidth,cursor:"pointer"});
			$(".imgtitle, .it_bg",this).css({textAlign:"center",width:"100%",position:"absolute",top:0,left:0,color:"#fff"});
			$(".it_bg",this).css({background:"black",filter:"alpha(opacity=40)",opacity:0.4,height:"100%"});
			$(".it_title",this).css({font:"800 1.5em 宋体",position:"relative",paddingTop:5});
			$(".it_cont",this).css({whiteSpace:"normal",textAlign:"left",position:"relative",padding:"5px 15px"});
			
			bigImgWrap.prepend(bigImgWrap.find("img:last").clone());//scroll fix
			if($.browser.msie){ //重设预览图宽度,ie hacker
				imgPrev.find("img").removeAttr("width").removeAttr("height");
			}
			//当前导航位置
			imgPrev.eq(0).addClass("highlight_img");
			if(options.hidePreview) numNavis.eq(0).addClass("current");
			
			m=setInterval(imgscroll,options.interval);
			
			//主算法
			function imgscroll(){
				var i;
				//计算下一个索引
				if(forceIndex==-1){
					i=naviWrap.find(".imgs").index($("div.highlight_img",thisWrap));//当前高亮位置
					if(i==imgCollection.length-1) i=0;
					else i++;
				}else i=forceIndex;
				
				if(indexbefore+1==imgCollection.length){
					if(i==0) bigImgWrap.css({marginLeft:0});//scroll fix
				}
				bigImgWrap.animate({marginLeft:options.wrapWidth*(-i-1)},options.speed);//移动大图
				//移动光标、高亮框
				$(".highlight_img",thisWrap).removeClass("highlight_img");
				var imgleft=options.arrPosition+i*70;
				naviWrap.animate({backgroundPosition:imgleft+"px 0"},options.speed,function(){$(".imgs",thisWrap).eq(i).addClass("highlight_img");});
				//移动数字导航
				$(".current",naviNumWrap).removeClass("current");
				numNavis.eq(i).addClass("current");
				indexbefore=i;//保存本次索引
				if(!options.hideCaptial){
					titleWrap.hide();
					objH=bigImgWrap.find("img").eq(i+1);
					titleWrap.find(".it_title").html(objH.attr("title")).end().find(".it_cont").html(objH.attr("cont")).end().slideDown(options.speed);
				}
			}
					
			//响应hover事件——预览图
			$(".smallimgs",thisWrap).find(".imgs").live("mouseover",function(){
					var thisobj=$(this);
					th=setTimeout(function(){
					clearInterval(m);
					forceIndex=$(".smallimgs",thisWrap).find(".imgs").index(thisobj);
					imgscroll(i);
					flag=1;//延时被执行到的标识
				},300);
			});
			
			$(".smallimgs",thisWrap).find(".imgs").live("mouseout",function(){
				clearTimeout(th);
				if(flag==1){
					m=setInterval(imgscroll,options.interval);
					flag=0;
					forceIndex=-1;
				}
			});
			
			//响应hover事件——数字
			$(".numNavi",thisWrap).find("span").live("mouseover",function(){
				var i=numNavis.index(this);//得到索引
				$(".smallimgs",thisWrap).find(".imgs").eq(i).trigger("mouseover");
			});
			$(".numNavi",thisWrap).find("span").live("mouseout",function(){
				clearTimeout(th);
				if(flag==1){
					m=setInterval(imgscroll,options.interval);
					flag=0;
					forceIndex=-1;
				}
			});
		});
    };
    //默认值
    $.fn.ImgSlider.defaults = {
		wrapName:'imgb',//窗口内唯一包裹所有幻灯图片的div的类名
		wrapWidth:351,//容器宽度
		wrapHeight:396,//容器高度
		speed:1000,//切换速度
		interval:5000,//切换间隔
		arrPosition:-403,//预览图箭头初始位置 
		hidePreview:false,//隐藏预览图，隐藏后会显示数字导航
		hideCaptial:false//隐藏标题/介绍
    }
})(jQuery);