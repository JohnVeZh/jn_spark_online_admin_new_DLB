package com.easecom.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DateUtil;

import com.easecom.system.exception.SystemException;

public class ExcelImport {

	private String excelFilePath = "";
	/**数据库表名称 */
	private String DATABASETABLENAME;
	/**数据库类型	 */
	private String DATABASETYPE = "";
	/**从配置文件读取的数据库字段，一个字符串形式的*/
	private String DATABASEFIELDS = "";
	/**有效数据开始行 */
	private String READEXCELSTARTROWS = "";
	/**有效数据开始列 */
	private String READEXCELSTARTCOLS = "";
	private String READEXCELTITLE = "";
	/**excel的标题行 行号*/
	private String READEXCELTITLEROWS = "";
	/**数据库字段信息， key:数据库字段，value:数据库字段信息，如：NAME_CHINA,VARCHAR2,200,Y,,,String,商标名称(中);*/
	private LinkedHashMap<String, String[]> dataBaseFiledsMap;
	/**数据库标题Map，将数据库字段信息的最后一个值变为了数字，当前的列号 */
	private LinkedHashMap<Integer, String[]> excelTitlesRuleMap;
	/**导入SQL*/
	private String importSQL = "";
	private Connection conn = null;
	private String DATABASETABLENOTREPEATFILEDS;
	private HttpServletResponse response;
	/**检查是否有重复的行 */
	private String DATABASETABLEEXPORTREPEATLINE;
	private String DATABASETABLEEXPORTREPEATPAGE;
	/**读取Excel结束行 */
	private String READEXCELENDROWS;
	/**读取Excel结束列 */
	private String READEXCELENDCOLS;
	private String EXPORTFILENAME;
	private String  DELCOLUNM;   //是否存在假删除
	private String  DELCOLUNMVALUE;   //是否存在假删除
	private HSSFWorkbook wb;
	
	/**主键*/
	private String PRIMARYKEY = null;
	/**主键是否自动增长	 */
	private String AUTOGENERATION = null;
	/**
	 * 构造方法,通过获取properties初始化相应参数
	 * */
	/**
	 * 
	 */
	public ExcelImport(InputStream stream, Connection conn1, HttpServletResponse response1,String propertie) throws SystemException {
		//excelFilePath = excelFilePath1;
		 
		conn = conn1;
		response = response1;
		// 读取配置文件属性
		initProperties(propertie);
		// 获取数据库字段值
		getDataBaseFileds();
		try {
			// 获取工作薄
			wb = readExcelWorkbook(stream);
			// 将配置的数据库字段对照excel的标题进行排序
			getExcelFiledsIndex();
			// 拼接插入sql
			writeDataBaseSql();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			throw e;
		}
	}

