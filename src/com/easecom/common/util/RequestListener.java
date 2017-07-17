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

import javax.servlet.*;
import java.io.PrintWriter; // import java.io.FileOutputStream;
// import java.io.BufferedWriter;
// import java.io.FileWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 请求监听类
 * 
 * @author wwk
 * 
 */
public class RequestListener implements ServletRequestListener,
		ServletRequestAttributeListener {
	ServletContext context;
	public void requestInitialized(ServletRequestEvent sre)// ServletRequestListener
	{
		ServletRequest sr = sre.getServletRequest();
		HttpServletRequest hsr = (HttpServletRequest) sr;
		HttpSession session = hsr.getSession();
		this.context = sre.getServletContext();

		try {
			hsr.setCharacterEncoding("UTF-8");
		} catch (java.io.UnsupportedEncodingException e) {
			System.out.println("    不支持的编码异常 " + e);
		}

		String username = session.getAttribute("SessionContainer") == null ? (hsr
				.getParameter("ID") == null ? "" : hsr.getParameter("ID"))
				: ((SessionContainer) session.getAttribute("SessionContainer"))
						.getUserName();
		logout("\n" + DateUtils.getCurrDateTimeStr() + " " + sr.getRemoteAddr()
				+ " " + username + " " + hsr.getRequestURI() + " ...");// URI用于指定具体的请求资源,是URL中（协议、主机名、端口）之后的部分.
		
 
		/*
		 * if( sr.getRemoteAddr().startsWith("127") )
		 * sr.setAttribute("isLogin",new Boolean(true)); else
		 * sr.setAttribute("isLogin",new Boolean(false));
		 */
	}

	public void requestDestroyed(ServletRequestEvent sre)// ServletRequestListener
	{
		ServletRequest sr = sre.getServletRequest();
		HttpServletRequest hsr = (HttpServletRequest) sr;
		HttpSession session;
		String username;
		try {
			hsr.setCharacterEncoding("UTF-8");
		} catch (java.io.UnsupportedEncodingException e) {
			System.out.println("    不支持的编码异常 " + e);
		}
		try {
			session = hsr.getSession();
			username = session.getAttribute("SessionContainer") == null ? ""
					: ((SessionContainer) session
							.getAttribute("SessionContainer")).getUserName();
		} catch (IllegalStateException e) {
			username = "";
		}

		logout(DateUtils.getCurrDateTimeStr() + " " + sr.getRemoteAddr() + " "
				+ username + " " + hsr.getRequestURI() + " Over");
	}

	public void attributeAdded(ServletRequestAttributeEvent event)// ServletRequestAttributeListener
	{
		// logout(" attributeAdded('" + event.getName() + "':'"
		// +event.getValue() + "')");
	}

	public void attributeRemoved(ServletRequestAttributeEvent event)// ServletRequestAttributeListener
	{
		// logout(" attributeRemoved('" + event.getName() + "':'"
		// +event.getValue() + "')");
	}

	public void attributeReplaced(ServletRequestAttributeEvent event)// ServletRequestAttributeListener
	{
		// logout(" attributeReplaced('" + event.getName() + "':'"
		// +event.getValue() + "')");
	}

	private void logout(String msg) {
		PrintWriter out = null;
		try {
			System.out.println(msg);
			// String strAbsPath=context.getRealPath("/");
			// out=new PrintWriter(new
			// FileOutputStream(strAbsPath+"\\logs\\request"+DateTime.todayToString()+".txt",true));
			// out.println(msg);
			// out.close();
		} catch (Exception e) {
			out.close();
		}
	}
}