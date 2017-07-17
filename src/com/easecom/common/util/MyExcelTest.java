/**     
 * @文件名称: MyExcelTest.java  
 * @类路径: com.easecom.common.util  
 * @描述: TODO  
 * @作者：hu shengyang  
 * @时间：2016年1月28日 下午2:39:40  
 * @版本：V1.0     
 */  

package com.easecom.common.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.contrib.RegionUtil;
import org.apache.poi.ss.util.Region;

import com.business.BookActivationCode.BookActivationCodeActionForm;



/**
 *  2012_12_21 OK 
 *  excel 合并单元格，(也可以单独设计单元格宽度等)设置背景色等  测试  
 * @author 
 *
 */
public class MyExcelTest {

	


	 public static void main(String[] args) {
	 // 创建新的Excel 工作簿      
	HSSFWorkbook workbook = new HSSFWorkbook(); 

	 // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称    
	HSSFSheet sheet = workbook.createSheet(); 


	 //System.out.println("isDisplayGridlines="+sheet.isDisplayGridlines());

	 //（可选:）(重要：)设置单元格的宽度 (注意：比较自由)
	sheet.setDefaultColumnWidth(15);
	 //sheet.setColumnWidth(i, 256 * 15);  //针对第个单元格 设置宽度

	//设置row高度
	sheet.setDefaultRowHeightInPoints(15);

	 //HSSFSheet sheet = workbook.createSheet("SheetName");  
	 // 用于格式化单元格的数据
	HSSFDataFormat format = workbook.createDataFormat(); 

	 // 设置字体
	HSSFFont font = workbook.createFont();
	 font.setFontHeightInPoints((short) 20);
	 //字体高度        
	font.setColor(HSSFFont.COLOR_NORMAL);//字体颜色       
	font.setFontName("黑体"); 
	 //字体       
	font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
	 //宽度       
	font.setItalic(true); 

	 //是否使用斜体
//	        font.setStrikeout(true); 
	 //是否使用划线       
	 // 设置单元格类型      
	HSSFCellStyle cellStyle = workbook.createCellStyle(); 
	 cellStyle.setFont(font);        
	 cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平布局：居中    

	    
	cellStyle.setWrapText(true);  


	 //如果设置前后、背景色时，前景色要先设置
	HSSFCellStyle cellStyle_2 = workbook.createCellStyle(); 


	 cellStyle_2.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平布局：居中   

	cellStyle_2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//HSSFCellStyle.FINE_DOTS  SOLID_FOREGROUND
	 cellStyle_2.setFillForegroundColor(new HSSFColor.BLUE_GREY().getIndex());
	 //cellStyle_2.setFillBackgroundColor(new HSSFColor.RED().getIndex());


	 //这种背景色，也可以
	// cellStyle_2 = wookbook.createCellStyle();
	 // cellStyle_2.setFillForegroundColor(HSSFColor.ORANGE.index);
	 // cellStyle_2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


	        

	 // // 添加单元格注释       
	 // // 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.   
	 // HSSFPatriarch patr = sheet.createDrawingPatriarch();    
	 // // 定义注释的大小和位置,详见文档       
	 // HSSFComment comment = patr.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short) 6, 5));      
	 // // 设置注释内容        
	 // comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));    
	 // // 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.     
	 // comment.setAuthor("Xuys.");  


	 //创建 "研发一部工作总结及计划" 跨7列 start=========
	 String title = "研发一部工作总结及计划";
	HSSFRow row = sheet.createRow(0);
	 // 创建单元格   
	HSSFCell cell = row.createCell(0); 
	 HSSFCell cell_1 = row.createCell(1); 
	 HSSFCell cell_2 = row.createCell(2); 
	 HSSFCell cell_3 = row.createCell(3); 
	 HSSFCell cell_4 = row.createCell(4); 
	 HSSFCell cell_5 = row.createCell(5); 
	 HSSFCell cell_6 = row.createCell(6); 

	 HSSFRichTextString hssfString = new HSSFRichTextString(title); 

	 cell.setCellValue(hssfString);//设置单元格内容
	cell.setCellStyle(cellStyle);

	 CellRangeAddress cellRangeAddr = new CellRangeAddress(0,0,0,6);
	 sheet.addMergedRegion(cellRangeAddr);

	 //设置边框测试
	//MyExcelTest.setRegionBorder(20, cellRangeAddr, sheet, workbook);

	 //创建 "研发一部工作总结及计划" 跨7列 end=========


	 //姓名 (6格)
	HSSFRow row_1 = sheet.createRow(1);
	 HSSFCell cell_1_0 = row_1.createCell(0);
	 int mergeCols = 6;
	 for(int i=1; i < mergeCols; i++){
	 row_1.createCell(i);
	 }
	 String userName = "陈丽惠";
	cell_1_0.setCellValue(userName);
	 CellRangeAddress cell_rang_2 = new CellRangeAddress(1,1,0,5);
	 sheet.addMergedRegion(cell_rang_2);

	 HSSFCell cell_1_6 = row_1.createCell(6);
	 String dateStr = "2012-12-13";
	 cell_1_6.setCellValue(dateStr);


	 //创建背景色cell

	 HSSFRow row_2 = sheet.createRow(2);
	 HSSFCell cell_2_0 = row_2.createCell(0);
	 cell_2_0.setCellValue("this is test");
	 cell_2_0.setCellStyle(cellStyle_2);


	 //边框样式
	      HSSFCellStyle style = workbook.createCellStyle();
	 //        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	 //        style.setBottomBorderColor(HSSFColor.BLACK.index);
	 //        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	 //        style.setLeftBorderColor(HSSFColor.GREEN.index);
	 //        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	 //        style.setRightBorderColor(HSSFColor.BLUE.index);
	 //        style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM_DASHED);
	 //        style.setTopBorderColor(HSSFColor.ORANGE.index);
	 //      
	        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	        style.setBottomBorderColor(HSSFColor.BLACK.index);
	        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        style.setLeftBorderColor(HSSFColor.BLACK.index);
	        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        style.setRightBorderColor(HSSFColor.BLACK.index);
	        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        style.setTopBorderColor(HSSFColor.BLACK.index);
	        
	       
	        
	        HSSFRow row_3 = sheet.createRow(3);
	 HSSFCell cell_3_0 = row_3.createCell(1);
	 cell_3_0.setCellValue("test kkk");
	 //cell_3_0.setCellStyle(style);    //单个单元格，设置框这个可以

	HSSFCell cell_3_2 = row_3.createCell(2);
	 CellRangeAddress cellRangeAddr3 = new CellRangeAddress(3,3,1,2);
	 MyExcelTest.setRegionBorder(1, cellRangeAddr3, sheet, workbook); //合并单元格，设置边框

	sheet.addMergedRegion(cellRangeAddr3);


	 //格式化时间  OK===
	 // we style the second cell as a date (and time).  It is important to create a new cell style from the workbook
	        // otherwise you can end up modifying the built in style and effecting not only this cell but other cells.
	        HSSFCellStyle cellStyle4 = workbook.createCellStyle();
	        cellStyle4.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
	        
	        HSSFRow row_4 = sheet.createRow(4);
	 HSSFCell cell_4_0 = row_4.createCell(0);
	 cell_4_0.setCellValue("test kkk");

	       
	 cell_4_0.setCellValue(new Date());
	 cell_4_0.setCellStyle(cellStyle4);
	  
	 // // 创建新行(row),并将单元格(cell)放入其中. 行号从0开始计算.
	 // HSSFRow row = sheet.createRow(1);
	 //
	 // // 创建单元格   
	 // HSSFCell cell = row.createCell(1);    
	 // HSSFRichTextString hssfString = new HSSFRichTextString("Hello World!这是内容这是内容这是内容这"); 
	 // cell.setCellValue(hssfString);//设置单元格内容        
	 //
	 // cell.setCellStyle(cellStyle);//设置单元格样式      
	 // cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串       
	 // //cell.setCellComment(comment);//添加注释      
	 //
	 // HSSFCell cell2 = row.createCell(2);
	 // HSSFCell cell3 = row.createCell(3);
	 // //合并单元格 (注意：重要 OK)
	 // //sheet.addMergedRegion(new Region(1,(short)1,1,(short)3));
	 //
	 // //CellRangeAddress(int firstRow, int lastRow, int firstCol, int lastCol) 
	 // sheet.addMergedRegion(new CellRangeAddress(1,1,1,3));
	 //
	 // //调整第一列宽度   (自动调整宽度)
	 //   // sheet.autoSizeColumn(1);
	 // sheet.autoSizeColumn(1,true); //true 考虑合并单元格
	////    
	 ////    sheet.autoSizeColumn(4);
	 //   
	 //    HSSFCell cell_2 = row.createCell(4);
	 //    String val = "this is cell 2 内容2";
	 //    cell_2.setCellValue(val);
	 //   
	 //    HSSFRow row2 = sheet.createRow(2);
	 //    HSSFCell row_cell = row2.createCell(1);
	 //    row_cell.setCellValue("this iss aaaaaaaaa");
	 //    
	 //    row_cell = row2.createCell(2);
	 //    row_cell.setCellValue("另一个cell aaa ");
	 try {          
	       String fileName = "C:\\Users\\Administrator.WGQHJPQG6FF1SCQ\\Desktop\\aa\\excel_my_2.xls";
	       FileOutputStream fileOut = new FileOutputStream(fileName);  
	       workbook.write(fileOut);          
	       fileOut.close();
	       System.out.println("fileName="+fileName);
	       } catch (Exception e) {  
	       System.out.println(e.toString());  
	      
	       }
	 }

