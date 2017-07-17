package com.business.v2.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.easecom.common.framework.hibernate.BaseModel;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;

public class TT {
	/**
	 * true根据数据库已有数据及excel数据自然顺序排序；false按照excel写入顺序排序
	 */
	public static final boolean INTERNAL_SORT = true;
	
	public static String queryString(String sql) {
		Object obj = queryBaseObject(sql);
		return obj==null ? null : String.valueOf(obj);
	}
	
	public static int queryInt(String sql) {
		Object obj = queryBaseObject(sql);
		if(obj == null) {
			return -1;
		} else if(obj instanceof BigDecimal) {
			return ((BigDecimal) obj).intValue();
		} else {
			return new BigDecimal(String.valueOf(obj)).intValue();
		}
	}
	
	private static Object queryBaseObject(String sql) {
		Object obj = null;
    	
        Session session = HibernateSessionFactory.getSession();
        Connection conn = session.connection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				obj = rs.getObject(1);
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
        
        return obj;
	}
	
	public static void save(List<BaseModel> modelList) {
		Session session = HibernateSessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			for(BaseModel model : modelList) {
				session.save(model);
			}
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static int valueInt(HSSFCell cell) {
		String value = value(cell, true, true);
		return value!=null ? Double.valueOf(value).intValue() : -1;
	}
	
	public static boolean valueBoolean(HSSFCell cell) {
		String value = value(cell, true, true);
		return value!=null && Double.valueOf(value)==1;
	}
	
	public static String value(HSSFCell cell) {
		return value(cell, true, true);
	}
	
	public static String value(HSSFCell cell, boolean trim, boolean nul) {
		String value = null;
		if(cell != null) {
			value = cell.toString();
			if(value!=null && trim) {
				value = value.trim();
			}
		}
		if("".equals(value) && nul) {
			value = null;
		}
		return nul || value!=null ? value : "";
	}
	
	public static HSSFSheet getSheet(InputStream file, int sheetIndex) {
		HSSFSheet sheet = null;
		InputStream in = null;
		try {
			in = file;
			HSSFWorkbook book = new HSSFWorkbook(in);
			sheet = book.getSheetAt(sheetIndex);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {}
			}
		}
		
		return sheet;
	}
	
	/**
	 * catIdMap、catOrderMap、trainOrderMap
	 */
	private static Map<String, Map<String, Integer>> seqMap = new HashMap<String, Map<String,Integer>>();
	static {
		seqMap.put("catIdMap", new HashMap<String, Integer>());
		seqMap.put("catOrderMap", new HashMap<String, Integer>());
		seqMap.put("trainOrderMap", new HashMap<String, Integer>());
	}
	
	public static synchronized String nextCatId(String pid, int sufLen) {
		Map<String, Integer> catIdMap = seqMap.get("catIdMap");
		Integer cid = catIdMap.get(pid);
		if(cid == null) {
			String id = TT.queryString("select t.id from tb_special_catalog t where t.p_id='" + pid + "' order by t.id desc limit 1");
			cid = id==null ? 0 : Integer.valueOf(id.substring(pid.length()), 10);
		}
		
		cid++;
		catIdMap.put(pid, cid);
		
		StringBuilder catId = new StringBuilder(pid);
		for(int i=String.valueOf(cid).length(); i<sufLen; i++) {
			catId.append(0);
		}
		return catId.append(cid).toString();
	}

	public static synchronized int nextCatOrder(String pid) {
		Map<String, Integer> catOrderMap = seqMap.get("catOrderMap");
		Integer cst = catOrderMap.get(pid);
		if(cst == null) {
			cst = TT.queryInt("select t.sort_order from tb_special_catalog t where t.p_id='" + pid + "' order by t.sort_order desc limit 1");
			cst = cst==-1 ? 0 : cst;
		}
		
		cst++;
		catOrderMap.put(pid, cst);
		
		return cst;
	}
	
	public static synchronized int nextTrainOrder(String catId) {
		Map<String, Integer> catOrderMap = seqMap.get("trainOrderMap");
		Integer cst = catOrderMap.get(catId);
		if(cst == null) {
			cst = TT.queryInt("select t.sort_order from tb_special_training t where t.catalog_id='" + catId + "' order by t.sort_order desc limit 1");
			cst = cst==-1 ? 0 : cst;
		}
		
		cst++;
		catOrderMap.put(catId, cst);
		
		return cst;
	}
}
