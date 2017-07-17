package com.easecom.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.upload.FormFile;

import com.easecom.system.exception.SystemException;

/**
 * 上传文件的工具类
 * 
 */
public class FileUploadUtil
{
    /**
     * 相对于本地服务器的文件存放根目录
     */
    public static final String UPLOAD_FILE_ROOT = "uploadFile";
    
    /**
     * 上传的文件类型
     */
    public static final String UPLOAD_FILE_TYPE = "";
    
    /**
     * 上传的大小限制
     * 单位字节
     */
    public static final long UPLOAD_FILE_MAX_SIZE = 500 * 1024 *1024; 
    
    /**
     * 取得上传文件的保存路径，相对于<FILE_SERVER_URL >/<UPLOAD_FILE_ROOT>而言
     * 
     * @return
     */
    public static String getSaveRelativePath(String saveFileName){
        
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        
        return year + "/" + month + "/" + saveFileName;
    }
    
    /**
     * 取得文件上传的文件放置的根路径
     * 
     * @param request
     * @return
     */
    public static String getFileUploadRootPath(HttpServletRequest request){
        return WebUtil.getWebRoot(request) + UPLOAD_FILE_ROOT + "/";
    }
    
    /**
     * 根据给定的相对路径来取得该文件在硬盘上的真实路径
     * 
     * @param relativePath 相对于<FILE_SERVER_URL >/<UPLOAD_FILE_ROOT>而言的路径
     * @param request
     * @return
     */
    public static String getDiskPath(String relativePath, HttpServletRequest request){
        return WebUtil.getWebRoot(request) + UPLOAD_FILE_ROOT + "/" + relativePath;
    }
    