	 //设置边框
	public static void setRegionBorder(int border, CellRangeAddress region, org.apache.poi.ss.usermodel.Sheet sheet,org.apache.poi.ss.usermodel.Workbook wb){  
	         RegionUtil.setBorderBottom(border,region, sheet, wb);  
	         RegionUtil.setBorderLeft(border,region, sheet, wb);  
	         RegionUtil.setBorderRight(border,region, sheet, wb);        
	         RegionUtil.setBorderTop(border,region, sheet, wb);  
	       
	     } 

/**
 * 
 * 方法功能说明： 导出 (带订单状态)
 * 创建：2016年1月29日 by Zzc   
 * 修改：日期 by 修改者  
 * 修改内容：  
 * @参数： @param list
 * @参数： @param path      
 * @return void     
 * @throws
 */
public static void excleOrder(List list,String path) {
    // 创建新的Excel 工作簿
   HSSFWorkbook workbook = new HSSFWorkbook();
    
    // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
   HSSFSheet sheet = workbook.createSheet();
    //HSSFSheet sheet = workbook.createSheet("SheetName"); 
    
    // 用于格式化单元格的数据
   HSSFDataFormat format = workbook.createDataFormat();
    
    // 创建新行(row),并将单元格(cell)放入其中. 行号从0开始计算.
    HSSFRow row = sheet.createRow((short) 100);
    
    // 设置字体
   HSSFFont font = workbook.createFont();
    font.setFontHeightInPoints((short) 24); //字体高度
   font.setColor(HSSFFont.COLOR_NORMAL); //字体颜色
   font.setFontName("宋体"); //字体
   font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //宽度
   font.setItalic(false); //是否使用斜体
//   font.setStrikeout(true); //是否使用划线
   
   HSSFFont font1 = workbook.createFont();
   font1.setFontHeightInPoints((short) 14); //字体高度
   font1.setColor(HSSFFont.COLOR_NORMAL); //字体颜色
   font1.setFontName("宋体"); //字体
   font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //宽度
   font1.setItalic(false); //是否使用斜体
//   font1.setStrikeout(true); //是否使用划线

   HSSFFont font2 = workbook.createFont();
   font2.setFontHeightInPoints((short) 14); //字体高度
   font2.setColor(HSSFFont.COLOR_NORMAL); //字体颜色
   font2.setFontName("宋体"); //字体
   font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //宽度
   font2.setItalic(false); //是否使用斜体
//   font1.setStrikeout(true); //是否使用划线
   
   
   // 设置单元格类型
   HSSFCellStyle cellStyle = workbook.createCellStyle();
    cellStyle.setFont(font);
    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平布局：居中
    cellStyle.setWrapText(true);
    
    HSSFCellStyle cellStyle1 = workbook.createCellStyle();
    cellStyle1.setFont(font1);
    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT); //水平布局：居中
    font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //宽度
    cellStyle1.setWrapText(true);

    
    HSSFCellStyle style = workbook.createCellStyle();   
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框   
    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框   
    style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框   
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
    cellStyle1.setFont(font2);
    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT); //水平布局：居中
    cellStyle1.setWrapText(true);
    
    // 添加单元格注释
   // 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.
    HSSFPatriarch patr = sheet.createDrawingPatriarch();
    // 定义注释的大小和位置,详见文档
   HSSFComment comment = patr.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short) 6, 5));
    // 设置注释内容
   comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
    // 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.
    comment.setAuthor("Xuys.");
    
    // 创建单元格
   HSSFCell cell = row.createCell((short) 0);
	 
	 
	
