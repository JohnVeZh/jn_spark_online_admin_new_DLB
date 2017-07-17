package com.easecom.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 下载文件的工具类
 * 
 */
public class FileDownloadUtil
{
	/**
	 * 下载文件方法
	 * @param request
	 * @param response
	 * @param fileName
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static void downloadFile(HttpServletRequest request,
			HttpServletResponse response, String fileName, String filePath)
			throws Exception {

		String path = request.getContextPath();
		filePath=path + filePath;
		// 判断文件是否存在
		File file = new File(filePath);
		if (!file.exists()) { // 不存在，返回false
			throw new Exception("没找到该文件，该文件可能已被删除");
		}
		String displayFileName = URLEncoder.encode(fileName, "UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment; filename="
				+ displayFileName);// 不是显示是下载
		BufferedInputStream inputStream = null;
		BufferedOutputStream outputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(filePath));
			outputStream = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while ((bytesRead = inputStream.read(buff, 0, buff.length)) != -1) {
				outputStream.write(buff, 0, bytesRead);
			}
			outputStream.flush();
		} catch (final IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}  finally {
			if (inputStream != null)
				inputStream.close();
			if (outputStream != null)
				outputStream.close();
		}
	}
}

