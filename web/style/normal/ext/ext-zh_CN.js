Ext.BLANK_IMAGE_URL = 'images/default/s.gif';
Ext.UpdateManager.defaults.indicatorText="<div class=\"loading-indicator\">加载中...</div>";
if(Ext.View)
{
	Ext.View.prototype.emptyText=""
}if(Ext.grid.Grid)
{
	Ext.grid.Grid.prototype.ddText="{0}行被选中"
}if(Ext.TabPanelItem)
{
	Ext.TabPanelItem.prototype.closeText="关闭该Tab页"
}if(Ext.form.Field)
{
	Ext.form.Field.prototype.invalidText="输入值不合法"
}
Date.monthNames=["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];
Date.dayNames=["日","一","二","三","四","五","六"];
Ext.DatePicker=Ext.extend(Ext.Component,{todayText:"今天",okText:"确定",cancelText:"取消",todayTip:"{0} (Spacebar)",minDate:null,maxDate:null,minText:"日期在最小日期之前",maxText:"日期在最大日期之后",format:"Y-m-d",disabledDays:null,disabledDaysText:"",disabledDatesRE:null,disabledDatesText:"",constrainToViewport:true,monthNames:Date.monthNames,dayNames:Date.dayNames,nextText:"下月 (Control+Right)",prevText:"上月 (Control+Left)",monthYearText:"选择一个月 (Control+Up/Down 来改变年)",startDay:0,initComponent:function(){Ext.DatePicker.superclass.initComponent.call(this);this.value=this.value?this.value.clearTime():new Date().clearTime();this.addEvents("select");if(this.handler){this.on("select",this.handler,this.scope||this)}this.initDisabledDays()},initDisabledDays:function(){if(!this.disabledDatesRE&&this.disabledDates){var A=this.disabledDates;var C="(?:";for(var B=0;B<A.length;B++){C+=A[B];if(B!=A.length-1){C+="|"}}this.disabledDatesRE=new RegExp(C+")")}},setValue:function(B){var A=this.value;this.value=B.clearTime(true);if(this.el){this.update(this.value)}},getValue:function(){return this.value},focus:function(){if(this.el){this.update(this.activeDate)}},onRender:function(A,F){var C=["<table cellspacing=\"0\">","<tr><td class=\"x-date-left\"><a href=\"#\" title=\"",this.prevText,"\">&#160;</a></td><td class=\"x-date-middle\" align=\"center\"></td><td class=\"x-date-right\"><a href=\"#\" title=\"",this.nextText,"\">&#160;</a></td></tr>","<tr><td colspan=\"3\"><table class=\"x-date-inner\" cellspacing=\"0\"><thead><tr>"];var E=this.dayNames;for(var D=0;D<7;D++){var G=this.startDay+D;if(G>6){G=G-7}C.push("<th><span>",E[G].substr(0,1),"</span></th>")}C[C.length]="</tr></thead><tbody><tr>";for(var D=0;D<42;D++){if(D%7==0&&D!=0){C[C.length]="</tr><tr>"}C[C.length]="<td><a href=\"#\" hidefocus=\"on\" class=\"x-date-date\" tabIndex=\"1\"><em><span></span></em></a></td>"}C[C.length]="</tr></tbody></table></td></tr><tr><td colspan=\"3\" class=\"x-date-bottom\" align=\"center\"></td></tr></table><div class=\"x-date-mp\"></div>";var B=document.createElement("div");B.className="x-date-picker";B.innerHTML=C.join("");A.dom.insertBefore(B,F);this.el=Ext.get(B);this.eventEl=Ext.get(B.firstChild);new Ext.util.ClickRepeater(this.el.child("td.x-date-left a"),{handler:this.showPrevMonth,scope:this,preventDefault:true,stopDefault:true});new Ext.util.ClickRepeater(this.el.child("td.x-date-right a"),{handler:this.showNextMonth,scope:this,preventDefault:true,stopDefault:true});this.eventEl.on("mousewheel",this.handleMouseWheel,this);this.monthPicker=this.el.down("div.x-date-mp");this.monthPicker.enableDisplayMode("block");var I=new Ext.KeyNav(this.eventEl,{"left":function(J){J.ctrlKey?this.showPrevMonth():this.update(this.activeDate.add("d",-1))},"right":function(J){J.ctrlKey?this.showNextMonth():this.update(this.activeDate.add("d",1))},"up":function(J){J.ctrlKey?this.showNextYear():this.update(this.activeDate.add("d",-7))},"down":function(J){J.ctrlKey?this.showPrevYear():this.update(this.activeDate.add("d",7))},"pageUp":function(J){this.showNextMonth()},"pageDown":function(J){this.showPrevMonth()},"enter":function(J){J.stopPropagation();return true},scope:this});this.eventEl.on("click",this.handleDateClick,this,{delegate:"a.x-date-date"});this.eventEl.addKeyListener(Ext.EventObject.SPACE,this.selectToday,this);this.el.unselectable();this.cells=this.el.select("table.x-date-inner tbody td");this.textNodes=this.el.query("table.x-date-inner tbody span");this.mbtn=new Ext.Button({text:"&#160;",tooltip:this.monthYearText,renderTo:this.el.child("td.x-date-middle",true)});this.mbtn.on("click",this.showMonthPicker,this);this.mbtn.el.child(this.mbtn.menuClassTarget).addClass("x-btn-with-menu");var H=(new Date()).dateFormat(this.format);this.todayBtn=new Ext.Button({renderTo:this.el.child("td.x-date-bottom",true),text:String.format(this.todayText,H),tooltip:String.format(this.todayTip,H),handler:this.selectToday,scope:this});if(Ext.isIE){this.el.repaint()}this.update(this.value)},createMonthPicker:function(){if(!this.monthPicker.dom.firstChild){var A=["<table border=\"0\" cellspacing=\"0\">"];for(var B=0;B<6;B++){A.push("<tr><td class=\"x-date-mp-month\"><a href=\"#\">",this.monthNames[B].substr(0,3),"</a></td>","<td class=\"x-date-mp-month x-date-mp-sep\"><a href=\"#\">",this.monthNames[B+6].substr(0,3),"</a></td>",B==0?"<td class=\"x-date-mp-ybtn\" align=\"center\"><a class=\"x-date-mp-prev\"></a></td><td class=\"x-date-mp-ybtn\" align=\"center\"><a class=\"x-date-mp-next\"></a></td></tr>":"<td class=\"x-date-mp-year\"><a href=\"#\"></a></td><td class=\"x-date-mp-year\"><a href=\"#\"></a></td></tr>")}A.push("<tr class=\"x-date-mp-btns\"><td colspan=\"4\"><button type=\"button\" class=\"x-date-mp-ok\">",this.okText,"</button><button type=\"button\" class=\"x-date-mp-cancel\">",this.cancelText,"</button></td></tr>","</table>");this.monthPicker.update(A.join(""));this.monthPicker.on("click",this.onMonthClick,this);this.monthPicker.on("dblclick",this.onMonthDblClick,this);this.mpMonths=this.monthPicker.select("td.x-date-mp-month");this.mpYears=this.monthPicker.select("td.x-date-mp-year");this.mpMonths.each(function(C,D,E){E+=1;if((E%2)==0){C.dom.xmonth=5+Math.round(E*0.5)}else{C.dom.xmonth=Math.round((E-1)*0.5)}})}},showMonthPicker:function(){this.createMonthPicker();var A=this.el.getSize();this.monthPicker.setSize(A);this.monthPicker.child("table").setSize(A);this.mpSelMonth=(this.activeDate||this.value).getMonth();this.updateMPMonth(this.mpSelMonth);this.mpSelYear=(this.activeDate||this.value).getFullYear();this.updateMPYear(this.mpSelYear);this.monthPicker.slideIn("t",{duration:0.2})},updateMPYear:function(E){this.mpyear=E;var C=this.mpYears.elements;for(var B=1;B<=10;B++){var D=C[B-1],A;if((B%2)==0){A=E+Math.round(B*0.5);D.firstChild.innerHTML=A;D.xyear=A}else{A=E-(5-Math.round(B*0.5));D.firstChild.innerHTML=A;D.xyear=A}this.mpYears.item(B-1)[A==this.mpSelYear?"addClass":"removeClass"]("x-date-mp-sel")}},updateMPMonth:function(A){this.mpMonths.each(function(B,C,D){B[B.dom.xmonth==A?"addClass":"removeClass"]("x-date-mp-sel")})},selectMPMonth:function(A){},onMonthClick:function(D,B){D.stopEvent();var C=new Ext.Element(B),A;if(C.is("button.x-date-mp-cancel")){this.hideMonthPicker()}else{if(C.is("button.x-date-mp-ok")){this.update(new Date(this.mpSelYear,this.mpSelMonth,(this.activeDate||this.value).getDate()));this.hideMonthPicker()}else{if(A=C.up("td.x-date-mp-month",2)){this.mpMonths.removeClass("x-date-mp-sel");A.addClass("x-date-mp-sel");this.mpSelMonth=A.dom.xmonth}else{if(A=C.up("td.x-date-mp-year",2)){this.mpYears.removeClass("x-date-mp-sel");A.addClass("x-date-mp-sel");this.mpSelYear=A.dom.xyear}else{if(C.is("a.x-date-mp-prev")){this.updateMPYear(this.mpyear-10)}else{if(C.is("a.x-date-mp-next")){this.updateMPYear(this.mpyear+10)}}}}}}},onMonthDblClick:function(D,B){D.stopEvent();var C=new Ext.Element(B),A;if(A=C.up("td.x-date-mp-month",2)){this.update(new Date(this.mpSelYear,A.dom.xmonth,(this.activeDate||this.value).getDate()));this.hideMonthPicker()}else{if(A=C.up("td.x-date-mp-year",2)){this.update(new Date(A.dom.xyear,this.mpSelMonth,(this.activeDate||this.value).getDate()));this.hideMonthPicker()}}},hideMonthPicker:function(A){if(this.monthPicker){if(A===true){this.monthPicker.hide()}else{this.monthPicker.slideOut("t",{duration:0.2})}}},showPrevMonth:function(A){this.update(this.activeDate.add("mo",-1))},showNextMonth:function(A){this.update(this.activeDate.add("mo",1))},showPrevYear:function(){this.update(this.activeDate.add("y",-1))},showNextYear:function(){this.update(this.activeDate.add("y",1))},handleMouseWheel:function(A){var B=A.getWheelDelta();if(B>0){this.showPrevMonth();A.stopEvent()}else{if(B<0){this.showNextMonth();A.stopEvent()}}},handleDateClick:function(B,A){B.stopEvent();if(A.dateValue&&!Ext.fly(A.parentNode).hasClass("x-date-disabled")){this.setValue(new Date(A.dateValue));this.fireEvent("select",this,this.value)}},selectToday:function(){this.setValue(new Date().clearTime());this.fireEvent("select",this,this.value)},update:function(W){var A=this.activeDate;this.activeDate=W;if(A&&this.el){var I=W.getTime();if(A.getMonth()==W.getMonth()&&A.getFullYear()==W.getFullYear()){this.cells.removeClass("x-date-selected");this.cells.each(function(a){if(a.dom.firstChild.dateValue==I){a.addClass("x-date-selected");setTimeout(function(){try{a.dom.firstChild.focus()}catch(b){}},50);return false}});return }}var F=W.getDaysInMonth();var J=W.getFirstDateOfMonth();var C=J.getDay()-this.startDay;if(C<=this.startDay){C+=7}var S=W.add("mo",-1);var D=S.getDaysInMonth()-C;var B=this.cells.elements;var K=this.textNodes;F+=C;var P=86400000;var U=(new Date(S.getFullYear(),S.getMonth(),D)).clearTime();var T=new Date().clearTime().getTime();var N=W.clearTime().getTime();var M=this.minDate?this.minDate.clearTime():Number.NEGATIVE_INFINITY;var Q=this.maxDate?this.maxDate.clearTime():Number.POSITIVE_INFINITY;var X=this.disabledDatesRE;var L=this.disabledDatesText;var Z=this.disabledDays?this.disabledDays.join(""):false;var V=this.disabledDaysText;var R=this.format;var G=function(d,a){a.title="";var b=U.getTime();a.firstChild.dateValue=b;if(b==T){a.className+=" x-date-today";a.title=d.todayText}if(b==N){a.className+=" x-date-selected";setTimeout(function(){try{a.firstChild.focus()}catch(f){}},50)}if(b<M){a.className=" x-date-disabled";a.title=d.minText;return }if(b>Q){a.className=" x-date-disabled";a.title=d.maxText;return }if(Z){if(Z.indexOf(U.getDay())!=-1){a.title=V;a.className=" x-date-disabled"}}if(X&&R){var c=U.dateFormat(R);if(X.test(c)){a.title=L.replace("%0",c);a.className=" x-date-disabled"}}};var O=0;for(;O<C;O++){K[O].innerHTML=(++D);U.setDate(U.getDate()+1);B[O].className="x-date-prevday";G(this,B[O])}for(;O<F;O++){intDay=O-C+1;K[O].innerHTML=(intDay);U.setDate(U.getDate()+1);B[O].className="x-date-active";G(this,B[O])}var Y=0;for(;O<42;O++){K[O].innerHTML=(++Y);U.setDate(U.getDate()+1);B[O].className="x-date-nextday";G(this,B[O])}this.mbtn.setText(this.monthNames[W.getMonth()]+" "+W.getFullYear());if(!this.internalRender){var E=this.el.dom.firstChild;var H=E.offsetWidth;this.el.setWidth(H+this.el.getBorderWidth("lr"));Ext.fly(E).setWidth(H);this.internalRender=true;if(Ext.isOpera&&!this.secondPass){E.rows[0].cells[1].style.width=(H-(E.rows[0].cells[0].offsetWidth+E.rows[0].cells[2].offsetWidth))+"px";this.secondPass=true;this.update.defer(10,this,[W])}}},beforeDestroy:function(){this.mbtn.destroy();this.todayBtn.destroy()}});Ext.reg("datepicker",Ext.DatePicker);
if(Ext.MessageBox)
{
	Ext.MessageBox.buttonText=
	{
		ok:"确定",cancel:"取消",yes:"是",no:"否"
	}
}if(Ext.util.Format)
{
	Ext.util.Format.date=function (A,B)
	{
		if(!A)
		{
			return ""
		}if(!(A instanceof Date))
		{
			A=new Date(Date.parse(A))
		}return A.dateFormat(B||"y年m月d日")
	}
}if(Ext.DatePicker)
{
	Ext.apply(Ext.DatePicker.prototype,
	{
		todayText:"今天",minText:"日期在最小日期之前",maxText:"日期在最大日期之后",disabledDaysText:"",disabledDatesText:"",monthNames:Date.monthNames,dayNames:Date.dayNames,nextText:"下月 (Control+Right)",prevText:"上月 (Control+Left)",monthYearText:"选择一个月 (Control+Up/Down 修改年份)",todayTip:"{0} (空格键选择)",format:"y年m月d日"
	})
}if(Ext.PagingToolbar)
{
	Ext.apply(Ext.PagingToolbar.prototype,
	{
		beforePageText:"第",afterPageText:"页 共 {0} 页",firstText:"第一页",prevText:"上一页",nextText:"下一页",lastText:"最后页",refreshText:"刷新",displayMsg:"显示 {0} - {1}，共 {2} 条",emptyMsg:"没有可显示的数据"
	})
}if(Ext.form.TextField)
{
	Ext.apply(Ext.form.TextField.prototype,
	{
		minLengthText:"该输入项的最小长度是 {0}",maxLengthText:"该输入项的最大长度是 {0}",blankText:"该输入项为必输项",regexText:"",emptyText:null
	})
}if(Ext.form.NumberField)
{
	Ext.apply(Ext.form.NumberField.prototype,
	{
		minText:"该输入项的最小值是 {0}",maxText:"该输入项的最大值是 {0}",nanText:"{0} 不是有效数值"
	})
}if(Ext.form.DateField)
{
	Ext.apply(Ext.form.DateField.prototype,
	{
		disabledDaysText:"禁用",disabledDatesText:"禁用",minText:"该输入项的日期必须在 {0} 之后",maxText:"该输入项的日期必须在 {0} 之前",invalidText:"{0} 是无效的日期 - 必须符合格式： {1}",format:"y年m月d日"
	})
}if(Ext.form.ComboBox)
{
	Ext.apply(Ext.form.ComboBox.prototype,
	{
		loadingText:"加载中...",valueNotFoundText:undefined
	})
}if(Ext.form.VTypes)
{
	Ext.apply(Ext.form.VTypes,
	{
		emailText:"该输入项必须是电子邮件地址，格式如： \"user@domain.com\"",urlText:"该输入项必须是URL地址，格式如： \"http:/"+"/www.domain.com\"",alphaText:"该输入项只能包含字符和_",alphanumText:"该输入项只能包含字符,数字和_"
	})
}if(Ext.grid.GridView)
{
	Ext.apply(Ext.grid.GridView.prototype,
	{
		sortAscText:"升序",sortDescText:"降序",lockText:"锁定列",unlockText:"解锁列",columnsText:"列隐藏/显示"
	})
}if(Ext.grid.PropertyColumnModel)
{
	Ext.apply(Ext.grid.PropertyColumnModel.prototype,
	{
		nameText:"名称",valueText:"值",dateFormat:"y年m月d日"
	})
}if(Ext.layout.BorderLayout.SplitRegion)
{
	Ext.apply(Ext.layout.BorderLayout.SplitRegion.prototype,
	{
		splitTip:"拖动可以改变尺寸.",collapsibleSplitTip:"拖动可以改变尺寸. 双击隐藏."
	})
};
