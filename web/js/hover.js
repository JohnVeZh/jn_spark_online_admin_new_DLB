// JavaScript Document
// JavaScript Document
var wbsource=3322823291;
function showmoreman()
{
	$("#moreman").toggle()
}
function show_comp_box(num){
	for(var i=0;i<6;i++){
		document.getElementById('all_company_box'+i).style.display="none";
		document.getElementById('all_company_re_id'+i).className="close";
	}
	document.getElementById('all_company_box'+num).style.display="block";
		document.getElementById('all_company_re_id'+num).className="open";
}
$(function()
{
	if($(".wbzhuanfa").length)
	{
		var wbzhuanfa=$(".wbzhuanfa");
		var wbid="";
		wbzhuanfa.each(function()
		{
			wbid+=$(this).data("id")+",";
		});
		$.ajax({
		 url: "https://api.weibo.com/2/statuses/count.json",
		 type: "GET",
		 dataType: "jsonp",
		 data: {
			source: wbsource,
			ids: wbid
		 },
		 success: function(data){
			if(data.code)
			{
				$(data.data).each(function(index, element) {
					$(".wbzhuanfa[data-id='" + element.id + "']").html(element.reposts);
					$(".wbpinglun[data-id='" + element.id + "']").html(element.comments);
				});
			}
		 }
	 });
	}
	
	if($(".rypinglunlist").length)
	{
		var wbpinglun=$(".rypinglunlist");
		wbpinglun.each(function()
		{
			var wbpinglunid=$(this).data("id");
			var wbpingluncount=$(this).data("count");
			$.ajax({
				 url: "https://api.weibo.com/2/comments/show.json",
				 type: "GET",
				 dataType: "jsonp",
				 data: {
					source: wbsource,
					id: wbpinglunid,
					count:wbpingluncount
				 },
				 success: function(data){
					if(data.code)
					{
						$(data.data.comments).each(function(index, element) {
							$(".rypinglunlist[data-id='" + wbpinglunid + "'] li.more").before("<li><span class=\"u\">" + element.user.name + "</span>：" + element.text + "</li>");
						});
					}
				 }
			 });
		});
	}
	if($(".rybtn").length)
	{
		$(".rybtn").each(function()
		{
			$(this).on('click',function()
			{
				if($(this).is(".current"))
				{
					$(this).removeClass("current").html("展开").siblings('.rypinglunlist').stop().slideUp('fast');
				}else{
					$(this).addClass("current").html("关闭").siblings('.rypinglunlist').stop().slideDown('fast');
				}
			});
		});
	}
	if($.query)
	{
		var loon=$.query.get('tab');
		loon=loon | 0;
		if(loon && $("[loon='tit']"))
		{
			$("[loon='tit'] .current").removeClass("current");
			$("[loon='tit'] > li").eq(loon-1).addClass("current");
			if($("[loon='con'] > li").length)
			{
				$("[loon='con'] > li").hide().eq(loon-1).show();
			}
		}
	}
	var url=window.location.href;
	var navcur=0;
	if(url.indexOf("/Solutions/")!=-1)
	{
		navcur=1;
	}else if(url.indexOf("/product/")!=-1)
	{
		navcur=2;
	}else if(url.indexOf("/huoban/")!=-1)
	{
		navcur=3;
	}else if(url.indexOf("/service/")!=-1)
	{
		navcur=4;
	}else if(url.indexOf("/zhibo/")!=-1)
	{
		navcur=5;
	}else if(url.indexOf("/guanli/")!=-1)
	{
		navcur=6;
	}else if(url.indexOf("/news/")!=-1)
	{
		navcur=7;
	}else if(url.indexOf("/stock/")!=-1)
	{
		navcur=8;
	}else if(url.indexOf("/about/")!=-1)
	{
		navcur=9;
	}else if(url.indexOf("/contact/")!=-1)
	{
		navcur=10;
	}else{
		navcur=0;
	}
	$("#navlist a.current").removeClass("current");
	$("#navlist a").eq(navcur).addClass("current");
	//////////////////////////////////////
	$(".huafenlist > li").hover(function()
	{
		$(this).css({"border":"2px solid #f12222"});
	},function()
	{
		$(this).css({"border":"2px solid #ffffff"});
	});
	//////////////////////////////////////
	$(document).on("click",".open",function()
	{
		$(this).toggleClass("close").prev("div").toggle();
//		if($(this).text()=="展开")
//		{
//			$(this).text("关闭");
//		}else{
//			$(this).text("展开");
//		}
	});
	//////////////////////////////////////
	$("#chanpinliebiao li").hover(function()
	{
		$(this).css({"border":"2px solid #e90101"});
	},function()
	{
		$(this).css({"border":"2px solid #dfdfdf"});
	});
	//////////////////////////////////////
	var hl3,hl2,hl1,hlnav;
	$("#navlist li").mouseenter(function()
	{
		clearTimeout(hlnav);
		$(".navcon").hide().eq($(this).index()).show();
	});
	$("#nav").mouseleave(function()
	{
		hlnav=setTimeout(function(){$(".navcon").hide()},200);
	});
	$("#navconlist").mouseenter(function()
	{
		clearTimeout(hlnav);
	});
	$("#navconlist").mouseleave(function()
	{
		hlnav=setTimeout(function(){$(".navcon").hide()},200);
	});
	
	$("#showhl1").mouseenter(function()
	{
		clearTimeout(hl1);
		$("#yp_sy_nav").show();
	}).mouseleave(function(){
		hl1=setTimeout(function(){$("#yp_sy_nav").hide();},200);
	});
	$("#yp_sy_nav").mouseenter(function(){
		clearTimeout(hl1);
	}).mouseleave(function()
	{
		$(this).hide();
	});
	/////////////////////////////////
	$("#showhl2").mouseenter(function()
	{
		$("#wk_div1").hide();
		$("#wk_div3").show();
		clearTimeout(hl2);
	}).mouseleave(function(){
		hl2=setTimeout(function(){$("#wk_div3").hide();},500);
	});
	$("#wk_div3").mouseenter(function()
	{
		clearTimeout(hl2);
	});
	$("#wk_div3").mouseleave(function()
	{
		hl2=setTimeout(function(){$("#wk_div3").hide();},500);
	});
	//////////////////////////////
	$("#showhl3").mouseenter(function()
	{
		$("#wk_div1").show();
		$("#wk_div3").hide();
		clearTimeout(hl3);
	}).mouseleave(function(){
		hl3=setTimeout(function(){$("#wk_div1").hide();},500);
	});
	$("#wk_div1").mouseenter(function()
	{
		clearTimeout(hl3);
	});
	$("#wk_div1").mouseleave(function()
	{
		hl3=setTimeout(function(){$("#wk_div1").hide();},500);
	});
	$(".tab > li").mouseover(function()
	{
		$(".tab .current").removeClass("current");
		$(this).addClass("current");
		$(".con3 > li").hide().eq($(this).index()).show();
	});
	$(".tabcp > li").mouseover(function()
	{
		$(".tabcp .current").removeClass("current");
		$(this).addClass("current");
		$(".con3 > li").hide().eq($(this).index()).show();
		if($(this).is("[reimg]"))
		{
			$(this).siblings().find("img").each(function()
			{
				$(this).attr("src",$(this).attr("src").toString().replace("cur.png",".png"));
			});
			$(this).find("img").attr("src",$(this).find("img").attr("src").toString().replace(/(\d)\.png/i,"$1cur.png"))
		}
	});
	var lunboAnimation;
	function lunbo()
	{
		run();
		$(".lunbotab li").click(function(){
			clearTimeout(lunboAnimation);
			$(".lunbotab li").eq($(this).index()).addClass("current").siblings(".current").removeClass("current");
			$(".lunboimg li").eq($(this).index()).fadeIn().siblings(":visible").fadeOut();
			lunboAnimation=setTimeout(function(){run()},2000);
		});
	}
	function run()
	{
		var cur=$(".lunbotab .current").index();
		if(cur>=$(".lunbotab li").length-1)
		{
			cur=0;
		}else{
			cur++;
		}
		$(".lunbotab li").eq(cur).addClass("current").siblings(".current").removeClass("current");
		$(".lunboimg li").eq(cur).fadeIn().siblings(":visible").fadeOut();
		lunboAnimation=setTimeout(function(){run()},2000);
	}
	setTimeout(function(){lunbo()},2000);
	
	$(".mauto").mouseenter(function(){
		if(!$(this).is(".current"))
		{
			$(".mauto.current").removeClass("current");
			$(this).addClass("current");
		}
	});
});