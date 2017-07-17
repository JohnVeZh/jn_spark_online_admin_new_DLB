/*
 * excel工具类，可以导出excel
 */

package com.easecom.common.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public final class ExcelUtils {
	private static LogUtils log = LogUtils.getLogger(ExcelUtils.class);

	public ExcelUtils() {
	}

	/**
	 * 导出excel对外接口
	 * 
	 * @param title
	 *          标题 如：2005年人力资源统计报表
	 * @param tableData
	 *          数据表数据 如：new String[][]{{"表头1","表头2"},{"aaa","bbb"}}
	 * @param exportFileName
	 *          导出后的文件名 如：hr.xls
	 * @param request
	 * @param response
	 */
	public static void exportExcelData(String title, String[][] tableData,
			String exportFileName, HttpServletRequest request,
			HttpServletResponse response) {
		response.reset();
		String fileName = "attachment;filename=\"" + exportFileName + "\";";
		response.setHeader("Content-disposition", fileName);
		response.setContentType("application/vnd.ms-excel");
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			exportExcel(title, tableData, response.getOutputStream(),
					wb);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void exportExcelData(String title, String[][][] tableData,
			String exportFileName, HttpServletRequest request,
			HttpServletResponse response, List sblxList, int sheetNum) {
		String fileName = "attachment;filename=\"" + exportFileName + "\";";
		response.setHeader("Content-disposition", fileName);
		response.setContentType("application/vnd.ms-excel");
		try {
			exportExcel(title, tableData, response.getOutputStream(), createWorkbook(
					sheetNum, sblxList), sheetNum);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}

	protected static void exportExcel(String title, String[][] tableData,
			OutputStream output, HSSFWorkbook workbook) throws Exception {
		HSSFSheet sheet = workbook.createSheet("导入失败的人员列表");
//		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillBackgroundColor((short) 55);
		HSSFFont hsFont = workbook.createFont();
		hsFont.setBoldweight((short) 700);
		String titles[] = tableData[0];
		titleStyle.setFont(hsFont);
		titleStyle.setAlignment((short) 2);
		HSSFRow row = sheet.createRow(1);

		//row.setHeight((short) 300);
		//row = sheet.createRow(3);
		//row.setHeight((short) 250);
		short i = 0;
		for (int forI = 0; forI < titles.length; forI++) {
			createCell(row, i, titles[forI], titleStyle);
			i++;
		}

		int rowCount = 2;

		for (int rowPos = 1; rowPos < tableData.length; rowPos++) {
			row = sheet.createRow(rowCount++);
			i = 0;
			for (int colPos = 0; colPos < tableData[rowPos].length; colPos++) {
				createCell(row, i, tableData[rowPos][colPos], null);
				i++;
			}
		}
		try {
			workbook.write(output);
		} catch (Exception e) {

		}
	}

	protected static void exportExcel(String title, String[][][] tableData,
			OutputStream output, HSSFWorkbook workbook, int sheetNum)
			throws Exception {
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillBackgroundColor((short) 55);
		HSSFFont hsFont = workbook.createFont();
		hsFont.setBoldweight((short) 700);
		titleStyle.setFont(hsFont);
		titleStyle.setAlignment((short) 2);

		for (int k = 0; k < sheetNum; k++) {
			HSSFSheet sheetk = workbook.getSheetAt(k);
			String titles[] = tableData[k][0];

			HSSFRow row = sheetk.createRow(1);
			row.setHeight((short) 300);
			row = sheetk.createRow(3);
			row.setHeight((short) 250);
			short i = 0;
			for (int forI = 0; forI < titles.length; forI++) {
				createCell(row, i, titles[forI], titleStyle);
				i++;
			}

			int rowCount = 4;
			for (int rowPos = 1; rowPos < tableData[k].length; rowPos++) {
				row = sheetk.createRow(rowCount++);
				i = 0;
				for (int colPos = 0; colPos < tableData[k][rowPos].length; colPos++) {
					createCell(row, i, tableData[k][rowPos][colPos], null);
					i++;
				}
			}
		}
		try {
			workbook.write(output);
		} catch (Exception e) {
			System.err.println("e====" + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	protected static HSSFCell createCell(HSSFRow row, short cellCount,
			String value, HSSFCellStyle titleStyle) {
		HSSFCell cell = row.createCell(cellCount);
		//cell.setEncoding((short) 1);
		if (titleStyle != null)
			cell.setCellStyle(titleStyle);
		cell.setCellValue(value);

		return cell;
	}

	public static HSSFWorkbook createWorkbook() throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		return wb;
	}

	public static HSSFWorkbook createWorkbook(int sheetCount) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();

		return wb;
	}

	@SuppressWarnings("unchecked")
	public static HSSFWorkbook createWorkbook(int sheetCount, List sblxList)
			throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		for (int i = 0; i < sheetCount; i++) {
			HSSFSheet si = wb.createSheet((String) sblxList.get(i));			 
			//wb.setSheetName(i, (String) sblxList.get(i), (short) 1);
		}

		return wb;
	}

}
