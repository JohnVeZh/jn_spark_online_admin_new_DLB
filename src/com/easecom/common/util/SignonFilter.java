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

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.File;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpSession;

public class SignonFilter implements Filter {
	protected FilterConfig filterConfig;

	public void doFilter(final ServletRequest req, final ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hres = (HttpServletResponse) res;
		HttpSession session = hreq.getSession(true);
    	try {
			if (session != null
					&& session.getAttribute("SessionContainer") != null) {
				chain.doFilter(req, res);
			} else {
				System.out.println("验证失败!");
//				hres.sendRedirect("index.jsp");
				hreq.getRequestDispatcher("/relogin.jsp").forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setFilterConfig(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
	}
}