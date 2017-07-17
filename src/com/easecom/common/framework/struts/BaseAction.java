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

package com.easecom.common.framework.struts;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.easecom.common.util.DateUtils;
import com.easecom.common.util.SessionContainer;
import com.easecom.common.util.WebDialogBox;
import com.easecom.common.util.WebDialogUtil;

/**
 * @title: Action基类
 * @description:
 * @author: wanghw
 * @version: 1.0
 * @create Date:2006-3-30
 */
public class BaseAction extends DispatchAction {

	// 日志调试器
	private static Logger logger = Logger.getLogger(BaseAction.class);

	/**
	 * 将一个对象的属性拷贝到另外一个，目的是完成FormBean到PO的属性拷贝
	 * 
	 * @param dest
	 * @param orig
	 */
	protected void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (Exception ex) {
			logger.error("\nEpisMessage >>> Copy property error: " + ex.toString());
		}
	}

	/**
	 * Session验证
	 * 
	 * @param request
	 * @param objName
	 * @return
	 */
	public boolean validateSession(HttpServletRequest request, String objName) {

		HttpSession session = request.getSession();

		// 检查访问是否有效
		SessionContainer sessionContainer = (SessionContainer) session
				.getAttribute("SessionContainer");
		if (sessionContainer == null) {

			// 消息传递对象
			WebDialogBox dialogBox = WebDialogUtil.setError("当前访问无效，请重新登录系统！");
			request.setAttribute("DialogBox", dialogBox);

			// Session中没有用户信息返回true
			return false;
		}
		return false;
	}

	/**
	 * {"json-response":{"result":true,"msg":"abc"}}
	 * 
	 * @param response
	 * @param result
	 * @param message
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected void outputJsonResponse(HttpServletResponse response,
			boolean result, String message) throws IOException {
		JSONObject json = new JSONObject();
		json.put("result", result);
		json.put("msg", message);
		JSONObject doc = new JSONObject();
		doc.put("json-response", json);
		String content = doc.toString();
		this.flushResponse(response, content);
	}

	/**
	 * {"json-response":{"result":true,"msg":"abc","msg":{"node":"node1","id":"id1"}}}
	 * 
	 * @param response
	 * @param result
	 * @param message
	 * @param customMap
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected void outputJsonResponse(HttpServletResponse response,
			boolean result, String message, Map customMap) throws IOException {
		JSONObject json = new JSONObject();
		json.put("result", result);
		json.put("msg", message);
		if (customMap != null && customMap.size() > 0)
			json.put("custom-content", customMap);
		JSONObject doc = new JSONObject();
		doc.put("json-response", json);
		String content = doc.toString();
		this.flushResponse(response, content);
	}

	/**
	 * 输出ext列表的json数据
	 * 输出格式为：{totalProperty:12,root:[{"name":"name1","id":"id1"},{"name":"name2","id":"id2"}]}
	 * 
	 * @param response
	 * @param totalProperty
	 * @param list
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected void outputExtJsonResponse(HttpServletResponse response,
			int totalProperty, List list) throws IOException {
		JSONObject json = new JSONObject();
		json.put("totalProperty", totalProperty);
		if (list != null && list.size() > 0) {
			JSONArray jsonArray = JSONArray.fromObject(list);
			json.put("root", jsonArray);
		}
		String content = json.toString();
		this.flushResponse(response, content);
	}

	/**
	 * 输出json数据
	 * 输出格式为：{result:true,message:"aaa",data:[{"name":"name1","id":"id1"},{"name":"name2","id":"id2"}]}
	 * 
	 * @param response
	 * @param result
	 * @param message
	 * @param list
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected void outputJsonResponse(HttpServletResponse response,
			boolean result, String message, List list) throws IOException {
		JSONObject json = new JSONObject();
		json.put("result", result);
		json.put("msg", message);
		if (list != null && list.size() > 0) {
			JSONArray jsonArray = JSONArray.fromObject(list);
			json.put("data", jsonArray);
		}
		String content = json.toString();
		this.flushResponse(response, content);
	}

	/**
	 * Method to flush a String as response.
	 * 
	 * @param response
	 * @param responseContent
	 * @throws IOException
	 */
	protected void flushResponse(HttpServletResponse response,
			String responseContent) throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.write(responseContent);
		writer.flush();
		writer.close();
	}

	/**
	 * 多文件上传 return:List<UploadCmd>
	 * 
	 * @param files
	 * @param folderPath
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	protected List<UploadCmd> upload(Hashtable files, String folderPath)
			throws Exception {
		try {
			File file = new File(folderPath);

			// 判断目录是否存在
			if (!file.exists()) { // 不存在创建
				file.mkdirs();
			}
			List<UploadCmd> list = new ArrayList<UploadCmd>();
			FormFile[] formFile = null;// 得到所有的文件请求元素
			if (files != null && files.size() > 0) {
				formFile = new FormFile[files.size()];// 初始化FormFile
				Enumeration enums = files.keys();// 得到files的keys
				String fileKey = null;
				int i = 0;// 遍历枚举
				while (enums.hasMoreElements()) {
					fileKey = (String) (enums.nextElement());// 取得fileKey
					formFile[i] = (FormFile) files.get(fileKey);// 分别上传
					InputStream input = null;
					OutputStream fos = null;
					input = formFile[i].getInputStream();
					UploadCmd uploadCmd = new UploadCmd();
					String fileName = formFile[i].getFileName();
					if (!fileName.equalsIgnoreCase("")) {
						String extName = fileName.substring(fileName.lastIndexOf("."))
								.toLowerCase();
						String newFileName = fileName.substring(0, fileName
								.lastIndexOf("."))
								+ "_" + System.currentTimeMillis() + extName;
						uploadCmd.setTitle(fileName);
						uploadCmd.setFullPath(folderPath + newFileName);
						list.add(uploadCmd);
						fos = new FileOutputStream(folderPath + newFileName);
						int bytesRead = 0;
						byte[] buf = new byte[8129];
						while ((bytesRead = input.read(buf, 0, 8129)) != -1) {
							fos.write(buf, 0, bytesRead);
						} // 从第零个字节开始读，把读取的内容赋给buf1er,当返回-1时表示已经读到文件末尾了,每次读入8129个字节;
						fos.close();
						input.close();
						formFile[i].destroy();
						i++;
					}
				}
			}
			return list;
		} catch (Exception e) {
			log.error(e.toString());
			return new ArrayList();
		}
	}

	/**
	 * 下载文件
	 * 
	 * @param request
	 * @param response
	 * @param fileName
	 * @param filePath
	 * @throws Exception
	 */
	protected void download(HttpServletRequest request,
			HttpServletResponse response, String fileName, String filePath)
			throws Exception {
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
			log.error("出现IOException." + e);
			System.out.println("出现IOException." + e);
		} finally {
			if (inputStream != null)
				inputStream.close();
			if (outputStream != null)
				outputStream.close();
		}
		return;
	}
	
	/**
	 * 单个文件上传
	 * 
	 * @param ff
	 *          文件的名字
	 * @param folderPath
	 *          上传路径
	 * @return 包含上传路径和上传文件名字的对象UploadCmd
	 * @throws Exception
	 */
	protected UploadCmd upload(FormFile ff, String folderPath) throws Exception {
		UploadCmd uploadCmd = new UploadCmd();
		try {
			File file = new File(folderPath);
			// 判断目录是否存在
			if (!file.exists()) { // 不存在创建
				file.mkdirs();
			}
			InputStream io = null;
			OutputStream os = null;
			String filename = ff.getFileName();
			if (!filename.equalsIgnoreCase("")) {
				io = ff.getInputStream();
				String filenameExt = filename.substring(filename.lastIndexOf("."))
						.toLowerCase();
				
				//String newFilename = filename.substring(0, filename.lastIndexOf("."))
				String newFilename = DateUtils.getCurrDateStr()
						+ "_" + System.currentTimeMillis() + filenameExt;
				uploadCmd.setTitle(newFilename);
				uploadCmd.setFullPath(folderPath + newFilename);
				os = new FileOutputStream(folderPath + newFilename);
				int readByte = 0;
				byte buf[] = new byte[8129];
				while ((readByte = io.read(buf, 0, 8129)) != -1) {
					os.write(buf, 0, readByte);
				}
			}
			os.close();
			io.close();
			ff.destroy();
		} catch (Exception e) {
			logger.error(e.toString());

		}
		return uploadCmd;
	}
}