//    HSSFRichTextString hssfString = new HSSFRichTextString("朗宜行PC20160125-01(AM)");
//    cell.setCellValue(hssfString);//设置单元格内容
//   cell.setCellStyle(cellStyle);//设置单元格样式
//   cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//   cell.setCellComment(comment);//添加注释
	/**
    * 合并单元格，使用addMergedRegoin函数.
    */
   sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)13));
   sheet.addMergedRegion(new Region((short)1, (short)0, (short)1, (short)13));
   
     CellRangeAddress cellRangeAddr5 = new CellRangeAddress(list.size()+4,list.size()+5,0,0);
	 MyExcelTest.setRegionBorder(1, cellRangeAddr5, sheet, workbook); //合并单元格，设置边框
	 sheet.addMergedRegion(cellRangeAddr5);
	 
	 CellRangeAddress cellRangeAddr5_5 = new CellRangeAddress(list.size()+4,list.size()+4,6,8);
	 MyExcelTest.setRegionBorder(1, cellRangeAddr5_5, sheet, workbook); //合并单元格，设置边框
	 sheet.addMergedRegion(cellRangeAddr5_5);
	 
	 CellRangeAddress cellRangeAddr6_5 = new CellRangeAddress(list.size()+5,list.size()+5,6,8);
	 MyExcelTest.setRegionBorder(1, cellRangeAddr6_5, sheet, workbook); //合并单元格，设置边框
	 sheet.addMergedRegion(cellRangeAddr6_5);
	 
	 CellRangeAddress cellRangeAddr7_5 = new CellRangeAddress(list.size()+6,list.size()+6,0,13);
	 MyExcelTest.setRegionBorder(1, cellRangeAddr7_5, sheet, workbook); //合并单元格，设置边框
	 sheet.addMergedRegion(cellRangeAddr7_5);
	 
	 
	 
  
   //格式化数据
   row = sheet.createRow((short) 0);
    cell = row.createCell((short) 0);
    cell.setCellValue("星火PC" +DateUtils.getCurrDateStr_());
