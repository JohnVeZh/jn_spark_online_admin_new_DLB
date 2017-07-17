/**
 * @(#)$CurrentFile
 * 版权声明 山东益信通科贸有限公司，版权所有 违者必究
 *
 *<br> Copyright：Copyright (c) 2010
 *<br> Company：山东益信通科贸有限公司
 *<br> @author XXXXX（XXXXX）
 *<br> 2010-05-01
 *<br> @version 1.0
 */

package com.easecom.common.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingListener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionBindingEvent;

import java.io.PrintWriter;

// import java.io.FileOutputStream;
// import java.util.Date;

public final class SessionListener implements HttpSessionActivationListener,
		HttpSessionBindingListener, HttpSessionAttributeListener,
		HttpSessionListener, ServletContextListener {

	ServletContext context;
	int users = 0;// 当前用户数量

	public void contextInitialized(ServletContextEvent sce)// ServletContextListener
	{
		this.context = sce.getServletContext();
		logout("contextInitialized()-->ServletContext初始化了");
	}

	public void contextDestroyed(ServletContextEvent sce)// ServletContextListener
	{
		logout("contextDestroyed()-->ServletContext被销毁");
		this.context = null;
	}

	public void sessionCreated(HttpSessionEvent event)// HttpSessionListener
	{
		users++;
		logout("sessionCreated('" + event.getSession().getId() + "'),目前有"
				+ users + "个用户");
		context.setAttribute("users", new Integer(users));
	}

	public void sessionDestroyed(HttpSessionEvent event)// HttpSessionListener
	{
		users--;
		logout("sessionDestroyed('" + event.getSession().getId() + "'),目前有"
				+ users + "个用户");
		context.setAttribute("users", new Integer(users));
	}

	public void attributeAdded(HttpSessionBindingEvent event)// HttpSessionAttributeListener
	{
		logout("attributeAdded('" + event.getSession().getId() + "'; '"
				+ event.getName() + "':'" + event.getValue() + "')");
	}

	public void attributeRemoved(HttpSessionBindingEvent event)// HttpSessionAttributeListener
	{
		logout("attributeRemoved('" + event.getSession().getId() + "'; '"
				+ event.getName() + "':'" + event.getValue() + "')");
	}

	public void attributeReplaced(HttpSessionBindingEvent se)// HttpSessionAttributeListener
	{
		logout("attributeReplaced('" + se.getSession().getId() + ";'"
				+ se.getName() + "':'" + se.getValue() + "')");
	}

	public void sessionDidActivate(HttpSessionEvent se)// HttpSessionActivationListener
	{
		logout("sessionDidActivate(" + se.getSession().getId() + ")");
	}

	public void sessionWillPassivate(HttpSessionEvent se)// HttpSessionActivationListener
	{
		logout("sessionWillPassivate(" + se.getSession().getId() + ")");
	}

	public void valueBound(HttpSessionBindingEvent event)// HttpSessionBindingListener
	{
		logout("valueBound(" + event.getSession().getId() + event.getValue()
				+ ")");
	}

	public void valueUnbound(HttpSessionBindingEvent event)// HttpSessionBindingListener
	{
		logout("valueUnbound(" + event.getSession().getId() + event.getValue()
				+ ")");
	}

	private void logout(String message)// 日志输出
	{
		PrintWriter out = null;
		try {
			System.out.println(message);
			// String strAbsPath=context.getRealPath("/");
			// out=new PrintWriter( new
			// FileOutputStream(strAbsPath+"\\logs\\session"+DateTime.todayToString()+".txt",true)
			// );
			// out.println(new Date().toLocaleString()+"::From SessionListener:
			// " + message);
			// out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}