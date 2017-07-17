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

package com.easecom.common.framework.hibernate;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.easecom.common.util.WebDialogBox;

/**
 * @title: Hibernate预加载类
 * @description:
 * @author: wanghw
 * @version: 1.0
 * @create Date:2006-3-30
 */
@SuppressWarnings("serial")
public class ServPreloadHibernate extends HttpServlet{
    
	private static Logger logger = Logger.getLogger(ServPreloadHibernate.class);
  
	public void init(ServletConfig config) throws ServletException {
      super.init(config);
      try {
          logger.info("开始加载Hibernate！");
          logger.info("完成加载Hibernate！");
      } catch (Exception e) {
          logger.debug("加载失败Hibernate！"+e);
      }finally {
          try {
          	HibernateSessionFactory.closeSession();
          } catch (Exception e) {
              logger.debug(e);
          }
      }

    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        WebDialogBox dialog=new WebDialogBox(3,"信息提示","这是用于预启动的程序，请点击确认返回！","确认","javascript:history.back();");
        request.setAttribute("DialogBox",dialog);
        request.getRequestDispatcher("/util/dialog.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

}
