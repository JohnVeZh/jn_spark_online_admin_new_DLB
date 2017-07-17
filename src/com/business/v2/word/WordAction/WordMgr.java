package com.business.v2.word.WordAction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.business.ProductType.ProductType;
import com.business.v2.question.TbQuestionTranslation;
import com.business.v2.special.TbSpecialCatalog;
import com.business.v2.special.TbWordSection;
import com.business.v2.word.TbWord;
import com.business.v2.word.TbWordQuestion;
import com.business.v2.word.TbWordQuestionOption;
import com.easecom.common.framework.hibernate.AbstractHibernateDAO;
import com.easecom.common.framework.hibernate.BaseModel;
import com.easecom.common.framework.hibernate.HibernateSessionFactory;
import com.easecom.common.util.DateUtils;
import com.easecom.common.util.ListContainer;
import com.easecom.common.util.LogUtils;
import com.easecom.common.util.PageAction;
import com.easecom.common.util.QueryHelper;
import com.easecom.common.util.Tool;
import com.easecom.common.util.TreeNode;
import com.easecom.system.exception.SystemException;

public class WordMgr extends AbstractHibernateDAO{
	private static LogUtils logger = LogUtils.getLogger(TbWord.class);
	/**
	 * 生成功能树
	 * 
	 * @param parentId
	 * @return
	 * @throws SystemException
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getFuntree(String sql) throws SystemException, Exception {
		List list = null;
		List treeNodes = new ArrayList();
		try {
			Collection queryConds = new ArrayList();
			Session ses = HibernateSessionFactory.openSession();
			String queryString = "(select t.* from tb_special_catalog t where t.p_id='0101' order by t.sort_order) union all  (select t.* from tb_special_catalog t where t.p_id='0201' order by t.sort_order)";
			Query query = ses.createSQLQuery(queryString).addEntity(TbSpecialCatalog.class);
			if (query == null) {
				list = new ArrayList();
			} else {
				list = query.list();
			}
			Iterator tempArray = list.iterator();
			
			TbSpecialCatalog function = null;
			TreeNode treeNode = null;
			while (tempArray.hasNext()) {
				function =  (TbSpecialCatalog)tempArray.next();
				treeNode = new TreeNode(function.getId(), (function.getPId().equals("0101")?"四级":"六级")+function.getName(), sql);
				treeNode.setTreeNodeURL("Word.do?act=list&Pid="+function.getId());//+"&state="+function.getState()
				treeNodes.add(treeNode);
			}
			return treeNodes;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			return null;
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception ex) {
				logger.error(ex);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public ListContainer list(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage,String pid) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();

			// 查询对象
			String hq = null;
			hq="select b.sort_order sortOrder,c.* from tb_special_catalog a "
					+ "LEFT JOIN tb_word_section b on a.id=b.catalog_id "
					+ "LEFT JOIN tb_word c on b.word_id = c.id where 1=1 and a.p_id like '"+ pid+"%"+"' "+QueryHelper.toAndQuery(queryConds)+" ORDER BY b.sort_order DESC";
			Query qCount = ses
					.createSQLQuery("select count(*) from tb_special_catalog a "
							+ "LEFT JOIN tb_word_section b on a.id=b.catalog_id "
							+ "LEFT JOIN tb_word c on b.word_id = c.id where 1=1 and a.p_id like '"+ pid+"%"+"' "+QueryHelper.toAndQuery(queryConds)+"");

			// 新建并设置列表容器
			ListContainer lc = new ListContainer();
			lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码
			lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
			lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
			lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略它
			List L = qCount.list();
			int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString()
					: "0");
			lc.setTotalItem(totalItems); // 设置记录总数
			Query query = ses.createSQLQuery(hq.toString()).addEntity(TbWord.class);
			query.setMaxResults(lc.getItemsInPage());
			query.setFirstResult(lc.calculateNextPageIndex());
			lc.getList().addAll(query.list());// 装填指定页的列表数据到列表容器
			return lc;
		} catch (Exception e) {
			logger.debug(e);
			throw new SystemException("查询数据列表出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public ListContainer listByObj(Collection queryConds, int currentPageInt,
			int itemsInPage, String action, int jumpPage,String id) throws SystemException,
			Exception {
		try {
			// 在数据准备完成后，获取hiernate会话
			Session ses = HibernateSessionFactory.openSession();
			
			// 查询对象
			String sql="SELECT a.* FROM tb_word_question_option a LEFT JOIN tb_word_question b ON a.question_id = b.id WHERE b.word_id = '"+id+"'";
			Query qCount = ses
					.createSQLQuery("SELECT count(a.id) FROM tb_word_question_option a LEFT JOIN tb_word_question b ON a.question_id = b.id WHERE b.word_id = '"+id+"'");
			
			// 新建并设置列表容器
			ListContainer lc = new ListContainer();
			lc.setCurrentPage(currentPageInt); // 设置分页前的当前页码
			lc.setItemsInPage(itemsInPage); // 设置每页可显示的记录数，默认是15条
			lc.setPageAction(PageAction.fromString(action)); // 设置分页行为，默认时为去首页
			lc.setJumpIndex(jumpPage); // 设置跳页的目标页码，如果分页行为不是跳页的话会忽略它
			List L = qCount.list();
			int totalItems = Integer.parseInt(L.size() > 0 ? L.get(0).toString()
					: "0");
			lc.setTotalItem(totalItems); // 设置记录总数
			Query query = ses.createSQLQuery(sql.toString()).addEntity(TbWordQuestionOption.class);
			query.setMaxResults(lc.getItemsInPage());
			query.setFirstResult(lc.calculateNextPageIndex());
			lc.getList().addAll(query.list());// 装填指定页的列表数据到列表容器
			return lc;
		} catch (Exception e) {
			logger.debug(e);
			throw new SystemException("查询数据列表出错！", e);
		} finally {
			try {
				HibernateSessionFactory.closeSession();
			} catch (Exception e) {
				logger.debug(e);
			}
		}
	}
	private String SqlQueryString (String sql){
		try {
			Session session = HibernateSessionFactory.openSession();
			org.hibernate.SQLQuery createSQLQuery = session.createSQLQuery(sql.toString());
			String string = (String) createSQLQuery.uniqueResult().toString();
			return string;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateSessionFactory.closeSession();
		}
	}
	private String SqlQueryInt (String sql){
		try {
			Session session = HibernateSessionFactory.openSession();
			org.hibernate.SQLQuery createSQLQuery = session.createSQLQuery(sql.toString());
			String string =  createSQLQuery.uniqueResult().toString();
			return string;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			HibernateSessionFactory.closeSession();
		}
	}
	//sql添加
	public  void SqlSave(String sqlstring) throws SQLException {
		logger.info("SQLExecute() " + sqlstring);
		Session session = HibernateSessionFactory.openSession();
		Connection conn = null;
		try {
			conn = session.connection();
			Statement stat = conn.createStatement();
			 stat.executeUpdate(sqlstring);
		} catch (SQLException e) {
			throw e;
		} finally {
			HibernateSessionFactory.closeSession();
		}

	}
	//model对象添加
	private void SqlInsertTwoObj (Object obj, Object obj1){
		Session session = HibernateSessionFactory.openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.save(obj);
			session.save(obj1);
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			HibernateSessionFactory.closeSession();
		}
	}
	private void SqlInsertObj (Object obj){
		Session session = HibernateSessionFactory.openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.save(obj);
			tx.commit();
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			HibernateSessionFactory.closeSession();
		}
	}
	public void listUpload(List<TbWordActionForm> wordList, List<TbWordQuestion> questionList, List<TbWordQuestionOption> optionList) throws SQLException {
		try {
			for (TbWordActionForm word : wordList) {
			//分组的最大ID
			String MaxTSCId=SqlQueryString("select max(id) from tb_special_catalog where id like '"+word.getTypes()+"%' and p_id != '"+word.getType()+"'");
			if(MaxTSCId==null || "".equals(MaxTSCId) ){
				TbSpecialCatalog catalog=new TbSpecialCatalog();
				catalog.setId(word.getTypes()+"0001");
				catalog.setPId(word.getTypes());
				catalog.setName("第1组");
				catalog.setSortOrder(1);
				catalog.setIsDel("0");
				catalog.setCreateTime(new Date());
				
				TbWordSection section=new TbWordSection(); 
				section.setId(UUID.randomUUID().toString().replace("-", ""));
				section.setWordId(word.getId());
				section.setSection(word.getCet());
				section.setCatalogId(word.getTypes()+"0001");
				section.setSortOrder(1);
				
				TbWord wordAdd=new TbWord();
				wordAdd.setId(word.getId());
				wordAdd.setWord(word.getWord());
				wordAdd.setPronunciationUrl(word.getPronunciationUrl());
				wordAdd.setParaphrase(word.getParaphrase());
				wordAdd.setPhoneticSymbol(word.getPhoneticSymbol());
				wordAdd.setExampleReference(word.getExampleReference());
				wordAdd.setExampleSentence(word.getExampleSentence());
				wordAdd.setExampleUrl(word.getExampleUrl());
				wordAdd.setCreateTime(new Date());
				String addWordSql="INSERT INTO tb_word(id,word,phonetic_symbol,pronunciation_url,paraphrase,example_sentence,example_reference,example_url,create_time)"
						+ "values("+getFormatField(wordAdd.getId())+","+getFormatField(wordAdd.getWord())+","+getFormatField(wordAdd.getPhoneticSymbol())+","
						+ ""+getFormatField(wordAdd.getPronunciationUrl())+","+getFormatField(wordAdd.getParaphrase())+","+getFormatField(wordAdd.getExampleSentence())+","
						+ ""+getFormatField(wordAdd.getExampleReference())+","+getFormatField(wordAdd.getExampleUrl())+",'"+DateUtils.getCurrDateTimeStr()+"')";
				SqlInsertTwoObj(catalog,section);
				SqlSave(addWordSql);
				continue;
			}
			
			//最大分组里已经存才多少个
			String MaxCount=SqlQueryInt("select count(*) from tb_word_section where  catalog_id = '"+MaxTSCId+"'");
			//最大排序号
			String MaxSortOrder=SqlQueryInt("select max(sort_order) from tb_word_section where  catalog_id = '"+MaxTSCId+"'");
			//判断最大分组
			int maxID=Integer.parseInt(MaxTSCId);
			//最大分组ID里有多少个单词
			int maxcounts=Integer.parseInt(MaxCount);
			//最大分组ID里单词最大排序
			int SortOrder=Integer.parseInt(MaxSortOrder);
			int sortOrders=SortOrder+1;
			
			TbWord wordAdd=new TbWord();
			wordAdd.setId(word.getId());
			wordAdd.setWord(word.getWord());
			wordAdd.setPronunciationUrl(word.getPronunciationUrl());
			wordAdd.setParaphrase(word.getParaphrase());
			wordAdd.setPhoneticSymbol(word.getPhoneticSymbol());
			wordAdd.setExampleReference(word.getExampleReference());
			wordAdd.setExampleSentence(word.getExampleSentence());
			wordAdd.setExampleUrl(word.getExampleUrl());
			wordAdd.setCreateTime(new Date());
			String addWordSql="INSERT INTO tb_word(id,word,phonetic_symbol,pronunciation_url,paraphrase,example_sentence,example_reference,example_url,create_time)"
					+ "values("+getFormatField(wordAdd.getId())+","+getFormatField(wordAdd.getWord())+","+getFormatField(wordAdd.getPhoneticSymbol())+","
					+ ""+getFormatField(wordAdd.getPronunciationUrl())+","+getFormatField(wordAdd.getParaphrase())+","+getFormatField(wordAdd.getExampleSentence())+","
					+ ""+getFormatField(wordAdd.getExampleReference())+","+getFormatField(wordAdd.getExampleUrl())+",'"+DateUtils.getCurrDateTimeStr()+"')";
			SqlSave(addWordSql);
			if(maxcounts==20 || maxcounts>20){
				//最大分组号
				String MaxSord=SqlQueryString("select max(sort_order) from tb_special_catalog where id like '"+word.getTypes()+"%'");
				//判断最大分组号
				int MaxSords=Integer.parseInt(MaxSord);
				int maxAddSord=MaxSords+1;
				int addId=maxID+1;
				String addIds="0"+addId;
				String groudByName="第"+maxAddSord+"组";
				//创建分组
				TbSpecialCatalog catalog=new TbSpecialCatalog();
				catalog.setId(addIds);
				catalog.setPId(word.getTypes());
				catalog.setName(groudByName);
				catalog.setSortOrder(maxAddSord);
				catalog.setIsDel("0");
				catalog.setCreateTime(new Date());
				
				TbWordSection section=new TbWordSection(); 
				section.setId(UUID.randomUUID().toString().replace("-", ""));
				section.setWordId(word.getId());
				section.setSection(word.getCet());
				section.setCatalogId(addIds);
				section.setSortOrder(sortOrders);
				
				SqlInsertTwoObj(catalog,section);
			}else{
				TbWordSection section=new TbWordSection(); 
				section.setId(UUID.randomUUID().toString().replace("-", ""));
				section.setWordId(word.getId());
				section.setSection(word.getCet());
				section.setCatalogId(MaxTSCId);
				section.setSortOrder(sortOrders);
				SqlInsertObj(section);
			}
		}
			for(TbWordQuestionOption option : optionList) {
				SqlInsertObj(option);
			}
			for(TbWordQuestion question : questionList) {
				SqlInsertObj(question);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			HibernateSessionFactory.closeSession();
		}
	}
	/**
	 * sql拼接去''
	 * @param obj
	 * @return
	 */
	public static String getFormatField(Object obj) {
		String resultStr = "NULL";
		if (obj instanceof Integer) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr =  obj.toString() ;
			}
		} else if(obj instanceof String) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = "'" + obj.toString() + "'";
			}
		} else if(obj instanceof Timestamp) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = "'" + obj.toString() + "'";
			}
		} else if(obj instanceof Double) {
			if(obj == null || obj.toString().equals("")) {
				resultStr = "NULL";
			} else {
				resultStr = obj.toString();
			}
		}
		return resultStr;
	}	
}
