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

/**
 * <p>
 * Title: 查询帮助类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * </p>
 * <p>
 * Company: Merit
 * </p>
 * 
 * @author Shark
 * @version 1.0
 */

import java.util.*;

public class QueryHelper {
	public QueryHelper() {
	}

	public static String toOrQuery(Collection Querys) {
		String rtnStr = toQuery(Querys);
		if (rtnStr == null) {
			rtnStr = "";
		}
		if (rtnStr.length() > 1) {
			rtnStr = " or ( " + rtnStr + " )";
		}
		return rtnStr;
	}

	public static String toAndQuery(Collection Querys) {
		String rtnStr = toQuery(Querys);
		if (rtnStr == null) {
			rtnStr = "";
		}
		if (rtnStr.length() > 1) {
			rtnStr = " and " + rtnStr + "";
		}
		return rtnStr;
	}

	public static String toQuery(Collection Querys) {
		if (Querys == null) {
			return "";
		} else if (Querys.size() < 1) {
			return "";
		}
		String rtnStr = "";
		Iterator it = Querys.iterator();
		QueryCond queryCond = null;
		while (it != null && it.hasNext()) {
			queryCond = (QueryCond) it.next();
			if (queryCond.getFieldVal() == null
					|| "".equals(queryCond.getFieldVal())) {
				continue;
			}
			if ("YYYY_MM_DD_HH24_MI_SS".equalsIgnoreCase(queryCond
					.getFieldType())
					|| "YYYY_MM_DD_HH_MI_SS".equalsIgnoreCase(queryCond
							.getFieldType())) {
				rtnStr += " and "
						+ queryCond.getFieldName()
						+ " "
						+ queryCond.getOperate()
						+ to_Date_YYYY_MM_DD_HH24_MI_SS(queryCond.getFieldVal());
			} else if ("YYYY_MM".equalsIgnoreCase(queryCond.getFieldType())) {
				rtnStr += " and " + queryCond.getFieldName() + " "
						+ queryCond.getOperate()
						+ to_Date_YYYY_MM(queryCond.getFieldVal());
			} else if ("date".equalsIgnoreCase(queryCond.getFieldType())) {
				rtnStr += " and " + queryCond.getFieldName() + " "
						+ queryCond.getOperate()
						+ to_Date_YYYY_MM_DD(queryCond.getFieldVal());
			} else if ("YYYY_MM_DD".equalsIgnoreCase(queryCond.getFieldType())) {
				rtnStr += " and " + queryCond.getFieldName() + " "
						+ queryCond.getOperate()
						+ to_Date_YYYY_MM_DD(queryCond.getFieldVal());
			} else if ("YYYY".equalsIgnoreCase(queryCond.getFieldType())) {
				rtnStr += " and " + queryCond.getFieldName() + " "
						+ queryCond.getOperate()
						+ to_Char_YYYY(queryCond.getFieldVal());
			} else if ("string".equalsIgnoreCase(queryCond.getFieldType())) {
				if ("like".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " like '%"
							+ queryCond.getFieldVal().trim() + "%' ";
				} else if ("likeLeft".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " like '"
							+ queryCond.getFieldVal().trim() + "%' ";
				} else if ("in".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " in ("
							+ queryCond.getFieldVal().trim() + ") ";
				}else if ("not in".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " not in ("
							+ queryCond.getFieldVal().trim() + ") ";
				} else if ("is".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " "
							+ queryCond.getOperate() + " "
							+ queryCond.getFieldVal().trim();
				}else {
					rtnStr += " and " + queryCond.getFieldName() + " "
							+ queryCond.getOperate() + " '"
							+ queryCond.getFieldVal().trim() + "' ";
				}
			} else if ("number".equalsIgnoreCase(queryCond.getFieldType())) {
				if ("in".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " in ("
							+ queryCond.getFieldVal() + ") ";
				}else if ("or".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " or " + queryCond.getFieldName() + " = "
							+ queryCond.getFieldVal().trim() + " ";
				}  else {
					rtnStr += " and " + queryCond.getFieldName() + " "
							+ queryCond.getOperate() + " "
							+ queryCond.getFieldVal() + " ";
				}
			}
		}
		if (rtnStr.length() > 1) {
			rtnStr = rtnStr.substring(5);
		}
		return rtnStr;
	}

	/**
	 * 产生将日期时间样式为yyyy-mm-dd的日期型数据转换成字符串型SQL select 语句 *
	 * 
	 * @param colName
	 *            数据库字段名
	 * @param pattern
	 *            时间模板例如：yyyy-mm-dd hh24:mi:ss
	 * @return SQL select 语句
	 */
	public static String to_Char(String colName, String pattern) {
		return " to_char(" + colName + ",'" + pattern + "') ";
	}

	public static String to_Date(String dateVal, String pattern) {
		return " to_date('" + dateVal + "','" + pattern + "') ";
	}

	/**
	 * 产生将日期时间样式为yyyy-mm-dd的日期型数据转换成字符串型SQL select 语句
	 * 
	 * @param colName
	 *            数据库字段名
	 * @return SQL select 语句
	 */
	public static String to_Char_YYYY(String colName) {
		return " to_char(" + colName + ",'yyyy') ";
	}

	public static String to_Date_YYYY(String dateVal) {
		return " to_date('" + dateVal + "','yyyy') ";
	}

	/**
	 * 产生将日期时间样式为yyyy-mm-dd的日期型数据转换成字符串型SQL select 语句
	 * 
	 * @param colName
	 *            数据库字段名
	 * @return SQL select 语句
	 */
	public static String to_Char_YYYY_MM_DD(String colName) {
		return " to_char(" + colName + ",'yyyy-mm-dd') ";
	}

	public static String to_Date_YYYY_MM_DD(String dateVal) {
		return " to_date('" + dateVal + "','yyyy-mm-dd') ";
	}

	/**
	 * 产生将日期时间样式为hh:mi:ss的日期型数据转换成字符串型SQL select 语句
	 * 
	 * @param colName
	 *            数据库字段名
	 * @return SQL select 语句
	 */
	public static String to_Char_HH24_MI_SS(String colName) {
		return " to_char(" + colName + ",'hh24:mi:ss') ";
	}

	public static String to_Date_HH24_MI_SS(String dateVal) {
		return " to_date('" + dateVal + "','hh24:mi:ss') ";
	}

	/**
	 * 产生将日期时间样式为yyyy-mm-dd hh:mi:ss的日期型数据转换成字符串型SQL select 语句
	 * 
	 * @param colName
	 *            数据库字段名
	 * @return SQL select 语句
	 */
	public static String to_Char_YYYY_MM_DD_HH24_MI_SS(String colName) {
		return " to_char(" + colName + ",'yyyy-mm-dd hh24:mi:ss') ";
	}

	public static String to_Date_YYYY_MM_DD_HH24_MI_SS(String dateVal) {
		return " to_date('" + dateVal + "','yyyy-mm-dd hh24:mi:ss') ";
	}

	/**
	 * 产生将日期时间样式为yyyy-mm的日期型数据转换成字符串型SQL select 语句
	 * 
	 * @param colName
	 *            数据库字段名
	 * @return SQL select 语句
	 */
	public static String to_Char_YYYY_MM(String colName) {
		return " to_char(" + colName + ",'yyyy-mm') ";
	}

	public static String to_Date_YYYY_MM(String dateVal) {
		return " to_date('" + dateVal + "','yyyy-mm') ";
	}

}
