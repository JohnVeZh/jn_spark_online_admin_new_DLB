package com.easecom.common.util;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class ExcelInfoBean {

	private String fileName = "";// Excel文件名称
	private String[] exTitle;// Excel表头
	private String[] exSheet;// Excel sheet表名称
	private List<Object> exData;// 导出数据
	//modi by yp 20120329
	//private int rows = 65534;// 每个工作表的记录数(默认值)
	private int rows = 5000;
	private double countRows = 0;// 总记录数
	private String sql = "";// 查询sql
	private Object[] sqlParam;// 查询Sql参数值
	private Connection conn;// 数据源

	private HttpServletResponse response;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getExTitle() {
		return exTitle;
	}

	public void setExTitle(String[] exTitle) {
		this.exTitle = exTitle;
	}

	public String[] getExSheet() {
		return exSheet;
	}

	public void setExSheet(String[] exSheet) {
		this.exSheet = exSheet;
	}

	public List<Object> getExData() {
		return exData;
	}

	public void setExData(List<Object> exData) {
		this.exData = exData;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public double getCountRows() {
		return countRows;
	}

	public void setCountRows(double countRows) {
		this.countRows = countRows;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getSqlParam() {
		return sqlParam;
	}

	public void setSqlParam(Object[] sqlParam) {
		this.sqlParam = sqlParam;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

}
