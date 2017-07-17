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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.system.exception.SystemException;

public class Tool {
	public static Logger logger = Logger.getLogger(Tool.class);

	/**
	 * 根据传入的查询sql语句返回可以向<select></select>的<option>项中添加项的字符串
	 * 
	 * @param sql
	 *            查询sql语句
	 * @param display
	 * @param value
	 * @return
	 */
	public static String getList(String sql, String display, String value) {
		try {
			String s3 = "";
			Session session = HibernateSessionFactory.openSession();
			Connection conn = session.connection();
			Statement statement = conn.createStatement();
			ResultSet resultset;
			for (resultset = statement.executeQuery(sql); resultset.next();) {
				String displayvalue = resultset.getString(display);
				if (displayvalue == null)
					displayvalue = "";
				s3 = s3 + "<option value=\"" + resultset.getString(value)
						+ "\">" + displayvalue + "</option>";
			}

			statement.close();
			resultset.close();
			return s3;
		} catch (Exception exception) {
			return "<option>Error</option>";
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 根据传入的查询sql语句返回可以向<select></select>的<option>项中添加项的字符串(html片段),并根据传入的值selected
	 * 
	 * @param sql
	 * @param display:显示的值
	 * @param value：实际值
	 * @param realValue：缺省值
	 * @return 例如：str="select id ,name from table" 调用getList(str,name,id,"001")
	 *         返回：<option value="001" selected>name1</option> <option
	 *         value="002" >name2</option> <option value="003" >name3</option>
	 *         ...
	 */
	public static String getList(String sql, String display, String value,
			String realValue) {
		try {
			String s4 = "";
			String s5 = "";
			Session session = HibernateSessionFactory.openSession();
			Connection conn = session.connection();
			Statement statement = conn.createStatement();
			ResultSet resultset;
			for (resultset = statement.executeQuery(sql); resultset.next();) {
				String s6 = resultset.getString(value);
				String displayvalue = resultset.getString(display);
				if (displayvalue == null)
					displayvalue = "";
				
				if (s6.equals(realValue))
					s4 = s4 + "<option value=\"" + s6 + "\" selected>"
							+ displayvalue + "</option>";
				else
					s4 = s4 + "<option value=\"" + s6 + "\">" + displayvalue
							+ "</option>";
			}
			statement.close();
			resultset.close();
			return s4;
		} catch (Exception exception) {
			logger.error(exception);
			return "<option>Error</option>";
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 根据传入的collection返回可以向<select></select>的<option>项中添加项的字符串
	 * 
	 * @param collection
	 * @return
	 */
	public static String getList(Collection collection) {
		try {
			Object aobj[] = collection.toArray();
			String s = "";
			for (int l = 0; l < aobj.length; l++)
				s = s + "<option value=\"" + l + "\">" + aobj[l].toString()
						+ "</option>";

			return s;
		} catch (Exception exception) {
			return "<option>Error</option>";
		}
	}

	/**
	 * 根据传入的collection返回可以向<select></select>的<option>项中添加项的字符串,并根据传入的值selected
	 * 
	 * @param collection
	 * @param s
	 * @return
	 */
	public static String getList(Collection collection, String s) {
		try {
			Object aobj[] = collection.toArray();
			String s1 = "";
			for (int l = 0; l < aobj.length; l++)
				if (s.equals(String.valueOf(l)))
					s1 = s1 + "<option value=\"" + l + "\" selected>"
							+ aobj[l].toString() + "</option>";
				else
					s1 = s1 + "<option value=\"" + l + "\">"
							+ aobj[l].toString() + "</option>";

			return s1;
		} catch (Exception exception) {
			return "<option>Error</option>";
		}
	}
	
	/**
	 * 根据数组获取select  格式
	 * @param collection
	 * @return
	 */
	public static String getListByArray(List collection) {
		try {
			if(collection==null) return "<option value=\"\">无数据</option>"; 
			StringBuffer sb = new StringBuffer();
			for (int l = 0; l < collection.size(); l++){
				Object[] objs = (Object[])collection.get(l);
				sb.append( "<option value=\"" + objs[0] + "\">" + objs[1].toString()
						+ "</option>");
			}
			return sb.toString();
		} catch (Exception exception) {
			return "<option>Error</option>";
		}
	}
	
	

	/**
	 * 根据传入的SQL得到查询结果的行数
	 * 
	 * @param conn
	 * @param sql
	 * @return
	 */
	public int getRowCount(Connection conn, String sql) {
		try {
			Statement statement = conn.createStatement();
			ResultSet resultset;
			int i = 0;
			for (resultset = statement.executeQuery(sql); resultset.next();) {
				i++;
			}
			resultset.close();
			statement.close();
			return i;
		} catch (SQLException e) {
			return 0;
		}

	}

	/**
	 * 
	 * @param sql
	 *            查询语句的列需按照规定顺序，1-唯一标识,2-显示值,3-关联值（父id或外键）
	 * @param l
	 * @param s1
	 * @param flag
	 * @return
	 * @throws SQLException
	 */
	public String change(String sql, String relationColumn, boolean flag)
			throws SQLException {
		StringBuffer sb = new StringBuffer();
		Session session = HibernateSessionFactory.openSession();
		Connection conn = session.connection();
		Statement statement = conn.createStatement();
		int maxCount = this.getRowCount(conn, sql);
		String as[] = new String[maxCount + 1];
		String as1[] = new String[maxCount + 1];
		String as2[] = new String[maxCount + 1];
		int i1 = 0;
		try {
			ResultSet resultset;

			for (resultset = statement.executeQuery(sql); resultset.next();) {
				if (flag)
					as[i1] = resultset.getString(1);
				as1[i1] = resultset.getString(2);
				as2[i1] = resultset.getString(3);
				i1++;
			}

			resultset.close();
			statement.close();
		} catch (Exception exception) {
			logger.error(exception);
			statement.close();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		sb = sb.append("var " + relationColumn + "_item=new Array();\r");
		sb = sb.append("var " + relationColumn + "_downlist;\r var "
				+ relationColumn + "_value=new Array() ;\r var "
				+ relationColumn + "_ref=new Array() ;\r");
		for (int j1 = 0; j1 < i1; j1++) {
			if (flag)
				sb.append(relationColumn + "_item[" + Integer.toString(j1)
						+ "]=\"" + as[j1] + "\";\r");
			sb.append(relationColumn + "_value[" + Integer.toString(j1)
					+ "]=\"" + as1[j1] + "\";\r");
			sb.append(relationColumn + "_ref[" + Integer.toString(j1) + "]=\""
					+ as2[j1] + "\";\r");
		}

		if (flag)
			sb.append(relationColumn + "_downlist=1;\r");
		else
			sb.append(relationColumn + "_downlist=0;\r");
		return sb.toString();
	}

	/**
	 * @param sql
	 *            查询中的字段名称与web页控件名称对应
	 * @param flag
	 * @return
	 * @throws SQLException
	 */
	public String changeValue(String sql, String relationColumn, boolean flag)
			throws SQLException {
		StringBuffer sb = new StringBuffer();
		Session session = HibernateSessionFactory.openSession();
		Connection conn = session.connection();
		Statement statement = conn.createStatement();
		List as = new ArrayList();
		Map mp = null;
		try {
			ResultSet resultset = statement.executeQuery(sql);
			int count = resultset.getMetaData().getColumnCount();
			while (resultset.next()) {
				for (int i = 1; i < count; i++) {
					String value = resultset.getString(i);
					if (value != null && !value.equals("")) {
						mp = new HashMap();
						mp.put("ref", resultset.getString(relationColumn));
						mp.put("column", resultset.getMetaData().getColumnName(
								i));
						mp.put("value", value);
						as.add(mp);
					}
				}
			}

			resultset.close();
			statement.close();
		} catch (Exception exception) {
			logger.error(exception);
			statement.close();
		} finally {
			HibernateSessionFactory.closeSession();
		}
		if (flag) {
			sb = sb.append("var " + relationColumn + "_item=new Array();\r");
			sb = sb.append(" var " + relationColumn
					+ "_value=new Array() ;\r var " + relationColumn
					+ "_ref=new Array() ;\r");
			count = 0;
		}

		for (int j1 = 0; j1 < as.size(); j1++) {
			mp = (Map) as.get(j1);
			sb.append(relationColumn + "_item[" + count + "]=\""
					+ mp.get("column") + "\";\r");
			sb.append(relationColumn + "_value[" + count + "]=\""
					+ mp.get("value") + "\";\r");
			sb.append(relationColumn + "_ref[" + count + "]=\"" + mp.get("ref")
					+ "\";\r");
			count++;
		}
		return sb.toString();
	}

	public static String getValue(String s) {
		String s1 = "";
		Statement statement = null;
		Object obj = null;
	
		try {
			Session session = HibernateSessionFactory.openSession();
			Connection conn = session.connection();
			statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(s);
			if (resultset.next())
				s1 = resultset.getString(1) == null ? "" : resultset
						.getString(1);
			else
				s1 = "";
			resultset.close();
			statement.close();
		} catch (Exception exception) {
			logger.error(exception.toString());
		} finally {
			 HibernateSessionFactory.closeSession();
		}

		return s1;
	}
	public static int getIntValue(String s) {
		int int1 = 0;
		Statement statement = null;
		try {
			Session session = HibernateSessionFactory.openSession();
			Connection conn = session.connection();
			statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(s);
			if (resultset.next())
				int1 = resultset.getInt(1);
			resultset.close();
			statement.close();
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error(exception.toString());
		} finally {
			// HibernateSessionFactory.closeSession();
		}
		return int1;
	}
	
	public static double geDoubleValue(String s) {
		double int1 = 0;
		Statement statement = null;
		try {
			Session session = HibernateSessionFactory.openSession();
			Connection conn = session.connection();
			statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(s);
			if (resultset.next())
				int1 = resultset.getDouble(1);
				resultset.close();
				statement.close();
		} catch (Exception exception) {
			logger.error(exception.toString());
		} finally {
			// HibernateSessionFactory.closeSession();
		}
		return int1;
	}
	
	public static long getLongValue(String s) {
		long long1 = 0;
		Statement statement = null;
		try {
			Session session = HibernateSessionFactory.openSession();
			Connection conn = session.connection();
			statement = conn.createStatement();
			ResultSet resultset = statement.executeQuery(s);
			if (resultset.next())
				long1 = resultset.getLong(1);
			resultset.close();
			statement.close();
		} catch (Exception exception) {
			logger.error(exception.toString());
		} finally {
			// HibernateSessionFactory.closeSession();
		}
		return long1;
	}

	/*
	 * 从查询语句里得到第一字段的值列表
	 */
	public static List getLongList(String sqlstring) throws SQLException {
		Session session = HibernateSessionFactory.openSession();
		List list = new ArrayList();
		try {
			Connection conn = session.connection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sqlstring);
			while (rs.next()) {
				list.add(new Long(rs.getLong(1)));
			}
			return list;
		} catch (SQLException e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public static List getRoleList(String s) {

		Statement statement = null;
		ResultSet resultset = null;
		List roleList = new ArrayList();
		try {
			Session session = HibernateSessionFactory.openSession();
			Connection conn = session.connection();
			statement = conn.createStatement();
			resultset = statement.executeQuery(s);
			while (resultset != null && resultset.next()) {
				roleList.add(resultset.getString(1));
			}
			return roleList;
		} catch (Exception exception) {
			logger.error(exception.toString());
			return roleList;
		} finally {
			// HibernateSessionFactory.closeSession();
		}
	}

	// s为执行SQL语句字符串
	public static boolean execute(String s) throws SQLException {
		boolean isSuccess = false;
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.openSession();
			tx = session.beginTransaction();
			Connection conn = session.connection();
			Statement statement = conn.createStatement();
			// statement.executeQuery(s);
			statement.execute(s);
			isSuccess = true;
			statement.close();
			tx.commit();
		} catch (Exception exception) {
			logger.error(exception.toString());
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return isSuccess;
	}

	// 生成样式下拉框的字符串 例如：dropdown_items="1=1;2=2;3=3;4=4"
	public static String getDropdownItems(List itemList) {
		StringBuffer items = new StringBuffer();
		for (int i = 0; i < itemList.size(); i++) {
			String item = (String) itemList.get(i);
			items.append(item + "=" + item + ";");
		}
		if (items.length() > 1)
			items.deleteCharAt(items.length() - 1);
		return items.toString();
	}

	private int count = 0;

	public static String[] oneRowSelect(String sqlStr) {
		return oneRowSelect(sqlStr, null);
	}

	public static String[] oneRowSelect(String sqlStr, String DBAlias) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String[] retStr = new String[0];
		int i;

		Session session = HibernateSessionFactory.openSession();
		conn = session.connection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlStr);
			if (rs.next()) {
				retStr = new String[rs.getMetaData().getColumnCount()];
				for (i = 0; i < retStr.length; i++) {
					retStr[i] = rs.getString(i + 1);
					if (retStr[i] == null)
						retStr[i] = "";
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {

		}
		return retStr;
	}

	public static String[] oneColSelect(String sqlStr) {
		return oneColSelect(sqlStr, null);
	}

	public static String[] oneColSelect(String sqlStr, String DBAlias) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String[] retStr = new String[0];
		int i;

		Session session = HibernateSessionFactory.openSession();
		conn = session.connection();
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sqlStr);
			rs.last();
			retStr = new String[rs.getRow()];
			rs.beforeFirst();
			while (rs.next()) {
				if (rs.getString(1) == null)
					retStr[rs.getRow() - 1] = "";
				else
					retStr[rs.getRow() - 1] = rs.getString(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				HibernateSessionFactory.closeSession();
			} catch (SQLException ex) {
			} finally {
				rs = null;
				stmt = null;
			}
		}
		return retStr;
	}

	/**
	 * 将一个对象的属性拷贝到另外一个，目的是完成FormBean到PO的属性拷贝
	 * 
	 * @param dest
	 * @param orig
	 */
	public static void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (Exception ex) {
			logger.error("\nEpisMessage >>> Copy property error: "
					+ ex.toString());
		}
	}
	
	
	/**
	 * 获得list，里面存储的map(字段名：value) map.get("字段名大写")
	 * @param conn
	 * @param sql
	 * @param object
	 * @return
	 */
	  public static List query(String sql){
		Session session = null;
		session = HibernateSessionFactory.openSession();	
		Connection conn = session.connection();
		List resultList=new ArrayList();
    	ResultSet rs=null;
    	PreparedStatement pstmt=null;
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt=getPreparedStatement(pstmt,object);
	    	rs=pstmt.executeQuery();
	    	while (rs.next()) {
				Object value = null;
				ResultSetMetaData rsm = rs.getMetaData();
				Map entity = new HashMap();
				
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					String strTempColumnName = rsm.getColumnName(i).toUpperCase();
					value = (rs.getObject(i)==null?"":rs.getObject(i));
					value=(value==null?"":value);
					//logger.info(strTempColumnName);
					entity.put(strTempColumnName, value);
				}
				resultList.add(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			free(rs,pstmt,null);
		}    
		return resultList;
	} 
	  public static void free(ResultSet rs, PreparedStatement pstmt,
				Connection conn) {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null)
						pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (conn != null)
						try {
							conn.close();
							// myDataSource.free(conn);
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			}
		}
	  
	  
	  
	  
	  	/**
	  	 * 	
	  	 * 系统日志记录
	  	 * 
	  	 *
	  	 * @param userid
	  	 * @param username
	  	 * @param content
	  	 * @param type 0 系统登录日志； 1 操作日志
	  	 * @return
	  	 * @throws SQLException
	  	 */
		public static void AddLog(String userid,String username,String content,String type,String ip) throws SQLException {
			String str = "insert into system_log (opetime,content,operid,oper,type,ipaddress)values('"+DateUtils.getCurrDateTimeStr()+"','"+content+"','"+userid+"','"+username+"','"+type+"','"+ip+"')";
			Transaction tx = null;			
			try {
				Session session = HibernateSessionFactory.openSession();
				tx = session.beginTransaction();
				Connection conn = session.connection();
				Statement statement = conn.createStatement();
				statement.execute(str);				 
				statement.close();
				tx.commit();
			} catch (Exception exception) {
				exception.printStackTrace();
				logger.error(exception.toString());
			} finally {
				HibernateSessionFactory.closeSession();
			}
		}
		/**
	  	 * 	
	  	 * 操作日志记录
	  	 *
	  	 * @param userid
	  	 * @param username
	  	 * @param content
	  	 * @param type 0 系统登录日志； 1 操作日志
	  	 * @return
	  	 * @throws SQLException
	  	 */
		public static void AddLog2(String userid,String username,String content) throws SQLException {
			String str = "insert into system_log (opetime,content,operid,oper,type)values('"+DateUtils.getCurrDateTimeStr()+"','"+content+"','"+userid+"','"+username+"','1')";
			Transaction tx = null;
			try {
				Session session = HibernateSessionFactory.openSession();
				tx = session.beginTransaction();
				Connection conn = session.connection();
				Statement statement = conn.createStatement();
				statement.execute(str);				 
				statement.close();
				tx.commit();
			} catch (Exception exception) {
				logger.error(exception.toString());
			} finally {
				HibernateSessionFactory.closeSession();
			}
		}
		@SuppressWarnings("unchecked")
		public static List findListByHql (String hq) throws SystemException,
				Exception {
			try {

				// 在数据准备完成后，获取hiernate会话
				Session ses = HibernateSessionFactory.openSession();
				// 保存对象
				Query query = ses.createQuery(hq.toString());
				return query.list();
				
			} catch (Exception e) {
				logger.debug(e);
				throw new SystemException("获取对象出错！", e);
			} finally {
				try {
					HibernateSessionFactory.closeSession();
				} catch (Exception ex) {
					logger.debug(ex);
				}
			}
		}
		//List 拼接成 sql  in 需要的条件 （'a','b','c'......）
		public static String getStr(List list){
			String str="";
			if(null!=list&&list.size()>0){
				str+="'";
				for(int i=0;i<list.size()-1;i++){
					str+=list.get(i)+"','";
				}
				str+=list.get(list.size()-1)+"'";
			}
			return str;
		}
		/**
		 * 作者：拓研-孙晓宇
		 * @Title: getCollection 
		 * @Description: 拼装查询条件 
		 * @return Collection 返回类型 
		 * @throws 
		 * 日期：2015-11-30
		 */
		public static Collection getCollection(Collection queryConds,String className,String fieldName,String dataType,String symbol,String fieldValue){
			if(!"".equals(fieldValue)&&null!=fieldValue){
				queryConds.add(new QueryCond(className+"."+fieldName,dataType,symbol, fieldValue));
				return queryConds;
			}else{
				return null;
			}
		}
		
		/**
		 * 方法功能说明： 无乱码编译 
		 * 创建：2016年3月11日 by Zzc   
		 * 修改：日期 by 修改者  
		 * 修改内容：  
		 * @参数： @param model
		 * @参数： @return
		 * @参数： @throws NoSuchMethodException
		 * @参数： @throws IllegalAccessException
		 * @参数： @throws IllegalArgumentException
		 * @参数： @throws InvocationTargetException      
		 * @return Object     
		 * @throws
		 */
		public static Object testReflect_admin(Object model) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
	        Field[] field = model.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组  
	        for(int j=0 ; j<field.length ; j++){     //遍历所有属性
	        	 	//关键。。。可访问私有变量
	            	field[j].setAccessible(true);
	                String name = field[j].getName();    //获取属性的名字
					if(!name.equals("fActivation") && !name.equals("sActivation")){
						name = name.substring(0,1).toUpperCase()+name.substring(1); //将属性的首字符大写，方便构造get，set方法
					}
	                String type = field[j].getGenericType().toString();    //获取属性的类型
	                if(type.equals("class java.lang.String")){   //如果type是类类型，则前面包含"class "，后面跟类名
	                    Method m = model.getClass().getMethod("get"+name);
	                    String value = (String) m.invoke(model);    //调用getter方法获取属性值
	                    if(value == null){
	                         //给属性设置
	                        try {
								field[j].set(model,  field[j].getType().getConstructor(field[j].getType()).newInstance(""));
							} catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                    }
	                }
	                if(type.equals("int")){
	                    Method m = model.getClass().getMethod("get"+name);
	                    Integer value = (Integer) m.invoke(model);
	                    if(value == null){
	                    	//给属性设置
	                        try {
								field[j].set(model,  field[j].getType().getConstructor(field[j].getType()).newInstance("0"));
							} catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                    }
	                }
	                if(type.equals("class java.lang.Short")){     
	                    Method m = model.getClass().getMethod("get"+name);
	                    Short value = (Short) m.invoke(model);
	                    if(value == null){
	                    	//给属性设置
	                        try {
								field[j].set(model,  field[j].getType().getConstructor(field[j].getType()).newInstance("0.0"));
							} catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                      }
	                }       
	                if(type.equals("double")){     
	                    Method m = model.getClass().getMethod("get"+name);
	                    Double value = (Double) m.invoke(model);
	                    if(value == null){                    
	                    	//给属性设置
	                        try {
								field[j].set(model,  field[j].getType().getConstructor(field[j].getType()).newInstance("0.00"));
							} catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                    }
	                }                  
	                if(type.equals("boolean")){
	                    Method m = model.getClass().getMethod("get"+name);    
	                    Boolean value = (Boolean) m.invoke(model);
	                    if(value == null){                      
	                    	//给属性设置
	                        try {
								field[j].set(model,  field[j].getType().getConstructor(field[j].getType()).newInstance("true"));
							} catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                    }
	                }
	                if(type.equals("class java.util.Date")){
	                    Method m = model.getClass().getMethod("get"+name);                    
	                    Date value = (Date) m.invoke(model);
	                    if(value != null){
	                        System.out.println("attribute value:"+value.toLocaleString());
	                    }else{
	                    	//给属性设置
	                        try {
								field[j].set(model, null);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                    }
	                }
	                if(type.equals("class java.sql.Blob")){   //如果type是类类型，则前面包含"class "，后面跟类名
	                    Method m = model.getClass().getMethod("get"+name);
	                    String value = (String) m.invoke(model);    //调用getter方法获取属性值
	                    if(value == null){
	                         //给属性设置
	                        try {
								field[j].set(model,  field[j].getType().getConstructor(field[j].getType()).newInstance(""));
							} catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                    }
	                }
	            }
	        return model;
	    }
}