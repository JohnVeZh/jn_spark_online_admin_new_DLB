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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
/**
 *xml工具类
 */
public final class XmlUtils {
	private static LogUtils log = LogUtils.getLogger(XmlUtils.class);

	public XmlUtils() {
	}

	/**
	 * 读取指定路径XML文件的节点值
	 * 
	 * @param infile
	 * @return
	 * @throws Exception
	 */
	public static String readXml(String infile, String childName)
			throws Exception {
		String str = "";
		SAXBuilder sb = new SAXBuilder();// 建立构造器
		org.jdom.Document doc = sb.build(new FileInputStream(infile));// 读入指定文件
		Element root = doc.getRootElement();// 获得根节点
		List list = root.getChildren();// 将根节点下的所有子节点放入List中
		for (int i = 0; i < list.size(); i++) {
			Element item = (Element) list.get(i);// 取得节点实例
			Element sub = item.getChild(childName);// 取得当前节点的字节点
			str = sub.getText();// 取得当前节点的值
		}
		return str;
	}
	
	/***
	 * 创建XML
	 * @param path  文件路路径和名称
	 * @author 李来源
	 * Sep 24, 20132:19:18 PM
	 */
    public static void BuildXMLDoc(String path){
    	try {
    		
    		Element plist = new Element("plist");
    		plist.setAttribute("version","1.0");
    		Document Doc = new Document(plist);
    		
    		 // 创建根节点 list;
            Element root = new Element("dict");
            // 将根节点添加到文档中；
            
            Element keyEle = new Element("key");
            keyEle.setText("items");
            root.addContent(keyEle);
            
            Element arrayEle = new Element("array");
            Element dicEle = new Element("dict");
            dicEle.addContent(new Element("key").setText("assets"));
            arrayEle.addContent(dicEle);
            
            Element arrayAEle = new Element("array");
            //1
            String[] objs = {"software-package","http://www.cmcrc.org.cn/front/downloadapk.do?act=downloadIPA&filePath=gameimage&fileName=chengxindianzishichangipa"};
            String[] keys = {"kind","url"};
            arrayAEle.addContent(bulidDictXml(0,keys,objs));
            //2
            String[] objs1 = {"display-image","http://app.cmcrc.org.cn/frontImages/cicon.png"};
            String[] keys1 = {"kind","needs-shine","url"};
            arrayAEle.addContent(bulidDictXml(1,keys1,objs1));
            //3
            String[] objs2 = {"full-size-image","http://app.cmcrc.org.cn/frontImages/cicon.png"};
            String[] keys2 = {"kind","needs-shine","url"};
            arrayAEle.addContent(bulidDictXml(1,keys2,objs2));
            dicEle.addContent(arrayAEle);
            //
            dicEle.addContent(new Element("key").setText("metadata"));
            
            //4
            String[] objs3 = {"com.zhangxing.cxdzsc","1.0","software","诚信电子市场","诚信电子市场"};
            String[] keys3 = {"bundle-identifier","bundle-version","kind","subtitle","title"};
            dicEle.addContent(bulidDictXml(2,keys3,objs3));
            
            root.addContent(arrayEle);
            plist.addContent(root);
            XMLOutputter XMLOut = new XMLOutputter();
            // 输出文件；
            XMLOut.output(Doc, new FileOutputStream(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /***
     * @return
     * @author 李来源
     * Sep 24, 20132:58:44 PM
     */
    public static Element bulidDictXml(int state,String[] keys,String[] objs){
    	Element dictEle = new Element("dict");
    	try {
    		dictEle.addContent(new Element("key").setText(keys[0]));
    		dictEle.addContent(new Element("string").setText(objs[0]));
    		dictEle.addContent(new Element("key").setText(keys[1]));
    		if(state==1){
    			dictEle.addContent(new Element("true"));
    			dictEle.addContent(new Element("key").setText(keys[2]));
    		}
    		dictEle.addContent(new Element("string").setText(objs[1]));
    		if(state==2){
    			dictEle.addContent(new Element("key").setText(keys[2]));
    			dictEle.addContent(new Element("string").setText(objs[2]));
    			dictEle.addContent(new Element("key").setText(keys[3]));
    			dictEle.addContent(new Element("string").setText(objs[3]));
    			dictEle.addContent(new Element("key").setText(keys[4]));
    			dictEle.addContent(new Element("string").setText(objs[4]));
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dictEle;
    }
    
    public static void main(String[] args) {
    	try {
    		BuildXMLDoc("d:/a.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