//    cellStyle = workbook.createCellStyle();
    cell.setCellStyle(cellStyle);//指定单元格格式：数值、公式或字符串
//    cellStyle.setDataFormat(format.getFormat("0.0"));
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    
  row = sheet.createRow((short) 1);
    cell = row.createCell((short) 0);
    cell.setCellValue("办公室电话： 57038818   18610399267");
    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    
  row = sheet.createRow((short) 2);
    cell = row.createCell((short) 0);
    cell.setCellValue("派车人签字： ");
    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 3);
    cell.setCellValue("车牌号：");
    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    
    /******以下是取得数据********/
  row = sheet.createRow((short) 3);
    cell = row.createCell((short) 0);
    cell.setCellStyle(style);
    cell.setCellValue("序号 ");
//    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 1);
    cell.setCellValue("激活码");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 2);
    cell.setCellValue("关联图书");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 3);
    cell.setCellValue("生成日期");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 4);
    cell.setCellValue("使用时间");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 5);
    cell.setCellValue("使用地区");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 6);
    cell.setCellValue("使用账号");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 7);
    cell.setCellValue("使用次数");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 8);
    cell.setCellValue("生成人");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 9);
    cell.setCellValue("是否使用");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 10);
    cell.setCellValue("是否删除");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 11);
    cell.setCellValue("备注");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 12);
    cell.setCellValue("");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 13);
    cell.setCellValue("");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    
    for (int i = 0; i < list.size(); i++) {
    	BookActivationCodeActionForm vo = (BookActivationCodeActionForm) list.get(i);
      row = sheet.createRow((short) i+4);
        cell = row.createCell((short) 0);
        cell.setCellStyle(style);
        cell.setCellValue(i+1);
//        cell.setCellStyle(cellStyle1);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 1);
        cell.setCellValue(vo.getCode());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 2);
        cell.setCellValue(vo.getProductId());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 3);
        cell.setCellValue(vo.getCreatetime());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 4);
        cell.setCellValue(vo.getUseTime());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 5);
        cell.setCellValue(vo.getUseRegion());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 6);
        cell.setCellValue(vo.getUserId());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 7);
        cell.setCellValue(vo.getUseNum());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 8);
        cell.setCellValue(vo.getSysUserId());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 9);
        cell.setCellValue(vo.getIsUse());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 10);
        cell.setCellValue(vo.getIsDel());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 11);
        cell.setCellValue(vo.getIsDel());
        cell.setCellStyle(style);
