(function($){
	/**
	  	按钮切换
	  */
	 $.ChanageNav = function(menus,curNav,curIndex){
		this.menus=menus;
		this.curNav = curNav;
		this.curIndex = curIndex || 0;
		//添加点击事件
		var _this = this;
		for(var i=0;i<this.menus.length;i++){
			(function(i){
				$(_this.menus[i]).click(function(){
					_this.curIndex=i;
					_this.skip(_this.curIndex);
				});
			})(i);
		};
		this.skip = function(i){
			this.rmClass();
			this.addCls(i);
		},
		this.rmClass = function(){
			for(var i=0;i<menus.length;i++){
				$(_this.menus[i]).removeClass(this.curNav);
			}
		},
		this.addCls = function(i){
			$(_this.menus[i]).addClass(this.curNav);
		}
		//设置默认选中
		this.skip(this.curIndex);
	};
})(jQuery);