package com.easecom.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class FontImg {
	// 根据str,font的样式以及输出文件目录
	public static void createImage(String str, Font font, File outFile)
			throws Exception {
		// 获取font的样式应用在str上的整个矩形
		Rectangle2D r = font.getStringBounds(str, new FontRenderContext(
				AffineTransform.getScaleInstance(1, 1), false, false));
		int unitHeight = (int) Math.floor(r.getHeight());// 获取单个字符的高度
		// 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
		int width = (int) Math.round(r.getWidth()) + 1;
		int height = unitHeight + 3;// 把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
		// 创建图片
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);// 先用白色填充整张图片,也就是背景
		g.setColor(Color.black);// 在换成黑色
		g.setFont(font);// 设置画笔字体
		g.drawString(str, 0, font.getSize());// 画出字符串
		g.dispose();
		ImageIO.write(image, "png", outFile);// 输出png图片
	}
	/*判断远程文件是否存在*/
	public static boolean fontFile(String url){
		java.net.URL url1 = null;  
		  
		java.net.URLConnection urlCon;  
		  
		try {  
		  
		    url1 = new java.net.URL(url);  
		  
		    urlCon = url1.openConnection();  
		  
		    String message = urlCon.getHeaderField(0);
		  //文件存在‘HTTP/1.1 200 OK’ 文件不存在 ‘HTTP/1.1 404 Not Found’
		    if (null!= message && message.startsWith("HTTP/1.1 200 OK")) {
		    	return true;
		    }else{
		    	return false;
		    }
		} catch (java.net.MalformedURLException e0) {  
			return false;
		} catch (java.io.IOException ie3) {  
		  
			return false;
		}
	}
}
