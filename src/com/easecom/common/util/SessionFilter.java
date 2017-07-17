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

public class SessionFilter implements Filter {
	protected FilterConfig filterConfig;
	String nofilter;
    String nofilterFiles[];
    String sendRedirect;

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		String path=request.getServletPath();
		
		//System.out.println("SessionFilter ----------------->path:"+path+", nofilterPath:"+Arrays.asList(nofilterFiles));
		if(isInArray(path,nofilterFiles)){
			 chain.doFilter(request, response);
			// System.out.println("SessionFilter ----------------->request go on,not to redirect!");
		}else{
			if(null==request.getSession() || null==request.getSession().getAttribute("SessionContainer")  ){
				//System.out.println("SessionFilter ----------------->session is null,request go to redirect!");
				String basepath = request.getContextPath();
				response.sendRedirect(basepath+sendRedirect);
				return;
			}else{
				chain.doFilter(request, response);
				//System.out.println("SessionFilter ----------------->session is not null,request go on!");
			}
		}
	}

	public void setFilterConfig(final FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void init(FilterConfig config) throws ServletException {
		   nofilter=config.getInitParameter("nofilter");
           nofilterFiles=nofilter.split(",");
           sendRedirect=config.getInitParameter("sendRedirect");
           //System.out.println(Arrays.asList(nofilterFiles));
	}
	private static boolean isInArray(String path,String nofilterFiles[]) {
      for (int i = 0; i < nofilterFiles.length; i++) {
              String nofilterFile=nofilterFiles[i];
              if(nofilterFile.equals(path)){
                  return true;
              }
      }
      return false;
  }
}