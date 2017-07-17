package com.easecom.common.util;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ExcelUtil {
	 /**
	   *
	   * param request    request对象
	   * param sheetname  工作表实例的名称
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
		               
		                 //cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);  
		                 if(null!=map.get(rows[b])&&""!=map.get(rows[b])){  
			                	 	//cell.setCellValue(Double.parseDouble(map.get(rows[b]).toString()));  
			                	 	cell.setCellValue(map.get(rows[b])+"");    
			                	 	
		                 }else{  
		                     cell.setCellValue("");    
		                 }  
	             }  
			 }

		 FileOutputStream fOut = new FileOutputStream(path+"uploadfiles/"+sheetName+".xls");
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
	}
	public static  String[] upCase(String[] s){
		String[] rowName=new String[s.length];
		for (int i = 0; i < s.length; i++) {
			rowName[i]=s[i].toUpperCase();
		}
	
		return rowName;
	}
	
	public void downloadExcel(
			HttpServletRequest request, HttpServletResponse response,String fileName,String filePath)
			throws Exception {

			try {
				//String filePath = request.getRealPath("/") + "/uploadfiles/" + fileName;
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
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	/**
	 * 导出订单
	 */
	public  void ExportExcel(String[] titles, List list, ServletOutputStream outputStream) {
		// 创建一个workbook 对应一个excel应用文件
		HSSFWorkbook workBook = new HSSFWorkbook();
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		// Sheet名称，可以自定义中文名称
		HSSFSheet sheet = workBook.createSheet("Sheet1");
		sheet.setDefaultRowHeightInPoints(50);
		sheet.setDefaultColumnWidth(15);
		// 构建表头
		HSSFRow headRow = sheet.createRow(0);
		HSSFCell cell = null;
		// 输出标题
		HSSFCellStyle cellStyle = workBook.createCellStyle();

		// 设置单元格的背景颜色为黄色
		cellStyle.setFillForegroundColor(new HSSFColor.YELLOW().getIndex());
		cellStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
		HSSFFont  font  =  workBook.createFont();
		//字体宽度
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		//字体高度
		font.setFontHeightInPoints((short)15);
		//字体颜色
		font.setColor(HSSFColor.GREY_50_PERCENT.index);
		cellStyle.setFont(font);
		cellStyle.setFillPattern(HSSFCellStyle.SPARSE_DOTS);
		for (int i = 0; i < titles.length; i++) {
			cell = headRow.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(cellStyle);
		}
		// 构建表体数据
		for (int j = 0; j < list.size(); j++) {
			Map map = (Map) list.get(j);
			HSSFRow bodyRow = sheet.createRow(j + 1);
			cell = bodyRow.createCell(0);
			try{
				cell.setCellValue(map.get("order_code").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}
			cell = bodyRow.createCell(1);
			try{
				cell.setCellValue(map.get("p_name").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}

			cell = bodyRow.createCell(2);
            cell.setCellValue("");
            cell = bodyRow.createCell(3);
            try{
                cell.setCellValue(map.get("pcount").toString());
            }catch(Exception e){
                cell.setCellValue("");
            }
			cell = bodyRow.createCell(4);
			cell.setCellValue("");

			cell = bodyRow.createCell(5);
            try{
				cell.setCellValue( Double.parseDouble(map.get("money").toString())* Integer.parseInt(map.get("pcount").toString()));
            }catch(Exception e){
                cell.setCellValue("");
            }

			cell = bodyRow.createCell(6);
			cell.setCellValue("");

			cell = bodyRow.createCell(7);
			try{
				cell.setCellValue(map.get("username").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}

			cell = bodyRow.createCell(8);
			try{
				cell.setCellValue(map.get("consignee").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}

			cell = bodyRow.createCell(9);
			try{
				cell.setCellValue(map.get("telephone").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}

			cell = bodyRow.createCell(10);
			cell.setCellValue("");

			cell = bodyRow.createCell(11);
			try{
				cell.setCellValue(map.get("zipcode").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}

			cell = bodyRow.createCell(12);
			try{
				cell.setCellValue(map.get("pname").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}

			cell = bodyRow.createCell(13);
			try{
				cell.setCellValue(map.get("city").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}

			cell = bodyRow.createCell(14);
			try{
				cell.setCellValue(map.get("area").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}

			cell = bodyRow.createCell(15);
			try{
				cell.setCellValue(map.get("address").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}

			cell = bodyRow.createCell(16);
			try{
				cell.setCellValue(map.get("remark").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}
			cell = bodyRow.createCell(17);
			cell.setCellValue("");
			cell = bodyRow.createCell(18);
			try{
				cell.setCellValue(map.get("postage").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}
			cell = bodyRow.createCell(19);
			try{
				cell.setCellValue(map.get("createtime").toString());
			}catch(Exception e){
				cell.setCellValue("");
			}
		}
		try {
			workBook.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 通用导出excel
	 * @param titles 头数组
	 * @param column map-key数组，无对应列的列数组中放null
	 * @param list<Map> map list集合
	 * @param fileName xml名称
	 * param outputStream
	 */

	// 导出订单
	public  void ExportExcelConmon(String[] titles, String []column, List<Map> list,String fileName, HttpServletResponse response) {

		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		response.setCharacterEncoding("utf-8");
		ServletOutputStream outputStream = null;
		// 创建一个workbook 对应一个excel应用文件
		HSSFWorkbook workBook = new HSSFWorkbook();
		// 在workbook中添加一个sheet,对应Excel文件中的sheet
		// Sheet名称，可以自定义中文名称
		HSSFSheet sheet = workBook.createSheet("Sheet1");
		sheet.setDefaultRowHeightInPoints(50);
		sheet.setDefaultColumnWidth(15);
		// 构建表头
		HSSFRow headRow = sheet.createRow(0);
		HSSFCell cell = null;
		// 输出标题
		HSSFCellStyle cellStyle = workBook.createCellStyle();

		// 设置单元格的背景颜色为黄色
//		cellStyle.setFillForegroundColor(new HSSFColor.YELLOW().getIndex());
//		cellStyle.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
		HSSFFont  font  =  workBook.createFont();
		//字体宽度
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		//字体高度
		font.setFontHeightInPoints((short)15);
		//字体颜色
		font.setColor(HSSFColor.GREY_50_PERCENT.index);
		cellStyle.setFont(font);
//		cellStyle.setFillPattern(HSSFCellStyle.SPARSE_DOTS);
		for (int i = 0; i < titles.length; i++) {
			cell = headRow.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(cellStyle);
		}
		// 构建表体数据
		for (int j = 0; j < list.size(); j++) {
			Map map  =  list.get(j);
			HSSFRow bodyRow = sheet.createRow(j + 1);
			for(int n = 0;n<column.length;n++){
				cell = bodyRow.createCell(n);
				if(column[n] != null && !column[n].equals("null")){
					try{
						if(column[n].equals("money")){
							if (null != map.get("money")){
								cell.setCellValue( Double.parseDouble(map.get("money").toString())* Integer.parseInt(map.get("pcount").toString()));
							}else{
								cell.setCellValue("");
							}
						}else{
							if(null != map.get(column[n])){
								cell.setCellValue(map.get(column[n]).toString());
							}else {
								cell.setCellValue("");
							}
						}

					}catch (Exception e){
						System.out.println(e.getMessage());
						System.out.println("导出部分，列"+column[n]+"取值报异常！");
					}
				}else{
					cell.setCellValue("");
				}
			}
		}
		try {
			outputStream = response.getOutputStream();
			workBook.write(outputStream);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}