//        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//        cell = row.createCell((short) 12);
//        cell.setCellValue(vo.getRemark());
//        cell.setCellStyle(style);
//        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 13);
        cell.setCellValue("");
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
	}
    
    /*******以下是底部部门*******/
 row = sheet.createRow((short) list.size()+4);
    cell = row.createCell((short) 0);
    cell.setCellStyle(cellStyle1);
    cell.setCellValue("注： ");
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 1);
    cell.setCellStyle(style);
    cell.setCellValue("最远端");
//    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 3);
    cell.setCellStyle(style);
    cell.setCellValue("包车价格");
//    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 5);
    cell.setCellStyle(style);
    cell.setCellValue("高速费");
//    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    
    cell = row.createCell((short) 2);
    cell.setCellStyle(style);
    cell = row.createCell((short) 4);
    cell.setCellStyle(style);
    cell = row.createCell((short) 6);
    cell.setCellStyle(style);
    cell = row.createCell((short) 7);
    cell.setCellStyle(style);
    cell = row.createCell((short) 8);
    cell.setCellStyle(style);
    cell = row.createCell((short) 9);
    cell.setCellStyle(style);
    cell = row.createCell((short) 10);
    cell.setCellStyle(style);
    cell = row.createCell((short) 11);
    cell.setCellStyle(style);
    cell = row.createCell((short) 12);
    cell.setCellStyle(style);
    cell = row.createCell((short) 13);
    cell.setCellStyle(style);
    
  row = sheet.createRow((short) list.size()+5);
    cell = row.createCell((short) 1);
    cell.setCellStyle(style);
    cell.setCellValue("最远端收费价格");
//    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 3);
    cell.setCellStyle(style);
    cell.setCellValue("改点个数");
//    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 5);
    cell.setCellStyle(style);
    cell.setCellValue("其他");
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//    cell.setCellStyle(cellStyle1);
    cell = row.createCell((short) 0);
    cell.setCellStyle(style);
    cell = row.createCell((short) 2);
    cell.setCellStyle(style);
    cell = row.createCell((short) 4);
    cell.setCellStyle(style);
    cell = row.createCell((short) 6);
    cell.setCellStyle(style);
    cell = row.createCell((short) 7);
    cell.setCellStyle(style);
    cell = row.createCell((short) 8);
    cell.setCellStyle(style);
    cell = row.createCell((short) 9);
    cell.setCellStyle(style);
    cell = row.createCell((short) 10);
    cell.setCellStyle(style);
    cell = row.createCell((short) 11);
    cell.setCellStyle(style);
    cell = row.createCell((short) 12);
    cell.setCellStyle(style);
    cell = row.createCell((short) 13);
    cell.setCellStyle(style);
    
 row = sheet.createRow((short) list.size()+6);
    cell = row.createCell((short) 0);
    cell.setCellStyle(cellStyle1);
    cell.setCellValue("注：请于送完货后，务必电话联系配送办公室电话57038818，否则会影响结算。提货之后，将派车单及时返回当办公室");
//    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
  
    
  row = sheet.createRow((short) list.size()+7);
        cell = row.createCell((short) 0);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("备货人签字：");
//        cell.setCellStyle(cellStyle1);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 2);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("发货人签字：");
//        cell.setCellStyle(cellStyle1);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 4);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("承运人签字：");
//        cell.setCellStyle(cellStyle1);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 6);
        cell.setCellStyle(cellStyle1);
        cell.setCellValue("联系电话：");
