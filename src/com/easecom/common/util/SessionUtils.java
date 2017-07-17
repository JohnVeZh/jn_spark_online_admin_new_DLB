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

/**
 * session 工具类
 */

package com.easecom.common.util;

import javax.servlet.http.*;

public class SessionUtils {

	public SessionUtils() {
	}

	public static Object getAttribute(HttpServletRequest req, String key) {
		HttpSession httpSession = req.getSession(false);
		Object rtn = null;// httpSession.getAttribute(key);
		if ("RowCountPerPage".equalsIgnoreCase(key)) {
			rtn = "18";
		} else if ("CurrentUserRealname".equalsIgnoreCase(key)) {
			rtn = "";
		} else {
			rtn = httpSession.getAttribute(key);
		}
		return rtn;
	}

	public static void setAttribute(HttpServletRequest req, String key,
			Object obj) {
		HttpSession httpSession = req.getSession(true);
		httpSession.setAttribute(key, obj);
	}
	/**首页通知中使用
	 * @author sunlei
	 * @param req
	 * @param key
	 * @param pageName：分页中一页显示的条数
	 * @return
	 */
	public static Object getAttribute(HttpServletRequest req, String key,
			String pageName) {
		HttpSession httpSession = req.getSession(false);
		Object rtn = null;// httpSession.getAttribute(key);
		if ("RowCountPerPage".equalsIgnoreCase(key)) {
			rtn = pageName;
		} else if ("CurrentUserRealname".equalsIgnoreCase(key)) {
			rtn = "";
		} else {
			rtn = httpSession.getAttribute(key);
		}
		return rtn;
	}

}
