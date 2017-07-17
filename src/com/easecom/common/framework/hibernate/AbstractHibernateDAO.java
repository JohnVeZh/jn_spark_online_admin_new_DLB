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

package com.easecom.common.framework.hibernate;

import com.easecom.common.filter.PagerParamsFilter;
import com.easecom.common.util.Tool;
import com.easecom.system.exception.SystemException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * @title: Hibernate数据库操作类
 * @description:
 * @author: wanghw
 * @version: 1.0
 * @create Date:2006-3-30
 */
public class AbstractHibernateDAO {
	private static Logger logger = Logger.getLogger(AbstractHibernateDAO.class);

	/**
	 * HQL查询结果集
	 * 
	 * @param querystring
	 * @return
	 * @throws HibernateException
	 */
	public Query query(String querystring) throws HibernateException {

		logger.info("List query() " + querystring);

		Session session = HibernateSessionFactory.openSession();

		try {
			Query query = session.createQuery(querystring);
			if (query == null)
				return null;
			return query;
		} catch (HibernateException he) {
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 刷新持久对象
	 * 
	 * @param model
	 * @throws HibernateException
	 */
	public void refresh(BaseModel model) throws HibernateException {

		logger.info("refresh() " + model);

		if (model == null)
			return;

		Session session = HibernateSessionFactory.openSession();

		try {
			session.refresh(model);
		} catch (HibernateException he) {
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	@SuppressWarnings("unchecked")
	public Object load(Class cls, String id) {
		logger.info("load() " + cls);
		if (cls == null)
			return null;
		Session session = HibernateSessionFactory.openSession();
		try {
			return session.load(cls, id);
		} catch (HibernateException he) {
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 数据添加
	 * 
	 * @param model
	 * @return
	 * @throws HibernateException
	 */
	public String add(BaseModel model) throws HibernateException {
		logger.info("add() " + model);
		if (model == null)
			return null;
		Session session = HibernateSessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Object obj = session.save(model);
			tx.commit();
			return (String) obj;
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 数据更新
	 * 
	 * @param model
	 * @return
	 * @throws HibernateException
	 */
	public boolean update(BaseModel model) throws HibernateException {
		logger.info("update() " + model);
		if (model == null)
			return false;
		Session session = HibernateSessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(model);
			tx.commit();
			return true;
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 数据保存
	 * 
	 * @param model
	 * @return
	 * @throws HibernateException
	 */
	public boolean save(BaseModel model) throws HibernateException {
		logger.info("save() " + model);
		if (model == null)
			return false;
		Session session = HibernateSessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(model);
			tx.commit();
			return true;
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 数据删除
	 * 
	 * @param model
	 * @return
	 * @throws HibernateException
	 */
	public boolean delete(BaseModel model) throws HibernateException {
		logger.info("delete() " + model);
		if (model == null)
			return false;
		Session session = HibernateSessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.refresh(model);
			session.delete(model);
			tx.commit();
			return true;
		} catch (HibernateException he) {
			tx.rollback();
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public int SQLExecute(String sqlstring) throws SQLException {
		logger.info("SQLExecute() " + sqlstring);
		Session session = HibernateSessionFactory.openSession();
		Connection conn = null;
		try {
			conn = session.connection();
			Statement stat = conn.createStatement();
			return stat.executeUpdate(sqlstring);
		} catch (SQLException e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}

	}

	@SuppressWarnings("unchecked")
	public List SQLQuery(String sqlstring) throws SQLException {
		logger.info("SQLQuery() " + sqlstring);
		Session session = HibernateSessionFactory.openSession();
		List querylist = new ArrayList();
		Map querymap = null;
		try {
			Connection conn = session.connection();
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(sqlstring);
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				querymap = new HashMap();
				for (int i = 1; i < rsmd.getColumnCount()+1; i++) {
					querymap.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				querylist.add(querymap);
			}
			return querylist;
		} catch (SQLException e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/**
	 * 将一个对象的属性拷贝到另外一个，目的是完成FormBean到PO的属性拷贝
	 * 
	 * @param dest
	 * @param orig
	 */
	protected void copyProperties(Object dest, Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("\nEpisMessage >>> Copy property error: " + ex.toString());
		}
	}

	/**
	 * 输出JSON相应字符串 {"json-response":{"result":true,"msg":"abc"}}
	 * 
	 * @param result
	 * @param message
	 * @return
	 * @throws IOException
	 */
	protected String outputJsonResponse(boolean result, String message)
			throws IOException {
		try {
			JSONObject json = new JSONObject();
			json.put("result", result);
			json.put("msg", message);
			JSONObject doc = new JSONObject();
			doc.put("json-response", json);
			return doc.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * 输出JSON相应的字符串
	 * {"json-response":{"result":true,"msg":"abc","msg":{"node":"node1","id":"id1"}}}
	 * 
	 * @param response
	 * @param result
	 * @param message
	 * @param customMap
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected String outputJsonResponse(HttpServletResponse response,
			boolean result, String message, Map customMap) throws IOException {
		try {
			JSONObject json = new JSONObject();
			json.put("result", result);
			json.put("msg", message);
			if (customMap != null && customMap.size() > 0)
				json.put("custom-content", customMap);
			JSONObject doc = new JSONObject();
			doc.put("json-response", json);
			return doc.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * 输出ext列表的json数据
	 * 输出格式为：{totalProperty:12,root:[{"name":"name1","id":"id1"},{"name":"name2","id":"id2"}]}
	 * 
	 * @param totalProperty
	 * @param list
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected String outputExtJsonResponse(int totalProperty, List list)
			throws IOException {
		try {
			JSONObject json = new JSONObject();
			json.put("totalProperty", totalProperty);
			if (list != null && list.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(list);
				json.put("root", jsonArray);
			}
			return json.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * 输出ext列表的json数据 map:自定义属性
	 * 输出格式为：{totalProperty:12,root:[{"name":"name1","id":"id1"},{"name":"name2","id":"id2"}],custom{"key1":"value1","key2":"value2"...}}
	 * 
	 * @param totalProperty
	 * @param list
	 * @param map
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected String outputExtJsonResponse(int totalProperty, List list, Map map)
			throws IOException {
		try {
			JSONObject json = new JSONObject();
			json.put("totalProperty", totalProperty);
			if (list != null && list.size() > 0) {
				JSONArray jsonArray = JSONArray.fromObject(list);
				json.put("root", jsonArray);
			}
			if (map != null && map.size() > 0) {
				json.put("custom", map);
			}
			return json.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * 动态加载实体类对象 （无延迟加载）
	 * 
	 * @param cls
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object loadObject(Class cls, String id) {
		logger.info("load() " + cls);
		Object ob = null;
		if (cls == null)
			return null;
		Session session = HibernateSessionFactory.openSession();
		try {
			ob = session.load(cls, id);
			Hibernate.initialize(ob);
			return ob;
		} catch (HibernateException he) {
			throw he;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	

	/**
	 * 根据hql查询一个list
	 * @param hql
	 * @param firstResult
	 * @param maxResult
	 * @return
	 * Jul 30, 20134:56:05 PM
	 */
	@SuppressWarnings("unchecked")
	public List<?> getListByHql(String hql, int firstResult, int maxResult) {
		Session session = null;
		List<?> list = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(hql);
			if (0 != maxResult) {
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResult);
				Integer rowCount = PagerParamsFilter.pagerFilterLocal.get();
				if (rowCount != null) {
					query.setMaxResults(rowCount);
				}
			}
			list = query.list();
			if (null == list) {
				return null;
			} else {
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e);
			e.printStackTrace();
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	
	
	/**
	 * 根据hql查询实体
	 * @param hql
	 * @return
	 * Jul 30, 20134:55:53 PM
	 */
	public Object getObjectByHql(String hql) {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(hql);
			query.setMaxResults(1);
			Object object = query.uniqueResult();
			if (null == object) {
				return null;
			} else {
				return object;
			}
		} catch (Exception e) {
			logger.debug(e);
			return null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	/**
	 * 合并多个id为查询条件的字符串
	 * @param ids
	 * @return
	 * @auth 周志君
	 * @created 2014-9-4 上午11:38:11
	 */
	public String concatIds(String[] ids) {
		String id = "";
		if (null!=ids && ids.length>0) {
			String splitChar = "";
			for (String s : ids) {
				id = id + splitChar + "'" + s + "'";
				splitChar = ",";
			}
		}
		
		return id;
	}
	/**
	 * 作者：拓研-孙晓宇
	 * @Title: getCharacterCn 
	 * @Description: 根据code获取getCharacterCn
	 * @return String 返回类型 
	 * @throws 
	 * 2015-11-16
	 */
	public String  getCharacterCn(String code) throws SystemException{
		String chcn="";
		try {
			chcn=Tool.getValue("select sct.character_cn from system_code_table as sct where sct.code='"+code+"' and 1=1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chcn;
	}

	/**
	 * 查询实体类
	 * @param hql
	 * @return
	 */
	public <T>T queryObjectByHql(String hql) {
		List<T> list = this.queryListByHql(hql);
		return list.size()>0 ? list.get(0) : null;
	}

	/**
	 * 查询实体类列表
	 * @param hql
	 * @return
	 */
	public <T>List<T> queryListByHql(String hql) {
		return this.queryListByHql(hql, 0, 0);
	}

	/**
	 * 查询实体类列表
	 * @param hql
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public <T>List<T> queryListByHql(String hql, int firstResult, int maxResult) {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(hql);
		if (0 != maxResult) {
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
		}

		return query.list();
	}

	/**
	 * 查询一个字段，如count()函数等
	 * @param sql
	 * @return
	 */
	public <T>T queryObjectBySql(String sql) {
		Object val = null;

		List<Map<String, Object>> list = this.queryListBySql(sql);
		if(list.size() > 0) {
			Map<String, Object> map = list.get(0);
			Iterator<Object> it = map.values().iterator();
			if(it.hasNext()) {
				val = it.next();
			}
		}

		return (T) val;
	}

	/**
	 * 查询一个字段，如count()函数等
	 * @param sql
	 * @return
	 */
	public <T>List<T> queryObjectListBySql(String sql) {
		List<T> list = new ArrayList<T>();

		Session session = HibernateSessionFactory.getSession();
		Connection conn = session.connection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add((T) rs.getObject(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}
		}

		return list;
	}

	/**
	 * 查询Map&lt;String, Object>
	 * @param sql
	 * @return
	 */
	public Map<String, Object> queryMapBySql(String sql) {
		List<Map<String, Object>> list = queryListBySql(sql);
		return list.size()>0 ? list.get(0) : new HashMap<String, Object>();
	}

	/**
	 * 查询List&lt;Map>
	 * @param sql
	 * @return
	 */
	public List<Map<String, Object>> queryListBySql(String sql) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Session session = HibernateSessionFactory.getSession();
		Connection conn = session.connection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					map.put(rsmd.getColumnName(i).toUpperCase(), rs.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}
		}

		return list;
	}

	public List<Map<String, Object>> queryListBySqlNotUpperCase(String sql) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Session session = HibernateSessionFactory.getSession();
		Connection conn = session.connection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}
		}

		return list;
	}

	/**
	 * 保存实体类
	 * @param model
	 * @return
	 */
	public String execSave(BaseModel model) {
		Session session = HibernateSessionFactory.getSession();
		Object obj = session.save(model);
		session.flush();
		return String.valueOf(obj);
	}

	/**
	 * 更新实体类
	 * @param model
	 * @return
	 */
	public boolean execUpdate(BaseModel model) {
		Session session = HibernateSessionFactory.getSession();
		session.update(model);
		session.flush();
		return true;
	}

	/**
	 * 执行sql
	 * @param sql
	 * @return
	 */
	public boolean execSql(String sql) {
		boolean flag = false;

		Session session = HibernateSessionFactory.getSession();
		Connection conn = session.connection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			flag = pstmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {}
			}
		}
		session.flush();

		return flag;
	}

	public void execSave(List<? extends BaseModel> modelList) {
		Session session = HibernateSessionFactory.getSession();
		for(int i=0; i<modelList.size();) {
			session.save(modelList.get(i));
			if(++i%100 == 0) {
				session.flush();
			}
		}
		session.flush();
	}
}