	/**
	 * 读取Excel内容
	 * */
	public void readExcel() throws IOException, SQLException {
		boolean boo = false;
		HSSFWorkbook writeWb = null;
		HSSFSheet writeSheet = null;
		int beforeLines = 1;// 当前行数(写Excel)
		ExcelInfoBean excelInfoBean = new ExcelInfoBean();
		ExcelExport excelExport = null;
	//	HSSFWorkbook wb = readExcelWorkbook(excelFilePath);
		if ("0".equals(DATABASETABLEEXPORTREPEATLINE)) {
			try {
				if (null != response) {
					response.reset(); // 清空输出流
					if (response.isCommitted()) {
					}
					writeWb = new HSSFWorkbook(); // excel文件,一个excel文件包含多个表
					// wb.setSheetName(0, "",
					// HSSFWorkbook.ENCODING_UTF_16); //
					// 设置sheet中文编码

					HSSFSheet sheet = wb.getSheetAt(0); // 读取excel的sheet，0表示读取第一个、1表示第二个.....
					// ///////
					// 取出sheet中的表头行数据
					HSSFRow row = sheet.getRow(Integer.parseInt(READEXCELTITLEROWS)); 
					// 获取Excel文件的有效列数
					int cellNum = getXlsValidCols(row);
					String[] excelTitle = new String[cellNum];
					// 获取该行中总共有多少列数据row.getLastCellNum()
					Iterator<Entry<String, String[]>> it = dataBaseFiledsMap.entrySet().iterator();
					String[] filedsInfo = null;
					int i = 0;
					while (it.hasNext()) {
						Entry<String, String[]> e = it.next();
						filedsInfo = e.getValue();
						int colsIndex = Integer.parseInt(filedsInfo[filedsInfo.length - 1]);
						HSSFCell cell = row.getCell((short) colsIndex); // 获取该行中的一个单元格对象
						excelTitle[i++] = cell.getStringCellValue();
					}
					excelInfoBean.setExTitle(excelTitle);
					excelExport = new ExcelExport(excelInfoBean);
					// 设置字体等样式
					writeSheet = writeWb.createSheet();
					excelExport.setFont(writeWb);
					excelExport.setCellType(writeWb);
					excelExport.createFirstTitle(writeSheet);

				}
			} catch (IllegalStateException ex3) {
				ex3.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 获取sheet中总共有多少行数据sheet.getPhysicalNumberOfRows()
		for (int sheetNumber = 0; sheetNumber < wb.getNumberOfSheets(); sheetNumber++) {
			HSSFSheet sheet = wb.getSheetAt(sheetNumber); // 读取excel的sheet，0表示读取第一个、1表示第二个.....
			int readRows = Integer.parseInt(READEXCELSTARTROWS);// 有效数据开始行
			int readEndRows = 0;
			// 获取要读取的行数
			if (null != READEXCELENDROWS && !"".equals(READEXCELENDROWS)) {
				readEndRows = Integer.parseInt(READEXCELENDROWS);
			} else {
				readEndRows = sheet.getPhysicalNumberOfRows();
			}

			for (int i = 0; i < readEndRows; i++) {// 循环取有效数据(循环条件sheet表中的有效行数)
				HSSFRow row = sheet.getRow(readRows); // 取出sheet中的某一行数据
				readRows++;
				if (row != null) {
					LinkedHashMap<Object, Object> sqlParam = new LinkedHashMap<Object, Object>();
					Iterator<Entry<String, String[]>> it = dataBaseFiledsMap.entrySet().iterator();
					String[] filedsInfo = null;
					while (it.hasNext()) {
						Entry<String, String[]> e = it.next();
						String filedsName = e.getKey();
						filedsInfo = e.getValue();
						int colsIndex = Integer.parseInt(filedsInfo[filedsInfo.length - 1]);
						
						Object value=null;
						HSSFCell cell = row.getCell((int) colsIndex); // 获取该行中的一个单元格对象
						if(null!=cell){
							// 判断单元格数据类型，这个地方值得注意：当取某一行中的数据的时候，需要判断数据类型，否则会报错
							// System.out.println("列" + colsIndex + "行" +
							// (readRows));
//							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							value = this.getStringCellValue(cell, filedsInfo);
						}
						sqlParam.put(filedsName, value);
						// 校验不重复列
						if (filedsInfo[0].equalsIgnoreCase(DATABASETABLENOTREPEATFILEDS)) {
							boo = this.validateNotRepeatFileds(filedsInfo, value);
						}
						// 校验不能为空的列
						if ("N".equals(filedsInfo[3].trim())) {
							if (null == value || "".equals(value)) {
								boo = true;
							}
						}
					}
					if (!boo) {
						boolean flag = this.writeDataBaseOper(importSQL, sqlParam);
						if(!flag){
							if ("0".equals(DATABASETABLEEXPORTREPEATLINE)) {
								if (beforeLines > Integer.parseInt(DATABASETABLEEXPORTREPEATPAGE)) {
									beforeLines = 1;
									writeSheet = writeWb.createSheet();
									excelExport.createFirstTitle(writeSheet);
									excelExport.invoke(sqlParam, beforeLines++, writeSheet);
								} else {
									excelExport.invoke(sqlParam, beforeLines++, writeSheet);
								}

							}
						}
						boo = false;
					} else {
						if ("0".equals(DATABASETABLEEXPORTREPEATLINE)) {
							if (beforeLines > Integer.parseInt(DATABASETABLEEXPORTREPEATPAGE)) {
								beforeLines = 1;
								writeSheet = writeWb.createSheet();
								excelExport.createFirstTitle(writeSheet);
								excelExport.invoke(sqlParam, beforeLines++, writeSheet);
							} else {
								excelExport.invoke(sqlParam, beforeLines++, writeSheet);
							}

						}
					}
				}
			}
		}
		if ("0".equals(DATABASETABLEEXPORTREPEATLINE)) {
			try {
				HSSFSheet sheet1 = writeWb.getSheetAt(0);
				if(sheet1.getPhysicalNumberOfRows()>1){
					// 导出
					OutputStream out = null;
					response.setContentType("application/x-msdownload"); // 设置生成的文件类型
					response
							.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(EXPORTFILENAME, "UTF-8"));
					out = response.getOutputStream(); // 取得输出流
					
					writeWb.write(out); // 写入Excel
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalStateException ex3) {
				ex3.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取有效列数
	 * @param row
	 * @return
	 */
	private int getXlsValidCols(HSSFRow row) {
		int cellNum = 0;
		if (null != READEXCELENDCOLS && !"".equals(READEXCELENDCOLS)) {
			cellNum = Integer.parseInt(READEXCELENDCOLS);
		} else {
			cellNum = row.getLastCellNum() - 1;
		}
		return cellNum;
	}

	private void writeExcelFile() {

	}

	/**
	 * 
	 * @Description:   需要执行的sql
	 * @param sql
	 * @param param
	 * @throws SQLException  
	 * @author: 窦恩虎
	 * @date Mar 2, 2012 2:44:43 PM
	 */
	private boolean writeDataBaseOper(String sql, LinkedHashMap<Object, Object> param) throws SQLException {
		PreparedStatement pstmt = null;
		boolean flag = false;
		ResultSet rs = null;
		
		try {
			if(sql!=null && !"".equals(sql)){
				
				if(null!=this.PRIMARYKEY && !"".equals(this.PRIMARYKEY) && !"1".equals(this.AUTOGENERATION)){
					sql = sql.substring(0,sql.indexOf(")"))+",ID"+sql.substring(sql.indexOf(")"));
					sql =  sql.substring(0,sql.indexOf("?")+1)+",?"+sql.substring(sql.indexOf("?")+1);
					 
					if("uuid".equals(AUTOGENERATION))
						param.put(PRIMARYKEY, IdGenerator.GenerateUUID());
				}
			}
			
//			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt = addPreparedStatementParam(pstmt, param);
			pstmt.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		} finally {
			this.free(rs, pstmt, null);
		}
		return flag;
	}
	/** 
	 * 关闭数据库操作流
	 * 
	 * **/
	private void free(final ResultSet rs, final PreparedStatement pstmt, final Connection conn) {
		try {
			if (null != rs) {
				rs.close();
			}
			if (null != pstmt) {
				pstmt.close();
			}
			if (null != conn) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 对PreparedStatement进行参数赋值
	 * 
	 * **/
	private PreparedStatement getPreparedStatementParam(final PreparedStatement pstmt,
			final LinkedHashMap<Object, Object> param) {
		try {
			if (null != param && param.size() > 0) {
				Iterator<Entry<Object, Object>> it = param.entrySet().iterator();
				int i = 1;
				while (it.hasNext()) {
					Entry<Object, Object> e = it.next();
					Object value = e.getValue();
					pstmt.setObject(i, value);
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	/**
	 * 
	 * @Description: 设置参数
	 * @param pstmt
	 * @param param
	 * @return  
	 * @author: 窦恩虎
	 * @date Mar 2, 2012 2:12:37 PM
	 */
	private PreparedStatement addPreparedStatementParam(final PreparedStatement pstmt,
			final LinkedHashMap<Object, Object> param) {
		try {
			if (null != param && param.size() > 0) {
				Iterator<Entry<Object, Object>> it = param.entrySet().iterator();
				int i = 1;
				while (it.hasNext()) {
					Entry<Object, Object> e = it.next();
					Object value = e.getValue();
					pstmt.setObject(i, value);
					i++;
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return pstmt;
	}
	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 * @throws Exception
	 */
	private Object getStringCellValue(HSSFCell cell, String[] filedsInfo) {
		Object strCell = "";
		try {
			String database_column_type = filedsInfo[filedsInfo.length - 2].toUpperCase();
			// 格式转换出错
			if ("DOUBLE".equals(database_column_type) || "FLOAT".equals(database_column_type)
					|| database_column_type.equals("NUMBER")) {
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				strCell = cell.getNumericCellValue();
			} else if (database_column_type.equals("STRING")) {
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				strCell = cell.getStringCellValue();
			} else if (database_column_type.equals("DATE")) {
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
					Date dt = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
					if (filedsInfo[1].indexOf("CHAR") > -1) {
						strCell = dateformat.format(dt);
					} else {
						strCell = dt;
					}
				} else {
					strCell = cell.getNumericCellValue();
				}
			} else if (database_column_type.equals("INT")) {
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				strCell = cell.getNumericCellValue();
			} else {
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				strCell = cell.getStringCellValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("读取第" + cell.getRowIndex() + "行,第" + cell.getColumnIndex() + "列时出现异常" + e);
		}
		return strCell;
	}

	/**
	 *读取Excel中的HSSFWorkbook
	 * 
	 * @param filePath
	 *            文件路径
	 * @return HSSFWorkbook Excel文件内容
	 */

	private HSSFWorkbook readExcelWorkbook(String filePath) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(filePath); // 根据excel文件路径创建文件流
		POIFSFileSystem fs = new POIFSFileSystem(fis); // 利用poi读取excel文件流
		HSSFWorkbook wb = new HSSFWorkbook(fs); // 读取excel工作簿
		return wb;
	}
	/**
	 * 
	 * 根据request.getInputStream()创建EXCEL对象
	 * @param request
	 * @return HSSFWorkbook 
	 * @throws FileNotFoundException
	 * @throws IOException  
	 * @author: 窦恩虎
	 * @date Mar 1, 2012 4:31:16 PM
	 */
	private HSSFWorkbook readExcelWorkbook(InputStream	stream) throws FileNotFoundException, IOException {
	//	ServletInputStream fis = request.getInputStream(); // 根据excel文件路径创建文件流
		//POIFSFileSystem fs = new POIFSFileSystem(stream); // 利用poi读取excel文件流
		 wb = new HSSFWorkbook(stream); // 读取excel工作簿
		return wb;
	}
	/**
	 * 拼接写数据库sql
	 * 
	 * @return insert sql
	 * **/
	public void writeDataBaseSql() {
		if (DATABASETYPE.toUpperCase().equals("ORACLE")) {
			StringBuffer filedsSql = new StringBuffer();
			StringBuffer filedsValueSql = new StringBuffer();
			filedsSql.append("INSERT INTO " + DATABASETABLENAME + " (");
			// 遍历数据库字段信息
			Iterator<Entry<String, String[]>> it = dataBaseFiledsMap.entrySet().iterator();
			String[] filedsInfo = null;
			while (it.hasNext()) {
				// 获取某一个字段entry
				Entry<String, String[]> e = it.next();
				// 获取字段信息
				filedsInfo = e.getValue();
				// 附加到插入字段sql中去
				filedsSql.append(filedsInfo[0] + ",");
				// 取得字段值sql
				filedsValueSql.append(this.getDataBaseFiledsTypeFunction(filedsInfo[1]) + ",");
			}
			// 将最后的分号去掉，拼接插入sql
			importSQL = filedsSql.toString().substring(0, filedsSql.length() - 1) + " ) values ( "
					+ filedsValueSql.toString().substring(0, filedsValueSql.length() - 1) + ")";
			System.out.println(importSQL);
		} else if (DATABASETYPE.toUpperCase().equals("MYSQL")) {
			StringBuffer filedsSql = new StringBuffer();
			StringBuffer filedsValueSql = new StringBuffer();
			filedsSql.append("INSERT INTO " + DATABASETABLENAME + " (");
			// 遍历数据库字段信息
			Iterator<Entry<String, String[]>> it = dataBaseFiledsMap.entrySet().iterator();
			String[] filedsInfo = null;
			while (it.hasNext()) {
				// 获取某一个字段entry
				Entry<String, String[]> e = it.next();
				// 获取字段信息
				filedsInfo = e.getValue();
				// 附加到插入字段sql中去
				filedsSql.append(filedsInfo[0] + ",");
				// 取得字段值sql
				filedsValueSql.append(this.getDataBaseFiledsTypeFunction(filedsInfo[1]) + ",");
			}
			// 将最后的分号去掉，拼接插入sql
			importSQL = filedsSql.toString().substring(0, filedsSql.length() - 1) + " ) values ( "
					+ filedsValueSql.toString().substring(0, filedsValueSql.length() - 1) + ")";
//			System.out.println(importSQL);
		}
	}

	/**
	 * 将配置的数据库字段对照excel的标题进行排序
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void getExcelFiledsIndex() throws FileNotFoundException, IOException, SystemException {
		if (READEXCELTITLE.equals("2")) {
			//HSSFWorkbook wb = readExcelWorkbook(excelFilePath);
			// 获取sheets
			int sheets = wb.getNumberOfSheets();
			// 循环遍历每一个sheets
			for (int i = 0; i < sheets; i++) {
				// 创建一个excel标题的列号
				excelTitlesRuleMap = new LinkedHashMap<Integer, String[]>();
				// 获取sheet
				HSSFSheet sheet = wb.getSheetAt(i); // 读取excel的sheet，0表示读取第一个、1表示第二个.....
				// 取出sheet中的某一行数据
				HSSFRow row = sheet.getRow(Integer.parseInt(READEXCELTITLEROWS)); 
				if (row != null) {
					
					if(Integer.parseInt(READEXCELENDCOLS)==row.getLastCellNum()){
						// 循环遍历每一个列
						for (int j = 0; j < row.getLastCellNum(); j++) {
							// 获取该行中的一个单元格对象
							HSSFCell cell = row.getCell((short) j); 
							// 获取单元格的值
							String cellValue = cell.getStringCellValue();
							// 获取数据库字段迭代器
							Iterator<Entry<String, String[]>> it = dataBaseFiledsMap.entrySet().iterator();
							String[] filedsInfo = null;
							// 循环遍历数据库字段
							while (it.hasNext()) {
								// 获取一个数据库字段
								Entry<String, String[]> e = it.next();
								filedsInfo = e.getValue();
								// 如果单元格的值等于数据库字段数组信息的最后一个值，则将最后一个值变成当前循环的索引值放进excelTitlesRuleMap中
								if (cellValue.equals(filedsInfo[filedsInfo.length - 1])) {
									filedsInfo[filedsInfo.length - 1] = String.valueOf(j);
									excelTitlesRuleMap.put(j, filedsInfo);
									break;
								}
							}
						}
					}
					if(Integer.parseInt(READEXCELENDCOLS)!=excelTitlesRuleMap.size())
						throw new SystemException("数据格式不对");
				}
			}
		}
	}

	/***
	 * 校验不可重复字段(xls单元列)
	 * 
	 * @throws SQLException
	 * */
	private boolean validateNotRepeatFileds(String[] filedsInfo, Object value) throws SQLException {
		boolean boo = false;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		LinkedHashMap<Object, Object> param = new LinkedHashMap<Object, Object>();
		try {
			String sql = this.getValidateFiledsNotRepeatSQL(filedsInfo);
			param.put(filedsInfo[0], value);
			pstmt = conn.prepareStatement(sql);
			pstmt = getPreparedStatementParam(pstmt, param);
			rst = pstmt.executeQuery();
			while (rst.next()) {
				int count_ = rst.getInt(1);
				if (count_ > 0) {
					boo = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			this.free(null, pstmt, null);
		}
		return boo;
	}

	/***
	 * 拼接验证重复列的SQL
	 * 
	 * */
	private String getValidateFiledsNotRepeatSQL(String[] filedsInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(1) FROM ");
		sql.append(DATABASETABLENAME);
		sql.append(" WHERE " + filedsInfo[0] + " = ");
		sql.append(this.getDataBaseFiledsTypeFunction(filedsInfo[1]));
		if(!"".equals(DELCOLUNM)&&!"".equals(DELCOLUNMVALUE)){
			
			sql.append(" and " + DELCOLUNM + " = '"+DELCOLUNMVALUE+"'");
		}
		return sql.toString();
	}

	/**
	 * 从配置文件properties中获取字段信息,放入dataBaseFiledsMap对象中
	 * 
	 */
	public void getDataBaseFileds() {
		String[] fileds = DATABASEFIELDS.split(";");
		if (null != fileds && fileds.length > 0) {
			dataBaseFiledsMap = new LinkedHashMap<String, String[]>();
			// 数据库字段,数据库字段类型,长度,是否可为空,代码转换对应的class,代码转换对应的方法,Excel表对应列
			//REGISTRATION_NUMBER,VARCHAR2,32,Y,,,String,注册号;
			for (int i = 0; i < fileds.length; i++) {
				String[] filedsInfo = fileds[i].split(",");
				dataBaseFiledsMap.put(filedsInfo[0].toUpperCase(), filedsInfo);
			}
		}
	}

	/***
	 * 
	 * 取得数据库字段类型转换函数
	 * 
	 * */
	private String getDataBaseFiledsTypeFunction(String database_column_type) {
		if ("DOUBLE".equals(database_column_type.toUpperCase()) || "FLOAT".equals(database_column_type.toUpperCase())) {
			return "TRIM(?)";
		} else if (database_column_type.indexOf("VARCHAR2") > -1 || database_column_type.indexOf("CHAR") > -1) {
			return "?";
		} else if (database_column_type.indexOf("DATE") > -1) {
			return "TO_DATE(?,'YYYY-MM-DD')";
		} else if ((database_column_type.indexOf("NUMBER") > -1 && database_column_type.indexOf(",") > -1)) {
			return "TRIM(?)";
		} else if (database_column_type.indexOf("NUMBER") > -1) {
			return "TRIM(?)";
		} else if (database_column_type.indexOf("INT") > -1) {
			return "TRIM(?)";
		} else {
			return "TRIM(?)";
		}
	}

	/**
	 * 记取配置参数
	 * **/
	private void initProperties(String propertie) {
		
		ResourceBundle rb = ResourceBundle.getBundle(propertie);
		DATABASETYPE = (rb.getString("dataBaseType").trim());
		DATABASEFIELDS = rb.getString("dataBaseFields").trim();
		READEXCELTITLE = rb.getString("readExcelTitle").trim();
		READEXCELTITLEROWS = rb.getString("readExcelTitleRows").trim();
		READEXCELSTARTROWS = rb.getString("readExcelStartRows").trim();
		READEXCELSTARTCOLS = rb.getString("readExcelStartCols").trim();
		DATABASETABLENAME = rb.getString("dataBaseTableName").trim();
		DATABASETABLENOTREPEATFILEDS = rb.getString("dataBaseTableNotRepeatFileds").trim();
		DATABASETABLEEXPORTREPEATLINE = rb.getString("dataBaseTableExportRepeatLine").trim();
		DATABASETABLEEXPORTREPEATPAGE = rb.getString("dataBaseTableExportRepeatPage").trim();
		READEXCELENDROWS = rb.getString("readExcelEndRows").trim();
		READEXCELENDCOLS = rb.getString("readExcelEndCols").trim();
		EXPORTFILENAME=rb.getString("exportFileName").trim();
		DELCOLUNM=rb.getString("delColumn").trim();
		DELCOLUNMVALUE=rb.getString("delColumnValue").trim();
		PRIMARYKEY=rb.getString("primaryKey").trim();
		AUTOGENERATION=rb.getString("autoGeneration").trim();
		
	}

	public static void main(String[] args) {

	}

}
