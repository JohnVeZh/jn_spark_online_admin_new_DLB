$(function () {
    var $menu = $(".menu"), $menuLi = $menu.find("li"), $current = $menu.find('.current'), $li_3 = $menu.find('li.li_3'), $li_3_content = $li_3.find('.li_3_content'),
    $li_2 = $menu.find('li.li_2'), $li_2_content = $li_2.find('.li_2_content'),$li_1 = $menu.find('li.li_1'), $li_1_content = $li_1.find('.li_1_content'),
    $li_4 = $menu.find('li.li_4'), $li_4_content = $li_4.find('.li_4_content');
    $menuLi.hover(function () {
       var $this = $(this), num = $menuLi.index($this), current = $menuLi.index($(".first")), len = current - num;
       $current.removeClass("lihover");
       $menuLi.removeClass("first");
       $menuLi.removeClass("licurrent");
       $this.addClass("first");
       $this.addClass("licurrent");
    });
    $li_3.hover(function () {
        $li_3_content.stop(true, true).fadeIn(0);
    }, function () {
        $li_3_content.fadeOut(500, function () {
            $li_3_content.css("display", "none");
        });
    });
    $li_2.hover(function () {
        $li_2_content.stop(true, true).fadeIn(0);
    }, function () {
        $li_2_content.fadeOut(500, function () {
            $li_2_content.css("display", "none");
        });
    });
    $li_1.hover(function () {
        $li_1_content.stop(true, true).fadeIn(0);
    }, function () {
        $li_1_content.fadeOut(500, function () {
            $li_1_content.css("display", "none");
        });
    });
    $li_4.hover(function () {
        $li_4_content.stop(true, true).fadeIn(0);
    }, function () {
        $li_4_content.fadeOut(500, function () {
            $li_4_content.css("display", "none");
        });
    });
    $menu.mouseleave(function () {
        var $this = $(this), num = $menuLi.index($this), current = $menuLi.index($current), len = current - num;
        $menuLi.removeClass("first");
        $current.addClass("first");
        if (len <= 0) { len = -len; };
        $menu.stop().animate({ backgroundPosition: (100 * current + 1) + "px" + " bottom" }, 100 * len);
    });
    $("a.noclick").click(function (event) {
        event.preventDefault();
    });
});