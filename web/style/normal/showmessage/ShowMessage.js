if (typeof _editor_url == "string") {
	_showmessage_path = _showmessage_path.replace(/\x2f*$/, '/');
} else {
	_showmessage_path = '';
}

function ShowMessage() {};
ShowMessage.PopUp = function (left, top, width, height) {
	this.caption = "信息提示";// 窗口标题
	this.subhead = "";// 副标题
	this.title = "信息标题";// 信息标题
  this.message = "信息内容";// 信息内容
  this.width = width?width:200;// 窗口宽度
  this.height = height?height:120;// 窗口高度
  this.timeout = 30;// 暂停时间
  this.right = screen.width;// 窗口位置-右边
  this.bottom = screen.height;// 窗口位置-底边
  this.left = left?left:(this.right - this.width);// 窗口位置-左边
  this.top = top?top:(this.bottom - this.height);// 窗口位置-上边
  this.timer = 0;// 定时器
  this.speed = 0;// 重复间隔时间
  this.autoHide = false;// 自动隐藏
  this.oncommand = null;// 链接操作
  this.popup = window.createPopup(); //IE5.5+
}

// 设置窗口大小
ShowMessage.PopUp.prototype.setSize = function(width, height){
	var w = parseInt(width);
	var h = parseInt(height);
	if (!isNaN(w)) {
		this.width = w;
	}
	if (!isNaN(h)) {
		this.height = h;
	}
}

// 设置窗口位置
ShowMessage.PopUp.prototype.setRect = function(left,top){ 
	var l = parseInt(left);
	var t = parseInt(top);
	
	if (!isNaN(l)) {
		this.left = l;
	}
	if (!isNaN(t)) {
		this.top = t;
	}
}

// 设置是否自动隐藏
ShowMessage.PopUp.prototype.setAutoHide = function(autoHide){ 
	this.autoHide = autoHide?autoHide:false;
}

// 设置重复间隔时间
ShowMessage.PopUp.prototype.setSpeed = function(speed){ 
	this.speed = speed?speed:0;
}

// 隐藏消息方法  
ShowMessage.PopUp.prototype.hide = function(){
	window.clearInterval(this.timer);
	this.popup.hide();
	this.timer = 0;
}

// 消息命令事件，要实现自己的连接，请重写它
ShowMessage.PopUp.prototype.setOncommand = function(vFunction){
	this.oncommand = vFunction;
} 

// 设置窗口内容
ShowMessage.PopUp.prototype.windowContent = function(caption, subhead, title, message){ 
	this.caption = caption?caption:"";
	this.subhead = subhead?subhead:"";
	this.title = title?title:"";
	this.message = message?message:"";
}
  
// 消息显示方法    
ShowMessage.PopUp.prototype.show = function(){
	var me = this;
	var popup = this.popup;
	var w = this.width;  
	var h = this.height;  
	var x = this.left; 
	var y = this.top;
	var speed = this.speed;
	var caption = this.caption;
	var subhead = this.subhead;
	var title = this.title;
	var message = this.message;
	var path = _showmessage_path;
	
	var str = "<table cellpadding='0' cellspacing='0' width='" + w + "' height='" + h + "'>"
	str += "<tr>"
	str += "<td>"
	str += "<table cellpadding='0' cellspacing='0' width='100%'>"
	str += " <tr>"
	str += "  <td width='34'><img src='" + path + "images/1_001.gif' width='34' height='20' /></td>"
	str += "  <td style='background: url(" + path + "images/1_002.gif); font-size: 12px;font: bold;color: #fff;padding-left:5px;padding-top:2px;'>"
	str += caption 
	str +=  "</td>"
	str += "  <td align='right' width='15' style='cursor: pointer;'><img id='btSysClose' src='" + path + "images/close.gif' width='15' height='20' /></td>"
	str += "  <td width='5'><img src='" + path + "images/1_003.gif' width='5' height='20' /></td>"
	str += " </tr>"
	str += "</table>"
	str += " </td>"
	str += " </tr>"
	str += " <tr>"
	str += " <td>"
	str += "  <table cellpadding='0' cellspacing='0' width='100%' height='" + (h - 20) + "'>"
	str += "   <tr>"
	str += "    <td style='width:5px;background:aqua url(" + path + "images/1_left.gif)'></td>"
	str += "    <td valign='top' align='left' style='padding:5px' bgcolor='#F1F9FF'>"
	str += "     <table cellpadding='0' cellspacing='0' width='100%'>"
	str += "      <tr>"
	str += "       <td style='font-size: 12px;color: #8D8D8D;'>" + subhead + "</td>"
	str += "      </tr>"
	str += "      <tr>"
	str += "       <td style='font-size: 12px;text-align: left; padding-top:5px;word-break: break-all; word-wrap:break-word;'>"
	str += "        <div style='width:260; height:110; overflow:auto;'>"
	str += "          <div style='font-size: 14px;font: bold;text-align: center;'>" + title + "</div>"
	str += "          <A href='javascript:void(0)' hidefocus=false id='btCommand' style='color: #1E90FF;' title='阅读全文'>" + message + "</A>"
	str += "        </div>"
	str += "       </td>"
	str += "      </tr>"
	str += "     </table>"
	str += "    </td>"
	str += "    <td style='width:5px;background: url(" + path + "images/1_right.gif)'></td>"
	str += " </tr>"
	str += "</table>"
	str += "</td>"
	str += "</tr>"
	str += "</table>"
	
	popup.document.body.innerHTML = str; 

	var fun = function(){
		popup.show(x,y,w,h);
	}  
	
	this.timer = window.setInterval(fun, speed); 
	
	var btClose = popup.document.getElementById("btSysClose");// 关闭按钮
	
	btClose.onclick = function(){
		me.hide();  
	}
	
	var btCommand = popup.document.getElementById("btCommand");// 链接事件
	btCommand.onclick = function(){
		if (me.oncommand) {
			try{eval(me.oncommand);} catch(E){}
		}
		window.focus();
		popup.show(x,y,w,h);
		me.hide();
	}   
	
	if (this.autoHide) {
		window.setTimeout(btClose.onclick, this.timeout * 1000);
	}
}