    /**
	 * 多文件上传
	 * 
	 * @param request
	 * @return
	 * @throws SystemException
	 */
	public static Map<String, String> upload(HttpServletRequest request)
			throws SystemException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		Map<String, String> map = new HashMap<String, String>();
		if (isMultipart == true) {

			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			String path = request.getContextPath()+"/"+UPLOAD_FILE_ROOT+"/";
			try {

				List<FileItem> fileItems = upload.parseRequest(request);
				Iterator<FileItem> iter = fileItems.iterator();
				List<String> photoUrl = new ArrayList<String>();
				List<String> videoUrl = new ArrayList<String>();
				List<String> voiceUrl = new ArrayList<String>();
				 
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					if (item.isFormField()) {
						// 如果item是正常的表单域
						String name = item.getFieldName();
						String value = new String(item.getString().getBytes(
								"ISO-8859-1"), "utf-8");
						map.put(name, value);
					} else {
						String fileName = item.getName();
						if(StringUtils.isNotEmpty(fileName)){
							File pathF = new File(path);
							if (!pathF.exists()) {
								// 目录不存在则创建
								pathF.mkdirs();
							}
							if (fileName.lastIndexOf("\\") > 0
									&& (fileName.lastIndexOf("\\") + 1) < fileName
											.length())
								fileName = fileName.substring(fileName
										.lastIndexOf("\\") + 1);
							String savepath = path+CodeGenerator.getUUID()+"."+getFileType(fileName);
							File saveFilepath = new File(savepath);
							item.write(saveFilepath);
							if (isImage(fileName)) {
								photoUrl.add(savepath);
							}
							if (isVideo(fileName)){
								videoUrl.add(savepath);
							}
							if (isVoice(fileName)){
								voiceUrl.add(savepath);
							}
						}
					}
				}
				String photoUrlstr = getStrFromList(photoUrl);
				String videoUrlstr = getStrFromList(videoUrl);
				String voiceUrlstr = getStrFromList(voiceUrl);
				map.put("photoUrl", photoUrlstr);
				map.put("videoUrl", videoUrlstr);
				map.put("voiceUrl", voiceUrlstr);
			} catch (FileUploadException e) {
				throw new SystemException(e.getMessage(), e);

			} catch (Exception e) {
				throw new SystemException(e.getMessage(), e);
			}

		}
		return map;
	}

	public static  String getStrFromList(List list){
		StringBuffer sb = new StringBuffer();
		if(null != list){
			for(int i=0;i<list.size();i++){
				if(i == 0){
					sb.append(list.get(i));
				}else{
					sb.append(",");
					sb.append(list.get(i));
				}
			}
		}
		return sb.toString();
	}
	/**
	 * 判断是否为图片文件
	 * @param fileName
	 * @return
	 */
	public static boolean isImage(String fileName) {
		String extend = getFileType(fileName);
		String[] types = { "jpg", "png", "gif", "bmp","ico" };
		for (String type : types) {
			if (extend.equalsIgnoreCase(type)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否为视频文件
	 * @param fileName
	 * @return
	 */
	public static boolean isVideo(String fileName) {
		String extend = getFileType(fileName);
		String[] types = { "wmv", "avi", "mpg", "mpeg", "mp4","mov","mid","zip","rar","html","htm","txt","doc","docx","xml","xls","xlsx","jsp","asp","php","pdf","iso","exe"};
		for (String type : types) {
			if (extend.equalsIgnoreCase(type)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否为声音文件
	 * @param fileName
	 * @return
	 */
	public static boolean isVoice(String fileName) {
		String extend = getFileType(fileName);
		String[] types = { "wav", "mp3","caf" };
		for (String type : types) {
			if (extend.equalsIgnoreCase(type)) {
				return true;
			}
		}
		return false;
	}
    /**
     * 取得要保存在硬盘上的文件，如果不存在，则创建
     * 
     * @param diskPath
     * @return
     */
    public static File getSaveDiskFile(String diskPath){
        
        File file = new File(diskPath);
             file.mkdirs();
             
        return file;
    }
    
    /**
     * 根据给定的文件名取得该文件的类型
     * 
     * @param fileName
     * @return
     */
    public static String getFileType(String fileName){
        
        int pointPos = fileName.lastIndexOf(".");
        
        if(pointPos == -1)
            return "";
        
        return fileName.substring(pointPos+1, fileName.length());
    }
    
    /**
     * 删除文件或目录
     * 
     * @param file
     */
    public static void deleteFile(File file){
                
        if(file.exists() && file.isDirectory()){
            File[] subFileArray = file.listFiles();
            for(File subFile : subFileArray){
                deleteFile(subFile);
            }
        }
        
        file.delete();
    }
    
    /**
     * 将输入流保存成文件
     * 
     * @param file 保存的文件
     * @param in 输入流
     * @return
     */
    public static boolean saveFile(File file, InputStream in) throws Exception {
        
        boolean result = false;
        
        if(file == null || in == null)
            return result;
        
        FileOutputStream out = null;
        
        try {            
            //如果指定文件的目录不存在,则创建之. 
            File parent = file.getParentFile(); 
            if(!parent.exists()){ 
                parent.mkdirs(); 
            } 
            
            out = new FileOutputStream(file); 
            byte[] c = new byte[1024];
            int slen;        
            
            while((slen = in.read(c) ) > 0){ 
                out.write(c , 0 , slen ); 
            } 
        } catch (Exception e) {
            // TODO: handle exception
        } finally { 
            if (in != null) {
                in.close();        //解压完成后注意关闭输入流对象
            }
            
            if (out != null) { 
                out.close();      //解压完成后注意关闭输出流对象
            } 
        } 
        
        
        return result;
    }
    
    /**
     * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     *
     * @param s 原文件名
     * @return 重新编码后的文件名
     */
    public static String toUtf8String(String s)
    {
        String strTitle = s.substring(0, s.lastIndexOf("."));
        String strExct = s.substring(s.lastIndexOf("."), s.length());
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < strTitle.length(); i++)
        {
            char c = strTitle.charAt(i);
            if (c >= 0 && c <= 255)
            {
                if (sb.toString().length() < 147)
                {
                    sb.append(c);
                }
                else
                {
                    sb.append("[1]");
                    break;
                }
            }
            else
            {
                byte[] b;
                try
                {
                    b = (new Character(c).toString()).getBytes("UTF-8"); //"utf-8"
                    // b = (new StringBuffer().append(c)).toString().getBytes("utf-8");
                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                    b = new byte[0];
                }
                if (sb.toString().length() < 140)
                {
                    for (int j = 0; j < b.length; j++)
                    {
                        int k = b[j];
                        if (k < 0)
                        {
                            k += 256;
                        }
                        sb.append("%" + Integer.toHexString(k).
                                  toUpperCase());
                    }
                }
                else
                {
                    sb.append("[1]");
                    break;
                }
            }
        }
        return sb.toString() + strExct;
    }
    
    /**
     * 异步上传文件返回图片路径
     * @param uploadFile
     * @param request
     * @return
     */
    public static String uploadFile(FormFile uploadFile ,String imgPath, HttpServletRequest request) {   
        String path = imgPath;
        
     Map map = new HashMap();
     try {   
     	 String logoRealPathDir = request.getSession().getServletContext().getRealPath(path);     
          /**根据真实路径创建目录**/      
          File logoSaveFile = new File(logoRealPathDir);       
          if(!logoSaveFile.exists())       
              logoSaveFile.mkdirs();
         int lastnum = uploadFile.getFileName().lastIndexOf('.');
         String newPath = DateUtils.getCurrDateStr_()+new Random().nextInt(10000);
         String name = newPath+uploadFile.getFileName().substring(lastnum, uploadFile.getFileName().length());
         FileOutputStream outer = new FileOutputStream(logoRealPathDir+"/"+name);   
         byte[] buffer = uploadFile.getFileData();   
         outer.write(buffer);   
         outer.close();   
         uploadFile.destroy();
         return path+"/"+name;
     } catch (Exception e) {   
         e.printStackTrace();
     }   
     return null;   
}
}

