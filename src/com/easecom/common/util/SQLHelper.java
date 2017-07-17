package com.easecom.common.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

public class SQLHelper {

	public SQLHelper() {
	}

	/**
	 * 统计记录总数SQL
	 * 
	 * @param type数据库类型
	 * @param sql
	 *            语句
	 * @return 统计的SQL语句
	 */
	public static String getCountSQL(String sql) {
		StringBuffer stringBuffer = new StringBuffer(100);
		stringBuffer.append("select count(1) as count_ from ( " + sql + " ) count_content");
		return stringBuffer.toString();
	}

	/***************************************************************************
	 * 检索此数据库产品的名称
	 * 
	 * @param con数据源
	 * @return 数据库名称
	 **************************************************************************/
	public static String getDataBaseType(Connection con) throws Exception {
		String name = null;
		try {
			DatabaseMetaData md = con.getMetaData();
			name = md.getDatabaseProductName().toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return name;
	}

	/***************************************************************************
	 * 
	 * 分页面SQL语句
	 * 
	 * @param sql
	 *            未进行页面前的SQL语句
	 * @return sql 分页后的SQL语句
	 **************************************************************************/
	// oracle 数据库
	public static String getLimitOracle(String sql) {
		StringBuffer stringBuffer = new StringBuffer(10);
		stringBuffer.append("select * from ( select row_.*, rownum rownum_ from ( ");
		stringBuffer.append(sql);
		stringBuffer.append(" ) row_ where rownum <= ?) where rownum_ >= ?");
		return stringBuffer.toString();
	}
	// mysql 数据库
	public static String getLimitMysql(String sql){
		StringBuffer stringBuffer = new StringBuffer(10);
		stringBuffer.append("select * from ( ");
		stringBuffer.append(sql);
		stringBuffer.append(" ) row_  limit ?,?");
		return stringBuffer.toString();
	}
	

	public static int judge(int size, int row) {
		double rate = 0.0;
		Integer newSize = new Integer(size);
		rate = newSize.doubleValue() / Double.parseDouble(String.valueOf(row));
		if (row > 0) {
			int newSize1 = size / row;
			if (rate > newSize1) {
				rate = rate + 1;
			}
			return (int) (rate);
		} else {
			return 0;
		}
	}

	/***************************************************************************
	 * 最后一条记录的行号
	 * 
	 * @author luck
	 * @param
	 * @return
	 **************************************************************************/
	public static String getCurrentRecordEnd(int count, int page) {
		int intResultData = 0;
		intResultData = count * page;
		return String.valueOf(intResultData);
	}

	/***************************************************************************
	 * 第一条记录的行号
	 * 
	 * @author luck
	 * @param
	 * @return
	 **************************************************************************/
	public static String getCurrentRecordStart(int count, int page) {
		int intResultData = 0;
		if (1 == page)
			intResultData = 1;
		else
			intResultData = count * page - (count - 1);
		return String.valueOf(intResultData);
	}

	/***************************************************************************
	 * 
	 * 分页面SQL语句
	 * 
	 * @param sql
	 *            未进行页面前的SQL语句
	 * @return sql 分页后的SQL语句
	 **************************************************************************/
	public static String getLimitMySQL(String sql) {
		StringBuffer stringBuffer = new StringBuffer(10);
		stringBuffer.append("select * from ( select row_.*, rownum rownum_ from ( ");
		stringBuffer.append(sql);
		stringBuffer.append(" ) row_ where limit #,#");
		return stringBuffer.toString();
	}

	public static List getSelectParam(String SQL) throws Exception {
		List parmList = new ArrayList();
		String splitListForWhere[] = SQL.toLowerCase().split("where");
		for (int j = 0; j < splitListForWhere.length; j++) {
			String splitListForAnd[] = splitListForWhere[j].split("and");
			for (int i = 0; i < splitListForAnd.length; i++) {
				String splitList[] = null;
				if (splitListForAnd[i].indexOf("?") != -1) {
					if (splitListForAnd[i].indexOf("=") != -1)
						splitList = splitListForAnd[i].split("=");
					else if (splitListForAnd[i].indexOf("like") != -1)
						splitList = splitListForAnd[i].split("like");
					else if (splitListForAnd[i].indexOf("in") != -1)
						splitList = splitListForAnd[i].split("in");
					String strSplit = splitList[0].trim();
					strSplit = strSplit.substring(strSplit.indexOf(".") + 1, strSplit.length());
					parmList.add(strSplit);
				}
			}

		}

		return parmList;
	}
}
