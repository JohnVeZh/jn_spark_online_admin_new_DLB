package com.easecom.common.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import oracle.sql.CLOB;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @Description:  导入导出excel 
 */
public class ExcelExport {
	private XSSFWorkbook xwb = null;
	private HSSFWorkbook wb = null;
	private String excelType = null;
	private FileInputStream excelFile = null;
	private ExcelInfoBean excelInfoBean;
	private Object[] exportSqlParam;
	private String exportSql;
	private HSSFCellStyle style;
	private HSSFFont font;
	private String databaseType;
	
	/**
	 * 构造函数
	 * @param excelInfoBean
	 * @throws Exception 
	 */
	public ExcelExport(ExcelInfoBean excelInfoBean) throws Exception {
		this.excelInfoBean = excelInfoBean;
		this.exportSqlParam = excelInfoBean.getSqlParam();
		this.exportSql = excelInfoBean.getSql();
		this.databaseType = SQLHelper.getDataBaseType(excelInfoBean.getConn());
	}

	/**
	 * 导出Excel
	 * 
	 * @throws IllegalAccessException
	 * @throws FileNotFoundException
	 * **/
	public void exportExcel(HttpServletResponse response) throws IllegalAccessException, FileNotFoundException {
		this.validateExcelInfoBean(excelInfoBean);
		if (null != excelInfoBean.getSql() && !"".equals(excelInfoBean.getSql())) {
			this.exportExcelBySql();
		} else {
			this.exportExcelByList();
		}

	}

	/**
	 * 导入Excel
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public void importExcel(String fileName) throws IOException {
		int index = -1;
		if (fileName == null || (index = fileName.lastIndexOf(".")) == -1 || index + 1 >= fileName.length()) {
			throw new IOException("文件名不能为空");
		}
		index = fileName.lastIndexOf(".");
		excelType = fileName.substring(index + 1).trim().toLowerCase();

		try {
			// 获取工作薄workbook
			excelFile = new FileInputStream(fileName);
			if (excelType.equals("xls")) {
				wb = new HSSFWorkbook(excelFile);
			} else if (excelType.equals("xlsx")) {
				xwb = new XSSFWorkbook(excelFile);
				// info = parseXlsxTypeFile(fileName);
			} else {
				throw new IOException("文件格式错误");
			}

		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			close();
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * 计算Excel文档中有效的记录数
	 * 
	 * @return int 记录数
	 * @throws IOException
	 */
	public int countRecord() throws IOException {
		int count = 0;
		if (null == excelType || "".equals(excelType)) {
			throw new IOException("文件格式错误");
		} else if (excelType.equals("xls")) {
			int temp = 0;
			for (int j = 0; j < wb.getNumberOfSheets(); j++) {
				HSSFSheet sheet = wb.getSheetAt(j);
				temp = sheet.getPhysicalNumberOfRows();
				count += temp > 0 ? temp - 1 : 0;
			}
		} else if (excelType.equals("xlsx")) {
			int temp = 0;
			for (int j = 0; j < xwb.getNumberOfSheets(); j++) {
				XSSFSheet sheet = xwb.getSheetAt(j);
				temp = sheet.getPhysicalNumberOfRows();
				count += temp > 0 ? temp - 1 : 0;
			}
		} else {
			throw new IOException("文件格式错误");
		}
		return count;
	}