//        cell.setCellStyle(cellStyle1);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    
        
   sheet.autoSizeColumn((short)0); //调整第一列宽度
   sheet.autoSizeColumn((short)1); //调整第二列宽度
   sheet.autoSizeColumn((short)2); //调整第三列宽度
   sheet.autoSizeColumn((short)3); //调整第四列宽度
   sheet.autoSizeColumn((short)4); //调整第四列宽度
   sheet.autoSizeColumn((short)5); //调整第四列宽度
   sheet.autoSizeColumn((short)6); //调整第四列宽度
   sheet.autoSizeColumn((short)7); //调整第四列宽度
   sheet.autoSizeColumn((short)8); //调整第四列宽度
   sheet.autoSizeColumn((short)9); //调整第四列宽度
   sheet.autoSizeColumn((short)10); //调整第四列宽度
   sheet.autoSizeColumn((short)11); //调整第四列宽度
   sheet.autoSizeColumn((short)12); //调整第四列宽度
   sheet.autoSizeColumn((short)13); //调整第四列宽度
   


	   try {
	        FileOutputStream fileOut = new FileOutputStream(path);
	        workbook.write(fileOut);
	        fileOut.close();
	    } catch (Exception e) {
	        System.out.println(e.toString());
	    }
	}
/**
 * 
 * 方法功能说明： 不带状态导出 
 * 创建：2016年2月24日 by Zzc   
 * 修改：日期 by 修改者  
 * 修改内容：  
 * @参数： @param list
 * @参数： @param path      
 * @return void     
 * @throws
 */
public static void excleOrderSta(List list,String path) {
    // 创建新的Excel 工作簿
   HSSFWorkbook workbook = new HSSFWorkbook();
    
    // 在Excel工作簿中建一工作表，其名为缺省值, 也可以指定Sheet名称
   HSSFSheet sheet = workbook.createSheet();
    //HSSFSheet sheet = workbook.createSheet("SheetName"); 
    
    // 用于格式化单元格的数据
   HSSFDataFormat format = workbook.createDataFormat();
    
    // 创建新行(row),并将单元格(cell)放入其中. 行号从0开始计算.
    HSSFRow row = sheet.createRow((short) 100);
    
    // 设置字体
   HSSFFont font = workbook.createFont();
    font.setFontHeightInPoints((short) 24); //字体高度
   font.setColor(HSSFFont.COLOR_NORMAL); //字体颜色
   font.setFontName("宋体"); //字体
   font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //宽度
   font.setItalic(false); //是否使用斜体
//   font.setStrikeout(true); //是否使用划线
   
   HSSFFont font1 = workbook.createFont();
   font1.setFontHeightInPoints((short) 14); //字体高度
   font1.setColor(HSSFFont.COLOR_NORMAL); //字体颜色
   font1.setFontName("宋体"); //字体
   font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //宽度
   font1.setItalic(false); //是否使用斜体
//   font1.setStrikeout(true); //是否使用划线

   HSSFFont font2 = workbook.createFont();
   font2.setFontHeightInPoints((short) 14); //字体高度
   font2.setColor(HSSFFont.COLOR_NORMAL); //字体颜色
   font2.setFontName("宋体"); //字体
   font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //宽度
   font2.setItalic(false); //是否使用斜体
//   font1.setStrikeout(true); //是否使用划线
   
   
   // 设置单元格类型
   HSSFCellStyle cellStyle = workbook.createCellStyle();
    cellStyle.setFont(font);
    cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平布局：居中
    cellStyle.setWrapText(true);
    
    HSSFCellStyle cellStyle1 = workbook.createCellStyle();
    cellStyle1.setFont(font1);
    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT); //水平布局：居中
    font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL); //宽度
    cellStyle1.setWrapText(true);

    
    HSSFCellStyle style = workbook.createCellStyle();   
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 下边框   
    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框   
    style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框   
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
    cellStyle1.setFont(font2);
    cellStyle1.setAlignment(HSSFCellStyle.ALIGN_LEFT); //水平布局：居中
    cellStyle1.setWrapText(true);
    
    // 添加单元格注释
   // 创建HSSFPatriarch对象,HSSFPatriarch是所有注释的容器.
    HSSFPatriarch patr = sheet.createDrawingPatriarch();
    // 定义注释的大小和位置,详见文档
   HSSFComment comment = patr.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short)4, 2, (short) 6, 5));
    // 设置注释内容
   comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
    // 设置注释作者. 当鼠标移动到单元格上是可以在状态栏中看到该内容.
    comment.setAuthor("Xuys.");
    
    // 创建单元格
   HSSFCell cell = row.createCell((short) 0);
	 
	 
	
