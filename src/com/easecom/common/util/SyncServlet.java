package com.easecom.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/*
 servlet 和Timer 结合起来 完成侦听定时执行任务，在初始化时加载调度器；
 在 web.xml文件中配置 servlet 类：
 <servlet>
 <servlet-name>taskServlet</servlet-name>
 <servlet-class>com.jngec.common.util.TaskServlet</servlet-class>
 <load-on-startup>0</load-on-startup>
 </servlet>
 这个servlet是执行后台操作，不需要相应外部请求，因此没有必要为它定义servlet-mapping。
 <load-on-startup>1</load-on-startup> 必须设置，容器自动初始化，加载任务；*/

public class SyncServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Timer timer = new Timer();
	Timer couponTime = new Timer();

	public void init() throws ServletException {
		int time = 5*60*1000;//执行间隔时间
		timer.schedule(new SyncTask(), new Date(), time);
		couponTime.schedule(new CouponTask(),new Date(),time);
		}

	public void destroy() {
		super.destroy();
		timer.cancel(); // 要主动销毁；否则关闭项目应用时，定时器没被销毁
		couponTime.cancel();
	}
}
