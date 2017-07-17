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

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {
	protected FilterConfig filterConfig;

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		if(null==request.getSession() || null==request.getSession().getAttribute("SessionContainer")  ){
			String act = request.getParameter("act");
			if(!"download".equals(act)){
				request.getRequestDispatcher("/relogin.jsp").forward(request, response);
				return;
			}
		}
		chain.doFilter(request, response);
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