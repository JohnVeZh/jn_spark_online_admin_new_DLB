//star
$(document).ready(function(){
    var stepW = 21;
    var description = new Array("很不满意","不满意","一般","满意","很满意");
    var stars = $("#star > li");
    var descriptionTemp;
    $("#showb").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description").text(description[i]);
                $("#grade").val(i+1);
            },
            function(){
                if(descriptionTemp != null){
                    $(".description").text(descriptionTemp);
                    $("#grade").val(i+1);
                  }
                else 
                    $(".description").text(" ");
            }
        );
    });
});
$(document).ready(function(){
    var stepW = 21;
    var description = new Array("很不满意","不满意","一般","满意","很满意");
    var stars = $("#star6 > li");
    var descriptionTemp;
    $("#showb6").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb6").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description6").text(description[i]);
            },
            function(){
                if(descriptionTemp != null)
                    $(".description6").text(descriptionTemp);
                else 
                    $(".description6").text(" ");
            }
        );
    });
});
$(document).ready(function(){
    var stepW = 21;
    var description = new Array("很不满意","不满意","一般","满意","很满意");
    var stars = $("#star7 > li");
    var descriptionTemp;
    $("#showb7").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb7").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description7").text(description[i]);
            },
            function(){
                if(descriptionTemp != null)
                    $(".description7").text(descriptionTemp);
                else 
                    $(".description7").text(" ");
            }
        );
    });
});
$(document).ready(function(){
    var stepW = 17;
    var description = new Array("和描述严重不符，卖家好黑心","和描述不符，鄙视一下","和描述基本相符，勉强接受","和描述大体相符，挺满意","和描述非常相符，好喜欢");
    var stars = $("#star2 > li");
    var descriptionTemp;
    $("#showb2").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb2").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description2").text(description[i]);
                //与宝贝相符度
                $("#descs").val(i+1);
            },
            function(){
                if(descriptionTemp != null){
                    $(".description2").text(descriptionTemp);
                  	  $("#descs").val(i+1);
                   }
                else 
                    $(".description2").text(" ");
            }
        );
    });
});
$(document).ready(function(){
    var stepW = 17;
    var description = new Array("商家发货速度慢的像蜗牛","商家发货速度有点慢，需要提速呀","商家发货速度勉强可以接受","商家发货速度还不错，再接再厉","闪电般的发货速度，给力");
    var stars = $("#star3 > li");
    var descriptionTemp;
    $("#showb3").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb3").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description3").text(description[i]);
                 $("#bus_speed").val(i+1);
            },
            function(){
                if(descriptionTemp != null){
                    $(".description3").text(descriptionTemp);
                     $("#bus_speed").val(i+1);
                    }
                else 
                    $(".description3").text(" ");
            }
        );
    });
});
$(document).ready(function(){
    var stepW = 17;
    var description = new Array("物流速度慢的像蜗牛","物流速度有点慢，需要提速呀","物流速度勉强可以接受啦","物流速度还不错，再接再厉","闪电般的发货速度呀，给力");
    var stars = $("#star4 > li");
    var descriptionTemp;
    $("#showb4").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb4").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description4").text(description[i]);
                $("#express_speed").val(i+1);
            },
            function(){
                if(descriptionTemp != null){
                    $(".description4").text(descriptionTemp);
                     $("#express_speed").val(i+1);
                    }
                else 
                    $(".description4").text(" ");
            }
        );
    });
});
$(document).ready(function(){
    var stepW = 17;
    var description = new Array("商家态度很差，简直不把顾客当回事","商家有点不耐烦，爱搭不理的样子","商家态度一般，还凑合吧","商家服务挺好的，总体满意","商家的服务太棒了，完全超出期望值");
    var stars = $("#star5 > li");
    var descriptionTemp;
    $("#showb5").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb5").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description5").text(description[i]);
                $("#service").val(i+1);
            },
            function(){
                if(descriptionTemp != null){
                    $(".description5").text(descriptionTemp);
                    $("#service").val(i+1);
                    }
                else 
                    $(".description5").text(" ");
            }
        );
    });
});
$(document).ready(function(){
    var stepW = 17;
    var description = new Array("商家态度很差，简直不把顾客当回事","商家有点不耐烦，爱搭不理的样子","商家态度一般，还凑合吧","商家服务挺好的，总体满意","商家的服务太棒了，完全超出期望值");
    var stars = $("#star5 > li");
    var descriptionTemp;
    $("#showb5").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb5").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description5").text(description[i]);
            },
            function(){
                if(descriptionTemp != null)
                    $(".description5").text(descriptionTemp);
                else 
                    $(".description5").text(" ");
            }
        );
    });
});
$(document).ready(function(){
    var stepW = 17;
    var description = new Array("和描述严重不符，卖家好黑心","和描述不符，鄙视一下","和描述基本相符，勉强接受","和描述大体相符，挺满意","和描述非常相符，好喜欢");
    var stars = $("#star8 > li");
    var descriptionTemp;
    $("#showb8").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb8").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description8").text(description[i]);
            },
            function(){
                if(descriptionTemp != null)
                    $(".description8").text(descriptionTemp);
                else 
                    $(".description8").text(" ");
            }
        );
    });
});
$(document).ready(function(){
    var stepW = 17;
    var description = new Array("商家发货速度慢的像蜗牛","商家发货速度有点慢，需要提速呀","商家发货速度勉强可以接受","商家发货速度还不错，再接再厉","闪电般的发货速度，给力");
    var stars = $("#star9 > li");
    var descriptionTemp;
    $("#showb9").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb9").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description9").text(description[i]);
            },
            function(){
                if(descriptionTemp != null)
                    $(".description9").text(descriptionTemp);
                else 
                    $(".description9").text(" ");
            }
        );
    });
});
$(document).ready(function(){
    var stepW = 17;
    var description = new Array("物流速度慢的像蜗牛","物流速度有点慢，需要提速呀","物流速度勉强可以接受啦","物流速度还不错，再接再厉","闪电般的发货速度呀，给力");
    var stars = $("#star10 > li");
    var descriptionTemp;
    $("#showb10").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb10").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description10").text(description[i]);
            },
            function(){
                if(descriptionTemp != null)
                    $(".description10").text(descriptionTemp);
                else 
                    $(".description10").text(" ");
            }
        );
    });
});
$(document).ready(function(){
    var stepW = 17;
    var description = new Array("商家态度很差，简直不把顾客当回事","商家有点不耐烦，爱搭不理的样子","商家态度一般，还凑合吧","商家服务挺好的，总体满意","商家的服务太棒了，完全超出期望值");
    var stars = $("#star11 > li");
    var descriptionTemp;
    $("#showb11").css("width",0);
    stars.each(function(i){
        $(stars[i]).click(function(e){
            var n = i+1;
            $("#showb11").css({"width":stepW*n});
            descriptionTemp = description[i];
            $(this).find('a').blur();
            return stopDefault(e);
            return descriptionTemp;
        });
    });
    stars.each(function(i){
        $(stars[i]).hover(
            function(){
                $(".description11").text(description[i]);
            },
            function(){
                if(descriptionTemp != null)
                    $(".description11").text(descriptionTemp);
                else 
                    $(".description11").text(" ");
            }
        );
    });
});
function stopDefault(e){
    if(e && e.preventDefault)
           e.preventDefault();
    else
           window.event.returnValue = false;
    return false;
};