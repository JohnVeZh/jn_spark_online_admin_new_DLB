package com.easecom.common.filter;

import com.easecom.common.util.SessionUtils;
import com.easecom.common.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by liubaibing on 2016/11/3.
 */
public class PagerParamsFilter implements Filter {
    public static ThreadLocal<Integer> pagerFilterLocal = new ThreadLocal<Integer>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String rowCount = (String) SessionUtils
                .getAttribute(request, "RowCountPerPage");
//        String rowCount = request.getParameter("RowCountPerPage");
        if(StringUtils.isNotBlank(rowCount)){
            pagerFilterLocal.set(Integer.parseInt(rowCount));
        }
        filterChain.doFilter(servletRequest,servletResponse);
        pagerFilterLocal.remove();
    }

    @Override
    public void destroy() {

    }
}