//    HSSFRichTextString hssfString = new HSSFRichTextString("朗宜行PC20160125-01(AM)");
//    cell.setCellValue(hssfString);//设置单元格内容
//   cell.setCellStyle(cellStyle);//设置单元格样式
//   cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//   cell.setCellComment(comment);//添加注释
	/**
    * 合并单元格，使用addMergedRegoin函数.
    */
   sheet.addMergedRegion(new Region((short)0, (short)0, (short)0, (short)10));
   sheet.addMergedRegion(new Region((short)1, (short)0, (short)1, (short)10));
   
//     CellRangeAddress cellRangeAddr5 = new CellRangeAddress(list.size()+4,list.size()+5,0,0);
//	 MyExcelTest.setRegionBorder(1, cellRangeAddr5, sheet, workbook); //合并单元格，设置边框
//	 sheet.addMergedRegion(cellRangeAddr5);
	 
//	 CellRangeAddress cellRangeAddr5_5 = new CellRangeAddress(list.size()+4,list.size()+4,6,8);
//	 MyExcelTest.setRegionBorder(1, cellRangeAddr5_5, sheet, workbook); //合并单元格，设置边框
//	 sheet.addMergedRegion(cellRangeAddr5_5);
	 
//	 CellRangeAddress cellRangeAddr6_5 = new CellRangeAddress(list.size()+5,list.size()+5,6,8);
//	 MyExcelTest.setRegionBorder(1, cellRangeAddr6_5, sheet, workbook); //合并单元格，设置边框
//	 sheet.addMergedRegion(cellRangeAddr6_5);
////	 
//	 CellRangeAddress cellRangeAddr7_5 = new CellRangeAddress(list.size()+6,list.size()+6,0,10);
//	 MyExcelTest.setRegionBorder(1, cellRangeAddr7_5, sheet, workbook); //合并单元格，设置边框
//	 sheet.addMergedRegion(cellRangeAddr7_5);
	 
	 
	 
  
   //格式化数据
   row = sheet.createRow((short) 0);
    cell = row.createCell((short) 0);
    cell.setCellValue("星火" +DateUtils.getCurrDateStr_());
//    cellStyle = workbook.createCellStyle();
    cell.setCellStyle(cellStyle);//指定单元格格式：数值、公式或字符串
//    cellStyle.setDataFormat(format.getFormat("0.0"));
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    
  row = sheet.createRow((short) 1);
    cell = row.createCell((short) 0);
    cell.setCellValue("办公室电话： 57038818   18610399267");
    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    
  row = sheet.createRow((short) 2);
    cell = row.createCell((short) 0);
    cell.setCellValue("生成人： ");
    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 3);
    cell.setCellValue("管理：");
    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    
    /******以下是取得数据********/
  row = sheet.createRow((short) 3);
    cell = row.createCell((short) 0);
    cell.setCellStyle(style);
    cell.setCellValue("序号 ");
//    cell.setCellStyle(cellStyle1);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 1);
    cell.setCellValue("激活码");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 2);
    cell.setCellValue("关联图书");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 3);
    cell.setCellValue("生成日期");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 4);
    cell.setCellValue("使用时间");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 5);
    cell.setCellValue("使用地区");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 6);
    cell.setCellValue("使用账号");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 7);
    cell.setCellValue("使用次数");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 8);
    cell.setCellValue("生成人");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 9);
    cell.setCellValue("是否使用");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    cell = row.createCell((short) 10);
    cell.setCellValue("是否删除");
    cell.setCellStyle(style);
    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//    cell = row.createCell((short) 11);
////    cell.setCellValue("");
//    cell.setCellStyle(style);
//    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
    
    for (int i = 0; i < list.size(); i++) {
    	BookActivationCodeActionForm vo = (BookActivationCodeActionForm) list.get(i);
      row = sheet.createRow((short) i+4);
        cell = row.createCell((short) 0);
        cell.setCellStyle(style);
        cell.setCellValue(i+1);
//        cell.setCellStyle(cellStyle1);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 1);
        cell.setCellValue(vo.getCode());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 2);
        cell.setCellValue(vo.getProductId());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 3);
        cell.setCellValue(vo.getCreatetime());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 4);
        cell.setCellValue(vo.getUseTime());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 5);
        cell.setCellValue(vo.getUseRegion());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 6);
        cell.setCellValue(vo.getUserId());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 7);
        cell.setCellValue(vo.getUseNum());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 8);
        cell.setCellValue(vo.getSysUserId());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 9);
        cell.setCellValue(vo.getIsUse());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
        cell = row.createCell((short) 10);
        cell.setCellValue(vo.getIsDel());
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
	}
    
    /*******以下是底部部门*******/
