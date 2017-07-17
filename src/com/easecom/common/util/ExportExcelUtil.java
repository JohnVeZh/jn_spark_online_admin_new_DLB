package com.easecom.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcelUtil {
	/** 
	   *  
	   * @param request    request对象 
	    * @param sheetname  工作表实例的名称 
	   * @return 
	   * @throws Exception 
	*/  
	public static void exportExcelList(String[] title,String[] rowsName,List<Map> list,String sheetName,String tableName,String path)throws Exception{  
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			workbook.setSheetName(0,sheetName);
			HSSFRow row= sheet.createRow((short)0);
			HSSFCell cell;
			
			for(int i=0;i<title.length;i++){
				cell = row.createCell((short)(i));
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(title[i]);
			}
			String[] rows=upCase(rowsName);
			
			 for (int i = 0; i < list.size(); i++) {  
	             HSSFRow dataRow = sheet.createRow(i + 1);  
	             Map map =list.get(i);  
	             int j = 0;  
	             for(int b=0;b<rows.length;b++){  
		              cell = dataRow.createCell((short)j++);// 创建数据列 
		              if(null!=map.get(rows[b])&&""!=map.get(rows[b])){  
		            	  	cell.setCellValue(map.get(rows[b])+""); 
		                 }else{  
		                     cell.setCellValue("");
		                 }  
	             }  
			 }

		 FileOutputStream fOut = new FileOutputStream(path+sheetName);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
	}
	public static  String[] upCase(String[] s){
		String[] rowName=new String[s.length];
		for (int i = 0; i < s.length; i++) {
			rowName[i]=s[i].toLowerCase();
		}
	
		return rowName;
	}
	public static void downloadExcel(HttpServletRequest request, HttpServletResponse response,String fileName,String filePath){
			try{	
				// 从文件完整路径中提取文件名，并进行编码转换，防止不能正确显示中文名
				fileName = new String(fileName.getBytes("GBK"), "ISO8859_1");
				// 打开指定文件的流信息
				FileInputStream fs = null;
				fs = new FileInputStream(new File(filePath));
				// 设置响应头和保存文件名
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", "attachment; filename=\""
						+ fileName + "\"");
				// 写出流信息
				int b = 0;
				PrintWriter out = response.getWriter();
				while ((b = fs.read()) != -1) {
					out.write(b);
				}
				fs.close();
				out.close();		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}