	/**
	 * 获取单元格类型
	 * */
	@SuppressWarnings("unused")
	private String getCellString(final HSSFCell cell) {
		String result = null;
		if (null != cell) {
			// 单元格类型：Numeric:0,String:1,Formula:2,Blank:3,Boolean:4,Error:5
			int cellType = cell.getCellType();
			switch (cellType) {
			case HSSFCell.CELL_TYPE_STRING:
				result = cell.getRichStringCellValue().getString();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					result = String.valueOf(cell.getDateCellValue());
				} else {
					result = String.valueOf(cell.getNumericCellValue());
				}
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				result = String.valueOf(cell.getNumericCellValue());
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				result = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				result = null;
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				result = null;
				break;
			default:
				// System.out.println("枚举了所有类型");
				break;
			}
		}
		return result;
	}

	/**
	 * 获取单元格类型
	 * */
	@SuppressWarnings("unused")
	private String getCellString(final XSSFCell cell) {
		String result = null;
		if (cell != null) {
			// 单元格类型：Numeric:0,String:1,Formula:2,Blank:3,Boolean:4,Error:5
			int cellType = cell.getCellType();
			switch (cellType) {
			case XSSFCell.CELL_TYPE_STRING:
				result = cell.getRichStringCellValue().getString();
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					result = String.valueOf(cell.getDateCellValue());
				} else {
					result = String.valueOf(cell.getNumericCellValue());
					;
				}
				break;
			case XSSFCell.CELL_TYPE_FORMULA:
				result = String.valueOf(cell.getNumericCellValue());
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN:
				result = String.valueOf(cell.getBooleanCellValue());
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				result = null;
				break;
			case XSSFCell.CELL_TYPE_ERROR:
				result = null;
				break;
			default:
				// System.out.println("枚举了所有类型");
				break;
			}
		}
		return result;
	}

	/***************************************************************************
	 **************************************************************************/
	public String exportExcelBySql() {
		String returnMessage = "";
		HttpServletResponse response = this.excelInfoBean.getResponse();
		try {
			response.reset(); // 清空输出流
			if (response.isCommitted()) {
				return returnMessage = "已经提交";
			}
			wb = new HSSFWorkbook(); // excel文件,一个excel文件包含多个表
			// wb.setSheetName(HSSFCell.ENCODING_UTF_16); //
			// 设置sheet中文编码
			// 设置字体等样式
			setFont(wb);
			setCellType(wb);

			fillDataBySql(wb); // 该方法由具体调用时进行实现
			
			// 导出
			OutputStream sout = null;
			String filenameDownload = URLEncoder.encode(excelInfoBean.getFileName(), "UTF-8");
			filenameDownload = StringUtils.replaceString(filenameDownload, "+", "%20");  
			//response.setContentType("application/x-msdownload"); // 设置生成的文件类型
			response.setContentType("application/vnd.ms-excel");   
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filenameDownload);
			sout = response.getOutputStream(); // 取得输出流
			wb.write(sout);
			sout.flush();
		} catch (IllegalStateException ex3) {
			ex3.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMessage;
	}

	/**
	 * 
	 * @Description: 设置EXCEL的表头（例：姓名   性别   出生日期  ） 
	 * @param sheet
	 * @return  0 表示不设置表头信息 1表示设置了表头信息并且位于EXCEL的第一行
	 * @author: 窦恩虎
	 * @date Feb 29, 2012 5:11:51 PM
	 */
	public int createFirstTitle(HSSFSheet sheet) {
		HSSFRow row;
		HSSFCell cell;
		// 生成首行标题
		String[] firstLine = excelInfoBean.getExTitle();
		if (null != firstLine && firstLine.length > 0) {
			row = sheet.createRow(0); // 由HSSFSheet生成行
			row.setHeightInPoints((float) 15); // 设置行高
			for (short j = 0; j < firstLine.length; j++) {
				cell = row.createCell(j); // 由行生成单元格
				cell.setCellStyle(style); // 设置单元格样式
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16); //
				// 设置cell中文编码；
				cell.setCellValue(firstLine[j]);
				sheet.setColumnWidth(j, (short) (5000)); // 设置列宽
			}
			return 1;
		} else {
			return 0;
		}
	}

	public String exportExcelByList() {
		String returnMessage = "";
		HttpServletResponse response = this.excelInfoBean.getResponse();
		try {
			response.reset(); // 清空输出流
			if (response.isCommitted()) {
				return returnMessage = "已经提交";
			}
			wb = new HSSFWorkbook(); // excel文件,一个excel文件包含多个表

			// wb.setSheetName(0, "", HSSFWorkbook.ENCODING_UTF_16); //
			// 设置sheet中文编码

			// 设置字体等样式
			setFont(wb);
			
			setCellType(wb);

			this.fillDataByList(wb);

			// 导出
			OutputStream sout = null;
			//response.setContentType("application/x-msdownload"); // 设置生成的文件类型
			response.setContentType("application/vnd.ms-excel");   
			response.setHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(excelInfoBean.getFileName(), "UTF-8"));
			sout = response.getOutputStream(); // 取得输出流

			wb.write(sout); // 写入Excel
			sout.flush();
		} catch (IOException e) {
			returnMessage = "导出失败!";
			e.printStackTrace();
		} catch (IllegalStateException ex3) {
			ex3.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMessage;
	}

	/**
	 * 该方法由生成具体的表格时去实现(方法体内进行填充数据)
	 * 
	 * @param sheet
	 * @param style
	 */
	public void fillDataByList(HSSFWorkbook wb) {
		List<Object> list = excelInfoBean.getExData();
		excelInfoBean.setCountRows(list.size());
		int rate = this.judge();
		for (int i = 0; i < rate; i++) {
			HSSFSheet sheet = wb.createSheet();
			int startRows = this.createFirstTitle(sheet);
			for (int j = 0 * i; j < list.size(); j++) {
				Object object = (Object) list.get(j);
				invoke(object, startRows + j, sheet);
			}
		}
	}

	/**
	 * 该方法由生成具体的表格时去实现(方法体内进行填充数据)
	 * 
	 * @param sheet
	 * @param style
	 */
	public void fillDataByList(int startRows, HSSFSheet sheet) {
		List<Object> list = excelInfoBean.getExData();
		for (int j = 0; j < list.size(); j++) {
			Object object = (Object) list.get(j);
			invoke(object, startRows + j, sheet);
		}
	}
