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

import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

/**
 *文件下载工具类
 */
public class UploadServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			//创建DiskFileItemFactory对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置缓冲区大小，这里是4kb
			factory.setSizeThreshold(4096);
			////设置缓冲区目录
			factory.setRepository(new File("/tmp"));
			//通过该工厂对象创建ServletFileUpload对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			//maximum size before a FileUploadException will be thrown
			upload.setSizeMax(1000000);
			//将转化请求保存到list对象中
			List fileItems = upload.parseRequest(request);
			//循环list中的对象
			//Map fields=new HashMap();
			Iterator it = fileItems.iterator();
			while (it.hasNext()) {
				FileItem item = (FileItem) it.next();
				if (!item.isFormField()) {//不是表单域
					//fields.put(item.getFieldName(),item);
					//String fieldName = item.getFieldName();
					//String fileName = item.getName();
					//String contentType = item.getContentType();
					//boolean isInMemory = item.isInMemory();
					//long sizeInBytes = item.getSize();
					processUploadedFile(item);//调用processUploadedFile方法,将数据保存到文件中
					item.delete();//内存中删除该数据流					 
				} else {//是表单域
					String name = item.getFieldName();
					String value = item.getString();
					//fields.put(name, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processUploadedFile(FileItem item) {
		//获得上传文件的文件名:上传的文件名+当前的时间
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String temp= "("+df.format(new Date())+")";
		String fileName = item.getName().substring(item.getName().lastIndexOf("\\") + 1)+temp;
		//创建File对象,将上传得文件保存到...文件夹下
		//String currentPath=request.getContextPath()+currentPath;
		File file = new File("../uploads/", fileName);
		InputStream in;
		try {
			in = item.getInputStream();//获得输入数据流文件
			//将该数据流写入到指定文件中
			FileOutputStream out = new FileOutputStream(file);
			byte[] buffer = new byte[4096]; // To hold file contents
			int bytes_read;
			while ((bytes_read = in.read(buffer)) != -1) // Read until EOF
			{
				out.write(buffer, 0, bytes_read);
			}
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					;
				}
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					;
				}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doPost(request, response);
	}
}
