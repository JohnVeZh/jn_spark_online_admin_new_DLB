package com.easecom.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;

/**
 * 
 * @author Administrator
 *
 */
public class UploadUtil {
	/**
	 * 
	 * @param url 上传文件的本地地址
	 * @param url2 上传文件的目的地址
	 * @return
	 */
	public boolean upload(ServletInputStream sis,String url2){
		BufferedInputStream bis =null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(sis);
			 bos = new BufferedOutputStream(new FileOutputStream(url2));
			 byte[] b = new byte[1024*1024];
			 int bs = 0;
			 try {
				 while((bs = bis.read(b))!=-1){
					 bos.write(b, 0, b.length);
					 bos.flush();
				 }
			 } catch (IOException e) {
				 // TODO Auto-generated catch block
				 e.printStackTrace();
			 }
			 return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			try {
				if(bis==null) bis.close();
				if(bos==null){
					bos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