//   row = sheet.createRow((short) list.size()+4);
//    cell = row.createCell((short) 0);
//    cell.setCellStyle(cellStyle1);
//    cell.setCellValue("注： ");
//    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//   
//    cell.setCellStyle(style);
//    cell.setCellValue("激活码位数");
////    cell.setCellStyle(cellStyle1);
//    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//    cell = row.createCell((short) 3);
//    cell.setCellStyle(style);
//    cell.setCellValue("激活码数量");
////    cell.setCellStyle(cellStyle1);
//    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//    cell = row.createCell((short) 5);
//    cell.setCellStyle(style);
//    cell.setCellValue("关联图书");
////    cell.setCellStyle(cellStyle1);
//    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//    
//    cell = row.createCell((short) 2);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 4);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 6);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 7);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 8);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 9);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 10);
//    cell.setCellStyle(style);
//    
//  row = sheet.createRow((short) list.size()+5);
//    cell = row.createCell((short) 1);
//    cell.setCellStyle(style);
//    cell.setCellValue("最远端收费价格");
////    cell.setCellStyle(cellStyle1);
//    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//    cell = row.createCell((short) 3);
//    cell.setCellStyle(style);
//    cell.setCellValue("改点个数");
////    cell.setCellStyle(cellStyle1);
//    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//    cell = row.createCell((short) 5);
//    cell.setCellStyle(style);
//    cell.setCellValue("其他");
//    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
////    cell.setCellStyle(cellStyle1);
//    cell = row.createCell((short) 0);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 2);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 4);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 6);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 7);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 8);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 9);
//    cell.setCellStyle(style);
//    cell = row.createCell((short) 10);
//    cell.setCellStyle(style);
//    
// row = sheet.createRow((short) list.size()+6);
//    cell = row.createCell((short) 0);
//    cell.setCellStyle(cellStyle1);
//    cell.setCellValue("注：");
////    cell.setCellStyle(cellStyle1);
//    cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//  
//    
//  row = sheet.createRow((short) list.size()+7);
//        cell = row.createCell((short) 0);
//        cell.setCellStyle(cellStyle1);
//        cell.setCellValue("");
////        cell.setCellStyle(cellStyle1);
//        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//        cell = row.createCell((short) 2);
//        cell.setCellStyle(cellStyle1);
//        cell.setCellValue("");
////        cell.setCellStyle(cellStyle1);
//        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//        cell = row.createCell((short) 4);
//        cell.setCellStyle(cellStyle1);
//        cell.setCellValue("");
////        cell.setCellStyle(cellStyle1);
//        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//        cell = row.createCell((short) 6);
//        cell.setCellStyle(cellStyle1);
//        cell.setCellValue("");
////        cell.setCellStyle(cellStyle1);
//        cell.setCellType(HSSFCell.CELL_TYPE_STRING);//指定单元格格式：数值、公式或字符串
//    
        
   sheet.autoSizeColumn((short)0); //调整第一列宽度
   sheet.autoSizeColumn((short)1); //调整第二列宽度
   sheet.autoSizeColumn((short)2); //调整第三列宽度
   sheet.autoSizeColumn((short)3); //调整第四列宽度
   sheet.autoSizeColumn((short)4); //调整第四列宽度
   sheet.autoSizeColumn((short)5); //调整第四列宽度
   sheet.autoSizeColumn((short)6); //调整第四列宽度
   sheet.autoSizeColumn((short)7); //调整第四列宽度
   sheet.autoSizeColumn((short)8); //调整第四列宽度
   sheet.autoSizeColumn((short)9); //调整第四列宽度
   sheet.autoSizeColumn((short)10); //调整第四列宽度


	   try {
	        FileOutputStream fileOut = new FileOutputStream(path);
	        workbook.write(fileOut);
	        fileOut.close();
	    } catch (Exception e) {
	        System.out.println(e.toString());
	    }
	}

}
