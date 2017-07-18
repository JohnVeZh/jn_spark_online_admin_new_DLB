function Timer(delay, period) {
	this.delay = delay?delay:0;// 启动多少秒后执行
	this.period = period?period:60;// 每隔多少秒执行一次
}

// 执行任务
Timer.prototype.task = function(vFunction){
	var delay = this.delay;
	var period = this.period;
	
	var run = function() {
		eval(vFunction);
		window.setTimeout(run, period * 1000);
	}
	
	window.setTimeout(run, delay*1000);
}
