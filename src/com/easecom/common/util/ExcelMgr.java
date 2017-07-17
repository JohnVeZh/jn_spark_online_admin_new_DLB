/**
 * SbluMgr.java   Mar 1, 2012 9:37:20 AM
 * Copyright:  Copyright (c) 2011
 * Company:山东益信通科贸有限公司
 */

package com.easecom.common.util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;

import com.easecom.common.framework.hibernate.HibernateSessionFactory;

/**
 * 
 * @Description 商标录入业务处理类
 *  
 * @author 窦恩虎
 * @date Mar 1, 2012 9:37:20 AM
 */

public class ExcelMgr {

	/**
	 * 
	 * @Description: EXCEL 导入功能
	 * @param request
	 * @param response
	 * @param propertie
	 *            配置文件地址
	 * @return 当request请求中不存在EXCEL文件或者读取EXCEL出错时返回false 操作成功返回true
	 * @author: 窦恩虎
	 * @date Mar 2, 2012 11:13:03 AM 
	 */
	public boolean excelUpload(HttpServletRequest request,
			HttpServletResponse response, String propertie) {
		SessionContainer sessionContainer = (SessionContainer)SessionUtils.getAttribute(request, "SessionContainer");
		
		boolean flag = false;
		Session ses = HibernateSessionFactory.openSession();
		Connection conn = ses.connection();
		
		try {  
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			Map<String, String> map = new HashMap<String, String>();
			if (isMultipart == true) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> fileItems = upload.parseRequest(request);
				Iterator<FileItem> iter = fileItems.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (!item.isFormField()) {
						String fileName = item.getName();
						if (!"".equals(fileName)) {
							String name_xls = fileName.substring(fileName
									.lastIndexOf("."));
							if (".xls".equals(name_xls)
									|| ".xlsx".equals(name_xls)) {
								if (StringUtils.isNotEmpty(fileName)) {
									InputStream in = item.getInputStream();
										ExcelImport excelImport = new ExcelImport(
												in, conn, response, propertie);
										excelImport.readExcel();
										flag = true;
								}
							}
						}
					}
				}
			}
			conn.close();
		} catch (Exception e) {

			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
}