/**
 * 
 * @Description: 根据sql语句填充excel数据
 * @param wb excel对象
 * @throws SQLException  
 * @author: 窦恩虎
 * @date Feb 29, 2012 5:03:38 PM
 */
	private void fillDataBySql(HSSFWorkbook wb) throws SQLException {
		double countRows = new Double(this.getMaxCountOracle_param());//得到记录的总条数
		excelInfoBean.setCountRows(countRows);
		int rate = this.judge();  //判断数据需要几个表展示
		for (int i = 1; i <= rate; i++) {
			HSSFSheet sheet = wb.createSheet(); // 表，一个表包含多个行
			int startRows = this.createFirstTitle(sheet); //设置EXCEL的表头
			this.queryResultSet(startRows, i, sheet);
			// wb.setSheetName(i, "");
		}
	}
/**
 * 
 * @Description: 创建单元格
 * @param row 行对象
 * @param cols 列索引
 * @param methodValue  单元格值
 * @author: 窦恩虎
 * @date Feb 29, 2012 5:27:08 PM
 */
	private void setHSSFCell(HSSFRow row, int cols, Object methodValue) {
		HSSFCell cell;// 单元格
		cell = row.createCell((short) cols);
		cell.setCellStyle(style);
		// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(String.valueOf(methodValue == null ? "" : methodValue));// 产品编号
	}
	/**
	 * 
	 * @Description:  创建单元格
	 * @param row 行对象
	 * @param cols 列索引
	 * @param methodValue 单元格值
	 * @param style   单元样式
	 * @author: 窦恩虎
	 * @date Feb 29, 2012 5:28:05 PM
	 */
	public void setHSSFCell(HSSFRow row, int cols, Object methodValue,HSSFCellStyle style) {
		HSSFCell cell;// 单元格
		cell = row.createCell((short) cols);
		cell.setCellStyle(style);
		// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(String.valueOf(methodValue == null ? "" : methodValue));// 产品编号
	}

	/**
	 * 根据List对象中的不同数据载体来做相应的处理, 目前支持JavaBean 与 HashMap为数据载体的数据集,
	 * */
	public void invoke(Object object, int startRows, HSSFSheet sheet) {
		if ("java.util.LinkedHashMap".equals(object.getClass().getName())) {
			this.setCellByLinkedHashMap(object, startRows, sheet);
		} else {
			this.setCellByBean(object, startRows, sheet);
		}
	}

	private void setCellByLinkedHashMap(Object cellValueMap, int startRows, HSSFSheet sheet) {
		// 得到所有属性
		if (null != cellValueMap) {
			LinkedHashMap<Object,Object> newCellValueMap=(LinkedHashMap<Object,Object>)cellValueMap;
			Iterator<Entry<Object, Object>> it = newCellValueMap.entrySet().iterator();
			HSSFRow hssfRow = createHssfRow(startRows, sheet);// 行，一行包括多个单元格
			int cols=0;
			while(it.hasNext()){
				Entry<Object, Object> entry=it.next();
				Object value=entry.getValue();
				setHSSFCell(hssfRow, cols++, value);
			}
		}
	}

	/**
	 * 数据源载体是javaBean
	 * 
	 * */
	public void setCellByBean(Object object, int startRows, HSSFSheet sheet) {
		// 得到所有属性
		java.lang.reflect.Field[] fields = object.getClass().getDeclaredFields();
		HSSFRow hssfRow = createHssfRow(startRows, sheet);// 行，一行包括多个单元格
		for (int n = 0; n < fields.length; n++) {
			// 得到所有方法，包括get和set方法
			Method method = null;
			try {
				method = object.getClass().getMethod(converseGetMothed(fields[n].getName()), null);
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			}
			String methodName = method.getName();
			Object methodValue = null;
			if (methodName.indexOf("get") == 0) {
				try {
					methodValue = method.invoke(object, null);
					setHSSFCell(hssfRow, n, methodValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// set 方法不需要
		// Class[] objSetMethodType = new Class[1];
		// objSetMethodType[0] = methodValue.getClass();
		// Method methodObj =
		// returnObj.getClass().getMethod(methodName.replaceFirst("get",
		// "set"),
		// objSetMethodType);
		// Object[] objProValue = new Object[1];
		// objProValue[0] = methodValue;
		// methodObj.invoke(returnObj, objProValue);
	}
/**
 * 
 * @Description: 创建行
 * @param startRows 行索引
 * @param sheet
 * @return  
 * @author: 窦恩虎
 * @date Feb 29, 2012 5:22:44 PM
 */
	private HSSFRow createHssfRow(int startRows, HSSFSheet sheet) {
		HSSFRow hssfRow;
		hssfRow = sheet.createRow(startRows);
		hssfRow.setHeightInPoints((float) 15);
		return hssfRow;
	}

	/**
	 * 获取javaBean的get属性
	 * */
	private static String converseGetMothed(String property) {
		String proFirst = property.substring(0, 1);
		String proReplace = proFirst.toUpperCase();
		property = "get" + property.replaceFirst(proFirst, proReplace);
		return property;
	}

	/**
	 * 
	 * @Description: 获取当前页信息
	 * @param xlsStartRows 表示数据从EXCEL的第几行开始写入
	 * @param page sheet的索引（即写入到那张表中）
	 * @param sheet  
	 * @throws SQLException  
	 * @author: 窦恩虎
	 * @date Feb 29, 2012 5:16:14 PM
	 */
	private void queryResultSet(int xlsStartRows, int page, HSSFSheet sheet) throws SQLException {
		Object[] newObject = null;
		String sql = exportSql;
		if (null != exportSqlParam && exportSqlParam.length > 0) {
			newObject = new Object[exportSqlParam.length + 2];
			for (int i = 0; i < exportSqlParam.length; i++) {
				newObject[i] = exportSqlParam[i];
			}
		} else {
			newObject = new Object[2];
		}
		if (page > 0) {
			if("mysql".equals(this.databaseType)){
				sql = SQLHelper.getLimitMysql(sql);
				// 最后一条记录的行号
				newObject[newObject.length - 1] = this.excelInfoBean.getRows();
				// 第一条记录的行号
				newObject[newObject.length - 2] = getCurrentRecordStart(excelInfoBean.getRows(), page)-1;
			}else{
				sql = SQLHelper.getLimitOracle(sql);
				// 最后一条记录的行号
				newObject[newObject.length - 2] = getCurrentRecordEnd(excelInfoBean.getRows(), page);
				// 第一条记录的行号
				newObject[newObject.length - 1] = getCurrentRecordStart(excelInfoBean.getRows(), page);
			}
		} else {
			newObject = new Object[] {};
		}
		commonQueryResult(xlsStartRows, sql, newObject, sheet);
	}

	/***************************************************************************
	 * @author luck
	 * @param conn
	 *            ,sql
	 * @return ArrayList;
	 * @throws SQLException
	 * @throws SQLException
	 **************************************************************************/
	private void commonQueryResult(int xlsStartRows, String sql, Object[] param, HSSFSheet sheet) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			conn = excelInfoBean.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt = getPreparedStatementParam(pstmt, param);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HSSFRow hssfRow = createHssfRow(xlsStartRows++, sheet);// 行，一行包括多个单元格
				Object value = null;
				ResultSetMetaData rsm = rs.getMetaData();
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					// String strTempColumnName =
					// rsm.getColumnName(i).toUpperCase();
					value = (rs.getObject(i) == null ? "" : rs.getObject(i));
					value = (value == null ? "" : value);
					if(value instanceof  oracle.sql.CLOB){
						
						oracle.sql.CLOB clob = (oracle.sql.CLOB)rs.getClob(i);   
						value = readClob(clob);
					}
					this.setHSSFCell(hssfRow, i - 1, value);
					sheet.setColumnWidth(i, (short) (5000)); // 设置列宽
				}
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			this.free(rs, pstmt, conn);
		}
	}

	/**
	  * 流输入方法
	  * clob 字符大对象
	  * Strwrite 长字符串
	  * @param clob
	  * @param Strwrite
	  */
	 public void writeClob(CLOB clob, String Strwrite) {

	  try {
	   Writer wr = clob.getCharacterOutputStream();
	   wr.write(Strwrite);
	   wr.flush();
	   wr.close();
	  } catch (SQLException e) {
	   e.printStackTrace();
	  } catch (IOException e) {
	   e.printStackTrace();
	  }
	 }
	 /**
	  * 
	  * @Description:  读clob类型
	  * @param clob
	  * @return  
	  * @author: 窦恩虎
	  * @date May 11, 2012 9:06:48 AM
	  */
	 public String readClob(oracle.sql.CLOB clob) {
		  StringBuffer sb = new StringBuffer();
		  try {
		            BufferedReader in = new BufferedReader(clob.getCharacterStream());   
		            sb.append(in.readLine());
		            in.close();   

		  } catch (SQLException e) {
		   e.printStackTrace();
		  } catch (IOException ex) {
		   ex.printStackTrace();
		  }
		  return sb.toString();
		 }



	/***************************************************************************
	 * 获取总记录数 有参数
	 * 
	 * @param conn数据源
	 * @param sql语句
	 * @return 总记录数
	 * @throws SQLException
	 * 
	 **************************************************************************/
	private int getMaxCountOracle_param() throws SQLException {
		
		//拼接导出数据总条数的sql语句
		String count_sql = SQLHelper.getCountSQL(exportSql);
		String count = "0";
		Connection conn = null;
		try {
			conn = excelInfoBean.getConn();
			//根据查询条件获取记录总条数
			count = find_nameByDm(conn, count_sql, exportSqlParam);
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			free(null, null, conn);
		}
		return new Integer(count).intValue();
	}
/**
 * 
 * @Description: 设置where参数值并进行查询第一行的内容
 * @param conn
 * @param sql  查询的sql语句
 * @param param sql语句中的参数对应的数据对象(语句的格式：)
 * @return
 * @throws SQLException  
 * @author: 窦恩虎
 * @date Feb 29, 2012 4:47:28 PM
 */
	private String find_nameByDm(Connection conn, String sql, Object[] param) throws SQLException {
		String returnStr = "";
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt = getPreparedStatementParam(pstmt, param);//设置where条件的参数值
			rs = pstmt.executeQuery();
			while (rs.next()) {
				returnStr = String.valueOf(rs.getObject(1) == null ? "" : rs.getObject(1));
			}
		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			free(rs, pstmt, null);
		}
		returnStr = (returnStr == null ? "" : returnStr);
		return returnStr;
	}

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
 * @Description: 设置jdbc中where参数值（例：select * from a where a.id=? and a.name=?  ）
 * @param pstmt
 * @param param 参数值数据对象param[0]表示第一个？param[1]表示第二个？
 * @return  
 * @author: 窦恩虎
 * @date Feb 29, 2012 4:52:32 PM
 */
	private PreparedStatement getPreparedStatementParam(final PreparedStatement pstmt, final Object[] param) {
		try {
			if (null != param && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	/**
	 * 
	 * @Description: 计算需要的工作表（sheet）个数
	 * @return  sheet 总数
	 * @author: 窦恩虎
	 * @date Feb 29, 2012 5:07:10 PM
	 */
	private int judge() {
		double rate = 0.0;
		rate = excelInfoBean.getCountRows() / new Double(excelInfoBean.getRows());
		if (excelInfoBean.getRows() > 0) {
			int newSize1 = (int) (excelInfoBean.getCountRows() / excelInfoBean.getRows());
			if (rate > newSize1) {
				rate = rate + 1;
			}
			return (int) (rate);
		} else {
			return 0;
		}
	}

	/**
	 * ExcelInfoBean 属性值验证
	 * 
	 * throws IllegalAccessException,FileNotFoundException
	 * */
	private void validateExcelInfoBean(ExcelInfoBean excelInfoBean) throws IllegalAccessException,
			FileNotFoundException {
		if (null == excelInfoBean) {
			throw new IllegalAccessException("空对象ExcelInfoBean");
		} else {
			if (null == excelInfoBean.getResponse()) {
				throw new FileNotFoundException("Response对象不能为空");
			}
			if (null == excelInfoBean.getFileName() || "".equals(excelInfoBean.getFileName())) {
				throw new FileNotFoundException("Excel文件名不能为空");
			} else {

			}
			if (null == excelInfoBean.getSql() || "".equals(excelInfoBean.getSql())) {
				if (null == excelInfoBean.getExData() || excelInfoBean.getExData().size() <= 0) {
					throw new NullPointerException("请选择Excel数据来源,ExData 或 Sql");
				}
			} else {
				if (null == excelInfoBean.getConn()) {
					throw new NullPointerException("请配置Excel的数据源");
				}
			}
		}
	}

	/***************************************************************************
	 * 最后一条记录的行号
	 * 
	 * @param rows
	 *            分页行数
	 * @param page
	 *            当前页数
	 * @return
	 **************************************************************************/
	private Integer getCurrentRecordEnd(int rows, int page) {
		int intResultData = 0;
		intResultData = rows * page;
		return intResultData;
	}

	/***************************************************************************
	 * 第一条记录的行号
	 * 
	 * @param rows
	 *            分页行数
	 * @param page
	 *            当前页数
	 * @return
	 **************************************************************************/
	private Integer getCurrentRecordStart(int rows, int page) {
		int intResultData = 0;
		if (1 == page)
			intResultData = 1;
		else
			intResultData = rows * page - (rows - 1);
		return intResultData;
	}

	/**
	 * 设置Excel样式
	 * */
	public HSSFCellStyle setCellType(HSSFWorkbook wb) {
		style = wb.createCellStyle();
		style.setFont(font);
		style.setWrapText(true);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return style;
	}

	/**
	 * 设置Excel字体
	 * **/
	public HSSFFont setFont(HSSFWorkbook wb) {
		font=wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("Courier New");
		return font;
	}

	/**
	 * 关闭文件流
	 * */
	public void close() {
		if (excelFile != null) {
			try {
				excelFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		HttpServletResponse response = null;
		ExcelInfoBean excelInfoBean = new ExcelInfoBean();
		try {
			ExcelExport bak = new ExcelExport(excelInfoBean);
			bak.exportExcel(response);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
